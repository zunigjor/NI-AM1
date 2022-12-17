package cz.cvut.fit.niam1.orderprocessor;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms
public class OrderProcessorApplication {

    private final Logger logger = LoggerFactory.getLogger(OrderProcessorApplication.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue bookingsQueue;

    @Bean
    public Queue bookingsQueue() {
        return new ActiveMQQueue("bookingsQueue");
    }

    @Autowired
    private Queue newTripsQueue;

    @Bean
    public Queue newTripsQueue() {
        return new ActiveMQQueue("newTripsQueue");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessorApplication.class, args);
    }

    @JmsListener(destination = "allOrdersQueue")
    public void readMessage(String message) throws Exception {
        logger.info("Received message: {}", message);

        String booking_or_trip = message.trim().split(";")[0];

        if (booking_or_trip.equals("booking")) {
            jmsTemplate.convertAndSend(bookingsQueue, message);
            logger.info("Adding {} to {}", message, bookingsQueue.getQueueName());
        } else if (booking_or_trip.equals("trip")) {
            jmsTemplate.convertAndSend(newTripsQueue, message);
            logger.info("Adding {} to {}", message, newTripsQueue.getQueueName());
        } else {
            logger.error("Invalid message");
        }
    }
}