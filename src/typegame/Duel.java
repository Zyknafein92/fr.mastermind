package typegame;

import java.util.Arrays;

import option.GameOptions;
import start.Game;
import tools.IObserver;

/**
 * 
 * Duel est la classe qui représente le jeu Duel.
 * @author Zyk
 *
 */

public class Duel extends Game{

	protected Integer[] secretBot = new Integer[GameOptions.PAWNS];
	protected Integer[] combiBot = new Integer[GameOptions.PAWNS];
	protected Integer[] secretPlayer = new Integer[GameOptions.PAWNS];
	protected Integer[] combiPlayer = new Integer[GameOptions.PAWNS];
	protected boolean isloose = false;


	public Duel () {
		super();
		secretBot = Game.generateBotRoll();
		moveI = moveJ = 0;
	}

	/**
	 * 
	 * Méthode de jeu Duel pour le mod +/-.
	 * 
	 */

	public void playDuelPlusMoins () {

		System.out.println(rulesDuelPlusMoins());

		secretPlayer = this.generateInput();
		combiBot = Game.generateBotRoll();

		System.out.println("A présent, tentez de découvrir la combinaison de l'ordinateur !");

		System.out.println(secretPlayer);
		System.out.println(Arrays.toString(secretBot));
		do {

			System.out.println("\n-------------------------");
			System.out.println("------ Tour Joueur ------");
			System.out.println("-------------------------");


			combiPlayer = this.generateInput();

			compareChallenger(secretBot,combiPlayer);

			System.out.println(resultat(combiPlayer,soluc));

			System.out.println("\n-------------------------");
			System.out.println("---- Tour Ordinateur ----");
			System.out.println("-------------------------\n");

			compareDefenseur(secretPlayer,combiBot);

			System.out.println(resultat(combiBot,soluc));



			if (isLoose(secretPlayer,combiBot) == true) {
				System.out.println("\nPerdu ! L'ordinateur a trouvé votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
			} 
			else if (isWin(secretBot,combiPlayer) == true) {
				System.out.println("\nBravo, vous avez trouvé la combinaison de l'ordinateur en "+gameCounter +" tentative(s) !  Retour au menu principal\n");
			}

			gameCounter++;

		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secretBot,combiPlayer) == false && isLoose(secretPlayer,combiBot) == false);

		System.out.println("Fin de partie, retour au menu principal !");
		this.notifyObserver();
	}

	/**
	 * 
	 * Méthode de jeu Duel pour le mod Mastermind.
	 * 
	 */


	public void playDuelMastermind () {

		System.out.println(rulesDuelPlusMoins());

		/* Secret's player */
		secretPlayer = this.generateInput();
		System.out.println(Arrays.toString(secretBot));
		do {

			System.out.println("\n-------------------------");
			System.out.println("------ Tour Joueur ------");
			System.out.println("-------------------------");

			combiPlayer = this.generateInput();	

			System.out.println(resultat(combiPlayer,secretBot));

			System.out.println("\n-------------------------");
			System.out.println("---- Tour Ordinateur ----");
			System.out.println("-------------------------\n");

			if(gameCounter < 2) {
				for(int y = 0; y < GameOptions.PAWNS; y++) {
					testColor[y]=color;
					combinaisonIA.add(color);
				}
			}	

			for(int i = 0; i < GameOptions.PAWNS; i++) {
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
			}

			if(ballFound != GameOptions.PAWNS) {
				System.out.println(resultat(combinaisonIA, secretPlayer));
			}

			if(ballFound == GameOptions.PAWNS) {
				movePawns(combinaisonIA, secretPlayer, listCombinaison);
			}	
			
			
			if (color <= GameOptions.MAX_NUMBERS) {
				color++;
			}
			
		//	if (compareInpositionIA(secretPlayer, combinaisonIA) == GameOptions.PAWNS) {
		//		System.out.println("\nPerdu ! L'ordinateur a trouvé votre combinaison  en "+gameCounter + " tentative(s) !  Retour au menu principal\n");
		//	} 
			else if (isWin(secretBot,combiPlayer) == true) {
				System.out.println("\nBravo, vous avez trouvé la combinaison de l'ordinateur en "+gameCounter +" tentative(s) !  Retour au menu principal\n");
			}
			
			gameCounter++;
			
		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secretBot,combiPlayer) == false && compareInposition(secretPlayer, combinaisonIA) != GameOptions.PAWNS);

		this.notifyObserver();
		listCombinaison.clear();
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
		str1 +=("\r\nElle est composée de " + GameOptions.PAWNS + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nVous avez le droit a " + GameOptions.MAX_TRY + " tentatives !");
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

	public boolean isLoose (Integer[] secretPlayer, Integer[] combiBot) {
		return (Arrays.equals(secretPlayer,combiBot));		
	}

	//	public boolean isLoose (Integer[] secretPlayer, ArrayList<Integer> combinaisonIA) {
	//		return combinaisonIA.equals(secretPlayer);
	//	}

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
