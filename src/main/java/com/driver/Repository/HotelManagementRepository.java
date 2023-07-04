package com.driver.Repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelManagementRepository {

    Map<String , Hotel> hotelDB = new HashMap<>();
    Map<Integer , User> userDB = new HashMap<>();

    Map<String , Booking> bookingDB = new HashMap<>();
    List<String > users = new ArrayList<>();

    public String addHotel(Hotel hotel) {
        if(hotel == null || hotel.getHotelName() == null) return "FAILURE";
        if(hotelDB.containsKey(hotel.getHotelName())) return "FAILURE";

        hotelDB.put(hotel.getHotelName() , hotel);
        return "SUCCESS";
    }

    public void addUser(User user) {
        userDB.put(user.getaadharCardNo() , user);
    }

    public String getHotelWithMostFacilities() {
        int max = Integer.MIN_VALUE;
        String hotelwithmax = "";
        for(String hotel : hotelDB.keySet()) {
            if(hotelDB.get(hotel).getFacilities().size() == 0) return "";
            if(hotelDB.get(hotel).getFacilities().size() > max) {
                max = hotelDB.get(hotel).getFacilities().size();
                hotelwithmax = hotel;
            }
            if(max == hotelDB.get(hotel).getFacilities().size()){
                hotelwithmax = hotel;
            }
        }
        return hotelwithmax;
    }

    public int bookARoom(Booking booking) {
        bookingDB.put(booking.getBookingId() , booking);
        if(booking.getNoOfRooms() > hotelDB.get(booking.getHotelName()).getAvailableRooms()) return -1;
        int pricePaid = booking.getNoOfRooms() * hotelDB.get(booking.getHotelName()).getPricePerNight();
        return pricePaid;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelDB.get(hotelName);
        hotel.setFacilities(newFacilities);
        hotelDB.put(hotelName , hotel);
        return hotel;
    }

    public int getBookings(Integer aadharCard) {
        User object = new User();
        String naam = object.getName();
        users.add(naam);
        return users.size() + 1;
    }
}