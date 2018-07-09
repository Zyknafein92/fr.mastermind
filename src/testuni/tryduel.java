package testuni;

import java.util.Arrays;

public class tryduel extends TestUni {

	public int[] secret = {5,4,3,7};

	int COLORS = 8;
	int numberOBalls = 4;
	public void jeu(int[] secret, int COLORS, int numberOBalls) {

		int[] temp = new int[numberOBalls]; //Tableau contenant la combinaison
		int[] combinaison = new int[numberOBalls]; // Tableau contenant les couleurs
		int color = 1; // Couleur de d�part
		int inposition = 0; // pion pr�sent � la bonne position
		int ispresent = 0;  // pion pr�sent mais mal plac�
		int ballFound = 0; // Nombre de boules de couleurs dont on a trouv� la bonne position
		int boucle = 0;    // nombres de tentatives
		int position = 0;  // indique la position de la boule.

		/*
		 * Boucle qui se r�p�te jusqu'� la r�solution du jeu.
		 */
		while (compareinposition(secret, temp, numberOBalls) <= numberOBalls){


			for (int i = 0; i < numberOBalls; i++) {  // Cr�ation d'une combinaison pour d�terminer ou non pr�sence d'une couleur
				if (combinaison[i] == 0)
					temp[i] = color;
				else
					temp[i] = combinaison[i];
			}
		
			boucle++;
			
			inposition = compareinposition(secret, temp, numberOBalls); // D�termine les pions bien plac�s
			ispresent = comparepresent(secret, temp, numberOBalls) - compareinposition(secret, temp, numberOBalls); // d�termine les pions pr�sent
		
			int Balls = inposition - ballFound; // nombre de boule

			if (Balls >= 1 && color <= (COLORS + 1)) {
				
				for (int x = 1; x <= Balls; x++) {
					ispresent = 1;
					position = 0;

					while (ispresent > 0) {
						while ((position < numberOBalls) && combinaison[position] != 0) // Nouvelle combinaison qui cherche position de la couleur actuelle.
							position++;

						for (int i = 0; i < numberOBalls; i++) { 
							if (combinaison[i] == 0) // Si on ne connait pas la couleur de la case courante.
							{
								if (i != position) {// Si la case n'est pas une case test�, color++
									temp[i] = color + 1;
								}else {
									temp[i] = color;
								}
							} else
								temp[i] = combinaison[i];
						}

						boucle++;
						inposition =compareinposition(secret, temp, numberOBalls);
						ispresent = comparepresent(secret, temp, numberOBalls)- inposition;
						
						position++;

					}

					// A la sortie de la boucle, on a la position de la boule de
					// couleur -> pos - 1
					// On ajoute donc cette boule � la combinaison contenant les
					// boules Trouv�es
					combinaison[position - 1] = color;

					// on incr�mente le nombre de boule trouv�es
					ballFound++;
					System.out.println(+boucle);
					System.out.println(Arrays.toString(temp));
					System.out.println(Arrays.toString(combinaison));
					System.out.println(" --> "+inposition+" biens plac�es, "+ispresent+" mal plac�es.");
				}
			}
			color++;
		}
	}
	public int compareinposition(int[] secret, int[] temp, int numberOBalls) {
		int inposition = 0;
		for (int i = 0; i < numberOBalls; i++) {
			if (secret[i] == temp[i]) {
				inposition++;
			}
		}
		return inposition;
	}


	public int comparepresent(int[] secret, int[] temp, int numberOBalls) {

		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < numberOBalls; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == temp[j]) {
					ispresent++;
					temp[j] = 0;
					found = true;
				}
				j++;
			} while (j < numberOBalls &&!found);
		}
		return ispresent;
	}
	

	public void tryduel() {
		jeu(secret,COLORS,numberOBalls);
	}
}