package service.impl;

import java.util.List;

import service.TypeMaterialAttributService;
import dao.TypeMaterialAttributDao;
import dao.manager.DaoManager;
import entity.TypeMaterialAttribute;


public class TypeMaterialAttributServiceImpl implements TypeMaterialAttributService {
	private TypeMaterialAttributDao typeMaterialAttributDao;
	
	

	public TypeMaterialAttributServiceImpl() {
		super();
		this.typeMaterialAttributDao = DaoManager.INSTANCE.getTypeMaterialAttributDao();
	}



	@Override
	public List<TypeMaterialAttribute> findAll() {
		return typeMaterialAttributDao.findAll();
	}



	@Override
	public TypeMaterialAttribute find(int id) {
		
		return typeMaterialAttributDao.find(id);
	}



	@Override
	public boolean remove(TypeMaterialAttribute type) {
		
		return typeMaterialAttributDao.remove(type);
	}



	@Override
	public boolean edit(TypeMaterialAttribute type) {
		
		return edit(type);
	}



	@Override
	public boolean add(TypeMaterialAttribute type) {
		// TODO Auto-generated method stub
		return typeMaterialAttributDao.add(type);
	}

	

	
}
