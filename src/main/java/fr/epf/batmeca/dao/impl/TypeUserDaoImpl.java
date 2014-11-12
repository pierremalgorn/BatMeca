package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.epf.batmeca.dao.TypeUserDao;
import fr.epf.batmeca.dao.manager.DaoManager;
import fr.epf.batmeca.entity.TypeUser;

@Repository
public class TypeUserDaoImpl implements TypeUserDao {

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
			if (em != null) {
				em.close();
			}
		}

		return types;
	}
}
