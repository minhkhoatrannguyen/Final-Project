package automation.testsuite.departments;

import static org.testng.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import automation.common.CommonBase;
import automation.constant.CT_Department;
import automation.constant.Constant_URL;
import automation.page.QuanlyPhongBanPage;
import automation.page.LoginPage;

public class AddDepartment extends CommonBase {
	public LoginPage loginPage;
	public QuanlyPhongBanPage quanlyPhongBanPage;


	@BeforeSuite
	public void openFireFox() {
		driver = initFirefoxDriver(Constant_URL.CODESTARCRM);
		loginPage = new LoginPage(driver);
		quanlyPhongBanPage = new QuanlyPhongBanPage(driver);
	}
	
	@BeforeTest
	public void Login() {
		loginPage.loginFunction(CT_Department.email, CT_Department.password);
	}
	
	@BeforeMethod
	public void openDepartmentManagement() {
		quanlyPhongBanPage.OpenMenu();
	}
	
	@Test(priority = 1)
	public void ThemPhongBan001() {
		quanlyPhongBanPage.AddPhongBan("TrangTT001");
		pause(1000);
		assertTrue(isElementPresent(CT_Department.TOAS_SUCCESS));
	}

	@Test(priority = 2)
	public void ThemPhongBan002() {
		quanlyPhongBanPage.AddPhongBan("TrangTT002");
		pause(1000);
		quanlyPhongBanPage.AddPhongBan("TrangTT002");
		pause(1000);
		assertTrue("Tên phòng ban đã tồn tại".equals(getElementPresentDOM(CT_Department.LABEL_ERROR).getText()));
	}
	
	@AfterTest
	public void XoaPhongBanDaThem() {
		quanlyPhongBanPage.OpenMenu();
		quanlyPhongBanPage.DeletePhongBan("TrangTT");
	}

	@AfterSuite
	public void closeWebPage() {
		driver.close();
	}
}


