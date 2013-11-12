package dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.MaterialDao;
import dao.SubMaterialDao;
import dao.TestDao;
import dao.UserDao;
import dao.impl.MaterialDaoImpl;
import dao.impl.SubMaterialDaoImpl;
import dao.impl.TestDaoImpl;
import dao.impl.UserDaoImpl;



public enum DaoManager {
	
	INSTANCE;
	
	
	private MaterialDao materialDao;
	private SubMaterialDao subMaterialDao;
	private TestDao testDao;
	private UserDao userDao;
	private EntityManagerFactory emf;

	private DaoManager(){

		emf = Persistence.createEntityManagerFactory("epfPU");
		materialDao = new MaterialDaoImpl();
		subMaterialDao = new SubMaterialDaoImpl();
		testDao = new TestDaoImpl();
		userDao = new UserDaoImpl();
	}

	

	

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

		
	public MaterialDao getMaterialDao(){
		return materialDao;
	}
	
	public SubMaterialDao getSubMaterialDao(){
		return subMaterialDao;
	}
	
	public TestDao getTestDao(){
		return testDao;
	}

	public UserDao getUserDao(){
		return userDao;
	}
	
}
