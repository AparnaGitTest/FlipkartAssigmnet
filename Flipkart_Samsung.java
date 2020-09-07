package TestCases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart_Samsung {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Setting up chrome driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		;
		WebDriverWait wait;
		Actions action = new Actions(driver);

		// Open the Flipkart Website
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 2000);

		// Close the popup button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2AkmmA _29YdH8']"))).click();

		// Search for "SamsungMobile" and Click on enter
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Samsung Mobiles");
		action.sendKeys(Keys.ENTER).perform();

		// First Filter: to select the Price 10000
		WebElement dropdown = driver.findElement(By.xpath("//div[@class='_1YoBfV']//*[@class='fPjUPw']"));
		Select select = new Select(dropdown);
		select.selectByValue("10000");

		// Second Filter: to select 2GB
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//div[@class='_2kFyHg _2mtkou']//div[@class='_1GEhLw'])[3]"))).click();

		// Third Filter: to select Processor as Snapdragon
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,1000)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Processor Brand']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Snapdragon']"))).click();
		Thread.sleep(3000);

		// Get the Name of the Mobile retrieved after applying the filter
		List<WebElement> mobileNameList = driver.findElements(By.xpath("//div[@class='_3wU53n']"));

		// Get the price of the Mobile retrieved after applying the filter
		List<WebElement> mobilePriceList = driver.findElements(By.xpath("//div[@class='_1vC4OE _2rQ-NK']"));

		// Put the mobile list in an array to get the text
		List<String> mobileNameList_array = new ArrayList<String>();
		for (WebElement webElement : mobileNameList) {
			String name = webElement.getText();

			mobileNameList_array.add(name);

		}
		System.out.println(mobileNameList_array);
		// Put the mobile price in an array to get the text
		List<String> mobilePriceList_array = new ArrayList<String>();
		for (WebElement webElement : mobilePriceList) {
			String name = webElement.getText().substring(1);

			mobilePriceList_array.add(name);
		}
		System.out.println(mobilePriceList_array);
		Thread.sleep(3000);
		File f=new File("F:\\test.txt");
		FileWriter fw=new FileWriter(f,true);
		BufferedWriter writer=new BufferedWriter(fw);
		 writer.write("The LIST of Mobile with prices retrived from Flipkart after applying following filter");
		 writer.newLine();
		 writer.write("1) on price with range \"Min\" to \"10000\"");
		 writer.newLine();
		 writer.write("2) RAM as 2GB");
		 writer.newLine();
		 writer.write("3) PROCESSOR BRAND as Snapdragon");
		 writer.newLine();
		 writer.newLine();
			 for (int i=0;i<mobilePriceList_array.size();i++)
			 	  
		       {
				

		           String name=mobileNameList_array.get(i)+"----Rs "+mobilePriceList_array.get(i);
		           writer.write(name);
		           writer.newLine();
		           
		       }
			 writer.close();
			 
			 driver.quit();

	}

}
