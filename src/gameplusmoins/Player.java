package gameplusmoins;

import java.util.Scanner;

import start.Board;

public class Player {
	
	
	Scanner sc = new Scanner(System.in);
	
	public Player()
	{
		{
			Scanner sc = new Scanner(System.in);
			String userc = "";

			do {
				System.out.println("Veuillez entrer " + +Board.optM + " chiffres");
				userc = sc.nextLine();

				if (userc.length() != Board.optM) 
				{
					System.out.println("Attention, vous n'avez pas sélectionné le bon nombre de chiffre !");
				}  
				else 
				{
					int[] Player = new int[userc.length()];
					for ( int i=0; i<userc.length(); i++)
					{
						Player[i]=Integer.parseInt(""+userc.charAt(i));
						System.out.println(Player[i]);
					}
				}
			} while (userc.length() != Board.optM);
			sc.close();
		}
	}	
}