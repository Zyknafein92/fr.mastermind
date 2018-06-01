package gamemastermind;

import java.util.Arrays;
import java.util.Scanner;

import start.Board;
import start.Game;

public class GameMastermind extends Game {
	
	Scanner sc = new Scanner(System.in);
	
public GameMastermind () {
	
}

public void generateRandomCombo () {
	
	
}


public void generatePlayerCombo () {
	
	
	char[] playerCombo = new char[Board.optM];	
	String userc = "";
	
	do {
		System.out.println("\r\nVeuillez entrer les " + +Board.optM + " premières lettres des couleurs choisies.");
		userc = sc.nextLine();

		if (userc.length() != Board.optM) 
		{
			System.out.println("Attention, vous n'avez pas sélectionné le bon nombre de lettres !");
		}  
		else 
		{
		
			for ( int i=0; i<playerCombo.length; i++)
			{
				playerCombo[i] = userc.charAt(i);
			}
		}
	} while (userc.length() != Board.optM);
	this.playerCombo = playerCombo;
     System.out.println(Arrays.toString(playerCombo));
}

@Override
public void randomPlayer() {
	// TODO Auto-generated method stub
	
}

@Override
public void randomComptuer() {
	// TODO Auto-generated method stub
	
}
	}


