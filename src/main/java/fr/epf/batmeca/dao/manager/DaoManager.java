package fr.epf.batmeca.dao.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DaoManager {

	INSTANCE;

	private EntityManagerFactory emf;

	/**
	 * Constructeur de la classe
	 * */
	private DaoManager() {
		emf = Persistence.createEntityManagerFactory("epfPU");
	}

	/**
	 * Permet de recuperer l'entity Manager
	 *
	 * @return EntityManagerFactory
	 * */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
