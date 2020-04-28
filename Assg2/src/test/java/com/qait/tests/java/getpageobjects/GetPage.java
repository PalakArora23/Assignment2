package com.qait.tests.java.getpageobjects;



import static com.qait.tests.java.getpageobjects.ObjectFileReader.getELementFromFile;
import static com.qait.tests.java.utils.ConfigPropertyReader.getProperty;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.qait.tests.java.utils.*;

public class GetPage extends BaseUI {

	protected WebDriver webdriver;
	String pageName;
	LayoutValidation layouttest;
	protected SeleniumWait wait;
	int x = 10;
	int counter = 1;

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
		this.wait = new SeleniumWait(driver, Integer.parseInt(getProperty("timeout")));
		layouttest = new LayoutValidation(driver, pageName);
	}

	

	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void switchToNestedFrames(String frameNames) {
		switchToDefaultContent();
		String[] frameIdentifiers = frameNames.split(":");
		for (String frameId : frameIdentifiers) {
			wait.waitForFrameToBeAvailableAndSwitchToIt(getLocator(frameId.trim()));
		}
	}

	// Take elementToken as String array.....
	// elemenToken[0]--"Element"..... elementToken[i]---"Replacements"

	protected WebElement element(String... elementToken) throws NoSuchElementException {
		WebElement elem = null;
		try {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken)));
			return elem;
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element '" + elementToken[0] + "' not found on the " + this.pageName + " !!!");
		}
		return null;
	}

	protected List<WebElement> elements(String... elementToken) throws NoSuchElementException {
		List<WebElement> elem = null;
		try {
			elem = wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken)));
			return elem;
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element '" + elementToken[0] + "' not found on the " + this.pageName + " !!!");
		}
		return null;
	}

	protected List<WebElement> elementsNotWait(String... elementToken) throws NoSuchElementException {
		List<WebElement> elem = null;
		try {
			elem = webdriver.findElements(getLocator(elementToken));
			return elem;
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element '" + elementToken[0] + "' not found on the " + this.pageName + " !!!");
		}
		return null;
	}

	protected void clickOnElement(String... elementToken) {
		element(elementToken).click();
		if (elementToken.length <= 2)
			logMessage("Info: clicked on: " + elementToken[elementToken.length - 1]);
		else
			logMessage("Info: clicked on: " + elementToken[1]);
	}

	protected void sendKeys(String data, String... elementToken) {
		element(elementToken).clear();
		element(elementToken).sendKeys(data);
		logMessage("Info: Data entered in " + elementToken[0] + " is: " + data);
	}

	protected String getText(String... elementToken) {
		return element(elementToken[0]).getText();
	}

	protected void _waitForElementToDisappear(String elementToken, String replacement) {
		int i = 0;
		int initTimeout = wait.getTimeout();
		wait.resetImplicitTimeout(2);
		int count;
		while (i <= 20) {
			if (replacement.isEmpty())
				count = elements(elementToken).size();
			else
				count = elements(elementToken, replacement).size();
			if (count == 0)
				break;
			i += 2;
		}
		wait.resetImplicitTimeout(initTimeout);
	}

	protected void waitForElementToDisappear(String elementToken) {
		_waitForElementToDisappear(elementToken, "");
	}

	protected void waitForElementToDisappear(String elementToken, String replacement) {
		_waitForElementToDisappear(elementToken, replacement);
	}

	protected void isStringMatching(String actual, String expected) {
		logMessage("Info: Comparing 2 strings...");
		logMessage("EXPECTED STRING :" + expected);
		logMessage("ACTUAL STRING :" + actual);
		Assert.assertEquals(actual, expected, "The strings do not match!!!");
		logMessage("String compare Assertion passed!");
	}

	protected void verifyElementText(String elementName, String expectedText) {
		wait.waitForElementToBeVisible(element(elementName));
		assertEquals(element(elementName).getText().trim(), expectedText,
				"TEST FAILED: element '" + elementName + "' Text is not as expected: ");
		logMessage("TEST PASSED: element " + elementName + " is visible and Text is " + expectedText);
	}

//	protected void verifyElementTextContains(String elementName, String expectedText) {
//		wait.waitForElementToBeVisible(element(elementName));
//		assertThat("TEST FAILED: element '" + elementName + "' Text is not as expected: ",
//	//			element(elementName).getText().trim(), containsString(expectedText));
//		logMessage("TEST PASSED: element " + elementName + " is visible and Text is " + expectedText);
//	}

	// Expected result = True--- If checking element should be visible
	// False---- IF checking element should be not visble
	protected Boolean isElementDisplayed(Boolean expected_result, String... elementName) {
		Boolean result = false;
		String element = elementName[0], subText="";

		if (elementName.length == 2)
			subText = " with subtext '"+elementName[1]+"'";
		else if (elementName.length == 3)
			subText = " with subtexts '"+elementName[1]+"' and '"+elementName[2]+"'";

		// Check if Element is Displayed
		if (expected_result) {
			result = element(elementName).isDisplayed();
			assertTrue(result, "Assertion FAILED: element '" + element +subText+" is not displayed.");
			logMessage("Assertion PASSED: element '" + element +"'"+subText+" is displayed.");

		}

		// Check if element is not Displayed
		else {
			try {
				driver.findElement(getLocator(elementName));
				result = element(elementName).isDisplayed();
			} catch (Exception excp) {
				result = false;
			}
			assertFalse(result, "element '" + element +subText+" is displayed.");
			logMessage("Assertion Passed: element '" + element +subText+" is not displayed as expected!!!");
		}

		return result;
	}

	protected Boolean isElementDisplayed(String... elementName) {
		return isElementDisplayed(true, elementName);
	}

	protected boolean isElementDisplayedByWebElement(WebElement el) {
		wait.waitForElementToBeVisible(el);
		boolean result = el.isDisplayed();
		assertTrue(result, "TEST FAILED: element '" + el + "' is not displayed.");
		// logMessage("TEST PASSED: element " + el + " is displayed.");
		return result;
	}

	protected boolean isElementEnabled(String elementName, boolean expected) {
		wait.waitForElementToBeVisible(element(elementName));
		boolean result = expected && element(elementName).isEnabled();
		assertTrue(result, "TEST FAILED: element '" + elementName + "' is  ENABLED :- " + !expected);
		logMessage("TEST PASSED: element " + elementName + " is enabled :- " + expected);
		return result;
	}

	protected By getLocator(String... elementToken) {
		try {
			if (elementToken.length == 1) {
				String[] locator = getELementFromFile(this.pageName, elementToken[0]);
				return getBy(locator[1].trim(), locator[2].trim());
			}
			if (elementToken.length == 2) {
				String[] locator = getELementFromFile(this.pageName, elementToken[0]);
				// locator[3] = locator[3].replaceAll("\\$\\{.+\\}",
				// elementToken[1]);
				locator[2] = locator[2].replaceAll("\\$\\{.+?\\}", elementToken[1]);
				return getBy(locator[1].trim(), locator[2].trim());
			}
			if (elementToken.length == 3) {
				String[] locator = getELementFromFile(this.pageName, elementToken[0]);
				// locator[3] = locator[3].replaceFirst("\\$\\{.+?\\}",
				// elementToken[1]);
				locator[2] = locator[2].replaceAll("\\$\\{.+?\\}", elementToken[1]);
				locator[2] = locator[2].replaceFirst("\\%\\{.+?\\}", elementToken[2]);
				return getBy(locator[1].trim(), locator[2].trim());
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}
		return null;
	}

	public void clickOnFirstLinkBasedOnProvidedText(String elementToken, String linkText) {
		element(elementToken, linkText).click();
	}

	public boolean matchGivenPatternWithProvidedText(String pattern, String text) {
		Matcher matcher = Pattern.compile(pattern).matcher(text);
		return matcher.matches();
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (locatorType) {
		case "id":
			return By.id(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		case "css":
			return By.cssSelector(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
			return By.className(locatorValue);
		case "linktext":
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	protected WebElement getElement(String... elementToken) {
		WebElement elem = null;
		elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken)));
		return elem;
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public boolean waitForAValueToAppearForAnElementBasedOnGivenTime(String element, String expectedValue,
			int maxTimeInSeconds) {
		int i;
		boolean flag = false;
		wait.waitForPageToLoadCompletely();

		for (i = 1; i <= maxTimeInSeconds; i++) {
			if (element(element).getText().trim().equalsIgnoreCase(expectedValue)) {
				flag = true;
				break;
			} else {
				wait.hardWait(1);
			}
		}

		return flag;
	}

	protected boolean checkIfElemenWithGivenTextIsNotThere(String eleString, String textToBeReplaced) {
		boolean flag = false;
		try {
			if (webdriver.findElement(getLocator(eleString, textToBeReplaced)).isDisplayed()) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (NoSuchElementException ex) {
			flag = true;
		}
		return flag;
	}

	/*
	 * Method to Match the pattern
	 */
	public boolean verifyFormat(String str, String pattern) {
		boolean matches;
		Pattern pat = Pattern.compile(pattern);
		Matcher m = pat.matcher(str);
		matches = m.find();
		return matches;
	}

	@SuppressWarnings("unchecked")
	public String verifyScrolledPosition(WebElement element, String RelatedText) {
		hardWait(5);
		String script = "return arguments[0].getBoundingClientRect()";
		Map<String, Object> viewport;
		viewport = (Map<String, Object>) executeJavascriptObject(script, element);
		Object top = viewport.get("top");
		logMessage("Top Position of " + RelatedText + " : " + top);
		return top.toString();
	}

	@SuppressWarnings("unchecked")
	public String getDimension(String dimension, WebElement element, String RelatedText) {
		hardWait(5);
		String script = "return arguments[0].getBoundingClientRect()";
		Map<String, Object> viewport;
		viewport = (Map<String, Object>) executeJavascriptObject(script, element);
		Object dimesion = viewport.get(dimension);
//		logMessage(dimension + " Position of " + RelatedText + " : " + dimesion);
		return dimesion.toString();
	}

	public boolean isValidDateFormate(String... date) {
		String value = element(date).getText();
		System.out.println("date is: " + value);
		try {
			new SimpleDateFormat("MMMM dd, yyyy").parse(value);
			Date date1 = new SimpleDateFormat("MMMM dd, yyyy").parse(value);
			System.out.println(date1);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean isValidDateFormateChaptersTOC(String... date) {
		String value = element(date).getText();
		try {
			new SimpleDateFormat("MMMM dd,yyyy").parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	protected List<WebElement> elementsinDom(String elementToken) {
		return elementsinDom(elementToken, "");
	}

	protected List<WebElement> elementsinDom(String elementToken, String replacement) {
		List<WebElement> list = null;
		try {
			list = wait.waitForElementsToBePresent(getLocator(elementToken, replacement));
		} catch (NoSuchElementException excp) {
			fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " !!!");
		} catch (NullPointerException npe) {
		}
		return list;
	}

	protected boolean checkIfElementIsThere(String... eleString) {
		boolean flag = false;
		try {
			if (webdriver.findElement(getLocator(eleString)).isDisplayed()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException ex) {
			flag = false;
		}
		return flag;
	}

	public boolean isValidDateFormatePublication(String date) {
		try {
			new SimpleDateFormat("dd MMMM yyyy").parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public void clickOnContinueOnBanner() {
		String tier ="";

//		try {
//			if (System.getProperty("tier").contains("defaultTier") || System.getProperty("tier").isEmpty())
//				tier = getProperty("Config.properties", "tier").trim();
//			else {
//				tier = System.getProperty("tier");
//			}
//		} catch (NullPointerException e) {
//			tier = getProperty("Config.properties", "tier").trim();
//		}
//
//		if (tier.equalsIgnoreCase("prod") || tier.equalsIgnoreCase("production")){
			JavascriptExecutor js = (JavascriptExecutor) driver;
			try {
//				System.out.println(driver.findElement(By.cssSelector("div[id=gdpr-message-modal]")).isDisplayed());
				if (driver.findElement(By.cssSelector("div[id=gdpr-message-modal]")).isDisplayed())
					js.executeScript("document.querySelector(\"a[id='gdpr-con-btn']\").click()");
			} catch (Exception e) {
				System.out.println("Info: Banner button not present.");
			}
//		}
	}
	
	public Boolean isElementInFocus(WebElement element){
		return element.equals(driver.switchTo().activeElement());
	}
	
}
