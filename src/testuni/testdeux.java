package testuni;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class testdeux {

	int numberOBalls = 9; // indique le nombre de boule a trouver.
	public Integer[] secret = {5,2,6,2,8,9,4,3,6}; // Le nombre a trouver.
	public Integer[] testcolor  = new Integer[numberOBalls];
	boolean check = true;
	public ArrayList<Integer> combinaison = new ArrayList<Integer>();
	
	int COLORS = 8; // indique le nombre de couleur maximum
	int present = 0;
	int inposition = 0;
	int color = 1;
	int boucle = 0;
	int index = 0;
	int ballFound = 0;
	int pos = 0;


	public void duelMasterMind (Integer[]secret,int COLORS, int numberOBalls) {

		int wPosition = 0;
		int gPosition = 0;

		for(int y = 0; y < numberOBalls; y++) {
			testcolor[y]=color;
			combinaison.add(0);
		}
		boucle++;
		do { 

			for(int i = 0; i < numberOBalls; i++) {
				testcolor[i]=color;
				if(check == true) {
					combinaison.set(i, color);
				}	
			}

			inposition = compareinposition(secret, testcolor, numberOBalls);
			present = comparepresent(secret, testcolor, numberOBalls)- inposition;
			int ballcolor = inposition + present;
			if(ballcolor > 0) {
				addToCombinaison(combinaison,color,ballcolor);
			}
			if(pos == numberOBalls) {
				moveBallCombinaison2(combinaison,secret);
			}
			gPosition = compareinposition(secret, combinaison, numberOBalls);
			wPosition = comparepresent(secret, combinaison, numberOBalls) - gPosition;
			System.out.println(+boucle +" boucle ");
			System.out.println(+gPosition +" position || "+wPosition +" présent");
			System.out.println(combinaison+ " temp");
			//System.out.println(combinaison + " comb");
			boucle++;
			color++;

		} while (compareinposition(secret, combinaison, numberOBalls) < numberOBalls && boucle < 150);
	}

	private int compareinposition(Integer[] secret, ArrayList<Integer> combinaison, int numberOBalls) {

		int inposition = 0;
		for (int i = 0; i < numberOBalls; i++) {
			if (combinaison.get(i).equals(secret[i])) {
				inposition++;
			}
		}
		return inposition;
	}

	private int comparepresent(Integer[] secret, ArrayList<Integer>combinaison, int numberOBalls) {
		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < numberOBalls; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == combinaison.get(j)) {
					ispresent++;
					found = true;
				}
				j++;	
			} while (j < numberOBalls &&!found);
		}
		return ispresent;
	}


	public void addToCombinaison (ArrayList<Integer> temp, int color, int ballcolor) {

		int x = 0;
		while(ballcolor > x) {
			temp.set(pos,color);
			check = false;
			x++;
			ballFound++;
			pos++;
		}
	}

	public ArrayList<Integer> moveBallCombinaison2 (ArrayList<Integer> combinaison, Integer[]secret) {



		for(int i=0; i < numberOBalls; i++) {
			boolean found = false;
			for(int j = 0; j < numberOBalls;j++) {
				if(combinaison.get(i)==secret[i]) {
					found = true;
				} else {
					Collections.swap(combinaison, i, j);	
				}
			}
		}


		return combinaison;
	}


	public int compareinposition(Integer[] secret,Integer[] testcolor, int numberOBalls) {
		int inposition = 0;
		for (int i = 0; i < numberOBalls; i++) {
			if (secret[i] == testcolor[i]) {
				inposition++;
			}
		}
		return inposition;
	}

	public int comparepresent(Integer[] secret, Integer[] testcolor, int numberOBalls) {

		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < numberOBalls; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == testcolor[j]) {
					ispresent++;
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