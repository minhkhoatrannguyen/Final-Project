package automation.testsuite;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.common.CommonBase;
import automation.constant.CodeStar_Account;
import automation.constant.Constant_URL;
import automation.page.LoginPage;
import automation.page.QuanlyNguoiDungPage;

public class ExportCSVFileTest extends CommonBase {

	public LoginPage loginPage;
	public QuanlyNguoiDungPage quanLyNguoiDung;
	String downloadFilePath = "path/to/download/directory";
	
	@BeforeMethod
	public void openFireFox() {
		driver = initFirefoxDriver(Constant_URL.CODESTARCRM);
		loginPage = new LoginPage(driver);
		quanLyNguoiDung = new QuanlyNguoiDungPage(driver);
	}

	@Test(priority = 1)
	public void TC_ExportCSVFile() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.clickExportCSVFile(downloadFilePath);
	}
	
	@AfterMethod
	public void closeWebPage() {
		driver.close();
	}
}
