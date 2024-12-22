package automation.testsuite.qlklv;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
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

public class ViewAndUpdateKVLV extends CommonBase {
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
	
	/*
	 * Case này phát hiện lỗi message xác nhận update đang không hiển thị nội dung text.
	 */
	@Test(priority = 1)
	public void XemvaupdateKVLV001() {
		quanlyKLV.CapnhatKVLV("KV003455","KVLV132");
		assertTrue(isElementPresent(CT_Khulamviec.LABEL_TITLE_DETAIL_KVLV));
	}
	
	@Test(priority = 2)
	public void XemKVLV002() {
		quanlyKLV.ViewKVLV("KVLV132");
		click(CT_Khulamviec.BUTTON_CANCEL);
		Assert.assertTrue(driver.getCurrentUrl().contains("http://test-system.crmstar.vn/work-space-management"));
	}
	
	@Test(priority = 3)
	public void XemKVLV003() {
		quanlyKLV.ViewKVLV("KVLV132");
		click(CT_Khulamviec.BUTTON_EDIT_KVLV);
		assertTrue(isElementPresent(CT_Khulamviec.LABEL_TITLE_MODIFY_KVLV));
	}
	
	
	@Test(priority = 4)
	public void SuaKVLV001() {
		quanlyKLV.CapnhatKVLV("KV0034555","KVLV1322");
		assertTrue(isElementPresent(CT_Khulamviec.TOAS_SUCCESS));
	}
	
//	@AfterTest
//	public void XoaKVLVDaThem() {
//		quanlyKLV.OpenMenu();
//		quanlyKLV.DeleteKVLV("KVLV005"
//				+ "");
//	}

	@AfterSuite
	public void closeWebPage() {
		driver.close();
	}

}
