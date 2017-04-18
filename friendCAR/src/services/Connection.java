package services;

import persistance.DBconfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

@Path("/co")
public class Connection {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of ServiceResource
	 */
	public Connection() {
		DBconfig co = new DBconfig();
		try {
			co.setUsername("six");
			co.setPassword("g6xgg1ya");
			co.getConnection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Retrieves representation of an instance of com.example.ServiceResource
	 *
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces("text/html")
	public String formulaireConnection() {
		return "<html> <title>" + "Friend CAR" + "</title>" + "<body><h1>" + "Friend CAR" + "</body></h1>"
				+ "<form action=\"validation\"> <label for=\"pseudo\">Pseudo :</label><input name=\"pseudo\" type=\"text\" id=\"pseudo\" /><br />\n"
				+ "	<label for=\"password\">Mot de Passe :</label><input type=\"password\" name=\"password\" id=\"password\" />\n\n"
				+ "<input type=\"submit\" value=\"Submit\" \n /> </form></html>";
	}

	/**
	 * PUT method for updating or creating an instance of ServiceResource
	 *
	 * @param content
	 *            representation for the resource
	 */
	@PUT
	@Consumes(MediaType.TEXT_HTML)
	public void putHtml(String content) {
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces("text/plain")
	public String postHandler(String content) {
		return content;
	}
}