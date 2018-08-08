package start;

public class Main {

	public static void main(String[] args){
		if(args.length >= 1) {
		System.out.println("Mode Developpeur");
		}
		
		Board newGame = new Board();
		newGame.Menu();
	}
}