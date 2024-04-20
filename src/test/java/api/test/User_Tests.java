package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.User_End_Points;
import api.payload.User;
import io.restassured.response.Response;

public class User_Tests {
	
	Faker faker;
	User user_Payload;
	
	@BeforeClass
	public void setup_Data() {
		
		faker = new Faker();
		user_Payload = new User();
		
		user_Payload.setId(faker.idNumber().hashCode());
		user_Payload.setUsername(faker.name().username());
		user_Payload.setFirstName(faker.name().firstName());
		user_Payload.setLastName(faker.name().lastName());
		user_Payload.setEmail(faker.internet().safeEmailAddress());
		user_Payload.setPassword(faker.internet().password(5, 10));
		user_Payload.setPhone(faker.phoneNumber().cellPhone());
				
	}
	
	@Test(priority=1)
	public void test_Post_User() {
		Response response = User_End_Points.create_User(user_Payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void test_Get_User_By_Name() {
		Response response = User_End_Points.read_User(this.user_Payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=3)
	public void test_Update_User_By_Name() {
		//update using payload
		user_Payload.setFirstName(faker.name().firstName());
		user_Payload.setLastName(faker.name().lastName());
		user_Payload.setEmail(faker.internet().safeEmailAddress());		
		
		Response response = User_End_Points.update_User(this.user_Payload.getUsername(), user_Payload);
		response.then().log().body();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		//checking data after update
		Response responseAfterupdate = User_End_Points.read_User(this.user_Payload.getUsername());
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void test_Delete_User_By_Name() {
		Response response = User_End_Points.delete_User(this.user_Payload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
