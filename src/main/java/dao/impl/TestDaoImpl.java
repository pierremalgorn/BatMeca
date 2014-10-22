package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import dao.TestDao;
import dao.manager.DaoManager;
import entity.Material;
import entity.Test;
import entity.User;

public class TestDaoImpl implements TestDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findAll() {
		EntityManager em = null;

		List<Test> list = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();

			list = em.createNamedQuery("findAllTest").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return list;

	}

	@Override
	public void add(Test test) {
		EntityManager em = null;
		try {

			em = DaoManager.INSTANCE.getEntityManager();

			em.getTransaction().begin();

			em.persist(test);

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

	}

	@Override
	public Test find(int id) {
		EntityManager em = null;
		Test test = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			//test = (Test) em.createNamedQuery("findTest").setParameter("id", id).getSingleResult();
			test = (Test) em.createQuery("Select t From Test t left join t.testAttributs ta Where t.id=:id").setParameter("id", id).getSingleResult();
			//test = em.find(Test.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return test;
	}

	@Override
	public void remove(Test test) {
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();
			em.getTransaction().begin();
			
			
			em.remove(em.contains(test) ? test : em.merge(test));
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findByMaterial(Material mat) {
		List<Test> tests = null;
        EntityManager em = null;

        try {
                em = DaoManager.INSTANCE.getEntityManager();
           
                tests = em
                                .createQuery(
                                                "Select t From Test t Where t.material = :material")
                                .setParameter("material", mat.getId())
                                .getResultList();

        } catch (NoResultException e) {
                // e.printStackTrace();
        } finally {
                if (em != null)
                        em.close();
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findByUser(User user) {
		EntityManager em = null;
		List<Test> tests = null;
		try{
			 em = DaoManager.INSTANCE.getEntityManager();
			 tests = em.createQuery("Select t From Test t Where t.user=:user").setParameter("user", user.getId()).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (em != null)
				em.close();
		}
		
		
		 
		return tests;
	}

	

}
