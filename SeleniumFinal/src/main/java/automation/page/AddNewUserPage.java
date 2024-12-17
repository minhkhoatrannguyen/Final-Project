package automation.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import automation.common.CommonBase;

public class AddNewUserPage extends CommonBase {

	private WebDriver driver;

	@FindBy(name = "department_id")
	WebElement dropdownListPhongBan;
	@FindBy(name = "role_id")
	WebElement dropdownListChucDanh;
	@FindBy(name = "workarea_id")
	WebElement dropdownListKhuLamViec;

	public AddNewUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void AddNewUserFunction(String hoten, String email, String sdt, String phongban, String chucdanh,
			String khulamviec, String usercode) {
		click(By.xpath("//button[contains(text(),'Thêm mới')]"));
		type(By.name("username"), hoten);
		type(By.name("email"), email);
		type(By.name("phone_number"), sdt);
		Select phongBanDropdownList = new Select(dropdownListPhongBan);
		phongBanDropdownList.selectByValue(phongban);
		Select chucDanhDropdownList = new Select(dropdownListChucDanh);
		chucDanhDropdownList.selectByValue(chucdanh);
		Select khuLamViecDropdownList = new Select(dropdownListKhuLamViec);
		khuLamViecDropdownList.selectByValue(khulamviec);
		type(By.name("code_user"), usercode);
		click(By.xpath("//button[contains(text(),'Lưu')]"));
	}

	public void clickConfirmButtonToAdd() {
		click(By.xpath("//button[@form='add_user']"));
	}
	
	public void searchUSer(String hoten) {
		type(By.xpath("//input[@placeholder='Nhập từ khóa cần tìm kiếm']"), hoten);
		click(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
	}
	
	public void goToTheLastPage() {
		List<WebElement> pageNumbers = driver.findElements(By.xpath("//li[@aria-label='« Previous']/parent::ul")); 
        // Modify the XPath based on your page's HTML structure

        // 2. Find the last page number dynamically
        int lastPageNumber = 1; // Default to page 1
        for (WebElement page : pageNumbers) {
            String pageText = page.getText();
            try {
                int pageNum = Integer.parseInt(pageText); // Convert text to number
                if (pageNum > lastPageNumber) {
                    lastPageNumber = pageNum; // Update to the largest page number
                }
            } catch (NumberFormatException e) {
                // Ignore non-numeric values (e.g., "Next", "Previous" buttons)
            }
        }
        // 3. Click on the last page number
        for (WebElement page : pageNumbers) {
            if (page.getText().equals(String.valueOf(lastPageNumber))) {
                page.click(); // Click the last page number
                break;
            }
        }
	}
}
