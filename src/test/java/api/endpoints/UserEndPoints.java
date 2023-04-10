package api.endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import static io.restassured.matcher.RestAssuredMatchers.*;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//userendpoints.java

//created to perform create,read,update,delete requests the user api

public class UserEndPoints {

	public static Response createUser(User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).log().all()
				.when().post(Routes.post_url);

		return response;

	}

	public static Response readUser(String username) {
		Response response = given().pathParam("username", username).log().all().when().get(Routes.get_url);

		return response;

	}

	public static Response updateteUser(String username, User payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).body(payload).log().all().when().put(Routes.update_url);

		return response;

	}

	public static Response deleteUser(String username) {
		Response response = given().pathParam("username", username).log().all().when().delete(Routes.delete_url);

		return response;

	}

}
