package start;

import java.util.Arrays;
import java.util.Scanner;

public abstract class Game {

	protected Integer[] combinaison = new Integer[Board.optM];
	protected Integer[] secret = new Integer[Board.optM];
	protected boolean win = false;
    Scanner sc = new Scanner(System.in); 
     
   
	public boolean iswin () {
		if (Arrays.equals(this.secret,this.combinaison))
			win = true;
		else
			win = false;
		return win;		
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
}


