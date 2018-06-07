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

	public void ChallengerPlusMoins() {

		String[] Soluc = new String [Board.optM];

		System.out.println(rulesChallengerPlusMoins());
		System.out.println(Arrays.toString(this.secret));

		do {
			Input player = new Input();
			this.combinaison = player.getInput();

			for (int i = 0; i <Board.optM; i++) {


				if(this.secret[i]< this.combinaison[i] ) {
					Soluc[i] = ""+ "-";
				}else if (this.secret[i] > this.combinaison[i]) {
					Soluc[i] = ""+ "+";
				}else {
					Soluc[i] = ""+ "=";
				}
				iswin();
			}
			Board.tried++;

			System.out.print("Proposition ->" +Arrays.toString(this.combinaison)  + "Résultat : " +Arrays.toString(Soluc));

			if (this.win == true) {
				System.out.println("Bravo, vous avez gagné en "+Board.tried + " tentative(s) !");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison !");
			}else {
				System.out.println("Vous avez perdu, retour au menu principal");
			}

		} while (Board.tried <= Board.life && this.win == false);
	}
	
	StringBuilder sb = new StringBuilder();
	
	String Temp = sb.toString();

	public void ChallengerMastermind() {
	
		System.out.println("Règles");
		System.out.println(Arrays.toString(this.secret));
   
		do {

			int position = 0;
			int present = 0;
			Input player = new Input();
			this.combinaison = player.getInput();

			for (int i = 0; i < secret.length; i++) {
				if(this.combinaison[i] == this.secret[i])
					position++;
		
			}
			
			
			Board.tried++;
			System.out.println("Proposition ->" +Arrays.toString(this.combinaison) + " || Résultat : " + +present + " présent(s), "  + +position + " bonne(s) position(s) ");
			iswin();
			
			if (this.win == true) {
				System.out.println("Bravo, vous avez gagné en "+Board.tried + " tentative(s) !");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison !");
			}else {
				System.out.println("Vous avez perdu, retour au menu principal");
			}
			
		} while (Board.tried <= Board.life && this.win == false);
		
	}

	public void MastermindCompare () {
		

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

}