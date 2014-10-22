package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MaterialService;
import service.TypeMaterialAttributService;
import service.UserService;
import service.manager.ServiceManager;
import entity.Material;
import entity.MaterialAttribute;
import entity.TypeMaterialAttribute;
import entity.User;

/**
 * Servlet implementation class addMaterialServlet
 */
@WebServlet("/addMaterial")
public class AddMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MaterialService materialService;
	private TypeMaterialAttributService typeMaterialAttributService;
	private UserService userService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMaterialServlet() {
        super();
        materialService = ServiceManager.INSTANCE.getMaterialService();
        typeMaterialAttributService = ServiceManager.INSTANCE.getTypeMaterialAttributService();
        userService = ServiceManager.INSTANCE.getUserService();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Material> materials;
		//Récupération de la liste des materiaux
		materials = materialService.findAll();
		//Récupération de la liste des attribuuts d'un matériaux
		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();
		
		
		request.setAttribute("mats", materials);
		request.setAttribute("matAttrs", listAttr);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				response.encodeURL("/WEB-INF/addMaterial.jsp"));
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récuperation des champs
		String name = request.getParameter("inputName");
		Material mat = new Material();
		mat.setName(name);
		String parent = request.getParameter("inputMaterialParent");
		
		List<TypeMaterialAttribute> listAttr;
		listAttr = typeMaterialAttributService.findAll();
		
		mat.setMatAttrs(new HashSet<MaterialAttribute>());
			
		//Création des attributs du matériel
		for (TypeMaterialAttribute tMatAttr : listAttr) {
			MaterialAttribute matAttr = new MaterialAttribute();
			String nameAttr = request.getParameter("input"+tMatAttr.getName());
			if(nameAttr.compareTo("") != 0){
				System.out.println("add attr");
				matAttr.setValue(nameAttr);
				matAttr.setTypeMatAttr(tMatAttr);
				matAttr.setMaterial(mat);
				mat.addMaterialAttribute(matAttr);
			}
			
		}
		
		if(parent.compareTo("")!= 0){
			Material matParent = materialService.find(Integer.parseInt(parent));
			mat.setMaterialParent(matParent);
		}
		
		//association du materiel a l'utilisateur courant
		HttpSession session = request.getSession();
		mat.setUser((User) session.getAttribute("sessionUser"));
		
		materialService.addMaterial(mat);
		response.sendRedirect(response.encodeURL("./IndexMaterial"));
	}

}
