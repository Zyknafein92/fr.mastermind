package typegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import start.Board;
import start.Game;
import tools.BotRoll;
import tools.IObserver;
import tools.Input;

/**
 * 
 * Duel est la classe qui représente le jeu Duel.
 * @author Zyk
 *
 */

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

	/**
	 * 
	 * Méthode de jeu Duel pour le mod +/-.
	 * 
	 */

	public void playDuelPlusMoins () {

		System.out.println(rulesDuelPlusMoins());

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

	/**
	 * 
	 * Méthode de jeu Duel pour le mod Mastermind.
	 * 
	 */

	@SuppressWarnings("unchecked")
	public void playDuelMastermind () {

		System.out.println(rulesDuelPlusMoins());

		Input secretplayer = new Input();
		secretPlayer = secretplayer.getInput();

		do {
			System.out.println("---- Tour Joueur ----");
			System.out.println("Faites une proposition de "+Board.pawns + " chiffres !" );
			System.out.println(Arrays.toString(secretBot));
			Input player = new Input();
			combiPlayer = player.getInput();

			compareInposition(secretBot,combiPlayer);
			comparePresent(secretBot,combiPlayer);

			System.out.println(resultat(combiPlayer,secretBot));

			iswin(secretBot,combiPlayer);

			System.out.println("---- Tour Ordinateur ----");

			if(Board.tried <= 1) {
				for(int y = 0; y < Board.pawns; y++) {
					testColor[y]=color;
					combinaisonIA.add(color);
				}
			}

			for(int i = 0; i < Board.pawns; i++) {
				testColor[i]=color;

				if(check == false) {
					combinaisonIA.set(i, color);
				}
				if( pos <= i ) {
					combinaisonIA.set(i, color);
				}
			}

			int inposition = compareInposition(secretPlayer, testColor);
			isPresent = comparePresent(secretPlayer, testColor);
			int ballcolor = inposition + isPresent;
			
			if(ballcolor > 0) {
				addToCombinaison(combinaisonIA,color,ballcolor);
				System.out.println(Board.tried);
				System.out.println(resultat(combinaisonIA,secretPlayer));
			}
			
			if(ballFound == Board.pawns) {
				moveBallCombinaisonDuelMastermind(combinaisonIA, secretBot, listCombinaison);
				
				for(int i = 0; i < tryCombinaisonBot.size(); i++){
				combinaisonIA.set(i, tryCombinaisonBot.get(i));	
				}
				
				System.out.println(Board.tried);
				System.out.println(resultat(combinaisonIA,secretPlayer));
				listCombinaison.add((ArrayList<Integer>)combinaisonIA.clone());
			}	
			
			Board.tried++;
			color++;


			if(compareInpositionIA(secretPlayer, combinaisonIA) == Board.pawns) {
				Board.tried--;
				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");
			}else if(iswin(secretBot,combinaison) == true){
				System.out.println("Bravo ! Vous avez trouvé la combinaison de l'ordinateur en "+Board.tried +" tentative(s) !  Retour au menu principal");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison ! Essaye encore !");
			}else {
				System.out.println("Egalité, aucun joueur n'a trouvé la combinaison de l'autre | Retour au menu principal");
			}
		} while (compareInpositionIA(secretPlayer, combinaisonIA) < Board.pawns && iswin == false && Board.tried <= Board.life);
		this.notifyObserver();
	}

	/**
	 * 
	 * Méthode qui génère un string contenant les règles du jeu Duel pour le mod +/-.
	 * 
	 * @return String contenant les règles.
	 * 
	 */

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

	/**
	 * Boolean qui permet de tester si l'ordinateur trouve ou non la combinaison du joueur.
	 * 
	 * @param Tableau d'Integer contenant le secret du joueur.
	 * 
	 * @param Tableau d'Integer contenant la combinaison de l'ordinateur.
	 * 
	 * @return la valeur du boolean isloose.
	 * 
	 */

	public boolean isloose (Integer[] secretPlayer, Integer[] combiBot) {
		if (Arrays.equals(secretPlayer,combiBot))
			isloose = true;
		else
			isloose = false;
		return isloose;		
	}

	/**
	 * @param Met à jour le booleen isloose.
	 */
	public void setisLoose(boolean isloose) {
		this.isloose = isloose;
	}


	/**
	 * @return Tableau Integer contenant le secret de l'ordinateur mis à jour.
	 */

	public Integer[] getSecretBot() {
		return secretBot;
	}


	/**
	 * @return Tableau d'Integer contenant le secret de l'ordinateur mis à jour.
	 */

	public Integer[] getCombiBot() {
		return combiBot;
	}


	/**
	 * @return Tableau d'Integer contenant le secret du joueur mis à jour.
	 */
	public Integer[] getSecretPlayer() {
		return secretPlayer;
	}


	/**
	 * @return boolean isloose mis à jour.
	 */
	public boolean isLoose() {
		return isloose;
	}


	/**
	 * @param Met à jour le tableau d'Integer contenant le secret de l'ordinateur.
	 */

	public void setSecretBot(Integer[] secretBot) {
		this.secretBot = secretBot;
	}


	/**
	 * @param Met à jour le tableau d'Integer contenant la combinaison de l'ordinateur.
	 */
	public void setCombiBot(Integer[] combiBot) {
		this.combiBot = combiBot;
	}


	/**
	 * @param Met à jour le tableau d'Integer contenant le secret du joueur.
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
