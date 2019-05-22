package helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helper {
	
	public static float round(float f) {
		BigDecimal bd = new BigDecimal(f).setScale(1, RoundingMode.HALF_UP ); //10c round
        return bd.floatValue(); //return float value
	}
	
}
