package automation.testsuite.qlklv;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_Khulamviec;
import automation.constant.Constant_URL;
import automation.page.LoginPage;
import automation.page.QuanlyKLVPage;

public class DeleteKVLV extends CommonBase {
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
		quanlyKLV.ThemmoiKVLV("KVLV006");
	}
	
	/*
	 * Case này phát hiện lỗi message xác nhận update đang không hiển thị nội dung text.
	 */
	@Test(priority = 1)
	public void XemKVLV001() {
		quanlyKLV.DeleteKVLV("KVLV006");
		pause(1000);
		quanlyKLV.SearchKVLV("KVLV006");
		pause(1000);
		assertTrue(getElementPresentDOM(CT_Khulamviec.LABEL_NOT_FOUND).isDisplayed());
	}
	

	@AfterTest
	public void XoaKVLVDaThem() {
		quanlyKLV.OpenMenu();
		quanlyKLV.DeleteKVLV("KVLV006");
	}

	@AfterSuite
	public void closeWebPage() {
		driver.close();
	}

}
