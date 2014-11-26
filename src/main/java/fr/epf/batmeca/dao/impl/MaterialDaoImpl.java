package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.MaterialDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;

@Repository
@Transactional
public class MaterialDaoImpl implements MaterialDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Material> findAll() {
		List<Material> list = null;

		try {
			list = em.createNamedQuery("findAllMaterial").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void addMaterial(Material mat) {
		try {
			em.getTransaction().begin();
			em.persist(mat);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Material find(int id) {
		Material material = null;

		try {
			material = em.find(Material.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return material;
	}

	@Override
	public void editMaterial(Material mat) {
		try {
			em.getTransaction().begin();
			em.merge(mat);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean remove(Material mat) {
		try {
			em.getTransaction().begin();
			em.remove(em.contains(mat) ? mat : em.merge(mat));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Material> findAllNoParent() {
		List<Material> materials = null;

		try {
			materials = em.createQuery(
					"Select m From Material m Where m.materialParent IS NULL")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> findByParent(Material parent) {
		List<Material> materials = null;
		try {
			materials = em
					.createQuery(
							"Select m From Material m Where m.materialParent=:mParent")
					.setParameter("mParent", parent).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> findByUser(User user) {
		List<Material> materials = null;
		try {
			materials = em
					.createQuery(
							"Select m From Material m Where m.user=:user AND m.materialParent IS NULL")
					.setParameter("user", user).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return materials;
	}
}
