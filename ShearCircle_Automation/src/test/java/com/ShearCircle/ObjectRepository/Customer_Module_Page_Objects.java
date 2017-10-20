package com.ShearCircle.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Customer_Module_Page_Objects{

	/*public String LOGIN = ".//*[@id='navbar-collapse-1']/span/a[1]";
	public String USERNAME = "username";
	public String PASSWORD = "password";
	public String SIGN = ".//*[@id='login_form']/div[3]/div/center/button";	
	*/
					
	@FindBy(xpath="//div/h1/b[text()='Customer?']//parent::h1/following-sibling::a[text()='Join Our Circle']")	
    public WebElement CR_customerJoinCircle_Link;	
	
	@FindBy(xpath="//div/h1/b[text()='Professional?']//parent::h1/following-sibling::a[text()='Join Our Circle']")	
    public WebElement CR_ProfessionalJoinCircle_Link;	
	
	@FindBy(xpath="//div/h2/b[text()='Join Now']")	
    public WebElement CR_JoinNow_Header;	
	
	@FindBy(xpath="//*[@name='firstname']")	
    public WebElement CR_FirstName_textbox;	
	@FindBy(xpath="//input[@name='lastname']")
	public WebElement CR_LastName_textbox;	
	@FindBy(xpath="//input[@name='username']")
	public WebElement CR_Email_textbox;
	@FindBy(xpath="//*[@id='password']")
	public WebElement CR_Password_textbox;
	@FindBy(xpath="//input[@name='cnf_password']")
	public WebElement CR_ReEnterPassword_textbox;
	@FindBy(xpath="//input[@name='has_agreed']")
	public WebElement CR_Termsofservice_checkbox;
	@FindBy(xpath="//input[@name='accepted_billing_agreement']")
	public WebElement CR_billingagrement_checkbox;
	@FindBy(xpath="//*[@id='register_form']/div/button")
	public WebElement CR_joinourcircle_button;
	@FindBy(xpath="//div/h5/a/u['Sign in']")
	public WebElement CR_signin_button;
	
	
	
	@FindBy(xpath="//*[@id='firstname']")
    public WebElement PF_FirstName_textbox;
	
	@FindBy(xpath="//input[@name='lastname']")
	public WebElement PF_LastName_textbox;
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement PF_Email_textbox;
	
	@FindBy(xpath="//input[@name='business_name']")
	public WebElement PF_businessName_textbox;
	
	@FindBy(xpath="//input[@name='subdomain']")
	public WebElement PF_Subdomain_textbox;
	
	@FindBy(xpath="//*[@id='password']")
	public WebElement PF_Password_textbox;
	
	@FindBy(xpath="//input[@name='cnf_password']")
	public WebElement PF_ReEnterPassword_textbox;
	
	@FindBy(xpath="//input[@name='has_agreed']")
	public WebElement PF_Termsofservice_checkbox;
	
	@FindBy(xpath="//input[@name='accepted_billing_agreement']")
	public WebElement PF_billingagrement_checkbox;
	
	@FindBy(xpath="//*[@id='register_form']/div/button[contains(text(),'free trial')]")
	public WebElement PF_joinourcircle_button;
	
	@FindBy(xpath="//input[@name='business_name']")
	public WebElement PF_signin_button;
			
	

}
