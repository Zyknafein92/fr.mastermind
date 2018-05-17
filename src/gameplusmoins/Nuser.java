package gameplusmoins;

import java.util.Scanner;

import start.Board;

public class Nuser {
	
	int [] Player;
	int optM = Board.optM;
	Scanner sc = new Scanner(System.in);
	
	protected Nuser()
	{
		{
			Scanner sc = new Scanner(System.in);
			String userc = "";

			do {
				System.out.println("Veuillez entrer " + +optM + " chiffres");
				userc = sc.nextLine();

				if (userc.length() != optM) 
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
			} while (userc.length() != optM);
			sc.close();
		}
	}
}