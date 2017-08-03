package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_Password {
	
	WebDriver driver;
	By txtPassword = By.name("password");
	By btnNext = By.xpath("//*[@id='passwordNext']/content/span");
	
	public Login_Password(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void setPassword(String strPassword)
	{
		driver.findElement(txtPassword).sendKeys(strPassword);
		driver.findElement(btnNext).click();
	}
}
