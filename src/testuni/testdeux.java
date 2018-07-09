package testuni;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class testdeux {

	int numberOBalls = 4; // indique le nombre de boule a trouver.
	public Integer[] secret = {2,4,6,2}; // Le nombre a trouver.
	public Integer[] testcolor  = new Integer[numberOBalls];
	boolean check = true;
	public LinkedList<Integer> temp = new LinkedList<Integer>();
	public LinkedList<Integer> combinaison = new LinkedList<Integer>();
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
			combinaison.add(1);
			temp.add(0);
		}
       boucle++;
		do { 

			for(int i = 0; i < numberOBalls; i++) {
				testcolor[i]=color;
				if(check == true) {
					temp.set(i, color);
				}	
			}

			inposition = compareinposition(secret, testcolor, numberOBalls);
			present = comparepresent(secret, testcolor, numberOBalls)- inposition;
			int ballcolor = inposition + present;

			if(ballcolor > 0) {
				addToCombinaison(temp,color,ballcolor);
			}
			moveBallCombinaison2(temp,secret);
			gPosition = compareinposition(secret, combinaison, numberOBalls);
			wPosition = comparepresent(secret, temp, numberOBalls) - gPosition;
			System.out.println(+boucle +" boucle ");
			System.out.println(+gPosition +" position || "+wPosition +" présent");
			System.out.println(temp+ " temp");
			System.out.println(combinaison + " comb");
			boucle++;
			color++;

		} while (compareinposition(secret, combinaison, numberOBalls) < numberOBalls && boucle < 15);
	}

	private int compareinposition(Integer[] secret, LinkedList<Integer> temp, int numberOBalls) {

		int inposition = 0;
		for (int i = 0; i < numberOBalls; i++) {
			if (temp.get(i).equals(secret[i])) {
				inposition++;
			}
		}
		return inposition;
	}

	private int comparepresent(Integer[] secret, LinkedList<Integer> temp, int numberOBalls) {
		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < numberOBalls; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == temp.get(j)) {
					ispresent++;
					found = true;
				}
				j++;	
			} while (j < numberOBalls &&!found);
		}
		return ispresent;
	}


	public void addToCombinaison (LinkedList<Integer> temp, int color, int ballcolor) {

		int x = 0;
		while(ballcolor > x) {
			temp.set(pos,color);
			check = false;
			x++;
			ballFound++;
		}
	}

	public LinkedList<Integer> moveBallCombinaison2 (LinkedList<Integer> temp,Integer[]secret) {

		int last = numberOBalls;
		boolean found = false;
		 
		Collections.rotate(temp, 1);
		for(int i = 0; i < numberOBalls; i++) {
			if(temp.get(index)==secret[i]) {
				combinaison.set(i,secret[i]);
				found = true;
			}else{
				if(found = false) {
					combinaison.set(i,color++);
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