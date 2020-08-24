package org.sefaz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sefaz.conn.Conn;
import org.sefaz.model.Telefone;

public class TelefoneDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean retorno;

	private Connection fazerConn() throws SQLException {
		return Conn.getConnection();
	}

	public boolean inserirTelefone(Telefone telefone) throws SQLException {
		String sql = null;
		retorno = false;
		connection = fazerConn();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO telefone (id_telefone, ddd, numero, tipo, fk_id_cliente) VALUES (?,?,?,?,?)";

			statement = connection.prepareStatement(sql);

			statement.setString(1, null);
			statement.setInt(2, telefone.getDdd());
			statement.setString(3, telefone.getNumero());
			statement.setString(4, telefone.getTipo());
			statement.setInt(5, telefone.getFk_id_cliente());

			retorno = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return retorno;
	}

	public boolean editarTelefone(Telefone telefone) throws SQLException {
		String sql = null;
		retorno = false;
		connection = fazerConn();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE telefone SET ddd=?, numero=?, tipo=? WHERE fk_id_cliente=?";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, telefone.getDdd());
			statement.setString(2, telefone.getNumero());
			statement.setString(3, telefone.getTipo());
			statement.setInt(4, telefone.getFk_id_cliente());

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

	public Telefone listarTelefone(int id_cliente) throws SQLException {
		ResultSet resultSet = null;
		Telefone telefone = new Telefone();

		String sql = null;
		connection = fazerConn();

		sql = "SELECT * FROM telefone WHERE fk_id_cliente=?";

		statement = connection.prepareStatement(sql);
		statement.setInt(1, id_cliente);

		resultSet = statement.executeQuery();
		try {
			if (resultSet.next()) {
				telefone.setId_telefone(resultSet.getInt(1));
				telefone.setDdd(resultSet.getInt(2));
				telefone.setNumero(resultSet.getString(3));
				telefone.setTipo(resultSet.getString(4));
				telefone.setFk_id_cliente(resultSet.getInt(5));
			}

			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return telefone;
	}

}
