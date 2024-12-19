package automation.testsuite;

import static org.testng.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import automation.common.CommonBase;
import automation.constant.CodeStar_Account;
import automation.constant.Constant_URL;
import automation.page.QuanlyNguoiDungPage;
import automation.page.LoginPage;

public class AddUserTest extends CommonBase {
	public LoginPage loginPage;
	public QuanlyNguoiDungPage quanLyNguoiDung;
	String hoten = CodeStar_Account.hoTen;
	String email = CodeStar_Account.userEmail;
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
	public void TC1_AddNewUserAndSearchSuccessfully() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.AddNewUserFunction(hoten, email, phoneNumber, phongBan, chucDanh, khuLamViec, userCode);
		quanLyNguoiDung.clickConfirmButtonToAdd();
		assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Thêm mới người dùng thành công')]")));
		quanLyNguoiDung.searchUSer(hoten);
		assertTrue(isElementPresent(By.xpath("//td[text()='" + email + "']/parent::tr")));
	}

	@Test(priority = 2)
	public void TC2_FailToAddNewUserByInputInvalidFormat() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.AddNewUserFunction("Khoa Trần", "1", "1", "3", "1", "7", "abc123");
		List<WebElement> elements = driver
				.findElements(By.xpath("//div[contains(text(),'Dữ liệu nhập vào sai định dạng')]"));
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed(), "Element is not visible.");
		}
	}
	
	@Test(priority = 3)
	public void TC3_FailToAddNewUserByLeavingAllFieldsWithBlank() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.AddNewUserFunction("", "", "", "", "", "", "");
		List<WebElement> elements = getElementSPresentDOM(By.xpath("//div[contains(text(),'Không được để trống trường này')]"));
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed(), "Element is not visible.");
		}
	}
	
	@Test(priority = 4)
	public void TC4_FailToAddNewUserByInputExistedEmailUserCode() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		quanLyNguoiDung.AddNewUserFunction(hoten, "khoaminhtran1999@gmail.com", phoneNumber, phongBan, chucDanh, khuLamViec, "1707");
		quanLyNguoiDung.clickConfirmButtonToAdd();
		assertTrue(getElementPresentDOM(By.xpath("//span[contains(text(),'Email đã tồn tại')]")).isDisplayed());
		assertTrue(getElementPresentDOM(By.xpath("//span[contains(text(),'Mã người dùng đã tồn tại')]")).isDisplayed());
		}

	@AfterMethod
	public void closeWebPage() {
		driver.close();
	}
}


