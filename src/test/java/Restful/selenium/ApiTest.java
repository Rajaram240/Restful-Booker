package Restful.selenium;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.json.Json;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import Restful.selenium.Payload;
public class ApiTest {
	
	private String bookingid;
	@Test(priority =1 )
	public void createToken()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		RestAssured.useRelaxedHTTPSValidation();
		String output = given().log().uri()
				.header("Content-Type","application/json")
				.body(Payload.crtToken()).when().post("/auth")//then methods do response validation
				.then().assertThat().statusLine("HTTP/1.1 200 OK").extract().response()
				.asString();
		
		//parsing the json response body
		JsonPath js=new JsonPath(output);
		String token=js.getString("token");
		System.out.println("The generated token is :"+token);

	}
	@Test(priority = 2)
	public void createBooking()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		RestAssured.useRelaxedHTTPSValidation();
		String output = given().log().uri()
				.header("Content-Type","application/json")
				.body(Payload.crtbooking()).when().post("/booking")//then methods do response validation
				.then().assertThat().statusLine("HTTP/1.1 200 OK").contentType(ContentType.JSON).extract().response()
				.asString();
		System.out.println(output);
		//parsing the json response body
		JsonPath js=new JsonPath(output);
		 bookingid=js.getString("bookingid");
		System.out.println("The bookingid is :"+bookingid);

	}
	@Test(priority=3)
	public void getBookingDetails() throws ParseException
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		RestAssured.useRelaxedHTTPSValidation();
		String output = given().log().uri().header("Content-Type","application/json")
				.when().get("/booking/"+bookingid+"")//Parameterized the booking id here
				.then().assertThat().statusLine("HTTP/1.1 200 OK").extract().response()
				.asString();
		System.out.println(output);
		JsonPath js=new JsonPath(output);
		String checkin=js.getString("bookingdates.checkin");
		System.out.println("checkin date is"+":"+checkin);
		String checkout=js.getString("bookingdates.checkout");
		System.out.println("checkout date is"+":"+checkout);
		
	}
}
