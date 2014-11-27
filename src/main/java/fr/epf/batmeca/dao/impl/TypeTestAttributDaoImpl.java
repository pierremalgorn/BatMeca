package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.epf.batmeca.dao.TypeTestAttributDao;
import fr.epf.batmeca.entity.TypeTestAttribute;

@Repository
public class TypeTestAttributDaoImpl implements TypeTestAttributDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeTestAttribute> findAll() {
		List<TypeTestAttribute> types = null;

		types = em.createQuery("Select t From TypeTestAttribute t")
				.getResultList();

		return types;
	}

	@Override
	public TypeTestAttribute find(int id) {
		TypeTestAttribute type = null;

		type = em.find(TypeTestAttribute.class, id);

		return type;
	}

	@Override
	public boolean remove(TypeTestAttribute type) {
		em.remove(em.contains(type) ? type : em.merge(type));

		return false;
	}

	@Override
	public boolean edit(TypeTestAttribute type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(TypeTestAttribute type) {
		em.persist(type);

		return true;
	}
}
