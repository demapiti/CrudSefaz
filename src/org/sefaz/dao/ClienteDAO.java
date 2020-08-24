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
	private PreparedStatement statement;
	private boolean retorno;

	private Connection fazerConn() throws SQLException {
		return Conn.getConnection();
	}
	
	public long inserir(Cliente cliente) throws SQLException {
		String sql = null;
		long fk_cliente = 0;
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		if(!checkEmail(cliente.getEmail()))
		{
			try {
				sql = "INSERT INTO clientes (id_cliente, nome, email, senha) VALUES (?,?,?,?)";
				statement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);

				statement.setString(1, null);
				statement.setString(2, cliente.getNome());
				statement.setString(3, cliente.getEmail());
				statement.setString(4, cliente.getSenha());

				retorno = statement.executeUpdate() > 0;
				if (retorno == false) {
					throw new SQLException("Ops, não foi possível criar um novo usuário.");
				}
			
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						fk_cliente = generatedKeys.getLong(1);
					}
					else {
						throw new SQLException("Não foi possível retornar um número de ID. Tente novamente.");
					}
				}
				connection.commit();
				statement.close();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				System.out.println("Fechando conexão...");
				connection.close();
			}
		}
		return fk_cliente;
	}

	public boolean editar(Cliente cliente) throws SQLException {
		String sql = null;
		retorno = false;
		connection = fazerConn();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE clientes SET nome=?, email=?, senha=? WHERE id_cliente=?";
			statement = connection.prepareStatement(sql);

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

	public boolean deletar(int idCliente) throws SQLException {
		String sql = null;
		retorno = false;
		connection = fazerConn();
		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM clientes WHERE id_cliente=?";
			statement = connection.prepareStatement(sql);

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

	public List<Cliente> listarClientes() throws SQLException {
		ResultSet resultSet = null;
		List<Cliente> listaClientes = new ArrayList<>();

		String sql = null;
		connection = fazerConn();

		try {
			sql = "SELECT * FROM clientes ORDER BY nome ASC";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Cliente c = new Cliente();
				c.setId_cliente(resultSet.getInt(1));
				c.setNome(resultSet.getString(2));
				c.setEmail(resultSet.getString(3));
				c.setSenha(resultSet.getString(4));
				listaClientes.add(c);
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

	public Cliente listarCliente(int id_cliente) throws SQLException {
		ResultSet resultSet = null;
		Cliente c = new Cliente();

		String sql = null;
		connection = fazerConn();

		try {
			sql = "SELECT * FROM clientes WHERE id_cliente=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id_cliente);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				c.setId_cliente(resultSet.getInt(1));
				c.setNome(resultSet.getString(2));
				c.setEmail(resultSet.getString(3));
				c.setSenha(resultSet.getString(4));
			}
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return c;
	}
	
	public Cliente clienteEntrar(Cliente c) throws SQLException {
		ResultSet resultSet = null;
		Cliente cliente = new Cliente();
		
		String sql = null;
		connection = fazerConn();
			
		try {
			sql = "SELECT * FROM clientes WHERE email=? AND senha=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, c.getEmail());
			statement.setString(2, c.getSenha());
			resultSet = statement.executeQuery();
				if (resultSet.next()) {
				cliente.setId_cliente(resultSet.getInt(1));
				cliente.setNome(resultSet.getString(2));
				cliente.setEmail(resultSet.getString(3));
				cliente.setSenha(resultSet.getString(4));
			}
			statement.close();
			resultSet.close();
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
		retorno = false;
		
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		PreparedStatement st = connection.prepareStatement("SELECT email FROM clientes WHERE email=?");
		st.setString(1, email);
		final ResultSet rset = st.executeQuery();
		if (rset.next())
		{
			retorno = true;
		}
		st.execute();
		st.close();
		return retorno;
	}
}
