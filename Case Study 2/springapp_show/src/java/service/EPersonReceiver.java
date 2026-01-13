/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 *
 * @author bluez
 */
public class EPersonReceiver {

    private void listen(String queueName) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received from " + queueName + ": '" + message + "'");

            // TODO: Parse message và merge EPerson => lưu cache hoặc gửi WebSocket
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }

    public void start() throws Exception {
        listen("AP");
        listen("AE");
    }
}
