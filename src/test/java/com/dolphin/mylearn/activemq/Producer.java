package com.dolphin.mylearn.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by jichuan.wang on 2017/9/30.
 */
public class Producer extends MessageBase{
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME,
                PASSWORD,BROKE_UER);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("my-queue");
        MessageProducer messageProducer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText("hello activemq");
        messageProducer.send(textMessage);
        session.commit();
        connection.close();

    }
}
