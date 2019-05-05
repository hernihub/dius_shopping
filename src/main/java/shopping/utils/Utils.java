package shopping.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	public static Properties getPricingRule(String prname) {
        Properties prop = new Properties();		
		try (InputStream input = Utils.class.getClassLoader().getResourceAsStream(String.format("%s.%s", prname, "properties"))) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return prop;
	}

	public static Float getFloatWithDecimals(float total, int i) {
		String floatString = String.format ("%.2f", total);		
		return Float.parseFloat(floatString);
	}
}
