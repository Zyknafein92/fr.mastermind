package typegame;



import java.util.Arrays;

import start.Board;
import start.Game;
import tools.BotRoll;
import tools.Input;


public class Challenger extends Game {


	BotRoll PC = new BotRoll();


	public Challenger () {
		this.secret = PC.getBotRoll();
	}

	public void playChallengerPlusMoins() {

		System.out.println(rulesChallengerPlusMoins());
		System.out.println(Arrays.toString(this.secret));

		do {
			Input player = new Input();
			this.combinaison = player.getInput();

			compareChallenger(this.secret,this.combinaison);

			iswin(this.secret,this.combinaison);
			
			System.out.print(resultat(combinaison,Soluc));
            
			if (iswin == true) {
				System.out.println("Bravo, vous avez gagné en "+Board.tried + " tentative(s) !");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison !");
			}else {
				System.out.println("Vous avez perdu, retour au menu principal");
			}
			Board.tried++;
		} while (Board.tried <= Board.life && iswin == false);
	}


	public void playChallengerMastermind() {

		System.out.println(rulesChallengerMastermind());
		System.out.println(Arrays.toString(this.secret));

		do {

			Input player = new Input();
			this.combinaison = player.getInput();	
			compareChallengerMasterMind(secret,combinaison);


			System.out.println(resultat(secret,present,position));
			
			iswin(this.secret,this.combinaison);

			if (iswin == true) {
				System.out.println("Bravo, vous avez gagné en "+Board.tried + " tentative(s) !");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison !");
			}else {
				System.out.println("Vous avez perdu, retour au menu principal");
			}
			Board.tried++;
		} while (Board.tried <= Board.life && this.iswin == false);

	}

	public static  String rulesChallengerPlusMoins() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de "+Board.optM + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+Board.life + " tentatives !");
		str1 +=("\r\nA vous de jouer !");
		return str1;
	}
	
	public static  String rulesChallengerMastermind() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de "+Board.optM + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+Board.life + " tentatives !");
		str1 +=("\r\nA vous de jouer !");
		return str1;
	}
}