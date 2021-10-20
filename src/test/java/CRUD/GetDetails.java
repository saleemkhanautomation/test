package CRUD;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import complexjson3.Address;
import complexjson3.Location;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class GetDetails {



	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		// TODO Auto-generated method stub
		
		/*Response res=given().when().get("/posts");
		System.out.println(res.getBody().asString());
		JsonPath jsonPathEvaluator = res.jsonPath();
		
		if(jsonPathEvaluator.get("id[99]"))
		System.out.println("Body received from Response " + jsonPathEvaluator.get("title"));
		 */
		ObjectMapper objectmapper=new ObjectMapper();
		UserDetails user=new UserDetails();
		objectmapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Response response=given().when().get("/posts");
		
		System.out.println("responseee  "+response.asString());
		JsonPath jsonPath = response.jsonPath();
		
		List<UserDetails> cmp2=jsonPath.getList("",UserDetails.class);
		System.out.println("total size "+cmp2.size());
		for(UserDetails user5 : cmp2)
		{
		if(user5.getId()==99) {
			System.out.println("Body received from Response " + user5.getTitle());
		}
		}
	}

		
		
	}

