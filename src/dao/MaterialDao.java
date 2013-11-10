package dao;

import java.util.List;

import entity.Material;

public interface MaterialDao {
	
	
	public List<Material> findAll();
	public void addMaterial(Material mat);
	public Material find(int id);
}