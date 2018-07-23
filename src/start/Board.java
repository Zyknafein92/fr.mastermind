package start;


import java.util.Scanner;
import tools.IObserver;
import typegame.Challenger;
import typegame.Defenseur;
import typegame.Duel;


/**
 * Board représente le menu du jeu, l'utilisateur choisi le mode de jeu, son type içi.
 * 
 * @author Zyk
 *
 */

public class Board implements IObserver {

	public static int mod; // sert à définir le mod de jeu
	public static int type; // sert à définir le type de jeu
	public static int pawns = 4; // sert à définir le nombre de chiffres/couleurss a découvrir
	public static int tried = 1; // sert à définir le nombre de tentative
	public static int life = 15; // // sert à définir le nombre de vie du joueur


/**
 * 
 *  Menu est la méthode qui affiche les choix utilisateurs, et permet de lancer grâce au pattern observeur les différents jeux proposés.
 *  A chaque fin de jeu, l'utilisateur est renvoyé içi.
 *  
 */
	
	public void Menu() {

		Scanner sc = new Scanner(System.in);


		System.out.println("------------------------------");
		System.out.println("- Bienvenue sur MasterNumber -");
		System.out.println("------------------------------");
		System.out.println("Quel jeu voulez-vous choisir ?");

		// présentation choix du mode de jeu.
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

				System.out.println("Au revoir, à bientôt !");
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
		System.out.println("2.Mode Défenseur");
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

				System.out.println(" Vous avez choisi le mode Défenseur !");
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
	 * @return le nombre d'essai réalisé par le joueur.
	 */
	public static int getTried() {
		return tried;
	}

	/**
	 * @param mise à jour d'essai réalisé par le joueur.
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
	 * @param mise à jour du nombre d'essai (vie du joueur) autorisés par les joueurs
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
	 * @param Crée une nouvelle partie
	 */
	
	public void update() {
		Board newGame = new Board();
		newGame.Menu();
	}

}




