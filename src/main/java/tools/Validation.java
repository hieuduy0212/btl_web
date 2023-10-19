package tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Validation {
	public static boolean isBlank(String str) {
		return str.isBlank();
	}

	public static boolean isNumber(String str) {
		return Pattern.compile("\\d+").matcher(str).matches();
	}

	public static boolean isEmail(String str) {
		String reg = "^[A-Za-z0-9._%+-]+\\@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(str).matches();
	}

	public static boolean isDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			sdf.parse(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isReleaseDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			Date releaseDate = sdf.parse(str);
			Date currentDate = new Date();
			int diff = currentDate.compareTo(releaseDate);
			if (diff >= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isUsername(String str) {
		return Pattern.compile("^[a-zA-Z0-9._-]{3,}$").matcher(str).matches();
	}

	public static boolean isPhoneNumber(String str) {
		return Pattern.compile("^\\d{10}$").matcher(str).matches();
	}

	public static boolean isZipcode(String str) {
		return Pattern.compile("^\\d{6}$").matcher(str).matches();
	}

}
