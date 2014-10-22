package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import dao.UserDao;
import dao.manager.DaoManager;
import entity.User;

public class UserDaoImpl implements UserDao{
	
	public List<User> findAllUsers() {
		EntityManager em = null;

		List<User> users = null;
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			users = em.createNamedQuery("findAllUsers").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return users;
	}

	/**
	 * Methode permettant de r�cuperer un Utilisateur en fonction de son login et son mot de passe
	 * @param String login Login Utilisateur
	 * @param String mdp mot de passe utilisateur
	 * 
	 * */
	public User getUserByLoginMdp(String login,String mdp){
		User user = null;
		EntityManager em = null;
		
		try{
			em = DaoManager.INSTANCE.getEntityManager();
			
			user = (User) em.createQuery("Select u From User u Where u.email= :email And u.password = :mdp")
					.setParameter("email", login)
					.setParameter("mdp", mdp)
					.getSingleResult();
		}catch (NoResultException e) {
			//e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
		return user;
	}
	
	public boolean loginExist(String login){
		User user = null;
		EntityManager em = null;
		
		try{
			em = DaoManager.INSTANCE.getEntityManager();
			
			user = (User) em.createQuery("Select u From User u Where u.login= :login")
					.setParameter("login", login)
					
					.getSingleResult();
			
		}catch (NoResultException e) {
			//e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
		if(user == null){
			return false;
		}else{
			return true;
		}
		
	}

	@Override
	public boolean addUser(User user) {
		
		EntityManager em = null;
		try {
			
			em = DaoManager.INSTANCE.getEntityManager();
			
			em.getTransaction().begin();
			
			em.persist(user);
			
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		
		return true;
	}
	
	/**
	 * Methode permettant de r�cuperer un Utilisateur en fonction de son login et son mot de passe
	 * @param String login Login Utilisateur
	 * @param String mdp mot de passe utilisateur
	 * 
	 * */
	public User getUser(int id){
		User user = null;
		EntityManager em = null;
		
		try{
			em = DaoManager.INSTANCE.getEntityManager();
			
			user = (User) em.createQuery("Select u From User u Where u.id= :id")
					.setParameter("id", id)
					.getSingleResult();
		}catch (NoResultException e) {
			//e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
		return user;
	}
	
	@Override
	public void removeUser(User user) {
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.contains(user) ? user : em.merge(user));
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

	}
	
	@Override
	public boolean editUser(User user) {
		
		EntityManager em = null;
		try {
			
			em = DaoManager.INSTANCE.getEntityManager();
			
			em.getTransaction().begin();
			
			em.merge(user);
			
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		
		return true;
	}

}
