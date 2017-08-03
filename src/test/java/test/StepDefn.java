package test;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import pages.Login_Login;
import pages.Login_Password;
import pages.Calendar_Event;
import pages.Calendar_Main;
import pages.Calendar_Settings;


public class StepDefn {
	
	WebDriver driver;

	private Response response;
	private RequestSpecification request;
    private String ENDPOINT = "https://www.googleapis.com/calendar/v3/users/me/calendarList/";	

	@Given("^I launch Google Calendar$")
	public void i_launch_Google_Calendar() throws Throwable
	{
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver();		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.google.com/calendar?");
		driver.manage().window().maximize();
		//throw new PendingException();
	}
	

	@When("^Creator logs with username as \"(.*?)\" and password as \"(.*?)\"$")
	public void creator_logs_with_username_as_and_password_as(String arg1, String arg2) throws Throwable {
			Login_Login objLogin_Login = new Login_Login(driver);
			Login_Password objLogin_Password = new Login_Password(driver);
			objLogin_Login.setUserName(arg1);
			Thread.sleep(1000);
			objLogin_Password.setPassword(arg2);
		    //throw new PendingException();
	}

	@When("^Attendee logs with username as \"(.*?)\" and password as \"(.*?)\"$")
	public void attendee_logs_with_username_as_and_password_as(String arg1, String arg2) throws Throwable {
			Login_Login objLogin_Login = new Login_Login(driver);
			Login_Password objLogin_Password = new Login_Password(driver);
			objLogin_Login.setUserName(arg1);
			Thread.sleep(1000);
			objLogin_Password.setPassword(arg2);
		    //throw new PendingException();
	}
	
	@When("^Select the date with date as \"(.*?)\" and month as \"(.*?)\" and year as \"(.*?)\"$")
	public void select_the_date_with_date_as_and_month_as_and_year_as(String arg1, String arg2, String arg3) throws Throwable {
			Thread.sleep(2000);
			Calendar_Main objCalendar_Main = new Calendar_Main(driver);
			objCalendar_Main.SelectDateFromDatePicker(arg1, arg2,arg3);
	}

	
	@And("^I modify the time format to \"(.*?)\"")
	public void i_modify_the_time_format_to(String arg1)
	{
		Calendar_Main objCalendar_Main = new Calendar_Main(driver);
		Calendar_Settings objCalendar_Settings = new Calendar_Settings(driver);
		objCalendar_Main.GoToSettings(driver);
		objCalendar_Settings.ChangeTimeFormat(arg1);
		
	}
	
	@And("^Open the event \"(.*?)\" and startDate as \"(.*?)\"$")
	public void open_the_event_and_startDate_as(String arg1, String arg2) throws ParseException
	{
		Calendar_Main objCalendar_Main = new Calendar_Main(driver);
		objCalendar_Main.openEvent(arg1,arg2);
	}

	@And("^I create an event with name as \"(.*?)\" startdate as \"(.*?)\" starttime as \"(.*?)\""
			+ " enddate as \"(.*?)\" endtime as \"(.*?)\" with guest as \"(.*?)\"$")
	public void i_create_an_event_with_name_as_startdate_as_starttime_as_enddate_as_endtime_as_with_guest_as(
			String strName, String strStartDate, String strStartTime, String strEndDate, String strEndTime, String strGuests){
		Calendar_Main objCalendar_Main = new Calendar_Main(driver);
		objCalendar_Main.createEvent();
		Calendar_Event objCalendar_Event = new Calendar_Event(driver);
		objCalendar_Event.saveEvent(strName, strStartDate, strStartTime, strEndTime, strEndDate, strGuests);
	}
	
	@Then("I validate the event name as \"(.*?)\" startdate as \"(.*?)\" starttime as \"(.*?)\""
			+ " enddate as \"(.*?)\" endtime as \"(.*?)\"$")
	public void i_validate_the_event_name_as_startdate_as_starttime_as_enddate_as_endtime_as_with_guest_as(
			String strName, String strStartDate, String strStartTime, String strEndDate, String strEndTime){
		Calendar_Event objCalendar_Event = new Calendar_Event(driver);
		objCalendar_Event.validateEvent(strName, strStartDate, strStartTime, strEndTime, strEndDate);
		
	}
	
	@And("^I logout$")
	public void logout() throws InterruptedException
	{
		
/*		Calendar_Main objCalendar_Main = new Calendar_Main(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(objCalendar_Main.btnDay));	*/	

		driver.get("https://accounts.google.com/Logout?service=cl&continue=https://calendar.google.com/calendar/render");
		Thread.sleep(3000);
		driver.close();
		driver.quit();
	}
	
/*	@And("^I create an event$")
	public void i_create_an_event(){
		Calendar_Main objCalendar_Main = new Calendar_Main(driver);
		objCalendar_Main.createEvent();
	}*/
	
	@Given("^I request as user \"(.*?)\" with accesscode as \"(.*?)\"$")
	public void i_request_as_user_with_accesscode_as(String arg1, String arg2) {
		System.out.println(arg2);
		request=given().header("Authorization","Bearer " + arg2);
		ENDPOINT = ENDPOINT + arg1;
	}
	
	@When("^I try to get list of events$")
	public void i_try_to_get_list_of_events(){
		response = request.when().get(ENDPOINT);
		System.out.println("response: " + response.prettyPrint());
	}
	
	@Then("^the status code is (\\d+)")
	public void verify_status_code(int statusCode){
		response.then().statusCode(statusCode);
	}	
	
	@And("^response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
				if(response.jsonPath().getString(field.getKey())!=null){
					if(response.jsonPath().get(field.getKey()).equals(field.getValue())){
						System.out.println("Found it " + response.jsonPath().getString("$") + ":" + response.jsonPath().get(field.getKey()));
					}
				}
		}
		
	}



}
