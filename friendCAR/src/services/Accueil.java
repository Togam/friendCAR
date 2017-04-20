package services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Commentaire;
import model.Statut;
import model.User;
import persistence.AccesDB;
import persistence.DBconfig;

/**
 * @author Margot
 *
 */
@Path("accueil")
public class Accueil {

	private User user = DBconfig.getInst().getUser();

	/**
	 * Renvoie la page d'accueil complète après authentification
	 * 
	 * @return Page d'accueil
	 * @throws SQLException
	 */
	@GET
	@Produces("text/html")
	public String pageAccueil() throws SQLException {
		return displayHeader() + displayAddStatut() + displayListStatut() + displayResearchTool() + displayFriends()
				+ displayNotFriends();
	}

	/**
	 * L'entête de la page d'accueil (informations de la personne connectée)
	 * 
	 * @return entête d'accueil
	 */
	@Produces("text/html")
	public String displayHeader() {
		String header = "<title>Friend CAR</title><b>" + user.getPseudo() + " : " + user.getNom() + " "
				+ user.getPrenom() + " - " + user.getMail() + "<hr></br>";
		return header;
	}

	/**
	 * Module permettant d'écrire et de publier un statut
	 * 
	 * @return
	 */
	@Produces("text/html")
	public String displayAddStatut() {
		return "<form action=\"ajoutStatut\" method=\"POST\">"
				+ "<label for=\"Statut\">Publier un statut : </label><br><input name=\"statut\" type=\"text\" id=\"statut\" />"
				+ "<input type=\"submit\" value=\"Publier\" \n /> </form>";
	}

	/**
	 * Liste des 10 derniers statuts publié par la personne connectée ou ses
	 * amis
	 * 
	 * @return affichage de la liste des 10 statuts
	 * @throws SQLException
	 */
	@Produces("text/html")
	public String displayListStatut() throws SQLException {
		List<Statut> listStatut = AccesDB.getTenLastStatuts(user);
		String str = "<b>What's up geeks ?  </b><br><br>";
		for (Statut st : listStatut) {
			str += "<form action=\"ajoutCom\" method=\"POST\"> <table style=\"border:1px dotted black;\">" + "<tr><td>"
					+ st.getUser() + "   " + st.getPubli() + "</td></tr>" + "<tr><td>" + st.getContenu()
					+ " </td></tr><tr><td><b>Commentaires</b></td></tr>";
			for (Commentaire com : st.getCommentaires()) {
				str += "<tr><td>" + com.getPseudo() + " " + com.getPubli() + " : " + com.getContenu() + "</td></tr>";
			}
			str += "<tr><td><input type=\"text\" name=\"idStatut\" value=\"" + st.getId()
					+ "\" hidden /> <label for=\"Commentaire\">Commentaire :</label><input name=\"commentaire\""
					+ "type=\"text\" id=\"commentaire\" required />"
					+ "<input type=\"submit\" name=\"Commenter\" value=\"Commenter\" \n /></td></tr>"
					+ "</table> </form>";
		}
		return str;
	}

	/**
	 * Liste des amis ajoutés par la personne connectée
	 * 
	 * @return affichage des amis
	 * @throws SQLException
	 */
	@Produces("text/html")
	public String displayFriends() throws SQLException {
		List<User> listFriends = AccesDB.getAllFriends(user);
		String str = "<b> Vos amis geek : </b><br>"
				+ "<form action=\"supprAmi\" method=\"POST\"><table style=\"border:1px dotted black;\">";
		if (listFriends.isEmpty()) {
			str += "<tr><td>  Vous ne suivez personne :(  </td></tr>";
		} else {
			for (User friend : listFriends) {
				str += "<tr><td><b>"+ friend.getPseudo() + " : </b>" + friend.getNom() + " "
						+ friend.getPrenom() + " <b>| derniere co : </b>" + friend.getLastco()
						+ "</td><td><td><b> | supprimer : </b><input type=\"submit\" value=\"" + friend.getPseudo() + "\" name=\"idFriend\"></td></tr>";
			}
		}
		str += "</table></form>";
		return str;
	}

	/**
	 * Liste des utilisateurs que la personne connectée n'a pas (encore) suivi
	 * 
	 * @return affichage des utilisateurs qui ne sont pas (encore) ami avec la
	 *         personne connectée
	 * @throws SQLException
	 */
	@Produces("text/html")
	public String displayNotFriends() throws SQLException {
		List<User> listNotFriends = AccesDB.getAllUserNotFriends(user);
		String str = "<b> Vous connaissez peut-etre ?</b><br>"
				+ "<form action=\"ajoutAmi\" method=\"POST\"><table style=\"border:1px dotted black;\">";
		if (listNotFriends.isEmpty()) {
			str += "<tr><td>  Felicitation vous suivez tout le monde :D  </td></tr>";
		} else {
			for (User notFriend : listNotFriends) {
				str += "<tr><td><b>"+ notFriend.getPseudo() + " : </b>" + notFriend.getNom() + " "
						+ notFriend.getPrenom() + "</td><td> <b>| ajouter : </b><input type=\"submit\" value=\"" + notFriend.getPseudo() + "\" name=\"idFriend\"></td></tr>";
			}
		}
		str += "</table></form>";
		return str;
	}

	/**
	 * Module de recherche d'un ami par son pseudo
	 * 
	 * @return la barre de recherche
	 * @throws SQLException
	 */
	@Produces("text/html")
	public String displayResearchTool() throws SQLException {
		return "<form action=\"accueil/recherche\" method=\"POST\">"
				+ "<label for=\"pseudo\">Pseudo d'un geek : </label><input name=\"pseudo\" type=\"text\" id=\"pseudo\" />"
				+ "<input type=\"submit\" value=\"Rerchercher\" /> </form>";
	}

}
