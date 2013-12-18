package dao;

import java.util.List;

import entity.TypeMaterialAttribute;

public interface TypeMaterialAttributDao {
	/**
	 * Permet de recuperer l'ensemble de type
	 * @return List<TypeMaterialAttribute> list
	 * */
	public List<TypeMaterialAttribute> findAll();
	/**
	 * permet de recupere un type present dans la base de données
	 * @param  int id : id du type
	 * @return TypeMaterialAttribute type
	 * */
	public TypeMaterialAttribute find(int id);
	
	/**
	 * Permet de supprimer un type
	 * @param TypeMaterialAttribute type: type a supprimer
	 * @return true si le type est supprimer false sinon
	 * */
	public boolean remove(TypeMaterialAttribute type);
	
	/**
	 * Permet de modifier un type
	 * @param TypeMaterialAttribute type: type a modifier
	 * @return true si le type est supprimer false sinon
	 * */
	public boolean edit(TypeMaterialAttribute type);
	/**
	 * permet d'ajouter un type dans la base de données
	 * @param TypeMaterialAttribute type : type à ajouter
	 * */
	public boolean add(TypeMaterialAttribute type);
}
