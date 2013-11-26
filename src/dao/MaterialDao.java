package dao;

import java.util.List;

import entity.Material;
import entity.User;

public interface MaterialDao {
	
	
	public List<Material> findAll();
	public void addMaterial(Material mat);
	public Material find(int id);
	public void editMaterial(Material mat);
	public boolean remove(Material mat);
	public List<Material> findAllNoParent();
	public List<Material> findByParent(Material parent);
	public List<Material> findByUser(User user);
		
	
}
