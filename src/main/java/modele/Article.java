package modele;

import java.util.ArrayList;

/**
 * Classe représentant un article proposé par l'association.
 */
public class Article implements Modele<Article> {
	
	private String nom;
	private String description;
	private int quantite;
	
	/**
	 * Créer un article proposé par l'association.
	 * @param String nom Nom de l'article.
	 * @param String description Description de l'article.
	 * @param int quantite Quantité disponible.
	 */
	public Article(String nom, String description, int quantite) {
		super();
		this.nom = nom;
		this.description = description;
		this.quantite = quantite;
	}

	/**
	 * Donne le nom de l'article.
	 * @return String Nom de l'article.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Change le nom de l'article.
	 * @param String nom Nom de l'article.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Donne la description de l'article.
	 * @return String Description de l'article.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Change la description de l'article.
	 * @param String description Description de l'article.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Donne la quantité restante de l'article.
	 * @return int Quantité restante de l'article.
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * Change la quantité restante de l'article.
	 * @param int quantiteAchetee Quantité à enlever au stock.
	 */
	public void setQuantite(int quantiteAchetee) {
		if((this.quantite - quantiteAchetee) > 0) {
			this.quantite -= quantiteAchetee;
		}
	}

	public boolean persist() {
		// TODO
		return true;
	}

	public boolean update() {
		// TODO
		return true;
	}

	public Article retrieve() {
		return new Article("Test", "Article de test", 10);
	}

	public ArrayList<Article> retrieveAll() {
		ArrayList<Article> mock = new ArrayList<Article>();
		Article article1 = new Article("Test", "Article de test", 10);
		Article article2 = new Article("Test2", "Article de test2", 100);
		Article article3 = new Article("Test3", "Article de test3", 0);
		mock.add(article1);
		mock.add(article2);
		mock.add(article3);
		return mock;
	}
}
