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

	@GET
	@Produces("text/html")
	public String pageAccueil() throws SQLException {
		return displayHeader() + displayAddStatut() + displayListStatut()+displayFriends();
	}

	@Produces("text/html")
	public String displayHeader() {
		String header = "<title>Friend CAR</title><b>" + user.getPseudo() + " : " + user.getNom() + " "
				+ user.getPrenom() + " - " + user.getMail() + "<hr></br>";
		return header;
	}

	@Produces("text/html")
	public String displayAddStatut() {
		return "<form action=\"ajoutStatut\" method=\"POST\">"
				+ "<label for=\"Statut\">Publier un statut : </label><br><input name=\"statut\" type=\"text\" id=\"statut\" />"
				+ "<input type=\"submit\" value=\"Publier\" \n /> </form>";
	}

	@Produces("text/html")
	public String displayListStatut() throws SQLException {
		List<Statut> listStatut = AccesDB.getTenLastStatuts(user);
		String str = "<b>What's up geeks ?  </b><br><br>";
		for (Statut st : listStatut) {
			str += "<form action=\"ajoutCom\" method=\"POST\"> <table style=\"border:1px dotted black;\">"
					+ "<tr><td>" + st.getUser() + "   " + st.getPubli() + "</td></tr>" + "<tr><td>" + st.getContenu()
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

	@Produces("text/html")
	public String displayFriends() throws SQLException {
		List<User> listFriends = AccesDB.getAllFriends(user);
		String str = "<b> Vos amis : </b><br>"
				+ "<table style=\"border:1px dotted black;\">";
		for(User friend : listFriends){
			str +="<tr><td><b>"+friend.getPseudo()+" : </b>"+friend.getNom()+" "+friend.getPrenom()
				+"</td><tr><table>";
		}
		return str;
	}

	@Produces("text/html")
	public String displayNotFriends() throws SQLException {
		String str="";
		return str;
	}
}
