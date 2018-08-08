package typegame;

import java.util.Arrays;

import option.GameOptions;
import start.Game;
import tools.IObserver;

/**
 * 
 * Duel est la classe qui repr�sente le jeu Duel.
 * @author Zyk
 *
 */

public class Duel extends Game{

	protected Integer[] secretBot = new Integer[GameOptions.PAWNS];
	protected Integer[] combiBot = new Integer[GameOptions.PAWNS];
	protected Integer[] secretPlayer = new Integer[GameOptions.PAWNS];
	protected Integer[] combiPlayer = new Integer[GameOptions.PAWNS];
	private boolean isloose = false;


	public Duel () {
		super();
		secretBot = Game.generateBotRoll();
		moveI = moveJ = 0;
	}

	/**
	 * 
	 * M�thode de jeu Duel pour le mod +/-.
	 * 
	 */

	public void playDuelPlusMoins () {

		System.out.println(rulesDuelPlusMoins());

		secretPlayer = this.generateInput();
		combiBot = Game.generateBotRoll();

		System.out.println("A pr�sent, tentez de d�couvrir la combinaison de l'ordinateur !");

		System.out.println(Arrays.toString(secretPlayer));

		do {

			/*	if (dev = 1) {
			System.out.println("Le secret de l'ordinateur est "+Arrays.toString(secretBot));
			}
		*/

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
				System.out.println("\nPerdu ! L'ordinateur a trouv� votre combinaison  en "+gameCounter + " tentative(s) ! Son secret �tait : "+Arrays.toString(secretBot) +"  Retour au menu principal\n");
			} 
			else if (isWin(secretBot,combiPlayer) == true) {
				System.out.println("\nBravo, vous avez trouv� la combinaison de l'ordinateur en "+gameCounter +" tentative(s) !  Retour au menu principal\n");
			}

			gameCounter++;

		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secretBot,combiPlayer) == false && isLoose(secretPlayer,combiBot) == false);

		System.out.println("Fin de partie, retour au menu principal !");
		this.notifyObserver();
	}

	/**
	 * 
	 * M�thode de jeu Duel pour le mod Mastermind.
	 * 
	 */


	public void playDuelMastermind () {

		System.out.println(rulesDuelPlusMoins());

		/* Secret's player */
		secretPlayer = this.generateInput();

		do {
			
		
			System.out.println("Le secret de l'ordinateur est "+Arrays.toString(secretBot));
			
			System.out.println("\n-------------------------");
			System.out.println("------ Tour Joueur ------");
			System.out.println("-------------------------");

			combiPlayer = this.generateInput();	
			
		    comparePositionA(secretBot, combiPlayer);
			System.out.println(resultat(combiPlayer,isPresent,inPosition));

			System.out.println("\n-------------------------");
			System.out.println("---- Tour Ordinateur ----");
			System.out.println("-------------------------\n");

			if(gameCounter < 2) {

				for(int i = 0; i < GameOptions.PAWNS; i++) {
					combinaisonIA.add(pawnsValue);
					testColor[i]= pawnsValue;
				}
			}

			for(int i = 0; i < GameOptions.PAWNS; i++) {
				testColor[i]=pawnsValue;
			}

			int pawnsToAdd = countPresent(secretPlayer, testColor); 

			addToCombinaison(combinaisonIA, testColor, pawnsToAdd);

			for(int i = 0; i < GameOptions.PAWNS ; i++) {		
				if( pos <= i) {
					combinaisonIA.set(i, pawnsValue);
				}
			}

			if(pawnsFound == GameOptions.PAWNS) {
				movePawns(combinaisonIA, listCombinaison);
			}
			
			comparePositionB(secretPlayer, combinaisonIA);
			System.out.println(resultat(combinaisonIA,isPresent,inPosition));

			if (pawnsValue < GameOptions.MAX_NUMBERS) {
				pawnsValue++;
			}

			//TODO : A revoir
			if (inPosition == GameOptions.PAWNS) {

				System.out.println("\nPerdu ! L'ordinateur a trouv� votre combinaison  en "+gameCounter + " tentative(s) ! Son secret �tait : "+Arrays.toString(secretBot) +"  Retour au menu principal\n");

			} else if (isWin(secretBot,combiPlayer) == true) {

				System.out.println("\nBravo, vous avez trouv� la combinaison de l'ordinateur en "+gameCounter +" tentative(s) !  Retour au menu principal\n");
			}

			gameCounter++;

		} while (gameCounter <= GameOptions.MAX_TRY && isWin(secretBot,combiPlayer) == false && inPosition != GameOptions.PAWNS);

		this.notifyObserver();
		listCombinaison.clear();
	}

	/**
	 * 
	 * M�thode qui g�n�re un string contenant les r�gles du jeu Duel pour le mod +/-.
	 * 
	 * @return String contenant les r�gles.
	 * 
	 */

	public static  String rulesDuelPlusMoins() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n------------ Duel ------------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nDans ce mode jeu, vous jouez contre l'ordinateur.");
		str1 +=("\r\nChacun votre tour, vous allez tenter de d�couvrir la combinaison secr�te de l'autre.");
		str1 +=("\r\nElle est compos�e de " + GameOptions.PAWNS + " chiffres compris entre 0 et "+GameOptions.MAX_NUMBERS);
		str1 +=("\r\nVous avez le droit a " + GameOptions.MAX_TRY + " tentatives !");
		str1 +=("\r\nA vous de jouer ! Veuillez choisir votre combinaison secr�te :");
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
	 * @param Met � jour le booleen isloose.
	 */
	public void setisLoose(boolean isloose) {
		this.isloose = isloose;
	}


	/**
	 * @return Tableau Integer contenant le secret de l'ordinateur mis � jour.
	 */

	public Integer[] getSecretBot() {
		return secretBot;
	}


	/**
	 * @return Tableau d'Integer contenant le secret de l'ordinateur mis � jour.
	 */

	public Integer[] getCombiBot() {
		return combiBot;
	}


	/**
	 * @return Tableau d'Integer contenant le secret du joueur mis � jour.
	 */
	public Integer[] getSecretPlayer() {
		return secretPlayer;
	}


	/**
	 * @return boolean isloose mis � jour.
	 */
	public boolean isLoose() {
		return isloose;
	}


	/**
	 * @param Met � jour le tableau d'Integer contenant le secret de l'ordinateur.
	 */

	public void setSecretBot(Integer[] secretBot) {
		this.secretBot = secretBot;
	}


	/**
	 * @param Met � jour le tableau d'Integer contenant la combinaison de l'ordinateur.
	 */
	public void setCombiBot(Integer[] combiBot) {
		this.combiBot = combiBot;
	}


	/**
	 * @param Met � jour le tableau d'Integer contenant le secret du joueur.
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

	/**
	 * @return the combiPlayer
	 */
	public Integer[] getCombiPlayer() {
		return combiPlayer;
	}

	/**
	 * @param combiPlayer the combiPlayer to set
	 */
	public void setCombiPlayer(Integer[] combiPlayer) {
		this.combiPlayer = combiPlayer;
	}
}
