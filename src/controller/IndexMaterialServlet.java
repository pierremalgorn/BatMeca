package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Material;
import entity.User;
import service.MaterialService;
import service.manager.ServiceManager;



/**
 * Servlet implementation class IndexMaterial
 */
@WebServlet("/IndexMaterial")
public class IndexMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MaterialService materialService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexMaterialServlet() {
        super();
        materialService = ServiceManager.INSTANCE.getMaterialService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Material> list = null;
		
		/**
		 * Récuepration de la liste des materiau
		 * */
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("sessionUser");
		if(user.getType().getId() == 1){
			list = materialService.findAllNoParent();
		}else{
			list = materialService.findByUser((User) session.getAttribute("sessionUser"));
		}
		
		 request.setAttribute("materials",list);
		 System.out.println("TAILLE LIST = "+list.size());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/indexMaterial.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
