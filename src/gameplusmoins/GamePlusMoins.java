package gameplusmoins;

import java.util.Scanner;

public class GamePlusMoins  {

	String NUser;
	static String NPC;
	static int optM;
	Scanner sc = new Scanner(System.in);

	public GamePlusMoins() {
		
		System.out.println("------------------------------");
		System.out.println("---------- Mode +/- ----------");
		System.out.println("------------------------------");
		
    }

	public static String NPC() 
	{
		for(int i= 0;i< optM;i++) 
		{
			NPC = ""+(int) (Math.random()*10);
		}
		return NPC;
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