package typegame;

import java.util.Arrays;

import start.Board;
import start.Game;
import tools.BotRoll;
import tools.Input;

public class Duel extends Game {


	private static final String[] Soluc = new String [Board.optM];


	BotRoll rollCombiBot = new BotRoll();
	BotRoll rollSecretBot = new BotRoll();
	private Integer[] secretBot = new Integer[Board.optM];
	private Integer[] combiBot = new Integer[Board.optM];
	private Integer[] secretPlayer = new Integer[Board.optM];
	private boolean isloose = false;

	public Duel () {
		combiBot = rollCombiBot.getBotRoll();
		secretBot = rollSecretBot.getBotRoll();
	}


	public void duelPlusMoins () {

		System.out.println(rulesDuelPlusMoins());
		System.out.println("Entrez votre combinaison secrète :");
		Input secretplayer = new Input();
		this.secret = secretplayer.getInput();

		// 1e tour
		System.out.println("A présent, tentez de découvrir la combinaison de l'ordinateur !");

		System.out.println(secretplayer);
		System.out.println(Arrays.toString(secretBot));
		do {
			System.out.println("---- Tour Joueur ----");
			System.out.println("Faites une proposition de "+Board.optM + " chiffres !" );
			Input combiplayer = new Input();
			this.combinaison = combiplayer.getInput();
			this.secret = this.secretBot;

			for (int i = 0; i <Board.optM; i++) {
				if(this.combinaison[i] < this.secret[i]) {
					Soluc[i] = ""+ "-";
				}else if (this.combinaison[i] > this.secret[i]) {
					Soluc[i] = ""+ "+";
				}else {
					Soluc[i] = ""+ "=";
				}

				iswin();
			}
			System.out.println("Proposition ->" +Arrays.toString(this.combinaison)  + "Résultat : " +Arrays.toString(Soluc));

			System.out.println("---- Tour Ordinateur ----");
			this.secret = this.secretPlayer;
			this.combinaison = this.combiBot;
			System.out.println("Tour" +Board.tried + Arrays.toString(this.combinaison));

			for(int i = 0; i < secret.length; i++ ) {
				if (this.secret[i] > combinaison[i]) {
					combinaison[i]++;}
				if (this.secret[i] < combinaison[i]) {
					combinaison[i]--;}

				this.combiBot = this.combinaison;
			}

			System.out.println("Tour" +Board.tried + Arrays.toString(this.combiBot));

			isloose();

			if (isloose == true) {

				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");

			}else if (Board.tried < Board.life) {

				System.out.println("Mauvaise combinaison ! Essaye encore !");

			}else {

				System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal");
			}


			Board.tried++;
		} while (Board.tried <= Board.life || win == true || isloose == true);

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

	public boolean isloose () {
		if (Arrays.equals(this.secret,this.combinaison))
			isloose = true;
		else
			isloose = false;
		return isloose;		
	}

	/**
	 * @param isloose the isloose to set
	 */
	public void setIsloose(boolean isloose) {
		this.isloose = isloose;
	}


	/**
	 * @return the secretBot
	 */
	public Integer[] getSecretBot() {
		return secretBot;
	}


	/**
	 * @return the combiBot
	 */
	public Integer[] getCombiBot() {
		return combiBot;
	}


	/**
	 * @return the secretPlayer
	 */
	public Integer[] getSecretPlayer() {
		return secretPlayer;
	}


	/**
	 * @return the isloose
	 */
	public boolean isIsloose() {
		return isloose;
	}


	/**
	 * @param secretBot the secretBot to set
	 */
	public void setSecretBot(Integer[] secretBot) {
		this.secretBot = secretBot;
	}


	/**
	 * @param combiBot the combiBot to set
	 */
	public void setCombiBot(Integer[] combiBot) {
		this.combiBot = combiBot;
	}


	/**
	 * @param secretPlayer the secretPlayer to set
	 */
	public void setSecretPlayer(Integer[] secretPlayer) {
		this.secretPlayer = secretPlayer;
	}
}
