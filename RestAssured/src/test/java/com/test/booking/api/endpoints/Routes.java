package com.test.booking.api.endpoints;

public class Routes {
    public static String BASE_URL = "http://localhost:7000";


    //User api
    public static String USER_REGISTER = BASE_URL + "/user/register";
    public static String USER_LOGIN = BASE_URL + "/user/login";
    public static String USER_USER_INFO = BASE_URL + "/user/userInfo";
    public static String USER_DELETE = BASE_URL + "/user/delete/userInfo";
    public static String USER_UPDATE = BASE_URL + "/user/update/userInfo";

    //Hotel api
    public static String HOTEL_CREATE = BASE_URL + "/hotel/create";
    public static String HOTEL_DETAILS = BASE_URL + "/hotel/{hotelId}";

    //Room api
    public static String ROOM_CREATE = BASE_URL + "/room/add";
    public static String ROOM_UPDATE = BASE_URL + "/room/update/{roomId}";
    public static String ROOM_BOOK = BASE_URL + "/room/book/{roomId}/{status}";
    public static String ROOM_DETAILS = BASE_URL + "/room/{roomId}";

    // Booking api
    public static String BOOKING_CREATE = BASE_URL + "/booking/create/userInfo";
    public static String BOOKING_UPDATE = BASE_URL + "/booking/update/{bookingId}";
    public static String BOOKING_DETAILS = BASE_URL + "/booking/{bookingId}";
    public static String BOOKING_DETAILS_BY_USER = BASE_URL + "/booking/user/userInfo";
    public static String BOOKING_CANCEL = BASE_URL + "/booking/cancel/{bookingId}";
}
