package cz.cvut.fit.niam1.bookingsprocessor;

import cz.cvut.fit.niam1.bookingsprocessor.data.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;

@SpringBootApplication
@EnableJms
public class BookingsProcessorApplication {

    private final Logger logger = LoggerFactory.getLogger(BookingsProcessorApplication.class);

    @JmsListener(destination = "bookingsQueue")
    public void readMessage(String message) throws InterruptedException {
        Booking booking = Booking.fromString(message);
        if (booking != null) {
            this.process(booking);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingsProcessorApplication.class, args);
    }

    private void process(Booking booking) {
        logger.info("Processing {}", booking.toString());
    }

}