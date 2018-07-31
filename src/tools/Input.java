package tools;

import java.util.Arrays;
import java.util.Scanner;

import option.GameOptions;
import start.Board;

/**
 * 
 * Cette classe sert à saisir et contrôler les saisis claviers de l'utilisateur grâce à la méthode Scanner.
 * @author Zyk
 *
 */

public class Input {
	
	Scanner sc = new Scanner(System.in);
	private int pawns = GameOptions.getPanws();
	private Integer[] Input = new Integer[pawns];

	public Input() {

		generateInput();

	}

	/**
	 * Méthode qui permet de contrôler la saisie de l'utilisateur et de la mémoriser pour une utilisation ultérieure.
	 * 
	 * @return
	 * Tableau d'Integer
	 */
	
	public Integer[] generateInput() {
		String userc = "";
		do {
			System.out.println("\r\nVeuillez entrer " + +pawns + " chiffres");
			userc = sc.nextLine();

			if (userc.length() != pawns) 
			{
				System.out.println("Attention, vous n'avez pas sélectionné le bon nombre de chiffre !");
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

	/**
	 * @return la saisie clavier de l'utilisateur.
	 */
	
	public Integer[] getInput() {
		return Input;
	}

	/**
	 * @param Saisie clavier de l'utilisateur.
	 */
	
	public void setInput(Integer[] playerInput) {
		this.Input = generateInput();
	}
	
	public String toString() {
		return Arrays.toString(Input);
	}

}