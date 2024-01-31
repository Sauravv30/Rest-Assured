package com.test.booking.api.test;

import com.github.javafaker.Faker;
import com.test.booking.api.endpoints.HotelEndpoints;
import com.test.booking.api.model.HotelModel;
import com.test.booking.api.model.RoomModel;
import com.test.booking.api.model.RoomType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class HotelTest {
    Logger log = LogManager.getLogger(HotelTest.class);
    private HotelModel hotelRequest;
    private RoomModel roomModel;
    private Faker faker = new Faker();
    @BeforeTest
    public void prepareUser() {

        String hotelName = faker.harryPotter().house();
        String location = faker.address().city();
        hotelRequest = new HotelModel();
        hotelRequest.setLocation(location);
        hotelRequest.setName(hotelName);

        roomModel = new RoomModel();
        roomModel.setBooked(false);
        roomModel.setNumber(faker.number().digit());

        roomModel.setRoomType(RoomType.DOUBLE);
    }
    @Test(priority = 1)
    public void createHotel(ITestContext testContext){
        log.info("************ Create Hotel ************");
        Response response = HotelEndpoints.createHotel(hotelRequest,testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(201).body("name", equalTo(hotelRequest.getName())).body("location",equalTo(hotelRequest.getLocation()));
        testContext.getSuite().setAttribute("hotelId", response.getBody().jsonPath().get("id"));
        log.info("************ Created Successfully ************");
    }

    @Test(priority = 2, dependsOnMethods = "createHotel")
    public void getHotelDetails(ITestContext testContext){
        log.info("************ Get Hotel Details ************");
        Response response = HotelEndpoints.getHotelDetails(Long.parseLong(testContext.getSuite().getAttribute("hotelId").toString()),testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("name", equalTo(hotelRequest.getName())).body("location",equalTo(hotelRequest.getLocation()));
        log.info("************ Get Details Successfully ************");
    }

    @Test(priority = 3, dependsOnMethods = "createHotel")
    public void createRoom(ITestContext testContext){
        log.info("************ Create Room ************");
        hotelRequest.setId(Long.parseLong(testContext.getSuite().getAttribute("hotelId").toString()));
        roomModel.setHotel(hotelRequest);
        Response response = HotelEndpoints.createRoom(roomModel,testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(201)
                .body("number", equalTo(roomModel.getNumber()));
//                .body("roomType",equalTo(roomModel.getRoomType()));
        testContext.getSuite().setAttribute("roomId",response.body().jsonPath().get("id"));
        log.info("************ Room Created Successfully ************");
    }

    @Test(priority = 4, dependsOnMethods = "createRoom")
    public void getRoomDetails(ITestContext testContext){
        log.info("************ Get Room Details ************");
        Response response = HotelEndpoints.getRoomDetail(Long.parseLong(testContext.getSuite().getAttribute("roomId").toString()),testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("number", equalTo(roomModel.getNumber()));
        log.info("************ Get Room Details Successfully ************");
    }

    @Test(priority = 5, dependsOnMethods = "getRoomDetails")
    public void bookRoom(ITestContext testContext){
        log.info("************ Get Room Details ************");
        Response response = HotelEndpoints.bookRoom(Long.parseLong(testContext.getSuite().getAttribute("roomId").toString()),"Booking",testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("booked", equalTo(true)).body("number",equalTo(roomModel.getNumber()));
        log.info("************ Get Room Details Successfully ************");
    }

    @Test(priority = 5, dependsOnMethods = "getRoomDetails")
    public void bookSameRoomAgain(ITestContext testContext){
        log.info("************ Book Room Again ************");
        Response response = HotelEndpoints.bookRoom(Long.parseLong(testContext.getSuite().getAttribute("roomId").toString()),"Booking",testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(500).body("errorMessage", equalTo("Room is already booked"));
        log.info("************ Book Room Failed ************");
    }

    @Test(priority = 6, dependsOnMethods = "getRoomDetails")
    public void cancelBooking(ITestContext testContext){
        log.info("************ Get Room Details ************");
        Response response = HotelEndpoints.bookRoom(Long.parseLong(testContext.getSuite().getAttribute("roomId").toString()),"Cancel",testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("booked", equalTo(false)).body("number",equalTo(roomModel.getNumber()));
        log.info("************ Get Room Details Successfully ************");
    }

    @Test(priority = 7, dependsOnMethods = "getRoomDetails")
    public void updateRoom(ITestContext testContext){
        log.info("************ Get Room Details ************");
        String updatedNumber = faker.number().digit();
        roomModel.setNumber(updatedNumber);
        roomModel.setId(Long.parseLong(testContext.getSuite().getAttribute("roomId").toString()));
        Response response = HotelEndpoints.updateRoom(roomModel,testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200).body("booked", equalTo(false)).body("number",equalTo(updatedNumber));
        log.info("************ Get Room Details Successfully ************");
    }

}
