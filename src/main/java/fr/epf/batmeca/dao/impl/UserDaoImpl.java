package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.epf.batmeca.dao.IUserDao;
import fr.epf.batmeca.entity.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> findAllUsers() {
		return em.createNamedQuery("findAllUsers").getResultList();
	}

	@Override
	public User getUserByLogin(String login) {
		// FIXME use "Select u From User u Where u.login= :login" instead?
		return (User) em
				.createQuery("Select u From User u Where u.email= :email")
				.setParameter("email", login).getSingleResult();
	}

	@Override
	public boolean addUser(User user) {
		em.persist(user);

		return true;
	}

	@Override
	public User getUser(int id) {
		return (User) em.createQuery("Select u From User u Where u.id= :id")
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public void removeUser(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}

	@Override
	public boolean editUser(User user) {
		em.merge(user);

		return true;
	}
}
