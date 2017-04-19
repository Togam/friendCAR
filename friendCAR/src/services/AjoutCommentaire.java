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

@Path("ajoutCom")
public class AjoutCommentaire {

	@Context
	private UriInfo context;

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	/**
	 * Méthode POST qui fait l'appel à l'insertion en base pour les commentaires
	 * 
	 * @param statut
	 * @return Revient sur la page d'accueil
	 */
	@POST
	@Produces("text/html")
	public Response ajoutCommentaire(@FormParam("commentaire") String commentaire, @FormParam("idStatut") int statut) {
		AccesDB.insertCom(commentaire, DBconfig.getInst().getUser(), statut);
		return Response.seeOther(URI.create("/friendCAR/rest/accueil")).build();
	}

}
