package service.impl;

import java.util.List;

import service.UserService;
import dao.UserDao;
import dao.manager.DaoManager;
import entity.User;


public class UserServiceImpl implements UserService{
UserDao userDao ;
	
	public UserServiceImpl(){
		userDao = DaoManager.INSTANCE.getUserDao();
	}
	
	public List<User> findAllUsers(){
		return userDao.findAllUsers();
	}
	
	public User getUserByLoginMdp(String login,String mdp){
		return userDao.getUserByLoginMdp(login, mdp);
	}
	
	public boolean addUser(User user){
		return userDao.addUser(user);
	}

	 public boolean loginExist(String login){
		 return userDao.loginExist(login);
	 }
	 
	 public User getUser(int id){
			return userDao.getUser(id);
		}
	 
	 public boolean editUser(User user){
			return userDao.editUser(user);
		}


}
