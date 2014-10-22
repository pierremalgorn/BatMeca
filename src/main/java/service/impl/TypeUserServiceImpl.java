package service.impl;

import java.util.List;

import service.TypeUserService;
import dao.TypeUserDao;
import dao.manager.DaoManager;
import entity.TypeUser;

public class TypeUserServiceImpl implements TypeUserService{
	
TypeUserDao typeUserDao;
	
	public TypeUserServiceImpl(){
		typeUserDao = DaoManager.INSTANCE.getTypeUserDao();
	}
	
public List<TypeUser> getTypes() {
		
		return typeUserDao.getTypes();
	}
}
