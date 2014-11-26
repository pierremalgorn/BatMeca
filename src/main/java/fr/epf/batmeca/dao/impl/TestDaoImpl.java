package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.TestDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.User;

@Repository
@Transactional
public class TestDaoImpl implements TestDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findAll() {
		List<Test> list = null;

		try {
			list = em.createNamedQuery("findAllTest").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void add(Test test) {
		try {
			em.getTransaction().begin();
			em.persist(test);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Test find(int id) {
		Test test = null;

		try {
			// test = (Test) em.createNamedQuery("findTest").setParameter("id",
			// id).getSingleResult();
			test = (Test) em
					.createQuery(
							"Select t From Test t left join t.testAttributs ta Where t.id=:id")
					.setParameter("id", id).getSingleResult();
			// test = em.find(Test.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return test;
	}

	@Override
	public void remove(Test test) {
		try {
			em.getTransaction().begin();
			em.remove(em.contains(test) ? test : em.merge(test));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findByMaterial(Material mat) {
		List<Test> tests = null;

		try {
			tests = em
					.createQuery(
							"Select t From Test t Where t.material = :material")
					.setParameter("material", mat.getId()).getResultList();
		} catch (NoResultException e) {
			// e.printStackTrace();
		}
		return null; // FIXME return null?
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findByUser(User user) {
		List<Test> tests = null;

		try {
			tests = em.createQuery("Select t From Test t Where t.user=:user")
					.setParameter("user", user.getId()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tests;
	}
}
