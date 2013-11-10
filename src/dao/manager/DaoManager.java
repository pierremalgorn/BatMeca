package dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.MaterialDao;
import dao.SubMaterialDao;
import dao.impl.MaterialDaoImpl;
import dao.impl.SubMaterialDaoImpl;



public enum DaoManager {
	
	INSTANCE;
	
	
	private MaterialDao materialDao;
	private SubMaterialDao subMaterialDao;
	private EntityManagerFactory emf;

	private DaoManager(){

		emf = Persistence.createEntityManagerFactory("epfPU");
		materialDao = new MaterialDaoImpl();
		subMaterialDao = new SubMaterialDaoImpl();
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

	

	
	
	
}
