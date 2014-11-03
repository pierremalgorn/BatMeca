package dao;

import java.util.List;

import entity.Material;
import entity.Test;
import entity.User;

public interface TestDao {
	/**
	 * Permet d'obtenir la liste de tout les essais
	 * 
	 * @return List<Test> list : liste de tout les essais
	 * */
	public List<Test> findAll();

	/**
	 * Permet d'ajouter un essai dans la base de données
	 * 
	 * @param Test
	 *            test: essai à ajouter
	 * */
	public void add(Test test);

	/**
	 * Permet de recuperer un essai present dans la base de données
	 * 
	 * @param int id: id de l'essai
	 * @return Test test
	 * */
	public Test find(int id);

	/**
	 * Permet de supprimer un essai de la base de données
	 * 
	 * @param Test
	 *            test : essai à supprimer
	 * 
	 * */
	public void remove(Test test);

	/**
	 * Permet de recuperer la liste des éssais appartenant à un matériaux
	 * 
	 * @param Material
	 *            mat: Material parent
	 * @return List<Test> test
	 * */
	public List<Test> findByMaterial(Material mat);

	/**
	 * Permet de recuperer l'ensemble des essais d'un utilisateur
	 * 
	 * @return List<Test> list
	 * */
	public List<Test> findByUser(User user);
}
