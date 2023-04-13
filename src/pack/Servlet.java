package pack;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Facade facade;

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String op = request.getParameter("op");
		if (op.equals("inscrire")) {
		    String nom = request.getParameter("nom");
		    String prenom = request.getParameter("prenom");
		    String email = request.getParameter("email");
		    String motDePasse = request.getParameter("motDePasse");
		    String motDePasse_copy = request.getParameter("motDePasse_copy");
		    
		    boolean formatcorrect = facade.verifierInscription(nom, prenom, motDePasse, motDePasse_copy, email);
		    if (formatcorrect) {
		    	Compte c = new Compte(nom, prenom, motDePasse, email);
		    	facade.ajouterCompte(c);
		    }else {
		    	System.out.print("Problem rencontré");
		    }
		}
	}

}
