package service;

import java.util.List;

import entity.Material;
import entity.SubMaterial;



public interface SubMaterialService {
	abstract List<SubMaterial> findByMat(Material mat);
	abstract void add(SubMaterial sub);
	abstract void remove(int id);
	abstract SubMaterial find(int id);
}
