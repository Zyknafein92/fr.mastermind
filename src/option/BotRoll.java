package option;
import java.util.Arrays;
import java.util.Random;

import start.Board;

public class BotRoll {

	private Integer[] BotRoll = new Integer [Board.optM];
	
	public BotRoll () {
	
		generateBotRoll();

	}

	public Integer[] generateBotRoll() {
		Random random = new Random();
		for(int i= 0;i< Board.optM;i++) 
		{
			BotRoll[i] = (random.nextInt(9) +1) ;
		}
		return BotRoll;
	
	}

	/**
	 * @return the botRoll
	 */
	public Integer[] getBotRoll() {
		return BotRoll;
	}

	/**
	 * @param botRoll the botRoll to set
	 */
	public void setBotRoll(Integer[] botRoll) {
		BotRoll = generateBotRoll();
	}
	public String toString() {
		return Arrays.toString(BotRoll);
	}
}