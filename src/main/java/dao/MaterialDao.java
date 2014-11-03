package dao;

import java.util.List;

import entity.Material;
import entity.User;

public interface MaterialDao {

	/**
	 * Permet de recuperer la list de tous les materiaux
	 * 
	 * @return List<Material> list: liste de tout les materiaux
	 * */
	public List<Material> findAll();

	/**
	 * Permet d'ajouter un material à la base de données
	 * 
	 * @param mat
	 *            material a ajouter
	 * */
	public void addMaterial(Material mat);

	/**
	 * Permet de recuperer un material en fonction de son Id
	 * 
	 * @param id
	 *            du material
	 * @return Material
	 * */
	public Material find(int id);

	/**
	 * Permet de modifier un material present dans la base de données
	 * 
	 * @param Material
	 *            mat : material a modifier
	 * */
	public void editMaterial(Material mat);

	/**
	 * Permet de modifier un material present dans la base de données
	 * 
	 * @param Material
	 *            mat
	 * @return true si le materiel est supprimer false sinon
	 * */
	public boolean remove(Material mat);

	/**
	 * Permet de renvoyer la liste des materiaux sans parents
	 * 
	 * @return List<Material> list liste des materiaux sans parent
	 * */
	public List<Material> findAllNoParent();

	/**
	 * Permet d'obtenir la liste des materiaux en focntion du parent
	 * 
	 * @param Material
	 *            parent: materiel parent
	 * @return List<Material> list: list des materiaux enfants
	 * */
	public List<Material> findByParent(Material parent);

	/**
	 * Permet d'obtenir la liste des materiaux apartenant à un utilisateur
	 * 
	 * @param User
	 *            user: user proprietaire du materiel
	 * @return List<Material> list
	 * */
	public List<Material> findByUser(User user);

}
