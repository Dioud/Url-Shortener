package com.supinfo.project.dao;

import com.supinfo.project.dao.impl.JpaStatDao;
import com.supinfo.project.dao.impl.JpaUrlDao;
import com.supinfo.project.dao.impl.JpaUserDao;
import com.supinfo.project.util.PersistenceManager;

/**
 * Classe usine permettant de récupérer les Data Access Object des entités Category et Product.
 */
public class DaoFactory {

	
	/**
	 * @return Un nouveau gestionnaire de persistence pour les Product.
	 */
	public static UrlDao getUrlDao() {
		return new JpaUrlDao(PersistenceManager.getEntityManagerFactory());
	}
	
	
	public static UserDao getUserDao() {
		return new JpaUserDao(PersistenceManager.getEntityManagerFactory());
	}
	
	public static StatDao getStatDao() {
		return new JpaStatDao(PersistenceManager.getEntityManagerFactory());
	}
}
