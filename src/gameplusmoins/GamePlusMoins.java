package gameplusmoins;

import java.util.Scanner;

import start.Board;
import start.Game;



public class GamePlusMoins extends Game {

    Scanner sc = new Scanner(System.in);
    private Integer randomPlayer[] = new Integer [Board.optM];
    private Integer randomComputer[] = new Integer [Board.optM];
    
   public GamePlusMoins () {
	   super();
	   
   }

   
     public Integer[] inputPlayer() {
		
		String userc = "";
		Integer[] pC = new Integer [Board.optM];
		
		do {
			System.out.println("\r\nVeuillez entrer " + +Board.optM + " chiffres");
			userc = sc.nextLine();

			if (userc.length() != Board.optM) 
			{
				System.out.println("Attention, vous n'avez pas sélectionné le bon nombre de chiffre !");
			}  
			else 
			{	
				for ( int i=0; i<userc.length(); i++)
				{
					pC[i]=Integer.parseInt(""+userc.charAt(i));
				}
			}
		} while (userc.length() != Board.optM);
		return this.randomPlayer = pC;
	}	  
		

	public Integer[] randomComputer() {
		Integer sC[] = new Integer [Board.optM];

		for(int i= 0;i< Board.optM;i++) 
		{
			sC[i] = (int) (Math.random()*10); 
		}
		return this.randomComputer = sC;
		
	}

}
	
