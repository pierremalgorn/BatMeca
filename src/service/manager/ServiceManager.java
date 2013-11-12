package service.manager;

import service.MaterialService;
import service.SubMaterialService;
import service.TestService;
import service.UserService;
import service.impl.MaterialServiceImpl;
import service.impl.SubMaterialServiceImpl;
import service.impl.TestServiceImpl;
import service.impl.UserServiceImpl;


public enum ServiceManager {
	INSTANCE;
	

	private MaterialService materialService;
	private SubMaterialService subMaterialService;
	private TestService testService;
	private UserService userService;
	
	private ServiceManager(){
		
		materialService = new MaterialServiceImpl();
		subMaterialService = new SubMaterialServiceImpl();
		testService = new TestServiceImpl();
		userService = new UserServiceImpl();
	}
	

	
	public MaterialService getMaterialService(){
		return materialService;
	}
	
	public SubMaterialService getSubMaterialService(){
		return subMaterialService;
	}
	
	public TestService getTestService(){
		return testService;
	}
	
	public UserService getUserService(){
		return userService;
	}
	

}
