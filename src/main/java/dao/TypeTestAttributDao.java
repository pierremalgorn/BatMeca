package dao;

import java.util.List;

import entity.TypeTestAttribute;

public interface TypeTestAttributDao {
	/**
	 * Permet de recuperer l'ensemble de type
	 * 
	 * @return List<TypeTestAttribute> list
	 * */
	public List<TypeTestAttribute> findAll();

	/**
	 * permet de recupere un type present dans la base de données
	 * 
	 * @param int id : id du type
	 * @return TypeTestAttribute type
	 * */
	public TypeTestAttribute find(int id);

	/**
	 * Permet de supprimer un type
	 * 
	 * @param TypeMaterialAttribute
	 *            type: type a supprimer
	 * @return true si le type est supprimer false sinon
	 * */
	public boolean remove(TypeTestAttribute type);

	/**
	 * Permet de modifier un type
	 * 
	 * @param TypeTestAttribute
	 *            type: type a modifier
	 * @return true si le type est supprimer false sinon
	 * */
	public boolean edit(TypeTestAttribute type);

	/**
	 * permet d'ajouter un type dans la base de données
	 * 
	 * @param TypeMaterialAttribute
	 *            type : type à ajouter
	 * */
	public boolean add(TypeTestAttribute type);
}
