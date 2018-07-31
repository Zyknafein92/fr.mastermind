package typegame;



import java.util.ArrayList;
import java.util.Arrays;

import start.Game;
import tools.BotRoll;
import tools.IObserver;
import tools.Input;


/**
 * 
 * Challenger est la classe qui représente le mod Challenger.
 * 
 * @author Zyk
 *
 */

public class Challenger extends Game  {

	private ArrayList<IObserver> listObserver = new ArrayList <IObserver>();
	BotRoll PC = new BotRoll();


	public Challenger () {
		
		super();
		this.secret = PC.getBotRoll();
		
	}

	/**
	 * Méthode de jeu pour le mode challenger +/-.
	 */
	
	public void playChallengerPlusMoins() {

		System.out.println(rulesChallengerPlusMoins());
		System.out.println(Arrays.toString(this.secret));

		do {
			Input player = new Input();
			this.combinaison = player.getInput();

			compareChallenger(secret,combinaison);

			System.out.println(resultat(combinaison,Soluc));

			if (isWin(secret,combinaison) == true) {
				System.out.println("Bravo, vous avez gagné en "+gameCounter + " tentative(s) ! Retour au menu principal\n");
			}else if (gameCounter < maxTry) {
				System.out.println("Mauvaise combinaison !\n");
			}else {
				System.out.println("Vous avez perdu ! Retour au menu principal\n");
			}
			gameCounter++;
			
		} while (gameCounter <= maxTry && isWin(secret,combinaison) == false);
		
		this.notifyObserver();
	}


	/**
	 * Méthode de jeu pour le mode challenger Mastermind
	 */

	public void playChallengerMastermind() {

		System.out.println(rulesChallengerMastermind());
		System.out.println(Arrays.toString(secret));

		do {

			Input player = new Input();
			this.combinaison = player.getInput();	
		
			System.out.println(resultat(combinaison,secret));

			if (isWin(secret,combinaison) == true) {
				System.out.println("Bravo, vous avez gagné en "+gameCounter + " tentative(s) ! Retour au menu principal\n");
			}else if (gameCounter < maxTry) {
				System.out.println("Mauvaise combinaison !\n");
			}else {
				System.out.println("Vous avez perdu ! Retour au menu principal\n");
			}
			gameCounter++;
		} while (gameCounter <= maxTry && isWin(secret,combinaison) == false);
		this.notifyObserver();
	}

	/**
	 * Méthode qui affiche les règles du jeu challenger en mode +/-.
	 * @return String 
	 */
	public static  String rulesChallengerPlusMoins() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de "+pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+maxTry + " tentatives !");
		str1 +=("\r\nA vous de jouer !\\n");
		return str1;
	}

	/**
	 * Méthode qui affiche les règles du jeu challenger en mode Mastermind.
	 * @return String
	 */
	public static  String rulesChallengerMastermind() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de "+pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+maxTry + " tentatives !");
		str1 +=("\r\nA vous de jouer !\n");
		return str1;
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