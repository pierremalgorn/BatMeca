package dao;

import java.util.List;

import entity.TypeMaterialAttribute;

public interface TypeMaterialAttributDao {
	public List<TypeMaterialAttribute> findAll();
	public TypeMaterialAttribute find(int id);
	public boolean remove(TypeMaterialAttribute type);
	public boolean edit(TypeMaterialAttribute type);
	public boolean add(TypeMaterialAttribute type);
}
