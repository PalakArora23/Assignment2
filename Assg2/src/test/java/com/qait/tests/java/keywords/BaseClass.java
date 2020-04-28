package com.qait.tests.java.keywords;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.qait.tests.java.automation.*;
import com.qait.tests.java.getpageobjects.GetPage;

public class BaseClass {
	protected GetPage logBase;
	protected TestSessionInitiator test;
	protected String urlForTemporaryStorage = null;
	protected int counterForTests;

	@BeforeClass
	public void Start_Test_Session() {
		counterForTests = 0;
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		// test.launchApplication(getYamlValue("baseUrl"));
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		test.stepStartMessage(method.getName());
		
	}
	
	@BeforeTest
	 public void URL_Launch() 
	 {
       ChromeDriver driver=new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       driver.get("https://webmail.qainfotech.com/#1");
       driver.findElement(By.id("username")).sendKeys("palakarora@qainfotech.com");
       driver.findElement(By.id("password")).click();
     //  driver.findElement(By.xpath("//input[@aria-label='Enter your password' and @name='password']")).sendKeys("********");
     //  Thread.sleep(200);
       driver.findElement(By.id("submit")).click();
	 }

	@AfterMethod
	public void take_screenshot_on_failure(ITestResult result, Method method) {
//		counterForTests = test.takescreenshot.incrementcounterForTests(counterForTests, result);
//		test.takescreenshot.takeScreenShotOnException(result, counterForTests, method.getName());
	}

//	@AfterClass
	public void close_Test_Session() throws IOException {
		test.closeBrowserSession();
	}

}