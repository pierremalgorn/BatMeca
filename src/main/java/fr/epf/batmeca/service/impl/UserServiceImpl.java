package fr.epf.batmeca.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.IUserDao;
import fr.epf.batmeca.entity.TypeUser;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		User user = userDao.getUserByLogin(login);

		return new org.springframework.security.core.userdetails.User(
				user == null ? null : user.getEmail(), // Login
				user == null ? null : user.getPassword(), // Password
				true, // Enabled
				true, // Account not expired
				true, // Credentials non expired
				true, // Account non locked
				user == null ? null : getAuthorities(user.getType()));
	}

	@Override
	@Transactional
	public boolean addUser(User user) {
		return userDao.addUser(user);
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

	/**
	 * Return a list with all the granted authorities for a particular user
	 * type.
	 *
	 * @param type
	 *            The type of the user.
	 * @return A list containing the granted authorities for this user type.
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(TypeUser type) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if (type.getId() == 1) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (type.getId() == 2) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		return authorities;
	}
}
