package com.qait.tests.java.utils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//import net.mindengine.galen.api.Galen;
//import net.mindengine.galen.reports.GalenTestInfo;
//import net.mindengine.galen.reports.HtmlReportBuilder;
//import net.mindengine.galen.reports.model.LayoutReport;
//import net.mindengine.galen.validation.ValidationError;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class LayoutValidation {

	WebDriver driver;
	String PageName;
	String tier;
	private String filepath = "src/test/resources/PageObjectRepository/";

	public LayoutValidation(WebDriver driver, String pageName) 
	{
		this.driver = driver;
		this.PageName = pageName;
	}
	}
