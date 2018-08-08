package option;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameOptions {

	static Properties p = new Properties();

	public static final int MAX_NUMBERS = setMAX_NUMBERS();
	public static final int MAX_TRY = setMAX_TRY();
	public static final int PAWNS = setPAWNS();

	
	public static int setMAX_TRY() {

		try {
			InputStream is = new FileInputStream("conf/config.properties");
			p.load(is);
			is.close();
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt(p.getProperty("MAX_TRY"));
	}

	public static int setPAWNS() {

		try {
			InputStream is = new FileInputStream("conf/config.properties");
			p.load(is);
			is.close();
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt(p.getProperty("PAWNS"));
	}

	public static int setMAX_NUMBERS() {

		try {
			InputStream is = new FileInputStream("conf/config.properties");
			p.load(is);
			is.close();
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt(p.getProperty("MAX_NUMBERS"));
	}

}





