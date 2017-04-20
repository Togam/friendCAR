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
import persistence.AccesDB;
import persistence.DBconfig;

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
	public Response Connection(@QueryParam("pseudo") String pseudo, @QueryParam("motdepasse") String motdepasse) {
		if (validerCo(pseudo, motdepasse)) {
			response.addCookie(new javax.servlet.http.Cookie("cookie1", pseudo));
			// updateConnection(user);
			 persistence.DBconfig.getInst().setUser(user);
			return Response.seeOther(URI.create("/friendCAR/rest/accueil")).build();
		} else {
			return Response.seeOther(URI.create("/friendCAR/rest/co")).build();
		}
	}

	public boolean validerCo(final String pseudo, final String motdepasse) {
		String req = "SELECT * FROM user WHERE pseudo = ? AND motdepasse = ? ";
		Boolean validation = false;
		try {
			AccesDB.updateLastCo(pseudo);
			PreparedStatement ps = DBconfig.getConnection().prepareStatement(req);
			ps.setString(1, pseudo);
			ps.setString(2, motdepasse);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setPseudo(pseudo);
				user.setMdp(motdepasse);
				user.setNom(rs.getString(3));
				user.setPrenom(rs.getString(4));
				user.setMail(rs.getString(6));
				user.setLastco(rs.getDate(5));
				this.setUser(user);
				validation = true;
			}
			return validation;
		} catch (SQLException ex) {
			System.out.println("Pas d'utilisateur avec ces id dans la bdd : " + ex);
			return validation;
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
	public void setUser(final User user) {
		this.user = user;
	}

}
