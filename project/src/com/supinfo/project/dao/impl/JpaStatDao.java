package com.supinfo.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.project.dao.StatDao;
import com.supinfo.project.entity.Stat;

/**
 * Implémentation du Dao de Product dédiée à la persistence via JPA. 
 */
public class JpaStatDao implements StatDao {

	/** Query JPQL de selection de l'ensemble des Category. */
	private static final String SELECT_ALL_BYUSER_QUERY 		= "SELECT p FROM Stat p WHERE url_id = ?1 GROUP BY referer";
	private static final String DELETE_QUERY 			= "DELETE FROM Stat p WHERE url_id = ?1";
	

	private EntityManagerFactory emf;

	public JpaStatDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Stat addStat(Stat p) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.persist(p);
			transaction.commit();
		} finally {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			em.close();
		}
		return p;
	}

	@Override
	public Stat findStatById(Long id) {
		final EntityManager em = emf.createEntityManager();
		try {
			return em.find(Stat.class, id);
		} finally {
			em.close();
		}
	}



	@Override
	public void removeStat(Long id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Query query = em.createQuery(DELETE_QUERY);
			query.setParameter(1, id);
			query.executeUpdate();
			transaction.commit();
		} finally {
			if(transaction.isActive()) {
				transaction.rollback();
			}
			em.close();
		}
	}



	@Override
	public List<Stat> findStatByUrlId(Long id) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery(SELECT_ALL_BYUSER_QUERY);
			query.setParameter(1, id);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

}