package tools;
import java.util.Random;

import option.GameOptions;

/**
 * Cette classe permet la g�n�ration d'un jet de d�s al�atoire pour l'ordinateur
 * 
 * @author Zyk
 *
 */

public class BotRoll {
	
	private int pawns = GameOptions.getPanws();
	private int maxnumbers = GameOptions.getMaxnumbers();
	private Integer[] BotRoll = new Integer[pawns];
	
	
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
			BotRoll[i] = (random.nextInt(maxnumbers)) ;
		}
		return BotRoll;
	}

}