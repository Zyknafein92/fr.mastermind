package start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import tools.IObservable;
import tools.IObserver;

public abstract class Game implements IObservable{

	protected String[] Soluc = new String [Board.optM];
	protected Integer[] combinaison = new Integer[Board.optM];
	protected Integer[] secret = new Integer[Board.optM];
	protected boolean iswin = false;
	protected boolean ispresent = false;
	protected int present;
	protected int position;
	protected ArrayList<IObserver> listObserver = new ArrayList <IObserver>();
	Scanner sc = new Scanner(System.in);

	public void compareChallenger (Integer[]secret, Integer[]combinaison) {

		for (int i = 0; i <Board.optM; i++) {
			if(secret[i]< combinaison[i] ) {
				Soluc[i] = ""+ "-";
			}else if (secret[i] > combinaison[i]) {
				Soluc[i] = ""+ "+";
			}else {
				Soluc[i] = ""+ "=";
			}
		}
	}

	public void compareChallengerMasterMind (Integer[]secret, Integer[]combinaison) {

		present = 0;
		position = 0;

		for(int i = 0; i < secret.length; i++) {
			if(secret[i].equals(combinaison[i]))
				position++;

			for(int y = 0; y < secret.length; y++) {
				if(secret[i].equals(combinaison[y]) && ((secret[i] > combinaison[i]) || (secret[i] < combinaison[i]))) {
					present++;
				}
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

	public void compareDefenseurMastermind(Integer[]secret, Integer[]combinaison) {

		present = 0;
		position = 0;

		compareDefenseur(secret,combinaison);

		for(int i = 0; i < secret.length; i++) {
			if(secret[i].equals(combinaison[i]))
				position++;

			for(int y = 0; y < secret.length; y++) {
				if(secret[i].equals(combinaison[y]) && ((secret[i] > combinaison[i]) || (secret[i] < combinaison[i]))) {
					present++;
				}
			}
		}	
	}


	public boolean iswin (Integer[] secret, Integer[] combinaison) {
		if (Arrays.equals(secret,combinaison))
			iswin = true;
		else
			iswin = false;
		return iswin;		
	}

	public String resultat (Integer[]combinaison, String[] Soluc) {
		String resultat = "";

		resultat +="Proposition ->" +Arrays.toString(combinaison)  + "Résultat : " +Arrays.toString(Soluc);

		return resultat;
	}

	public String resultat(Integer[]combinaison,int present,int position) {
		String resultat = "";

		if (present != 0)	
			resultat +="Proposition ->" +Arrays.toString(combinaison) + " || Résultat : " + +present + " présent(s), "  + +position + " bonne(s) position(s) ";
		else
			resultat +="Proposition ->" +Arrays.toString(combinaison) + " || Résultat : "+position + " bonne(s) position(s) ";
		return resultat;

	}

	/**
	 * @return the combinaison
	 */
	public Integer[] getCombinaison() {
		return combinaison;
	}


	/**
	 * @return the secret
	 */
	public Integer[] getSecret() {
		return secret;
	}


	/**
	 * @param combinaison the combinaison to set
	 */
	public void setCombinaison(Integer[] combinaison) {
		this.combinaison = combinaison;
	}


	/**
	 * @param secret the secret to set
	 */
	public void setSecret(Integer[] secret) {
		this.secret = secret;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}


	/**
	 * @return the present
	 */
	public boolean getPresent() {
		return ispresent;
	}


	/**
	 * @param present the present to set
	 */
	public void setPresent(boolean present) {
		this.ispresent = present;
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


