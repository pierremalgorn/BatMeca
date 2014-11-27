package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.epf.batmeca.dao.TypeMaterialAttributDao;
import fr.epf.batmeca.entity.TypeMaterialAttribute;

@Repository
public class TypeMaterialAttributDaoImpl implements TypeMaterialAttributDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeMaterialAttribute> findAll() {
		List<TypeMaterialAttribute> list = null;

		list = em.createQuery("Select t From TypeMaterialAttribute t")
				.getResultList();

		return list;
	}

	@Override
	public TypeMaterialAttribute find(int id) {
		TypeMaterialAttribute type = null;

		type = em.find(TypeMaterialAttribute.class, id);

		return type;
	}

	@Override
	public boolean remove(TypeMaterialAttribute type) {
		em.remove(em.contains(type) ? type : em.merge(type));

		return false;
	}

	@Override
	public boolean edit(TypeMaterialAttribute type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(TypeMaterialAttribute type) {
		em.persist(type);
		return true;
	}
}
