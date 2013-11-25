package dao;

import java.util.List;

import entity.TypeTestAttribute;

public interface TypeTestAttributDao {
	public List<TypeTestAttribute> findAll();
	public TypeTestAttribute find(int id);
	public boolean remove(TypeTestAttribute type);
	public boolean edit(TypeTestAttribute type);
	public boolean add(TypeTestAttribute type);
}
