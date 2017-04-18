package services;

import java.net.URI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import model.User;
import persistance.DBconfig;

/**
 * @author Margot
 *
 */
@Path("validation")
public class ValidationCo {

	private User user;
	
	@Context
    private UriInfo context;

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;
	
    @GET
	public Response Connection(@QueryParam("pseudo") String pseudo, @QueryParam("motdepasse") String mdp){
		if(validerCo(pseudo, mdp)){
			response.addCookie(new javax.servlet.http.Cookie("cookie1", pseudo));
//			updateConnection(user);
//	        Persistence.PersistenceConnection.getInstance().setUser(user);
	        return Response.seeOther(URI.create("friendCAR/rest/accueil")).build();		
		} else {
			return Response.seeOther(URI.create("/friendCAR/rest/co")).build();
		}
	}
    
    //TODO : update la last connexion ?

	public boolean validerCo(String pseudo, String motdepasse) {
		String req = "SELECT * FROM user WHERE pseudo = ? AND motdepasse = ? ";
		try {
			PreparedStatement ps = DBconfig.getConnection().prepareStatement(req);
			ps.setString(1, pseudo);
			ps.setString(2, motdepasse);
			ResultSet rs = ps.executeQuery();
			rs.next();
			User user = new User();
			user.setPseudo(pseudo);
			user.setMdp(motdepasse);
			this.user = user;
			return true;
		} catch (SQLException ex) {
			System.out.println("Pas d'utilisateur avec ces id dans la bdd : " + ex);
			return false;
		}
	}

}
