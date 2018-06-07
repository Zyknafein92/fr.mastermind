package typegame;

import java.util.Arrays;

import start.Board;
import start.Game;
import tools.BotRoll;
import tools.Input;

public class Defenseur extends Game  {


	BotRoll PC = new BotRoll();
	
public Defenseur () {
	
	
	this.combinaison = PC.getBotRoll();
}

public void playdefenseur() {
	
	System.out.println(rulesDefenseur());
	Input player = new Input();
	this.secret = player.getInput();
	System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));
	
	do {
		
		for(int i = 0; i < combinaison.length; i++ ) {
			if (this.secret[i] > combinaison[i]) {
				combinaison[i]++;}
			if (this.secret[i] < combinaison[i]) {
				combinaison[i]--;}
			}
		
		Board.tried++;
		System.out.println("Tour" +Board.tried +Arrays.toString(combinaison));
		iswin();
		
		if (win == true) {

			System.out.println("Perdu ! L'ordinateur a trouv� votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");

		}else if (Board.tried < Board.life) {

			System.out.println("Mauvaise combinaison ! Essaye encore !");

		}else {

			System.out.println("Vous avez gagn� ! L'ordinateur n'a pas trouv� votre combinaison ! Retour au menu principal");
		}
	
	}while (Board.tried <= Board.life && win == false);
}

private static  String rulesDefenseur() {
	String str1 = "";

	str1 = ("\r\n------------------------------");
	str1 +=("\r\n--------- Defenseur ----------");
	str1 +=("\r\n------------------------------");
	str1 +=("\r\nVous devez entrer un nombre myst�re que l'ordinateur va devoir trouver.");
	str1 +=("\r\nIl est compos� de "+Board.optM + " chiffres compris entre 0 et 9.");
	str1 +=("\r\nL'ordinateur � le droit � "+Board.life + " tentative(s) !");
	str1 +=("\r\nA vous de jouer !");
	return str1;
}
}
