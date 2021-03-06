package fr.epf.batmeca.dao;

import java.util.List;

import fr.epf.batmeca.entity.TypeUser;

public interface ITypeUserDao {

	/**
	 * Methode permettant de recuperer le liste des types
	 *
	 * @return liste des types d'utilisateur
	 * */
	List<TypeUser> getTypes();
}
