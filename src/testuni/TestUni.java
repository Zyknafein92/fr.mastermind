package testuni;

import java.util.Random;

import start.Board;

public class TestUni {

	public static void main(String[] args) {


		Random random = new Random();

		for(int i= 0;i< Board.optM;i++) 
		{
			Integer[] botroll = new Integer[4];
			botroll[i] = (random.nextInt(9) +1) ;
			System.out.println(botroll[i]);
		}

	}
}