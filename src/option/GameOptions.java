package option;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameOptions   {
		
	public static final int MAX_TRY = 15;   //getMAX_TRY();
	public static final int PAWNS =4; //getPAWNS();
	public static final int MAX_NUMBERS = 6; //getMAX_NUMBERS();
	static Properties p = new Properties();
	
	public static final int getMAX_TRY() {

		try {
			InputStream is = new FileInputStream("config.properties");
			p.load(is);
			is.close();
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt(p.getProperty("MAX_TRY"));
	}
	
	public static final int getPAWNS() {

		try {
			InputStream is = new FileInputStream("config.properties");
			p.load(is);
			is.close();
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt(p.getProperty("PAWNS"));
	}
	
	public static final int getMAX_NUMBERS() {

		try {
			InputStream is = new FileInputStream("config.properties");
			p.load(is);
			is.close();
		}
		catch ( IOException e ) {
			e.printStackTrace( );
		}
		return Integer.parseInt(p.getProperty("MAX_NUMBERS"));
	}

	
}



