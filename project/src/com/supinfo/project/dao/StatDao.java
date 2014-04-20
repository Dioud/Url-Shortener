package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.Stat;

/**
 * Classe de gestion des opérations liées à la persistence des entités Url.
 */
public interface StatDao {
	
	/**
	 * Ajoute un Url en base de donnée.
	 * @param Url
	 * 			Url à ajouter en base.
	 * @return Le Url ajouté en base.
	 */
	Stat addStat(Stat p);
	
	/**
	 * Retourne le Url spécifiée par l'ID passé en paramètre.
	 * @param id
	 * 			Clé primaire du Url à retrouver.
	 * @return Url associé à l'ID spécifié.
	 */
	Stat findStatById(Long id);
	
	/**
	 * @return L'ensemble des Url en base.
	 */
	
	/**
	 * Supprime un Url dont l'ID est l'ID spécifié.
	 * @param id
	 * 			Clé primaire du produit à supprimer.
	 */
	void removeStat(Long id);

	List<Stat> findStatByUrlId(Long idUrl);
		
}
