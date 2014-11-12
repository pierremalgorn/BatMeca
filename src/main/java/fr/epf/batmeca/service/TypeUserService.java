package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.TypeUser;

public interface TypeUserService {
	abstract List<TypeUser> getTypes();
}
