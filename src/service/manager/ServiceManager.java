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
	
	
	private ServiceManager(){
		
		materialService = new MaterialServiceImpl();
		testService = new TestServiceImpl();
		userService = new UserServiceImpl();
		typeUserService = new TypeUserServiceImpl();
		typeMaterialAttributService = new TypeMaterialAttributServiceImpl();
		typeTestAttributService = new TypeTestAttributServiceImpl();
	}
	
	public MaterialService getMaterialService(){
		return materialService;
	}
	
	
	
	public TestService getTestService(){
		return testService;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public TypeUserService getTypeUserService(){
		return typeUserService;
	}



	public TypeMaterialAttributService getTypeMaterialAttributService() {
		return typeMaterialAttributService;
	}

	public TypeTestAttributService getTypeTestAttributService() {
		return typeTestAttributService;
	}


}
