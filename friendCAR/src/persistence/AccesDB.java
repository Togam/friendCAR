package persistence;

import static persistence.DBconfig.c;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

/**
 * @author Margot
 *
 */
public class AccesDB {

	/**
	 * Insérer un statut en base
	 * 
	 * @param statut
	 * @param user
	 */
	public static void insertStatut(String statut, User user) {
		try {
			String req = "INSERT INTO statut (pseudo, contenu, temps_publi) VALUES (?,?,NOW())";
			java.sql.PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, statut);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("erreur lors de l'insertion du statut en base : " + e);
		}
	}

}
