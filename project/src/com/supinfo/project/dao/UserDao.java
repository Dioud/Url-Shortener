package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.User;

/**
 * Classe de gestion des op�rations li�es � la persistence des entit�s User.
 */
public interface UserDao {
	
	/**
	 * Ajoute une User en base de donn�e.
	 * @param User
	 * 			User � ajouter en base.
	 * @return La User ajout�e en base.
	 */
	User addUser(User User);
	
	/**
	 * Retourne la User sp�cifi�e par l'ID pass� en param�tre.
	 * @param id
	 * 			Cl� primaire de la User � retrouver.
	 * @return User associ� � l'ID sp�cifi�.
	 */
	User findUserById(Long id);
	
	/**
	 * @return L'ensemble des User en base.
	 */
	List<User> getAllUsers();
	User findUserByName(String name);
	User findUserByNamePassword(String name,String password);
	
}
