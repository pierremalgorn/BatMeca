package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.TypeTestAttribute;

public interface TypeTestAttributService {
	abstract List<TypeTestAttribute> findAll();

	abstract TypeTestAttribute find(int id);

	abstract boolean remove(TypeTestAttribute type);

	abstract boolean edit(TypeTestAttribute type);

	abstract boolean add(TypeTestAttribute type);
}