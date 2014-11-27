package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.User;

public interface IUserService {
	List<User> findAllUsers();

	User getUserByLoginMdp(String login, String mdp);

	boolean addUser(User user);

	boolean loginExist(String login);

	User getUser(int id);

	boolean editUser(User user);

	void removeUser(User user);
}
