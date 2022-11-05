package cz.cvut.fit.niam1.wsserver;

import https.courses_fit_cvut_cz.ni_am1.hw._03.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class WebServiceRepository {

    private static final List<Booking> bookings = new ArrayList<>();

    @PostConstruct
    public void initRepo() throws DatatypeConfigurationException {
        // Initial data record.
        Booking b1 = new Booking();
        b1.setId("123");
        b1.setPassengerFirstName("Jorge");
        b1.setPassengerLastName("Zuniga");
        Instant instant1 = Instant.parse("2022-11-15T11:00:00Z");
        ZonedDateTime zonedDateTime1 = instant1.atZone(ZoneId.of("UTC"));
        GregorianCalendar g1 = GregorianCalendar.from(zonedDateTime1);
        XMLGregorianCalendar dateTime1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(g1);
        Instant instant2 = Instant.parse("2022-11-15T23:23:00Z");
        ZonedDateTime zonedDateTime2 = instant2.atZone(ZoneId.of("UTC"));
        GregorianCalendar g2 = GregorianCalendar.from(zonedDateTime2);
        XMLGregorianCalendar dateTime2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(g2);
        b1.setDeparture(dateTime1);
        b1.setArrival(dateTime2);
        b1.setFrom(Airport.PRAGUE);
        b1.setTo(Airport.ZURICH);

        bookings.add(b1);
    }


    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void deleteBooking(String name) {
        bookings.removeIf(booking -> booking.getId().equals(name));
    }

    public void updateBooking(Booking booking) {
        Booking record = bookings.stream().filter(b -> b.getId().equals(booking.getId())).findFirst().orElse(null);
        if (record == null) return;

        int index = bookings.indexOf(record);
        bookings.set(index, booking);
    }
}