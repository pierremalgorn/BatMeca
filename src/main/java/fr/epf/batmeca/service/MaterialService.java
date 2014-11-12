package fr.epf.batmeca.service;

import java.util.List;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;

public interface MaterialService {
	abstract List<Material> findAll();

	abstract void addMaterial(Material mat);

	abstract Material find(int id);

	abstract void editMaterial(Material mat);

	abstract boolean remove(Material mat);

	abstract List<Material> findAllNoParent();

	abstract List<Material> findByParent(Material parent);

	abstract List<Material> findByUser(User user);
}
