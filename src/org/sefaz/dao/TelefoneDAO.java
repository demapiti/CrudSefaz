package org.sefaz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sefaz.conn.Conn;
import org.sefaz.model.Telefone;

public class TelefoneDAO {
	private Connection connection;
	private boolean retorno = false;

	private Connection fazerConn() throws SQLException {
		return Conn.getConnection();
	}

	public boolean inserirTelefone(Telefone telefone) throws SQLException
	{
		connection = fazerConn();
		connection.setAutoCommit(false);

		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO telefone (id_telefone, ddd, numero, tipo, fk_id_cliente) VALUES (?,?,?,?,?)");

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

	public boolean editarTelefone(Telefone telefone) throws SQLException
	{
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		try {
			PreparedStatement statement = connection.prepareStatement("UPDATE telefone SET ddd=?, numero=?, tipo=? WHERE fk_id_cliente=?");
			
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

	public Telefone listarTelefone(int id_cliente) throws SQLException
	{
		connection = fazerConn();
		Telefone telefone = new Telefone();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM telefone WHERE fk_id_cliente=?");
		statement.setInt(1, id_cliente);

		ResultSet rset = statement.executeQuery();
		try {
			if (rset.next()) {
				telefone.setId_telefone(rset.getInt(1));
				telefone.setDdd(rset.getInt(2));
				telefone.setNumero(rset.getString(3));
				telefone.setTipo(rset.getString(4));
				telefone.setFk_id_cliente(rset.getInt(5));
			}

			statement.close();
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return telefone;
	}

}
