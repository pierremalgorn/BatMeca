package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import dao.TestDao;
import dao.manager.DaoManager;
import entity.SubMaterial;
import entity.Test;

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

			test = em.find(Test.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return test;
	}

	@Override
	public void remove(int id) {
		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();

			Test test = em.find(Test.class, id);
			em.getTransaction().begin();
			em.remove(test);
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
	public List<Test> findBySub(SubMaterial sub) {
		List<Test> tests = null;

		EntityManager em = null;

		try {
			em = DaoManager.INSTANCE.getEntityManager();

			tests = em
					.createQuery(
							"Select t From Test t Where t.sub= :sub")
					.setParameter("sub", sub).getResultList();

		} catch (NoResultException e) {
			// e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

		return tests;
	}

}
