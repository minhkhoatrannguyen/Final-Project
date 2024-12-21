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

public class ViewDepartment extends CommonBase {
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
		quanlyPhongBanPage.OpenMenu();
		quanlyPhongBanPage.AddPhongBan("TrangTT201");
	}
	
	@BeforeMethod
	public void openDepartmentManagement() {
		quanlyPhongBanPage.OpenMenu();
	}
	
	/*
	 * Case này phát hiện lỗi message xác nhận update đang không hiển thị nội dung text.
	 */
	@Test(priority = 1)
	public void XemPhongBan001() {
		quanlyPhongBanPage.ViewPhongBan("TrangTT201");
		assertTrue(isElementPresent(CT_Department.LABEL_TITLE_DETAIL_DEPARTMENTS));
	}
	
	@Test(priority = 2)
	public void XemPhongBan002() {
		quanlyPhongBanPage.ViewPhongBan("TrangTT201");
		click(CT_Department.BUTTON_CANCEL);
		Assert.assertTrue(driver.getCurrentUrl().contains("http://test-system.crmstar.vn/department"));
	}
	
	@Test(priority = 3)
	public void XemPhongBan003() {
		quanlyPhongBanPage.ViewPhongBan("TrangTT201");
		click(CT_Department.BUTTON_EDIT_DEPARTMENT);
		assertTrue(isElementPresent(CT_Department.LABEL_TITLE_MODIFY_DEPARTMENTS));
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


