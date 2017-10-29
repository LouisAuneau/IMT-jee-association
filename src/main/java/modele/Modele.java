package modele;

import java.util.ArrayList;

public interface Modele<M> {
	/**
	 * Persiste le mod�le dans la base de donn�es.
	 * @return boolean Vrai si l'objet a bien �t� persist�, faux sinon.
	 */
	public boolean persist();
	
	/**
	 * R�cup�re l'objet courant depuis la base de donn�es.
	 * @return M Object r�cup�r� depuis la base de donn�es.
	 */
	public M retrieve();
	
	/**
	 * R�cup�re tous les objects depuis la base de donn�es. 
	 * @return ArrayList<M> Liste des objets.
	 */
	public ArrayList<M> retrieveAll();
}
