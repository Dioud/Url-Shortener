package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.User;

/**
 * Classe de gestion des opérations liées à la persistence des entités User.
 */
public interface UserDao {
	
	/**
	 * Ajoute une User en base de donnée.
	 * @param User
	 * 			User à ajouter en base.
	 * @return La User ajoutée en base.
	 */
	User addUser(User User);
	
	/**
	 * Retourne la User spécifiée par l'ID passé en paramètre.
	 * @param id
	 * 			Clé primaire de la User à retrouver.
	 * @return User associé à l'ID spécifié.
	 */
	User findUserById(Long id);
	
	/**
	 * @return L'ensemble des User en base.
	 */
	List<User> getAllUsers();
	User findUserByName(String name);
	User findUserByNamePassword(String name,String password);
	
}
