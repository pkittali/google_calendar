package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Login {
	
	WebDriver driver;
	
	By txtUserName = By.name("identifier");
	By btnNext = By.id("identifierNext");
	
	public Login_Login(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void setUserName(String strUserName)
	{
		driver.findElement(txtUserName).sendKeys(strUserName);
		driver.findElement(btnNext).click();
	}
	
}
