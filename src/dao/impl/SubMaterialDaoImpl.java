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

}
