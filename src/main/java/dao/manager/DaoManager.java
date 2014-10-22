package dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.MaterialDao;
import dao.TestDao;
import dao.TypeMaterialAttributDao;
import dao.TypeTestAttributDao;
import dao.UserDao;
import dao.TypeUserDao;
import dao.impl.MaterialDaoImpl;
import dao.impl.TestDaoImpl;
import dao.impl.TypeMaterialAttributDaoImpl;
import dao.impl.TypeTestAttributDaoImpl;
import dao.impl.UserDaoImpl;
import dao.impl.TypeUserDaoImpl;


public enum DaoManager {
	
	INSTANCE;
	
	
	private MaterialDao materialDao;
	private TestDao testDao;
	private UserDao userDao;
	private EntityManagerFactory emf;
	private TypeUserDao typeUserDao;
	private TypeMaterialAttributDao typeMaterialAttributDao ;
	private TypeTestAttributDao typeTestAttributDao;

	/**
	 * Constructeur de la classe
	 * */
	private DaoManager(){

		emf = Persistence.createEntityManagerFactory("epfPU");
		materialDao = new MaterialDaoImpl();
		testDao = new TestDaoImpl();
		userDao = new UserDaoImpl();
		typeUserDao = new TypeUserDaoImpl();
		typeMaterialAttributDao = new TypeMaterialAttributDaoImpl();
		typeTestAttributDao = new TypeTestAttributDaoImpl();
	}

	

	
	/**
	 * Permet de recuperer l'entity Manager
	 * @return EntityManagerFactory
	 * */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Permet de recuperer le MaterialDao
	 * @return MaterialDao
	 * */	
	public MaterialDao getMaterialDao(){
		return materialDao;
	}
	
	/**
	 * Permet de recuperer le TestDao
	 * @return TestDao
	 * */		
	public TestDao getTestDao(){
		return testDao;
	}
	/**
	 * Permet de recuperer le UserDao
	 * @return UserDao
	 * */	
	public UserDao getUserDao(){
		return userDao;
	}
	
	/**
	 * Permet de recuperer le TypeUserDao
	 * @return TypeUserDao
	 * */	
	public TypeUserDao getTypeUserDao(){
		return typeUserDao;
	}


	/**
	 * Permet de recuperer le TypeMaterialAttributDao
	 * @return TypeMaterialAttributDao
	 * */	
	public TypeMaterialAttributDao getTypeMaterialAttributDao() {
		return typeMaterialAttributDao;
	}

	/**
	 * Permet de recuperer le TypeTestAttributDao
	 * @return TypeTestAttributDao
	 * */	
	public TypeTestAttributDao getTypeTestAttributDao() {
		return typeTestAttributDao;
	}


	
}
