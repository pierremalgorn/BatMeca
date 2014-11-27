package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.TypeTestAttribute;

public interface ITypeTestAttributService {
	List<TypeTestAttribute> findAll();

	TypeTestAttribute find(int id);

	boolean remove(TypeTestAttribute type);

	boolean edit(TypeTestAttribute type);

	boolean add(TypeTestAttribute type);
}
