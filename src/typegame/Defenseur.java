package typegame;

import java.util.Arrays;

import start.Game;
import tools.BotRoll;
import tools.IObserver;
import tools.Input;

/**
 * 
 * Cette classe repr�sente le mode de jeu Defenseur
 * @author Zyk
 *
 */

public class Defenseur extends Game {


	BotRoll PC = new BotRoll();
	
	public Defenseur () {
		super();
		combinaison = PC.generateBotRoll();
		moveI = moveJ = 0;
	}

	/**
	 * 
	 * M�thode de jeu Defenseur pour le mod +/-.
	 * 
	 */

	public void playDefenseurPlusMoins() {

		System.out.println(rulesDefenseur());
		
		Input player = new Input();
		this.secret = player.generateInput();
		
		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		do {

			compareDefenseur(this.secret,this.combinaison);

			System.out.println(resultat(combinaison,Soluc));

			if (isWin(secret,combinaison) == true) {
				System.out.println("Perdu ! L'ordinateur a trouv� votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
			} else if (gameCounter >= maxTry) {
				System.out.println("Vous avez gagn� ! L'ordinateur n'a pas trouv� votre combinaison ! Retour au menu principal\n");
			}
			gameCounter++;

		} while (gameCounter <= maxTry && isWin(secret,combinaison) == false);

		this.notifyObserver();
	}

	/**
	 * 
	 * M�thode de jeu Defenseur pour le mode MasterMind.
	 * 
	 */

	public void playDefenseurMastermind() {

		System.out.println(rulesDefenseur());
		
		Input player = new Input();
		this.secret = player.generateInput();

		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		for(int y = 0; y < pawns; y++) {
			testColor[y]=color;
			combinaisonIA.add(color);
		}

		do { 

			for(int i = 0; i < pawns; i++) {
				testColor[i]=color;

				if(check == false) {
					combinaisonIA.set(i, color);
				}
				if( pos <= i ) {
					combinaisonIA.set(i, color);
				}
			}

			inPosition = compareInposition(secret, testColor);
			isPresent = comparePresent(secret, testColor);
			
			int ballcolor = inPosition + isPresent;

			if(ballcolor > 0) {
				addToCombinaison(combinaisonIA,color,ballcolor);
			}

			if(ballFound != pawns) {
				System.out.println(resultat(combinaisonIA, secret));
			}

			if(ballFound == pawns) {
				movePawns(combinaisonIA, secret, listCombinaison);
			}

			if (color <= maxNumbers) {
				color++;
			}

			if (compareInpositionIA(secret, combinaisonIA) == pawns) {
				System.out.println("Perdu ! L'ordinateur a trouv� votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
			}else if (gameCounter >= maxTry) {
				System.out.println("Vous avez gagn� ! L'ordinateur n'a pas trouv� votre combinaison ! Retour au menu principal\n");
			}
			
			gameCounter++;

		} while (compareInpositionIA(secret, combinaisonIA) < pawns && gameCounter <= maxTry);

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
		str1 +=("\r\nIl est compos� de "+pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nL'ordinateur � le droit � "+maxTry + " tentative(s) !");
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

