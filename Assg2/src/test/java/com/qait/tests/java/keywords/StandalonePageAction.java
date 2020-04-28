package com.qait.tests.java.keywords;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeMethod;

import com.qait.tests.java.getpageobjects.BaseUI; 
import com.qait.tests.java.PageObjectRepository.*;

public class StandalonePageAction extends BaseUI {
	

	protected StandalonePageAction(WebDriver driver, String pageName) {
		super(driver, pageName);
		// TODO Auto-generated constructor stub
	}


	static String NoOfEmails;
	                                                                                                                      

	 public static void main(String[] args) throws InterruptedException{
	  
	//	 @BeforeTest
	        ChromeDriver driver=new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	        driver.get("https://webmail.qainfotech.com/#1");
	        driver.findElement(By.id("username")).sendKeys("palakarora@qainfotech.com");
	        driver.findElement(By.id("password")).click();
	   //     driver.findElement(By.xpath("//input[@aria-label='Enter your password' and @name='password']")).sendKeys("********");
	        Thread.sleep(200);
	        driver.findElement(By.id("submit")).click();
	        
	        
	        //fetch nof emails
	        driver.findElement(By.className("ImgDownArrowSmall"));
	  
	        NoOfEmails = driver.findElement(By.xpath("//tbody//tr[@valign='top']//td[@id='DWT90']")).getText();
	        System.out.println("Number of Email is printed" + NoOfEmails);
	        driver.findElement(By.id("FolderProperties_button2_title")).click();
	        
	        //compose email
	        driver.findElement(By.id("zb__NEW_MENU_title")).click();
	        driver.findElement(By.id("DWT103")).click();
	        driver.findElement(By.id("DWT103")).sendKeys("akankshaangrish@qainfotech.com");
	        
	        driver.findElement(By.id("zv__COMPOSE-1_subject_control")).click();
	        driver.findElement(By.id("zv__COMPOSE-1_subject_control")).sendKeys("Assignment 2");
	        
	        driver.findElement(By.id("tinymce")).click();
	        driver.findElement(By.id("tinymce")).sendKeys("This is a spam email");
	        
	        //verify notification
	//     driver.findElement(By.id("z_toast_text")).notify();
	        
	        
	        		
	 }
	        
	        

}




