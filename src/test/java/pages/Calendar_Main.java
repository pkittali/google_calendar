package pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import lib.utils;

public class Calendar_Main {
	
	WebDriver driver;
	
	By btnDay = By.xpath("//*[@id='topRightNavigation']/div[1]/div[2]");
	By btnWeek = By.xpath("//*[@id='topRightNavigation']/div[1]/div[3]");
	By btnMonth = By.xpath("//*[@id='topRightNavigation']/div[1]/div[4]");
	By btn4Days = By.xpath("//*[@id='topRightNavigation']/div[1]/div[5]");
	
	By btnSettings = By.id("mg-settings");
	
	//By lblCalendarMonthYear = By.className("calHeaderSpace");
	By lblCalendarMonthYear = By.xpath("//*[@id='dp_0_cur']/span[2]");
	By tblCalendar = By.xpath("//*[@id='dp_0_tbl']");
	
	By btnMoreOptions = By.id("clst_my_menu");
		
	public Calendar_Main(WebDriver driver)
	{
		this.driver=driver;
	}
	
 
	public void GoToSettings(WebDriver driver)
	{
		driver.findElement(btnSettings).click();
		
		driver.findElement(btnSettings).sendKeys(Keys.DOWN);
		driver.findElement(btnSettings).sendKeys(Keys.DOWN);
		driver.findElement(btnSettings).sendKeys(Keys.DOWN);
		driver.findElement(btnSettings).sendKeys(Keys.ENTER);
	}
	
	public void CreateNewEvent()
	{
		
		driver.get("https://calendar.google.com/calendar/render#eventpage_6");
	}
	
	public void SelectDateFromDatePicker(String gotoDay, String gotoMonth, String gotoYear){
		
		String [] splitArr = StringUtils.split(driver.findElement(lblCalendarMonthYear).getText(), " ");
		Integer currYear = Integer.parseInt(splitArr[1]);
		String currMonth = splitArr[0];
		

		Integer intClicks = ((Integer.parseInt(gotoYear)-currYear)*12)+(utils.monthAsNumber(gotoMonth,Locale.ENGLISH,false,true)-
				utils.monthAsNumber(currMonth,Locale.ENGLISH,false,true));
		
		 if(intClicks>0){
			 for(int i=0;i< intClicks;i++){
				 driver.findElement(By.xpath("//*[@id='dp_0_next']")).click();
				 }
	     }
	     else if(intClicks<0){
                for(int i=0;i>intClicks;i--){
                    driver.findElement(By.xpath("//*[@id='dp_0_prev']")).click();
                }
         }
				 
		 WebElement tblDatePicker = driver.findElement(By.xpath("//*[@id='dp_0_tbl']"));
		 List <WebElement> wblDates= tblDatePicker.findElements((By.tagName("td")));
		 
		 for(WebElement cell:wblDates){
			 if(cell.getText().equals(gotoDay)){
				 System.out.println(gotoDay);
				 cell.click();
				 break;
			 }
		 }
	}
	
	public void clickCalendarSettings()
	{
		driver.findElement(btnSettings).click();
	}
	
	public void openEvent(String strMeetingName, String strStartDate) throws ParseException
	{
		DateFormat inputDF  = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = inputDF.parse(strStartDate);

		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);

		int intMonth = cal.get(Calendar.MONTH);
		int intDay = cal.get(Calendar.DAY_OF_MONTH);
		int intYear = cal.get(Calendar.YEAR);
		
		SelectDateFromDatePicker(Integer.toString(intDay), utils.getMonthName(intMonth,Locale.ENGLISH,false), Integer.toString(intYear));
		
		WebElement tblCalendar = driver.findElement(By.id("tgTable"));
		List <WebElement> wblEvents = tblCalendar.findElements(By.className("cpchip"));
		
		Boolean flgMeetingFound = false;

		for(WebElement cell:wblEvents){
			WebElement spnMeetingName = cell.findElement(By.xpath(".//span"));
			if(spnMeetingName.getText().equals(strMeetingName)){
				System.out.println("Event found");
				spnMeetingName.click();
				flgMeetingFound = true;
				break;
			}			
		}
		
		if(flgMeetingFound=false){
			System.out.println("Event not found");
		}
		
	}
	
	public void createEvent(){

		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.id("tgCol2"))).perform();
		
		driver.findElement(By.className("tab-labels")).sendKeys(Keys.TAB);
		driver.findElement(By.className("tab-labels")).sendKeys(Keys.ENTER);
		
	}


}
