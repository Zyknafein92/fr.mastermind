package gameplusmoins;

import java.util.Scanner;

public class GamePlusMoins  {

	String NUser;
	String NPC;
	int optM = 4;
	Scanner sc = new Scanner(System.in);

	public GamePlusMoins() {

		System.out.println("------------------------------");
		System.out.println("---------- Mode +/- ----------");
		System.out.println("------------------------------");

		NPC();

		System.out.println(NPC);
	}

	private void NPC() {
		
		for(int i= 0;i< optM;i++) 
		{
			NPC = (""+(int) (Math.random()*10));
		}
		
		
	}


	public void NUser()
	{
		do {
			System.out.println("Veuillez entrer " + +optM + " chiffres");
			NUser = sc.nextLine();

			if (NUser.length() != optM) 
			{
				System.out.println("Attention, vous n'avez pas sélectionné le bon nombre de chiffre !");
			}  
		} while (NUser.length() != optM);
		sc.close();
	}
}