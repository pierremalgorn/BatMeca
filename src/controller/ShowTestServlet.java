package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * Permet d'afficher un éssai
 * Servlet implementation class ShowTestServlet
 */
@WebServlet("/ShowTest")
public class ShowTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTestServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idTest = Integer.parseInt(request.getParameter("idTest"));
		Test t = testService.find(idTest);
		ServletContext context = getServletContext();
		CsvHandler csv = new CsvHandler(context.getInitParameter("root")+"/"+context.getInitParameter("name"));	
		
		FolderHandler f = new FolderHandler(context.getInitParameter("ressourcePath"));
		
		
	
		//Récuperation de l'header
		ArrayList<String[]> list =  f.deserializeFileJson(f.getPathSave(t)+"/header.json");
	
		
		//String data = csv.readAll(f.getPathSave(t)+"/dataInput.csv");
		
		//Recuperation des données et du nom des fichiers
		File[] files = f.listCurve(t);
		ArrayList<String[]> listData = new ArrayList<String[]>();
		ArrayList<String> listFile = new ArrayList<String>();
		for (File file : files) {
			
			System.out.println("NAME = "+file.getAbsolutePath());
			listFile.add(file.getAbsolutePath());
			String[] tab = {csv.readAll(file.getAbsolutePath()),file.getAbsolutePath()};
			listData.add(tab);
		}
		
		
		request.setAttribute("colHeader", list);
		
		request.setAttribute("files", files);
		//request.setAttribute("data", data);
		request.setAttribute("listData", listData);
		request.setAttribute("test", t);
		request.setAttribute("listFile", new Gson().toJson(listFile));
		//request.setAttribute("listCol", listCol);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/test.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
