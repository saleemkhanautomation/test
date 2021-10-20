package complexjson3;

import static io.restassured.RestAssured.given;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import secondcomplexjson.Company;

public class JsonCombine {

	public static void main(String[] args) throws JsonProcessingException {
		
		Address add=new Address();
		add.setFlat("18");
		add.setPincode("90001");
		add.setStreet("kot");
		add.setType("primary");
		
		Address add1=new Address();
		add1.setFlat("181");
		add1.setPincode("900011");
		add1.setStreet("kot1");
		add1.setType("primary1");
		
		
		
		
		List<Address> listadd=new ArrayList<Address>();
		listadd.add(add);
		listadd.add(add1);
		
		
		Location2 loc3=new Location2();
		loc3.setId("1");
		loc3.setCountry("ind");
		loc3.setCity("blr");
		loc3.setAddress(listadd);
		
		Address add19=new Address();
		add19.setFlat("1819");
		add19.setPincode("9000119");
		add19.setStreet("kot19");
		List<Address> listadd2=new ArrayList<Address>();
		listadd2.add(add19);
		
		
		
		Location2 loc4=new Location2();
		loc4.setId("3");
		loc4.setCountry("ind3");
		loc4.setCity("blr3");
		loc4.setAddress(listadd2);
		
		Address add5=new Address();
		add1.setFlat("1815");
		add1.setPincode("9000115");
		add1.setStreet("kot15");
		add1.setType("primary15");
		List<Address> listadd5=new ArrayList<Address>();
		listadd5.add(add5);
		
		
		List<Location2> locationlist=new ArrayList<Location2>();
		locationlist.add(loc3);
		locationlist.add(loc4);
		
		
		
		Location loc=new Location();
		loc.setLoc2(locationlist);
		
		
		
		
		ObjectMapper objectmapper=new ObjectMapper();
		String convertJson=objectmapper.writeValueAsString(loc);
		objectmapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Response res=given().header("Content-Type", "application/json")
				.when().body(convertJson).log().all().post("https://reqres.in/api/users/1");
		
		
	 
	 //Getting response by using deserialization
	  
	  Location cmp2=objectmapper.readValue(res.asString(), Location.class);
		List<Address> lastname5=cmp2.getLoc2().get(0).getAddress();
		
		for(Address a8: lastname5) {
			if(a8.getStreet().contains("kot15")) {
				System.out.println("pincode is "+a8.getPincode());
				break;
			}
		}
		
		String pincode44=lastname5.get(0).getPincode();
		
	//		System.out.println("pincode is "+pincode44);
		
		
		
		System.out.println("res is " +res.asString());
		
		System.out.println("ID is " +JsonPath.from(res.asString()).getInt("id"));
	
		
		
		
		
		 //Getting response by using Json Path
		JsonPath jsonPathEvaluator = res.jsonPath();
		System.out.println("City received from Response " + jsonPathEvaluator.get("loc2[1].address[0].pincode"));
		 
	}

	}


