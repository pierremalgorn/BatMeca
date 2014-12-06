package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.epf.batmeca.dao.ITestDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.entity.User;

@Repository
public class TestDaoImpl implements ITestDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findAll() {
		List<Test> list = null;

		list = em.createNamedQuery("findAllTest").getResultList();

		return list;
	}

	@Override
	public void add(Test test) {
		em.persist(test);
	}

	@Override
	public Test find(int id) {
		Test test = null;

		// test = (Test) em.createNamedQuery("findTest").setParameter("id",
		// id).getSingleResult();
		test = (Test) em
				.createQuery(
						"Select t From Test t left join t.testAttributs ta Where t.id=:id")
				.setParameter("id", id).getSingleResult();
		// test = em.find(Test.class, id);

		return test;
	}

	@Override
	public void remove(Test test) {
		em.remove(em.contains(test) ? test : em.merge(test));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findByMaterial(Material mat) {
		List<Test> tests = null;

		tests = em
				.createQuery(
						"Select t From Test t Where t.material = :material")
				.setParameter("material", mat.getId()).getResultList();

		return tests;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findByUser(User user) {
		List<Test> tests = null;

		tests = em.createQuery("Select t From Test t Where t.user=:user")
				.setParameter("user", user.getId()).getResultList();

		return tests;
	}
}
