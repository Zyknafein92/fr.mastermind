package gameplusmoins;

import java.util.Scanner;

import start.Board;



public abstract class GamePlusMoins {

	private int[] secretCombo;
    private int[] playerCombo;
    
    
	public GamePlusMoins() {

		
	}	

	public void generateSecretCombo () {
		
		int sC[] = new int [Board.optM];

		for(int i= 0;i< Board.optM;i++) 
		{
			sC[i] = (int) (Math.random()*10); 

		}
		this.secretCombo = sC;
	}

	public void generateSecretPlayer()
	{
		
			Scanner sc = new Scanner(System.in);
			String userc = "";
			int[] pC = new int[Board.optM];
			do {
				System.out.println("Veuillez entrer " + +Board.optM + " chiffres");
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
						System.out.println(pC[i]);
					}
				}
			} while (userc.length() != Board.optM);
			this.playerCombo = pC;
		  
	}

	public int[] getSecretCombo() {
		return secretCombo;
	}

	public int[] getPlayerCombo() {
		return playerCombo;
	}	
}
	
