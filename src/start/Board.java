package start;


import java.util.InputMismatchException;
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

	private int mod; // sert à définir le mod de jeu
	private int type; // sert à définir le type de jeu


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


		System.out.println("1.Mode +/-");
		System.out.println("2.Mode Mastermind");
		System.out.println("3.Quitter");

		do { 

			System.out.println("Choisissez votre mode de jeu :");

			try {
				
				mod = sc.nextInt();
				
			}catch(InputMismatchException e) {
				
				System.out.println(" Veuillez entrer un chiffre  !");
				sc.next();
			}

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

		} while (mod != 1 && mod != 2 && mod != 3); 


		System.out.println("Veuillez choisir un type de jeu !");

		do {

			System.out.println("1.Mode Challenger");
			System.out.println("2.Mode Défenseur");
			System.out.println("3.Mode Duel");


			try {
				
				type = sc.nextInt();
				
			}catch(InputMismatchException e) {
				
				System.out.println(" Veuillez entrer un chiffre  !");
				sc.next();
			}



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
				System.out.println("Veuillez choisir un type de jeu valide !");
				break;
			}

		} while (type != 1 && type != 2 && type != 3); 

	}

	public void update() {
		Board newGame = new Board();
		newGame.Menu();
	}

}




