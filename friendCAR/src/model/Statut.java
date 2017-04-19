package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Statut {

	private int id;
	private String user;
	private Date publi;
	private String contenu;
	private List<Commentaire> commentaires;

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param user
	 * @param publi
	 * @param contenu
	 */
	public Statut(final int id, final String user, final Date publi, final String contenu) {
		this.id = id;
		this.user = user;
		this.publi = publi;
		this.contenu = contenu;
		this.commentaires = new ArrayList<Commentaire>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(final String user) {
		this.user = user;
	}

	/**
	 * @return the publi
	 */
	public Date getPubli() {
		return publi;
	}

	/**
	 * @param publi
	 *            the publi to set
	 */
	public void setPubli(final Date publi) {
		this.publi = publi;
	}

	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}

	/**
	 * @param contenu
	 *            the contenu to set
	 */
	public void setContenu(final String contenu) {
		this.contenu = contenu;
	}

	/**
	 * @return the commentaires
	 */
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	/**
	 * @param commentaires
	 *            the commentaires to set
	 */
	public void setCommentaires(final List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

}
