package com.fancy.order.facade;

import com.fancy.order.core.utils.OrderCodeUtil;
import com.fancy.order.dal.dao.OrderDao;
import com.fancy.order.dal.dao.UserInfoDao;
import com.fancy.order.dal.mapper.OrderMapper;
import com.fancy.order.service.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static com.fancy.order.core.enums.OrderStatusEnum.INITIAL;
import static org.hamcrest.CoreMatchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderFacadeApplicationTests {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private MockHttpSession session;


    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        UserInfoDao user =new UserInfoDao();
        user.setUid("123");
        session.setAttribute("user",user);
    }
    @Test
    public void addOrderControllerTest() throws Exception{
        String json="{\"spuId\":\"001\",\"count\":\"1\",\"address\":\"西安\",\"name\":\"yao\",\"telephone\":\"1234\"}";
        mvc.perform(MockMvcRequestBuilders.get("/order/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .session(session)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }




/*    @Test
    public void addOrderMapperTest() {
        OrderDao order = new OrderDao();
        order.setOid(OrderCodeUtil.createOrderNumber());
        order.setStatus(INITIAL.getCode());
        order.setOrderCreateTime(LocalDateTime.now());
        Assert.assertThat(orderMapper.addOrder(order),is(1));
    }

    @Test
    public void addOrderServiceTest() {
        OrderDao order = new OrderDao();
        Assert.assertThat(orderService.addOrder(order),is(1));
    }*/

}
