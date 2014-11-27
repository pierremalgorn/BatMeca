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
		List<User> users = null;

		users = em.createNamedQuery("findAllUsers").getResultList();

		return users;
	}

	/**
	 * Methode permettant de r�cuperer un Utilisateur en fonction de son login
	 * et son mot de passe
	 *
	 * @param String
	 *            login Login Utilisateur
	 * @param String
	 *            mdp mot de passe utilisateur
	 *
	 * */
	@Override
	public User getUserByLoginMdp(String login, String mdp) {
		User user = null;

		user = (User) em
				.createQuery(
						"Select u From User u Where u.email= :email And u.password = :mdp")
				.setParameter("email", login).setParameter("mdp", mdp)
				.getSingleResult();
		// } catch (NoResultException e) {
		// e.printStackTrace();
		// }

		return user;
	}

	@Override
	public boolean loginExist(String login) {
		User user = null;

		user = (User) em
				.createQuery("Select u From User u Where u.login= :login")
				.setParameter("login", login).getSingleResult();
		// } catch (NoResultException e) {
		// e.printStackTrace();
		// }

		return user != null;
	}

	@Override
	public boolean addUser(User user) {
		em.persist(user);

		return true;
	}

	/**
	 * Methode permettant de r�cuperer un Utilisateur en fonction de son login
	 * et son mot de passe
	 *
	 * @param String
	 *            login Login Utilisateur
	 * @param String
	 *            mdp mot de passe utilisateur
	 *
	 * */
	@Override
	public User getUser(int id) {
		User user = null;

		user = (User) em.createQuery("Select u From User u Where u.id= :id")
				.setParameter("id", id).getSingleResult();
		// } catch (NoResultException e) {
		// e.printStackTrace();
		// }

		return user;
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
