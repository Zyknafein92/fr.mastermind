package option;

public class GameOptions   {

private static final int MAXTRY = 15;
private static final int PANWS = 4;
private static final int MAXNUMBERS = 6;


/**
 * @return la valeur du nombre d'essai maximum autorise
 */

public static int getMaxtry() {
	return MAXTRY;
}

/**
 * @return le nombre de pion défini par l'utilisateur
 */

public static int getPanws() {
	return PANWS;
}

/**
 * @return le nombre maximum de chiffres utilises pour le jeu.
 */

public static int getMaxnumbers() {
	return MAXNUMBERS;
}
 
 

 
}
 
 
