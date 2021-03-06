package fr.epf.batmeca.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.IMaterialDao;
import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;
import fr.epf.batmeca.service.IFileService;
import fr.epf.batmeca.service.IMaterialService;

@Service
public class MaterialServiceImpl implements IMaterialService {

	@Autowired
	private IMaterialDao materialDao;
	@Autowired
	private IFileService fileService;

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
	public boolean remove(Material mat) throws IOException {
		boolean r = materialDao.remove(mat);
		fileService.cleanMaterial(mat);
		return r;
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
