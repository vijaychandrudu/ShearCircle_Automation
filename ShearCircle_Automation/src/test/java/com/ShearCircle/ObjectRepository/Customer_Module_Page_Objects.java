package com.ShearCircle.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Customer_Module_Page_Objects{
					
	@FindBy(xpath="//div/h1/b[text()='Customer?']//parent::h1/following-sibling::a[text()='Join Our Circle']")	
    public WebElement customerJoinCircle_Link;	
	
	@FindBy(xpath="//div/h1/b[text()='Professional?']//parent::h1/following-sibling::a[text()='Join Our Circle']")	
    public WebElement ProfessionalJoinCircle_Link;	
	
	@FindBy(xpath="//div/h2/b[text()='Join Now']")	
    public WebElement CustomerReg_JoinNow_Header;
	
	@FindBy(xpath="//*[@id=\"position\"]/div/ul/li[text()='Customer']")	
    public WebElement CustomerReg_Joincircle_navigation;
	
	@FindBy(xpath="//*[@id='position']/div/ul/li[text()='Professional']")	
    public WebElement SubscriberReg_Joincircle_navigation;	
	
	@FindBy(xpath="//*[@name='firstname']")	
    public WebElement CustomerReg_FirstName_textbox;	
	@FindBy(xpath="//input[@name='lastname']")
	public WebElement CustomerReg_LastName_textbox;	
	@FindBy(xpath="//input[@name='username']")
	public WebElement CustomerReg_Email_textbox;
	@FindBy(xpath="//*[@id='password']")
	public WebElement CustomerReg_Password_textbox;
	@FindBy(xpath="//input[@name='cnf_password']")
	public WebElement CustomerReg_ReEnterPassword_textbox;
	@FindBy(xpath="//input[@name='has_agreed']")
	public WebElement CustomerReg_Termsofservice_checkbox;
	@FindBy(xpath="//input[@name='accepted_billing_agreement']")
	public WebElement CustomerReg_billingagrement_checkbox;
	@FindBy(xpath="//*[@id='register_form']/div/button")
	public WebElement CustomerReg_joinourcircle_button;
	@FindBy(xpath="//div/h5/a/u['Sign in']")
	public WebElement CustomerReg_signin_button;
	
			
	@FindBy(xpath="//*[@id='register_form']/div/em[@for='firstname']")
	public WebElement CustomerReg_invalidFirstname_errorMessage;	
	
	@FindBy(xpath="//*[@id='register_form']/div/em[@for='lastname']")
	public WebElement CustomerReg_invalidLastname_errorMessage;	
		
	@FindBy(xpath="//*[@id='register_form']/div/em[text()='The entered email is already registered.']")
	public WebElement CustomerReg_invalidEmail_errorMessage;
	
	@FindBy(xpath="//*[@id='register_form']/div/em[@for='password']")
	public WebElement CustomerReg_invalidPassword_errorMessage;
	
	@FindBy(xpath="//*[@id='register_form']/div/em[@for='cnf_password']")
	public WebElement CustomerReg_invalidRePassword_errorMessage;
	
	@FindBy(xpath="//*[@id='register_form']/div//em[@for='has_agreed']")
	public WebElement CustomerReg_AcceptTermsandConditons_errorMessage;
	
	@FindBy(xpath="//*[@id='register_form']/div//em[@for='accepted_billing_agreement']")
	public WebElement CustomerReg_AcceptourbillingAgreement_errorMessage;		

}
