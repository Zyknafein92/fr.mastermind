package option;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameOptions   {
	
	
	public static int MAX_NUMBERS;
	public static int MAX_TRY;
	public static int PAWNS;
	
	
	static Properties p = new Properties();
	
	public GameOptions() {
		
		MAX_NUMBERS = getMAX_NUMBERS();
		MAX_TRY = getMAX_TRY();
		PAWNS = getPAWNS();
		
	}

	public static int getMAX_TRY() {

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

	public static int getPAWNS() {

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

	public static int getMAX_NUMBERS() {

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



