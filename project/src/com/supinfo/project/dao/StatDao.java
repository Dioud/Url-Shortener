package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.Stat;

/**
 * Classe de gestion des op�rations li�es � la persistence des entit�s Url.
 */
public interface StatDao {
	
	/**
	 * Ajoute un Url en base de donn�e.
	 * @param Url
	 * 			Url � ajouter en base.
	 * @return Le Url ajout� en base.
	 */
	Stat addStat(Stat p);
	
	/**
	 * Retourne le Url sp�cifi�e par l'ID pass� en param�tre.
	 * @param id
	 * 			Cl� primaire du Url � retrouver.
	 * @return Url associ� � l'ID sp�cifi�.
	 */
	Stat findStatById(Long id);
	
	/**
	 * @return L'ensemble des Url en base.
	 */
	
	/**
	 * Supprime un Url dont l'ID est l'ID sp�cifi�.
	 * @param id
	 * 			Cl� primaire du produit � supprimer.
	 */
	void removeStat(Long id);

	List<Stat> findStatByUrlId(Long idUrl);
		
}
