package handler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import entity.MaterialAttribute;
import entity.Test;
import entity.TestAttribute;
import entity.TypeMaterialAttribute;
import entity.TypeTestAttribute;

public class ParserConfig {
	
	
	public Test parseFileConfig(Test test,String input,List<TypeMaterialAttribute> listAttrMat,List<TypeTestAttribute> listAttrTest) throws IOException{
		InputStream ips = new FileInputStream(input);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne = "";
		
		while ((ligne = br.readLine()) != null) {
			System.out.println(ligne);
			MaterialAttribute matAttr = this.getAttributMat(ligne, listAttrMat);
			if(matAttr != null){
				matAttr.setMaterial(test.getMaterial());
				test.getMaterial().addMaterialAttribute(matAttr);
				System.out.println("+++++++++++++++++++++++++++++++++++++");
				System.out.println("MATCH");
				System.out.println("+++++++++++++++++++++++++++++++++++++");
			}else{
				TestAttribute testAttr = this.getAttributTest(ligne, listAttrTest);
				if(testAttr != null){
					testAttr.setTest(test);
					test.addTestAttribute(testAttr);
					
					System.out.println("+++++++++++++++++++++++++++++++++++++");
					System.out.println("MATCH");
					System.out.println("+++++++++++++++++++++++++++++++++++++");
				}
			}
			
		}
		br.close();
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
	
}
