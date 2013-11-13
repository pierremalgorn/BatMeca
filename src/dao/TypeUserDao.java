package dao;

import java.util.List;

import entity.TypeUser;

public interface TypeUserDao {
	
	/**
	 * Methode permettant de recuperer le liste des types
	 * @return liste des types d'utilisateur
	 * */
	List<TypeUser> getTypes();

}
