package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.TypeMaterialAttribute;

public interface ITypeMaterialAttributService {
	List<TypeMaterialAttribute> findAll();

	TypeMaterialAttribute find(int id);

	boolean remove(TypeMaterialAttribute type);

	boolean edit(TypeMaterialAttribute type);

	boolean add(TypeMaterialAttribute type);
}
