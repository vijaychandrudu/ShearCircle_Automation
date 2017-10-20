package com.ShearCircle.ObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_page_objects {
	@FindBy(xpath="//div/div[1]/a/img")
    public WebElement Home_ShearCircle_Image;
	
	@FindBy(xpath="//*[contains(@id,'navbar-collapse')]/span/a[text()='Login']")
    public WebElement Home_Login_Link;	
	
	@FindBy(xpath="//*[@id='navbar-collapse-1']/span/a[contains(text(),'Circle')]")
    public WebElement Home_JoinOurCircle_Link;
	
	@FindBy(xpath="//*[@id='username']")
    public WebElement Login_UserName_textbox;
	@FindBy(xpath="//*[@id='password']")
    public WebElement Login_PassWord_textbox;
	@FindBy(xpath="//*[@id=\"login_form\"]/div/p/a/u[text()='Forgot Your Password ?']")
    public WebElement Login_forgotPassword_Link;
	@FindBy(xpath="//*[@id='login_form']/div/div/center/button")
    public WebElement Login_signin_button;
	@FindBy(xpath="//*[@id='customBtn']")
    public WebElement Login_google_Link;
	@FindBy(xpath="//*[@id='fbLink']")
    public WebElement Login_facebook_Link;
	@FindBy(xpath="//section/div/h5/a/u")
    public WebElement Login_JoinOurCircle_Link;
	
	
	@FindBy(xpath="//div/h2/strong[text()='Reset Password']")
    public WebElement ResetPassword_Header;
	
	@FindBy(xpath="//form[@id='forgot_password_form']/div/*[@id='username']")
    public WebElement ResetPassword_UserName_textbox;
	
	@FindBy(xpath="//*[@id='forgot_password_form']/div/button[text()='Send instructions to reset password']")
    public WebElement ResetPassword_SendInstruction_Link;
	
	@FindBy(xpath="//div/h5/a/u[text()='Cancel']")
    public WebElement ResetPassword_Cancel_textbox;
	
	@FindBy(xpath="//div/div/h4[@class='valid-message']")
    public WebElement ResetPassword_ValidMessage_text;
	
	@FindBy(xpath="//div/div/h4[@class='invalid-message']")
    public WebElement ResetPassword_InvalidMessage_text;
	
	
	
	
	
	

}
