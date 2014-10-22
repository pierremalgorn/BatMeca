package service.impl;

import java.util.List;

import service.TypeTestAttributService;
import dao.TypeTestAttributDao;
import dao.manager.DaoManager;
import entity.TypeTestAttribute;

public class TypeTestAttributServiceImpl implements TypeTestAttributService {

	private TypeTestAttributDao typeDao;
	
	public TypeTestAttributServiceImpl(){
		super();
		typeDao = DaoManager.INSTANCE.getTypeTestAttributDao();
	}
	
	@Override
	public List<TypeTestAttribute> findAll() {
		
		return typeDao.findAll();
	}

	@Override
	public TypeTestAttribute find(int id) {
		
		return typeDao.find(id);
	}

	@Override
	public boolean remove(TypeTestAttribute type) {
		// TODO Auto-generated method stub
		return typeDao.remove(type);
	}

	@Override
	public boolean edit(TypeTestAttribute type) {
		// TODO Auto-generated method stub
		return typeDao.edit(type);
	}

	@Override
	public boolean add(TypeTestAttribute type) {
		
		return typeDao.add(type);
	}

}
