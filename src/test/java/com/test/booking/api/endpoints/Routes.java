package com.test.booking.api.endpoints;

/**
 * The type Routes.
 */
public class Routes {
    /**
     * The constant BASE_URL.
     */
    public static String BASE_URL = "http://localhost:7000";


    /**
     * The constant USER_REGISTER.
     */
//User api
    public static String USER_REGISTER = BASE_URL + "/user/register";
    /**
     * The constant USER_LOGIN.
     */
    public static String USER_LOGIN = BASE_URL + "/user/login";
    /**
     * The constant USER_USER_INFO.
     */
    public static String USER_USER_INFO = BASE_URL + "/user/userInfo";
    /**
     * The constant USER_DELETE.
     */
    public static String USER_DELETE = BASE_URL + "/user/delete/userInfo";
    /**
     * The constant USER_UPDATE.
     */
    public static String USER_UPDATE = BASE_URL + "/user/update/userInfo";

    /**
     * The constant HOTEL_CREATE.
     */
//Hotel api
    public static String HOTEL_CREATE = BASE_URL + "/hotel/create";
    /**
     * The constant HOTEL_DETAILS.
     */
    public static String HOTEL_DETAILS = BASE_URL + "/hotel/{hotelId}";

    /**
     * The constant ROOM_CREATE.
     */
//Room api
    public static String ROOM_CREATE = BASE_URL + "/room/add";
    /**
     * The constant ROOM_UPDATE.
     */
    public static String ROOM_UPDATE = BASE_URL + "/room/update/{roomId}";
    /**
     * The constant ROOM_BOOK.
     */
    public static String ROOM_BOOK = BASE_URL + "/room/book/{roomId}/{status}";
    /**
     * The constant ROOM_DETAILS.
     */
    public static String ROOM_DETAILS = BASE_URL + "/room/{roomId}";

    /**
     * The constant BOOKING_CREATE.
     */
// Booking api
    public static String BOOKING_CREATE = BASE_URL + "/booking/create/userInfo";
    /**
     * The constant BOOKING_UPDATE.
     */
    public static String BOOKING_UPDATE = BASE_URL + "/booking/update/{bookingId}";
    /**
     * The constant BOOKING_DETAILS.
     */
    public static String BOOKING_DETAILS = BASE_URL + "/booking/{bookingId}";
    /**
     * The constant BOOKING_DETAILS_BY_USER.
     */
    public static String BOOKING_DETAILS_BY_USER = BASE_URL + "/booking/user/userInfo";
    /**
     * The constant BOOKING_CANCEL.
     */
    public static String BOOKING_CANCEL = BASE_URL + "/booking/cancel/{bookingId}";
}
