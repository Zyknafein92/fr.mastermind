package tools;
import java.util.Arrays;
import java.util.Random;

import start.Board;

/**
 * Cette classe permet la génération d'un jet de dés aléatoire pour l'ordinateur
 * 
 * @author Zyk
 *
 */

public class BotRoll {

	private Integer[] BotRoll = new Integer [Board.pawns];
	
	
	public BotRoll () {
	
		BotRoll = generateBotRoll();

	}

	/**
	 * @return Génère un tableau d'Integer aléatoire.
	 */
	
	public Integer[] generateBotRoll() {
		Random random = new Random();
		for(int i= 0;i< Board.pawns;i++) 
		{
			BotRoll[i] = (random.nextInt(9)) ;
		}
		return BotRoll;
	}

	/**
	 * @return le tableau Integer BotRoll
	 */
	
	public Integer[] getBotRoll() {
		return BotRoll;
	}

	/**
	 * @param Génère un tableau selon la méthode generateBotRoll
	 */
	
	public void setBotRoll(int[] botRoll) {
		BotRoll = generateBotRoll();
	}
	

	public String toString() {
		return Arrays.toString(BotRoll);
	}
}