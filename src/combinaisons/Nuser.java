package combinaisons;

import java.util.Scanner;

public class Nuser {
	
	String Nuser = "";
	int optM = 4;
	Scanner sc = new Scanner(System.in);
	
	protected void Nuser()
	{
		do {
			System.out.println("Veuillez entrer " + +optM + " chiffres");
			Nuser = sc.nextLine();

			if (Nuser.length() != optM) 
			{
				System.out.println("Attention, vous n'avez pas sélectionné le bon nombre de chiffre !");
			}
			
		} while (Nuser.length() != optM);
		sc.close();
	}

}