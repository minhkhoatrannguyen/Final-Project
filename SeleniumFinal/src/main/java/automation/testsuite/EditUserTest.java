package automation.testsuite;

import static org.testng.Assert.assertEquals;
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

public class EditUserTest extends CommonBase {

	public LoginPage loginPage;
	public QuanlyNguoiDungPage quanLyNguoiDung;
	String hoten = CodeStar_Account.hoTen;
	String phoneNumber = CodeStar_Account.phoneNumber;
	String phongBan = CodeStar_Account.phongBan;
	String chucDanh = CodeStar_Account.chucDanh;
	String khuLamViec = CodeStar_Account.khuLamViec;
	String userCode = CodeStar_Account.userCode;
	
	@BeforeMethod
	public void openFireFox() {
		driver = initFirefoxDriver(Constant_URL.CODESTARCRM);
		loginPage = new LoginPage(driver);
		quanLyNguoiDung = new QuanlyNguoiDungPage(driver);
	}

	@Test(priority = 1)
	public void TC_EditSuccessfully() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.searchUSer(CodeStar_Account.SearchedHoTen);
		quanLyNguoiDung.editUserInformation(hoten, phoneNumber, phongBan, chucDanh, khuLamViec, userCode);
		String editedPhongBan = quanLyNguoiDung.getTextOfEditedPhongBan();
		String editedChucDanh = quanLyNguoiDung.getTextOfEditedChucDanh();
		String editedKLV = quanLyNguoiDung.getTextOfEditedKLV();
		quanLyNguoiDung.clickConfirmButtonToEdit();
		assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Chỉnh sửa thông tin người dùng thành công')]")));
		quanLyNguoiDung.searchUSer(hoten);
		quanLyNguoiDung.viewUserInformation();
		assertEquals((getElementPresentDOM(By.xpath("//label[contains(text(),'Họ và tên :')]/parent::div//following-sibling::div/label")).getText()),hoten);
		assertEquals((getElementPresentDOM(By.xpath("//label[contains(text(),'Số điện thoại :')]/parent::div//following-sibling::div[2]/label")).getText()),phoneNumber);
		assertEquals((getElementPresentDOM(By.xpath("//label[contains(text(),'Phòng ban :')]/parent::div//following-sibling::div/label")).getText()), editedPhongBan);
		assertEquals((getElementPresentDOM(By.xpath("//label[contains(text(),'Chức danh :')]/parent::div//following-sibling::div/label")).getText()), editedChucDanh);
		assertEquals((getElementPresentDOM(By.xpath("//label[contains(text(),'Khu vực làm việc :')]/parent::div//following-sibling::div/label")).getText()),editedKLV);
		assertEquals((getElementPresentDOM(By.xpath("//label[contains(text(),'Mã người dùng :')]/parent::div//following-sibling::div/label")).getText()),userCode);
	}

//	@AfterMethod
//	public void closeWebPage() {
//		driver.close();
//	}


}
