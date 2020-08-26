package org.sefaz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			PreparedStatement statement = connection.prepareStatement("INSERT INTO telefone (ddd, numero, tipo, fk_id_cliente) VALUES (?,?,?,?)");

			statement.setInt(1, telefone.getDdd());
			statement.setString(2, telefone.getNumero());
			statement.setString(3, telefone.getTipo());
			statement.setInt(4, telefone.getFk_id_cliente());

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
			PreparedStatement statement = connection.prepareStatement("UPDATE telefone SET ddd=?, numero=?, tipo=? WHERE id_telefone=? AND fk_id_cliente=?");
			
			statement.setInt(1, telefone.getDdd());
			statement.setString(2, telefone.getNumero());
			statement.setString(3, telefone.getTipo());
			statement.setInt(4, telefone.getId_telefone());
			statement.setInt(5, telefone.getFk_id_cliente());

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
	
	public boolean deletarTelefone(int id_telefone) throws SQLException
	{
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM telefone WHERE id_telefone=?");
			statement.setInt(1, id_telefone);
			
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
	
	public List<Telefone> listarTelefone(int id_cliente) throws SQLException
	{
		connection = fazerConn();
		
		List<Telefone> arrayTelefone = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM telefone WHERE fk_id_cliente=?");
			statement.setInt(1, id_cliente);

			ResultSet rset = statement.executeQuery();
			while (rset.next())
			{
				Telefone telefone = new Telefone();
				telefone.setId_telefone(rset.getInt("id_telefone"));
				telefone.setDdd(rset.getInt("ddd"));
				telefone.setNumero(rset.getString("numero"));
				telefone.setTipo(rset.getString("tipo"));
				telefone.setFk_id_cliente(rset.getInt("fk_id_cliente"));
				arrayTelefone.add(telefone);
			}
			rset.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return arrayTelefone;
	}
}
