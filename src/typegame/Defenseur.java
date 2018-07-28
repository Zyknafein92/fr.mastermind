package typegame;

import java.util.ArrayList;
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
				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison ! Essaye encore !");
			}else {
				System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal");
			}
			Board.tried++;
		} while (Board.tried <= Board.life && iswin == false);
		this.notifyObserver();
	}

	@SuppressWarnings("unchecked")
	public void playDefenseurMastermind() {

		System.out.println(rulesDefenseur());
		Input player = new Input();
		this.secret = player.getInput();

		System.out.println(" Votre nombre secret est :"+Arrays.toString(secret));

		for(int y = 0; y < Board.pawns; y++) {
			testColor[y]=color;
			combinaisonIA.add(color);
		}

		do { 
			do {
				for(int i = 0; i < Board.pawns; i++) {
					testColor[i]=color;

					if(check == false) {
						combinaisonIA.set(i, color);
					}
					if( pos <= i ) {
						combinaisonIA.set(i, color);
					}
				}

				int inposition = compareInposition(secret, testColor);
				isPresent = comparePresent(secret, testColor);
				int ballcolor = inposition + isPresent;
				if(ballcolor > 0) {
					addToCombinaison(combinaisonIA,color,ballcolor);
				}

				System.out.println(Board.tried);
				System.out.println(resultat(combinaisonIA,secret));
				listCombinaison.add((ArrayList<Integer>)combinaisonIA.clone());
				Board.tried++;
				color++;
				
			} while (ballFound != Board.pawns);
		
			moveBallCombinaison(combinaisonIA, secret, listCombinaison);
			
			if (compareInpositionIA(secret, combinaisonIA) == Board.pawns) {
				Board.tried--;
				System.out.println("Perdu ! L'ordinateur a trouvé votre combinaison  en "+Board.tried + " tentative(s) !  Retour au menu principal");
			}else if (Board.tried < Board.life) {
				System.out.println("Mauvaise combinaison ! Essaye encore !");
			}else {
				System.out.println("Vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison ! Retour au menu principal");
			}		
		} while (compareInpositionIA(secret, combinaisonIA) < Board.pawns && Board.tried <= Board.life);

		this.notifyObserver();
	}


	private static  String rulesDefenseur() {
		String str1 = "";

		str1 = ("\r\n------------------------------");
		str1 +=("\r\n--------- Defenseur ----------");
		str1 +=("\r\n------------------------------");
		str1 +=("\r\nVous devez entrer un nombre mystère que l'ordinateur va devoir trouver.");
		str1 +=("\r\nIl est composé de "+Board.pawns + " chiffres compris entre 0 et 9.");
		str1 +=("\r\nL'ordinateur à le droit à "+Board.life + " tentative(s) !");
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

