package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.IUserDao;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByLoginMdp(String login, String mdp) {
		return userDao.getUserByLoginMdp(login, mdp);
	}

	@Override
	@Transactional
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean loginExist(String login) {
		return userDao.loginExist(login);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUser(int id) {
		return userDao.getUser(id);
	}

	@Override
	@Transactional
	public boolean editUser(User user) {
		return userDao.editUser(user);
	}

	@Override
	@Transactional
	public void removeUser(User user) {
		userDao.removeUser(user);
	}
}
