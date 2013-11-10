package dao;

import java.util.List;

import entity.Material;
import entity.SubMaterial;

public interface SubMaterialDao {

	public List<SubMaterial> findByMaterial(Material mat);
}
