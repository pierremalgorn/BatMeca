package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TestService;
import service.manager.ServiceManager;
import entity.Test;

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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Récuperation de l'abscisse et de l'ordonnées*/
		int x = Integer.parseInt(request.getParameter("inputX"));
		int y = Integer.parseInt(request.getParameter("inputY"));
		
		ServletContext context = getServletContext();
		Test test = testService.find(Integer.parseInt(request.getParameter("inputId")));
		FolderHandler f =  new FolderHandler(context.getInitParameter("ressourcePath"));
		CsvHandler csv = new CsvHandler(context.getInitParameter("root")+"/"+context.getInitParameter("name"));
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
			try {
				csv.selectCurve(f.getPathSave(test)+"/dataInput.csv", f.getPathSave(test)+"/curve/"+x+"-"+y+".csv", x, y);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
