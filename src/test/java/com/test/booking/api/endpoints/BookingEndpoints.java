package com.test.booking.api.endpoints;

import com.test.booking.api.model.BookingModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * The type Booking endpoints.
 */
public class BookingEndpoints {

    /**
     * Create booking response.
     *
     * @param bookingModel the booking model
     * @param accessToken  the access token
     * @return the response
     */
    public static Response createBooking(List<BookingModel> bookingModel, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(bookingModel).header("Authorization", accessToken)
                .when().post(Routes.BOOKING_CREATE);
    }

    /**
     * Update booking response.
     *
     * @param bookingModel the booking model
     * @param bookingId    the booking id
     * @param accessToken  the access token
     * @return the response
     */
    public static Response updateBooking(BookingModel bookingModel, long bookingId, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(bookingModel).pathParam("bookingId",bookingId).header("Authorization", accessToken)
                .when().put(Routes.BOOKING_UPDATE);
    }

    /**
     * Gets booking.
     *
     * @param bookingId   the booking id
     * @param accessToken the access token
     * @return the booking
     */
    public static Response getBooking(long bookingId, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("bookingId",bookingId).header("Authorization", accessToken)
                .when().get(Routes.BOOKING_DETAILS);
    }

    /**
     * Gets booking by user.
     *
     * @param accessToken the access token
     * @return the booking by user
     */
    public static Response getBookingByUser(String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken)
                .when().get(Routes.BOOKING_DETAILS_BY_USER);
    }

    /**
     * Cancel booking response.
     *
     * @param bookingId   the booking id
     * @param accessToken the access token
     * @return the response
     */
    public static Response cancelBooking(long bookingId, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("bookingId",bookingId).header("Authorization", accessToken)
                .when().delete(Routes.BOOKING_CANCEL);
    }
}
