package services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import persistence.DBconfig;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

@Path("/inscription")
public class Inscription {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of ServiceResource
	 */
	public Inscription() {
		DBconfig co = new DBconfig();
		try {
			co.setUsername("six");
			co.setPassword("g6xgg1ya");
			co.startConnection();
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
	public String formulaireInscription() {
		try {
			return "<html>"
					+"<body>"
					+"<h1>Inscription</h1>" 
					+"<meta charset=\"utf-8\">"
					+"<div class=\"tab-content-inner\" data-content=\"signup\">"
					+"<form action=\"valid_inscr\">"
					+"<div class=\"col-md-12\">"
					+"<label for=\"username\">Pseudo : </label>"
					+"<input type=\"text\" class=\"form-control\" name=\"username\">"
					+"</div>"
					+"</br>"
					+"<div class=\"col-md-12\">"
					+"<label for=\"motdepasse\">Mot de Passe : </label>"
					+"<input type=\"password\" class=\"form-control\" name=\"password\">"
					+"</div>"
					+"</br>"
					+"<div class=\"col-md-12\">"
					+"<label for=\"password2\">Confirmez Mot de Passe : </label>"
					+"<input type=\"password\" class=\"form-control\" name=\"password2\">"
					+"</div>"
					+"</br>"
					+"<div class=\"col-md-12\">"
					+"<label for=\"lastname\">Nom : </label>"
					+"<input type=\"text\" class=\"form-control\" name=\"lastname\">"
					+"</div>"
					+"</br>"
					+"<div class=\"col-md-12\">"
					+"<label for=\"firstname\">Prenom : </label>"
					+"<input type=\"text\" class=\"form-control\" name=\"firstname\">"
					+"</div>"
					+"</br>"
					+"<div class=\"col-md-12\">"
					+"<label for=\"mail\">Mail : </label>"
					+"<input type=\"text\" class=\"form-control\" name=\"mail\">"
					+"</div>"
					+"</br>"
					+"<div class=\"col-md-12\">"
					+"<input type=\"submit\" value=\"S'inscrire\" \n />"
					+"</div>"
					+"</form>"
					+"</div>"
					+"</body>"
					+"</html>";
		} catch (Exception e) {
			System.out.println(e);
			return "une erreur s'est produite lors de l'inscription";
		}
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