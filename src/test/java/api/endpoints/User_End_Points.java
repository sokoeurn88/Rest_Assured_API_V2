package api.endpoints;

import static io.restassured.RestAssured.*;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class User_End_Points {
	
	public static Response create_User(User payload){
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url)
		;
		return response;
	}
	
	public static Response read_User(String userName){
		
		Response response = given()
					.pathParam("username", userName)
				.when()
				.get(Routes.get_url)
				;
		return response;
	}
	
	public static Response update_User(String userName, User payload){
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.update_url)
		;
		return response;
	}
	
	public static Response delete_User(String userName){
		
		Response response = given()
					.pathParam("username", userName)
				.when()
				.delete(Routes.delete_url)
				;
		return response;
	}
	
	

}