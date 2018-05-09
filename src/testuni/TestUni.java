package testuni;

import java.util.Random;
import java.util.Scanner;

public class TestUni {
	public static void main(String[] args) {

		int optM = 4;
		String PC;

		for(int i= 0;i< optM;i++) 
		{

			PC = ""+(int) (Math.random()*10);


			System.out.print(PC);
		}
	}
}