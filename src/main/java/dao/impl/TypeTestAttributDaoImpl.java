package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import dao.TypeTestAttributDao;
import dao.manager.DaoManager;
import entity.TypeTestAttribute;

@Repository
public class TypeTestAttributDaoImpl implements TypeTestAttributDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeTestAttribute> findAll() {
		EntityManager em = null;
		List<TypeTestAttribute> types =null;
		
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			
			types =  em.createQuery("Select t From TypeTestAttribute t").getResultList();
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
		return types;
	}

	@Override
	public TypeTestAttribute find(int id) {
		EntityManager em = null;
		TypeTestAttribute type = null;
		try {
			em = DaoManager.INSTANCE.getEntityManager();


			type = em.find(TypeTestAttribute.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return type;
	}

	@Override
	public boolean remove(TypeTestAttribute type) {
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();


			em.getTransaction().begin();
			em.remove(em.contains(type) ? type : em.merge(type));
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
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
EntityManager em = null;
		
		try {
			em = DaoManager.INSTANCE.getEntityManager();
			
			em.getTransaction().begin();

			em.persist(type);

			em.getTransaction().commit();
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
		return true;
	}

}
