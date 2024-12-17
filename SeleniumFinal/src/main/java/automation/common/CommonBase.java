package automation.common;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonBase {

	public static WebDriver driver;
	public int initWaitTime = 50;

	public WebDriver initChromeDriver(String URL) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		System.out.println("user.dir is: " + System.getProperty("user.dir"));
		driver = new ChromeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(initWaitTime, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver initFirefoxDriver(String URL) {
		System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
		System.out.println("user.dir is: " + System.getProperty("user.dir"));
		driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(initWaitTime, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver initMSEdgeDriver(String URL) {
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\driver\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(initWaitTime, TimeUnit.SECONDS);
		return driver;
	}
	
	public WebDriver initChromeDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		System.out.println("user.dir is: " + System.getProperty("user.dir"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(initWaitTime, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver initFirefoxDriver() {
		System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
		System.out.println("user.dir is: " + System.getProperty("user.dir"));
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(initWaitTime, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver initMSEdgeDriver() {
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\driver\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(initWaitTime, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver setUpDriver(String browserName) {
		switch (browserName.trim()) {
		case "chrome":
			System.out.println("Initialize chrome browser...");
			initChromeDriver();
			break;
		case "firefox":
			System.out.println("Initialize firefox browser...");
			initFirefoxDriver();
			break;
		case "edge":
			System.out.println("Initialize msedge browser...");
			initMSEdgeDriver();
			break;
		default:
			System.out.println("Browser: " + browserName + " is invalid, launching Chrome as browser of choice...");
			initChromeDriver();
		}
		return driver;
	}

	public WebElement getElementPresentDOM(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}

	public List<WebElement> getElementSPresentDOM(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElements(locator);
	}

	public void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isElementPresent(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
			wait.until(ExpectedConditions.visibilityOf(getElementPresentDOM(locator)));
			return getElementPresentDOM(locator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		} catch (org.openqa.selenium.TimeoutException e2) {
			return false;
		}
	}

	public void inputTextJavacript_ToInnerHTMLAttribute(By inputElement, String value) {
		WebElement element = getElementPresentDOM(inputElement);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		try {
			executor.executeScript("arguments[0].innerHTML = '" + value + "'", element);
		} catch (StaleElementReferenceException ex) {
			pause(1000);
			inputTextJavacript_ToInnerHTMLAttribute(inputElement, value);
		}
	}

	public void inputTextJavacript_ToValueAttribute(By locator, String value) {
		WebElement element = getElementPresentDOM(locator);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + value + "'", element);
		} catch (StaleElementReferenceException ex) {
			pause(1000);
			inputTextJavacript_ToValueAttribute(locator, value);
		}
	}

	public void scrollToElement(By locator) {
		WebElement element = getElementPresentDOM(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void click(By locator) {
		WebElement element = getElementPresentDOM(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(initWaitTime));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void type(By locator, String value) {
		WebElement element = getElementPresentDOM(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void pause(long timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void waitForAlert(long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

		try {
			// Wait until the alert is present
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
		}
	}

	public String getTextOfAlert() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		return alertText;
	}
}
