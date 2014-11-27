package fr.epf.batmeca.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.epf.batmeca.dao.IMaterialDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;

@Repository
public class MaterialDaoImpl implements IMaterialDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Material> findAll() {
		List<Material> list = null;

		list = em.createNamedQuery("findAllMaterial").getResultList();

		return list;
	}

	@Override
	public void addMaterial(Material mat) {
		em.persist(mat);
	}

	@Override
	public Material find(int id) {
		Material material = null;

		material = em.find(Material.class, id);

		return material;
	}

	@Override
	public void editMaterial(Material mat) {
		em.merge(mat);
	}

	@Override
	public boolean remove(Material mat) {
		em.remove(em.contains(mat) ? mat : em.merge(mat));

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Material> findAllNoParent() {
		List<Material> materials = null;

		materials = em.createQuery(
				"Select m From Material m Where m.materialParent IS NULL")
				.getResultList();

		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> findByParent(Material parent) {
		List<Material> materials = null;
		materials = em
				.createQuery(
						"Select m From Material m Where m.materialParent=:mParent")
				.setParameter("mParent", parent).getResultList();

		return materials;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> findByUser(User user) {
		List<Material> materials = null;
		materials = em
				.createQuery(
						"Select m From Material m Where m.user=:user AND m.materialParent IS NULL")
				.setParameter("user", user).getResultList();

		return materials;
	}
}
