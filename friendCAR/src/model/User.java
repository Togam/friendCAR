package model;

import java.io.Serializable;

/**
 * @author Margot
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 6963156678736434987L;
	protected String pseudo;
	protected String mail;
	protected String motdepasse;
	protected int idUser;
//	 protected ArrayList<Friend> friends = new ArrayList();

	public User() {

	}

	/**
	 * @param id
	 * @param pseudo
	 * @param mail
	 */
	public User(int id, String pseudo, String mail) {
		this.idUser = id;
		this.pseudo = pseudo;
		this.mail = mail;
	}

	/**
	 * @param id
	 * @param pseudo
	 * @param pwd
	 * @param mail
	 */
	public User(int id, String pseudo, String motdepasse, String mail) {
		this.pseudo = pseudo;
		this.mail = mail;
		this.motdepasse = motdepasse;
	}

	/**
	 * @param pseudo
	 * @param pwd
	 * @param mail
	 */
	public User(String pseudo, String motdepasse, String mail) {
		this.pseudo = pseudo;
		this.mail = mail;
		this.motdepasse = motdepasse;
	}

	/*
	 * public ArrayList<Friend> getFriends() { return friends; } public void
	 * setFriends(ArrayList<Friend> friends) { this.friends = friends; }
	 */

	/**
	 * @return
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
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
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return
	 */
	public String getMdp() {
		return motdepasse;
	}

	/**
	 * @param pwd
	 */
	public void setMdp(String mdp) {
		this.motdepasse = mdp;
	}

	/**
	 * @return
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ("id: " + this.idUser + "\nPseudo : " + this.pseudo + "\nmail: " + this.mail);
	}
}