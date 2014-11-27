package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.ITypeUserDao;
import fr.epf.batmeca.entity.TypeUser;
import fr.epf.batmeca.service.ITypeUserService;

@Service
public class TypeUserServiceImpl implements ITypeUserService {

	@Autowired
	ITypeUserDao typeUserDao;

	@Override
	@Transactional(readOnly = true)
	public List<TypeUser> getTypes() {
		return typeUserDao.getTypes();
	}
}
