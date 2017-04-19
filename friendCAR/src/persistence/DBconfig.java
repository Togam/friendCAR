package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.User;

/**
 * @author six
 * 
 *         Classe DBconfig : classe permettant la configuration et la connexion
 *         à la bdd.
 */
public class DBconfig {

	static DBconfig inst;
	public static Connection c;
	private static String username;
	private static String password;
	private User user = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://webtp.fil.univ-lille1.fr/six";

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
	public static DBconfig getInst() {
		if (inst == null) {
			inst = new DBconfig();
		}
		return inst;
	}

	/**
	 * Démarrer la connexion
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void startConnection() throws SQLException, Exception {
		Class.forName(this.JDBC_DRIVER);
		this.c = DriverManager.getConnection(this.DB_URL, this.username, this.password);
	}

	public static Connection getConnection() {
		return c;
	}

	public static void setConnection(Connection c) {
		DBconfig.c = c;
	}

	/**
	 * @return l'utilisateur connecté
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
