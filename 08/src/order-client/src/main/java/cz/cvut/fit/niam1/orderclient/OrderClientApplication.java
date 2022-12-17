package cz.cvut.fit.niam1.orderclient;

import cz.cvut.fit.niam1.orderclient.data.Booking;
import cz.cvut.fit.niam1.orderclient.data.Trip;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import java.util.Random;

@SpringBootApplication
@EnableJms
public class OrderClientApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(OrderClientApplication.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("allOrdersQueue");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        runTest();
    }

    private void runTest() throws Exception {
        int max_test_orders = 10;
        String[] to_list = {"Helsinki", "Riga", "Vilnius", "Tallinn"};
        String[] from_list = {"Prague", "Berlin", "Paris"};
        String[] companies_list = {"AAA Travel", "Travelify", "Travelo"};
        String[] addresses_list = {"Thákurova 9", "Technická 2", "Sněmovní 176/4"};

        for (int id = 1; id <= max_test_orders; id++){
            int price = new Random().nextInt(500) + 1000;
            Thread.sleep(2500 + price);
            int booking_or_trip = new Random().nextInt(100);
            if (booking_or_trip % 2 == 0){
                int min = 3;
                int max = 5;
                int days = new Random().nextInt(max - min + 1) + min;
                String address = addresses_list[new Random().nextInt(addresses_list.length)];
                Booking booking = new Booking(id, days, price, address);
                jmsTemplate.convertAndSend(queue, booking.toString());
                logger.info("Added {} to {}", booking.toString(), queue.getQueueName());
            } else {
                String to = to_list[new Random().nextInt(to_list.length)];
                String from = from_list[new Random().nextInt(from_list.length)];
                String company = companies_list[new Random().nextInt(companies_list.length)];
                Trip trip = new Trip(id, to, from, company, price);
                jmsTemplate.convertAndSend(queue, trip.toString());
                logger.info("Added {} to {}", trip.toString(), queue.getQueueName());
            }
        }
    }
}