package dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;



import dao.SubMaterialDao;
import dao.manager.DaoManager;
import entity.Material;
import entity.SubMaterial;

public class SubMaterialDaoImpl implements SubMaterialDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SubMaterial> findByMaterial(Material mat) {
		List<SubMaterial> subMats = null;
		
		EntityManager em = null;
		
		try{
			em = DaoManager.INSTANCE.getEntityManager();
			
			subMats = em.createQuery("Select s From SubMaterial s Where s.material= :material")
					.setParameter("material", mat)
					.getResultList();
				
		}catch (NoResultException e) {
			//e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
		
		return subMats;
	}

	@Override
	public void add(SubMaterial sub) {
		EntityManager em = null;
		try {
			
			em = DaoManager.INSTANCE.getEntityManager();
			
			em.getTransaction().begin();
			
			em.persist(sub);
			
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(em != null)
				em.close();
		}
		
	}

	@Override
	public void remove(int id) {
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();

			
			SubMaterial sub = em.find(SubMaterial.class, id);
			em.getTransaction().begin();
			em.remove(sub);
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
	}

	@Override
	public SubMaterial find(int id) {
		EntityManager em = null;
		SubMaterial sub = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();

			sub = em.find(SubMaterial.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return sub;
	}

}
