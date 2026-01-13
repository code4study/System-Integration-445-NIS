/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package until;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;

/**
 *
 * @author bluez
 */
public class RabbitMQConfig {
     public static final String QUEUE_AP = "AP";
    public static final String QUEUE_AE = "AE";

    @Bean
    public Queue apQueue() {
        return new Queue(QUEUE_AP);
    }

    @Bean
    public Queue aeQueue() {
        return new Queue(QUEUE_AE);
    }
}
