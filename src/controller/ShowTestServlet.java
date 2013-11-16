package controller;

import handler.CsvHandler;
import handler.FolderHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		CsvHandler csv = new CsvHandler();	
		FolderHandler f = new FolderHandler();
		
		String path = f.getPathSave(t);
		File[] filesConf = f.getListDir(path+"/config");
		File[] filesData = f.getListDir(path+"/data");
		File[] filesTemp = f.getListDir(path+"/temp");
		File[] filesRes = f.getListDir(path+"/result");
		
		String data = csv.readAll(f.getPathSave(t)+"/data.csv");
		
		request.setAttribute("data", data);
		request.setAttribute("test", t);
		request.setAttribute("filesConf", filesConf);
		request.setAttribute("filesData", filesData);
		request.setAttribute("filesTemp", filesTemp);
		request.setAttribute("filesRes", filesRes);
		
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
