package typegame;



import java.util.ArrayList;
import java.util.Arrays;

import option.GameOptions;
import start.Game;
import tools.IObserver;


/**
 * 
 * Challenger est la classe qui représente le mod Challenger.
 * 
 * @author Zyk
 *
 */

public class Challenger extends Game  {

	private ArrayList<IObserver> listObserver = new ArrayList <IObserver>();

	public Challenger () {

		super();
		this.secret = Game.generateBotRoll();

	}

	/**
	 * Méthode de jeu pour le mode challenger +/-.
	 */

	public void playChallengerPlusMoins() {

		System.out.println(rulesChallengerPlusMoins());
		
		do {
			/*	if (dev = 1) {
			System.out.println("Le secret de l'ordinateur est "+Arrays.toString(secret));
			}
		*/
			this.combinaison = this.generateInput();

			compareChallenger(secret,combinaison);

			System.out.println(resultat(combinaison,soluc));

			if (isWin(secret,combinaison) == true) {
				System.out.println("Bravo, vous avez gagné en "+gameCounter + " tentative(s) ! Retour au menu principal\n");
			}else if (gameCounter < GameOptions.MAX_TRY) {
				System.out.println("Mauvaise combinaison !\n");
			}else {
				System.out.println("Vous avez perdu ! Le secret a découvrir était: "+Arrays.toString(secret)+ " Retour au menu principal\n");
			}
			gameCounter++;

		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secret,combinaison) == false);

		this.notifyObserver();
	}


	/**
	 * Méthode de jeu pour le mode challenger Mastermind
	 */

	public void playChallengerMastermind() {

		System.out.println(rulesChallengerMastermind());
	
		do {
			
		//	if (dev = 1) {
			System.out.println("Le secret de l'ordinateur est "+Arrays.toString(secret));
		//	}
		
			this.combinaison = this.generateInput();
			
			
			comparePositionA(secret, combinaison);
			System.out.println(Arrays.toString(resultat));
			System.out.println(resultat(combinaison, isPresent, inPosition));

			if (isWin(secret,combinaison) == true) {
				System.out.println("Bravo, vous avez gagné en "+gameCounter + " tentative(s) ! Retour au menu principal\n");
			}else if (gameCounter < GameOptions.MAX_TRY) {
				System.out.println("Mauvaise combinaison !\n");
			}else {
				System.out.println("Vous avez perdu ! Le secret a découvrir était: "+Arrays.toString(secret)+ " Retour au menu principal\n");
			}

			gameCounter++;

		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secret,combinaison) == false);

		this.notifyObserver();

	}

	/**
	 * Méthode qui affiche les règles du jeu challenger en mode +/-.
	 * @return String contenant les règles.
	 */
	public static  String rulesChallengerPlusMoins() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de " + GameOptions.PAWNS + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a " + GameOptions.MAX_TRY + " tentatives !");
		str1 +=("\r\nA vous de jouer !\n");
		return str1;
	}

	/**
	 * Méthode qui affiche les règles du jeu challenger en mode Mastermind.
	 * @return String contenant les règles.
	 */
	
	public static  String rulesChallengerMastermind() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de " + GameOptions.PAWNS + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a " + GameOptions.MAX_TRY + " tentatives !");
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