package typegame;



import java.util.Arrays;

import gameplusmoins.GamePlusMoins;
import start.Board;


public class Challenger extends GamePlusMoins {

	private String[] Soluc = new String[Board.optM]; // sert à informer le joueur si le nbr est + grand, + petit ou = a valeur[i]
	boolean win = false; // booléen définissant la victoire ou non du joueur.



	public Challenger() {

		this.generateComputerCombo();

		ChallengerPlusMoins();
	}



	public void ChallengerPlusMoins() {
		this.getSecretCombo();


		System.out.println(rulesChallengerPlusMoins());
		System.out.println(Arrays.toString(this.getSecretCombo()));

		do {		
			this.generatePlayerCombo();

			for (int i = 0; i <Board.optM; i++) {


				if(this.getSecretCombo()[i] < this.getPlayerCombo()[i]) {
					Soluc[i] = ""+ "-";
				}else if (this.getSecretCombo()[i] > this.getPlayerCombo()[i]) {
					Soluc[i] = ""+ "+";
				}else {
					Soluc[i] = ""+ "=";
				}
				win();
			}

			Board.tried++;

			System.out.print("Proposition ->" +Arrays.toString(this.getPlayerCombo())  + "Résultat : " +Arrays.toString(Soluc));


			if (win == true) {

				System.out.println("Bravo, vous avez gagné en "+Board.tried + " tentative(s) !");

			}else if (Board.tried < Board.life) {

				System.out.println("Mauvaise combinaison !");

			}else {

				System.out.println("Vous avez perdu, retour au menu principal");
			}

		} while (Board.tried < Board.life && win == false);
	}

	private boolean win () {

		if (Arrays.equals(this.getPlayerCombo(),this.getSecretCombo())) {
			win = true;
		}else {
			win = false;
		}
		return win;
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






