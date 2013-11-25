package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Material;
import service.MaterialService;
import service.TestService;
import service.manager.ServiceManager;

/**
 * Servlet implementation class MaterialServlet
 */
@WebServlet("/Material")
public class MaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MaterialService materialService;
	 TestService testService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaterialServlet() {
        super();
        materialService = ServiceManager.INSTANCE.getMaterialService();
        testService = ServiceManager.INSTANCE.getTestService();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idMat");
		Material mat = null;
		
		mat = materialService.find(Integer.parseInt(id));

	
		request.setAttribute("material", mat);
	

		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/material.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
