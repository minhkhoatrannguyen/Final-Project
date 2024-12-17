package automation.testsuite;

import static org.testng.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import automation.common.CommonBase;
import automation.constant.CodeStar_Account;
import automation.constant.Constant_URL;
import automation.page.AddNewUserPage;
import automation.page.LoginPage;

public class AddUserTest extends CommonBase {
	public LoginPage loginPage;
	public AddNewUserPage addNewUserPage;

	@BeforeMethod
	public void openFireFox() {
		driver = initFirefoxDriver(Constant_URL.CODESTARCRM);
		loginPage = new LoginPage(driver);
		addNewUserPage = new AddNewUserPage(driver);
	}

	@Test(priority = 1)
	public void TC1_AddNewUserAndSearchSuccessfully() {
		String hoten = CodeStar_Account.hoTen;
		String email = CodeStar_Account.userEmail;
		String phoneNumber = CodeStar_Account.phoneNumber;
		String phongBan = CodeStar_Account.phongBan;
		String chucDanh = CodeStar_Account.chucDanh;
		String khuLamViec = CodeStar_Account.khuLamViec;
		String userCode = CodeStar_Account.userCode;
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		addNewUserPage.AddNewUserFunction(hoten, email, phoneNumber, phongBan, chucDanh, khuLamViec, userCode);
		addNewUserPage.clickConfirmButtonToAdd();
		assertTrue(isElementPresent(By.xpath("//div[contains(text(),'Thêm mới người dùng thành công')]")));
		addNewUserPage.searchUSer(hoten);
		if (isElementPresent(By.xpath("//li[@aria-label='« Previous']/parent::ul"))) {
			addNewUserPage.goToTheLastPage();
			assertTrue(isElementPresent(By.xpath("//td[text()='" + email + "']/parent::tr")));
			
		} else {
		assertTrue(isElementPresent(By.xpath("//td[text()='" + email + "']/parent::tr")));
		}
	}

	@Test(priority = 2)
	public void TC2_FailToAddNewUserByInputInvalidFormat() {
		loginPage.loginFunction(CodeStar_Account.email, CodeStar_Account.password);
		addNewUserPage.AddNewUserFunction("Khoa Trần", "1", "1", "3", "1", "7", "abc123");
		List<WebElement> elements = driver
				.findElements(By.xpath("//div[contains(text(),'Dữ liệu nhập vào sai định dạng')]"));
		for (WebElement element : elements) {
			assertTrue(element.isDisplayed(), "Element is not visible.");
		}
	}

//	@AfterMethod
//	public void closeWebPage() {
//		driver.close();
//	}

}
