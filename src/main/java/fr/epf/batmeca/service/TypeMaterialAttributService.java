package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.TypeMaterialAttribute;

public interface TypeMaterialAttributService {
	abstract List<TypeMaterialAttribute> findAll();

	abstract TypeMaterialAttribute find(int id);

	abstract boolean remove(TypeMaterialAttribute type);

	abstract boolean edit(TypeMaterialAttribute type);

	abstract boolean add(TypeMaterialAttribute type);
}
