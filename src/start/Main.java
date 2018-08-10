package start;

import option.GameOptions;

public class Main {

	public static void main(String[] args){
		
		if( args.length > 0 && args[0].equals("dev"))  {
			System.out.println("Mode Developpeur");	
			GameOptions.DEV_MODE = 1;
		}else {
			GameOptions.DEV_MODE = 0;
		}

		Board newGame = new Board();
		newGame.Menu();
	}
}