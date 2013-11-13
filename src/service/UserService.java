package service;

import java.util.List;

import entity.User;

public interface UserService {
	
	abstract List<User> findAllUsers();
	abstract User getUserByLoginMdp(String login,String mdp);
	abstract boolean addUser(User user);
	abstract boolean loginExist(String login);
	abstract User getUser(int id);
	abstract boolean editUser(User user);
}
