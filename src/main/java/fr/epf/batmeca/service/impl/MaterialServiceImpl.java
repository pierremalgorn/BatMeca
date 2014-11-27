package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.MaterialDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialDao materialDao;

	@Override
	@Transactional(readOnly = true)
	public List<Material> findAll() {
		return materialDao.findAll();
	}

	@Override
	@Transactional
	public void addMaterial(Material mat) {
		materialDao.addMaterial(mat);
	}

	@Override
	@Transactional(readOnly = true)
	public Material find(int id) {
		return materialDao.find(id);
	}

	@Override
	@Transactional
	public void editMaterial(Material mat) {
		materialDao.editMaterial(mat);
	}

	@Override
	@Transactional
	public boolean remove(Material mat) {
		return materialDao.remove(mat);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> findAllNoParent() {
		return materialDao.findAllNoParent();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> findByParent(Material parent) {
		return materialDao.findByParent(parent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Material> findByUser(User user) {
		return materialDao.findByUser(user);
	}
}
