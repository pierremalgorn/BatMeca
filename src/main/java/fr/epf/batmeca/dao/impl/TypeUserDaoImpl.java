package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.epf.batmeca.dao.ITypeUserDao;
import fr.epf.batmeca.entity.TypeUser;

@Repository
public class TypeUserDaoImpl implements ITypeUserDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeUser> getTypes() {
		List<TypeUser> types = null;

		// Ici on appelle la namedQuery declaree en annotation dans la
		// classe domain.User
		types = em.createNamedQuery("findAllTypeUser").getResultList();

		return types;
	}
}
