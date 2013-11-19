package handler;

import java.io.File;

import entity.Test;

public class FolderHandler {
	
	private static String root;
	private File file;
	
	
	public FolderHandler() {
		super();
		root = "/home/max/BatMeca/Ressources";
		//file = new File(root+"/toto/tutu");
		//file.mkdirs();
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	/**
	 * useless
	 * */
	public void makeDir(String path){
		File f = new File(path);
		f.mkdir();
	}
	public String getPathSave(Test test){
		return this.root+"/"+test.getName();
	}

	public void deleteFolder(Test test){
		File f = new File(this.getPathSave(test));
		f.delete();
	}
	
	public void initDirectory(Test test){
		
		String path = this.getPathSave(test);

		File f = new File(path);
		f.mkdirs();
		this.makeDir(path+"/config");
		this.makeDir(path+"/data");
		;
	}
	
	public File[] getListDir(String path){
		File f = new File(path);
		return f.listFiles();
	}
	
	public String getFileNameData(Test t){
		File file = new File(this.getPathSave(t)+"/data");
		File[] files = file.listFiles();
		
		/*for (File f : files) {
			String name = f.getName();
		//	System.out.println("NAME FILE = "+name);
		}*/
		
		return files[files.length - 1].getName();
	}
	
	public String getFileNameConfig(Test t ){
		File file = new File(this.getPathSave(t)+"/config");
		File[] files = file.listFiles();
		
		/*for (File f : files) {
			String name = f.getName();
		//	System.out.println("NAME FILE = "+name);
		}*/
		
		return files[files.length - 1].getName();
	}
	
	
}
