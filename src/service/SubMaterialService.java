package service;

import java.util.List;

import entity.Material;
import entity.SubMaterial;



public interface SubMaterialService {
	abstract List<SubMaterial> findByMat(Material mat);
}
