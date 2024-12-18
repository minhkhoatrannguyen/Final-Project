package automation.testsuite;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.*;

import automation.common.CommonBase;
import automation.constant.CodeStar_Account;
import automation.constant.Constant_URL;
import automation.page.LoginPage;
import automation.page.QuanlyNguoiDungPage;

public class LamMoiMatKhauTest extends CommonBase {
	public LoginPage loginPage;
	public QuanlyNguoiDungPage quanLyNguoiDung;
	
	@BeforeMethod
	public void openFireFox() {
		driver = initFirefoxDriver(Constant_URL.CODESTARCRM);
		loginPage = new LoginPage(driver);
		quanLyNguoiDung = new QuanlyNguoiDungPage(driver);
	}

	@Test(priority = 1)
	public void TC_LamMoiMatKhau() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.searchUSer(CodeStar_Account.hoTen);
		quanLyNguoiDung.clickLamMoiMatKhau();
		driver.switchTo().alert().accept();
		assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Làm mới mật khẩu thành công!!!')]")));
	}

	@AfterMethod
	public void closeWebPage() {
		driver.close();
	}


}
