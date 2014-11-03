package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TypeUserService;
import dao.TypeUserDao;
import entity.TypeUser;

@Service
public class TypeUserServiceImpl implements TypeUserService {
	@Autowired
	TypeUserDao typeUserDao;

	public TypeUserServiceImpl() {
	}

	public List<TypeUser> getTypes() {
		return typeUserDao.getTypes();
	}
}
