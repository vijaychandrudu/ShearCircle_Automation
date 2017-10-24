package com.ShearCircle.ShearCirclePageComponents;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


import com.ShearCircle.ObjectRepository.Customer_Module_Page_Objects;
import com.ShearCircle.ObjectRepository.Login_page_objects;
import com.ShearCircle.Utilities.CommonFunctions;
import com.ShearCircle.Utilities.StaticVariables;
import com.ShearCircle.Utilities.Web_Browser;

public class Customer_Module_Page_Components extends StaticVariables {
	
	//public static WebDriver driver;
	public Customer_Module_Page_Objects CustomerModule;
	public Login_page_objects Login;
	public CommonFunctions browser;	
	String TestDataPath = null;
	public Customer_Module_Page_Components() throws IOException {			
		browser = new CommonFunctions();
		//System.out.println(driver.getWindowHandle());
		TestDataPath = browser.TestDataPathOf("CustomerTestData.properties");
		CustomerModule = PageFactory.initElements(driver, Customer_Module_Page_Objects.class);
		Login = PageFactory.initElements(driver, Login_page_objects.class);
	}
	
	//Application launch
	public void launchbowser_application(){
		String baseUrl = null;
	    try {   
	    	//System.out.println(driver.getWindowHandle());
	    	browser.loaddata(TestDataPath);
	    	baseUrl = browser.getdata("TestBedURL");
		    driver.get(baseUrl + "");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		    //driver.wait(5);
		    String apptitle = driver.getTitle();
		    if (apptitle.equalsIgnoreCase(baseUrl)){
		    System.out.println("Browser Tilte:"+apptitle);     
		    }		    
			
		    if(browser.elmentisdisplayed(Login.Home_ShearCircle_Image)) {
		    	browser.reportscomtep("Passed", "Verify ShearCircle Home page is displayed", "ShearCircle Home page should be displayed", "ShearCircle Home page displayed");
				 System.out.println("ShearCircle Home page is displayed");
			 }else {
				 browser.reportscomtep("Failed", "Verify ShearCircle Home page is displayed", "ShearCircle Home page should be displayed", "ShearCircle Home page not displayed");
				 System.out.println("ShearCircle Image not displayed");
			 }
			  
		  }catch(Exception e){
				  System.out.println("Exception: "+e); 
		  }	
	}	
	/***************User Click on Customer or Professional Join Our Circle links with help of given input parameter*******************/
	
	public void Click_Customer_OR_Professional_JoinOurCircle(String JoinOurCircleflag) {
		try {
			if (browser.elmentisdisplayed(CustomerModule.customerJoinCircle_Link) && browser.elmentisdisplayed(CustomerModule.ProfessionalJoinCircle_Link)) {
				browser.reportscomtep("Passed", "Verify Are you a Customer and Are you a Professional JoinCircle links are displayed",
						"Are you a Customer and Are you a Professional JoinCircle links are should be displayed", "Are you a Customer and Are you a Professional JoinCircle links displayed");
				
				switch (JoinOurCircleflag) {
				case "Click_CustomerJoinCircle":
					browser.click(CustomerModule.customerJoinCircle_Link);
					
					if (browser.elmentisdisplayed(CustomerModule.CustomerReg_Joincircle_navigation)) {
						browser.reportscomtep("Passed", "Verify Customer Registration form is displayed",
								"Customer Registration form should be displayed",
								"Customer Registration form displayed");
					} else {
						browser.reportscomtep("Failed", "Verify Customer Registration form is displayed",
								"Customer Registration form should be displayed",
								"Customer Registration form not displayed");
					}
					break;
				case "Click_ProfessionalJoinCircle":
					browser.click(CustomerModule.ProfessionalJoinCircle_Link);
					if (browser.elmentisdisplayed(CustomerModule.SubscriberReg_Joincircle_navigation)) {
						browser.reportscomtep("Passed", "Verify Profession Registration form is displayed",
								"Profession Registration form should be displayed",
								"Profession Registration form displayed");
					} else {
						browser.reportscomtep("Failed", "Verify Profession Registration form is displayed",
								"Profession Registration form should be displayed",
								"Profession Registration form not displayed");
					}
					break;
				}

			} else {
				browser.reportscomtep("Failed", "Verify Are you a Customer and Are you a Professional JoinCircle links are displayed",
						"Are you a Customer and Are you a Professional JoinCircle links are should be displayed", "Are you a Customer and Are you a Professional JoinCircle links not displayed");
			}

		} catch (Exception e) {
			browser.reportscomtep("Failed", "Verify Are you a Customer and Are you a Professional JoinCircle links are displayed",
					"Are you a Customer and Are you a Professional JoinCircle links are should be displayed", "Are you a Customer and Are you a Professional JoinCircle links not displayed");
			System.out.println("Error description: " + e.getStackTrace());
		}
	}
	
	public void Customer_Registrationwithvaliddata(){
		String FirstName = null;
		String LastName = null;
		String customerEmail = null;
		String Password = null;
		browser.loaddata(TestDataPath);
		FirstName = browser.getdata("FirstName");
		LastName = browser.getdata("LastName");
		customerEmail = browser.getdata("customerEmail");
		Password = browser.getdata("Password");
		
		try{
			if(browser.elmentisdisplayed(Login.Home_JoinOurCircle_Link)) {
				browser.reportscomtep("Passed", "Verify JoinCircle link is displayed"  , "JoinCircle link should be displayed", "JoinCircle link is displayed");
				browser.click(Login.Home_JoinOurCircle_Link);
				//browser.wait(5);
				if(browser.elmentisdisplayed(CustomerModule.customerJoinCircle_Link)){
					browser.reportscomtep("Passed", "Verify Customer JoinCircle link is displayed"  , "Customer JoinCircle link should be displayed", "Customer JoinCircle link is displayed");
					browser.click(CustomerModule.customerJoinCircle_Link);
					//browser.wait(5);
					if(browser.elmentisdisplayed(CustomerModule.CustomerReg_JoinNow_Header)){						
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_FirstName_textbox, "FirstName textbox");
						browser.sendkeys(CustomerModule.CustomerReg_FirstName_textbox,FirstName);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_LastName_textbox, "LastName textbox");
						browser.sendkeys(CustomerModule.CustomerReg_LastName_textbox,LastName);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_Email_textbox, "Email textbox");
						browser.sendkeys(CustomerModule.CustomerReg_Email_textbox,customerEmail);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_Password_textbox, "Password textbox");
						browser.sendkeys(CustomerModule.CustomerReg_Password_textbox,Password);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_ReEnterPassword_textbox, "ReEnteredPassword textbox");
						browser.sendkeys(CustomerModule.CustomerReg_ReEnterPassword_textbox,Password);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_Termsofservice_checkbox, "Termsofservice checkbox");
						browser.check_Checkbox(CustomerModule.CustomerReg_Termsofservice_checkbox);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_billingagrement_checkbox, "billingagrement Checkbox");
						browser.check_Checkbox(CustomerModule.CustomerReg_billingagrement_checkbox);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_joinourcircle_button, "JoinCircle Button");
						browser.click(CustomerModule.CustomerReg_joinourcircle_button);						
					}
				}else {
					browser.reportscomtep("Failed", "Verify Customer JoinCircle link is displayed"  , "Customer JoinCircle link should be displayed", "Customer JoinCircle link not displayed");
				}
			}else {
				browser.reportscomtep("Failed", "Verify JoinCircle link is displayed"  , "JoinCircle link should be displayed", "JoinCircle link not displayed");
			}
			
		}catch(Exception e){
			System.out.println("Error description: " + e.getStackTrace() );
		}			
		
	}
	
	public void Customer_Registrationwithinvaliddata(){
		String FirstName = null;
		String LastName = null;
		String customerEmail = null;
		String Password = null;
		String Actual_FirstNameErrorMessage = null;
		String Expct_FirstNameErrorMessage = "Alphabets only";
		browser.loaddata(TestDataPath);
		FirstName = browser.getdata("InvalidFirstName");
		LastName = browser.getdata("InvalidLastName");
		customerEmail = browser.getdata("InvalidcustomerEmail");
		Password = browser.getdata("InvalidPassword");
		
		try{
			if(browser.elmentisdisplayed(Login.Home_JoinOurCircle_Link)) {
				browser.reportscomtep("Passed", "Verify JoinCircle link is displayed"  , "JoinCircle link should be displayed", "JoinCircle link is displayed");
				browser.click(Login.Home_JoinOurCircle_Link);
				//browser.wait(5);
				if(browser.elmentisdisplayed(CustomerModule.customerJoinCircle_Link)){
					browser.reportscomtep("Passed", "Verify Customer JoinCircle link is displayed"  , "Customer JoinCircle link should be displayed", "Customer JoinCircle link is displayed");
					browser.click(CustomerModule.customerJoinCircle_Link);
					//browser.wait(5);
					if(browser.elmentisdisplayed(CustomerModule.CustomerReg_JoinNow_Header)){						
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_FirstName_textbox, "FirstName textbox");
						browser.sendkeys(CustomerModule.CustomerReg_FirstName_textbox,FirstName);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_LastName_textbox, "LastName textbox");
						browser.sendkeys(CustomerModule.CustomerReg_LastName_textbox,LastName);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_Email_textbox, "Email textbox");
						browser.sendkeys(CustomerModule.CustomerReg_Email_textbox,customerEmail);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_Password_textbox, "Password textbox");
						browser.sendkeys(CustomerModule.CustomerReg_Password_textbox,Password);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_ReEnterPassword_textbox, "ReEnteredPassword textbox");
						browser.sendkeys(CustomerModule.CustomerReg_ReEnterPassword_textbox,Password);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_Termsofservice_checkbox, "Termsofservice checkbox");
						browser.check_Checkbox(CustomerModule.CustomerReg_Termsofservice_checkbox);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_billingagrement_checkbox, "billingagrement Checkbox");
						browser.check_Checkbox(CustomerModule.CustomerReg_billingagrement_checkbox);
						browser.Verify_elmentisdisplayed_Report(CustomerModule.CustomerReg_joinourcircle_button, "JoinCircle Button");
						browser.click(CustomerModule.CustomerReg_joinourcircle_button);	
						
						if(browser.elmentisdisplayed(CustomerModule.CustomerReg_invalidFirstname_errorMessage)){
							Actual_FirstNameErrorMessage = browser.getelementtext(CustomerModule.CustomerReg_invalidFirstname_errorMessage);
							browser.assertEquals(Actual_FirstNameErrorMessage,Expct_FirstNameErrorMessage);
						}
						
						if(browser.elmentisdisplayed(CustomerModule.CustomerReg_invalidFirstname_errorMessage)){
							Actual_FirstNameErrorMessage = browser.getelementtext(CustomerModule.CustomerReg_invalidFirstname_errorMessage);
							browser.assertEquals(Actual_FirstNameErrorMessage,Expct_FirstNameErrorMessage);
						}
						
						if(browser.elmentisdisplayed(CustomerModule.CustomerReg_invalidFirstname_errorMessage)){
							Actual_FirstNameErrorMessage = browser.getelementtext(CustomerModule.CustomerReg_invalidFirstname_errorMessage);
							browser.assertEquals(Actual_FirstNameErrorMessage,Expct_FirstNameErrorMessage);
						}
						
						if(browser.elmentisdisplayed(CustomerModule.CustomerReg_invalidFirstname_errorMessage)){
							Actual_FirstNameErrorMessage = browser.getelementtext(CustomerModule.CustomerReg_invalidFirstname_errorMessage);
							browser.assertEquals(Actual_FirstNameErrorMessage,Expct_FirstNameErrorMessage);
						}
						
						if(browser.elmentisdisplayed(CustomerModule.CustomerReg_invalidFirstname_errorMessage)){
							Actual_FirstNameErrorMessage = browser.getelementtext(CustomerModule.CustomerReg_invalidFirstname_errorMessage);
							browser.assertEquals(Actual_FirstNameErrorMessage,Expct_FirstNameErrorMessage);
						}
						
						
					}
				}else {
					browser.reportscomtep("Failed", "Verify Customer JoinCircle link is displayed"  , "Customer JoinCircle link should be displayed", "Customer JoinCircle link not displayed");
				}
			}else {
				browser.reportscomtep("Failed", "Verify JoinCircle link is displayed"  , "JoinCircle link should be displayed", "JoinCircle link not displayed");
			}
			
		}catch(Exception e){
			System.out.println("Error description: " + e.getStackTrace() );
		}			
		
	}
	
	public void ShearCircle_Verify_Customer_forgotPassword(String p_in_Valid_or_Invalid, String p_in_FinalAction) {
		String customerValidEmail = null;		
		String customerInValidEmail = null;
		String invalid_Message;
		String valid_Message;
		
		browser.loaddata(TestDataPath);
		customerValidEmail = browser.getdata("customerValidEmail");
		customerInValidEmail = browser.getdata("customerInValidEmail");
		invalid_Message = "No records for the Username";
		valid_Message = "Password Reset Instructions Sent Successfully";
		String getmessage = null;
		try{
			if(browser.elmentisdisplayed(Login.Home_Login_Link)) {
				browser.reportscomtep("Passed", "Verify Login link is displayed"  , "Login link should be displayed", "Login link is displayed");
				browser.click(Login.Home_Login_Link);
				//browser.wait(5);
				if(browser.elmentisdisplayed(Login.Login_forgotPassword_Link)){
					browser.reportscomtep("Passed", "Verify Forgotpassword link is displayed"  , "Forgotpassword link should be displayed", "Forgotpassword link is displayed");
					browser.click(Login.Login_forgotPassword_Link);
					//browser.wait(5);
					if(browser.elmentisdisplayed(Login.ResetPassword_Header)){
						browser.reportscomtep("Passed", "Verify Reset Password is displayed"  , "Reset Password should be displayed", "Reset Password is displayed");
						
						browser.Verify_elmentisdisplayed_Report(Login.ResetPassword_UserName_textbox, "UserName textbox");
						browser.Verify_elmentisdisplayed_Report(Login.ResetPassword_SendInstruction_Link, "Send Instruction ForgotPassword Button");
						browser.Verify_elmentisdisplayed_Report(Login.ResetPassword_Cancel_textbox, "Cancel Link");
						
						if(p_in_Valid_or_Invalid.equalsIgnoreCase("Valid")) {
							browser.sendkeys(Login.ResetPassword_UserName_textbox,customerValidEmail);
							
						}else if(p_in_Valid_or_Invalid.equalsIgnoreCase("InValid")){
							browser.sendkeys(Login.ResetPassword_UserName_textbox,customerInValidEmail);
						}
						
						switch(p_in_FinalAction) {
							case "Click Send instructions to reset password":
								browser.click(Login.ResetPassword_SendInstruction_Link);
								
								if(p_in_Valid_or_Invalid.equalsIgnoreCase("Valid")) {
									getmessage = browser.getelementtext(Login.ResetPassword_ValidMessage_text);
									if(valid_Message.equalsIgnoreCase(getmessage)) {
										browser.reportscomtep("Passed", p_in_FinalAction+" and Verify Reset Password Successful message is displayed"  , "Reset Password Successful message should be displayed", "Reset Password Successful message displayed as : "+ getmessage);
									}else {
										browser.reportscomtep("Failed", p_in_FinalAction+ " and Verify Reset Password Successful message is displayed"  , "Reset Password Successful message should be displayed", "Reset Password Successful message Not displayed as :"+valid_Message);
									}								
								}else if(p_in_Valid_or_Invalid.equalsIgnoreCase("InValid")){
									getmessage = browser.getelementtext(Login.ResetPassword_InvalidMessage_text);
									if(invalid_Message.equalsIgnoreCase(getmessage)) {
										browser.reportscomtep("Passed", p_in_FinalAction+" and Verify Reset Password invalid message is displayed"  , "Reset Password invalid message should be displayed", "Reset Password invalid message displayed as : "+ getmessage);
									}else {
										browser.reportscomtep("Failed", p_in_FinalAction+" and Verify Reset Password invalid message is displayed"  , "Reset Password invalid message should be displayed", "Reset Password invalid message Not displayed as : "+ invalid_Message);
									}
								}
									
								break;
							case "Click Cancel":	
								browser.click(Login.ResetPassword_Cancel_textbox);
								if(browser.elmentisdisplayed(Login.Login_forgotPassword_Link)) {
									browser.reportscomtep("Passed", p_in_FinalAction+ " and Verify Customer navigate Reset Password page"  , "Customer should be navigate Reset Password page", "Customer navigated Reset Password page");
								}else {
									browser.reportscomtep("Failed", p_in_FinalAction+ " and Verify Customer navigate Reset Password page"  , "Customer should be navigate Reset Password page", "Customer Not navigated Reset Password page");
									
								}
							break;
						}					
						
						
					}else {
						browser.reportscomtep("Failed", "Verify Reset Password is displayed"  , "Reset Password should be displayed", "Reset Password is Not displayed");
					}
						
				}else {
					browser.reportscomtep("Failed", "Verify Forgotpassword link is displayed"  , "Forgotpassword link should be displayed", "Forgotpassword link is displayed");
				}
			}else {
				browser.reportscomtep("Failed", "Verify Login link is displayed"  , "Login link should be displayed", "Login link is not displayed");
			}
			
		}catch(Exception e){
			System.out.println("Error description: " + e.getStackTrace() );
		}	
	}
	
	/*public void Professional_Registration(){
		
		String FirstName = null;
		String LastName = null;
		String ProfessionalEmail = null;
		String Password = null;
		String BusinessName = null;
		String Subdomain = null;
		browser.loaddata(TestDataPath);
		FirstName = browser.getdata("FirstName");
		LastName = browser.getdata("LastName");
		BusinessName = browser.getdata("BusinessName");
		Password = browser.getdata("Password");
		ProfessionalEmail = browser.getdata("ProfessionalEmail");
		Subdomain = browser.getdata("Subdomain");
		try {	
		
		if(browser.elmentisdisplayed(Login.Home_JoinOurCircle_Link)) {
			browser.reportscomtep("Passed", "Verify JoinCircle link is displayed"  , "JoinCircle link should be displayed", "JoinCircle link is displayed");
			browser.click(Login.Home_JoinOurCircle_Link);
			//browser.wait(5);
			if(browser.elmentisdisplayed(CustomerModule.CustomerReg_customerJoinCircle_Link)){
				browser.reportscomtep("Passed", "Verify Professional JoinCircle link is displayed"  , "Professional JoinCircle link should be displayed", "Professional JoinCircle link is displayed");
				browser.click(CustomerModule.CustomerReg_ProfessionalJoinCircle_Link);
				//browser.wait(5);
				if(browser.elmentisdisplayed(CustomerModule.CustomerReg_JoinNow_Header)){
					
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_FirstName_textbox, "FirstName textbox");
					browser.sendkeys(CustomerModule.PF_FirstName_textbox,FirstName);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_LastName_textbox, "LastName textbox");
					browser.sendkeys(CustomerModule.PF_LastName_textbox,LastName);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_Email_textbox, "Email textbox");
					browser.sendkeys(CustomerModule.PF_Email_textbox,ProfessionalEmail);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_businessName_textbox, "buisiness textbox");
					browser.sendkeys(CustomerModule.PF_businessName_textbox,BusinessName);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_Subdomain_textbox, "Subdomain textbox");
					browser.sendkeys(CustomerModule.PF_Subdomain_textbox,Subdomain);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_Password_textbox, "Password textbox");
					browser.sendkeys(CustomerModule.PF_Password_textbox,Password);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_ReEnterPassword_textbox, "ReEnteredPassword textbox");
					browser.sendkeys(CustomerModule.PF_ReEnterPassword_textbox,Password);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_Termsofservice_checkbox, "Termsofservice checkbox");
					browser.check_Checkbox(CustomerModule.PF_Termsofservice_checkbox);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_billingagrement_checkbox, "billingagrement Checkbox");
					browser.check_Checkbox(CustomerModule.PF_billingagrement_checkbox);
					browser.Verify_elmentisdisplayed_Report(CustomerModule.PF_joinourcircle_button, "JoinCircle Button");
					browser.click(CustomerModule.PF_joinourcircle_button);
				}
			}else {
				browser.reportscomtep("Failed", "Verify Professional JoinCircle link is displayed"  , "Professional JoinCircle link should be displayed", "Professional JoinCircle link not displayed");
			}
		}else {
			browser.reportscomtep("Failed", "Verify JoinCircle link is displayed"  , "JoinCircle link should be displayed", "JoinCircle link not displayed");
		}
		}catch(Exception e) {
			System.out.println("Error description: " + e.getStackTrace() );
		}
					
	}*/
	
	
}
