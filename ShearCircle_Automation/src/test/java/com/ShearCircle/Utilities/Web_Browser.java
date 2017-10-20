package com.ShearCircle.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

//import com.sun.org.apache.bcel.internal.generic.Select;

public class Web_Browser extends StaticVariables {

	// public Properties prop;
	public Web_Browser(WebDriver driver) {
		super.driver = driver;
	}

	Properties prop = new Properties();

	// to get the data from Property file
	public void loaddata(String path) {

		File file = new File(path);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getdata(String key) {
		String keyvlaue = prop.getProperty(key);
		return keyvlaue;

	}

	public String TestDataPathOf(String TestDataFileName) throws IOException {
		
		String TestDataPath = ".\\TestData\\" + TestDataFileName;
		return TestDataPath;

	}
public void scrollintoviewelement(WebElement element) {
	try {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}catch(Exception e) {
		System.out.println("Error description: " + e.getStackTrace());
	}
	
	
}
	public boolean elmentisdisplayed(WebElement element) {
		boolean elementdisplayedflag = false;
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				elementdisplayedflag = true;
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
		return elementdisplayedflag;

	}

	public void Verify_elmentisdisplayed_Report(WebElement element, String Reporttext) {

		try {
			if (element.isDisplayed() && element.isEnabled()) {
				this.reportscomtep("Passed", "Verify The Element is " + Reporttext + "displayed",
						"The Element is " + Reporttext + "should be displayed",
						"The Element is " + Reporttext + "displayed");

			} else {
				this.reportscomtep("Passed", "Verify The Element is " + Reporttext + "displayed",
						"The Element is " + Reporttext + "should be displayed",
						"The Element is " + Reporttext + "Not displayed");

			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public String elementgetAttributevalue(WebElement element, String p_in_propertyname) {
		String attributevalue = "";
		try {
			if (element.isDisplayed()) {
				attributevalue = element.getAttribute(p_in_propertyname);
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
		return attributevalue;

	}

	public String elementgettext(WebElement element) {
		String textvalue = "";
		try {
			if (element.isDisplayed()) {
				textvalue = element.getText();
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
		return textvalue;

	}

	public void sendkeys(WebElement element, String p_in_inputvalue) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.sendKeys(p_in_inputvalue);
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void check_Checkbox(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				;
			} else {
				System.out.println("Check box existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}

	public void click(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();
				;
			} else {
				System.out.println("Element existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void select_Dropdownvalue(WebElement element, String p_in_inputvalue) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText(p_in_inputvalue);
			} else {
				System.out.println("Element existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	/******************** Log Report ***************************************/

	public void reportscomtep(String status, String Description, String Expectedvalue, String Actualvalue) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		switch (status) {
		case "Passed":
		case "Pass":
		case "pass":
		case "passed":
		case "PASSED":
			System.out.println(status + ", " + Description + ", " + Expectedvalue + ", " + Actualvalue);
			try {
				FileUtils.copyFile(scrFile, new File(ScreenshotsPath + "Pass_" + TimeStampasString() + ".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Failed":
		case "Fail":
		case "fail":
		case "failed":
		case "FAILED":
			System.out.println(status + ", " + Description + ", " + Expectedvalue + ", " + Actualvalue);
			try {
				FileUtils.copyFile(scrFile, new File(ScreenshotsPath + "Pass_" + TimeStampasString() + ".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	/*************** ScreenshotOnPassFail **********************/
	public void ScreenshotOnPassFail(ITestResult testResult) throws IOException {

		StaticVariables.ClassName = testResult.getTestClass().getName().trim();
		StaticVariables.MethodName = testResult.getName().trim();
		String ClsNmMtdNm = StaticVariables.ClassName + "_" + StaticVariables.MethodName + "_";

		if (testResult.getStatus() != ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println("ClsNmMtdNm: " + ClsNmMtdNm + "; TimeStampasString" + TimeStampasString());
			FileUtils.copyFile(scrFile,
					new File(ScreenshotsPath + "Pass_" + ClsNmMtdNm + TimeStampasString() + ".jpg"));
		}
		if (testResult.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println("ClsNmMtdNm: " + ClsNmMtdNm + "; TimeStampasString" + TimeStampasString());
			FileUtils.copyFile(scrFile,
					new File(ScreenshotsPath + "Fail_" + ClsNmMtdNm + TimeStampasString() + ".jpg"));
		}
	}

	public String TimeStampasString() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

}
