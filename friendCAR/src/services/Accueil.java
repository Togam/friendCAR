package services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Commentaire;
import model.Statut;
import persistence.AccesDB;
import persistence.DBconfig;

/**
 * @author Margot
 *
 */
@Path("accueil")
public class Accueil {

	@GET
	@Produces("text/html")
	public String pageAccueil() throws SQLException {
		return displayAddStatut() + displayStatut();
	}

	@Produces("text/html")
	public String displayAddStatut() {
		return "<form action=\"ajoutStatut\" method=\"POST\">"
				+ "<label for=\"Statut\">Statut :</label><input name=\"statut\" type=\"text\" id=\"statut\" /><br />\n"
				+ "<input type=\"submit\" value=\"Publier\" \n /> </form>";
	}

	@Produces("text/html")
	public String displayStatut() throws SQLException {
		List<Statut> listStatut = AccesDB.getTenLastStatuts(DBconfig.getInst().getUser());
		String str = "";
		for (Statut st : listStatut) {
			str += "<form action=\"ajoutCom\" method=\"POST\"> <table <table style=\"border:1px dotted black;\">" + "<tr><td>"
					+ st.getUser() + "   " + st.getPubli() + "</td></tr>"
					+"<tr><td>" + st.getContenu() + " </td></tr><tr><td><b>Commentaires</b></td></tr>";
			// TODO : gestion des commentaires
			// for (Commentaire com : st.getCommentList()) {
			// str += "<tr><td>" + com.getAuteur() + " : " +
			// com.getCommentaire() + "</td></tr>";
			// }
			str += "<tr><td><input type=\"text\" name=\"idStatut\" value=\"" + st.getId()
					+ "\" hidden /> <label for=\"Commentaire\">Commentaire :</label><input name=\"commentaire\""
					+ "type=\"text\" id=\"commentaire\" required />"
					+ "<input type=\"submit\" name=\"Commenter\" value=\"Commenter\" \n /></td></tr>"
					+ "</table> </form> <br/>";
		}
		return str;
	}

}
