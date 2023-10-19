package tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UniqueImageFileName {
	public static String getUniqueImageFileName(String root) {
		String uuid = UUID.randomUUID().toString();
		
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String currTime = ldt.format(dtf);
		
		int dot = root.lastIndexOf(".");
		String extensionFileName = ".jpg";
		if(dot > 0 && dot < root.length() - 1) {
			extensionFileName = root.substring(dot);
		}
		 
		return uuid + currTime + extensionFileName;
	}
}
