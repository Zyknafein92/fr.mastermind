package gameplusmoins;
import start.Board;

public class PC{

	
 int PC[] = new int[Board.optM];

	protected PC() {

		for(int i= 0;i< Board.optM;i++) 
		{
			PC[i] = (int) (Math.random()*10); 

		}
	}
	
	
	/**
	 * @return 
	 * @return the computeur numbers
	 */
	public  int[] getPC() {
		return PC;
	}

}
