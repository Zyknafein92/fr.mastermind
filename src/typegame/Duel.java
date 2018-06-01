package typegame;

import java.util.Arrays;

import gameplusmoins.GamePlusMoins;
import start.Board;

public class Duel {

	private int playerSecret[] = new int [Board.optM];
	private int computerSecret[] = new int [Board.optM];
	private int combinaisonPlayer[] =  new int [Board.optM];
	private int combinaisonComputer[] =   new int [Board.optM];

	private String Soluc[] = new String [Board.optM];

	private boolean win = false;
	private boolean loose = false;

	public Duel () {

		System.out.println(rulesDuelPlusMoins());
		playerSecret = this.generatePlayerCombo();
		computerSecret = this.generateComputerCombo();

	}

	public void duelPlusMoins () {
		
		System.out.println(Arrays.toString(playerSecret)+ "CombiSJ");
		System.out.println(Arrays.toString(computerSecret)+ "CombiS PC");
		System.out.println(Arrays.toString(combinaisonComputer)+ "Combi TryPC");
		// 1e tour
		System.out.println("A présent, tentez de découvrir la combinaison de l'ordinateur !");
		
	do {
		System.out.println("---- Tour Joueur ----");
		System.out.println("Faites une proposition de "+Board.optM + " chiffres !" );
		
		getCombinaisonPlayer();
		
		
		for (int i = 0; i <Board.optM; i++) {


			if(this.getComputerSecret()[i] < this.getCombinaisonPlayer()[i]) {
				Soluc[i] = ""+ "-";
			}else if (getComputerSecret()[i] > this.getCombinaisonPlayer()[i]) {
				Soluc[i] = ""+ "+";
			}else {
				Soluc[i] = ""+ "=";
			}
			
			win();
		}

		System.out.println("Proposition ->" +Arrays.toString(this.getCombinaisonPlayer())  + "Résultat : " +Arrays.toString(Soluc));

		System.out.println("---- Tour Ordinateur ----");
	
		
			
			for(int i = 0; i < combinaisonComputer.length; i++ ) {
				if (this.getPlayerSecret()[i] > combinaisonComputer[i]) {
				combinaisonComputer[i]++;}
				if (this.getPlayerSecret()[i] < combinaisonComputer[i]) {
				combinaisonComputer[i]--;}
				}
			
			
			System.out.println("Tour" +Board.tried +Arrays.toString(combinaisonComputer));
			loose();
			
			if (loose == true) {

				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");

			}else if (Board.tried < Board.life) {

				System.out.println("Mauvaise combinaison ! Essaye encore !");

			}else {

				System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal");
			}
		
		
		Board.tried++;
	} while (Board.tried < Board.life && win == false && loose == false);

	}

	
	private boolean win () {

		if (Arrays.equals(this.getCombinaisonPlayer(),this.computerSecret)) {
			win = true;
		}else {
			win = false;
		}
		return win;

	}
	private boolean loose () {
		if (Arrays.equals(this.combinaisonComputer, this.playerSecret))
			loose = true;
		else
			loose = false;

		return loose;
	}


	public static  String rulesDuelPlusMoins() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n------------ Duel ------------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nDans ce mode jeu, vous jouez contre l'ordinateur.");
		str1 +=("\r\nChacun votre tour, vous allez tenter de découvrir la combinaison secrète de l'autre.");
		str1 +=("\r\nElle est composée de "+Board.optM + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+Board.life + " tentatives !");
		str1 +=("\r\nA vous de jouer ! Veuillez choisir votre combinaison secrète :");
		return str1;
	}

}

