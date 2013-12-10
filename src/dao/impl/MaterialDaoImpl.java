package dao.impl;


import java.util.List;

import javax.persistence.EntityManager;

import dao.MaterialDao;
import dao.manager.DaoManager;
import entity.Material;
import entity.User;


public class MaterialDaoImpl implements MaterialDao {

	@SuppressWarnings("unchecked")
	/**
	 * Permet de recuperer la list de tous les materiaux
	 * 
	 * */
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

	/**
	 * Permet d'ajouter un material à la base de données
	 * @param mat material a ajouter
	 * */
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
	/**
	 * Permet de recuperer un material en fonction de son Id
	 * @param id du material
	 * @return Material 
	 * */
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
	/**
	 * Permet de modifier un material present dans la base de données
	 * @param Material mat : materail a modifier
	 * */
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
	/**
	 * Permet de modifier un material present dans la base de données
	 * @param Material mat 
	 * */
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
	/**
	 * Permet de renvoyer la liste des materiaux sans parents
	 * */
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
	/**
	 * Permet d'obtenir la liste des materiaux en focntion du parent
	 * */
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
	/**
	 * Permet d'obtenir la liste des materiaux apartenant à un utilisateur
	 * */
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
