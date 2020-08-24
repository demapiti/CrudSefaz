package org.sefaz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sefaz.conn.Conn;
import org.sefaz.model.Saldo;

public class SaldoDAO {
	private Connection connection;

	private Connection fazerConn() throws SQLException {
		return Conn.getConnection();
	}

	public boolean adicionarSaldo(Saldo saldo) throws SQLException
	{
		connection = fazerConn();
		connection.setAutoCommit(false);
		
		boolean retorno = false;
		boolean check = false;
		float saldoAtual = 0;
		
		try {
			PreparedStatement st = connection.prepareStatement("SELECT * FROM clientes_saldo WHERE fk_id_cliente=?");
			st.setInt(1, saldo.getFk_id_cliente());
			
			ResultSet rset = st.executeQuery();
			if (rset.next())
			{
				check = true;
				saldoAtual = rset.getFloat("saldo");
			}
			else
			{
				check = false;
			}
			st.execute();
			st.close();
			
			if (check == true)
			{
				PreparedStatement statement = connection.prepareStatement("UPDATE clientes_saldo SET saldo=? WHERE fk_id_cliente=?");
				
				statement.setFloat(1, saldo.getSaldo() + saldoAtual);
				statement.setInt(2, saldo.getFk_id_cliente());

				if (statement.executeUpdate() > 0)
				{
					retorno = true;
				}
				
				connection.commit();
				statement.close();
			}
			else
			{
				PreparedStatement statement = connection.prepareStatement("INSERT INTO clientes_saldo (id_saldo, saldo, fk_id_cliente) VALUES (?,?,?)");

				statement.setString(1, null);
				statement.setFloat(2, saldo.getSaldo());
				statement.setInt(3, saldo.getFk_id_cliente());

				if (statement.executeUpdate() > 0)
				{
					retorno = true;
				}
				
				connection.commit();
				statement.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return retorno;
	}

	public Saldo listarSaldo(int id_cliente) throws SQLException
	{
		connection = fazerConn();
		
		Saldo saldo = new Saldo();
		
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientes_saldo WHERE fk_id_cliente=?");
		statement.setInt(1, id_cliente);

		ResultSet rset = statement.executeQuery();
		try {
			if (rset.next())
			{
				saldo.setId_saldo(rset.getInt(1));
				saldo.setSaldo(rset.getFloat(2));
				saldo.setFk_id_cliente(rset.getInt(3));
			}

			statement.close();
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fechando conexão...");
			connection.close();
		}
		return saldo;
	}

}
