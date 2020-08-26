package org.sefaz.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sefaz.dao.ClienteDAO;
import org.sefaz.dao.SaldoDAO;
import org.sefaz.dao.TelefoneDAO;
import org.sefaz.model.Cliente;
import org.sefaz.model.Saldo;
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
		} else if (op.contentEquals("menu")) {
			HttpSession session = request.getSession(false);
			
			if (session.getAttribute("cliente") == null)
			{
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
			else
			{
				request.getRequestDispatcher("views/menu.jsp").include(request, response);
			}
		} else if (op.contentEquals("index")) {
			request.getRequestDispatcher("index.jsp").include(request, response);
		} else if (op.contentEquals("listar")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			List<Cliente> lista = new ArrayList<>();
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			List<ArrayList<Telefone>> arrayTelefone = new ArrayList<>();
			SaldoDAO saldoDAO = new SaldoDAO();
			List<Saldo> saldo = new ArrayList<>();

			try {
				lista = clienteDAO.listarClientes();
				for (Cliente cliente : lista) {
					arrayTelefone.add((ArrayList<Telefone>) telefoneDAO.listarTelefone(cliente.getId_cliente()));
					saldo.add(saldoDAO.listarSaldo(cliente.getId_cliente()));
				}
				
				request.setAttribute("lista", lista);
				request.setAttribute("telefone", arrayTelefone);
				request.setAttribute("saldo", saldo);
				
				HttpSession session = request.getSession(false);
				
				if (session.getAttribute("cliente") == null)
				{
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
				else
				{
					request.getRequestDispatcher("views/listar.jsp").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Listar clientes.");
		} else if (op.contentEquals("editar")) {
			int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
			System.out.println("Editar id: " + id_cliente);

			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			List<Telefone> arrayTelefone = new ArrayList<>();

			try {
				cliente = clienteDAO.listarCliente(id_cliente);
				arrayTelefone = telefoneDAO.listarTelefone(cliente.getId_cliente());
				request.setAttribute("cliente", cliente);
				request.setAttribute("telefone", arrayTelefone);
				
				HttpSession session = request.getSession(false);
				
				if (session.getAttribute("cliente") == null)
				{
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
				else
				{
					request.getRequestDispatcher("views/editar.jsp").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (op.contentEquals("saldo")) {
			int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
			System.out.println("Adicionar saldo ao id: " + id_cliente);

			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			SaldoDAO saldoDAO = new SaldoDAO();
			Saldo saldo = new Saldo();

			try {
				cliente = clienteDAO.listarCliente(id_cliente);
				saldo = saldoDAO.listarSaldo(cliente.getId_cliente());
				request.setAttribute("cliente", cliente);
				request.setAttribute("saldo", saldo);
				
				HttpSession session = request.getSession(false);
				
				if (session.getAttribute("cliente") == null)
				{
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
				else
				{
					request.getRequestDispatcher("views/saldo.jsp").include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (op.contentEquals("deletar")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
			try {
				HttpSession session = request.getSession(false);
				
				if (session.getAttribute("cliente") == null)
				{
					request.getRequestDispatcher("index.jsp").include(request, response);
				}
				else
				{
					clienteDAO.deletar(id_cliente);
					System.out.println("Cliente deletado com sucesso!");
					if(id_cliente == Integer.parseInt(session.getAttribute("id_cliente").toString()))
					{
						request.setAttribute("status", "erro");
						request.setAttribute("statusMsg", "Ops! Parece que você deletou o seu próprio usuário.");
						request.getRequestDispatcher("sair").include(request, response);
					}
					else
					{
						request.getRequestDispatcher("cliente?op=listar").include(request, response);
					}
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
					
					if(request.getParameter("tipo2") != null)
					{
						telefone.setDdd(Integer.parseInt(request.getParameter("ddd2")));
						telefone.setNumero(request.getParameter("numero2"));
						telefone.setTipo(request.getParameter("tipo2"));
						telefone.setFk_id_cliente(fk_cliente);

						telefoneDAO.inserirTelefone(telefone);
					}

					request.setAttribute("status", "ok");
					request.setAttribute("statusMsg", "Cliente inserido com sucesso!");
					
					HttpSession session = request.getSession(false);
					if (session.getAttribute("cliente") == null)
					{
						request.getRequestDispatcher("index.jsp").include(request, response);
					}
					else
					{
						request.getRequestDispatcher("views/menu.jsp").include(request, response);
					}
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
			telefone.setId_telefone(Integer.parseInt(request.getParameter("id_telefone")));
			telefone.setDdd(Integer.parseInt(request.getParameter("ddd")));
			telefone.setNumero(request.getParameter("numero"));
			telefone.setTipo(request.getParameter("tipo"));
			telefone.setFk_id_cliente(Integer.parseInt(request.getParameter("id_cliente")));
			
			if(request.getParameter("tipo2") != null)
			{
				Telefone telefone2 = new Telefone();
				telefone2.setId_telefone(Integer.parseInt(request.getParameter("id_telefone2")));
				telefone2.setDdd(Integer.parseInt(request.getParameter("ddd2")));
				telefone2.setNumero(request.getParameter("numero2"));
				telefone2.setTipo(request.getParameter("tipo2"));
				telefone2.setFk_id_cliente(Integer.parseInt(request.getParameter("id_cliente")));
				
				try {
					telefoneDAO.editarTelefone(telefone2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

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
		} else if (op.contentEquals("saldo")) {
			SaldoDAO saldoDAO = new SaldoDAO();

			Saldo saldo = new Saldo();
			saldo.setSaldo(Float.parseFloat(request.getParameter("saldo")));
			saldo.setFk_id_cliente(Integer.parseInt(request.getParameter("id_cliente")));

			try {
				saldoDAO.adicionarSaldo(saldo);
				
				try {
					Saldo saldoAtt = new Saldo();
					saldoAtt = saldoDAO.listarSaldo(Integer.parseInt(request.getParameter("id_cliente")));
					
					HttpSession session = request.getSession(false);
					session.setAttribute("saldo", saldoAtt);
				} catch (Exception e) {}
				
				request.setAttribute("status", "ok");
				request.setAttribute("statusMsg", "Saldo adicionado com sucesso!");
				request.getRequestDispatcher("views/menu.jsp").include(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
