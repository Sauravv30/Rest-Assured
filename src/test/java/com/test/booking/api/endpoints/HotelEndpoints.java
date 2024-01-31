package com.test.booking.api.endpoints;

import com.test.booking.api.model.HotelModel;
import com.test.booking.api.model.RoomModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HotelEndpoints {
    public static Response createHotel(HotelModel hotelRequest, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(hotelRequest).header("Authorization", accessToken)
                .when().post(Routes.HOTEL_CREATE);
    }

    public static Response getHotelDetails(long id, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("hotelId", id).header("Authorization", accessToken)
                .when().get(Routes.HOTEL_DETAILS);
    }

    public static Response createRoom(RoomModel roomModel, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(roomModel).header("Authorization", accessToken)
                .when().post(Routes.ROOM_CREATE);
    }

    public static Response updateRoom(RoomModel roomModel, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("roomId", roomModel.getId()).body(roomModel).header("Authorization", accessToken)
                .when().put(Routes.ROOM_UPDATE);
    }

    public static Response bookRoom(long roomId, String status, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("roomId", roomId).pathParam("status",status).header("Authorization", accessToken)
                .when().put(Routes.ROOM_BOOK);
    }

    public static Response getRoomDetail(long roomId, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("roomId", roomId).header("Authorization", accessToken)
                .when().get(Routes.ROOM_DETAILS);
    }

}
