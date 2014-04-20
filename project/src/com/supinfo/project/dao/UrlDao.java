package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.Url;

/**
 * Classe de gestion des op�rations li�es � la persistence des entit�s Url.
 */
public interface UrlDao {
	
	/**
	 * Ajoute un Url en base de donn�e.
	 * @param Url
	 * 			Url � ajouter en base.
	 * @return Le Url ajout� en base.
	 */
	Url addUrl(Url p);
	
	/**
	 * Retourne le Url sp�cifi�e par l'ID pass� en param�tre.
	 * @param id
	 * 			Cl� primaire du Url � retrouver.
	 * @return Url associ� � l'ID sp�cifi�.
	 */
	Url findUrlById(Long id);
	void enabledisableById(Long id, Boolean enable);
	
	/**
	 * @return L'ensemble des Url en base.
	 */
	List<Url> getAllUrls();
	
	/**
	 * Supprime un Url dont l'ID est l'ID sp�cifi�.
	 * @param id
	 * 			Cl� primaire du produit � supprimer.
	 */
	void removeUrl(Long id);

	
	List<Url> getUrlsByUser(Long idUser);
	Url findUrlByLink(String link);
	
}
