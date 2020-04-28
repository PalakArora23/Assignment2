package com.qait.tests.java.automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

//import org.openqa.selenium.Proxy;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

	private static String browser;
	private static final DesiredCapabilities capabilities = new DesiredCapabilities();

	public WebDriver getDriver(Map<String, String> seleniumconfig) {
		browser = seleniumconfig.get("browser");

		switch (seleniumconfig.get("seleniumserver").toLowerCase()) {
		case "local":
			switch (browser.toLowerCase()) {
			case "chrome":
				return getChromeDriver(seleniumconfig.get("driverpathChrome"));
			case "chromeproxy":
				return getChromeDriverWithProxy(seleniumconfig.get("driverpathChrome"));
			case "safari":
				return getSafariDriver();
			case "ie":
				return getInternetExplorerDriver(seleniumconfig.get("driverpathIE"));
			case "internetexplorer":
				return getInternetExplorerDriver(seleniumconfig.get("driverpathIE"));
			case "internet explorer":
				return getInternetExplorerDriver(seleniumconfig.get("driverpathIE"));
			default:
				return getChromeDriver(seleniumconfig.get("driverpathChrome"));
			}
		case "remote":
			return setRemoteDriver(seleniumconfig);
		}
		return getChromeDriver(seleniumconfig.get("driverpathChrome"));
	}

	private WebDriver setRemoteDriver(Map<String, String> selConfig) {
		DesiredCapabilities cap = null;
	    browser = selConfig.get("browser");
	    if (browser.equalsIgnoreCase("firefox")) {
	      cap = DesiredCapabilities.firefox();
	    } else if (browser.equalsIgnoreCase("chrome")) {
	      cap = DesiredCapabilities.chrome();
	    } else if (browser.equalsIgnoreCase("chromeProxy")) {
	      cap = DesiredCapabilities.chrome();
	      Proxy proxy = new Proxy();
	      proxy.setHttpProxy("stag-lnx-133.acs.org:38888");
	      cap.setCapability("proxy", proxy);
	    } else if (browser.equalsIgnoreCase("Safari")) {
	      cap = DesiredCapabilities.safari();
	    } else if ((browser.equalsIgnoreCase("ie")) || (browser.equalsIgnoreCase("internetexplorer")) || (browser.equalsIgnoreCase("internet explorer"))) {
	      cap = DesiredCapabilities.internetExplorer();
	    }
	    String seleniuhubaddress = selConfig.get("seleniumserverhost");
	    URL selserverhost = null;
	    try {
	      selserverhost = new URL(seleniuhubaddress);
	    } catch (MalformedURLException e) {
	      e.printStackTrace();
	    }
	    cap.setJavascriptEnabled(true);
	    return new RemoteWebDriver(selserverhost, cap);
		
	}

	private static WebDriver getChromeDriver(String driverpath) {
		String curdir, downloadFilePath;
		System.setProperty("webdriver.chrome.driver", driverpath);

		curdir = System.getProperty("user.dir");
		downloadFilePath = curdir + "\\src\\test\\resources\\testdata";
		System.out.println(downloadFilePath);

		System.setProperty("webdriver.chrome.driver", driverpath);
		ChromeOptions options = new ChromeOptions();

//		options.addArguments("--disable-extensions");
//		options.addArguments("chrome.switches", "--disable-extensions");
		options.addArguments("--always-authorize-plugins");
//		options.addArguments("test-type");
//		options.addArguments("--dns-prefetch-disable");
//		options.addArguments("--explicitly-allowed-ports=8085");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);
		options.setExperimentalOption("prefs", chromePrefs);
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		
		return new ChromeDriver(cap);
	}

	
	public WebDriver getChromeDriverWithProxy(String driverpath)
	{
		String curdir, downloadFilePath;
		curdir = System.getProperty("user.dir");
		downloadFilePath = curdir + "\\src\\test\\resources\\testdata";
		
		System.out.println(downloadFilePath);
		System.setProperty("webdriver.chrome.driver", driverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("chrome.switches", "--disable-extensions");
		options.addArguments("--always-authorize-plugins");
		options.addArguments("test-type");
		options.addArguments("--dns-prefetch-disable");
		options.addArguments("--explicitly-allowed-ports=8085");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilePath);
		options.setExperimentalOption("prefs", chromePrefs);

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		String PROXY = "stag-lnx-133.acs.org:38888";
		Proxy proxy = new Proxy();
		proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
		cap.setCapability(CapabilityType.PROXY, proxy);
		ChromeDriver driver = new ChromeDriver(cap);
		return driver;

	}

	
	private static WebDriver getInternetExplorerDriver(String driverpath) {
		System.setProperty("webdriver.ie.driver", driverpath);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("ignoreZoomLevel", true);
		capabilities.setJavascriptEnabled(true);
		return new InternetExplorerDriver(capabilities);
	}

	private static WebDriver getSafariDriver() {
		return new SafariDriver();
	}
	

}
