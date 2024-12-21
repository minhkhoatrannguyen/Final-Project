package automation.testsuite.qlklv;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CT_Department;
import automation.constant.CT_Khulamviec;
import automation.constant.Constant_URL;
import automation.page.LoginPage;
import automation.page.QuanlyKLVPage;
import automation.page.QuanlyPhongBanPage;

public class AddKVLV extends CommonBase {
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
	public void Themkvlv001() {
		quanlyKLV.ThemmoiKVLV("KVLV005");
		pause(1000);
		assertTrue(isElementPresent(CT_Khulamviec.TOAS_SUCCESS));
	}

	@Test(priority = 2)
	public void ThemkvLV002() {
		quanlyKLV.ThemmoiKVLV("KVLV005");
		pause(1000);
		quanlyKLV.ThemmoiKVLV("KVLV005");
		pause(1000);
		assertTrue("Tên phòng ban đã tồn tại".equals(getElementPresentDOM(CT_Khulamviec.LABEL_ERROR).getText()));
	}
	
	@AfterTest
	public void XoaKVLVdathem() {
		quanlyKLV.OpenMenu();
		quanlyKLV.DeleteKVLV("KVLV005");
	}

	@AfterSuite
	public void closeWebPage() {
		driver.close();
	}

}