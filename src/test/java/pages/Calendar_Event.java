package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calendar_Event {

	WebDriver driver;
	By txtName = By.xpath("//*[@title='Event title']");
	By txtStartDate = By.xpath("//*[@title='From date']");
	By txtStartTime = By.xpath("//*[@title='From time']");
	By txtEndTime = By.xpath("//*[@title='Until time']");
	By txtEndDate = By.xpath("//*[@title='Until date']");
	By txtGuests = By.xpath("//*[@title='Add guests']");
	By chkModifyEvent = By.xpath("//*[contains(@id,'guests-modify')]");
	
	
	//By btnSave = By.className("goog-imageless-button-content");
	By btnSave = By.xpath("//*[@id=\":1k.save_top\"]/div/div/div");
	//By btnSave = By.xpath("//*[@id=\":1k.save_top\"]/div/div/div/div/div[2]");
	
	public Calendar_Event(WebDriver driver){
		this.driver = driver;
	}
	
	public void saveEvent(String strName, String strStartDate, String strStartTime, String strEndTime, String strEndDate, String strGuests)
	{
		//System.out.println("Create Evnt");
		//driver.navigate().to("https://calendar.google.com/calendar/render#eventpage_6");
		//driver.get("https://calendar.google.com/calendar/render#eventpage_6");
		driver.findElement(txtName).clear();
		driver.findElement(txtName).sendKeys(strName);
		driver.findElement(txtStartDate).clear();
		driver.findElement(txtStartDate).sendKeys(strStartDate);
		driver.findElement(txtStartTime).clear();
		driver.findElement(txtStartTime).sendKeys(strStartTime);
		driver.findElement(txtEndTime).clear();
		driver.findElement(txtEndTime).sendKeys(strEndTime);
		driver.findElement(txtEndDate).clear();
		driver.findElement(txtEndDate).sendKeys(strEndDate);
		
		driver.findElement(txtGuests).sendKeys(strGuests);
		
		if (!driver.findElement(chkModifyEvent).isSelected())
		{
		     driver.findElement(chkModifyEvent).click();
		}
		
		driver.findElement(txtName).click();
		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).sendKeys(Keys.TAB,Keys.TAB).perform();
		action.sendKeys(Keys.ENTER).perform();
		action.sendKeys(Keys.ENTER).perform();
				
	}
	
	public void validateEvent(String strName, String strStartDate, String strStartTime, String strEndTime, String strEndDate){
		
		if(strName.equals(driver.findElement(txtName).getText())){
			System.out.println("EventName matches");
		}
		else{
			System.out.println("EventName unmatched");
		}
		
		if(strStartDate.equals(driver.findElement(txtStartDate).getText())){
			System.out.println("StartDate matches");
		}
		else{
			System.out.println("StartDate unmatched");
		}
		if(strStartDate.equals(driver.findElement(txtStartTime).getText())){
			System.out.println("StartTime matches");
		}
		else{
			System.out.println("StartTime unmatched");
		}
		if(strStartDate.equals(driver.findElement(txtEndDate).getText())){
			System.out.println("EndDate matches");
		}
		else{
			System.out.println("EndDate unmatched");
		}
		if(strStartDate.equals(driver.findElement(txtEndTime).getText())){
			System.out.println("EndTime matches");
		}
		else{
			System.out.println("EndTime unmatched");
		}
		
	}

}
