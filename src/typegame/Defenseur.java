package typegame;

import java.util.Arrays;

import option.GameOptions;
import start.Game;
import tools.IObserver;

/**
 * 
 * Cette classe représente le mode de jeu Defenseur
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
	 * Méthode de jeu Defenseur pour le mod +/-.
	 * Le joueur choisi son secret, puis l'ordinateur va le comparer tour par tour. En fonction du résultat du comparateur,
	 * il va soit incrémenter, soit décrémenter la valeur des pions.
	 */

	public void playDefenseurPlusMoins() {

		System.out.println(rulesDefenseur());

		this.secret = this.generateInput();

		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		do {

			compareDefenseur(this.secret,this.combinaison);
			
			
			
			System.out.println(resultat(combinaison,soluc));

			if (isWin(secret,combinaison) == true) {
				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
			} else if (gameCounter >= GameOptions.MAX_TRY) {
				System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal\n");
			}
			gameCounter++;

		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secret,combinaison) == false);

		this.notifyObserver();
	}

	/**
	 * Méthode de jeu Defenseur pour le mode MasterMind. 
	 * Après avoir initialisé la combinaisonIA, testColor va prendre chaque tour la valeur de panwsValue et être comparé au secret.
	 * Une variable pawnsToAdd va être incrémentée a chaque fois qu'un nombre de la combinaison est présent dans le secret.
	 * Cette variable va définir le nombre de pions à ajouter par la méthode addToCombinaison qui ajoutera les valeurs une à une de l'indice
	 * 0 a l'indice maximum autorisé. Les autres pions qui n'ont pas été modifiés prendront la valeur actuelle de pawnsValue.
	 * Ensuite, le nombre de pion présent, et à la bonne position sont calculés dans les variables inPresent et inPosition.
	 */

	public void playDefenseurMastermind() {

		System.out.println(rulesDefenseur());

		this.secret = generateInput();

		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		// Initialisation de l'ArrayList à la couleur 0.

		for(int i = 0; i < GameOptions.PAWNS; i++) {
			combinaisonIA.add(pawnsValue);
		}

		do {
			for(int i = 0; i < GameOptions.PAWNS; i++) {
				testColor[i]=pawnsValue;
			}
	
			int pawnsToAdd = countPresent(secret, testColor); 
		//	LOGGER.trace("pawnsToAdd" +pawnsToAdd);
			
			addToCombinaison(combinaisonIA, testColor, pawnsToAdd);

			for(int i = 0; i < GameOptions.PAWNS ; i++) {		
				if( pos <= i) {
					combinaisonIA.set(i, pawnsValue);
				}
			}

			if(pawnsFound == GameOptions.PAWNS) {
				movePawns(combinaisonIA, listCombinaison);
			}

			
			comparePositionB(secret, combinaisonIA);
			
		//	LOGGER.trace("DefenseurMastermind résultat : inPresent :" +isPresent + " inPosition :" +inPosition);
			
			System.out.println(resultat(combinaisonIA,isPresent,inPosition));

			if (pawnsValue < GameOptions.MAX_NUMBERS) {
				pawnsValue++;
			}

			if (inPosition == GameOptions.PAWNS) {
				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
			}else if (gameCounter >= GameOptions.MAX_TRY) {
				System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal\n");
			}

			gameCounter++;

		} while (inPosition < GameOptions.PAWNS && gameCounter <= GameOptions.MAX_TRY);

		this.notifyObserver();
		combinaisonIA.clear();
		listCombinaison.clear();
	}

/**
 * Méthode qui retourne un string contenant les règles du jeu Defenseur.
 * 
 * @return String avec les règles.
 * 
 */

private static  String rulesDefenseur() {
	String str1 = "";

	str1 = ("\r\n------------------------------");
	str1 +=("\r\n--------- Defenseur ----------");
	str1 +=("\r\n------------------------------");
	str1 +=("\r\nVous devez entrer un nombre mystère que l'ordinateur va devoir trouver.");
	str1 +=("\r\nIl est composé de "+GameOptions.PAWNS + " chiffres compris entre 0 et "+GameOptions.MAX_NUMBERS);
	str1 +=("\r\nL'ordinateur à le droit à "+GameOptions.MAX_TRY + " tentative(s) !");
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

