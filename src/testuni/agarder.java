	int Player[];
    int PC[];
	int optM = 4;


	public void setPlayer() 

	{
		Scanner sc = new Scanner(System.in);
		String userc;

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
		;
	}
	protected  int[] PC () {

		PC = new int[optM];

		for(int i= 0;i< optM;i++) 
		{
			PC[i] = (int) (Math.random()*10); 
			System.out.println(PC[i]);
		}
		return PC;
	}package testuni;

import java.util.Scanner;

public class agarder {

}
