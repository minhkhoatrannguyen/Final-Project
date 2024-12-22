package automation.testsuite.qlklv;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_Khulamviec;
import automation.constant.Constant_URL;
import automation.page.LoginPage;
import automation.page.QuanlyKLVPage;


public class SearchKVLV extends CommonBase  {
	public LoginPage loginPage;
	public QuanlyKLVPage quanlyKLV;



	@BeforeSuite
	public void openFireFox() {
		driver = initFirefoxDriver(Constant_URL.CODESTARCRM);
		loginPage = new LoginPage(driver);
		quanlyKLV = new QuanlyKLVPage(driver);
	}
	
	@BeforeTest
	public void Login() {
		loginPage.loginFunction(CT_Khulamviec.email, CT_Khulamviec.password);
	}
	
	@BeforeMethod
	public void openquanlykvlv() {
		quanlyKLV.OpenMenu();
	}

	@Test(priority = 1)
	public void TimKiemKVLV001() {
		quanlyKLV.SearchKVLV("KVLV132");
		pause(1000);
		assertTrue(getElementPresentDOM(CT_Khulamviec.LABEL_NOT_FOUND).isDisplayed());
	}
	
	/*
	 * Test này phát hiện ra lỗi
	 * Khi tìm kiếm có điều kiện sau đó chuyển page thì điều kiện bị mất 
	 * và kết quả lại là tìm kiếm tất cả. 
	 */
	@Test(priority = 2)
	public void TimKiemKVLV002() {
		quanlyKLV.SearchKVLV("KVLV132");
		pause(1000);
		quanlyKLV.NavigateToLastPage();
	}

	@AfterSuite
	public void closeWebPage() {
		driver.close();
	}
}
