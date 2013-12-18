package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Test;
import service.TestService;
import service.manager.ServiceManager;

/**
 * Permet de selectioner un courbe
 * Servlet implementation class SelectRowServlet
 */
@WebServlet("/SelectRow")
public class SelectRowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectRowServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Code de test
		 * int id = Integer.parseInt(request.getParameter("idTest"));
		Test test = testService.find(id);
		FolderHandler f =  new FolderHandler();
		CsvHandler csv = new CsvHandler();
		
		csv.selectCurve(f.getPathSave(test)+"/dataInput.csv", f.getPathSave(test)+"/curve/curve1.csv", 1, 4);
		File[] list = f.listCurve(test);
		int nbCurve = list.length;
		System.out.println("nbCurve");
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Récuperation de l'abscisse et de l'ordonnées*/
		int x = Integer.parseInt(request.getParameter("inputX"));
		int y = Integer.parseInt(request.getParameter("inputY"));
		
		Test test = testService.find(Integer.parseInt(request.getParameter("inputId")));
		FolderHandler f =  new FolderHandler();
		CsvHandler csv = new CsvHandler();
		File[] list = f.listCurve(test);
		int nbCurve = list.length;
		//Verification si le tracer n'a pas deja été éffectuer
		boolean content = false;
		for (File file : list) {
			if(file.getName().compareTo(x+"-"+y+".csv") == 0){
				content = true;
			}
		}
		if(content == false){
			//Création de la courbe
			csv.selectCurve(f.getPathSave(test)+"/dataInput.csv", f.getPathSave(test)+"/curve/"+x+"-"+y+".csv", x, y);
			String data = csv.readAll(f.getPathSave(test)+"/curve/"+x+"-"+y+".csv");
			response.setContentType("application/json");
			response.getWriter().write("{\"data\":"+data+",\"nbCurve\":"+(nbCurve +1)+",\"nameFile\":\""+x+"-"+y+".csv\"}");
		}else{
			response.setContentType("application/json");
			response.getWriter().write("{\"content\":true}");
		}
		
		//response.getWriter().write((new Gson().toJson(nbCurve)));
	
		
		
	}

}
