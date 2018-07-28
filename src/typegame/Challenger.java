package typegame;



import java.util.ArrayList;
import java.util.Arrays;

import start.Board;
import start.Game;
import tools.BotRoll;
import tools.IObserver;
import tools.Input;


/**
 * Challenger est la classe qui représente le mod Challenger.
 * 
 * @author Zyk
 *
 */
public class Challenger extends Game  {

	private ArrayList<IObserver> listObserver = new ArrayList <IObserver>();
	BotRoll PC = new BotRoll();


	public Challenger () {
		this.secret = PC.getBotRoll();
	}

	public void playChallengerPlusMoins() {

		System.out.println(rulesChallengerPlusMoins());
		System.out.println(Arrays.toString(this.secret));

		do {
			Input player = new Input();
			this.combinaison = player.getInput();

			compareChallenger(this.secret,this.combinaison);

			iswin(this.secret,this.combinaison);

			System.out.print(resultat(combinaison,Soluc));

			if (iswin == true) {
				System.out.println("Bravo, vous avez gagné en "+Board.tried + " tentative(s) !");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison !");
			}else {
				System.out.println("Vous avez perdu, retour au menu principal");
			}
			Board.tried++;
		} while (Board.tried <= Board.life && iswin == false);
		
		this.notifyObserver();
	}


	public void playChallengerMastermind() {

		System.out.println(rulesChallengerMastermind());
		System.out.println(Arrays.toString(this.secret));

		do {

			Input player = new Input();
			this.combinaison = player.getInput();	
			compareInposition(secret,combinaison);
            comparePresent(secret,combinaison);

			System.out.println(resultat(combinaison,secret));

			iswin(this.secret,this.combinaison);

			if (iswin == true) {
				System.out.println("Bravo, vous avez gagné en "+Board.tried + " tentative(s) !");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison !");
			}else {
				System.out.println("Vous avez perdu, retour au menu principal");
			}
			Board.tried++;
		} while (Board.tried <= Board.life && iswin == false);
		this.notifyObserver();
	}

	public static  String rulesChallengerPlusMoins() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de "+Board.pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+Board.life + " tentatives !");
		str1 +=("\r\nA vous de jouer !");
		return str1;
	}

	public static  String rulesChallengerMastermind() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Challenger ---------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez trouver la combinaison mystère de votre adversaire !");
		str1 +=("\r\nElle est composée de "+Board.pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a "+Board.life + " tentatives !");
		str1 +=("\r\nA vous de jouer !");
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