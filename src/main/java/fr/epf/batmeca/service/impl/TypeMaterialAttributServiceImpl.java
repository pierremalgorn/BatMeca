package fr.epf.batmeca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.batmeca.dao.TypeMaterialAttributDao;
import fr.epf.batmeca.entity.TypeMaterialAttribute;
import fr.epf.batmeca.service.TypeMaterialAttributService;

@Service
public class TypeMaterialAttributServiceImpl implements
		TypeMaterialAttributService {

	@Autowired
	private TypeMaterialAttributDao typeMaterialAttributDao;

	@Override
	@Transactional(readOnly = true)
	public List<TypeMaterialAttribute> findAll() {
		return typeMaterialAttributDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TypeMaterialAttribute find(int id) {
		return typeMaterialAttributDao.find(id);
	}

	@Override
	@Transactional
	public boolean remove(TypeMaterialAttribute type) {
		return typeMaterialAttributDao.remove(type);
	}

	@Override
	@Transactional
	public boolean edit(TypeMaterialAttribute type) {
		return edit(type);
	}

	@Override
	@Transactional
	public boolean add(TypeMaterialAttribute type) {
		return typeMaterialAttributDao.add(type);
	}
}
