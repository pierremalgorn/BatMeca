package service.impl;

import java.util.List;

import entity.TypeUser;
import dao.TypeUserDao;
import dao.manager.DaoManager;
import service.TypeUserService;

public class TypeUserServiceImpl implements TypeUserService{
	
TypeUserDao typeUserDao;
	
	public TypeUserServiceImpl(){
		typeUserDao = DaoManager.INSTANCE.getTypeUserDao();
	}
	
public List<TypeUser> getTypes() {
		
		return typeUserDao.getTypes();
	}
}
