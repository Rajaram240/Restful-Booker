package Restful.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class APIDoc {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://restful-booker.herokuapp.com/");
		driver.findElement(By.xpath("//a[@alt='API documentation']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> methods = driver.findElements(By.xpath("//span[starts-with(@class,'type type')]"));
		List<WebElement> endpoints = driver.findElements(By.xpath("//pre[@class=\"  language-http\"]"));
		System.out.println("The http methods with respective endpoints are:");
for(int i=0;i<methods.size();i++)
{
	WebElement ele1=methods.get(i);
	WebElement ele2=endpoints.get(i);
	
	System.out.println(ele1.getText()+" -> "+ele2.getText());
}
		driver.quit();
	}
}
