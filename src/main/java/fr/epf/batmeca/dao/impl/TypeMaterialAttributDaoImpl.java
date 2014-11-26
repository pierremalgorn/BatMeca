package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.TypeMaterialAttributDao;
import fr.epf.batmeca.entity.TypeMaterialAttribute;

@Repository
@Transactional
public class TypeMaterialAttributDaoImpl implements TypeMaterialAttributDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeMaterialAttribute> findAll() {
		List<TypeMaterialAttribute> list = null;

		try {
			list = em.createQuery("Select t From TypeMaterialAttribute t")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public TypeMaterialAttribute find(int id) {
		TypeMaterialAttribute type = null;

		try {
			type = em.find(TypeMaterialAttribute.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return type;
	}

	@Override
	public boolean remove(TypeMaterialAttribute type) {
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
	public boolean edit(TypeMaterialAttribute type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(TypeMaterialAttribute type) {
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
