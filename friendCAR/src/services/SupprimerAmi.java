package services;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import persistence.AccesDB;
import persistence.DBconfig;

@Path("supprAmi")
public class SupprimerAmi {

	/**
	 * M�thode POST qui fait l'appel � la suppresion en base du lien d'amiti�
	 * entre l'utilisateur connect� et l'ami qu'il choisit
	 * 
	 * @param pseudo
	 *            de la personne � ajouter
	 * @return Revient sur la page d'accueil
	 */
	@POST
	@Produces("text/html")
	public Response ajoutAmi(@FormParam("idFriend") String pseudo) {
		AccesDB.deleteFriend(DBconfig.getInst().getUser(), pseudo);
		return Response.seeOther(URI.create("/friendCAR/rest/accueil")).build();
	}
	
}
