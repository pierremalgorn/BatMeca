package handler;

import java.io.File;

import entity.Test;

public class FolderHandler {
	
	private static String root;
	private File file;
	
	
	public FolderHandler() {
		super();
		root = "C:\\Users\\max\\workspace\\Ressource";
		file = new File("C:\\Users\\max\\workspace\\Ressource\\test");
		file.mkdir();
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public void initTest(Test test){
		//File.mkdir("test");
	}
	
}
