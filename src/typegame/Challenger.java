package typegame;

import gameplusmoins.GamePlusMoins;

import start.Board;


public class Challenger extends GamePlusMoins {

	private String[] Soluc = new String[Board.optM];

	public Challenger() {
		
		this.generateSecretCombo();
	
	}



	public void comparePlusMoins() {

		
		
		for (int i = 0; i <Board.optM; i++) {


			if(this.getSecretCombo()[i] < this.getPlayerCombo()[i]) {
				Soluc[i] = ""+ "-";
			}else if (this.getSecretCombo()[i] > this.getPlayerCombo()[i]) {
				Soluc[i] = ""+ "+";
			}else  {
				Soluc[i] = ""+ "=";
			}
			System.out.print("Proposition ->" + this.getPlayerCombo()[i] + "Résultat : " + Soluc[i]);
		}

	}

	public static  String rulesChallenger() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de "+Board.optM + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a (life) tentatives !");
		str1 +=("\r\nA vous de jouer !");
		return str1;
	}

}




