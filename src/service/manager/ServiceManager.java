package service.manager;

import service.MaterialService;
import service.SubMaterialService;
import service.TestService;
import service.impl.MaterialServiceImpl;
import service.impl.SubMaterialServiceImpl;
import service.impl.TestServiceImpl;


public enum ServiceManager {
	INSTANCE;
	

	private MaterialService materialService;
	private SubMaterialService subMaterialService;
	private TestService testService;
	
	private ServiceManager(){
		
		materialService = new MaterialServiceImpl();
		subMaterialService = new SubMaterialServiceImpl();
		testService = new TestServiceImpl();
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
	
	

}
