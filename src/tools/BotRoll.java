package tools;
import java.util.Arrays;
import java.util.Random;

import option.GameOptions;
import start.Board;

/**
 * Cette classe permet la g�n�ration d'un jet de d�s al�atoire pour l'ordinateur
 * 
 * @author Zyk
 *
 */

public class BotRoll {
	
	private int pawns = GameOptions.getPanws();
	private Integer[] BotRoll = new Integer [pawns];
	
	
	public BotRoll () {
	
		BotRoll = generateBotRoll();
        
	}

	/**
	 * @return G�n�re un tableau d'Integer al�atoire.
	 */
	
	public Integer[] generateBotRoll() {
		Random random = new Random();
		for(int i= 0; i < pawns; i++) 
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
	 * @param G�n�re un tableau selon la m�thode generateBotRoll
	 */
	
	public void setBotRoll(int[] botRoll) {
		BotRoll = generateBotRoll();
	}
	

	public String toString() {
		return Arrays.toString(BotRoll);
	}
}