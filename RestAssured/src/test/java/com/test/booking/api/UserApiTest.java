//package com.test.booking.api;
//
//import com.test.booking.api.model.UserModel;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.json.JSONObject;
//import org.testng.Assert;
//import org.testng.ITestContext;
//import org.testng.annotations.Test;
////import org.testng.asserts.Assertion;
////import static io.restassured.RestAssured.*;
//// import static       io.restassured.matcher.RestAssuredMatchers.*;
//        import static org.hamcrest.Matchers.*;
//
//import static io.restassured.RestAssured.given;
//
//public class UserApiTest {
//    private String url = "http://localhost:7000";
//
//    @Test(priority = 1)
//    public void testUserRegistration(ITestContext testContext) {
//        UserModel userModel = new UserModel();
//        userModel.setEmail("verma12@email.com");
//        userModel.setUsername("user-30");
//        userModel.setPassword("password");
//        userModel.setRole("User");
//        userModel.setMobile(998746727);
//        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(userModel)
//                .when().post(url + "/user/register");
//        Assert.assertNotNull(response.getBody());
//        response.then().statusCode(201).body("message",equalTo("User Registered"));
//        testContext.setAttribute("userId", response.getBody().jsonPath().get("userId"));
//    }
//
////    @Test(priority = 2)
//    @Test(priority = 2, dependsOnMethods = "testUserRegistration")
//    public void testUserLogin(ITestContext testContext) {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("username", "user-30");
//        jsonObject.put("password", "password");
//        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(jsonObject.toString())
//                .when().post(url + "/user/login");
//        Assert.assertEquals(200, response.getStatusCode());
//        Assert.assertNotNull(response.getBody());
//        String accessToken = response.getBody().jsonPath().get("accessToken");
//        testContext.setAttribute("accessToken",accessToken);
//    }
//
//    @Test(priority = 3, dependsOnMethods = "testUserLogin")
//    public void testGetUserDetails(ITestContext testContext) {
//        given().header("authorization", testContext.getAttribute("accessToken")).contentType(ContentType.JSON).accept(ContentType.JSON)
//                .when().get(url+"/user/userInfo")
//                .then().statusCode(200)
//                .body("username",equalTo("user-30"))
//                .body("email",equalTo("verma12@email.com"))
//                .body("mobile",equalTo(998746727))
//                .body("role", equalTo("User"));
//    }
//
//    @Test(priority = 4, dependsOnMethods = "testUserLogin")
//    public void testUpdateUserDetails(ITestContext testContext) {
//        UserModel userModel = new UserModel();
//        userModel.setEmail("verma12@email.com");
//        userModel.setUsername("user-30");
//        userModel.setPassword("password");
//        userModel.setRole("User");
//        userModel.setMobile(123456789);
//
//        given().header("authorization", testContext.getAttribute("accessToken")).contentType(ContentType.JSON).accept(ContentType.JSON).body(userModel)
//                .when().put(url+"/user/update/userInfo")
//                .then().statusCode(200)
//                .body("username",equalTo("user-30"))
//                .body("email",equalTo("verma12@email.com"))
//                .body("mobile",equalTo(123456789))
//                .body("role", equalTo("User"));
//    }
//
//    @Test(priority = 5, dependsOnMethods = "testUserLogin")
//    public void testDeleteUserDetails(ITestContext testContext) {
//             given().header("authorization", testContext.getAttribute("accessToken")).contentType(ContentType.JSON).accept(ContentType.JSON)
//                .when().delete(url+"/user/delete/userInfo")
//                .then().statusCode(204);
////                .body("status",equalTo("User deleted"));
//    }
//
//}
