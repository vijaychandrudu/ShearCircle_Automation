package com.ShearCircle.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

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
	public void GetAndOpenBrowser(String browser) throws IOException {
		try {
			if (browser.equalsIgnoreCase("IE")) {
				// IE browser script
				System.setProperty("webdriver.ie.driver", IEDriverPath());
				driver = new InternetExplorerDriver();
			}
			if (browser.equalsIgnoreCase("CHROME")) {
				System.setProperty("webdriver.chrome.driver", ChromeDriverPath());
				driver = new ChromeDriver();
			}
			if (browser.equalsIgnoreCase("FIREFOX")) {
				System.setProperty("webdriver.gecko.driver", FireFoxDriverPath());
				driver = new FirefoxDriver();
			}
			if (browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		// to delete all cookies
		driver.manage().deleteAllCookies();
		// implisit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// IEDriverPath
	public String IEDriverPath() throws IOException {
		String IEDriverPath = ProjectDir + "\\drivers\\ie-32bit\\IEDriverServer.exe";
		return IEDriverPath;
	}

	// ChromeDriverPath
	public String ChromeDriverPath() throws IOException {
		String ChromeDriverPath = ProjectDir + "\\drivers\\chromedriver.exe";
		return ChromeDriverPath;
	}

	// FireFoxDriverPath
	public String FireFoxDriverPath() throws IOException {
		String FireFoxDriverPath = ProjectDir + "\\drivers\\geckodriver.exe";
		return FireFoxDriverPath;
	}

	/************* Enter URL ****************/
	public void TypeURL(String URL) {
		driver.navigate().to(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

	public String TimeStampasString() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public void takeScreenshot(String Name) throws IOException, Exception {
		// String filepath = FileOrDriverPathOf("ScreenshotsPath");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(ScreenshotsPath + Name + TimeStampasString() + ".png"));
			System.out.println("***Placed screen shot in " + ScreenshotsPath + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String TestDataPathOf(String TestDataFileName) throws IOException {
		String TestDataPath = ProjectDir + "\\TestData\\" + TestDataFileName;
		return TestDataPath;
	}

	/********** to click on any element by locater ******************/
	public void ClickByXpath(String locaterName) {
		driver.findElement(By.xpath(locaterName)).click();
	}

	public void ClickById(String locaterName) {
		driver.findElement(By.id(locaterName)).click();
	}

	public void ClickByNm(String locaterName) {
		driver.findElement(By.name(locaterName)).click();
	}
	
	public void ClickByLink(String locater)
	{
		driver.findElement(By.linkText(locater)).click();
	}

	/**** to type data in edit box By any Locaters ********/
	public void TypebyId(String LocaterName, String Inputdata) {
		driver.findElement(By.id(LocaterName)).clear();
		driver.findElement(By.id(LocaterName)).sendKeys(Inputdata);
	}

	public void TypebyName(String LocaterName, String Inputdata) {
		driver.findElement(By.name(LocaterName)).clear();
		driver.findElement(By.name(LocaterName)).sendKeys(Inputdata);
	}

	public void TypebyXpath(String LocaterName, String Inputdata) {
		driver.findElement(By.xpath(LocaterName)).clear();
		driver.findElement(By.xpath(LocaterName)).sendKeys(Inputdata);
	}

	/****** ExplicitWait ******/
	public void ExplicitWaitByIdUsingElementToBeClickable(String locater) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(locater)));
	}

	public void ExplicitWaitByXpathUsingElementToBeClickable(String locater) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locater)));
	}

	public void ExplicitWaitByXpathUsingPresenceOfElementLocated(String locater) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locater)));
	}

	/*************** Click using JavaScript **************/
	public void ClickUsingJavaScript(String locater) {
		WebElement Button = driver.findElement(By.xpath(locater));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", Button);
	}

	/******** Assertion using TestNG ******************/

	public void AssertionByusingXpath(String locater, String Inputdata) {
		String ActualMsg = driver.findElement(By.xpath(locater)).getText();
		String ExpectedMsg = Inputdata;
		Assert.assertEquals(ExpectedMsg, ActualMsg);
		System.out.println("************Assertion Done**********");
	}

	/******************* To Clear the edit/Text box ***********/
	public void ClearByXpath(String LocaterName) {
		driver.findElement(By.xpath(LocaterName)).clear();
	}

	public void ClearByName(String LocaterName) {
		driver.findElement(By.name(LocaterName)).clear();
	}

	public void ClearById(String LocaterName) {
		driver.findElement(By.id(LocaterName)).clear();
	}

	/************************
	 * ScrollToElement
	 * 
	 * @throws Exception
	 **********************/
	public void ScrollToElement(String locater) throws Exception {
		WebElement element = driver.findElement(By.xpath(locater));
		System.out.println("***ScrollToElement: ***");// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
	}

	public void ScrollToElementById(String locater) throws Exception {
		WebElement element = driver.findElement(By.id(locater));
		System.out.println("***ScrollToElement: ***");// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
	}

	/****************** ScrollToElementBottom *****************************/
	public void ScrollToElementBottom(WebElement element) {
		System.out.println("***ScrollToElementBottom:  ***");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
	}

}
