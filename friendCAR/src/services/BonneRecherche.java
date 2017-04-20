package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.User;
import persistence.AccesDB;

@Path("completed")
public class BonneRecherche {

	/**
	 * Retrieves representation of an instance of com.example.ServiceResource
	 *
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces("text/html")
	public String formulaireConnection() {
		User connecte = persistence.DBconfig.getInst().getUser();
		User recherche = persistence.DBconfig.getInst().getUser().getRecherche();
		if (AccesDB.findFriendship(connecte, recherche)) {
			return "<title>Friend CAR</title><form action=\"supprAmi\" method=\"POST\"> <b>Ce geek est deja dans vos amis  : </b><br><br><input type=\"text\" name=\"idFriend\" value=\""
					+ recherche.getPseudo() + "\" hidden /><b>" + recherche.getPseudo() + " : </b>" + recherche.getNom()
					+ " " + recherche.getPrenom() + " </td><td><input type=\"submit\" value=\"Supprimer\" \n /></form>";
		} else {
			return "<title>Friend CAR</title><form action=\"ajoutAmi\" method=\"POST\"> <b>Utilisateur trouve  : </b><br><br><input type=\"text\" name=\"idFriend\" value=\""
					+ recherche.getPseudo() + "\" hidden /><b>" + recherche.getPseudo() + " : </b>" + recherche.getNom()
					+ " " + recherche.getPrenom() + " </td><td><input type=\"submit\" value=\"Ajouter\" \n /></form>";
		}
	}

}