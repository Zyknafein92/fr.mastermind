package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import option.GameOptions;

/**
 * 
 * Cette classe sert � saisir et contr�ler les saisis claviers de l'utilisateur gr�ce � la m�thode Scanner.
 * @author Zyk
 *
 */

public class Input {

	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	
	private int PANWS = GameOptions.getPanws();
	private int MAXNUMBERS = GameOptions.getMaxnumbers();
	private Integer[] Input = new Integer[PANWS];



	/**
	 * M�thode qui permet de contr�ler la saisie de l'utilisateur et de la m�moriser pour une utilisation ult�rieure.
	 * 
	 * @return
	 * Tableau d'Integer
	 */
	/*	public Integer[] generateInput() {

		String userc = "";

		do {

			System.out.println("\r\nVeuillez entrer " + +pawns + " chiffres");
			userc = sc.nextLine();

			if (userc.length() != pawns) 
			{
				System.out.println("Attention, vous n'avez pas s�lectionn� le bon nombre de chiffre !");
			}  
			else 
			{	
				for ( int i=0; i<userc.length(); i++)
				{
					Input[i]=Integer.parseInt(""+userc.charAt(i));
				}
			}
		} while (userc.length() != pawns);

		return Input;
	}
	 */

	public Integer[] generateInput() {

		String userc = null;
		boolean badnumbers = false;
		
		do {
			while (true) {	
				System.out.println("\r\nVeuillez entrer " + +PANWS + " chiffres");
				try {

					userc = br.readLine();

					for(int i = 0; i < PANWS; i++) {

						Input[i] = Integer.parseInt(""+userc.charAt(i));
                     
					if (Input[i] > MAXNUMBERS) {
						badnumbers = true;
					}else {
						badnumbers = false;
					}
					
					}
					
					break;
					
				} catch (IOException e) {
					e.printStackTrace();	
				} catch (NumberFormatException e) { 
					System.out.println("La valeur est incorrecte");
				}
			}

			if(userc.length() != PANWS) {
				System.out.println("Attention ! Merci de saisir " + +PANWS + " chiffres" );
			}
			
			if(badnumbers == true) {
				System.out.println("Une des valeurs choisies est sup�rieur � " +MAXNUMBERS);
				
			}
			  
		} while (userc.length() != PANWS || badnumbers == true);

		return Input;
	}
}