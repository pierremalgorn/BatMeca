package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.TypeMaterialAttributService;
import dao.TypeMaterialAttributDao;
import entity.TypeMaterialAttribute;

@Service
public class TypeMaterialAttributServiceImpl implements
		TypeMaterialAttributService {

	@Autowired
	private TypeMaterialAttributDao typeMaterialAttributDao;

	public TypeMaterialAttributServiceImpl() {
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
