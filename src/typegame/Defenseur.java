package typegame;

import java.util.Arrays;

import option.GameOptions;
import start.Game;
import tools.IObserver;

/**
 * 
 * Cette classe repr�sente le mode de jeu Defenseur
 * @author Zyk
 *
 */


public class Defenseur extends Game {

	public Defenseur () {
		super();
		combinaison = Game.generateBotRoll();
		moveI = moveJ = 0;
	}

	/**
	 * 
	 * M�thode de jeu Defenseur pour le mod +/-.
	 * 
	 */

	public void playDefenseurPlusMoins() {

		System.out.println(rulesDefenseur());

		this.secret = this.generateInput();

		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		do {

			compareDefenseur(this.secret,this.combinaison);

			System.out.println(resultat(combinaison,soluc));

			if (isWin(secret,combinaison) == true) {
				System.out.println("Perdu ! L'ordinateur a trouv� votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
			} else if (gameCounter >= GameOptions.MAX_TRY) {
				System.out.println("Vous avez gagn� ! L'ordinateur n'a pas trouv� votre combinaison ! Retour au menu principal\n");
			}
			gameCounter++;

		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secret,combinaison) == false);

		this.notifyObserver();
	}

	/**
	 * 
	 * M�thode de jeu Defenseur pour le mode MasterMind.
	 * @param panwsToAdd 
	 * 
	 */

	public void playDefenseurMastermind() {

		System.out.println(rulesDefenseur());

		this.secret = generateInput();

		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		// Initialisation de l'ArrayList � la couleur 0.
		for(int i = 0; i < GameOptions.PAWNS; i++) {
			combinaisonIA.add(pawnsValue);
			testColor[i]= pawnsValue;
		}

		do {
			for(int i = 0; i < GameOptions.PAWNS; i++) {
				testColor[i]=pawnsValue;
			}
			int pawnsToAdd = countPresent(secret, testColor); 

			addToCombinaison(combinaisonIA, testColor, pawnsToAdd);

			for(int i = 0; i < GameOptions.PAWNS ; i++) {		
				if( pos <= i) {
					combinaisonIA.set(i, pawnsValue);
				}
			}

			if(pawnsFound == GameOptions.PAWNS) {
				movePawns(combinaisonIA, listCombinaison);
			}

		    countPosition(secret, combinaisonIA);
			//System.out.println(+isPresent +"a"+ +inPosition);
			System.out.println(resultat(combinaisonIA,isPresent,inPosition));

			if (pawnsValue < GameOptions.MAX_NUMBERS) {
				pawnsValue++;
			}

			if (inPosition == GameOptions.PAWNS) {
				System.out.println("Perdu ! L'ordinateur a trouv� votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
			}else if (gameCounter >= GameOptions.MAX_TRY) {
				System.out.println("Vous avez gagn� ! L'ordinateur n'a pas trouv� votre combinaison ! Retour au menu principal\n");
			}

			gameCounter++;

		} while (compareInpositionIA(combinaisonIA,secret) < GameOptions.PAWNS && gameCounter <= GameOptions.MAX_TRY);

		this.notifyObserver();
		combinaisonIA.clear();
		listCombinaison.clear();
	}

	/**
	 * M�thode qui retourne un string contenant les r�gles du jeu Defenseur.
	 * 
	 * @return String avec les r�gles.
	 * 
	 */

	private static  String rulesDefenseur() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Defenseur ----------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez entrer un nombre myst�re que l'ordinateur va devoir trouver.");
		str1 +=("\r\nIl est compos� de "+GameOptions.PAWNS + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nL'ordinateur � le droit � "+GameOptions.MAX_TRY + " tentative(s) !");
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

