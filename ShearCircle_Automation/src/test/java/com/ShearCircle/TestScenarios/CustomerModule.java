package com.ShearCircle.TestScenarios;

import org.testng.annotations.Test;

import com.ShearCircle.ObjectRepository.SubscriberElementLocaters;
import com.ShearCircle.ShearCirclePageComponents.Customer_Module_Page_Components;
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

public class CustomerModule extends StaticVariables {
	public String TestDataPath;
	public Customer_Module_Page_Components CR;
	public CommonFunctions cfn;
	
	
	public CustomerModule() throws IOException, InterruptedException{
		cfn = new CommonFunctions();
		//CR = new Customer_Module_Page_Components();	
	}
	
	
	@BeforeClass
	@Parameters("browser")
	public void beforeclass(@Optional("chrome") String browser) throws IOException, InterruptedException {
		TestDataPath = cfn.TestDataPathOf("CustomerTestData.properties");
		Browser = browser;
		cfn.getAndOpenBrowser(browser);
	}

	@AfterMethod
	public void StatusScreenShots(ITestResult testResult) throws IOException, InterruptedException {
		cfn.ScreenshotOnPassFail(testResult);
		Thread.sleep(1000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}	
	

	@Test(priority=0)
	public void Customer_ForgotPassword_scenario() throws IOException {
		CR = new Customer_Module_Page_Components();	
		CR.ShearCircle_Launchbowser_Application();
		CR.ShearCircle_Verify_Customer_forgotPassword("Valid", "Click Send instructions to reset password");
		CR.ShearCircle_Verify_Customer_forgotPassword("Valid", "Click Cancel");
		CR.ShearCircle_Verify_Customer_forgotPassword("InValid", "Click Send instructions to reset password");
		CR.ShearCircle_Verify_Customer_forgotPassword("InValid", "Click Cancel");
	}
	
	
	@Test(priority=1)
	public void Customer_clicks_JoinOurCircle() throws IOException {
		CR = new Customer_Module_Page_Components();	
		CR.ShearCircle_Launchbowser_Application();
		CR.ShearCircle_Click_Login_OR_JoinOurCircle("Click_JoinOurCircle");
		CR.ShearCircle_Click_Customer_OR_Professional_JoinOurCircle("Click_CustomerJoinCircle");
		CR.ShearCircle_Verify_Customer_Registrationform_Fieldvalidation();
		CR.ShearCircle_Customer_Registrationwithvaliddata();
	}
	
	
	

}
