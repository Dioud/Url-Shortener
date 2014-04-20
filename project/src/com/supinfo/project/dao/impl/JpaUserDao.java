package com.supinfo.project.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.supinfo.project.dao.UserDao;
import com.supinfo.project.entity.User;

/**
 * Implémentation du Dao de User dédiée à la persistence via JPA. 
 */
public class JpaUserDao implements UserDao {

	/** Query JPQL de selection de l'ensemble des User. */
	private static final String SELECT_ALL_QUERY = "SELECT c FROM User c";
	private static final String SELECT_USERBYID_QUERY = "SELECT c FROM User c WHERE email = :email ";
	private static final String SELECT_USERBYEMAILPASSWORD_QUERY = "SELECT c FROM User c WHERE email = :email AND password = :password ";

	private EntityManagerFactory emf;


	public JpaUserDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public User addUser(User p) {
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
	public User findUserById(Long id) {
		final EntityManager em = emf.createEntityManager();
		try {
			return em.find(User.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<User> getAllUsers() {
		final EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery(SELECT_ALL_QUERY);
			return query.getResultList();
		} finally {
			em.close();
		}
	}	

	@Override
	public User findUserByName(String email) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery(SELECT_USERBYID_QUERY);
			query.setParameter("email", email);
			return (User) query.getSingleResult();
		} finally {
			em.close();
		}
	}
	@Override
	public User findUserByNamePassword(String email,String password) {
		final EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createQuery(SELECT_USERBYEMAILPASSWORD_QUERY);
			query.setParameter("email", email);
			query.setParameter("password", password);
			
			try {
				if (query.getSingleResult() != null)	{
					return (User) query.getSingleResult();
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


