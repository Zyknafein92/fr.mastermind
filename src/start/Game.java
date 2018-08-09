package start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
 * communs de tous les jeux. Ceci inclus les méthodes de comparaison, les ajouts
 * et déplacements des pions, l'affichage du résultat.
 * 
 * @author Zyk
 *
 */

public abstract class Game  implements IObservable {

	//protected static final Logger LOGGER = LogManager.getRootLogger();
	
	Scanner sc = new Scanner(System.in);

	protected int gameCounter; // variable qui represente le nombre de tours joués.
	protected int isPresent;   // variable qui represente le nombre de pions présents.
	protected int inPosition;  // variable qui represente les pions à la bonne position.
	protected int pawnsValue;  // variable qui represente la valeur actuelle du pion.
	protected int pawnsFound;  // variable qui represente le nombre de pion trouvé.
	protected int pawnsToAdd;  // variable qui represente le nombre de pion à ajouter.
	protected int moveI;       // variable qui represente l'indice de déplacement I.
	protected int moveJ;       // variable qui represente l'indice de déplacement J.
	protected int pos;         // variable qui represente une position.
	protected String[] soluc;  // String qui contient des indications  + - = dans le jeu.
	protected Integer[] combinaison; // Tableau d'Integer qui contient la combinaison.
	protected Integer[] secret;      // Tableau d'Integer qui contient le secret.
	protected Integer[] testColor;   // Tableau d'Integer qui contient la valeur panwsValue à tester.
	protected int[] resultat;        // Tableau d'Integer qui contient le résultat (indice 0 : inPosition / indice 1: isPresent).
	protected ArrayList<Integer> combinaisonIA; //ArrayList d'Integer qui contient la combinaison de l'IA pour le mode Mastermind Duel et Defenseur.
	protected ArrayList<ArrayList<Integer>> listCombinaison; //ArrayList des combinaisons testées par l'ordinateur dans Mastermind Duel et Defenseur.
	protected ArrayList<IObserver> listObserver;
	protected boolean iswin; // boolean qui renvoie true si le joueur actuel gagne la partie.

	/**
	 * Constructeur de Game qui contient la majorité des variables utilisées lors des jeux.
	 */

	public Game () {

		this.gameCounter = 1;
		this.isPresent = 0;
		this.inPosition = 0;
		this.pawnsValue = 0;
		this.pawnsFound = 0;
		this.pawnsToAdd = 0;
		this.moveI = 0;
		this.moveJ = 0;
		this.pos = 0;
		this.soluc = new String[GameOptions.PAWNS];
		this.combinaison = new Integer[GameOptions.PAWNS];
		this.secret = new Integer[GameOptions.PAWNS];
		this.testColor = new Integer[GameOptions.PAWNS];
		this.resultat = new int [2];
		this.combinaisonIA = new ArrayList<Integer>();
		this.listCombinaison = new ArrayList<ArrayList<Integer>>();
		this.listObserver = new ArrayList<IObserver>();
		this.iswin = false;

	}

	/**
	 *  Méthode qui génère un nombre aléatoire, compris entre 0 et le nombre maximum autorisé .
	 *  
	 * @return Tableau d'Integer aléatoire.
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
	 * @return Tableau d'Interger input.
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
	 * compareChallenger compare les tableaux d'Integer secret et combinaison pour 
	 * mettre à jour le String Soluc qui affichera le résultat de la proposition dans le mode ChallengerPlusMoins.
	 * 
	 * @param secret 
	 *  Tableau d'Integer qui contient le secret.
	 * 
	 * @param combinaison
	 * Tableau d'Integer qui contient la combinaison.
	 *         
	 */

	protected void compareChallenger(Integer[] secret, Integer[] combinaison) {

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

	protected void compareDefenseur(Integer[] secret, Integer[] combinaison) {

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
	 * Méthode qui compare les tableaux d'Integer secret et testColor.
	 * La méthode incrémente la valeur pawnsToAdd à chaque égalité.
	 * 
	 * @param secret
	 * Tableau d'Integer contenant le secret.
	 * @param testColor
	 * Tableau d'Integer qui contient la valeur panwsValue à tester.
	 * @return
	 * Renvois pawnsToAdd qui est le nombre de pion à ajouter à la combinaisonIA.
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


	/**
	 * Méthode qui compare le tableau d'Integer secret, et le tableau d'Integer combinaison.
	 * A chaque fois que la combinaison est égale au secret, la valeur inPosition est incrémentée,
	 * Si la valeur est présente, mais n'est pas à la bonne position, isPresent est incrémentée.
	 * 
	 * @param secret
	 *  Tableau d'Integer contenant le secret.
	 * @param combinaison
	 * Tableau d'Integer qui représente la combinaison.
	 * @return
	 * Tableau d'Integer qui contient le resultat (indice 0 : inPosition / indice 1: isPresent).
	 */

	protected int[] comparePositionA (Integer[]secret, Integer[] combinaison) {
		isPresent = 0;
		inPosition = 0;

		Set<Integer> uk = new HashSet<>();

		for(int i = 0; i < secret.length; i++) {
			if(combinaison[i].equals(secret[i])) {
				inPosition++;
			}
			else{
				for(int key : secret){
					if (combinaison[i].equals(key)) {
						uk.add(key);
					}
				}
			}
		}

		isPresent = uk.size();
		resultat[0]= inPosition;
		resultat[1]= isPresent;
		return resultat;
	}


	/**
	 * Méthode qui compare le tableau d'Integer secret, et l'ArrayList d'Integer combinaisonIA.
	 * A chaque fois que la combinaison est égale au secret, la valeur inPosition est incrémentée,
	 * Si la valeur est présente, mais n'est pas à la bonne position, isPresent est incrémentée.
	 * La création d'un Hashset permet d'éviter les doubles ajouts en cas de présence d'un nombre similaire.
	 * 
	 * @param secret
	 *  Tableau d'Integer contenant le secret.
	 * @param combinaisonIA
	 * ArrayList d'Integer qui représente la combinaison.
	 * @return
	 * Tableau d'Integer qui contient le resultat (indice 0 : inPosition / indice 1: isPresent).
	 */

	protected int[] comparePositionB(Integer[]secret, ArrayList<Integer>combinaisonIA){
		inPosition = 0;
		isPresent = 0;
		boolean [] testtab = new boolean[secret.length]; 
		Set<Integer> num = new HashSet<>();

		for(int i = 0; i < secret.length; i++) {
			if(combinaisonIA.get(i).equals(secret[i])) {
				inPosition++;
				testtab[i]=true;
			}
		}

		for(int i = 0; i < secret.length; i++) {
			for(int j= 0; j < secret.length; j++) {
				if(combinaisonIA.get(i).equals(secret[j]) && testtab[j] == false) {
					num.add(j);

				}
			}
		}
		isPresent =  num.size();
		resultat[0]= inPosition;
		resultat[1]= isPresent;
		return resultat;
	}


	/**
	 * addToCombinaison remplace à chaque fois qu'un pion est présent ou à la bonne position représentée par pawnsFound, 
	 * un autre pion à la valeur actuelle.
	 * Le pion est ajouté à l'indice de "pos" à la valeur actuelle. 
	 * A chaque ajout, la valeur de pos est incrémentée ainsi que le nombre de pion trouvé.
	 * 
	 * @param combinaisonIA
	 *  ArrayList qui contient la combinaison de l'ordinateur.
	 *  
	 * @param pawnsValue
	 * Int qui représente la valeur de la valeur du pion actuelle.
	 * 
	 * @param panwsFound
	 * Nombre de pion étant présent ou à la bonne position à ajouter.
	 * 
	 */

	protected void addToCombinaison (ArrayList<Integer> combinaisonIA, Integer[] testColor, int pawnsToAdd) {

		if(pawnsToAdd > 0 && pos < GameOptions.PAWNS) {
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
	 * ArrayList contenant les combinaisons déjà tentées par l'ordinateur.
	 * 
	 */

	@SuppressWarnings("unchecked")
	protected void movePawns (ArrayList<Integer> combinaisonIA, ArrayList<ArrayList<Integer>> listCombinaison) {

		do {
			// Si J arrive a la valeur 3 -> Incrémente la position de I;
			if (moveJ > GameOptions.PAWNS - 1) {
				moveJ = 0;
				moveI++;	
			}
			// Si I arrive à 3 -> I = 0,la liste est shuffle pour permettre de nouvelles combinaisons.
			
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
	 * resultat affiche un String contenant le numéro de la tentative, la combinaison et le String soluc.
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
	 * Méthode qui affiche un String informant le joueur du résultat de sa dernière tentative.
	 * Le nombre de tentative (gameCounter), la combinaison testée, et le résultat avec les pions présents, et bien placés,
	 * ou seulement les nombres bien placés si il n'y a pas de nombre présent.
	 * @param secret
	 * Tableau d'Integer contenant le secret.
	 * @param combinaison
	 * Tableau d'Integer contenant la combinaison jouée.
	 * @param isPresent
	 * variable qui represente le nombre de pions mal placés.
	 * @param inPosition
	 * variable qui représente le nombre de pions bien placés.
	 * @return
	 * String contenant la tentative actuelle ainsi que sa combinaison, le nombre de pions présents et à la bonne position.
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
	 * Méthode qui affiche un String informant le joueur du résultat de sa dernière tentative.
	 * Le nombre de tentative (gameCounter), la combinaison testée, et le résultat avec les pions présents, et bien placés,
	 * ou seulement les nombres bien placés si il n'y a pas de nombre présent.
	 * 
	 * @param combinaisonIA
	 * ArrayList d'Integer contenant la combinaison de l'ordinateur.
	 * @param secret
	 * Tableau d'Integer contenant le secret.
	 * @param isPresent
	 * variable qui represente le nombre de pions mal placés.
	 * @param inPosition
	 * variable qui représente le nombre de pions bien placés.
	 * @return
	 * String contenant la tentative actuelle ainsi que sa combinaison, le nombre de pions présents et à la bonne position.
	 */

	protected String resultat(ArrayList<Integer> combinaisonIA,int isPresent, int inPosition) {
		String resultat = "";

		if (isPresent != 0)
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || Résultat : "+ isPresent + " présent(s), " + inPosition + " bonne(s) position(s) ";
		else
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || Résultat : "+ inPosition + " bonne(s) position(s) ";
		return resultat;
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


