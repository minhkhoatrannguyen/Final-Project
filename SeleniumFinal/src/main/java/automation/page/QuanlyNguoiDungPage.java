package automation.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import automation.common.CommonBase;

public class QuanlyNguoiDungPage extends CommonBase {

	private WebDriver driver;

	@FindBy(name = "department_id")
	WebElement dropdownListPhongBan;
	@FindBy(name = "role_id")
	WebElement dropdownListChucDanh;
	@FindBy(name = "workarea_id")
	WebElement dropdownListKhuLamViec;

	public QuanlyNguoiDungPage(WebDriver driver) {
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
	
	public void clickConfirmButtonToEdit() {
		click(By.xpath("//button[contains(text(),'Chỉnh sửa')]"));
	}
	
	public void searchUSer(String hoten) {
		type(By.xpath("//input[@placeholder='Nhập từ khóa cần tìm kiếm']"), hoten);
		click(By.xpath("//button[contains(text(),'Tìm kiếm')]"));
	}
	
	public void goToTheLastPage() {
		try {
            // Locate all page number elements (modify the locator to match your pagination)
            List<WebElement> pageNumbers = driver.findElements(By.xpath("//ul[@class='pagination']//a[last()]"));

            // Find the last page number dynamically
            int lastPageNumber = 1; // Default to the first page
            for (WebElement page : pageNumbers) {
                String pageText = page.getText();
                try {
                    int pageNum = Integer.parseInt(pageText);
                    if (pageNum > lastPageNumber) {
                        lastPageNumber = pageNum;
                    }
                } catch (NumberFormatException e) {
                    // Ignore non-numeric page links
                }
            }
            // Click on the last page link
            for (WebElement page : pageNumbers) {
                if (page.getText().equals(String.valueOf(lastPageNumber))) {
                	scrollToBottom();
                    page.click();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void clickActivate() {
		List<WebElement> buttons = getElementSPresentDOM(By.xpath("//a[contains(text(),'Kích hoạt')]"));
		if (!buttons.isEmpty()) {
            // Click the first button
            buttons.get(0).click();
		}
	}
	
	public void clickDeactivate() {
		List<WebElement> buttons = getElementSPresentDOM(By.xpath("//a[contains(text(),'Khóa tài khoản')]"));
		if (!buttons.isEmpty()) {
            // Click the first button
            buttons.get(0).click();
		}
	}
	
	public void clickLamMoiMatKhau() {
		List<WebElement> buttons = getElementSPresentDOM(By.xpath("//a[contains(text(),'Làm mới mật khẩu')]"));
		if (!buttons.isEmpty()) {
            // Click the first button
            buttons.get(0).click();
		}
	}
	
	public void editUserInformation(String hoten, String sdt, String phongBan, String chucDanh, String KLV, String userCode) {
		List<WebElement> buttons = getElementSPresentDOM(By.xpath("//a[contains(text(),'Sửa')]"));
		if (!buttons.isEmpty()) {
            // Click the first button
            buttons.get(0).click();
		}
		type(By.name("username"), hoten);
		type(By.name("phone_number"), sdt);
		if ((getElementPresentDOM(By.xpath("//input[@value='active']"))).isSelected()) {
            click(By.xpath("//input[@value='deactive']"));
            System.out.println("Switched to: Hoạt động");
        } else {
        	click(By.xpath("//input[@value='active']"));
        }
		Select phongBanDropdownList = new Select(dropdownListPhongBan);
		phongBanDropdownList.selectByValue(phongBan);
		Select chucDanhDropdownList = new Select(dropdownListChucDanh);
		chucDanhDropdownList.selectByValue(chucDanh);
		Select khuLamViecDropdownList = new Select(dropdownListKhuLamViec);
		khuLamViecDropdownList.selectByValue(KLV);
		type(By.name("code_user"), userCode);
		click(By.xpath("//button[contains(text(),'Lưu')]"));
	}
	
	public void viewUserInformation() {
		List<WebElement> buttons = getElementSPresentDOM(By.xpath("//a[contains(text(),'Xem')]"));
		if (!buttons.isEmpty()) {
            // Click the first button
            buttons.get(0).click();
		}
	}
	
	public String getTextOfEditedPhongBan() {
		Select dropdown = new Select(getElementPresentDOM(By.id("select_department_id")));

        // Get the selected option
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        return selectedOption.getText();
	}
	
	public String getTextOfEditedChucDanh() {
		Select dropdown = new Select(getElementPresentDOM(By.id("select_role_id")));

        // Get the selected option
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        return selectedOption.getText();
	}
	
	public String getTextOfEditedKLV() {
		Select dropdown = new Select(getElementPresentDOM(By.id("select_workarea_id")));

        // Get the selected option
        WebElement selectedOption = dropdown.getFirstSelectedOption();
        return selectedOption.getText();
	}
}
