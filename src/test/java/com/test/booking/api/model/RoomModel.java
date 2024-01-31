package com.test.booking.api.model;

/**
 * The type Room model.
 */
public class RoomModel {

    private long id;
    private String number;
    private boolean booked;
    private Enum<RoomType> roomType;
    private HotelModel hotel;

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Is booked boolean.
     *
     * @return the boolean
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * Sets booked.
     *
     * @param booked the booked
     */
    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    /**
     * Gets room type.
     *
     * @return the room type
     */
    public Enum<RoomType> getRoomType() {
        return roomType;
    }

    /**
     * Sets room type.
     *
     * @param roomType the room type
     */
    public void setRoomType(Enum<RoomType> roomType) {
        this.roomType = roomType;
    }

    /**
     * Gets hotel.
     *
     * @return the hotel
     */
    public HotelModel getHotel() {
        return hotel;
    }

    /**
     * Sets hotel.
     *
     * @param hotel the hotel
     */
    public void setHotel(HotelModel hotel) {
        this.hotel = hotel;
    }
}
