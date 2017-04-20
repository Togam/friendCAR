package services;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.User;
import persistence.AccesDB;

@Path("accueil/recherche")
public class RechercheAmi {

	@POST
	@Produces("text/html")
	public Response rechercheAmi(@FormParam("pseudo") String pseudo) {
		User recherche = AccesDB.getUserByPseudo(pseudo);
		if (recherche.getPseudo() == null) {
			return Response.seeOther(URI.create("/friendCAR/rest/failure")).build();
		} else {
			persistence.DBconfig.getInst().getUser().setRecherche(recherche);
			return Response.seeOther(URI.create("/friendCAR/rest/completed")).build();
		}
	}

}
