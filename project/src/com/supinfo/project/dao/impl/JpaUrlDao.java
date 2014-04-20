package com.supinfo.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.project.dao.UrlDao;
import com.supinfo.project.entity.Url;

/**
 * Implémentation du Dao de Product dédiée à la persistence via JPA. 
 */
public class JpaUrlDao implements UrlDao {

	/** Query JPQL de selection de l'ensemble des Category. */
	private static final String SELECT_ALL_QUERY 		= "SELECT p FROM Url p";
	private static final String SELECT_ALL_BYUSER_QUERY 		= "SELECT p FROM Url p WHERE user_id = ?1";
	/** Query JPQL de suppression de Product par ID. */
	private static final String DELETE_QUERY 			= "DELETE FROM Url p WHERE p.id = ?1";
	private static final String ENABLE_QUERY 			= "UPDATE Url set enable = ?2 WHERE id = ?1";
	private static final String SELECT_URL_QUERY 			= "SELECT p FROM Url p WHERE urlGenere = ?1 AND enable = true";


	private EntityManagerFactory emf;

	public JpaUrlDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Url addUrl(Url p) {
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
	public Url findUrlById(Long id) {
		final EntityManager em = emf.createEntityManager();
		try {
			return em.find(Url.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Url> getAllUrls() {
		final EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery(SELECT_ALL_QUERY);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public void removeUrl(Long id) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Query query = em.createQuery(DELETE_QUERY);
			query.setParameter(1, id);
			// Equivalent à :
			// Query query = em.createQuery("DELETE FROM Product p WHERE p.id = :monId");
			// query.setParameter("monId", id);
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
	public void enabledisableById(Long id, Boolean enable) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Query query = em.createQuery(ENABLE_QUERY);
			query.setParameter(1, id);
			query.setParameter(2, enable);
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
	public List<Url> getUrlsByUser(Long id) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery(SELECT_ALL_BYUSER_QUERY);
			query.setParameter(1, id);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Url findUrlByLink(String urlGenere) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery(SELECT_URL_QUERY);
			query.setParameter(1, urlGenere);
			try {
				if (query.getSingleResult() != null)	{
					return (Url) query.getSingleResult();
				}
				else	{
					return null;
				}

			}catch(javax.persistence.NoResultException nre){  
				return null;
			}

		} finally {
			em.close();
		}
	}
}