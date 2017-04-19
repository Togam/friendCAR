package persistence;

import static persistence.DBconfig.c;

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
			String req = "INSERT INTO statut (pseudo, contenu, temps_publi) VALUES (?,?,?)";
			java.sql.PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, statut);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			ps.setDate(3, sqlDate);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("erreur lors de l'insertion du statut en base : " + e);
		}
	}

}
