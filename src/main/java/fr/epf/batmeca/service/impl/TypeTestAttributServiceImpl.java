package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.epf.batmeca.dao.TypeTestAttributDao;
import fr.epf.batmeca.entity.TypeTestAttribute;
import fr.epf.batmeca.service.TypeTestAttributService;

@Service
public class TypeTestAttributServiceImpl implements TypeTestAttributService {

	@Autowired
	private TypeTestAttributDao typeDao;

	public TypeTestAttributServiceImpl() {
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
		return typeDao.remove(type);
	}

	@Override
	public boolean edit(TypeTestAttribute type) {
		return typeDao.edit(type);
	}

	@Override
	public boolean add(TypeTestAttribute type) {
		return typeDao.add(type);
	}
}
