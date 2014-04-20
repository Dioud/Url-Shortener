package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.Url;

/**
 * Classe de gestion des opérations liées à la persistence des entités Url.
 */
public interface UrlDao {
	
	/**
	 * Ajoute un Url en base de donnée.
	 * @param Url
	 * 			Url à ajouter en base.
	 * @return Le Url ajouté en base.
	 */
	Url addUrl(Url p);
	
	/**
	 * Retourne le Url spécifiée par l'ID passé en paramètre.
	 * @param id
	 * 			Clé primaire du Url à retrouver.
	 * @return Url associé à l'ID spécifié.
	 */
	Url findUrlById(Long id);
	void enabledisableById(Long id, Boolean enable);
	
	/**
	 * @return L'ensemble des Url en base.
	 */
	List<Url> getAllUrls();
	
	/**
	 * Supprime un Url dont l'ID est l'ID spécifié.
	 * @param id
	 * 			Clé primaire du produit à supprimer.
	 */
	void removeUrl(Long id);

	
	List<Url> getUrlsByUser(Long idUser);
	Url findUrlByLink(String link);
	
}
