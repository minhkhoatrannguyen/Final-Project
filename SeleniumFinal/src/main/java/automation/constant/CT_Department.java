package automation.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;

public class CT_Department {
	
	static LocalDateTime currentDateTime = LocalDateTime.now();
	public static String email = "admin@gmail.com";
	public static String password = "12345678";
	
	public static By LINK_DEPARTMENT_MANAGEMENT = By.xpath("//a[@href='http://test-system.crmstar.vn/department']");
	public static By TOAS_SUCCESS = By.xpath("//div[contains(@class , 'toast toast-success')]");
	
	// =================== Danh sách phòng ban =================== 
	public static By TEXT_SEARCH_DEPARTMENT = By.xpath("//input[@name='query']");
	public static By BUTTON_SEARCH_DEPARTMENT = By.xpath("//button[@type='submit' and text()='Tìm kiếm']");
	public static By BUTTON_ADD_DEPARTMENT = By.xpath("//a[@href='http://test-system.crmstar.vn/department/add']//button[text()='Thêm mới']");
	public static By BUTTON_EXPORT_DEPARTMENT = By.xpath("//button[text()='Xuất ra Excel']");
	
	public static By BUTTON_EDIT_DEPARTMENT = By.xpath("//a[contains(@href, '/modify/')]");
	public static By BUTTON_VIEW_DEPARTMENT = By.xpath("//a[contains(@href, '/detail/')]");
	public static By BUTTON_DELETE_DEPARTMENT = By.xpath("//a[contains(@href, '/delete/')]");
	
	public static By LABEL_NOT_FOUND = By.xpath("//h4[text()='Không tìm thấy kết quả']");
	public static By BUTTON_PAGE_LAST = By.xpath("//ul[@class='pagination']//a[last()]");
	
	// =================== Thêm mới phòng ban =================== 
	public static By TEXT_DEPARTMENT = By.xpath("//input[@name='name']");
	public static By BUTTON_CANCEL = By.xpath("//a[@class='btn btn-outline-success' and text()='Hủy']");
	public static By BUTTON_SAVE = By.xpath("//button[@class='btn btn-outline-success' and text()='Lưu']");
	
	public static By BUTTON_MODAL_CANCEL = By.xpath("//button[@class='btn btn-danger' and text()='Hủy']");
	public static By BUTTON_MODAL_SAVE = By.xpath("//button[@form='add_department' and text()='Thêm']");
	public static By BUTTON_MODAL_UPDATE = By.xpath("//button[@form='mod_department' and text()='Thêm']");
	
	public static By LABEL_ERROR = By.xpath("//span[@class='text-error']");
	
	//=================== Chi tiết phòng ban =================== 
	public static By LABEL_TITLE_DETAIL_DEPARTMENTS = By.xpath("//p[@class='text-child-page' and text()='Chi tiết phòng ban']");
	public static By LABEL_TITLE_MODIFY_DEPARTMENTS = By.xpath("//p[@class='text-child-page' and text()='Chỉnh sửa phòng ban']");
}
