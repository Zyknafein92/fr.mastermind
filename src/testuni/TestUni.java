package testuni;

import java.util.Random;
import java.util.Scanner;

public class TestUni {
	public static void main(String[] args) {

		String str1 = "1254"; // choix u
		String str2 = "1234"; // choix pc
		int optM = 4;

for(int i= 0; i < str1.length(); i++)
System.out.println(str1.substring(0,i).compareTo(str2.substring(0,i)));
	}
}
