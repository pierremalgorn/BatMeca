package controller;

import handler.FolderHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TestService;
import service.manager.ServiceManager;

import com.google.gson.Gson;

import entity.Test;

/**
 * Permet de créer un courbe en sélectionnant l'abscisse et l'ordonnée
 * Servlet implementation class ColValueServlet
 */
@WebServlet("/ColValue")
public class ColValueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColValueServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nbCol = Integer.parseInt(request.getParameter("nbField"));
		int id = Integer.parseInt(request.getParameter("inputId"));
		ServletContext context = getServletContext();
		FolderHandler f = new FolderHandler(context.getInitParameter("ressourcePath"));
		Test test = testService.find(id);
		ArrayList<String[]> list = new ArrayList<String[]>(); 
		String[] elem = new String[nbCol];
		String[] unit = new String[nbCol];
		for(int i = 0;i < nbCol ; i ++){
			
			elem[i] =  request.getParameter("nameCol"+i);
			unit[i] = request.getParameter("unit"+i);
			
		}
		
		list.add(elem);
		list.add(unit);
		
		f.saveToJson(list, f.getPathSave(test)+"/header.json");
		
		response.getWriter().write(new Gson().toJson(list));
	}

}
