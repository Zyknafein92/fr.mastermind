package testuni;

import java.util.Arrays;

public class testdeux {

	int numberOBalls = 4; // indique le nombre de boule a trouver.
	public Integer[] secret = {1,4,1,2}; // Le nombre a trouver.
	public Integer[] temp  = new Integer[numberOBalls];
	public Integer[] combinaison = new Integer[numberOBalls];


	int COLORS = 8; // indique le nombre de couleur maximum
	int present = 0;
	int inposition = 0;
	int color = 1;
	int boucle = 0;
	int index = 0;
	int ballFound = 0;


	public void duelMasterMind (Integer[]secret,int COLORS, int numberOBalls) {

		int nextColor = color+1;
		int searchColor = 0;

		for(int i = 0; i < numberOBalls; i++) {
			temp[i]=color;
			combinaison[i]=0;
		}
		
		boucle++;
		inposition =compareinposition(secret, temp, numberOBalls);
		present = comparepresent(secret, temp, numberOBalls)- inposition;
		System.out.println(boucle);
		System.out.println(+inposition +" position || "+present +" présent");
		System.out.println(Arrays.toString(combinaison)+ " comb");
		System.out.println(Arrays.toString(temp)+ " temp");
		
		do {

			for (int i = 0; i < numberOBalls; i++) {
				int position = 0;
				
				if(present > 0) 
				if(combinaison[i]!=0) {
					temp[i]= combinaison[i];
				}else {
					temp[i]=color;
					if(present > 0){
						searchColor = color;
						for(int x = 0; x < numberOBalls; x++) {
							if(temp[position] != secret[i]){
								temp[position] = searchColor;
					            position++;
							}else if(temp[position]==secret[i]) {
									combinaison[position]=temp[position];
								}
							}
						}
					}
				boucle++;
				color++;
				inposition =compareinposition(secret, temp, numberOBalls);
				present = comparepresent(secret, temp, numberOBalls)- inposition;
				System.out.println(boucle);
				System.out.println(+inposition +" position || "+present +" présent");
				System.out.println(Arrays.toString(combinaison)+ " comb");
				System.out.println(Arrays.toString(temp)+ " temp");
				}
	
		} while (compareinposition(secret, temp, numberOBalls) <= numberOBalls && boucle < 5);
	}


	public int compareinposition(Integer[] secret,Integer[] temp, int numberOBalls) {
		int inposition = 0;
		for (int i = 0; i < numberOBalls; i++) {
			if (secret[i] == temp[i]) {
				inposition++;
			}
		}
		return inposition;
	}

	public int comparepresent(Integer[] secret, Integer[] temp, int numberOBalls) {

		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < numberOBalls; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == temp[j]) {
					ispresent++;
					temp[j] = color;
					found = true;
				}
				j++;
			} while (j < numberOBalls &&!found);
		}
		return ispresent;
	}

	public void tryduel () {
		duelMasterMind(secret,COLORS,numberOBalls);
	}
}