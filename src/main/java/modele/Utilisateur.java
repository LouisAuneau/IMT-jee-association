package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Classe représentant un utilisateur du système.
 */
public class Utilisateur implements Modele<Utilisateur>{
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

	public boolean persist() {
		// TODO
		return true;
	}

	public boolean update() {
		// TODO 
		return true;
	}

	public Utilisateur retrieve() {
		this.login = "TestUser";
		this.password = "TestPassword";
		return this;
	}

	public ArrayList<Utilisateur> retrieveAll() {
		ArrayList<Utilisateur> mock = new ArrayList<Utilisateur>();
		Utilisateur user1 = new Utilisateur("TestUser", "TestPassword");
		Utilisateur user2 = new Utilisateur("TestUser2", "TestPassword2");
		Utilisateur user3 = new Utilisateur("TestUser3", "TestPassword3");
		mock.add(user1);
		mock.add(user2);
		mock.add(user3);
		return mock;
	}
}
