package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import dao.TypeUserDao;
import dao.manager.DaoManager;
import entity.TypeUser;

public class TypeUserDaoImpl implements TypeUserDao{
	public TypeUserDaoImpl() {

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TypeUser> getTypes() {

		EntityManager em = null;

		List<TypeUser> types = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			// Ici on appelle la namedQuery declaree en annotation dans la
			// classe domain.User
			types = em.createNamedQuery("findAllTypeUser").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return types;
	}

	

}
