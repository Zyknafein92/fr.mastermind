package start;

import option.GameOptions;

public class Main {

	public static void main(String[] args){
		
		GameOptions options = new GameOptions();

		if(args.length >= 1) {
			System.out.println("Mode Developpeur");	
			GameOptions.DEV_MODE = 1;
		}else {
			GameOptions.DEV_MODE = 0;
		}

		Board newGame = new Board();
		newGame.Menu();
	}
}