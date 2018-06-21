package testuni;

import java.util.Arrays;

public class tryduel extends TestUni {

	Integer[] secret = {8,7,3,8};
	Integer[] combinaison = new Integer[4];
	int present = 0;
	int inposition= 0;
	int colors = 1;
	int boucle = 0;

	public void jeu (Integer[]combinaison, int colors, int boucle) {

		boolean isinposition = false;
		boolean ispresent = false;
		Integer[] temp = new Integer[4];
		int p =0;

		for(int x =0;x <4;x++) {
			temp[x] = colors;
			combinaison[x] = temp[x];
		}

		do {

			if(boucle > 0 && !ispresent) colors++;

			for(int i = 0; i <4; i++) {
				isinposition = (secret[i]==combinaison[i]);
				for(int y = 0; y < 4; y++) {
					ispresent =(secret[i]==combinaison[y] && (secret[i]!=combinaison[i]));
					if(isinposition) {
					combinaison[i] = temp[i];
					}
					if(!isinposition && !ispresent) {
						temp[i]= colors; 
					}
					if(!isinposition && ispresent) {
						p = colors;
						temp[i] = p;
					}
					if(ispresent && (!isinposition)) {
						temp[i]=p;
					}
				}
				combinaison[i] = temp[i];
			}
 
			comparepresent(secret, combinaison);  //Comparaison des résultats
			compareinpositon(secret, combinaison);

			System.out.println(inposition+" positions" + "|" + present+" presents"); // Affichage console
			System.out.println(Arrays.toString(combinaison)+" combi");
			//System.out.println(Arrays.toString(temp)+" temp");

			boucle++;

		} while ((secret != combinaison) && (boucle < 10));

	}

	public int comparepresent (Integer[]secret,Integer[]combinaison) {
		present = 0;

		for(int i = 0; i < 4; i++) {
			for(int y = 0; y < 4; y++) {
				if(secret[i]==combinaison[y] && secret[i]!=combinaison[i])
					present++;
			}
		}
		return present; 	
	}

	public int compareinpositon(Integer[]secret,Integer[]combinaison) {
		inposition = 0;
		for(int i = 0; i < 4; i++) {
			if (secret[i]==combinaison[i]) {
				inposition++;
			}
		}
		return inposition;
	}

	public void tryduel() {
		jeu(combinaison,colors,boucle);
	}
}