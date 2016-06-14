package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.Persistance.DBAgent;
import org.hsqldb.ClientConnection;

import Domain.Client;

public class DAOClient {
	private DBAgent dbAgent;

	public DAOClient() throws Exception {
		this.dbAgent = DBAgent.getAgente();
	}

	public boolean readClient(Client cliente) throws Exception {
		String sql = "SELECT * from UsersDB where name='" + cliente.getUser() + "' AND password='"
				+ cliente.getPassword() + "';";
		ResultSet respuesta = DBAgent.getAgente().read(sql);
		while (respuesta.next()) {
			String nombre = respuesta.getString("name");
			String password = respuesta.getString("Password");
			respuesta.close();
			return true;
		}
		respuesta.close();
		return false;
	}
}
