package gameplusmoins;

import java.util.Scanner;

import start.Board;
import typegame.Challenger;



public class GamePlusMoins {
	int mod = Board.mod;

	public GamePlusMoins(int mod) {

		System.out.println("---------- Mode +/- ----------");

		if (mod == 1) {
			System.out.println(Challenger.ruleschallenger());
            PC.getPC();
            
		}else if (mod == 2) {

		}else if (mod == 3) {

		}
	}

}