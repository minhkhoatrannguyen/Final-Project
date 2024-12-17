package automation.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;

public class CodeStar_Account {

	static LocalDateTime currentDateTime = LocalDateTime.now();
    static DateTimeFormatter formatterOfEmail = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    static DateTimeFormatter formatterOfUserCode = DateTimeFormatter.ofPattern("MMddHHmmss");
    static String formattedDateTimeOfEmail = currentDateTime.format(formatterOfEmail);
    static String formattedDateTimeOfUserCode = currentDateTime.format(formatterOfUserCode);
	public static By EMAIL_TEXTBOX = By.id("email");
	public static By PASSWORD_TEXTBOX = By.id("password");
	public static By DANGNHAP_BUTTON = By.xpath("//button[@name='signin']");
	public static By USERNAME_BUTTON = By.id("dropdownMenuLink");
	public static By LOGOUT_BUTTON = By.xpath("//button[contains(text(),'Đăng xuất') and @type='button']");
	public static By LOGOUTCONFIRM_BUTTON = By.xpath("//button[contains(text(),'Đăng xuất') and @type='submit']");
	public static String email = "admin@gmail.com";
	public static String incorrectEmail = "khoaminhtran1999@gmail.com";
	public static String password = "12345678";
	public static String incorrectPassword = "abcabc@";
	public static String hoTen = "KhoaTran";
	public static String userEmail = "khoatran" + formattedDateTimeOfEmail + "@gmail.com";
	public static String phoneNumber = "123456789";
	static Random random = new Random();
	public static String phongBan = String.valueOf(random.nextInt(8) + 1);
	public static String chucDanh = String.valueOf(random.nextInt(6) + 1);
	public static String khuLamViec = String.valueOf(random.nextInt(13) + 1);
	public static String userCode = formattedDateTimeOfUserCode;

}
