package option;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import start.Game;

public class GameOptions extends Game {

	static Properties p = new Properties();

	public static int MAX_NUMBERS = setMAX_NUMBERS();
	public static int MAX_TRY = setMAX_TRY();
	public static int PAWNS = setPAWNS();;
/*
	public GameOptions() {
		
	MAX_NUMBERS = setMAX_NUMBERS();
	MAX_TRY = setMAX_TRY();
	PAWNS = setPAWNS();	
	
	}
*/	
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





