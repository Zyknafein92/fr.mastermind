package tools;

import java.util.Arrays;
import java.util.Scanner;

import start.Board;

public class Input {
	Scanner sc = new Scanner(System.in);
	private Integer[] Input = new Integer[Board.optM];

	public Input() {

		generateInput();

	}

	public Integer[] generateInput() {
		String userc = "";
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

					Input[i]=Integer.parseInt(""+userc.charAt(i));
				}
			}
		} while (userc.length() != Board.optM);
		return Input;
	}

	public Integer[] getInput() {
		return Input;
	}

	public void setInput(Integer[] playerInput) {
		this.Input = generateInput();
	}
	
	public String toString() {
		return Arrays.toString(Input);
	}

}