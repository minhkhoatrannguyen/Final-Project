package automation.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;

public class CodeStar_Account {

	public static String generateRandomAlphabeticString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            result.append(alphabet.charAt(index));
        }

        return result.toString();
    }
	static LocalDateTime currentDateTime = LocalDateTime.now();
    static DateTimeFormatter formatterOfEmail = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    static DateTimeFormatter formatterOfUserCode = DateTimeFormatter.ofPattern("MMddHHmmss");
    static String formattedDateTimeOfEmail = currentDateTime.format(formatterOfEmail);
    static String formattedDateTimeOfUserCode = currentDateTime.format(formatterOfUserCode);
	public static String email = "admin@gmail.com";
	public static String password = "12345678";
	public static String hoTen = generateRandomAlphabeticString(8);
	public static String SearchedHoTen = "KhoaTran";
	public static String userEmail = "khoatran" + formattedDateTimeOfEmail + "@gmail.com";
	public static String phoneNumber = "123456789";
	static Random random = new Random();
	public static String phongBan = String.valueOf(random.nextInt(8) + 1);
	public static String chucDanh = String.valueOf(random.nextInt(6) + 1);
	public static String khuLamViec = String.valueOf(random.nextInt(13) + 1);
	public static String userCode = formattedDateTimeOfUserCode;

}
