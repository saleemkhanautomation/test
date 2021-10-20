package CRUD;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostPut {

	public static void main(String[] args) throws JsonProcessingException {

		NewUser user = new NewUser();
		user.setName("morpheus");
		user.setJob("leader");
/********************************************************************/		
		//Post
		ObjectMapper objectmapper=new ObjectMapper();
		String convertJson=objectmapper.writeValueAsString(user);
		objectmapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Response res=given().header("Content-Type", "application/json")
				.when().body(convertJson).log().all().post("https://reqres.in/api/users/1");
		
		NewUser cmp2=objectmapper.readValue(res.asString(), NewUser.class);
		String job1=cmp2.getJob();
		System.out.println("Job is "+job1 );
		System.out.println("Sttaus code of post is "+res.statusCode() );
		System.out.println("ID is " +JsonPath.from(res.asString()).getInt("id"));
		int id=JsonPath.from(res.asString()).getInt("id");
/***********************************************************************/
		//Put
		
		user.setJob("Headofofccie");
		String convertJson1=objectmapper.writeValueAsString(user);
		Response res1=given().pathParam("id", id).header("Content-Type", "application/json")
				.when().body(convertJson1).log().all().patch("https://reqres.in/api/users/{id}");
		
		System.out.println("Status code "+res1.getStatusCode()+"Updated body "+res1.getBody().asString());
		

	}

}
