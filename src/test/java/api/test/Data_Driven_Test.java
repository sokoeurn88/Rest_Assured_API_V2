package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.User_End_Points;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class Data_Driven_Test {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph) {
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		
		Response response = User_End_Points.create_User(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		
		Response response = User_End_Points.delete_User(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	

}
