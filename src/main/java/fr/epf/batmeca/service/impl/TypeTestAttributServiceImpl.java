package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.ITypeTestAttributDao;
import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.service.ITypeTestAttributService;

@Service
public class TypeTestAttributServiceImpl implements ITypeTestAttributService {

	@Autowired
	private ITypeTestAttributDao typeDao;

	@Override
	@Transactional(readOnly = true)
	public List<TypeTestAttribute> findAll() {
		return typeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TypeTestAttribute find(int id) {
		return typeDao.find(id);
	}

	@Override
	@Transactional
	public boolean remove(TypeTestAttribute type) {
		return typeDao.remove(type);
	}

	@Override
	@Transactional
	public boolean edit(TypeTestAttribute type) {
		return typeDao.edit(type);
	}

	@Override
	@Transactional
	public boolean add(TypeTestAttribute type) {
		return typeDao.add(type);
	}
}
