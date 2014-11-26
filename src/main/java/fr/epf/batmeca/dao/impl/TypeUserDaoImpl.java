package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.TypeUserDao;
import fr.epf.batmeca.entity.TypeUser;

@Repository
@Transactional
public class TypeUserDaoImpl implements TypeUserDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeUser> getTypes() {
		List<TypeUser> types = null;

		try {
			// Ici on appelle la namedQuery declaree en annotation dans la
			// classe domain.User
			types = em.createNamedQuery("findAllTypeUser").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return types;
	}
}
