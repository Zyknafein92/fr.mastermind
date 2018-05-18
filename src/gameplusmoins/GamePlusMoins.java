package gameplusmoins;

import start.Board;



public abstract class GamePlusMoins {

	static int mod = Board.mod;



	public GamePlusMoins(int []PC) {

		PC = PC();

	}	

	public static int[] PC () {
		int PC[] = new int [Board.optM];

		for(int i= 0;i< Board.optM;i++) 
		{
			PC[i] = (int) (Math.random()*10); 

		}
		return PC();
	}
	
}
