package tools;
import java.util.Random;

import option.GameOptions;

/**
 * Cette classe permet la génération d'un jet de dés aléatoire pour l'ordinateur
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
	 * @return Génère un tableau d'Integer aléatoire.
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