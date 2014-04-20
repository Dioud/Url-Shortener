package com.supinfo.project.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
	private static EntityManagerFactory emf;

	// Consturcteur privé pour empecher l'instanciation de la classe
	private PersistenceManager() {
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("SupCommerce-PU");
		}

		return emf;
	}

	public static void closeEntityManagerFactory() {
		if (emf != null && emf.isOpen()) {
			emf.close();
			emf = null;
		}
	}
}
