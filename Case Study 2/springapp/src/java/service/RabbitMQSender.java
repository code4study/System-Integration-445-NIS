/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

// RabbitMQSender.java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQSender {
    private static final String QUEUE_NAME = "AP";

    public static void sendEmployeeMessage(String jsonData) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost"); // IP RabbitMQ server
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                channel.basicPublish("", QUEUE_NAME, null, jsonData.getBytes("UTF-8"));
                System.out.println("✅ Sent message to queue AP: " + jsonData);
            }
        } catch (Exception e) {
            System.err.println("❌ RabbitMQ Error: " + e.getMessage());
        }
    }
}
