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

public class AddAndDeleteKVLV extends CommonBase {
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
	public void ThemVaXoakvlv001() {
		// Khai báo random mã KLV và tên KLV
		String maKLV = quanlyKLV.generateRandomString(4, 4);
		String tenKLV = quanlyKLV.generateRandomString(4, 4);
		// Add KLV mới với mã và tên được khai báo ở trên
		quanlyKLV.ThemKhuLamViec(maKLV, tenKLV);
		assertTrue(isElementPresent(CT_Khulamviec.TOAS_SUCCESS));
		// Xóa KLV mới được add với mã và tên được khai báo ở trên
		//quanlyKLV.OpenMenu();
		quanlyKLV.DeleteKVLV(tenKLV);
		//Them ham assert duoi nay xoa thanh cong 
	}

	@Test(priority = 2)
	public void ThemkvLVKhongthanhcong002() {
		quanlyKLV.ThemKhuLamViec("KV003455", "KVLV132");
//		pause(1000);
		assertTrue("Tên khu vực làm việc đã tồn tại".equals(getElementPresentDOM(CT_Khulamviec.LABEL_ERROR).getText()));
	}

//	@AfterSuite
//	public void closeWebPage() {
//		driver.close();
//	}

}