package persistence;

import static persistence.DBconfig.c;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Statut;
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
	public static void insertStatut(final String statut, final User user) {
		try {
			String req = "INSERT INTO statut (pseudo, contenu, temps_publi) VALUES (?,?,NOW())";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, statut);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("erreur lors de l'insertion du statut en base : " + e);
		}
	}

	/**
	 * Méthode permettant de récupérer les 10 derniers statuts présents pour
	 * l'utilisateur connecté
	 * 
	 * @param user
	 * @return liste des 10 derniers statuts
	 */
	public static List<Statut> getTenLastStatuts(final User user) {
		List<Statut> list = new ArrayList<Statut>();
		try {
			String req = "select * from (" + "(select id, pseudo, temps_publi, contenu FROM statut where pseudo=?) "
					+ "UNION ALL " + "(select id, pseudo, temps_publi, contenu FROM statut, est_ami "
					+ "where statut.pseudo = est_ami.pseudo2 and pseudo1=?)"
					+ ") as statuts ORDER BY temps_publi DESC LIMIT 10";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, user.getPseudo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Statut statut = new Statut(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4));
				// TODO : faire la gestion des commentaires ici
				list.add(statut);
			}
		} catch (SQLException e) {
			System.out.println("erreur lors de la récupération des statuts en base : " + e);
		}
		return list;
	}

}
