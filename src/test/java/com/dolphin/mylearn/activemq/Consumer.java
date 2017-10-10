package com.dolphin.mylearn.activemq;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by jichuan.wang on 2017/9/30.
 */
public class Consumer extends MessageBase{
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME,
                PASSWORD,BROKE_UER);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("my-queue");
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true){
            TextMessage textMessage = (TextMessage)messageConsumer.receive();
            if(textMessage == null){
                break;
            }
            PrintUtil.print(textMessage.getText());
        }
        session.commit();
        connection.close();

    }
}
