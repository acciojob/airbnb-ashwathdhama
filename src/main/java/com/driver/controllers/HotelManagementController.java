package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.driver.model.Hotel.hotels;

@RestController
@RequestMapping("/hotel")
public class HotelManagementController<BookingRepository, aadharCardNo> {
    @Autowired
       

    @PostMapping("/add-hotel")
    public String addHotel(@RequestBody Hotel hotel) {
        if (hotel == null || hotel.getHotelName() == null) {
            return "FAILURE";
        }

        if (hotels.containsKey()) {
            return "FAILURE";
        }

        hotels.put(hotel.getHotelName(), hotel);
        return "SUCCESS";


    }

    @PostMapping("/add-user")
    public <BookingRepository> Integer addUser(@RequestBody User user) {

        //You need to add a User Object to the database
        //Assume that user will always be a valid user and return the aadharCardNo of the user
        BookingRepository bookingRepository = (BookingRepository) getBookings();


        Integer aadharCardNo = user.getaadharCardNo();
        User.put(aadharCardNo, user);
        return aadharCardNo;


    }

    @GetMapping("/get-hotel-with-most-facilities")
    public String getHotelWithMostFacilities() {

        //Out of all the hotels we have added so far, we need to find the hotelName with most no of facilities
        //Incase there is a tie return the lexicographically smaller hotelName
        //Incase there is not even a single hotel with atleast 1 facility return "" (empty string)

        String result = "";
        int maxFacilityCount = 0;

        HashMap<Object, Object> hotel;
        for (Hotel hotels : hotels.values()) {
            int facilityCount = hotels.getFacilities().size();

            if (facilityCount > maxFacilityCount) {
                maxFacilityCount = facilityCount;
                result = hotels.getHotelName();
            } else if (facilityCount == maxFacilityCount && hotels.getHotelName().compareTo(result) < 0) {
                result = hotels.getHotelName();
            }
        }

        return maxFacilityCount > 0 ? result : "";
    }

    @PostMapping("/book-a-room")
    public int bookARoom(@RequestBody Booking booking) {

        //The booking object coming from postman will have all the attributes except bookingId and amountToBePaid;
        //Have bookingId as a random UUID generated String
        //save the booking Entity and keep the bookingId as a primary key
        //Calculate the total amount paid by the person based on no. of rooms booked and price of the room per night.
        //If there arent enough rooms available in the hotel that we are trying to book return -1
        //in other case return total amount paid

        String bookingId = UUID.randomUUID().toString();
        booking.setBookingId(bookingId);

        Hotel hotel = Hotel.getHotels().get(booking.getHotelName());
        int roomsAvailable = hotel.getAvailableRooms();
        int roomsBooked = booking.getNoOfRooms();

        if (roomsBooked > roomsAvailable) {
            return -1;
        }

        hotel.setAvailableRooms(roomsAvailable - roomsBooked);
        Hotel.getHotels().put(hotel.getHotelName(), hotel);

        double amountToBePaid = roomsBooked * hotel.getPricePerNight();
        booking.setAmountToBePaid((int) amountToBePaid);

        Booking.getBookings().put(bookingId, (Hotel) getBookings());

        return (int) amountToBePaid;
    }

    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public List<String> getBookings() {
        // In this function, return the bookings done by a person
        User object = new User();
        double aadharCard = object.getaadharCardNo();
        String naam = object.getName();
        List<String > lst = new ArrayList<>();
        lst.add(naam);
        return lst;
    }

    @PutMapping("/update-facilities")
    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {

        //We are having a new facilites that a hotel is planning to bring.
        //If the hotel is already having that facility ignore that facility otherwise add that facility in the hotelDb
        //return the final updated List of facilities and also update that in your hotelDb
        //Note that newFacilities can also have duplicate facilities possible
        Booking booking = null;
        Hotel hotel = Hotel.get(booking.getHotelName());

        for (Facility facility : newFacilities) {
            if (!hotel.getFacilities().contains(facility)) {
                hotel.getFacilities().add(facility);
            }
        }
        return hotel;
    }
}
