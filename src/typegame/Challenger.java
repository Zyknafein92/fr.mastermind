package typegame;



import java.util.Arrays;

import option.BotRoll;
import option.PlayerInput;
import start.Board;
import start.Game;


public class Challenger extends Game {




public Challenger () {
	
secret = new BotRoll();
	
}

	public void ChallengerPlusMoins() {

		String[] Soluc = new String [Board.optM];

		System.out.println(rulesChallengerPlusMoins());
		System.out.println(secret);

		do {		
			new PlayerInput();
		

			for (int i = 0; i <Board.optM; i++) {


				if(this.secret[i] < this.combinaison[i]) {
					Soluc[i] = ""+ "-";
				}else if (this.secret[i] > this.combinaison[i]) {
					Soluc[i] = ""+ "+";
				}else {
					Soluc[i] = ""+ "=";
				}
				win(this.secret, this.combinaison);
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

		} while (Board.tried < Board.life && this.win == false);
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