package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.MaterialService;
import dao.MaterialDao;
import entity.Material;
import entity.User;

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
