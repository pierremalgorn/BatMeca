package fr.epf.batmeca.service;

import java.io.IOException;
import java.util.List;

import fr.epf.batmeca.entity.Material;
import fr.epf.batmeca.entity.User;

public interface IMaterialService {
	List<Material> findAll();

	void addMaterial(Material mat);

	Material find(int id);

	void editMaterial(Material mat);

	boolean remove(Material mat) throws IOException;

	List<Material> findAllNoParent();

	List<Material> findByParent(Material parent);

	List<Material> findByUser(User user);
}
