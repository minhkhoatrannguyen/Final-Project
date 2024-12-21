package automation.testsuite;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.testng.annotations.*;

import automation.common.CommonBase;
import automation.constant.CodeStar_Account;
import automation.constant.Constant_URL;
import automation.page.QuanlyNguoiDungPage;
import automation.page.LoginPage;

public class ActivateOrDeactivateUser extends CommonBase {

	public LoginPage loginPage;
	public QuanlyNguoiDungPage quanLyNguoiDung;
	
	@BeforeMethod
	public void openFireFox() {
		driver = initFirefoxDriver(Constant_URL.CODESTARCRM);
		loginPage = new LoginPage(driver);
		quanLyNguoiDung = new QuanlyNguoiDungPage(driver);
	}

	@Test(priority = 1)
	public void TC_ActivateOrDeactivateUser() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.searchUSer(CodeStar_Account.SearchedHoTen);
		quanLyNguoiDung.clickActivate();
		driver.switchTo().alert().accept();
		assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Đã kích hoạt người dùng')]")));
		quanLyNguoiDung.clickDeactivate();
		driver.switchTo().alert().accept();
		assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Đã vô hiệu hóa người dùng')]")));
	}
	@AfterMethod
	public void closeWebPage() {
		driver.close();
	}

}
