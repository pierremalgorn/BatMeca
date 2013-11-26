package dao.impl;


import java.util.List;

import javax.persistence.EntityManager;

import dao.MaterialDao;
import dao.manager.DaoManager;
import entity.Material;
import entity.User;


public class MaterialDaoImpl implements MaterialDao {

	@SuppressWarnings("unchecked")
	public List<Material> findAll() {
		EntityManager em = null;

		List<Material> list = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();

			list = em.createNamedQuery("findAllMaterial").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return list;
	}

	public void addMaterial(Material mat) {
		EntityManager em = null;
		try {

			em = DaoManager.INSTANCE.getEntityManager();

			em.getTransaction().begin();

			em.persist(mat);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public Material find(int id) {
		 EntityManager em = null;
		
		 Material material = null;
		
		
		
		 try {
		 em = DaoManager.INSTANCE.getEntityManager();
		
		 material = em.find(Material.class, id);
		
		
		
		
		 } catch (Exception e) {
		 e.printStackTrace();
		 } finally {
		 if (em != null)
		 em.close();
		 }
		
		 return material;
		
	}

	@Override
	public void editMaterial(Material mat) {
		EntityManager em = null;
		try {

			em = DaoManager.INSTANCE.getEntityManager();

			em.getTransaction().begin();

			em.merge(mat);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

	}

	@Override
	public boolean remove(Material mat) {
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			em.remove(em.contains(mat) ? mat : em.merge(mat));
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Material> findAllNoParent(){
		EntityManager em = null;
		List<Material> materials = null;
		

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			
			materials =  em.createQuery("Select m From Material m Where m.materialParent IS NULL").getResultList();
		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> findByParent(Material parent) {
		EntityManager em = null;
		List<Material> materials = null;
		try{
			 em = DaoManager.INSTANCE.getEntityManager();
			 materials = em.createQuery("Select m From Material m Where m.materialParent=:mParent").setParameter("mParent", parent).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (em != null)
				em.close();
		}
		
		
		 
		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> findByUser(User user) {
		EntityManager em = null;
		List<Material> materials = null;
		try{
			 em = DaoManager.INSTANCE.getEntityManager();
			 materials = em.createQuery("Select m From Material m Where m.user=:user AND m.materialParent IS NULL").setParameter("user", user).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (em != null)
				em.close();
		}
		
		
		 
		return materials;
	}

	

}
