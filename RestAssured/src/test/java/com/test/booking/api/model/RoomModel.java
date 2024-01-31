package com.test.booking.api.model;

public class RoomModel {

    private long id;
    private String number;
    private boolean booked;
    private Enum<RoomType> roomType;
    private HotelModel hotel;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Enum<RoomType> getRoomType() {
        return roomType;
    }

    public void setRoomType(Enum<RoomType> roomType) {
        this.roomType = roomType;
    }

    public HotelModel getHotel() {
        return hotel;
    }

    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }
}
