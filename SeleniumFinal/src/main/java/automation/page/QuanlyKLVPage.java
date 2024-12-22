package automation.page;

import static org.testng.Assert.assertFalse;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import automation.common.CommonBase;
import automation.constant.CT_Department;
import automation.constant.CT_Khulamviec;

public class QuanlyKLVPage extends CommonBase {
	private WebDriver driver;

	public QuanlyKLVPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void OpenMenu() {
		click(CT_Khulamviec.LINK_KLV_MANAGEMENT);
		pause(1000);
	}

	public void SearchKVLV(String KVLV) {
		type(CT_Khulamviec.TEXT_SEARCH_KVLV, KVLV);
		click(CT_Khulamviec.BUTTON_SEARCH_KVLV);
	}
	
	public void ThemKhuLamViec(String maKLV, String tenKLV) {
		click(By.xpath("//a[normalize-space()='Quản lý khu làm việc']"));
		click(By.xpath("//button[text()='Thêm mới']"));
		type(By.name("work_areas_code"), maKLV);
		type(By.name("name"), tenKLV);
		click(By.xpath("//button[contains(text(),'Lưu')]"));
		click(By.xpath("//button[contains(text(),'Thêm')]"));
	}

	public void ThemmoiKVLV(String KVLV) {
		click(CT_Khulamviec.BUTTON_ADD_KVLV);

		//isElementPresent(CT_Khulamviec.TEXT_KVLV);
		type(CT_Khulamviec.TEXT_KVLV,KVLV);
		click(CT_Khulamviec.BUTTON_SAVE);

		//isElementPresent(CT_Khulamviec.BUTTON_MODAL_SAVE);
		click(CT_Khulamviec.BUTTON_MODAL_SAVE);
	}	
	
	public void CapnhatKVLV(String truoc, String sau) {
		type(CT_Khulamviec.TEXT_SEARCH_KVLV, truoc);
		click(CT_Khulamviec.BUTTON_SEARCH_KVLV);

		List<WebElement> listKVLV = driver.findElements(CT_Khulamviec.BUTTON_EDIT_KVLV);
		if(listKVLV.size() != 1) assertFalse(listKVLV.size() != 1);
		
		listKVLV.get(0).click();
				
		isElementPresent(CT_Khulamviec.TEXT_KVLV);
		type(CT_Khulamviec.TEXT_KVLV, sau);
		click(CT_Khulamviec.BUTTON_SAVE);

		isElementPresent(CT_Khulamviec.BUTTON_MODAL_UPDATE);
		click(CT_Khulamviec.BUTTON_MODAL_UPDATE);
	}
	
	public void ViewKVLV(String KVLV) {
		type(CT_Khulamviec.TEXT_SEARCH_KVLV, KVLV);
		click(CT_Khulamviec.BUTTON_SEARCH_KVLV);
		
		List<WebElement> listKVLV = driver.findElements(CT_Khulamviec.BUTTON_VIEW_KVLV);
		if(listKVLV.size() != 1) assertFalse(listKVLV.size() != 1);
		
		listKVLV.get(0).click();
	}
	
	public void DeleteKVLV(String KVLV) {
		type(CT_Khulamviec.TEXT_SEARCH_KVLV,KVLV);
		click(CT_Khulamviec.BUTTON_SEARCH_KVLV);
		click(By.xpath("//td[text()='" + KVLV + "']/following-sibling::td/descendant::a[contains(text(),'Xóa')]"));
		driver.switchTo().alert().accept();


//		List<WebElement> listKVLV;
//		do {
//			listKVLV = driver.findElements(CT_Khulamviec.BUTTON_DELETE_KVLV);
//			for (WebElement KVLV1 : listKVLV) {
//				KVLV1.click();
//				driver.switchTo().alert().accept();
//				pause(100);
//			}
//		}
//			while (listKVLV.size() > 0);
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
	public String generateRandomString(int length, int minCaps) {
		if (length < minCaps) {
			throw new IllegalArgumentException(
					"String length must be at least as long as the number of required capital letters.");
		}

		Random random = new Random();
		StringBuilder result = new StringBuilder(length);

		// Define the character pools
		String lower = "abcdefghijklmnopqrstuvwxyz";
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String allChars = lower + upper;

		// Add the minimum required capital letters
		for (int i = 0; i < minCaps; i++) {
			result.append(upper.charAt(random.nextInt(upper.length())));
		}

		// Fill the remaining characters randomly from all character pools
		for (int i = minCaps; i < length; i++) {
			result.append(allChars.charAt(random.nextInt(allChars.length())));
		}

		// Shuffle the result to mix capital letters
		return shuffleString(result.toString(), random);
	}

	// Utility method to shuffle a string
	private String shuffleString(String input, Random random) {
		char[] characters = input.toCharArray();
		for (int i = characters.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			char temp = characters[i];
			characters[i] = characters[j];
			characters[j] = temp;
		}
		return new String(characters);
	}
}
	
	
	
	
	
	
	
	
	
	
	