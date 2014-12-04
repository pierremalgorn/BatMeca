package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.User;

public interface IUserService {
	List<User> findAllUsers();

	boolean addUser(User user);

	User getUser(int id);

	boolean editUser(User user);

	void removeUser(User user);
}
