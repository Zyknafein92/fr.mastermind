package start;


import java.util.Scanner;

import tools.IObserver;
import typegame.Challenger;
import typegame.Defenseur;
import typegame.Duel;


public class Board implements IObserver {

	public static int mod; // sert à définir le mod de jeu
	public static int type; // sert à définir le type de jeu
	public static int pawns = 4; // sert à définir le nombre de chiffres/couleurss a découvrir
	public static int tried = 1; // sert à définir le nombre de tentative
	public static int life = 5; // // sert à définir le nombre de vie du joueur



	public void Menu() {

		Scanner sc = new Scanner(System.in);


		System.out.println("------------------------------");
		System.out.println("- Bienvenue sur MasterNumber -");
		System.out.println("------------------------------");
		System.out.println("Quel jeu voulez-vous choisir ?");

		// choix du mode de jeu.
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

	public int getMod() {
		return type;
	}

	public void setMod(int mod) {
		Board.type = mod;
	}

	/**
	 * @return return try var
	 */
	public static int getTried() {
		return tried;
	}

	/**
	 * @param tried the tried to set
	 */
	public static void setTried(int tried) {
		Board.tried = tried;
	}

	/**
	 * @return the life
	 */
	public static int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public static void setLife(int life) {
		Board.life = life;
	}

	public void update() {
		Board newGame = new Board();
		newGame.Menu();
	}

	/**
	 * @return the numberOfPawns
	 */
	protected static int getNumberOfPawns() {
		return pawns;
	}

}




