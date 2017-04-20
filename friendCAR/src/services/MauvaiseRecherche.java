package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("failure")
public class MauvaiseRecherche {

	/**
	 * Retrieves representation of an instance of com.example.ServiceResource
	 *
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces("text/html")
	public String formulaireConnection() {
		return "<title>Friend CAR</title><form action=\"accueil\"> <b>Aucun geek ne possede ce pseudo ! <b>"
				+ "<br><input type=\"submit\" value=\"Retour Accueil\" /></form>";
	}

}