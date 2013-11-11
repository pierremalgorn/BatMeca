package dao;

import java.util.List;

import entity.Material;
import entity.SubMaterial;

public interface SubMaterialDao {

	public List<SubMaterial> findByMaterial(Material mat);
	public void add(SubMaterial sub);
	public void remove(int id);
	public SubMaterial find(int id);
}
