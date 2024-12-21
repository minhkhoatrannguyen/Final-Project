package automation.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.common.CommonBase;
import automation.constant.CT_Department;

public class LoginPage extends CommonBase {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void loginFunction(String email, String password) {
		type(By.id("email"),email);
		type(By.id("password"),password);
		click(By.name("signin"));
	}
	
//	public void logoutFunction() {
//        WebElement btnAdmin = driver.findElement(CodeStar_Account.USERNAME_BUTTON);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        boolean isToastGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Đăng nhập thành công!')]")));
//        if (isToastGone) {
//        	btnAdmin.click();
//		}
//        WebElement btnLogout = driver.findElement(CodeStar_Account.LOGOUT_BUTTON);
//        if (btnLogout.isDisplayed()) {
//        	btnLogout.click();
//		}
//        WebElement btnLogoutConfirm = driver.findElement(CodeStar_Account.LOGOUTCONFIRM_BUTTON);
//        if (btnLogoutConfirm.isDisplayed()) {
//        	btnLogoutConfirm.click();
//		}
//	}
	public void waitForSuccessfullyToastMessageShowing() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Đăng nhập thành công!')]")));
	}
	
	public void waitForIncorrectEmailPasswordToastMessageShowing() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Email hoặc mật khẩu không đúng')]")));
	}
}
