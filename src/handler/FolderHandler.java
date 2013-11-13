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
		return this.root+"/"+test.getSub().getMaterial().getName()+"/"+test.getSub().getName()+"/"+test.getName();
	}
	
	public void deleteFolder(Test test){
		File f = new File(this.getPathSave(test));
		f.delete();
	}
	
	public void initDirectory(Test test){
		
		String path = test.getSub().getMaterial().getName()+"/"+test.getSub().getName()+"/"+test.getName();
		System.out.println("PATH = "+path);
		File f = new File(this.root+"/"+path);
		f.mkdirs();
		this.makeDir(this.root+"/"+path+"/config");
		this.makeDir(this.root+"/"+path+"/data");
		this.makeDir(this.root+"/"+path+"/result");
		this.makeDir(this.root+"/"+path+"/temp");
		this.makeDir(this.root+"/"+path+"/log");
	}
	
	public File[] getListDir(String path){
		File f = new File(path);
		return f.listFiles();
	}
	
	
}
