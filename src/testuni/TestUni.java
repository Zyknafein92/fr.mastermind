package testuni;

import java.util.Arrays;
import java.util.Iterator;

import start.Board;

public class TestUni {


	public static void main(String[] args) {
		int vie = 10;
		int tentative = 0;

		int a [] = {4,5,6,8};
		int b[] = new int [4];

		for(int i= 0;i< a.length;i++) 
		{
			b[i] = (int) (Math.random()*10); 
			System.out.println(b[i]);
		}

		do {
			for (int i = 0; i < b.length; i++) {
				for(int j = 0; j < b[j]; j++ ) {
					if (a[i] > b[i]) b[j]++;
					if (a[i] < b[i]) b[j]--;
					
				}
			}
				tentative++;
				System.out.println("Tour"+tentative + Arrays.toString(b));

			}while (vie > tentative);





	}
}

