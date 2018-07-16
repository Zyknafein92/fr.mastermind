package typegame;

import java.util.Arrays;

import start.Board;
import start.Game;
import tools.BotRoll;
import tools.IObserver;
import tools.Input;

public class Defenseur extends Game {


	BotRoll PC = new BotRoll();
	

	public Defenseur () {

		this.combinaison = PC.getBotRoll();
	}

	public void playDefenseurPlusMoins() {

		System.out.println(rulesDefenseur());
		Input player = new Input();
		this.secret = player.getInput();
		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		do {

			compareDefenseur(this.secret,this.combinaison);

			System.out.println(resultat(combinaison,Soluc));

			iswin(this.secret,this.combinaison);

			if (iswin == true) {
				System.out.println("Perdu ! L'ordinateur a trouv� votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison ! Essaye encore !");
			}else {
				System.out.println("Vous avez gagn� ! L'ordinateur n'a pas trouv� votre combinaison ! Retour au menu principal");
			}
			Board.tried++;
		} while (Board.tried <= Board.life && iswin == false);
		this.notifyObserver();
	}

	public void playDefenseurMastermind() {

		System.out.println(rulesDefenseur());
		Input player = new Input();
		this.secret = player.getInput();
		
		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));
		
			for(int y = 0; y < Board.pawns; y++) {
				testcolor[y]=color;
				combinaisonIA.add(0);
			}
			
			Board.tried++;
			
			do { 

				for(int i = 0; i < Board.pawns; i++) {
					testcolor[i]=color;
					if(check == true) {
					combinaisonIA.set(i, color);
					}	
				}

				int inposition = compareinposition(secret, testcolor);
				ispresent = comparepresent(secret, testcolor);
				int ballcolor = inposition + ispresent;
				if(ballcolor > 0) {
					addToCombinaison(combinaisonIA,color,ballcolor);
				}
				if(pos == Board.pawns) {
					moveBallCombinaison(combinaisonIA,secret);
				}
				
				compareinpositionIA(secret, combinaisonIA);
				comparepresentIA(secret, combinaisonIA);
				
				System.out.println(resultat(combinaisonIA));
				
				Board.tried++;
				color++;

			} while (compareinpositionIA(secret, combinaisonIA) < Board.pawns && Board.tried < 15);
			
			this.notifyObserver();
			
		}
		
	
	private static  String rulesDefenseur() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Defenseur ----------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez entrer un nombre myst�re que l'ordinateur va devoir trouver.");
		str1 +=("\r\nIl est compos� de "+Board.pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nL'ordinateur � le droit � "+Board.life + " tentative(s) !");
		str1 +=("\r\nA vous de jouer !");
		return str1;
	}

	@Override
	public void addObserver(IObserver obs) {
		listObserver.add(obs);
		
	}

	@Override
	public void notifyObserver() {
		for(IObserver obs : listObserver) {
			obs.update();
		}
		
	}
}

