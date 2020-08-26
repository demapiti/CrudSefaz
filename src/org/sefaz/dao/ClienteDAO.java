package org.sefaz.dao;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sefaz.conn.Conn;
import org.sefaz.model.Cliente;

public class ClienteDAO {
	private Connection connection;
	private boolean retorno = false;

	private Connection fazerConn() throws SQLException {
		return Conn.getConnection();
	}
	
	public long inserir(Cliente cliente) throws SQLException
	{
		long fk_cliente = 0;
		
		if(!checkEmail(cliente.getEmail()))
		{
			connection = fazerConn();
			connection.setAutoCommit(false);
			
			try {
				PreparedStatement statement = connection.prepareStatement("INSERT INTO clientes (nome, email, senha) VALUES (?,?,?)", RETURN_GENERATED_KEYS);
		
				statement.setString(1, cliente.getNome());
				statement.setString(2, cliente.getEmail());
				statement.setString(3, cliente.getSenha());

				retorno = statement.executeUpdate() > 0;
				if (retorno == false) {
					throw new SQLException("Ops, não foi possível criar um novo usuário.");
				}
			
				try (ResultSet generatedKeys = statement.getGeneratedKeys())
				{
					if (generatedKeys.next())
					{
						fk_cliente = generatedKeys.getLong(1);
					}
					else
					{
						throw new SQLException("Não foi possível retornar um número de ID. Tente novamente.");
					}
				}
				connection.commit();
				statement.close();
			} catch (SQLException e){
				connection.rollback();
				e.printStackTrace();
			} finally {
				System.out.println("Fechando conexão...");
				connection.close();
			}
		}
		return fk_cliente;
	}

	public boolean editar(Cliente cliente) throws SQLException
	{
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE clientes SET nome=?, email=?, senha=? WHERE id_cliente=?");

			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getEmail());
			statement.setString(3, cliente.getSenha());
			statement.setInt(4, cliente.getId_cliente());

			retorno = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return retorno;
	}

	public boolean deletar(int idCliente) throws SQLException
	{
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		try {
			
			PreparedStatement statement = connection.prepareStatement("DELETE FROM clientes WHERE id_cliente=?");
			statement.setInt(1, idCliente);

			retorno = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return retorno;
	}

	public List<Cliente> listarClientes() throws SQLException
	{
		connection = fazerConn();
		
		List<Cliente> listaClientes = new ArrayList<>();

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes ORDER BY nome ASC");
			
			ResultSet rset = statement.executeQuery();
			while (rset.next())
			{
				Cliente cliente = new Cliente();
				cliente.setId_cliente(rset.getInt("id_cliente"));
				cliente.setNome(rset.getString("nome"));
				cliente.setEmail(rset.getString("email"));
				cliente.setSenha(rset.getString("senha"));
				listaClientes.add(cliente);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return listaClientes;
	}

	public Cliente listarCliente(int id_cliente) throws SQLException
	{
		connection = fazerConn();
		
		Cliente cliente = new Cliente();

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes WHERE id_cliente=?");
			statement.setInt(1, id_cliente);
			
			ResultSet rset = statement.executeQuery();
			if (rset.next())
			{
				cliente.setId_cliente(rset.getInt("id_cliente"));
				cliente.setNome(rset.getString("nome"));
				cliente.setEmail(rset.getString("email"));
				cliente.setSenha(rset.getString("senha"));
			}
			statement.close();
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return cliente;
	}
	
	public Cliente clienteEntrar(Cliente c) throws SQLException
	{
		connection = fazerConn();
		
		Cliente cliente = new Cliente();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes WHERE email=? AND senha=?");
			statement.setString(1, c.getEmail());
			statement.setString(2, c.getSenha());
			
			ResultSet rset = statement.executeQuery();
			if (rset.next())
			{
				cliente.setId_cliente(rset.getInt("id_cliente"));
				cliente.setNome(rset.getString("nome"));
				cliente.setEmail(rset.getString("email"));
				cliente.setSenha(rset.getString("senha"));
			}
			statement.close();
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return cliente;
	}
	
	public boolean checkEmail(String email) throws SQLException
	{
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT email FROM clientes WHERE email=?");
			statement.setString(1, email);
		
			ResultSet rset = statement.executeQuery();
			if (rset.next())
			{
				retorno = true;
			}
			statement.execute();
			statement.close();
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return retorno;
	}
}
