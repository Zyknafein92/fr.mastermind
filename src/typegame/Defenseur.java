package typegame;

import java.util.Arrays;

import gameplusmoins.GamePlusMoins;
import start.Board;

public class Defenseur  {

	boolean win = false;
    int tempComputer[] = new int [Board.optM];

public Defenseur () {
	
	this.generateComputerCombo();
	playdefenseur();
}

public void playdefenseur() {
	
	System.out.println(rulesDefenseur());
	
	this.generatePlayerCombo();
	this.generateComputerCombo();

	System.out.println(" Votre combinaison est :"+Arrays.toString(getPlayerCombo()));
	tempComputer = this.getSecretCombo();
	do {
		
		for(int i = 0; i < tempComputer.length; i++ ) {
			if (this.getPlayerCombo()[i] > tempComputer[i]) {
			tempComputer[i]++;}
			if (this.getPlayerCombo()[i] < tempComputer[i]) {
			tempComputer[i]--;}
			}
		
		Board.tried++;
		System.out.println("Tour" +Board.tried +Arrays.toString(tempComputer));
		win();
		
		if (win == true) {

			System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");

		}else if (Board.tried < Board.life) {

			System.out.println("Mauvaise combinaison ! Essaye encore !");

		}else {

			System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal");
		}
	
	}while (Board.tried < Board.life && win == false);
     Board Menu = new Board();
	 Menu.doGame();
}

private boolean win () {

	if (Arrays.equals(this.getPlayerCombo(),tempComputer)) {
		win = true;
	}else {
		win = false;
	}
	return win;
}

private static  String rulesDefenseur() {
	String str1 = "";

	str1 = ("\r\n------------------------------");
	str1 +=("\r\n--------- Defenseur ---------");
	str1 +=("\r\n------------------------------");
	str1 +=("\r\nVous devez entrer une combinaison mystère que l'ordinateur va devoir trouver.");
	str1 +=("\r\nElle est composée de "+Board.optM + " chiffres compris entre 0 et 9.");
	str1 +=("\r\nL'ordinateur à le droit a "+Board.life + " tentative(s) !");
	str1 +=("\r\nA vous de jouer !");
	return str1;
}
}
