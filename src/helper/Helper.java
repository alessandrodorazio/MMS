package helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helper {

	public static float round(float f) {
		BigDecimal bd = new BigDecimal(f).setScale(1, RoundingMode.HALF_UP); // 10c round
		return bd.floatValue(); // return float value
	}
	
	public static int intFromString(String s) {
		if(s.isEmpty()) return 0;
		return Integer.valueOf(s);
	}

}
