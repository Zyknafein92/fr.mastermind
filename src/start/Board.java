package start;


import java.util.Scanner;

import gameplusmoins.GamePlusMoins;


public class Board {
	int mod;
	int type;

	public Board() {



		Scanner sc = new Scanner(System.in);


		System.out.println("------------------------------");
		System.out.println("- Bienvenue sur MasterNumber -");
		System.out.println("------------------------------");
		System.out.println("Quel jeu voulez-vous choisir ?");

		// choix du mode de jeu.
		System.out.println("1.Mode +/-");
		System.out.println("2.Mode Mastermind");


		do { // boucle choix du mode de jeu.
			System.out.println("Choisissez votre mode de jeu :");
			type = sc.nextInt();

			switch (type) {
			case 1:

				System.out.println("Vous avez choisi le mode + / -");	
				break;

			case 2:

				System.out.println(" Vous avez choisi le mode Mastermind");
				break;

			default:

				System.out.println("Veuillez choisir un mode valide !");
				break;

			}
		} while (type != 1 && type != 2);

		System.out.println("Veuillez choisir un type de jeu !");
		System.out.println("1.Mode Challenger");
		System.out.println("2.Mode Défenseur");
		System.out.println("3.Mode Duel");
		System.out.println("4.Menu principal");

		mod = sc.nextInt();

		do {
			switch (mod) {

			case 1:

				System.out.println("Vous avez choisi le mode Challenger !");
				new GamePlusMoins();
				break;

			case 2:

				System.out.println(" Vous avez choisi le mode Défenseur !");
				break;

			case 3:

				System.out.println("Vous avez choisi le mode Duel !");
				break;

			case 4:

				System.out.println("Au revoir, à bientôt !");
				System.exit(0);
				break;

			default:

				System.out.println("Veuillez choisir un mode valide !");
				break;
			}
			sc.close();
		} while (mod == 0 || mod > 4);
	
	}
}



