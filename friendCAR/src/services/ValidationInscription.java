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
@Path("valid_inscr")
public class ValidationInscription {

	private User user;

	@Context
	private UriInfo context;

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@GET
	public Response Inscription(@QueryParam("username") String pseudo, @QueryParam("password") String motdepasse, @QueryParam("password2") String motdepasse2) {
		if (validerInscription(pseudo, motdepasse, motdepasse2)) {
//			response.addCookie(new javax.servlet.http.Cookie("cookie1", pseudo));
			// updateConnection(user);
			// Persistence.PersistenceConnection.getInstance().setUser(user);
			return Response.seeOther(URI.create("friendCAR/rest/co")).build();
		} else {
			return Response.seeOther(URI.create("/friendCAR/rest/inscription")).build();
		}
	}

	public boolean validerInscription(String pseudo, String motdepasse, String mdp2) {
		String req = "SELECT * FROM user WHERE pseudo = ?";
		Boolean validation = true;
		try {
			PreparedStatement ps = DBconfig.getConnection().prepareStatement(req);
			ps.setString(1, pseudo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				validation = false;
			}
			if (!motdepasse.equals(mdp2)){
				validation = false;
			}
			return validation;
		} catch (SQLException ex) {
			System.out.println("erreur sql  : " + ex);
			return false;
		}
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	// TODO : update la last connexion ?

}
