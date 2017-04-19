package persistance;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 * @author six
 * 
 *         Classe DBconfig : classe permettant la configuration et la connexion
 *         à la bdd.
 */
public class DBconfig {

	private static Connection c;
	private static String username;
	private static String password;

	/**
	 * @param username
	 */
	public static void setUsername(String username) {
		DBconfig.username = username;
	}

	/**
	 * @param password
	 */
	public static void setPassword(String password) {
		DBconfig.password = password;
	}

	/**
	 * Connection à la bdd
	 * 
	 * @return la connection Ã  la bdd
	 */
	public static Connection getConnection() {
		if (c == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");   
				c = DriverManager.getConnection("jdbc:mysql://webtp.fil.univ-lille1.fr/six", username, password);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return c;
	};
}
