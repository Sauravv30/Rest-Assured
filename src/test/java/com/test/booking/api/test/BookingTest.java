package com.test.booking.api.test;

import com.test.booking.api.endpoints.BookingEndpoints;
import com.test.booking.api.model.BookingModel;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class BookingTest {

    Logger log = LogManager.getLogger(BookingTest.class);
    private BookingModel bookingModel;

    @BeforeTest
    public void prepareUser(ITestContext testContext) {
        bookingModel = new BookingModel();
        bookingModel.setUserId(Long.parseLong(testContext.getSuite().getAttribute("userId").toString()));
        bookingModel.setRoomId(Long.parseLong(testContext.getSuite().getAttribute("roomId").toString()));
        bookingModel.setCheckInDate(LocalDate.of(2024, 02, 10).toString());
        bookingModel.setCheckOutDate(LocalDate.of(2024, 02, 12).toString());
        bookingModel.setBookingStatus("CONFIRM");
    }

    @Test(priority = 1)
    public void createBooking(ITestContext testContext) {
        log.info("************ Create Booking ************");
        List<BookingModel> list = new ArrayList<>();
        list.add(bookingModel);
        Response response = BookingEndpoints.createBooking(list, testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(201).body("bookingStatus[0]", equalTo(bookingModel.getBookingStatus()));
        testContext.getSuite().setAttribute("bookingId", response.getBody().jsonPath().get("id[0]"));
        log.info("************ Booking Created Successfully ************");
    }

    @Test(priority = 2, dependsOnMethods = "createBooking")
    public void getBookingDetails(ITestContext testContext) {
        log.info("************ Get Booking ************");
        Response response = BookingEndpoints.getBooking(Long.parseLong(testContext.getSuite().getAttribute("bookingId").toString()), testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("checkOutDate", equalTo(bookingModel.getCheckOutDate()))
                .body("userId", equalTo((int)bookingModel.getUserId()))
                .body("roomId", equalTo((int)bookingModel.getRoomId()));
        log.info("************ Get Booking Successfully ************");
    }

    @Test(priority = 3, dependsOnMethods = "createBooking")
    public void getBookingByUser(ITestContext testContext) {
        log.info("************ Get Booking By User ************");
        Response response = BookingEndpoints.getBookingByUser(testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("checkOutDate[0]", equalTo(bookingModel.getCheckOutDate()))
                .body("userId[0]", equalTo((int)bookingModel.getUserId()))
                .body("roomId[0]", equalTo((int)bookingModel.getRoomId()));
        log.info("************ Get Booking By User Successfully ************");
    }

    //@Test(priority = 4, dependsOnMethods = "createBooking")
    public void cancelBooking(ITestContext testContext) {
        log.info("************ Cancel Booking By Id ************");
        Response response = BookingEndpoints.cancelBooking(Long.parseLong(testContext.getSuite().getAttribute("bookingId").toString()), testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        response.then().statusCode(200);
        log.info("************ Booking Cancelled By Id ************");
    }


    @Test(priority = 5, dependsOnMethods = "createBooking")
    public void updateBooking(ITestContext testContext) {
        log.info("************ Update Booking ************");
        bookingModel.setId(Long.parseLong(testContext.getSuite().getAttribute("bookingId").toString()));
        bookingModel.setCheckOutDate(LocalDate.of(2024, 02, 15).toString());
        Response response = BookingEndpoints.updateBooking(bookingModel, bookingModel.getId(), testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("checkOutDate", equalTo(bookingModel.getCheckOutDate()));
        log.info("************ Updated Successfully ************");
    }


}
