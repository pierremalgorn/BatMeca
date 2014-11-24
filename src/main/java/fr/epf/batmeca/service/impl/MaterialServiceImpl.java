package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epf.batmeca.dao.MaterialDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialDao materialDao;

	public MaterialServiceImpl() {
	}

	@Override
	public List<Material> findAll() {
		return materialDao.findAll();
	}

	@Override
	public void addMaterial(Material mat) {
		materialDao.addMaterial(mat);
	}

	@Override
	public Material find(int id) {
		return materialDao.find(id);
	}

	@Override
	public void editMaterial(Material mat) {
		materialDao.editMaterial(mat);
	}

	@Override
	public boolean remove(Material mat) {
		return materialDao.remove(mat);
	}

	@Override
	public List<Material> findAllNoParent() {
		return materialDao.findAllNoParent();
	}

	@Override
	public List<Material> findByParent(Material parent) {
		return materialDao.findByParent(parent);
	}

	@Override
	public List<Material> findByUser(User user) {
		return materialDao.findByUser(user);
	}
}
