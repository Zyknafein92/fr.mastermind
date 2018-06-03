package typegame;

import java.util.Arrays;

import start.Board;
import start.Game;

public class Defenseur extends Game  {



public Defenseur () {
	
	this.secret = this.generateInputPlayer();
	this.combinaison = this.generateBotRoll();
}

public void playdefenseur() {
	
	System.out.println(rulesDefenseur());
	System.out.println(" Votre combinaison secrète est :"+Arrays.toString(secret));
	
	do {
		
		for(int i = 0; i < combinaison.length; i++ ) {
			if (this.secret[i] > combinaison[i]) {
				combinaison[i]++;}
			if (this.secret[i] < combinaison[i]) {
				combinaison[i]--;}
			}
		
		Board.tried++;
		System.out.println("Tour" +Board.tried +Arrays.toString(combinaison));
		win(secret, combinaison);
		
		if (win == true) {

			System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");

		}else if (Board.tried < Board.life) {

			System.out.println("Mauvaise combinaison ! Essaye encore !");

		}else {

			System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal");
		}
	
	}while (Board.tried < Board.life && win == false);
}

private static  String rulesDefenseur() {
	String str1 = "";

	str1 = ("\r\n------------------------------");
	str1 +=("\r\n--------- Defenseur ----------");
	str1 +=("\r\n------------------------------");
	str1 +=("\r\nVous devez entrer une combinaison mystère que l'ordinateur va devoir trouver.");
	str1 +=("\r\nElle est composée de "+Board.optM + " chiffres compris entre 0 et 9.");
	str1 +=("\r\nL'ordinateur à le droit a "+Board.life + " tentative(s) !");
	str1 +=("\r\nA vous de jouer !");
	return str1;
}
}
