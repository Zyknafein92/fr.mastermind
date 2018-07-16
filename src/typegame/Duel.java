package typegame;

import java.util.Arrays;

import start.Board;
import start.Game;
import tools.BotRoll;
import tools.IObserver;
import tools.Input;

public class Duel extends Game{

	BotRoll rollCombiBot = new BotRoll();
	BotRoll rollSecretBot = new BotRoll();
	protected Integer[] secretBot = new Integer[Board.pawns];
	protected Integer[] combiBot = new Integer[Board.pawns];
	protected Integer[] secretPlayer = new Integer[Board.pawns];
	protected Integer[] combiPlayer = new Integer[Board.pawns];
	protected boolean isloose = false;

	public Duel () {
		combiBot = rollCombiBot.getBotRoll();
		secretBot = rollSecretBot.getBotRoll();
	}


	public void playDuelPlusMoins () {

		System.out.println(rulesDuelPlusMoins());
		System.out.println("Entrez votre combinaison secrète :");
		Input secretplayer = new Input();
		secretPlayer = secretplayer.getInput();

		// 1e tour
		System.out.println("A présent, tentez de découvrir la combinaison de l'ordinateur !");

		System.out.println(secretplayer);
		System.out.println(Arrays.toString(secretBot));
		do {

			System.out.println("---- Tour Joueur ----");
			System.out.println("Faites une proposition de "+Board.pawns + " chiffres !" );

			Input combiplayer = new Input();
			combiPlayer = combiplayer.getInput();
			compareChallenger(secretBot,combiPlayer);

			iswin(secretBot,combiPlayer);

			System.out.println(resultat(combiPlayer,Soluc));

			System.out.println("---- Tour Ordinateur ----");

			System.out.println("Tour" +Board.tried + Arrays.toString(combiBot));

			compareDefenseur(secretPlayer,combiBot);

			System.out.println(resultat(combiBot,Soluc));

			isloose(secretPlayer,combiBot);
			
			if (isloose == true) {
				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");
			} 
			else if (iswin == true) {
				System.out.println("Bravo, vous avez trouvé la combinaison de l'ordinateur en "+Board.tried +" tentative(s) !  Retour au menu principal ");
			}
			else {
				System.out.println("Tour suivant !");
			}
			
			Board.tried++;
			
		} while (Board.tried <= Board.life && iswin == false && isloose == false);
		
		System.out.println("Fin de partie, retour au menu principal !");
		this.notifyObserver();
	}

	public void playDuelMastermind () {
		
		System.out.println(rulesDuelPlusMoins());
		System.out.println("Entrez votre combinaison secrète :");
		Input secretplayer = new Input();
		secretPlayer = secretplayer.getInput();

		System.out.println("A présent, tentez de découvrir la combinaison de l'ordinateur !");

		System.out.println(secretplayer);
		System.out.println(Arrays.toString(secretBot));
		do {
		} while (Board.tried <= Board.life && iswin == false && isloose == false);
		this.notifyObserver();
		
	}

	public static  String rulesDuelPlusMoins() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n------------ Duel ------------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nDans ce mode jeu, vous jouez contre l'ordinateur.");
		str1 +=("\r\nChacun votre tour, vous allez tenter de découvrir la combinaison secrète de l'autre.");
		str1 +=("\r\nElle est composée de "+Board.pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+Board.life + " tentatives !");
		str1 +=("\r\nA vous de jouer ! Veuillez choisir votre combinaison secrète :");
		return str1;
	}

	public boolean isloose (Integer[] secretPlayer, Integer[] combiBot) {
		if (Arrays.equals(secretPlayer,combiBot))
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


	@Override
	public void addObserver(IObserver obs) {
		listObserver.add(obs);
		
	}

	@Override
	public void notifyObserver() {
		for(IObserver obs : listObserver) {
			obs.update();
		}
		
	}
}
