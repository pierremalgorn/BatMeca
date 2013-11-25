package service.impl;

import java.util.List;



import dao.MaterialDao;
import dao.manager.DaoManager;
import entity.Material;
import service.MaterialService;

public class MaterialServiceImpl implements MaterialService {

	private MaterialDao materialDao ;
	
	public MaterialServiceImpl(){
		materialDao = DaoManager.INSTANCE.getMaterialDao();
	}
	
	@Override
	public List<Material> findAll() {
		return materialDao.findAll();
	}
	
	public void addMaterial(Material mat){
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

}
