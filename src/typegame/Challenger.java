package typegame;

import gameplusmoins.GamePlusMoins;

import start.Board;


public class Challenger extends GamePlusMoins {

	static String[] Soluc = new String[Board.optM];

	public Challenger(int[] PC) {
		super(PC);	
		compareplusmoins();
	}



	public static  void compareplusmoins() {

		
		
		for (int i = 0; i <Board.optM; i++) {


			if(PC()[i] < Player[i]) {
				Soluc[i] = ""+ "-";
			}else if (PC()[i] > Player[i]) {
				Soluc[i] = ""+ "+";
			}else  {
				Soluc[i] = ""+ "=";
			}
			System.out.print("Proposition ->" + Player[i] + "Résultat : " + Soluc[i]);
		}

	}

	public static  String ruleschallenger() {
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




