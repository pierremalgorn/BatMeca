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

	private DaoManager(){

		emf = Persistence.createEntityManagerFactory("epfPU");
		materialDao = new MaterialDaoImpl();
		testDao = new TestDaoImpl();
		userDao = new UserDaoImpl();
		typeUserDao = new TypeUserDaoImpl();
		typeMaterialAttributDao = new TypeMaterialAttributDaoImpl();
		typeTestAttributDao = new TypeTestAttributDaoImpl();
	}

	

	

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

		
	public MaterialDao getMaterialDao(){
		return materialDao;
	}
	
	
	public TestDao getTestDao(){
		return testDao;
	}

	public UserDao getUserDao(){
		return userDao;
	}
	
	public TypeUserDao getTypeUserDao(){
		return typeUserDao;
	}


	public TypeMaterialAttributDao getTypeMaterialAttributDao() {
		return typeMaterialAttributDao;
	}


	public TypeTestAttributDao getTypeTestAttributDao() {
		return typeTestAttributDao;
	}


	
}
