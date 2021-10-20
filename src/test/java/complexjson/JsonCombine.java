package complexjson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class JsonCombine {

	public static void main(String[] args) throws JsonProcessingException {
		
		List<WebAutomation> webs=new ArrayList<WebAutomation>();
		WebAutomation sel=new WebAutomation();
		sel.setCourseTitle("selenium");
		sel.setPrice(100);
		
		webs.add(sel);
		
		List<ApiAutomation> api=new ArrayList<ApiAutomation>();
		ApiAutomation ap=new ApiAutomation();
		ap.setCourseTitle("selenium");
		ap.setPrice(100);
		
		api.add(ap);
		
		List<MobileAutomation> mobs=new ArrayList<MobileAutomation>();
		MobileAutomation mb=new MobileAutomation();
		mb.setCourseTitle("selenium");
		mb.setPrice(100);
		
		mobs.add(mb);
		
		Courses courses=new Courses();
		courses.setWebautomation(webs);
		courses.setApiautomation(api);
		courses.setMobileautomation(mobs);
		
		ServiceDetailsPOJO sp= new ServiceDetailsPOJO();
		sp.setInstructor("msk");
		sp.setUrl("abc.com");
		sp.setServices("testing");
		sp.setExpertise("java");
		sp.setCourses(courses);
		sp.setLinkedin("saleem");
		
		ObjectMapper objectmapper=new ObjectMapper();
		String convertJson=objectmapper.writeValueAsString(sp);
		
		int res=given().header("Content-Type", "application/json")
				.when().body(convertJson).log().all().post("https://reqres.in/api/users").statusCode();
		
		System.out.println(res);

	}

}
