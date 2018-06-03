package typegame;

import java.util.Arrays;

import option.BotRoll;
import start.Board;
import start.Game;

public class Duel extends Game {


	private static final String[] Soluc = new String [Board.optM];
	
	private Integer[] combinaisonBOT = new Integer[Board.optM];
	private Integer[] secretPlayer = new Integer[Board.optM];
	private Integer[] secretBOT = new Integer[Board.optM];
	BotRoll combinaisonbot = new BotRoll();
	
	public Duel () {

		this.secretBOT = this.generateBotRoll();
		
	}
	public void duelPlusMoins () {
	
		System.out.println(rulesDuelPlusMoins());
		System.out.println("Entrez votre combinaison secrète :");
		this.secretPlayer = this.generateInputPlayer();
	
		// 1e tour
		System.out.println("A présent, tentez de découvrir la combinaison de l'ordinateur !");
		
		System.out.println(Arrays.toString(secretPlayer));
		System.out.println(Arrays.toString(secretBOT));
		do {
			System.out.println("---- Tour Joueur ----");
			System.out.println("Faites une proposition de "+Board.optM + " chiffres !" );
			this.combinaison = this.generateInputPlayer();


			for (int i = 0; i <Board.optM; i++) {
				if(this.combinaison[i] < this.secretBOT[i]) {
					Soluc[i] = ""+ "-";
				}else if (this.combinaison[i] > this.secretBOT[i]) {
					Soluc[i] = ""+ "+";
				}else {
					Soluc[i] = ""+ "=";
				}
				this.secret = this.secretBOT;
				win(combinaison, secret);
			}
			System.out.println("Proposition ->" +Arrays.toString(this.secretBOT)  + "Résultat : " +Arrays.toString(Soluc));

			System.out.println("---- Tour Ordinateur ----");
			
			this.combinaisonBOT = this.combinaison;


			for(int i = 0; i < secretPlayer.length; i++ ) {
				if (this.combinaison[i] > this.secretPlayer[i]) {
					this.combinaison[i]++;}
				if (this.combinaison[i] < this.secretPlayer[i]) {
					this.combinaison[i]--;}
				
			}
		
            this.secret = this.secretPlayer;
			System.out.println("Tour" +Board.tried +Arrays.toString(combinaison));

			win(combinaison,secret);

			if (win == true) {

				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");

			}else if (Board.tried < Board.life) {

				System.out.println("Mauvaise combinaison ! Essaye encore !");

			}else {

				System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal");
			}


			Board.tried++;
		} while (Board.tried < Board.life && win == false && win == false);

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
	/**
	 * @return the combinaison
	 */
	public Integer[] getCombinaison() {
		return combinaison;
	}
	/**
	 * @return the combinaisonBOT
	 */
	public Integer[] getCombinaisonBOT() {
		return combinaisonBOT;
	}
	/**
	 * @return the secretPlayer
	 */
	public Integer[] getSecretPlayer() {
		return secretPlayer;
	}
	/**
	 * @return the secretBOT
	 */
	public Integer[] getSecretBOT() {
		return secretBOT;
	}
	/**
	 * @param combinaison the combinaison to set
	 */
	public void setCombinaison(Integer[] combinaison) {
		this.combinaison = combinaison;
	}
	/**
	 * @param combinaisonBOT the combinaisonBOT to set
	 */
	public void setCombinaisonBOT(Integer[] combinaisonBOT) {
		this.combinaisonBOT = combinaisonBOT;
	}
	/**
	 * @param secretPlayer the secretPlayer to set
	 */
	public void setSecretPlayer(Integer[] secretPlayer) {
		this.secretPlayer = secretPlayer;
	}
	/**
	 * @param secretBOT the secretBOT to set
	 */
	public void setSecretBOT(Integer[] secretBOT) {
		this.secretBOT = secretBOT;
	}

}

