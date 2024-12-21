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

public class DeleteDepartment extends CommonBase {
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
		quanlyPhongBanPage.AddPhongBan("TrangTT301");
	}
	
	/*
	 * Case này phát hiện lỗi message xác nhận update đang không hiển thị nội dung text.
	 */
	@Test(priority = 1)
	public void XemPhongBan001() {
		quanlyPhongBanPage.DeletePhongBan("TrangTT301");
		pause(1000);
		quanlyPhongBanPage.SearchDepartment("TrangTT301");
		pause(1000);
		assertTrue(getElementPresentDOM(CT_Department.LABEL_NOT_FOUND).isDisplayed());
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


