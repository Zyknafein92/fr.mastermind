package option;

import java.util.Arrays;
import java.util.Scanner;

import start.Board;

public class PlayerInput {
	Scanner sc = new Scanner(System.in);
	private Integer[] playerInput = new Integer[Board.optM];

	public PlayerInput() {

		generatePlayerInput();

	}

	public Integer[] generatePlayerInput() {
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

					playerInput[i]=Integer.parseInt(""+userc.charAt(i));
				}
			}
		} while (userc.length() != Board.optM);
		return playerInput;
	}

	public Integer[] getPlayerInput() {
		return playerInput;
	}

	public void setPlayerInput(Integer[] playerInput) {
		this.playerInput = generatePlayerInput();
	}
	
	public String toString() {
		return Arrays.toString(playerInput);
	}

}