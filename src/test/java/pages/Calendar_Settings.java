package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Calendar_Settings {

	WebDriver driver;
	
	By ddlTime = By.name("format24HourTime");
	
	By ddlFirstDay = By.name("firstDay");
	By btnSave = By.id("settings_save_btn");
	
	By lnkGeneral = By.xpath("//*[@id='tab_0']");
	By lnkCalendars = By.xpath("//*[@id='tab_1']");
	
	By lnkBkToCalendar = By.linkText("« Back to calendar");
	
	public Calendar_Settings(WebDriver driver){
		this.driver=driver;
	}
	
	public void ChangeTimeFormat(String strTimeFormat)
	{
		Select selTimeFormat = new Select(driver.findElement(ddlTime));
		selTimeFormat.selectByVisibleText(strTimeFormat);
		driver.findElement(btnSave).click();
	}
	
}
