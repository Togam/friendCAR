package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Margot
 *
 */
@Path("accueil")
public class Accueil {

	@GET
	@Produces("text/html")
	public String pageAccueil() {
		return displayAddStatut();
	}

	@Produces("text/html")
	public String displayAddStatut() {
		return "<form action=\"ajoutStatut\" method=\"POST\">"
				+ "<label for=\"Statut\">Statut :</label><input name=\"statut\" type=\"text\" id=\"statut\" /><br />\n"
				+ "<input type=\"submit\" value=\"Publier\" \n /> </form>";
	}

}
