package gameplusmoins;

import java.util.Scanner;

import start.Board;
import start.Game;



public abstract class GamePlusMoins extends Game{

	private int[] computerCombo;
    private int[] playerCombo;
    

	public void generateComputerCombo () {
		
		int sC[] = new int [Board.optM];

		for(int i= 0;i< Board.optM;i++) 
		{
			sC[i] = (int) (Math.random()*10); 
		}
		this.computerCombo = sC;
	}

	public void generatePlayerCombo()
	{
		
			Scanner sc = new Scanner(System.in);
			String userc = "";
			int[] pC = new int[Board.optM];
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
			this.playerCombo = pC;
		  
	}

	public int[] getSecretCombo() {
		return computerCombo;
	}

	public int[] getPlayerCombo() {
		return playerCombo;
	}	
}
	
