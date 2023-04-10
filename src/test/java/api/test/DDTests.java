package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	Response createresponse;

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email,
			String Password, String Phone) {
		User userpayload = new User();

		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstname(FirstName);
		userpayload.setLastname(LastName);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);

		createresponse = UserEndPoints.createUser(userpayload);

		Assert.assertEquals(createresponse.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String UserName) {

		Response response = UserEndPoints.deleteUser(UserName);

		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
