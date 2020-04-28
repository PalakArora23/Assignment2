

package com.qait.tests.java.automation;

import static com.qait.tests.java.getpageobjects.ObjectFileReader.setTier;

import static com.qait.tests.java.utils.ConfigPropertyReader.getProperty;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import com.qait.tests.java.utils.*;
import com.qait.tests.java.keywords.BaseClass;
import com.qait.tests.java.keywords.StandalonePageAction;
import com.qait.tests.java.getpageobjects.ObjectFileReader;


public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	String browser;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;

	/**
	 * Initiating the page objects _initPage
	 */
//	public TakeScreenshot takescreenshot;

	public BaseClass homePage;
	public StandalonePageAction loginPage;
	

	

	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
	//homePage = new BaseClass(driver);
//		loginPage = new loginPageActions(driver);

		
	}

	
	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname);
	}

	public TestSessionInitiator(String testname, boolean proxyFlag) {
		wdfactory = new WebDriverFactory();
		testInitiator(testname, proxyFlag);
	}

	private void testInitiator(String testname, boolean proxyFlag) {

		_configureBrowser(proxyFlag);
		_initPage();
	//	TakeScreenshot takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser(boolean proxyFlag) {
		Map<String, String> sessionConfig = new HashMap<String, String>();
		sessionConfig = _getSessionConfig();
		if (proxyFlag)
			sessionConfig.put("browser", "chromeProxy");
		else
			sessionConfig.put("browser", "chrome");
		driver = wdfactory.getDriver(sessionConfig);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);

	}

	private void testInitiator(String testname) {
		setTier();
	//	setYamlFilePath();
		_configureBrowser();
		_initPage();
	//	TakeScreenshot takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser() {
		driver = wdfactory.getDriver(_getSessionConfig());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Integer.parseInt(getProperty("timeout")), TimeUnit.SECONDS);

	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Map<String, String> _getSessionConfig() {
	    String[] configKeys = {"tier", "browser", "seleniumserver", "seleniumserverhost", "timeout", "driverpathIE", "driverpathChrome"};
	    Map<String, String> config = new HashMap<String, String>();
	    for (String string : configKeys) {
	      try {
	        if (System.getProperty(string).isEmpty())
	          config.put(string, getProperty("./Config.properties", string));
	        else
	          config.put(string, System.getProperty(string));
	      } catch (NullPointerException e) {
	        config.put(string, getProperty("./Config.properties", string));

	      }
	    }
	    return config;

	  }


	

	public void launchApplication(String baseurl) {
		Reporter.log("The application url is :- " + baseurl, true);
		Reporter.log("The test browser is :- " + _getSessionConfig().get("browser") + "\n", true);
		driver.manage().deleteAllCookies();
		driver.get(baseurl);
		clickOnContinue();
	}

//	public void launchApplication2(String baseurl) {
//		Reporter.log("The application url is :- " + baseurl, true);
//		Reporter.log("The test browser is :- " + _getSessionConfig().get("browser") + "\n", true);
////		driver.manage().deleteAllCookies();
//		driver.get(baseurl);
////		clickOnContinue();
//	}
	
	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST STEP:- " + testStepName.toUpperCase() + " *****", true);
		Reporter.log(" ", true);
	}
	
	public void clickOnContinue(){

    		JavascriptExecutor js = (JavascriptExecutor) driver;
    		try{
//    			System.out.println(driver.findElement(By.cssSelector("div[id=gdpr-message-modal]")).isDisplayed());
    			if(driver.findElement(By.cssSelector("div[id=gdpr-message-modal]")).isDisplayed())
    				js.executeScript("document.querySelector(\"a[id='gdpr-con-btn']\").click()");
    		}
    		catch(Exception e){
    			System.out.println("Info: Banner button not present.");
    		}
//    	}
	}


	public void openUrl(String url) {
		driver.get(url);
		Reporter.log("Info: Launched URL " + url, true);
		clickOnContinue();
		clickOnContinue();
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void closeBrowserSession() {
		Reporter.log("Browser selected from 'Config.properties' file: " + _getSessionConfig().get("browser"));

		driver.quit();
	}

	public void pageRefresh() {
		driver.navigate().refresh();
	}

	public String getpageTitle() {
		return driver.getTitle();
	}

	public void closeBrowser() {
		driver.close();
	}
	
	public void logMessage(String message) {
		Reporter.log(message, true);
	}
}
