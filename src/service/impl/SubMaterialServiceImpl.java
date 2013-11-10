package service.impl;



import java.util.List;

import dao.SubMaterialDao;
import dao.manager.DaoManager;
import entity.Material;
import entity.SubMaterial;
import service.SubMaterialService;

public class SubMaterialServiceImpl implements SubMaterialService {

	private SubMaterialDao subMaterialDao;
	
	public SubMaterialServiceImpl(){
		subMaterialDao = DaoManager.INSTANCE.getSubMaterialDao();
	}
	@Override
	public List<SubMaterial> findByMat(Material mat) {
		return subMaterialDao.findByMaterial(mat);
	}
	
}
