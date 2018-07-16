package start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import tools.IObservable;
import tools.IObserver;

public abstract class Game implements IObservable{

	protected String[] Soluc = new String [Board.pawns];
	protected Integer[] combinaison = new Integer[Board.pawns];
	protected ArrayList<Integer> combinaisonIA = new ArrayList<Integer>();
	protected Integer[] secret = new Integer[Board.pawns];
	protected Integer[] testcolor = new Integer[Board.pawns];
	protected boolean iswin = false;
	protected int ispresent = 0;
	protected int inposition = 0;
	protected ArrayList<IObserver> listObserver = new ArrayList <IObserver>();
	Scanner sc = new Scanner(System.in);
	protected int pos = 0;
	protected int color = 1;
	protected boolean check = true;
	protected int ballFound = 0;
	
	                                    /* Méthode Challenger */

	public void compareChallenger (Integer[]secret, Integer[]combinaison) {

		for (int i = 0; i <Board.pawns; i++) {
			if(secret[i]< combinaison[i] ) {
				Soluc[i] = ""+ "-";
			}else if (secret[i] > combinaison[i]) {
				Soluc[i] = ""+ "+";
			}else {
				Soluc[i] = ""+ "=";
			}
		}
	}
	
	public void compareDefenseur (Integer[]secret, Integer[]combinaison) {

		for(int i = 0; i < combinaison.length; i++ ) {
			if (secret[i] > combinaison[i]) {
				combinaison[i]++; }
			if (secret[i] < combinaison[i]) {
				combinaison[i]--;}
			if(secret[i]< combinaison[i] ) {
				Soluc[i] = ""+ "-";
			}else if (secret[i] > combinaison[i]) {
				Soluc[i] = ""+ "+";
			}else {
				Soluc[i] = ""+ "=";
			}	
		}
	}
	
	                                   /* Méthode MasterMind */
	
	protected int compareinposition(Integer[] secret, Integer[] combinaison) {

		int inposition = 0;
		for (int i = 0; i < Board.pawns; i++) {
			if (combinaison[i].equals(secret[i])) {
				inposition++;
			}
		}
		return inposition;
	}
	protected int comparepresent(Integer[] secret, Integer[] combinaison) {
		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < Board.pawns; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == combinaison[j]) {
					ispresent++;
					found = true;
				}
				j++;	
			} while (j < Board.pawns &&!found);
		}
		return ispresent - compareinposition(secret,combinaison);
	}
	
	protected int compareinpositionIA(Integer[] secret,ArrayList<Integer> combinaison) {

		int inposition = 0;
		for (int i = 0; i < Board.pawns; i++) {
			if (combinaison.get(i).equals(secret[i])) {
				inposition++;
			}
		}
		return inposition;
	}

	protected int comparepresentIA(Integer[] secret, ArrayList<Integer>combinaison) {
		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < Board.pawns; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == combinaison.get(j)) {
					ispresent++;
					found = true;
				}
				j++;	
			} while (j < Board.pawns &&!found);
		}
		return ispresent - compareinpositionIA(secret, combinaison);
	}
	
	protected void addToCombinaison (ArrayList<Integer> combinaison, int color, int ballcolor) {

		int x = 0;
		while(ballcolor > x) {
			combinaisonIA.set(pos,color);
			check = false;
			x++;
			ballFound++;
			pos++;
		}
	}

	protected ArrayList<Integer> moveBallCombinaison (ArrayList<Integer> combinaison, Integer[]secret) {

		for(int i=0; i < Board.pawns; i++) {
			boolean found = false;
			for(int j = 0; j < Board.pawns;j++) {
				if(combinaison.get(i)==secret[i]) {
					found = true;
				} else {
					Collections.swap(combinaisonIA, i, j);	
				}
			}
		}

		return combinaison;
	}

	protected boolean iswin (Integer[] secret, Integer[] combinaison) {
		if (Arrays.equals(secret,combinaison))
			iswin = true;
		else
			iswin = false;
		return iswin;		
	}

	protected String resultat (Integer[]combinaison, String[] Soluc) {
		String resultat = "";

		resultat +="Proposition ->" +Arrays.toString(combinaison)  + "Résultat : " +Arrays.toString(Soluc);

		return resultat;
	}

	protected String resultat(Integer[]combinaison) {
		String resultat = "";

		if (comparepresent(secret,combinaison) != 0)	
			resultat +="Proposition ->" +Arrays.toString(combinaison) + " || Résultat : " +comparepresent(secret,combinaison) + " présent(s), "  + compareinposition(secret,combinaison) + " bonne(s) position(s) ";
		else
			resultat +="Proposition ->" +Arrays.toString(combinaison) + " || Résultat : " +compareinposition(secret,combinaison) + " bonne(s) position(s) ";
		return resultat;
	}
	
	protected String resultat(ArrayList<Integer>combinaisonIA) {
			String resultat = "";

			if (comparepresentIA(secret,combinaisonIA) != 0)	
				resultat +="Proposition ->" +combinaisonIA + " || Résultat : " +comparepresentIA(secret,combinaisonIA) + " présent(s), "  + compareinpositionIA(secret,combinaisonIA) + " bonne(s) position(s) ";
			else
				resultat +="Proposition ->" +combinaisonIA + " || Résultat : " +compareinpositionIA(secret,combinaisonIA) + " bonne(s) position(s) ";
			return resultat;
	}
	
	/**
	 * @return the soluc
	 */
	protected String[] getSoluc() {
		return Soluc;
	}

	/**
	 * @return the combinaison
	 */
	protected Integer[] getCombinaison() {
		return combinaison;
	}

	/**
	 * @return the combinaisonIA
	 */
	protected ArrayList<Integer> getCombinaisonIA() {
		return combinaisonIA;
	}

	/**
	 * @return the secret
	 */
	protected Integer[] getSecret() {
		return secret;
	}

	/**
	 * @return the testcolor
	 */
	protected Integer[] getTestcolor() {
		return testcolor;
	}

	/**
	 * @return the iswin
	 */
	protected boolean isIswin() {
		return iswin;
	}

	/**
	 * @return the ispresent
	 */
	protected int getIspresent() {
		return ispresent;
	}

	/**
	 * @return the inposition
	 */
	protected int getInposition() {
		return inposition;
	}

	/**
	 * @return the listObserver
	 */
	protected ArrayList<IObserver> getListObserver() {
		return listObserver;
	}

	/**
	 * @return the pos
	 */
	protected int getPos() {
		return pos;
	}

	/**
	 * @return the color
	 */
	protected int getColor() {
		return color;
	}

	/**
	 * @return the check
	 */
	protected boolean isCheck() {
		return check;
	}

	/**
	 * @return the ballFound
	 */
	protected int getBallFound() {
		return ballFound;
	}

	/**
	 * @param soluc the soluc to set
	 */
	protected void setSoluc(String[] soluc) {
		Soluc = soluc;
	}

	/**
	 * @param combinaison the combinaison to set
	 */
	protected void setCombinaison(Integer[] combinaison) {
		this.combinaison = combinaison;
	}

	/**
	 * @param combinaisonIA the combinaisonIA to set
	 */
	protected void setCombinaisonIA(ArrayList<Integer> combinaisonIA) {
		this.combinaisonIA = combinaisonIA;
	}

	/**
	 * @param secret the secret to set
	 */
	protected void setSecret(Integer[] secret) {
		this.secret = secret;
	}

	/**
	 * @param testcolor the testcolor to set
	 */
	protected void setTestcolor(Integer[] testcolor) {
		this.testcolor = testcolor;
	}

	/**
	 * @param iswin the iswin to set
	 */
	protected void setIswin(boolean iswin) {
		this.iswin = iswin;
	}

	/**
	 * @param ispresent the ispresent to set
	 */
	protected void setIspresent(int ispresent) {
		this.ispresent = ispresent;
	}

	/**
	 * @param inposition the inposition to set
	 */
	protected void setInposition(int inposition) {
		this.inposition = inposition;
	}

	/**
	 * @param listObserver the listObserver to set
	 */
	protected void setListObserver(ArrayList<IObserver> listObserver) {
		this.listObserver = listObserver;
	}

	/**
	 * @param pos the pos to set
	 */
	protected void setPos(int pos) {
		this.pos = pos;
	}

	/**
	 * @param color the color to set
	 */
	protected void setColor(int color) {
		this.color = color;
	}

	/**
	 * @param check the check to set
	 */
	protected void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * @param ballFound the ballFound to set
	 */
	protected void setBallFound(int ballFound) {
		this.ballFound = ballFound;
	}
	
	@Override
	public void addObserver(IObserver obs) {
		listObserver.add(obs);
		
	}

	@Override
	public void notifyObserver() {
		for(IObserver obs : listObserver) {
			obs.update();
		}
		
	}

}


