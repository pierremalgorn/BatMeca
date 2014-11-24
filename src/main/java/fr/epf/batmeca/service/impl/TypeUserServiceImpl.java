package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epf.batmeca.dao.TypeUserDao;
import fr.epf.batmeca.entity.TypeUser;
import fr.epf.batmeca.service.TypeUserService;

@Service
public class TypeUserServiceImpl implements TypeUserService {
	@Autowired
	TypeUserDao typeUserDao;

	public TypeUserServiceImpl() {
	}

	@Override
	public List<TypeUser> getTypes() {
		return typeUserDao.getTypes();
	}
}
