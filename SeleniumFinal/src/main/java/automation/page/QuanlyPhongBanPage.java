package automation.page;

import static org.testng.Assert.assertFalse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import automation.common.CommonBase;
import automation.constant.CT_Department;

public class QuanlyPhongBanPage extends CommonBase {

	private WebDriver driver;

	public QuanlyPhongBanPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void OpenMenu() {
		click(CT_Department.LINK_DEPARTMENT_MANAGEMENT);
		pause(1000);
	}

	public void SearchDepartment(String department) {
		type(CT_Department.TEXT_SEARCH_DEPARTMENT, department);
		click(CT_Department.BUTTON_SEARCH_DEPARTMENT);
	}

	public void AddPhongBan(String phongban) {
		click(CT_Department.BUTTON_ADD_DEPARTMENT);

		isElementPresent(CT_Department.TEXT_DEPARTMENT);
		type(CT_Department.TEXT_DEPARTMENT, phongban);
		click(CT_Department.BUTTON_SAVE);

		isElementPresent(CT_Department.BUTTON_MODAL_SAVE);
		click(CT_Department.BUTTON_MODAL_SAVE);
	}
	
	public void UpdatePhongBan(String truoc, String sau) {
		type(CT_Department.TEXT_SEARCH_DEPARTMENT, truoc);
		click(CT_Department.BUTTON_SEARCH_DEPARTMENT);

		List<WebElement> listDepartment = driver.findElements(CT_Department.BUTTON_EDIT_DEPARTMENT);
		if(listDepartment.size() != 1) assertFalse(listDepartment.size() != 1);
		
		listDepartment.get(0).click();
				
		isElementPresent(CT_Department.TEXT_DEPARTMENT);
		type(CT_Department.TEXT_DEPARTMENT, sau);
		click(CT_Department.BUTTON_SAVE);

		isElementPresent(CT_Department.BUTTON_MODAL_UPDATE);
		click(CT_Department.BUTTON_MODAL_UPDATE);
	}
	
	public void ViewPhongBan(String phongban) {
		type(CT_Department.TEXT_SEARCH_DEPARTMENT, phongban);
		click(CT_Department.BUTTON_SEARCH_DEPARTMENT);
		
		List<WebElement> listDepartment = driver.findElements(CT_Department.BUTTON_VIEW_DEPARTMENT);
		if(listDepartment.size() != 1) assertFalse(listDepartment.size() != 1);
		
		listDepartment.get(0).click();
	}

	public void DeletePhongBan(String phongban) {
		type(CT_Department.TEXT_SEARCH_DEPARTMENT, phongban);
		click(CT_Department.BUTTON_SEARCH_DEPARTMENT);

		List<WebElement> listDepartment;
		do {
			listDepartment = driver.findElements(CT_Department.BUTTON_DELETE_DEPARTMENT);
			for (WebElement department : listDepartment) {
				department.click();
				driver.switchTo().alert().accept();
				pause(100);
			}
		} while (listDepartment.size() > 0);
	}

	public void NavigateToLastPage() {
		// Locate all page number elements (modify the locator to match your pagination)
		List<WebElement> pageNumbers = driver.findElements(By.xpath("//ul[@class='pagination']//a[last()]"));
		if (pageNumbers.size() < 2) {
			return;
		}

		// Find the last page number dynamically
		int lastPageNumber = 1; // Default to the first page
		WebElement lastPage = pageNumbers.get(0);
		for (WebElement page : pageNumbers) {
			String pageText = page.getText();
			try {
				int pageNum = Integer.parseInt(pageText);
				if (lastPageNumber < pageNum) {
					lastPageNumber = pageNum;
					lastPage = page;
				}
			} catch (Exception ex) {
			}
		}
		lastPage.click();
	}

}
