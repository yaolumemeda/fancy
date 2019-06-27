package com.fancy.order.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.fancy.order.core.constants.WxConstant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.*;

import static com.fancy.order.core.constants.WxConstant.connectTimeoutMs;
import static org.springframework.http.HttpHeaders.USER_AGENT;


public class wxpayUtil {

    /*
     * 调用统一下单api
     * 返回预付单信息
     * */
    public static JSONObject wxPay(String oid,String spuName,String money,HttpServletRequest request) {
        JSONObject json = new JSONObject();
        try {
            //生成的随机字符串
            String nonce_str = getRandomStringByLength(32);
            //生成签名
            String sign="";
            //商品名称,后期解决乱码问题
            String body = new String(spuName.getBytes("ISO-8859-1"), "UTF-8");
            //获取终端ip地址
            String spbill_create_ip = getIpAddr(request);
            Map<String, String> map = new HashMap<String, String>();
            map.put("appid", WxConstant.appId);
            map.put("mch_id", WxConstant.mch_id);
            map.put("nonce_str", nonce_str);
            map.put("body", body);
            map.put("out_trade_no", oid);//商户订单号
            map.put("total_fee", money);
            map.put("spbill_create_ip", spbill_create_ip);
            map.put("notify_url", WxConstant.notify_url);
            map.put("trade_type", WxConstant.TRADETYPE);
            map.put("sign", generateSignature(map));
            String xml=mapToXml(map);
            




        } catch (Exception e) {
            return null;
        }


        return null;
    }

   public static String payRequest(String xml) throws Exception {
       BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
               RegistryBuilder.<ConnectionSocketFactory>create()
                       .register("http", PlainConnectionSocketFactory.getSocketFactory())
                       .register("https", SSLConnectionSocketFactory.getSocketFactory())
                       .build(),
               null,
               null,
               null
       );
       HttpClient httpClient = HttpClientBuilder.create()
               .setConnectionManager(connManager)
               .build();

       HttpPost httpPost = new HttpPost(WxConstant.pay_url);
       HttpResponse httpResponse = null;
       try {
           RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(WxConstant.readTimeoutMs).setConnectTimeout(WxConstant.connectTimeoutMs).build();
           httpPost.setConfig(requestConfig);

           StringEntity postEntity = new StringEntity(xml, "UTF-8");
           httpPost.addHeader("Content-Type", "text/xml;charset=utf8");
           //httpPost.addHeader("User-Agent", USER_AGENT + " " + config.getMchID());
           httpPost.setEntity(postEntity);

           httpResponse = httpClient.execute(httpPost);
           HttpEntity httpEntity = httpResponse.getEntity();
           return EntityUtils.toString(httpEntity, "UTF-8");
       } catch (Exception e) {
           e.printStackTrace();
           return e.getMessage();
       } finally {

           // 释放资源
           if (httpClient != null) {
               ((CloseableHttpClient) httpClient).close();

           }
       }
   }


       public static String generateSignature (Map < String, String > map) throws Exception {
           List<String> keys = new ArrayList<String>(map.keySet());
           Collections.sort(keys);
           StringBuilder sb = new StringBuilder();
           for (String k : keys) {
               sb.append(k).append("=").append(map.get(k).trim()).append("&");
           }
           sb.append("key=").append(WxConstant.key);
           MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] array = md.digest(sb.toString().getBytes("UTF-8"));
           StringBuilder s = new StringBuilder();
           for (byte item : array) {
               int i = item & 0xFF;
               if (i < 16) {
                   s.append("0");
               }
               s.append(Integer.toHexString(i));
           }
           return s.toString().toUpperCase();
       }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        Document document = WXPayXmlUtil.newDocument();
       Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
		//将源树转换为结果树(将dom树转化为String)
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		//指定了当输出结果树时，Transformer 是否可以添加额外的空白
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }
    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     *
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilder documentBuilder = WXPayXmlUtil.newDocumentBuilder();
          //Document document = db.parse(new InputSource(new StringReader(strXML)));
			InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
			//合并相邻的文本节点并删除空的文本节点
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
			NodeList nodeList=root.getChildNodes();
            for (int idx =0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            throw ex;
        }

    }




    /*
     * 获取随机字符串
     * */
    public static String getRandomStringByLength(int len) {
        String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new SecureRandom();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();

    }
    /*
    * 获取终端ip
    * */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip!=null && ip.length() !=0 && !"unknown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，X-Forwarded-For: client, proxy1, proxy2，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
           ip=request.getHeader("X-Real-IP");
        if(ip!=null && ip.length() !=0 && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static void main(String[] args) throws Exception{
        Map<String, String> map = new HashMap();
        map.put("appid", "123456");

        map.put("body", "商品");
        map.put("out_trade_no", "20190420");//商户订单号
        System.out.println(generateSignature(map));
        System.out.println(mapToXml(map));
        String xml = "<xml version='1.0' encoding='UTF-8'>" + "<appid>123456</appid>"
                + "<body>商品</body>" + "</xml>";
        System.out.println(xmlToMap(xml));


    }

}
