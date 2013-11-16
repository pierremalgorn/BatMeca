package service;

import java.util.List;

import entity.Material;

public interface MaterialService {
	abstract List<Material> findAll();
	abstract void addMaterial(Material mat);
	abstract Material find(int id);
	abstract void editMaterial(Material mat);
	abstract boolean remove(Material mat);

}
