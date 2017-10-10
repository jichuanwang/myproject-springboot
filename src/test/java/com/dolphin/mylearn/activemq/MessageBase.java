package com.dolphin.mylearn.activemq;

/**
 * Created by jichuan.wang on 2017/9/30.
 */
public abstract class MessageBase {
    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin";
    public static final String BROKE_UER = "failover:(tcp://localhost:61616)";
    public void sendMsg(){

    }
    public void receiveMsg(){

    }
}
