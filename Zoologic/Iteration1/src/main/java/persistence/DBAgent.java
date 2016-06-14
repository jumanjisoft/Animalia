package persistence;

import java.sql.*;
import java.util.Vector;

public class DBAgent {
	protected static DBAgent mInstancia = null;

	protected static Connection mBD;

	private static String url = "jdbc:ucanaccess://C:\\Users\\Jaime\\Documents\\Iso2Practical\\Zoologic\\Persistance\\src\\main\\java\\ZoologicBD.accdb";

	private static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";

	private DBAgent() throws Exception {
		conectar();

	}

	public static DBAgent getAgente() throws Exception {
		if (mInstancia == null) {
			mInstancia = new DBAgent();
		}
		return mInstancia;
	}

	private void conectar() throws Exception {
		Class.forName(driver);
		mBD = DriverManager.getConnection(url);
	}

	public void desconectar() throws Exception {
		mBD.close();
	}

	public ResultSet read(String SQL) throws SQLException, Exception {
		conectar();
		PreparedStatement sentencia = mBD.prepareStatement(SQL);
		ResultSet respuesta = sentencia.executeQuery();
		sentencia.close();
		return respuesta;
	}

}
