package com.test.booking.api.endpoints;

import com.test.booking.api.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

/**
 * The type User endpoints.
 */
public class UserEndpoints {
    /**
     * User registration response.
     *
     * @param userRequest the user request
     * @return the response
     */
    public static Response userRegistration(UserModel userRequest) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(userRequest)
                .when().post(Routes.USER_REGISTER);
    }

    /**
     * Login user response.
     *
     * @param username the username
     * @param password the password
     * @return the response
     */
    public static Response loginUser(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post(Routes.USER_LOGIN);
    }

    /**
     * User details response.
     *
     * @param accessToken the access token
     * @return the response
     */
    public static Response userDetails(String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken)
                .when().get(Routes.USER_USER_INFO);
    }

    /**
     * Update user response.
     *
     * @param userModel   the user model
     * @param accessToken the access token
     * @return the response
     */
    public static Response updateUser(UserModel userModel,String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken).body(userModel)
                .when().put(Routes.USER_UPDATE);
    }

    /**
     * Delete user response.
     *
     * @param accessToken the access token
     * @return the response
     */
    public static Response deleteUser(String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken)
                .when().delete(Routes.USER_DELETE);
    }
}
