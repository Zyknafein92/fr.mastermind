package start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import option.GameOptions;
import tools.IObservable;
import tools.IObserver;

/**
 * 
 * Game est la classe qui regroupe les m�thodes communes, et les attributs
 * communs de tous les jeux. Ceci inclut : les m�thodes de comparaisons, ajouts
 * et d�placements des pions, l'affichage de r�sultat
 * 
 * @author Zyk
 *
 */

public abstract class Game implements IObservable {

	Scanner sc = new Scanner(System.in);

	protected int gameCounter;
	protected int isPresent;
	protected int inPosition;
	protected int color;
	protected int ballFound;
	protected int moveI; 
	protected int moveJ;
	protected String[] soluc;
	protected Integer[] combinaison;
	protected Integer[] secret;
	protected Integer[] testColor;
	protected ArrayList<Integer> combinaisonIA;
	protected ArrayList<ArrayList<Integer>> listCombinaison;
	protected ArrayList<IObserver> listObserver;
	protected boolean iswin;
	protected boolean check;

	public Game () {
		this.gameCounter = 1;
		this.isPresent = 0;
		this.color = 0;
		this.ballFound = 0;
		this.moveI = 0;
		this.moveJ = 0;
		this.soluc = new String[GameOptions.PAWNS];
		this.combinaison = new Integer[GameOptions.PAWNS];
		this.secret = new Integer[GameOptions.PAWNS];
		this.testColor = new Integer[GameOptions.PAWNS];
		this.combinaisonIA = new ArrayList<Integer>();
		this.listCombinaison = new ArrayList<ArrayList<Integer>>();
		this.listObserver = new ArrayList<IObserver>();
		this.iswin = false;
		this.check = false;
	}

	/**
	 * @return G�n�re un tableau d'Integer al�atoire.
	 */

	public static Integer[] generateBotRoll() {
		Integer[] botRoll = new Integer[GameOptions.PAWNS];
		Random random = new Random();
		for(int i = 0; i < GameOptions.PAWNS; i++) 
		{
			botRoll[i] = random.nextInt(GameOptions.MAX_NUMBERS);
		}
		return botRoll;
	}

	/**
	 * TODO
	 * @return
	 */
	
	public Integer[] generateInput() {
		Scanner scan = new Scanner(System.in);
		Integer[] input = new Integer[GameOptions.PAWNS];
		Boolean badnumbers;

		do {
			badnumbers = false;
			Integer value;
			String userc = scan.nextLine();
			System.out.println("\r\nVeuillez entrer " + GameOptions.PAWNS + " chiffres");
			if (userc.length() != GameOptions.PAWNS) {
				System.out.println("Attention ! Merci de saisir " + GameOptions.PAWNS + " chiffres" );
				badnumbers = true;
			} else {
				for(int i = 0; i < input.length; i++) {
					value = Character.getNumericValue(userc.charAt(i));
					if (Character.isDigit(userc.charAt(i)) && value <= GameOptions.MAX_NUMBERS) {
						input[i] = value;
					} else {
						System.out.println("La valeur : '" + userc.charAt(i) + "' est sup�rieur � " + GameOptions.MAX_NUMBERS + " ou ce ne sont pas des chiffres...");
						badnumbers = true;
					}
				}
			}

		} while (badnumbers);

		return input;
	}

	// M�thode des jeux PlusMoins

	/**
	 * 
	 * compareChallenger compare les tableaux d'integers secret et combinaison pour 
	 * mettre � jour un string qui affichera le r�sultat de la proposition dans le mode ChallengerPlusMoins
	 * 
	 * @param secret 
	 *  Tableau d'Integer qui contient le secret.
	 * 
	 * @param combinaison
	 * Tableau d'Integer qui contient la combinaison.
	 *         
	 */

	public void compareChallenger(Integer[] secret, Integer[] combinaison) {

		for (int i = 0; i < GameOptions.PAWNS; i++) {
			if (secret[i] < combinaison[i]) {
				soluc[i] = "" + "-";
			} else if (secret[i] > combinaison[i]) {
				soluc[i] = "" + "+";
			} else {
				soluc[i] = "" + "=";
			}
		}
	}

	/**
	 * 
	 * compareDefenseur est la m�thode de l'IA qui compare secret et combinaison pour mettre � jour la combinaison de l'ordinateur.
	 *  
	 * @param secret
	 * Tableau d'Integer qui repr�sente le secret.
	 * @param combinaison
	 * Tableau d'Integer qui repr�sente la combinaison.
	 * 
	 */

	public void compareDefenseur(Integer[] secret, Integer[] combinaison) {

		for (int i = 0; i < combinaison.length; i++) {
			if (secret[i] > combinaison[i] && combinaison[i] < GameOptions.MAX_NUMBERS) {
				combinaison[i]++;
			}
			if (secret[i] < combinaison[i] && combinaison[i] <  GameOptions.MAX_NUMBERS) {
				combinaison[i]--;
			}
			if (secret[i] < combinaison[i]) {
				soluc[i] = "" + "-";
			} else if (secret[i] > combinaison[i]) {
				soluc[i] = "" + "+";
			} else {
				soluc[i] = "" + "=";
			}
		}
	}

	// M�thode des jeux MasterMind


	/**
	 * 
	 * compareInposition compare les pions a l'indice i de secret et combinaison. 
	 * Si un pion est bien plac�, la valeur de inposition est incr�ment�.
	 * 
	 * @param secret
	 * Tableau d'Integer qui repr�sente le secret.
	 * 
	 * @param combinaison
	 * Tableau d'Integer qui repr�sente la combinaison.
	 * 
	 * @return
	 * Retourne le nombre de pion en position
	 * 
	 */

	protected int compareInposition(Integer[] secret, Integer[] combinaison) {

		int inposition = 0;
		for (int i = 0; i < GameOptions.PAWNS; i++) {
			if (combinaison[i].equals(secret[i])) {
				inposition++;
			}
		}
		return inposition;
	}


	/**
	 * 
	 * comparePresent est la m�thode qui compare le secret et la combinaison a l'indice i. 
	 * Si le pion de la combinaison est pr�sent dans le secret, il incr�mente la valeur ispresent.
	 * 
	 * @param secret
	 * Tableau d'Integer qui repr�sente le secret.
	 * 
	 * @param combinaison
	 * Tableau d'Integer qui repr�sente la combinaison.
	 * 
	 * @return
	 * Retourne le nombre de pion pr�sent apr�s savoir soustrait les pions qui sont � la bonne position.
	 * 
	 */

	protected int comparePresent(Integer[] secret, Integer[] combinaison) {

		int ispresent = 0;
		boolean found = false;
		int j = 0;

		for (int i = 0; i < GameOptions.PAWNS; i++) {
			j = 0;
			found = false;
			do {

				if (combinaison[i] == secret [j]) {
					ispresent++;
					found = true;
				}

				j++;
			} while (j < GameOptions.PAWNS && !found);
		}
		return ispresent;//- compareInposition(secret, combinaison);
		
	}

	protected int compareInpositionIA(ArrayList<Integer> combinaisonIA, Integer[] secret) {

		int inposition = 0;
		for (int i = 0; i < GameOptions.PAWNS; i++) {
			if (combinaisonIA.get(i).equals(secret[i])) {
				inposition++;
			}
		}
		return inposition;
	}

	/**
	 * 
	 * comparePresentIA est la m�thode qui compare le secret du joueur et la combinaison de l'ordinateur a l'indice i. 
	 * Si le pion de la combinaison est pr�sent dans le secret, il incr�mente la valeur ispresent et passe au pion suivant.
	 * 
	 * @param secret
	 * Tableau d'Integer qui repr�sente le secret.
	 * @param combinaison
	 * ArrayList qui contient la combinaison de l'ordinateur.
	 * @return
	 * Retourne le nombre de pion pr�sent apr�s soustraction avec le nombre de pion � la bonne position.
	 * 
	 */

	protected int comparePresentIA(ArrayList<Integer> combinaisonIA, Integer[] secret) {
		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < GameOptions.PAWNS; i++) {
			int j = 0;
			found = false;
			do {
				if (combinaisonIA.get(j).equals(secret[i])) {
					ispresent++;
					found = true;
				}
				j++;
			} while (j < GameOptions.PAWNS && !found);
		}
		return ispresent - compareInpositionIA(combinaisonIA, secret);
	}
	protected int countPresentIA(Integer[] secret, Integer[] testColor) {
		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < GameOptions.PAWNS; i++) {
			int j = 0;
			found = false;
			do {
				if (combinaisonIA.get(j).equals(secret[i])) {
					ispresent++;
					found = true;
				}
				j++;
			} while (j < GameOptions.PAWNS && !found);
		}
		return ispresent;
	}
	


	 
	/**
	 * 
	 * addToCombinaison remplace � chaque fois qu'un pion est pr�sent ou � la bonne position repr�sent� par int ballcolor. 
	 * Le pion est ajout� � l'indice de "pos" � la couleur actuelle. 
	 * A chaque ajout, la valeur de pos est incr�ment� ainsi que le nombre de pion trouv�.
	 * 
	 * @param combinaisonIA
	 *  ArrayList qui contient la combinaison de l'ordinateur.
	 * @param color
	 * Int qui repr�sente la valeur de la couleur actuelle.
	 * @param ballcolor
	 * Nombre de pion �tant pr�sent ou � la bonne position � ajouter.
	 * 
	 */

	protected void addToCombinaison (ArrayList<Integer> combinaisonIA, int color, int ballcolor) {
     
		}
	


	/**
	 * 
	 * movePawns modifie l'emplacement des pions de la position I � la position J pour trouver la bonne position des pions.
	 * A chaque d�placement de pion, J est incr�ment�.
	 * 
	 * @param combinaisonIA
	 * ArrayList qui contient la combinaison de l'ordinateur.
	 * 
	 * @param listCombinaison
	 * ArrayList contenant les combinaisons d�j� tent� par l'ordinateur.
	 * 
	 */

	@SuppressWarnings("unchecked")
	protected void movePawns (ArrayList<Integer> combinaisonIA, ArrayList<ArrayList<Integer>> listCombinaison) {


		do {

			if (moveJ > GameOptions.PAWNS - 1) {
				moveJ = 0;
				moveI++;
			}

			if (moveI > GameOptions.PAWNS - 1) {
				moveI = 0;
			}

			Collections.swap(combinaisonIA, moveI, moveJ);
			//int temp = combinaisonIA[moveI];
			//combinaison[moveJ] = combinaisonIA[moveI];
			//combinaisonIA[moveI] = temp;
			moveJ++;
		} while(listCombinaison.contains(combinaisonIA));
		listCombinaison.add((ArrayList<Integer>) combinaisonIA.clone());
		
	}	

	/**
	 * 
	 * Boolean qui compare le secret et la combinaison.
	 * Si il est retourne la valeur True, la partie est gagn�e
	 * 
	 * @param secret
	 * Tableau d'Integer qui contient le secret.
	 * @param combinaison
	 * Tableau d'Integer qui contient la combinaison.
	 * @return
	 * Retourne la valeur du boolean
	 * 
	 */

	protected boolean isWin(Integer[] secret, Integer[] combinaison) {
		return Arrays.equals(secret, combinaison);
	}

	/**
	 * resultat affiche un String contenant la combinaison et soluc.
	 * 
	 * @param combinaison
	 *  Tableau d'Integer qui contient la combinaison.
	 *  
	 * @param Soluc
	 * Tableau String qui contient le r�sultat de la comparaison de la combinaison et du secret affich� sous la forme de + - ou =.
	 * 
	 * @return
	 * Retourne le String resultat.
	 * 
	 */

	protected String resultat(Integer[] combinaison, String[] Soluc) {
		String resultat = "";

		resultat += "Tentative(s) : "+gameCounter +" Combinaison : " + Arrays.toString(combinaison) + " || R�sultat : " + Arrays.toString(Soluc);

		return resultat;
	}

	/**
	 * 
	 * resultat affiche un String contenant le nombre de tour, la proposition du joueur (combinaison), 
	 * le nombre de pion pr�sent, � la bonne position.
	 * 
	 * @param combinaison
	 * Tableau d'Integer qui contient la combinaison.
	 * 
	 * @param secret
	 * Tableau d'Integer qui contient le secret.
	 * 
	 * @return
	 * Retourne le String resultat.
	 * 
	 */

	protected String resultat(Integer[] secret, Integer[] combinaison) {
		String resultat = "";

		if (comparePresent(secret, combinaison) != 0)
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + Arrays.toString(combinaison) + " || R�sultat : "
					+ comparePresent(secret, combinaison) + " pr�sent(s), " + compareInposition(secret, combinaison)
					+ " bonne(s) position(s) ";
		else
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + Arrays.toString(combinaison) + " || R�sultat : "
					+ compareInposition(secret, combinaison) + " bonne(s) position(s) ";
		return resultat;
	}

	protected String resultat(ArrayList<Integer> combinaison, Integer[] secret) {
		String resultat = "";

		if (comparePresentIA(combinaisonIA,secret) != 0)
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || R�sultat : "
					+ comparePresentIA(combinaisonIA,secret) + " pr�sent(s), " + compareInpositionIA(combinaisonIA,secret)
					+ " bonne(s) position(s) ";
		else
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || R�sultat : "
					+ compareInpositionIA(combinaisonIA,secret) + " bonne(s) position(s) ";
		return resultat;
	}
	 

	/**
	 * 
	 * @return le tableau String Soluc
	 * 
	 */

	protected String[] getSoluc() {
		return soluc;
	}

	/**
	 * 
	 * @return le tableau Integer combinaison
	 * 
	 */

	protected Integer[] getCombinaison() {
		return combinaison;
	}

	/**
	 * 
	 * @return l'ArrayList Integer de combinaisonIA
	 * 
	 */

	protected ArrayList<Integer> getCombinaisonIA() {
		return combinaisonIA;
	}

	/**
	 * 
	 * @return le tableau Integer secret
	 * 
	 */

	protected Integer[] getSecret() {
		return secret;
	}

	/**
	 * 
	 * @return le tableau Integer testcolor
	 * 
	 */

	protected Integer[] getTestcolor() {
		return testColor;
	}

	/**
	 * 
	 * @return int ispresent
	 * 
	 */

	protected int getIspresent() {
		return isPresent;
	}

	/**
	 * 
	 * @return int inposition
	 * 
	 */

	protected int getInposition() {
		return inPosition;
	}

	/**
	 * 
	 * @return  listObserver
	 * 
	 */

	protected ArrayList<IObserver> getListObserver() {
		return listObserver;
	}

	/**
	 * 
	 * @return int color
	 * 
	 */

	protected int getColor() {
		return color;
	}

	/**
	 * 
	 * @return booleen check
	 * 
	 */

	protected boolean isCheck() {
		return check;
	}

	/**
	 * 
	 * @return int ballFound
	 * 
	 */

	protected int getBallFound() {
		return ballFound;
	}

	/**
	 * 
	 * @param soluc
	 * Le String soluc mis � jour.
	 * 
	 */
	protected void setSoluc(String[] soluc) {
		this.soluc = soluc;
	}

	/**
	 * 
	 * @param combinaison
	 * Tableau Integer combinaison mis � jour
	 * 
	 */

	protected void setCombinaison(Integer[] combinaison) {
		this.combinaison = combinaison;
	}

	/**
	 * 
	 * @param combinaisonIA
	 * Arraylist Integer combinaisonIA mise � jour.
	 *            
	 */

	protected void setCombinaisonIA(ArrayList<Integer> combinaisonIA) {
		this.combinaisonIA = combinaisonIA;
	}

	/**
	 * 
	 * @param secret
	 * Tableau Integer secret mis � jour.
	 *            
	 */

	protected void setSecret(Integer[] secret) {
		this.secret = secret;
	}

	/**
	 * 
	 * @param testcolor
	 * Tableau Integer test color mis � jour
	 * 
	 */

	protected void setTestcolor(Integer[] testcolor) {
		this.testColor = testcolor;
	}

	/**
	 * 
	 * @param ispresent
	 * int isPresent mis � jour.
	 *            
	 */

	protected void setisPresent(int isPresent) {
		this.isPresent = isPresent;
	}

	/**
	 * 
	 * @param inposition
	 * int inPosition mis � jour.
	 * 
	 */

	protected void setInposition(int inPosition) {
		this.inPosition = inPosition;
	}

	/**
	 * 
	 * @param listObserver
	 * listObserver mise � jour.
	 * 
	 */

	protected void setListObserver(ArrayList<IObserver> listObserver) {
		this.listObserver = listObserver;
	}

	/**
	 * 
	 * @param color
	 * int color mis � jour.      
	 *            
	 */

	protected void setColor(int color) {
		this.color = color;
	}

	/**
	 * 
	 * @param check
	 * booleen check mis � jour.
	 * 
	 */

	protected void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * 
	 * @param ballFound
	 *  int ballFound mis � jour.
	 *  
	 */

	protected void setBallFound(int ballFound) {
		this.ballFound = ballFound;
	}

	@Override
	public void addObserver(IObserver obs) {
		listObserver.add(obs);

	}

	@Override
	public void notifyObserver() {
		for (IObserver obs : listObserver) {
			obs.update();
		}

	}

}
