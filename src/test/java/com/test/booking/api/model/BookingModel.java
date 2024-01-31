package com.test.booking.api.model;

/**
 * The type Booking model.
 */
public class BookingModel {
    private long id;
    private long roomId;
    private long userId;
    private String bookingStatus;
    private String checkInDate;
    private String checkOutDate;
    private String bookingDate;

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
     * Gets room id.
     *
     * @return the room id
     */
    public long getRoomId() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets booking status.
     *
     * @return the booking status
     */
    public String getBookingStatus() {
        return bookingStatus;
    }

    /**
     * Sets booking status.
     *
     * @param bookingStatus the booking status
     */
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    /**
     * Gets check in date.
     *
     * @return the check in date
     */
    public String getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets check in date.
     *
     * @param checkInDate the check in date
     */
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Gets check out date.
     *
     * @return the check out date
     */
    public String getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Sets check out date.
     *
     * @param checkOutDate the check out date
     */
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * Gets booking date.
     *
     * @return the booking date
     */
    public String getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets booking date.
     *
     * @param bookingDate the booking date
     */
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
