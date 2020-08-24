package org.sefaz.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sefaz.dao.ClienteDAO;
import org.sefaz.model.Cliente;

/**
 * Servlet implementation class EntrarController
 */
@WebServlet(description = "/EntrarController", urlPatterns = { "/entrar" })
public class EntrarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EntrarController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	//doGet(request, response);
    	
    	String op = request.getParameter("op");
    	if(op.contentEquals("entrar")) {
    		ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			
			cliente.setEmail(request.getParameter("email"));
			cliente.setSenha(request.getParameter("senha"));
			
			try {
				cliente = clienteDAO.clienteEntrar(cliente);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HttpSession session=request.getSession();
          
    		if(cliente.getId_cliente() != 0)
    		{
    			session.setAttribute("cliente", cliente);
    			session.setAttribute("status", "ok");
    			request.getRequestDispatcher("views/menu.jsp").include(request, response);
    		}  
    		else
    		{
    			session.setAttribute("status", "erro");
    			session.setAttribute("statusMsg", "E-mail ou senha incorreto.");
    			request.getRequestDispatcher("index.jsp").include(request, response);  
    		}
    	}
    }
}  