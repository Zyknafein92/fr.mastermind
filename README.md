                                                 #### MasterNumber ####
																								
 L'application contient deux jeux :
 
 - Le jeu PlusMoins :
 Le joueur ou l'ordinateur devra découvrir le secret de l'autre. Il va pour cela soumettre une combinaison qui sera comparé avec le secret.
 Chaque nombre est comparé, et vous obtiendrez un résultat sous la forme :
 
 + : si le chiffre est plus grand.
 - : si le chiffre est plus petit.
 = : si le chiffre est trouvé.
 
 
 - Le jeu Mastermind :
 Le joueur et l'ordinateur vont s'affronter pour découvrir le secret de l'autre. Une combinaison sera soumise et comparé au secret.
 Chaque nombre est comparé, et vous obtiendrez un résultat sous la forme :
 
 x présent(s) : si le chiffre est présent dans le secret.
 x bonne(s) position(s) : si le chiffre est à la bonne position.
 Un chiffre ne peut pas être présent et à la bonne position en même temps.
 
 
 Comment jouer ? 
 
 -> Téléchargez le fichier .zip
 -> Ouvrez le dossier contenant le fichier, et dézippez le en créant un nouveau fichier.
 -> Double-cliquez sur le fichier "MasterNumber.bat"
    Ce fichier ne contient aucun virus mais vous pouvez obtenir un message d'erreur de la part de Windows.
    Lancez en programme en cliquant sur "Executez quand même l'application"
    
    Modifier les paramètres de jeu :
    
    Si vous souhaitez modifier les paramètres de jeu, ouvrez le fichier "conf/config.properties".
    Vous verrez alors s'afficher :
    ## Nombre de tentative maximales ##
    MAX_TRY=15
    ## Nombre de pion ##
    PAWNS=4
    ## Valeur du pion maximal ##
    MAX_NUMBERS=8
    
    Pour modifier une valeur, vous devrez modifier le chiffre suivant la valeur que vous voulez modifier.
    Exemple :
    Une partie ayant 20 tentatives maximales, 6 pions, et dont la valeur maximale d'un pion peut-être 9.
    ## Nombre de tentative maximales ##
    MAX_TRY=20
    ## Nombre de pion ##
    PAWNS=6
    ## Valeur du pion maximal ##
    MAX_NUMBERS=9
    
    Sauvegarder, et quitter.
    Relancer le programme.
    Amusez-vous bien !
    
    Lancer le jeu en mode dev.
    
    Selectionnez et cliquer droit sur "MasterNumber.bat" et sur Renommer.
    Renommez le en "MasterNumber.txt" et ouvrez le avec NotePad ou fichier.txt.
    Recopier la ligne suivante : java -jar MasterNumber.jar dev
    Remplacer la ligne existante dans le fichier.
    Sauvegarder.
    
    Si vous souhaitez enlever le mode développeur, retirez "dev" en réitérant la méthode précédente.
    Sauvegarder.
