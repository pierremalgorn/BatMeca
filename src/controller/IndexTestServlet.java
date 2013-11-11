package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.SubMaterial;
import entity.Test;
import service.SubMaterialService;
import service.TestService;
import service.manager.ServiceManager;

/**
 * Servlet implementation class IndexTestServlet
 */
@WebServlet("/IndexTest")
public class IndexTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestService testService;
    private SubMaterialService subMaterialSevice;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexTestServlet() {
        super();
        testService = ServiceManager.INSTANCE.getTestService();
        subMaterialSevice = ServiceManager.INSTANCE.getSubMaterialService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idSub");
		System.out.println("id sub = "+id);
		SubMaterial sub = new SubMaterial();
		sub = subMaterialSevice.find(Integer.parseInt(id));
		List<Test> tests = testService.findBySub(sub);
		System.out.println("NB TEST = "+tests.size());
		request.setAttribute("sub", sub);
		request.setAttribute("tests", tests);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/indexTest.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
