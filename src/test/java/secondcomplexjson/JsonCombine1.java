package secondcomplexjson;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;



public class JsonCombine1 {

	public static void main(String[] args) throws JsonProcessingException {

		Employee employee=new Employee();
		
		employee.setFirstName("Seema");
		employee.setLastName("lastName");
		employee.setGender("M");
		employee.setAge("13");
		employee.setSalary("888");
		employee.setMarried("yes");
		
		Employee employee2=new Employee();
		employee2.setFirstName("Seema1");
		employee2.setLastName("KHAN");
		employee2.setGender("M1");
		employee2.setAge("131");
		employee2.setSalary("8881");
		employee2.setMarried("yes1");
		
		List<Employee> emplist=new ArrayList<Employee>();
		emplist.add(employee);
		emplist.add(employee2);
		
		Contractors cont1=new Contractors();
		cont1.setContractFrom("65");
		cont1.setContractTo("12");
		cont1.setFirstName("hum");
		cont1.setLastName("kkk");
		
		Contractors cont2=new Contractors();
		cont2.setContractFrom("651");
		cont2.setContractTo("121");
		cont2.setFirstName("hum1");
		cont2.setLastName("kkk1");
		
		List<Contractors> contlist=new ArrayList<Contractors>();
		contlist.add(cont1);
		contlist.add(cont2);
		
		CompanyPFDeails cmp=new CompanyPFDeails();
		cmp.setPfCity("rajsthn");
		cmp.setPfName("epfo");
		cmp.setPfYear("1998");
	
		
		Company company=new Company();
		company.setCompanyName("MSE");
		company.setCompanyHOCity("Benagluru");
		company.setCompanyCEO("Amod");
		
		List<String> supportedSalaryBanks=new ArrayList<String>();
		supportedSalaryBanks.add("HDFC");
		supportedSalaryBanks.add("ICICI");
		supportedSalaryBanks.add("AXIS");
		company.setSupportedSalaryBanks(supportedSalaryBanks);
		
		List<Integer> pincodesOfCityOffice=new ArrayList<Integer>();
		pincodesOfCityOffice.add(560037);
		pincodesOfCityOffice.add(560036);
		pincodesOfCityOffice.add(560035);
		company.setPincodesOfCityOffice(pincodesOfCityOffice);
		
		company.setEmployee(emplist);
		
		company.setContractors(contlist);
		
		company.setCompanyPFDeails(cmp);
		
		ObjectMapper objectmapper=new ObjectMapper();
		objectmapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		String convertJson=objectmapper.writeValueAsString(company);
		
		Response res=given().header("Content-Type", "application/json")
				.when().body(convertJson).log().all().post("https://reqres.in/api/users");
		
		System.out.println("Response "+res.body().asString());
		System.out.println("charset "+res.getBody().path("charset"));
		System.out.println("Status code "+res.getStatusCode());
		System.out.println("Execution time "+res.getTimeIn(TimeUnit.SECONDS));
		
		
		
		Company cmp2=objectmapper.readValue(res.asString(), Company.class);
		String lastname5=cmp2.getEmployee().get(1).getLastName();
		
		System.out.println("lastname "+ lastname5);

	}

}
