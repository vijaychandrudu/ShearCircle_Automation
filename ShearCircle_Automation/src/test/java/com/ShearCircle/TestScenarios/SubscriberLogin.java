package com.ShearCircle.TestScenarios;

import org.testng.annotations.Test;

import com.ShearCircle.ObjectRepository.SubscriberElementLocaters;
import com.ShearCircle.Utilities.CommonFunctions;
import com.ShearCircle.Utilities.StaticVariables;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class SubscriberLogin extends StaticVariables {
	public String TestDataPath;
	CommonFunctions cfn = new CommonFunctions();
	//Create an Object for SubscriberElementLocaters class
	SubscriberElementLocaters obj = new SubscriberElementLocaters();
	
	// to get the data from Property file
	public String p(String path) {
		File file = new File(TestDataPath);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String prop1 = prop.getProperty(path);
		return prop1;

	}

	@BeforeClass
	@Parameters("browser")
	public void beforeclass(@Optional("chrome") String browser) throws IOException, InterruptedException {
		TestDataPath = cfn.TestDataPathOf("SubscriberTestData.properties");
		Browser = browser;
		cfn.GetAndOpenBrowser(browser);

	}

	@AfterMethod
	public void StatusScreenShots(ITestResult testResult) throws IOException, InterruptedException {
		cfn.ScreenshotOnPassFail(testResult);
		Thread.sleep(1000);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	@Test
	public void LoginAsSubscriber() {

		cfn.TypeURL(p("TestBedURL"));
		//driver.findElement(By.linkText("Login")).click();
		cfn.ClickByXpath(obj.LOGIN);
		
		cfn.TypebyId(obj.USERNAME, p("UserName"));
		cfn.TypebyId(obj.PASSWORD, p("Password"));
		cfn.ClickByXpath(obj.SIGN);
		

	}

}
