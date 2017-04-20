package model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Margot
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 6963156678736434987L;
	private String pseudo;
	private String mail;
	private String motdepasse;
	private String nom;
	private String prenom;
	private User recherche;
	private Date lastco;

	public User() {
	}

	/**
	 * @param pseudo
	 * @param mail
	 */
	public User(final String pseudo, final String mail) {
		this.pseudo = pseudo;
		this.mail = mail;
	}

	/**
	 * @param pseudo
	 * @param pwd
	 * @param mail
	 */
	public User(final String pseudo, final String motdepasse, final String mail) {
		this.pseudo = pseudo;
		this.mail = mail;
		this.motdepasse = motdepasse;
	}

	/**
	 * @return
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo
	 */
	public void setPseudo(final String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 */
	public void setMail(final String mail) {
		this.mail = mail;
	}

	/**
	 * @return
	 */
	public String getMdp() {
		return motdepasse;
	}

	/**
	 * @param mdp
	 */
	public void setMdp(final String mdp) {
		this.motdepasse = mdp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ("Pseudo : " + this.pseudo + "\nmail: " + this.mail);
	}

	/**
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * @return prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 */
	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the recherche
	 */
	public User getRecherche() {
		return recherche;
	}

	/**
	 * @param recherche
	 *            the recherche to set
	 */
	public void setRecherche(final User recherche) {
		this.recherche = recherche;
	}

	/**
	 * @return the lastco
	 */
	public Date getLastco() {
		return lastco;
	}

	/**
	 * @param lastco the lastco to set
	 */
	public void setLastco(final Date lastco) {
		this.lastco = lastco;
	}
}