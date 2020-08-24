package org.sefaz.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sefaz.dao.ClienteDAO;
import org.sefaz.dao.TelefoneDAO;
import org.sefaz.model.Cliente;
import org.sefaz.model.Telefone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ClienteController
 */
@WebServlet(description = "Gerenciar solicitações para a tabela de clientes.", urlPatterns = { "/cliente" })
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String op = request.getParameter("op");

		if (op.contentEquals("inserir")) {
			System.out.println("Inserir cliente.");
			request.getRequestDispatcher("views/inserir.jsp").include(request, response);
		} else if (op.contentEquals("listar")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			List<Cliente> lista = new ArrayList<>();
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			List<Telefone> telefone = new ArrayList<>();

			try {
				lista = clienteDAO.listarClientes();
				for (Cliente cliente : lista) {
					telefone.add(telefoneDAO.listarTelefone(cliente.getId_cliente()));
				}

				request.setAttribute("lista", lista);
				request.setAttribute("telefone", telefone);
				request.getRequestDispatcher("views/listar.jsp").include(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Listar clientes.");
		} else if (op.contentEquals("editar")) {
			int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
			System.out.println("Editar id: " + id_cliente);

			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente c = new Cliente();
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			Telefone telefone = new Telefone();

			try {
				c = clienteDAO.listarCliente(id_cliente);
				telefone = telefoneDAO.listarTelefone(c.getId_cliente());
				request.setAttribute("cliente", c);
				request.setAttribute("telefone", telefone);
				request.getRequestDispatcher("views/editar.jsp").include(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (op.contentEquals("deletar")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
			try {
				HttpSession session = request.getSession(false);
				if (session.getAttribute("cliente") == null) {
					request.getRequestDispatcher("index.jsp").include(request, response);
				} else {
					clienteDAO.deletar(id_cliente);
					System.out.println("Cliente deletado com sucesso!");
					request.getRequestDispatcher("cliente?op=listar").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		String op = request.getParameter("op");

		if (op.contentEquals("inserir")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			TelefoneDAO telefoneDAO = new TelefoneDAO();

			Cliente cliente = new Cliente();
			cliente.setNome(request.getParameter("nome"));
			cliente.setEmail(request.getParameter("email"));
			cliente.setSenha(request.getParameter("senha"));
			try {
				int fk_cliente = (int) clienteDAO.inserir(cliente);
				if(fk_cliente > 0)
				{
					Telefone telefone = new Telefone();
					telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
					telefone.setNumero(request.getParameter("numero"));
					telefone.setTipo(request.getParameter("tipo"));
					telefone.setFk_id_cliente(fk_cliente);

					telefoneDAO.inserirTelefone(telefone);

					request.setAttribute("status", "ok");
					request.setAttribute("statusMsg", "Cliente inserido com sucesso!");
					request.getRequestDispatcher("views/menu.jsp").include(request, response);
				}
				else
				{
					request.setAttribute("status", "erro");
					request.setAttribute("statusMsg", "E-mail já cadastrado.");
					request.getRequestDispatcher("views/inserir.jsp").include(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (op.contentEquals("editar")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			TelefoneDAO telefoneDAO = new TelefoneDAO();

			Cliente cliente = new Cliente();
			cliente.setId_cliente(Integer.parseInt(request.getParameter("id_cliente")));
			cliente.setNome(request.getParameter("nome"));
			cliente.setEmail(request.getParameter("email"));
			cliente.setSenha(request.getParameter("senha"));

			Telefone telefone = new Telefone();
			telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
			telefone.setNumero(request.getParameter("numero"));
			telefone.setTipo(request.getParameter("tipo"));
			telefone.setFk_id_cliente(Integer.parseInt(request.getParameter("id_cliente")));

			try {
				clienteDAO.editar(cliente);
				telefoneDAO.editarTelefone(telefone);
				request.setAttribute("status", "ok");
				request.setAttribute("statusMsg", "Cliente editado com sucesso!");
				request.getRequestDispatcher("views/menu.jsp").include(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
