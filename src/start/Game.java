package start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

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
	
	protected String[] Soluc = new String[Board.pawns];
	protected Integer[] combinaison = new Integer[Board.pawns];
	protected ArrayList<Integer> combinaisonIA = new ArrayList<Integer>();
	protected ArrayList<ArrayList<Integer>> listCombinaison = new ArrayList<ArrayList<Integer>>();
	protected ArrayList<ArrayList<Integer>> tryCombinaisonBot = new ArrayList<ArrayList<Integer>>();
	protected Integer[] secret = new Integer[Board.pawns];
	protected Integer[] testColor = new Integer[Board.pawns];
	protected boolean iswin = false;
	protected int isPresent = 0;
	protected int inPosition = 0;
	protected ArrayList<IObserver> listObserver = new ArrayList<IObserver>();
	Scanner sc = new Scanner(System.in);
	protected int pos = 0;
	protected int color = 1;
	protected boolean check = false;
	protected int ballFound = 0;

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

		for (int i = 0; i < Board.pawns; i++) {
			if (secret[i] < combinaison[i]) {
				Soluc[i] = "" + "-";
			} else if (secret[i] > combinaison[i]) {
				Soluc[i] = "" + "+";
			} else {
				Soluc[i] = "" + "=";
			}
		}
	}

	/**
	 * 
	 * compareDefenseur est la méthode de l'IA qui compare secret et combinaison pour mettre à jour la combinaison de l'ordinateur
	 *  et affiche le résultat de la tentative dans le String Soluc
	 *  
	 * @param secret
	 * Tableau d'Integer qui représente le secret.
	 * @param combinaison
	 * Tableau d'Integer qui représente la combinaison.
	 * 
	 */

	public void compareDefenseur(Integer[] secret, Integer[] combinaison) {

		for (int i = 0; i < combinaison.length; i++) {
			if (secret[i] > combinaison[i]) {
				combinaison[i]++;
			}
			if (secret[i] < combinaison[i]) {
				combinaison[i]--;
			}
			if (secret[i] < combinaison[i]) {
				Soluc[i] = "" + "-";
			} else if (secret[i] > combinaison[i]) {
				Soluc[i] = "" + "+";
			} else {
				Soluc[i] = "" + "=";
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
		for (int i = 0; i < Board.pawns; i++) {
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

		for (int i = 0; i < Board.pawns; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == combinaison[j]) {
					ispresent++;
					found = true;
				}
				j++;
			} while (j < Board.pawns && !found);
		}
		return ispresent - compareInposition(secret, combinaison);
	}

	/**
	 * 
	 * compareInpositionIA est la méthode qui compare le secret du joueur et la combinaison de l'ordinateur a l'indice i. 
	 * Si le pion de la combinaison est à la bonne position par rapport au secret, il incrémente la valeur inposition.
	 * 
	 * @param secret
	 * Tableau d'Integer qui représente le secret.
	 * @param combinaison
	 * ArrayList qui contient la combinaison.
	 * @return
	 * Retourne le nombre de pion à la bonne position.
	 * 
	 */
	
	protected int compareInpositionIA(Integer[] secret, ArrayList<Integer> combinaisonIA) {

		int inposition = 0;
		for (int i = 0; i < Board.pawns; i++) {
			if (combinaisonIA.get(i).equals(secret[i])) {
				inposition++;
			}
		}
		return inposition;
	}

	/**
	 * 
	 * comparePresentIA est la méthode qui compare le secret du joueur et la combinaison de l'ordinateur a l'indice i. 
	 * Si le pion de la combinaison est présent dans le secret, il incrémente la valeur ispresent et passe au pion suivant.
	 * 
	 * @param secret
	 * Tableau d'Integer qui représente le secret.
	 * @param combinaisonIA
	 * ArrayList qui contient la combinaison de l'ordinateur.
	 * @return
	 * Retourne le nombre de pion présent après soustraction avec le nombre de pion à la bonne position.
	 * 
	 */
	
	protected int comparePresentIA(Integer[] secret, ArrayList<Integer> combinaisonIA) {
		int ispresent = 0;
		boolean found = false;

		for (int i = 0; i < Board.pawns; i++) {
			int j = 0;
			found = false;
			do {
				if (secret[i] == combinaisonIA.get(j)) {
					ispresent++;
					found = true;
				}
				j++;
			} while (j < Board.pawns && !found);
		}
		return ispresent - compareInpositionIA(secret, combinaisonIA);
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
	
	protected void addToCombinaison (ArrayList<Integer> combinaisonIA, int color, int ballcolor) {

		int x = 0;
		while(ballcolor > x) {
			combinaisonIA.set(pos,color);
			check = true;
			x++;
			ballFound++;
			pos++;
		}
	}

	/**
	 * moveBallCombinaison compare les valeurs entre la combinaisonIA, 
	 * et le secret puis déplace les pions en fonction des résultats entre l'indice i et j.
	 * 
	 * A chaque tour, la combinaison est ajoutée à une liste de combinaison afin d'éviter qu'elle soit tirée à nouveau.
	 * Si elle est déjà présente dans la liste des combinaisons, la JVM refait un swap.
	 * 
	 * La valeur de tried est incrémenté a chaque tentative et le résultat est affiché dans la console.
	 * 
	 * @param combinaisonIA
	 *  ArrayList qui contient la combinaison de l'ordinateur.
	 *  
	 * @param secret
	 * Tableau d'Integer qui contient le secret.
	 * 
	 * @param listCombinaison
	 * ArrayList des différentes combinaisons proposées par l'ordinateur
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	protected void moveBallCombinaison(ArrayList<Integer> combinaisonIA, Integer[] secret, ArrayList<ArrayList<Integer>>listCombinaison) {

		for (int i = 0; i < Board.pawns; i++) {
			for (int j = 0; j < Board.pawns; j++) {
				if (combinaisonIA.get(i) == secret[i]) {
				} else {
					listCombinaison.add((ArrayList<Integer>) combinaisonIA.clone());
					Collections.swap(combinaisonIA, i, j);
					if (!listCombinaison.contains(combinaisonIA)) {
						System.out.println(Board.tried);
						System.out.println(resultat(combinaisonIA, secret));
						Board.tried++;
					} else {
						Collections.swap(combinaisonIA, i, j);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void moveBallCombinaisonDuelMastermind(ArrayList<Integer> combinaisonIA, Integer[] secret, ArrayList<ArrayList<Integer>>listCombinaison) {
		
		for (int i = 0; i < Board.pawns; i++) {
			for (int j = 0; j < Board.pawns; j++) {
				if (combinaisonIA.get(i) == secret[i]) {
				} else {
					listCombinaison.add((ArrayList<Integer>)combinaisonIA.clone());
					Collections.swap(combinaisonIA, i, j);
					if (!listCombinaison.contains(combinaisonIA)) {
						tryCombinaisonBot.add((ArrayList<Integer>)combinaisonIA.clone());
					} else {
						Collections.swap(combinaisonIA, i, j);
					}
				}
			}
		}
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
	
	protected boolean iswin(Integer[] secret, Integer[] combinaison) {
		if (Arrays.equals(secret, combinaison))
			iswin = true;
		else
			iswin = false;
		return iswin;
	}

	/**
	 * 
	 * Boolean qui compare le secret et la combinaison.
	 * Si il est retourne la valeur True, la partie est gagnée
	 * 
	 * @param secret
	 * Tableau d'Integer qui contient le secret.
	 * 
	 * @param combinaisonIA
	 * Arraylist qui contient la combinaison.
	 * 
	 * @return
	 * Retourne la valeur du boolean
	 * 
	 */
	
	protected boolean iswin(Integer[] secret, ArrayList<Integer> combinaisonIA) {
		if (compareInpositionIA(secret, combinaisonIA) == Board.pawns)
			iswin = true;
		else
			iswin = false;
		return iswin;
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

		resultat += "Proposition ->" + Arrays.toString(combinaison) + "Résultat : " + Arrays.toString(Soluc);

		return resultat;
	}

	/**
	 * 
	 * resultat affiche un String contenant le nombre de tour, la proposition du joueur (combinaison), 
	 * le nombre de pion présent, à la bonne position.
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
	
	protected String resultat(Integer[] combinaison, Integer[] secret) {
		String resultat = "";

		if (comparePresent(secret, combinaison) != 0)
			resultat += "Tour :" + Board.tried + " | Proposition ->" + Arrays.toString(combinaison) + " || Résultat : "
					+ comparePresent(secret, combinaison) + " présent(s), " + compareInposition(secret, combinaison)
					+ " bonne(s) position(s) ";
		else
			resultat += "Boucle " + Board.tried + " | Proposition ->" + Arrays.toString(combinaison) + " || Résultat : "
					+ compareInposition(secret, combinaison) + " bonne(s) position(s) ";
		return resultat;
	}

	
	/**
	 * resultat affiche un String contenant le nombre de tour, la proposition de l'ordinateur(combinaisonIA), 
	 * le nombre de pion présent, à la bonne position.
	 * 
	 * @param combinaisonIA
	 * Arraylist qui contient la combinaison.
	 * 
	 * @param secret
	 * 
	 * Tableau d'Integer qui contient le secret.
	 * 
	 * @return
	 * Retourne le String resultat.
	 * 
	 */
	
	protected String resultat(ArrayList<Integer> combinaisonIA, Integer[] secret) {
		String resultat = "";

		if (comparePresentIA(secret, combinaisonIA) != 0)
			resultat += "Boucle " + Board.tried + " | Proposition ->" + combinaisonIA + " || Résultat : "
					+ comparePresentIA(secret, combinaisonIA) + " présent(s), "
					+ compareInpositionIA(secret, combinaisonIA) + " bonne(s) position(s) ";
		else
			resultat += "Boucle " + Board.tried + " | Proposition ->" + combinaisonIA + " || Résultat : "
					+ compareInpositionIA(secret, combinaisonIA) + " bonne(s) position(s) ";
		return resultat;
	}

	/**
	 * 
	 * @return le tableau String Soluc
	 * 
	 */
	
	protected String[] getSoluc() {
		return Soluc;
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
	 * @return le booleen iswin
	 * 
	 */
	
	protected boolean isIswin() {
		return iswin;
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
	 * @return int pos
	 * 
	 */
	
	protected int getPos() {
		return pos;
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
	 * Le String soluc mis à jour.
	 * 
	 */
	protected void setSoluc(String[] soluc) {
		Soluc = soluc;
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
	 * @param iswin
	 *  booleen iswin mis à jour.
	 *            
	 */
	
	protected void setisWin(boolean iswin) {
		this.iswin = iswin;
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
	 * @param pos
	 * int pos mis à jour.
	 *            
	 */
	
	protected void setPos(int pos) {
		this.pos = pos;
	}

	/**
	 * 
	 * @param color
	 * int color mis à jour.      
	 *            
	 */
	
	protected void setColor(int color) {
		this.color = color;
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
