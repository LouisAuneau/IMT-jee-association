package modele;

import java.util.ArrayList;

public interface Modele<M> {
	/**
	 * Persiste le modèle dans la base de données.
	 * @return boolean Vrai si l'objet a bien été persisté, faux sinon.
	 */
	public boolean persist();
	
	/**
	 * Met à jour le modele.
	 * @return boolean Vrai si le modele est mis à jour, faux sinon.
	 */
	public boolean update();
	
	/**
	 * Récupère l'objet courant depuis la base de données.
	 * @return M Object récupéré depuis la base de données.
	 */
	public M retrieve();
	
	/**
	 * Récupère tous les objects depuis la base de données. 
	 * @return ArrayList<M> Liste des objets.
	 */
	public ArrayList<M> retrieveAll();
}
