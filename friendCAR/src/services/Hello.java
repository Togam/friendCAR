package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {

	/**
	 * Retrieves representation of an instance of com.example.ServiceResource
	 *
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces("text/html")
	public String formulaireConnection() {
		return "<html> <title>" + "Friend CAR" + "</title>" + "<body><center><h1>FriendCAR, le reseau social des geeks!</h1></body>"
				+ "Nouveau ? \n <form action=\"inscription\"> "
				+ "<input type=\"submit\" value=\"Inscription\" \n /> </form>"
				+ "Deja membre ? \n <form action=\"co\">"
				+ "<input type=\"submit\" value=\"Connexion\" \n /> </form></center></html>";
	}

}