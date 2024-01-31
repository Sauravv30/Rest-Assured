package com.test.booking.api.test;

import com.github.javafaker.Faker;
import com.test.booking.api.endpoints.UserEndpoints;
import com.test.booking.api.model.UserModel;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserTest {
    Logger log = LogManager.getLogger(UserTest.class);
    private UserModel userRequest;
    private Faker faker = new Faker();

    @BeforeTest
    public void prepareUser() {

        userRequest = new UserModel();
        userRequest.setUsername(faker.name().username());
        userRequest.setPassword(faker.internet().password());
        userRequest.setEmail(faker.internet().emailAddress());
        userRequest.setMobile(faker.phoneNumber().cellPhone());
        userRequest.setRole("User");
    }

    @Test(priority = 1)
    public void registerUser(ITestContext testContext) {
        log.info("************ Register User ************");
        Response response = UserEndpoints.userRegistration(userRequest);
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(201).body("message", equalTo("User Registered"));
        testContext.getSuite().setAttribute("userId", response.getBody().jsonPath().get("userId"));
        log.info("************ Register Success ************");
    }

    @Test(priority = 1)
    public void registerUserAgain(ITestContext testContext) {
        log.info("************ Register User Again ************");
        Response response = UserEndpoints.userRegistration(userRequest);
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(500).body("errorMessage", equalTo("Username already preserved, please choose another username"));
        log.info("************ Register Again Failed ************");
    }

    @Test(priority = 2, dependsOnMethods = "registerUser")
    public void loginUser(ITestContext testContext) {
        log.info("************ Login User ************");
        Response response = UserEndpoints.loginUser(userRequest.getUsername(), userRequest.getPassword());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        String accessToken = response.getBody().jsonPath().get("accessToken");
        testContext.getSuite().setAttribute("accessToken", accessToken);
        log.info("************ Login Success ************");
    }

    @Test(priority = 3, dependsOnMethods = "loginUser")
    public void getUserDetails(ITestContext testContext) {
        log.info("************ Getting User Details ************");
        Response response = UserEndpoints.userDetails(testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().body("username", equalTo(userRequest.getUsername()))
                .body("email", equalTo(userRequest.getEmail()));
        log.info("************ Getting User Details Success ************");

    }

   @Test(priority = 4, dependsOnMethods = "loginUser")
    public void updateUserDetails(ITestContext testContext) {
        log.info("************ Updating User Details ************");
        String updatedEmailAddress = faker.internet().emailAddress();
        userRequest.setEmail(updatedEmailAddress);
        Response response = UserEndpoints.updateUser(userRequest, testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        Assert.assertNotNull(response.getBody());
        response.then().statusCode(200)
                .body("email", equalTo(updatedEmailAddress))
                .body("username", equalTo(userRequest.getUsername()));
        log.info("************ User Details Updated************");
    }

   // @Test(priority = 5, dependsOnMethods = "loginUser")
    public void deleteUserDetails(ITestContext testContext) {
        log.info("************ Deleting User Details ************");
        Response response = UserEndpoints.deleteUser(testContext.getSuite().getAttribute("accessToken").toString());
        response.then().log().all();
        response.then().statusCode(204);
        log.info("************ User details deleted Successfully ************");
    }
}
