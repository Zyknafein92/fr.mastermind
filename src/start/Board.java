package start;


import java.util.Scanner;
import tools.IObserver;
import typegame.Challenger;
import typegame.Defenseur;
import typegame.Duel;


/**
 * Board repr�sente le menu du jeu, l'utilisateur choisi le mode de jeu, son type i�i.
 * 
 * @author Zyk
 *
 */

public class Board implements IObserver {

	public static int mod; // sert � d�finir le mod de jeu
	public static int type; // sert � d�finir le type de jeu
	public static int pawns = 4; // sert � d�finir le nombre de chiffres/couleurss a d�couvrir
	public static int tried = 1; // sert � d�finir le nombre de tentative
	public static int life = 15; // // sert � d�finir le nombre de vie du joueur


/**
 * 
 *  Menu est la m�thode qui affiche les choix utilisateurs, et permet de lancer gr�ce au pattern observeur les diff�rents jeux propos�s.
 *  A chaque fin de jeu, l'utilisateur est renvoy� i�i.
 *  
 */
	
	public void Menu() {

		Scanner sc = new Scanner(System.in);


		System.out.println("------------------------------");
		System.out.println("- Bienvenue sur MasterNumber -");
		System.out.println("------------------------------");
		System.out.println("Quel jeu voulez-vous choisir ?");

		// pr�sentation choix du mode de jeu.
		System.out.println("1.Mode +/-");
		System.out.println("2.Mode Mastermind");
		System.out.println("3.Quitter");

		do { // boucle choix du mode de jeu.
			System.out.println("Choisissez votre mode de jeu :");
			mod = sc.nextInt();

			switch (mod) {
			case 1:

				System.out.println("Vous avez choisi le mode + / -");

				break;

			case 2:

				System.out.println(" Vous avez choisi le mode Mastermind");
				break;

			case 3:

				System.out.println("Au revoir, � bient�t !");
				System.exit(0);
				break;


			default:

				System.out.println("Veuillez choisir un mode valide !");
				break;

			}
		} while (mod != 1 && mod != 2 && mod != 3); //fin de la boucle du type de jeu


		//choix du type de jeu
		System.out.println("Veuillez choisir un type de jeu !");
		System.out.println("1.Mode Challenger");
		System.out.println("2.Mode D�fenseur");
		System.out.println("3.Mode Duel");

		type = sc.nextInt();

		do { // boucle du type de jeu
			switch (type) {

			case 1:

				System.out.println("Vous avez choisi le mode Challenger !");
				if (mod == 1) {
					Challenger jeu = new Challenger();
					jeu.addObserver(this);
					jeu.playChallengerPlusMoins();

				}
				else {
					Challenger jeu = new Challenger();
					jeu.addObserver(this);
					jeu.playChallengerMastermind();

				}

				break;

			case 2:

				System.out.println(" Vous avez choisi le mode D�fenseur !");
				if (mod == 1) {
					Defenseur jeu = new Defenseur();
					jeu.addObserver(this);
					jeu.playDefenseurPlusMoins();

				}
				else {
					Defenseur jeu = new Defenseur();
					jeu.addObserver(this);
					jeu.playDefenseurMastermind();

				}
				break;

			case 3:

				System.out.println("Vous avez choisi le mode Duel !");
				if (mod == 1) {
					Duel jeu = new Duel();
					jeu.addObserver(this);
					jeu.playDuelPlusMoins();

				}
				else {
					Duel jeu = new Duel();
					jeu.addObserver(this);
					jeu.playDuelMastermind();

				}
				break;

			default:

				System.out.println("Veuillez choisir un mode valide !");
				break;
			}

		} while (type == 0 || type > 3); // fin de la boucle du type de jeu

	}
	
	/**
	 * @return retourne le type de jeu
	 */
	
	public int getMod() {
		return type;
	}
	
	/**
	 * @return le type de jeu
	 */
	
	public void setMod(int mod) {
		Board.type = mod;
	}

	/**
	 * @return le nombre d'essai r�alis� par le joueur.
	 */
	public static int getTried() {
		return tried;
	}

	/**
	 * @param mise � jour d'essai r�alis� par le joueur.
	 */
	
	public static void setTried(int tried) {
		Board.tried = tried;
	}

	/**
	 * @return le nombre de vie.
	 */
	
	public static int getLife() {
		return life;
	}

	/**
	 * @param mise � jour du nombre d'essai (vie du joueur) autoris�s par les joueurs
	 */
	
	public static void setLife(int life) {
		Board.life = life;
	}

	/**
	 * @return Retourne le nombre de pion effectif pendant le jeu.
	 */
	
	protected static int getNumberOfPawns() {
		return pawns;
	}
	
	/**
	 * @param Cr�e une nouvelle partie
	 */
	
	public void update() {
		Board newGame = new Board();
		newGame.Menu();
	}

}




