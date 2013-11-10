package service.manager;

import service.MaterialService;
import service.SubMaterialService;
import service.impl.MaterialServiceImpl;
import service.impl.SubMaterialServiceImpl;


public enum ServiceManager {
	INSTANCE;
	

	private MaterialService materialService;
	private SubMaterialService subMaterialService;
	
	private ServiceManager(){
		
		materialService = new MaterialServiceImpl();
		subMaterialService = new SubMaterialServiceImpl();
	}
	

	
	public MaterialService getMaterialService(){
		return materialService;
	}
	
	public SubMaterialService getSubMaterialService(){
		return subMaterialService;
	}
	
	

}
