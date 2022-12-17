package cz.cvut.fit.niam1.newtripsprocessor;

import cz.cvut.fit.niam1.newtripsprocessor.data.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
@EnableJms
public class NewTripsProcessorApplication {

    private final Logger logger = LoggerFactory.getLogger(NewTripsProcessorApplication.class);

    @JmsListener(destination = "newTripsQueue")
    public void readMessage(String message) throws InterruptedException {
        Trip booking = Trip.fromString(message);
        if (booking != null) {
            this.process(booking);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(NewTripsProcessorApplication.class, args);
    }

    private void process(Trip trip) {
        logger.info("Processing {}", trip.toString());
    }
}