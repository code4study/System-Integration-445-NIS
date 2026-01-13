/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author bluez
 */
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springapp.web.model.Employee;
@Component
public class EmployeeSender {

     private static final String QUEUE_NAME = "AP";

     // add
    public void sendEmployeeCreatedMessage(Employee  emp) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            System.out.println("OK CONECTED");
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = emp.getIdEmployee() + "|" + emp.getFirstName() + "|" + emp.getLastName();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent: " + message);
        }
    }
    // edit
}
