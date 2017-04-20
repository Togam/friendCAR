package persistence;

import static persistence.DBconfig.c;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Commentaire;
import model.Statut;
import model.User;

/**
 * @author Margot
 *
 */
/**
 * @author Margot
 *
 */
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
				statut.setCommentaires(getAllComByStatut(statut));
				list.add(statut);
			}
		} catch (SQLException e) {
			System.out.println("erreur lors de la récupération des statuts en base : " + e);
		}
		return list;
	}

	/**
	 * Insérer un commentaire en base
	 * 
	 * @param commentaire
	 * @param user
	 */
	public static void insertCom(final String commentaire, final User user, final int idStatut) {
		try {
			String req = "INSERT INTO commentaire (pseudo, contenu, temps_publi, id_statut) VALUES (?,?,NOW(),?)";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, commentaire);
			ps.setInt(3, idStatut);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("erreur lors de l'insertion du commentaire en base : " + e);
		}
	}

	/**
	 * Méthode qui permet de récupérer tous les commentaires d'un statut
	 * 
	 * @param statut
	 * @return la liste des commentaires du statut concerné
	 */
	public static List<Commentaire> getAllComByStatut(final Statut statut) {
		List<Commentaire> list = new ArrayList<Commentaire>();
		try {
			String req = "select * from commentaire where id_statut=? ORDER BY temps_publi ASC";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setInt(1, statut.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Commentaire com = new Commentaire(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
						rs.getInt(5));
				list.add(com);
			}
		} catch (SQLException e) {
			System.out.println("erreur lors de la récupération des statuts en base : " + e);
		}
		return list;
	}

	/**
	 * Méthode permettant de crée un objet User à partir des données récupérées
	 * en base grâce au pseudo passé en paramètre
	 * 
	 * @param pseudo
	 * @return l'objet utilisateur correspondant au pseudo
	 */
	public static User getUserByPseudo(final String pseudo) {
		User user = new User();
		try {
			String req = "select * from user where pseudo=?";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, pseudo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setPseudo(pseudo);
				user.setNom(rs.getString(3));
				user.setPrenom(rs.getString(4));
				user.setMail(rs.getString(6));
				user.setLastco(rs.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("erreur lors de la récupération des données de l'user en base : " + e);
		}
		return user;
	}

	/**
	 * Méthode permettant de récupérer tous les utilisateurs amis avec
	 * l'utilisateur en param
	 * 
	 * @param user
	 * @return liste d'ami
	 */
	public static List<User> getAllFriends(final User user) {
		List<User> list = new ArrayList<User>();
		try {
			String req = "select pseudo2 from est_ami where pseudo1=?";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User friend = getUserByPseudo(rs.getString(1));
				list.add(friend);
			}
		} catch (SQLException e) {
			System.out.println("erreur lors de la récupération des amis en base : " + e);
		}
		return list;
	}

	/**
	 * Méthode qui récupère tous les utilisateurs qui ne sont pas ami avec
	 * l'utilisateur passé en param
	 * 
	 * @param user
	 * @return la liste des utilisateurs non ami
	 */
	public static List<User> getAllUserNotFriends(final User user) {
		List<User> list = new ArrayList<User>();
		try {
			String req = "select pseudo from user where pseudo not in (select pseudo2 from est_ami where pseudo1=?) and pseudo not like ?";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, user.getPseudo());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User notfriend = getUserByPseudo(rs.getString(1));
				list.add(notfriend);
			}
		} catch (SQLException e) {
			System.out.println("erreur lors de la récupération des non-amis en base : " + e);
		}
		return list;
	}

	/**
	 * Supprime le lien d'amitié entre l'utilisateur et un ami choisi en base
	 * 
	 * @param user
	 * @param idFriend
	 */
	public static void deleteFriend(final User user, final String idFriend) {
		try {
			String req = "delete from est_ami where pseudo1=? and pseudo2=?";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, idFriend);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("erreur lors de la suppresion du lien d'amitié en base : " + e);
		}
	}

	/**
	 * Supprime le lien d'amitié entre l'utilisateur et un ami choisi en base
	 * 
	 * @param user
	 * @param idFriend
	 */
	public static void insertFriend(final User user, final String idNotFriend) {
		try {
			String req = "insert into est_ami VALUES (?,?)";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user.getPseudo());
			ps.setString(2, idNotFriend);
			System.out.println(idNotFriend);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("erreur lors de l'insertion du lien d'amitié en base : " + e);
		}
	}

	/**
	 * Méthode qui permet de savoir si la personne 1 a ajouté la personne 2
	 * 
	 * @param user1
	 * @param user2
	 * @return booléen d'amitié ou non
	 */
	public static boolean findFriendship(final User user1, final User user2) {
		Boolean friendship = false;
		try {
			String req = "select * from est_ami where pseudo1=? and pseudo2=?";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, user1.getPseudo());
			ps.setString(2, user2.getPseudo());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				friendship = true;
			}
		} catch (SQLException e) {
			System.out.println("erreur lors de la recherche du lien d'amitié en base : " + e);
		}
		return friendship;
	}

	/**
	 * Met à jour la date de la dernière connexion
	 * 
	 * @param user
	 */
	public static void updateLastCo(final String pseudo) {
		try {
			String req = "update user set derniereco=NOW() where pseudo=?";
			PreparedStatement ps = c.prepareStatement(req);
			ps.setString(1, pseudo);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("erreur lors de la maj de la last co: " + e);
		}
	}

}
