package com.ShearCircle.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import net.sourceforge.htmlunit.corejs.javascript.ast.WhileLoop;

public class CommonFunctions extends StaticVariables {
	/************** CommonFunctions Constructor *********************/
	public CommonFunctions() {
		ProjectDir = System.getProperty("user.dir");

		File file = new File(ProjectDir + "\\screenshots");
		boolean a = false;
		if (!file.exists()) {
			a = file.mkdir();
		}
		if (a)
			System.out.println("screenshots folder successfully created.");
		else
			System.out.println("screenshots folder already exists.");

		ScreenshotsPath = ProjectDir + "\\screenshots\\";
		System.out.println("Current Project Physical Location: " + ProjectDir);
		System.out.println("For screenshots path: " + ScreenshotsPath);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
	}

	/********************* Launch Browser *******************************/
	public void getAndOpenBrowser(String browser) throws IOException {
		try {
			if (browser.equalsIgnoreCase("IE")) {
				// IE browser script
				System.setProperty("webdriver.ie.driver", iEDriverPath());
				driver = new InternetExplorerDriver();
			}
			if (browser.equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath());
				driver = new ChromeDriver();
			}
			if (browser.equalsIgnoreCase("FIREFOX")) {
				System.setProperty("webdriver.gecko.driver", fireFoxDriverPath());
				driver = new FirefoxDriver();
			}
			if (browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
			Reporter.log(e.getMessage());
		}
		// to delete all cookies
		driver.manage().deleteAllCookies();
		// implisit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// IEDriverPath
	public String iEDriverPath() throws IOException {
		String IEDriverPath = ProjectDir + "\\drivers\\ie-32bit\\IEDriverServer.exe";
		return IEDriverPath;
	}

	// ChromeDriverPath
	public String chromeDriverPath() throws IOException {
		String ChromeDriverPath = ProjectDir + "\\drivers\\chromedriver.exe";
		return ChromeDriverPath;
	}

	// FireFoxDriverPath
	public String fireFoxDriverPath() throws IOException {
		String FireFoxDriverPath = ProjectDir + "\\drivers\\geckodriver.exe";
		return FireFoxDriverPath;
	}

	/************* Enter URL ****************/
	public void launchURL(String URL) {
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*************** ScreenshotOnPassFail **********************/
	public void screenshotOnPassFail(ITestResult testResult) throws IOException {

		StaticVariables.ClassName = testResult.getTestClass().getName().trim();
		StaticVariables.MethodName = testResult.getName().trim();
		String ClsNmMtdNm = StaticVariables.ClassName + "_" + StaticVariables.MethodName + "_";

		if (testResult.getStatus() != ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println("ClsNmMtdNm: " + ClsNmMtdNm + "; TimeStampasString" + timeStampasString());
			FileUtils.copyFile(scrFile,
					new File(ScreenshotsPath + "Pass_" + ClsNmMtdNm + timeStampasString() + ".jpg"));
		}
		if (testResult.getStatus() == ITestResult.FAILURE) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			System.out.println("ClsNmMtdNm: " + ClsNmMtdNm + "; TimeStampasString" + timeStampasString());
			FileUtils.copyFile(scrFile,
					new File(ScreenshotsPath + "Fail_" + ClsNmMtdNm + timeStampasString() + ".jpg"));
		}
	}

	// scrollDown
	public void scrollDown(int down) {
		System.out.println("***scrollDown: Move to default Content explicitly. Otherwise it won't work for Firefox***");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + down + ")", "");
	}

	public void scrollUp(int up) {
		System.out.println("***scrollUp: Move to default Content explicitly. Otherwise it won't work for Firefox***");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + up + ",0)", "");
	}

	public String timeStampasString() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public String getDateFormat(String dateformat) {
		String timeStamp = new SimpleDateFormat(dateformat).format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public String getTimeFormat(String timeformat) {
		String timeStamp = new SimpleDateFormat(timeformat).format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public void takeScreenshot(String Name) throws IOException, Exception {
		// String filepath = FileOrDriverPathOf("ScreenshotsPath");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(ScreenshotsPath + Name + timeStampasString() + ".png"));
			System.out.println("***Placed screen shot in " + ScreenshotsPath + " ***");
			Reporter.log("***Placed screen shot in " + ScreenshotsPath + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String testDataPathOf(String TestDataFileName) throws IOException {
		String TestDataPath = ProjectDir + "\\TestData\\" + TestDataFileName;
		return TestDataPath;
	}

	/****** ExplicitWait ******/
	public void explicitWaitUsingElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitforelementtobevisible(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitforelementinvisibility(WebElement element, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void Fluent_Wait(WebElement El) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {

				return El;
			}
		});
	}

	/**
	 * instead of fluent wait use customized While loop statement
	 * 
	 * @throws Exception
	 *****/

	public void waitforElement(String locater) throws Exception {
		int i = 0;
		while (driver.findElements(By.xpath(locater)).size() < 1) {
			Thread.sleep(500);
			System.out.println("Wait for the element***************");
			// Suppose system has not present the element it will repeat 30 time then stop
			// the execution using break
			if (i > 30) {
				System.out.println("Element is not present");
				break;

			}
		}
	}

	/***********
	 * Get Random number with in the range
	 ********************************/
	public int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public int RandomNo(int Max) {
		int randomInt = 0;
		Random rg = new Random();
		for (int idx = 1; idx <= Max; ++idx) {
			randomInt = rg.nextInt(Max);
			if (randomInt == 0) {
				randomInt = 1;
			}
			System.out.println("Generated : " + randomInt);
		}
		return randomInt;
	}

	/**********************/

	/*************** Click using JavaScript **************/
	public void clickUsingJavaScript(WebElement element) {
		// WebElement Button = driver.findElement(By.xpath(locater));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	/****************** Check Multiple Checkboxes ************************/

	public void safeSelectCheckBoxes(int waitTime, WebElement... elements) throws Exception {
		WebElement checkElement = null;
		try {
			if (elements.length > 0) {
				for (WebElement currentElement : elements) {
					checkElement = currentElement;
					WebDriverWait wait = new WebDriverWait(driver, waitTime);
					wait.until(ExpectedConditions.elementToBeClickable(currentElement));

					WebElement checkBox = currentElement;
					if (checkBox.isSelected())
						System.out.println("CheckBox " + currentElement + " is already selected");
					else
						checkBox.click();
				}
			} else {
				System.out.println("Expected atleast one element as argument to safeSelectCheckboxes function");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + checkElement + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + checkElement + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to select checkbox " + e.getStackTrace());
		}
	}

	/******** Assertion using TestNG ******************/

	public void assertionByusingWebElement(WebElement element, String Inputdata) {
		try {
		String ActualMsg = element.getText();
		String ExpectedMsg = Inputdata;
		Assert.assertEquals(ExpectedMsg, ActualMsg);
		System.out.println("************Assertion Done**********");
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to find element " + e.getStackTrace());
		}
	}

	public void assertEquals(String receivedMessage, String expectedMessage) throws Exception {
		Thread.sleep(2000);
		Assert.assertEquals(receivedMessage, expectedMessage);
	}

	/******************* To Clear the edit/Text box ***********/
	public void clearText(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.clear();
			} else {
				System.out.println("Element existance and enabled status Failed");
				Reporter.log("Element existance and enabled status Failed");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());

		}
	}

	/************************
	 * ScrollToElement
	 * 
	 * @throws Exception
	 **********************/

	/****************** ScrollToElementBottom *****************************/

	public void ScrollToElementBottom(WebElement element) {
		try {
		System.out.println("***ScrollToElementBottom:  ***");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());

		}
	}

	public void scrollintoviewelement(WebElement element) {
		try {
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} catch (StaleElementReferenceException e) {
			System.out.println(
					"Element - " + element + " is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}

	}

	/********* Read Data from Properties file *********************/

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
		String keyvlaue = null;
		try {
			
			keyvlaue = prop.getProperty(key);
		
		}catch(Exception e){
			System.out.println("Error description: " + e.getStackTrace());
		}
		return keyvlaue;

	}

	/********************** TestDataPathOf ******************/
	public String TestDataPathOf(String TestDataFileName) throws IOException {

		String TestDataPath = ".\\TestData\\" + TestDataFileName;
		return TestDataPath;

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
						"The Element " + Reporttext + "should be displayed", "The Element " + Reporttext + "displayed");

			} else {
				this.reportscomtep("Failed", "Verify The Element is " + Reporttext + "displayed",
						"The Element " + Reporttext + "should be displayed",
						"The Element " + Reporttext + "Not displayed");

			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}
/*******************Get webElement attribute value****************/
	public String elementgetAttributevalue(WebElement element, String p_in_attributename) {
		String attributevalue = "";
		try {
			if (element.isDisplayed()) {
				attributevalue = element.getAttribute(p_in_attributename);
			} else {
				System.out.println("Element existance and enabled status Failed");
			}

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
		return attributevalue;
	}
/**********************Get the webelement text************************/
	public String getelementtext(WebElement element) {
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
/*
 *Created date:21/10/2017
 *Description:
 *Parameters:
 *ReturnType:
 */
	public void check_Checkbox(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				if (element.isSelected()) {
					System.out.println("Check box already selected");
				} else {
					element.click();
				}

			} else {
				System.out.println("Check box existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
	}

	public void click(WebElement element) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				element.click();

			} else {
				System.out.println("Element existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void selectByVisibleText(WebElement element, String p_in_inputvalue) {
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

	public void selectByIndex(WebElement element, int p_in_inputvalue) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByIndex(p_in_inputvalue);
			} else {
				System.out.println("Element existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void selectByValue(WebElement element, String p_in_inputvalue) {
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByValue(p_in_inputvalue);
			} else {
				System.out.println("Element existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
	}

	public List<WebElement> getOptions(WebElement element) {
		List<WebElement> elementCount = null;
		try {
			if (element.isDisplayed() && element.isEnabled()) {
				Select dropdown = new Select(element);
				elementCount = dropdown.getOptions();
			} else {
				System.out.println("Element existance and enabled status Failed");
			}
		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}
		return elementCount;
	}

	/************************* MouseHover Actions ************/

	public void mouseHoveronElement(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.click().build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void mouseHoverClickandHold(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.clickAndHold(element);
			actions.click().build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void mouseHoverContextClick(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.contextClick(element);
			actions.click().build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void doubleClick(WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.doubleClick(element);
			actions.click().build().perform();

		} catch (Exception e) {
			System.out.println("Error in description: " + e.getStackTrace());
		}

	}

	public void dragandDrop(WebElement sourceElement, WebElement destinationElement) {
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				System.out.println("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + sourceElement + "or" + destinationElement
					+ "is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
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

	/******************* Get & verify URLStatus *******************/
	public void verifyURLStatus(String URL) {
		// http://roadtoautomation.blogspot.in/2013/04/road-to-verify-200-response-code-of-web.html
		try {
			WebClient webClient = new WebClient();
			HtmlPage htmlPage = webClient.getPage(URL);
			System.out.println("Status Code: " + htmlPage.getWebResponse().getStatusCode() + "; Web Response: "
					+ htmlPage.getWebResponse().getStatusMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**************** GetYLocationOfElement*******************/
	public int GetYLocationOfElement(WebElement element) {
		System.out.println("***GetYLocationOfElement: Don't forget to move to your frame.***");
		Point point = element.getLocation();
		int y = point.getY();
		System.out.println("Y Coordination of the Element: " + y);
		return y;
	}
	public int GetXLocationOfElement(WebElement element) {
		System.out.println("***GetXLocationOfElement: Don't forget to move to your frame.***");
		Point point = element.getLocation();
		int x = point.getX();
		System.out.println("X Coordination of the Element: " + x);
		return x;
	}

	public void ScrollToXY(int x, int y) {
		System.out.println("***ScrollToXY: Move to default Content explicitly. Otherwise it won't work. 'Call switchToDefaultFrame and don't forget to move to your frame.' ***");
		String script = "window.scrollTo(" + x + "," + y + ");";
		((JavascriptExecutor) driver).executeScript(script);
	}
	
	/*********** HighlightElement ******************/
	public void HighlightElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
	}
	
	/*********************** String functions **********************************/
	public String RemoveSpaceAndNonAlphaFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-zA-Z]", "");
		return Output;
	}

	public String RemoveNonAlphaExcludingSpaceFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-z A-Z]", "");
		return Output;
	}

	public String RemoveNonAlphaNumericFromString(String inputString) {
		String Output = inputString.replaceAll("[^a-z A-Z0-9]", "");
		return Output;
	}public String RemoveNonNumericFromString(String inputString) {
		String Output = inputString.replaceAll("[^0-9]", "");
		return Output;
	}

	public String RemoveSpaceAndAlpha(String inputString) {
		String Output = inputString.replaceAll("[a-z A-Z]", "");
		return Output;
	}

	

	
	

}
