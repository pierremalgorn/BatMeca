package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import entity.MaterialAttribute;
import entity.Test;
import entity.TestAttribute;
import entity.TypeMaterialAttribute;
import entity.TypeTestAttribute;

public class ParserConfig {
	
	
	public Test parseFileConfig(String root,Test test,String input,List<TypeMaterialAttribute> listAttrMat,List<TypeTestAttribute> listAttrTest) throws IOException{
		FolderHandler f = new FolderHandler(root);
		InputStream ips = new FileInputStream(input);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne = "";
		ArrayList<String[]> list = new ArrayList<String[]>();
		//ArrayList<String[]> listCol = new ArrayList<String[]>();
		String[] row = {"",""}; 
		while ((ligne = br.readLine()) != null) {
			System.out.println(ligne);
			MaterialAttribute matAttr = this.getAttributMat(ligne, listAttrMat);
			if(ligne.matches("^c_(.*)")){
			
				//listCol.add(ligne.split("\t"));
				//list.add(row);
			}
			else if(matAttr != null){
				matAttr.setMaterial(test.getMaterial());
				test.getMaterial().addMaterialAttribute(matAttr);
				
			}else{
				TestAttribute testAttr = this.getAttributTest(ligne, listAttrTest);
				if(testAttr != null){
					testAttr.setTest(test);
					test.addTestAttribute(testAttr);
					
				
				}
			}
			
		}
//		
//		for (String[] elem : listCol) {
//			//System.out.println("NB = "+Integer.parseInt(elem[1]));
//			//System.out.println("ELEM = "+elem[0]);
//			String[] tab = { elem[0],"" };
//			list.set(Integer.parseInt(elem[1]) -1 ,tab);
//		}
		
		br.close();
		//File file = new File(f.getPathSave(test)+"/ColValue.json");
		
		PrintWriter pr;

		//pr = new PrintWriter(new BufferedWriter(new FileWriter(f.getFileNameData(test)+"/ColValue.json")));
//		pr = new PrintWriter(file);
//		
//		pr.println(new Gson().toJson(list));
//
//		pr.close();
		
		return test;
	}
	
	public MaterialAttribute getAttributMat(String ligne,List<TypeMaterialAttribute> listAttrMat){
		MaterialAttribute matAttr = null;
		String[] attr = ligne.split("\t");
	
		//if(listAttrMat.contains(attr[0])){
			 
			for(TypeMaterialAttribute type : listAttrMat){
				if(type.getPattern().compareTo(attr[0]) == 0){
					matAttr = new MaterialAttribute();
					matAttr.setTypeMatAttr(type);
					matAttr.setValue(attr[1]);
					
				}
			//}
		}
		return matAttr;
	}
	
	public TestAttribute getAttributTest(String ligne,List<TypeTestAttribute> listAttrTest){
		TestAttribute matAttr = null;
		String[] attr = ligne.split("\t");
			
			for(TypeTestAttribute type : listAttrTest){
				if(type.getPattern().compareTo(attr[0]) == 0){
					matAttr = new TestAttribute();
					matAttr.setTypeTestAttr(type);
					matAttr.setValue(attr[1]);
				}
			}
		
		return matAttr;
	}
	
	public void parseHeader(String input,String output) throws IOException{
		InputStream ips = new FileInputStream(input);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		ArrayList<String[]> list = new ArrayList<String[]>();
		String ligne = "";

		while ((ligne = br.readLine()) != null) {
			list.add(ligne.split(",")); 
			
		}
		
		br.close();
		
		PrintWriter pr;

		pr = new PrintWriter(new BufferedWriter(new FileWriter(output)));

		pr.println(new Gson().toJson(list));

		pr.close();
	}
	
}
