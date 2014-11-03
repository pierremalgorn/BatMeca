package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	/**
	 * Methode permettant de renvoyer la liste de tout les utilisateur
	 * 
	 * @return liste des utilisateur
	 * */
	public List<User> findAllUsers();

	/**
	 * Permet de renvoyer un utilisateur en fonction de son login et son mot de
	 * passe
	 * 
	 * @param String
	 *            login Login de l'utilisateur
	 * @param String
	 *            mdp mot de passe de l'utilisateur
	 * @retun user
	 * */
	public User getUserByLoginMdp(String login, String mdp);

	/**
	 * Methode permettant d'ajouter un utilisateur
	 * 
	 * @param user
	 *            utilisateur a ajouter
	 * @return true si l'utilisateur � bien �t� ajouter false sinon
	 * */
	public boolean addUser(User user);

	/**
	 * Methode permettant de savoir si un login existe
	 * 
	 * @param String
	 *            login
	 * @return true si le login existe false sinon
	 * */
	public boolean loginExist(String login);

	/**
	 * Permet de renvoyer un utilisateur en fonction de son id
	 * 
	 * @param String
	 *            login Login de l'utilisateur
	 * @param String
	 *            mdp mot de passe de l'utilisateur
	 * @retun user
	 * */
	public User getUser(int id);

	/**
	 * Methode permettant de modifier un utilisateur
	 * 
	 * @param user
	 *            utilisateur � modifier
	 * @return true si l'utilisateur � bien �t� modifi� false sinon
	 * */
	public boolean editUser(User user);

	/**
	 * Permet de supprimer un utilisateur
	 * 
	 * @param User
	 *            user: utilisateur à supprimer
	 * */
	public void removeUser(User user);
}
