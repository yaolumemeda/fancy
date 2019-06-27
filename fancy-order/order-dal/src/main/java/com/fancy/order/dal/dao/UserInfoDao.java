package com.fancy.order.dal.dao;


public class UserInfoDao {

    private String uid;

    private String userName;

    private String openId;

    private String sessionKey;

    private String unionId;

    private String userSelfInstruction;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSelfInstruction() {
        return userSelfInstruction;
    }

    public void setUserSelfInstruction(String userSelfInstruction) {
        this.userSelfInstruction = userSelfInstruction;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
