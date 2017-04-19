package services;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.User;
import persistence.AccesDB;
import persistence.DBconfig;

@Path("ajoutStatut")
public class AjoutStatut {

	@Context
	private UriInfo context;

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	/**
	 * Méthode POST qui fait l'appel à l'insertion en base pour les statut
	 * 
	 * @param statut
	 * @return Revient sur la page d'accueil
	 */
	@POST
	@Produces("text/html")
	public Response ajoutStatut(@FormParam("statut") String statut) {
		AccesDB.insertStatut(statut, DBconfig.getInst().getUser());
		return Response.seeOther(URI.create("/friendCAR/rest/accueil")).build();
	}

}
