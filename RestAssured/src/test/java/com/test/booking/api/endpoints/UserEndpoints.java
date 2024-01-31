package com.test.booking.api.endpoints;

import com.test.booking.api.model.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
    public static Response userRegistration(UserModel userRequest) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(userRequest)
                .when().post(Routes.USER_REGISTER);
    }

    public static Response loginUser(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).body(jsonObject.toString())
                .when().post(Routes.USER_LOGIN);
    }

    public static Response userDetails(String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken)
                .when().get(Routes.USER_USER_INFO);
    }

    public static Response updateUser(UserModel userModel,String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken).body(userModel)
                .when().put(Routes.USER_UPDATE);
    }

    public static Response deleteUser(String accessToken) {
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).header("Authorization", accessToken)
                .when().delete(Routes.USER_DELETE);
    }
}
