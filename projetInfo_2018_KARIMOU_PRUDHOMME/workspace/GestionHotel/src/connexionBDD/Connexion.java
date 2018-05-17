package connexionBDD;
/**
* Classe permettant de se connecter à la base de donnée
*
* @version 1.0
* @author Prud'homme Fiona, Karimou Samira
*/

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	/**
	 * 
	 */
	private final String DB_URL = "jdbc:postgresql://localhost:5432/gestionhotel";//url de la bdd
	private final String USER = "postgres";//nom d'utilisateur
	private final String PASS = "postgres";//mot de passe

	private static Connection connection; // l’objet Connection

	private Connexion() { // Constructeur prive ! Objet impossible a instancier
		try {
			
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver O.K.");
			
			
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("vous êtes connecté");
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	//methode qui retourne l’instance et qui la cree si elle n’existe pas encore
	public static Connection getInstance(){
		if(connection == null){
			new Connexion();
		}
		return connection;
	}


}
