package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.TypeTestAttributDao;
import fr.epf.batmeca.entity.TypeTestAttribute;

@Repository
@Transactional
public class TypeTestAttributDaoImpl implements TypeTestAttributDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeTestAttribute> findAll() {
		List<TypeTestAttribute> types = null;

		try {
			types = em.createQuery("Select t From TypeTestAttribute t")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return types;
	}

	@Override
	public TypeTestAttribute find(int id) {
		TypeTestAttribute type = null;

		try {
			type = em.find(TypeTestAttribute.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;
	}

	@Override
	public boolean remove(TypeTestAttribute type) {
		try {
			em.getTransaction().begin();
			em.remove(em.contains(type) ? type : em.merge(type));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean edit(TypeTestAttribute type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(TypeTestAttribute type) {
		try {
			em.getTransaction().begin();
			em.persist(type);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
}
