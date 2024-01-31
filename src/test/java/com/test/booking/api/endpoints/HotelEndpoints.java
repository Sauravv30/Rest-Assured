package com.test.booking.api.endpoints;

import com.test.booking.api.model.HotelModel;
import com.test.booking.api.model.RoomModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * The type Hotel endpoints.
 */
public class HotelEndpoints {
    /**
     * Create hotel response.
     *
     * @param hotelRequest the hotel request
     * @param accessToken  the access token
     * @return the response
     */
    public static Response createHotel(HotelModel hotelRequest, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(hotelRequest).header("Authorization", accessToken)
                .when().post(Routes.HOTEL_CREATE);
    }

    /**
     * Gets hotel details.
     *
     * @param id          the id
     * @param accessToken the access token
     * @return the hotel details
     */
    public static Response getHotelDetails(long id, String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("hotelId", id).header("Authorization", accessToken)
                .when().get(Routes.HOTEL_DETAILS);
    }

    /**
     * Create room response.
     *
     * @param roomModel   the room model
     * @param accessToken the access token
     * @return the response
     */
    public static Response createRoom(RoomModel roomModel, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(roomModel).header("Authorization", accessToken)
                .when().post(Routes.ROOM_CREATE);
    }

    /**
     * Update room response.
     *
     * @param roomModel   the room model
     * @param accessToken the access token
     * @return the response
     */
    public static Response updateRoom(RoomModel roomModel, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("roomId", roomModel.getId()).body(roomModel).header("Authorization", accessToken)
                .when().put(Routes.ROOM_UPDATE);
    }

    /**
     * Book room response.
     *
     * @param roomId      the room id
     * @param status      the status
     * @param accessToken the access token
     * @return the response
     */
    public static Response bookRoom(long roomId, String status, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("roomId", roomId).pathParam("status",status).header("Authorization", accessToken)
                .when().put(Routes.ROOM_BOOK);
    }

    /**
     * Get room detail response.
     *
     * @param roomId      the room id
     * @param accessToken the access token
     * @return the response
     */
    public static Response getRoomDetail(long roomId, String accessToken){
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParam("roomId", roomId).header("Authorization", accessToken)
                .when().get(Routes.ROOM_DETAILS);
    }

}
