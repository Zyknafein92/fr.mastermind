package gameplusmoins;
import start.Board;

public class PC{

	
	static int PC[] = new int[Board.optM];

	private PC() {

		for(int i= 0;i< Board.optM;i++) 
		{
			PC[i] = (int) (Math.random()*10); 
			System.out.println(PC[i]);
		}
	}
	
	
	/**
	 * @return the computeur numbers
	 */
	public static int[] getPC() {
		return PC;
	}

}
