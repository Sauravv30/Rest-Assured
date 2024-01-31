package com.test.booking.api.endpoints;

import com.test.booking.api.model.BookingModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class BookingEndpoints {

    public static Response createBooking(List<BookingModel> bookingModel, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(bookingModel).header("Authorization", accessToken)
                .when().post(Routes.BOOKING_CREATE);
    }

    public static Response updateBooking(BookingModel bookingModel, long bookingId, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(bookingModel).pathParam("bookingId",bookingId).header("Authorization", accessToken)
                .when().put(Routes.BOOKING_UPDATE);
    }

    public static Response getBooking(long bookingId, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("bookingId",bookingId).header("Authorization", accessToken)
                .when().get(Routes.BOOKING_DETAILS);
    }

    public static Response getBookingByUser(String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken)
                .when().get(Routes.BOOKING_DETAILS_BY_USER);
    }

    public static Response cancelBooking(long bookingId, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("bookingId",bookingId).header("Authorization", accessToken)
                .when().delete(Routes.BOOKING_CANCEL);
    }
}
