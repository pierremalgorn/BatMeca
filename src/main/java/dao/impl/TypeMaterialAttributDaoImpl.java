package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import dao.TypeMaterialAttributDao;
import dao.manager.DaoManager;
import entity.TypeMaterialAttribute;

@Repository
public class TypeMaterialAttributDaoImpl implements TypeMaterialAttributDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeMaterialAttribute> findAll() {
		EntityManager em = null;
		List<TypeMaterialAttribute> list = null;
		
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			
			list =  em.createQuery("Select t From TypeMaterialAttribute t").getResultList();
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return list;
	}

	@Override
	public TypeMaterialAttribute find(int id) {
		EntityManager em = null;
		TypeMaterialAttribute type = null;
		try {
			em = DaoManager.INSTANCE.getEntityManager();


			type = em.find(TypeMaterialAttribute.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return type;
	}

	@Override
	public boolean remove(TypeMaterialAttribute type) {
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();


			em.getTransaction().begin();
			em.remove(em.contains(type) ? type : em.merge(type));
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
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
		EntityManager em = null;
		
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			
			em.getTransaction().begin();

			em.persist(type);

			em.getTransaction().commit();
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
		
		return true;
	}

}
