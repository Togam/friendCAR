package model;

import java.util.Date;

/**
 * @author Margot
 *
 */
public class Commentaire {

	private int id;
	private String contenu;
	private Date publi;
	private String pseudo;
	private int idStatut;

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param contenu
	 * @param publi
	 * @param pseudo
	 * @param idStatut
	 */
	public Commentaire(final int id, final String contenu, final Date publi, final String pseudo, final int idStatut) {
		this.id = id;
		this.contenu = contenu;
		this.publi = publi;
		this.pseudo = pseudo;
		this.idStatut = idStatut;
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
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo
	 *            the pseudo to set
	 */
	public void setPseudo(final String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the idStatut
	 */
	public int getIdStatut() {
		return idStatut;
	}

	/**
	 * @param idStatut
	 *            the idStatut to set
	 */
	public void setIdStatut(final int idStatut) {
		this.idStatut = idStatut;
	}

}
