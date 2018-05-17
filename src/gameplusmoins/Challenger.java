package gameplusmoins;

import start.Board;
import gameplusmoins.PC;


public class Challenger {

	
	static String[] Soluc = new String[Board.optM];
    PC pc = new PC();
   
    
   Challenger (PC PC) {
	 this.pc = PC;
	  }
	 
  
	
protected static void compareplusmoins() {
		
do {
		for (int i = 0; i < PC.length; i++) {

			if(PC[i] < Nuser[i]) {
				Soluc[i] = ""+ "-";
			}else if (PC[i] > Nuser[i]) {
				Soluc[i] = ""+ "+";
			}else  {
				Soluc[i] = ""+ "=";
			}	
		}
	while ()
	}
*/
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




