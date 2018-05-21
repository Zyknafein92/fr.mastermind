package start;


import java.util.Scanner;

import gamemastermind.GameMastermind;
import gameplusmoins.GamePlusMoins;
import typegame.Challenger;


public class Board {

	public static int mod;
	public static int type;
	public static int optM = 4;
	private GamePlusMoins modgame;
	public static int tried = 0;
    public static int life = 5;

			public void doGame() {



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
				System.out.println("4.Quitter");

				mod = sc.nextInt();

				do {
					switch (mod) {

					case 1:

						System.out.println("Vous avez choisi le mode Challenger !");

						if (type == 1)
							modgame = new Challenger(); 
						else
							new GameMastermind();
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

				} while (mod == 0 || mod > 4);

			}



			public int getMod() {
				return mod;
			}

			public void setMod(int mod) {
				Board.mod = mod;
			}

			protected int getOptM() {
				return optM;
			}

			protected void setOptM(int optM) {
				Board.optM = optM;
			}



			/**
			 * @return the tried
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
}



