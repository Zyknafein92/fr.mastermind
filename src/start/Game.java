package start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import option.GameOptions;
import tools.IObservable;
import tools.IObserver;

/**
 * 
 * Game est la classe qui regroupe les méthodes communes, et les attributs
 * communs de tous les jeux. Ceci inclut : les méthodes de comparaisons, ajouts
 * et déplacements des pions, l'affichage de résultat
 * 
 * @author Zyk
 *
 */

public abstract class Game implements IObservable {

	static final Logger LOGGER = LogManager.getRootLogger();
	Scanner sc = new Scanner(System.in);

	protected int gameCounter;
	protected int isPresent;    
	protected int inPosition;
	protected int pawnsValue;
	protected int pawnsFound;
	protected int pawnsToAdd;
	protected int moveI; 
	protected int moveJ;
	protected int pos;
	protected String[] soluc;
	protected Integer[] combinaison;
	protected Integer[] secret;
	protected Integer[] testColor;
	protected int [] resultat;
	protected ArrayList<Integer> combinaisonIA;
	protected ArrayList<ArrayList<Integer>> listCombinaison;
	protected ArrayList<IObserver> listObserver;
	protected boolean iswin;
	protected boolean check;

	public Game () {

		this.gameCounter = 1;
		this.isPresent = 0;
		this.pawnsValue = 0;
		this.pawnsFound = 0;
		this.pawnsToAdd = 0;
		this.moveI = 0;
		this.moveJ = 0;
		this.pos = 0;
		this.resultat = new int[2];
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
	 *  Méthode qui génère un nombre aléatoire, compris entre 0 et le nombre maximum autorisé 
	 *  
	 * @return Génère un tableau d'Integer aléatoire.
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
	 * Méthode qui mémorise la saisit console l'utilisateur.
	 * 
	 * @return tableau interger input
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
						System.out.println("La valeur : '" + userc.charAt(i) + "' est supérieur à " + GameOptions.MAX_NUMBERS + " ou ce ne sont pas des chiffres...");
						badnumbers = true;
					}
				}
			}

		} while (badnumbers);

		return input;
	}

	// Méthode des jeux PlusMoins

	/**
	 * 
	 * compareChallenger compare les tableaux d'integers secret et combinaison pour 
	 * mettre à jour un string qui affichera le résultat de la proposition dans le mode ChallengerPlusMoins
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
	 * compareDefenseur est la méthode de l'IA qui compare secret et combinaison pour mettre à jour la combinaison de l'ordinateur.
	 *  
	 * @param secret
	 * Tableau d'Integer qui représente le secret.
	 * @param combinaison
	 * Tableau d'Integer qui représente la combinaison.
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

	// Méthode des jeux MasterMind


	/**
	 * 
	 * compareInposition compare les pions a l'indice i de secret et combinaison. 
	 * Si un pion est bien placé, la valeur de inposition est incrémenté.
	 * 
	 * @param secret
	 * Tableau d'Integer qui représente le secret.
	 * 
	 * @param combinaison
	 * Tableau d'Integer qui représente la combinaison.
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
	 * comparePresent est la méthode qui compare le secret et la combinaison a l'indice i. 
	 * Si le pion de la combinaison est présent dans le secret, il incrémente la valeur ispresent.
	 * 
	 * @param secret
	 * Tableau d'Integer qui représente le secret.
	 * 
	 * @param combinaison
	 * Tableau d'Integer qui représente la combinaison.
	 * 
	 * @return
	 * Retourne le nombre de pion présent après savoir soustrait les pions qui sont à la bonne position.
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
				if (combinaison[i].equals(secret[j])) {
					ispresent++;
					found = true;
				}
				j++;
			} while (j < GameOptions.PAWNS && !found);
		}
		return ispresent - compareInposition(secret, combinaison);

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
	 * comparePresentIA est la méthode qui compare le secret du joueur et la combinaison de l'ordinateur a l'indice i. 
	 * Si le pion de la combinaison est présent dans le secret, il incrémente la valeur ispresent et passe au pion suivant.
	 * 
	 * @param secret
	 * Tableau d'Integer qui représente le secret.
	 * @param combinaison
	 * ArrayList qui contient la combinaison de l'ordinateur.
	 * @return
	 * Retourne le nombre de pion présent après soustraction avec le nombre de pion à la bonne position.
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
	/**
	 * Méthode qui compare le secret
	 * @param secret
	 * @param testColor
	 * @return
	 */

	protected int countPresent(Integer[] secret, Integer[] testColor) {
		int pawnsToAdd = 0;
		boolean found = false;

		for (int i = 0; i < GameOptions.PAWNS; i++) {
			int j = 0;
			found = false;
			do {
				if (testColor[j].equals(secret[i])) {
					pawnsToAdd++;
					found = true;
				}
				j++;
			} while (j < GameOptions.PAWNS && !found);
		}
		return pawnsToAdd;
	}


	protected int[] countPosition (Integer[]secret, Integer[] combinaison) {

		inPosition = 0;
		isPresent = 0;

		Set<Integer> num = new HashSet<>();

		for(int i = 0; i < secret.length; i++) {
			if(combinaison[i] == secret[i]) inPosition++;
			else{
				 for(int key : secret){
			            if (combinaison[i] == key) num.add(key);
			        }
				}
			}
	
		isPresent = num.size();
		resultat[0]= inPosition;
		resultat[1]= isPresent;
		return resultat;
	}

	protected int[] countPosition (Integer[]secret, ArrayList<Integer> combinaisonIA) {

		inPosition = 0;
		isPresent = 0;
		
		Integer[] testComb = combinaisonIA.toArray(new Integer[combinaisonIA.size()]);
		
		System.out.println(Arrays.toString(testComb));
		
		Set<Integer> num = new HashSet<>();

		for(int i = 0; i < secret.length; i++) {
			if(testComb[i] == secret[i]) inPosition++;
			else{
				for(int key : secret){
		            if (testComb[i] == key) num.add(key);
			        }
				}
			}
	
		isPresent = num.size();
		resultat[0]= inPosition;
		resultat[1]= isPresent;
		System.out.println(Arrays.toString(resultat));
		return resultat;
	}
	
	
	/**
	 * 
	 * addToCombinaison remplace à chaque fois qu'un pion est présent ou à la bonne position représenté par int ballcolor. 
	 * Le pion est ajouté à l'indice de "pos" à la couleur actuelle. 
	 * A chaque ajout, la valeur de pos est incrémenté ainsi que le nombre de pion trouvé.
	 * 
	 * @param combinaisonIA
	 *  ArrayList qui contient la combinaison de l'ordinateur.
	 * @param color
	 * Int qui représente la valeur de la couleur actuelle.
	 * @param ballcolor
	 * Nombre de pion étant présent ou à la bonne position à ajouter.
	 * 
	 */

	protected void addToCombinaison (ArrayList<Integer> combinaisonIA, Integer[] testColor, int pawnsToAdd) {

		if(pawnsToAdd > 0 && pos < GameOptions.PAWNS) { //- 1) {
			int x = 0;
			while(pawnsToAdd  > x) {
				combinaisonIA.set(pos, pawnsValue);
				pos++;
				pawnsFound++;
				x++;
			}
		}

		for(int i = 0; i < GameOptions.PAWNS ; i++) {		
			if( pos <= i) {
				combinaisonIA.set(i, pawnsValue);
			}
		}
	}



	/**
	 * 
	 * movePawns modifie l'emplacement des pions de la position I à la position J pour trouver la bonne position des pions.
	 * A chaque déplacement de pion, J est incrémenté.
	 * 
	 * @param combinaisonIA
	 * ArrayList qui contient la combinaison de l'ordinateur.
	 * 
	 * @param listCombinaison
	 * ArrayList contenant les combinaisons déjà tenté par l'ordinateur.
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
				Collections.shuffle(combinaisonIA);
			}

			Collections.swap(combinaisonIA, moveI, moveJ);
			moveJ++;



		}while(listCombinaison.contains(combinaisonIA));



		listCombinaison.add((ArrayList<Integer>) combinaisonIA.clone());


	}	

	/**
	 * 
	 * Boolean qui compare le secret et la combinaison.
	 * Si il est retourne la valeur True, la partie est gagnée
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
	 * Tableau String qui contient le résultat de la comparaison de la combinaison et du secret affiché sous la forme de + - ou =.
	 * 
	 * @return
	 * Retourne le String resultat.
	 * 
	 */

	protected String resultat(Integer[] combinaison, String[] Soluc) {
		String resultat = "";

		resultat += "Tentative(s) : "+gameCounter +" Combinaison : " + Arrays.toString(combinaison) + " || Résultat : " + Arrays.toString(Soluc);

		return resultat;
	}


	/**
	 * Méthode qui affiche un String informant l
	 * @param secret
	 * @param combinaison
	 * @param isPresent
	 * @param inPosition
	 * @return
	 */
	protected String resultat(Integer[] combinaison, int isPresent, int inPosition) {
		String resultat = "";

		if (isPresent != 0)
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + Arrays.toString(combinaison) + " || Résultat : "+ isPresent + " présent(s), " + inPosition + " bonne(s) position(s) ";
		else
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + Arrays.toString(combinaison) + " || Résultat : "+ inPosition + " bonne(s) position(s) ";
		return resultat;
	}

	/**
	 * @param combinaisonIA
	 * @param secret
	 * @return
	 */
	protected String resultat(ArrayList<Integer> combinaisonIA,int isPresent, int inPosition) {
		String resultat = "";

		if (isPresent != 0)
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || Résultat : "+ isPresent + " présent(s), " + inPosition + " bonne(s) position(s) ";
		else
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || Résultat : "+ inPosition + " bonne(s) position(s) ";
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
		return pawnsValue;
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
	 * @param soluc
	 * Le String soluc mis à jour.
	 * 
	 */
	protected void setSoluc(String[] soluc) {
		this.soluc = soluc;
	}

	/**
	 * 
	 * @param combinaison
	 * Tableau Integer combinaison mis à jour
	 * 
	 */

	protected void setCombinaison(Integer[] combinaison) {
		this.combinaison = combinaison;
	}

	/**
	 * 
	 * @param combinaisonIA
	 * Arraylist Integer combinaisonIA mise à jour.
	 *            
	 */

	protected void setCombinaisonIA(ArrayList<Integer> combinaisonIA) {
		this.combinaisonIA = combinaisonIA;
	}

	/**
	 * 
	 * @param secret
	 * Tableau Integer secret mis à jour.
	 *            
	 */

	protected void setSecret(Integer[] secret) {
		this.secret = secret;
	}

	/**
	 * 
	 * @param testcolor
	 * Tableau Integer test color mis à jour
	 * 
	 */

	protected void setTestcolor(Integer[] testcolor) {
		this.testColor = testcolor;
	}

	/**
	 * 
	 * @param ispresent
	 * int isPresent mis à jour.
	 *            
	 */

	protected void setisPresent(int isPresent) {
		this.isPresent = isPresent;
	}

	/**
	 * 
	 * @param inposition
	 * int inPosition mis à jour.
	 * 
	 */

	protected void setInposition(int inPosition) {
		this.inPosition = inPosition;
	}

	/**
	 * 
	 * @param listObserver
	 * listObserver mise à jour.
	 * 
	 */

	protected void setListObserver(ArrayList<IObserver> listObserver) {
		this.listObserver = listObserver;
	}

	/**
	 * 
	 * @param color
	 * int color mis à jour.      
	 *            
	 */

	protected void setColor(int color) {
		this.pawnsValue = color;
	}

	/**
	 * 
	 * @param check
	 * booleen check mis à jour.
	 * 
	 */

	protected void setCheck(boolean check) {
		this.check = check;
	}

	/**
	 * 
	 * @param ballFound
	 *  int ballFound mis à jour.
	 *  
	 */

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
