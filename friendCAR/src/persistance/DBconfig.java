package persistance;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * @author six Classe ConnectionBDD : classe qui configure l'accès à la bdd.
 */
public class DBconfig {

	private static Connection c;
	private static String username;
	private static String password;

	/**
	 * @param username
	 *            permet de changer le nom de l'utilisateur dans le Main plutôt
	 *            que de l'écrire en dur dans le programme (problème de
	 *            sécurité)
	 */
	public static void setUsername(String username) {
		DBconfig.username = username;
	}

	/**
	 * @param password
	 *            permet de changer le mot de passe de l'utilisateur dans le
	 *            Main plutôt que de l'écrire en dur dans le programme (problème
	 *            de sécurité)
	 */
	public static void setPassword(String password) {
		DBconfig.password = password;
	}

	/**
	 * @return la connection à la bdd
	 * 
	 *         Méthode qui permet de se connecter à la base de données.
	 */
	public static Connection getConnection() {
		if (c == null) {
			try {
				c = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/six", username, password);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return c;
	};
}
