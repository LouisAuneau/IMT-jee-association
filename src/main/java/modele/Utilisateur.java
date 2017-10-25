package modele;

public class Utilisateur {
	private String login;
	private String password;
	
	/**
	 * Créer un utilisateur avec un nom d'utilisateur.
	 * @param String login - Nom de l'utilisateur
	 * @param String password - Mot de passe de l'utilisateur
	 */
	public Utilisateur(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	/**
	 * Donne le login de l'utilisateur.
	 * @return String - Login de l'utilisateur
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Change le login de l'utilisateur,
	 * @param String login - Login de l'utilisateur
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Donne le mot de passe de l'utilisateur.
	 * @return String - Mot de passe de l'utilisateur
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Change le mot de passe de l'utilisateur.
	 * @param String password - Mot de passe de l'utilisateur
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
