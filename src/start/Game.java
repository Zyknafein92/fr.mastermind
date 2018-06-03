package start;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public abstract class Game {
	
	
	protected Integer secret[] = new Integer[Board.optM];
	protected Integer combinaison[] = new Integer[Board.optM];   
	protected Integer botroll[] = new Integer[Board.optM];
	protected Integer player[] = new Integer[Board.optM];
	protected boolean win = false;
	

	static Scanner sc = new Scanner(System.in); 


	public boolean win (Integer[] secret,Integer[]combinaison) {
		if (Arrays.equals(this.secret, this.combinaison))
		win = true;
		else
	    win = false;
       return win;		
	}
	
	
	public void setWin(boolean win) {
		this.win = win;
	}
}
	
	
