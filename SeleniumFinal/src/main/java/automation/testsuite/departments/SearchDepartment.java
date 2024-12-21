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

public class SearchDepartment extends CommonBase {
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
	public void TimKiemPhongBan001() {
		quanlyPhongBanPage.SearchDepartment("Phòngaaaa");
		pause(2000);
		assertTrue(getElementPresentDOM(CT_Department.LABEL_NOT_FOUND).isDisplayed());
	}
	
	/*
	 * Test này phát hiện ra lỗi
	 * Khi tìm kiếm có điều kiện sau đó chuyển page thì điều kiện bị mất 
	 * và kết quả lại là tìm kiếm tất cả. 
	 */
	@Test(priority = 2)
	public void TimKiemPhongBan002() {
		quanlyPhongBanPage.SearchDepartment("Phòng");
		pause(2000);
		quanlyPhongBanPage.NavigateToLastPage();
	}

	@AfterSuite
	public void closeWebPage() {
		driver.close();
	}
}


