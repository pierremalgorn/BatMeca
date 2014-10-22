package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.UserService;
import dao.UserDao;
import entity.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
UserDao userDao ;
	
	public UserServiceImpl(){
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
	 
	 public void removeUser(User user){
			userDao.removeUser(user);
		}

	


}
