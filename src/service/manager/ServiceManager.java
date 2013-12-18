package service.manager;

import java.util.List;

import entity.TypeMaterialAttribute;
import service.MaterialService;
import service.TestService;
import service.TypeMaterialAttributService;
import service.TypeTestAttributService;
import service.UserService;
import service.TypeUserService;
import service.impl.MaterialServiceImpl;
import service.impl.TestServiceImpl;
import service.impl.TypeMaterialAttributServiceImpl;
import service.impl.TypeTestAttributServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.TypeUserServiceImpl;

public enum ServiceManager {
	INSTANCE;
	
	
	private MaterialService materialService;
	private TypeMaterialAttributService typeMaterialAttributService ;
	private TestService testService;
	private UserService userService;
	private TypeUserService typeUserService;
	private TypeTestAttributService typeTestAttributService;
	
	
	/**
	 * Constructeur de la classe
	 * */
	private ServiceManager(){
		
		materialService = new MaterialServiceImpl();
		testService = new TestServiceImpl();
		userService = new UserServiceImpl();
		typeUserService = new TypeUserServiceImpl();
		typeMaterialAttributService = new TypeMaterialAttributServiceImpl();
		typeTestAttributService = new TypeTestAttributServiceImpl();
	}
	
	/**
	 * permet de recuperer le Service material 
	 * @return MaterialService 
	 * */
	public MaterialService getMaterialService(){
		return materialService;
	}
	
	
	/**
	 * Permet de recuperer le service Test
	 * @return TestService 
	 * */
	public TestService getTestService(){
		return testService;
	}
	
	/**
	 * Permet de recuperer le service user
	 * @return UserService
	 * */
	public UserService getUserService(){
		return userService;
	}
	
	/**
	 * Permet de recuperer le service de type user
	 * @return TypeUserService
	 * */
	public TypeUserService getTypeUserService(){
		return typeUserService;
	}


	/**
	 * Permet de recuperer le service des types materiel attribut
	 * @return TypeMaterialAttributService
	 * */
	public TypeMaterialAttributService getTypeMaterialAttributService() {
		return typeMaterialAttributService;
	}

	/**
	 * Permet de recuperer le service des types test attribut
	 * @return TypeMaterialAttributService
	 * */
	public TypeTestAttributService getTypeTestAttributService() {
		return typeTestAttributService;
	}


}
