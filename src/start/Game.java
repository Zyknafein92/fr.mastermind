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
 * Game est la classe qui regroupe les m�thodes communes, et les attributs
 * communs de tous les jeux. Ceci inclus les m�thodes de comparaison, les ajouts
 * et d�placements des pions, l'affichage du r�sultat.
 * 
 * @author Zyk
 *
 */

public abstract class Game  implements IObservable {

	//protected static final Logger LOGGER = LogManager.getRootLogger();
	
	Scanner sc = new Scanner(System.in);

	protected int gameCounter; // variable qui represente le nombre de tours jou�s.
	protected int isPresent;   // variable qui represente le nombre de pions pr�sents.
	protected int inPosition;  // variable qui represente les pions � la bonne position.
	protected int pawnsValue;  // variable qui represente la valeur actuelle du pion.
	protected int pawnsFound;  // variable qui represente le nombre de pion trouv�.
	protected int pawnsToAdd;  // variable qui represente le nombre de pion � ajouter.
	protected int moveI;       // variable qui represente l'indice de d�placement I.
	protected int moveJ;       // variable qui represente l'indice de d�placement J.
	protected int pos;         // variable qui represente une position.
	protected String[] soluc;  // String qui contient des indications  + - = dans le jeu.
	protected Integer[] combinaison; // Tableau d'Integer qui contient la combinaison.
	protected Integer[] secret;      // Tableau d'Integer qui contient le secret.
	protected Integer[] testColor;   // Tableau d'Integer qui contient la valeur panwsValue � tester.
	protected int[] resultat;        // Tableau d'Integer qui contient le r�sultat (indice 0 : inPosition / indice 1: isPresent).
	protected ArrayList<Integer> combinaisonIA; //ArrayList d'Integer qui contient la combinaison de l'IA pour le mode Mastermind Duel et Defenseur.
	protected ArrayList<ArrayList<Integer>> listCombinaison; //ArrayList des combinaisons test�es par l'ordinateur dans Mastermind Duel et Defenseur.
	protected ArrayList<IObserver> listObserver;
	protected boolean iswin; // boolean qui renvoie true si le joueur actuel gagne la partie.

	/**
	 * Constructeur de Game qui contient la majorit� des variables utilis�es lors des jeux.
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
	 *  M�thode qui g�n�re un nombre al�atoire, compris entre 0 et le nombre maximum autoris� .
	 *  
	 * @return Tableau d'Integer al�atoire.
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
	 * M�thode qui m�morise la saisit console l'utilisateur.
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
	 * compareChallenger compare les tableaux d'Integer secret et combinaison pour 
	 * mettre � jour le String Soluc qui affichera le r�sultat de la proposition dans le mode ChallengerPlusMoins.
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
	 * compareDefenseur est la m�thode de l'IA qui compare secret et combinaison pour mettre � jour la combinaison de l'ordinateur.
	 *  
	 * @param secret
	 * Tableau d'Integer qui repr�sente le secret.
	 * @param combinaison
	 * Tableau d'Integer qui repr�sente la combinaison.
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

	// M�thode des jeux MasterMind



	/**
	 * M�thode qui compare les tableaux d'Integer secret et testColor.
	 * La m�thode incr�mente la valeur pawnsToAdd � chaque �galit�.
	 * 
	 * @param secret
	 * Tableau d'Integer contenant le secret.
	 * @param testColor
	 * Tableau d'Integer qui contient la valeur panwsValue � tester.
	 * @return
	 * Renvois pawnsToAdd qui est le nombre de pion � ajouter � la combinaisonIA.
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
	 * M�thode qui compare le tableau d'Integer secret, et le tableau d'Integer combinaison.
	 * A chaque fois que la combinaison est �gale au secret, la valeur inPosition est incr�ment�e,
	 * Si la valeur est pr�sente, mais n'est pas � la bonne position, isPresent est incr�ment�e.
	 * 
	 * @param secret
	 *  Tableau d'Integer contenant le secret.
	 * @param combinaison
	 * Tableau d'Integer qui repr�sente la combinaison.
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
	 * M�thode qui compare le tableau d'Integer secret, et l'ArrayList d'Integer combinaisonIA.
	 * A chaque fois que la combinaison est �gale au secret, la valeur inPosition est incr�ment�e,
	 * Si la valeur est pr�sente, mais n'est pas � la bonne position, isPresent est incr�ment�e.
	 * La cr�ation d'un Hashset permet d'�viter les doubles ajouts en cas de pr�sence d'un nombre similaire.
	 * 
	 * @param secret
	 *  Tableau d'Integer contenant le secret.
	 * @param combinaisonIA
	 * ArrayList d'Integer qui repr�sente la combinaison.
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
	 * addToCombinaison remplace � chaque fois qu'un pion est pr�sent ou � la bonne position repr�sent�e par pawnsFound, 
	 * un autre pion � la valeur actuelle.
	 * Le pion est ajout� � l'indice de "pos" � la valeur actuelle. 
	 * A chaque ajout, la valeur de pos est incr�ment�e ainsi que le nombre de pion trouv�.
	 * 
	 * @param combinaisonIA
	 *  ArrayList qui contient la combinaison de l'ordinateur.
	 *  
	 * @param pawnsValue
	 * Int qui repr�sente la valeur de la valeur du pion actuelle.
	 * 
	 * @param panwsFound
	 * Nombre de pion �tant pr�sent ou � la bonne position � ajouter.
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
	 * movePawns modifie l'emplacement des pions de la position I � la position J pour trouver la bonne position des pions.
	 * A chaque d�placement de pion, J est incr�ment�.
	 * 
	 * @param combinaisonIA
	 * ArrayList qui contient la combinaison de l'ordinateur.
	 * 
	 * @param listCombinaison
	 * ArrayList contenant les combinaisons d�j� tent�es par l'ordinateur.
	 * 
	 */

	@SuppressWarnings("unchecked")
	protected void movePawns (ArrayList<Integer> combinaisonIA, ArrayList<ArrayList<Integer>> listCombinaison) {

		do {
			// Si J arrive a la valeur 3 -> Incr�mente la position de I;
			if (moveJ > GameOptions.PAWNS - 1) {
				moveJ = 0;
				moveI++;	
			}
			// Si I arrive � 3 -> I = 0,la liste est shuffle pour permettre de nouvelles combinaisons.
			
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
	 * resultat affiche un String contenant le num�ro de la tentative, la combinaison et le String soluc.
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
	 * M�thode qui affiche un String informant le joueur du r�sultat de sa derni�re tentative.
	 * Le nombre de tentative (gameCounter), la combinaison test�e, et le r�sultat avec les pions pr�sents, et bien plac�s,
	 * ou seulement les nombres bien plac�s si il n'y a pas de nombre pr�sent.
	 * @param secret
	 * Tableau d'Integer contenant le secret.
	 * @param combinaison
	 * Tableau d'Integer contenant la combinaison jou�e.
	 * @param isPresent
	 * variable qui represente le nombre de pions mal plac�s.
	 * @param inPosition
	 * variable qui repr�sente le nombre de pions bien plac�s.
	 * @return
	 * String contenant la tentative actuelle ainsi que sa combinaison, le nombre de pions pr�sents et � la bonne position.
	 */

	protected String resultat(Integer[] combinaison, int isPresent, int inPosition) {
		String resultat = "";

		if (isPresent != 0)
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + Arrays.toString(combinaison) + " || R�sultat : "+ isPresent + " pr�sent(s), " + inPosition + " bonne(s) position(s) ";
		else
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + Arrays.toString(combinaison) + " || R�sultat : "+ inPosition + " bonne(s) position(s) ";
		return resultat;
	}

	/**
	 * M�thode qui affiche un String informant le joueur du r�sultat de sa derni�re tentative.
	 * Le nombre de tentative (gameCounter), la combinaison test�e, et le r�sultat avec les pions pr�sents, et bien plac�s,
	 * ou seulement les nombres bien plac�s si il n'y a pas de nombre pr�sent.
	 * 
	 * @param combinaisonIA
	 * ArrayList d'Integer contenant la combinaison de l'ordinateur.
	 * @param secret
	 * Tableau d'Integer contenant le secret.
	 * @param isPresent
	 * variable qui represente le nombre de pions mal plac�s.
	 * @param inPosition
	 * variable qui repr�sente le nombre de pions bien plac�s.
	 * @return
	 * String contenant la tentative actuelle ainsi que sa combinaison, le nombre de pions pr�sents et � la bonne position.
	 */

	protected String resultat(ArrayList<Integer> combinaisonIA,int isPresent, int inPosition) {
		String resultat = "";

		if (isPresent != 0)
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || R�sultat : "+ isPresent + " pr�sent(s), " + inPosition + " bonne(s) position(s) ";
		else
			resultat += "Tentative(s) : " + gameCounter + "  | Proposition -> " + combinaisonIA + " || R�sultat : "+ inPosition + " bonne(s) position(s) ";
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


