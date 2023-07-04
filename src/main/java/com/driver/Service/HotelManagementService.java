package com.driver.Service;

import com.driver.Repository.HotelManagementRepository;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.List;

public class HotelManagementService {

    HotelManagementRepository hotelManagementRepository = new HotelManagementRepository();
    public String addHotel(Hotel hotel) {
        return hotelManagementRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        hotelManagementRepository.addUser(user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        return hotelManagementRepository.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return hotelManagementRepository.bookARoom(booking);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotelManagementRepository.updateFacilities(newFacilities, hotelName);
    }
}