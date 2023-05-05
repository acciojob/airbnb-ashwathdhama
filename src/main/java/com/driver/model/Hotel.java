package com.driver.model;

import java.util.List;

public class Hotel {

    private String hotelName; //This will be the primary key and will be unique for each hotel in hotelDb
    private int availableRooms;

    private List<Facility> facilities;

    private int pricePerNight;

    public Hotel(String hotelName, int availableRooms, List<Facility> facilities, int pricePerNight) {
        this.hotelName = hotelName;
        this.availableRooms = availableRooms;
        this.facilities = facilities;
        this.pricePerNight = pricePerNight;
    }

    public static Hotel get(String hotelName) {
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean containsKey(String hotelName) {
    }

    public void put(String hotelName, Hotel hotel) {
    }

    public int getRoomsAvailable() {
        return 0;
    }

    public void setRoomsAvailable(int i) {

    }
}
