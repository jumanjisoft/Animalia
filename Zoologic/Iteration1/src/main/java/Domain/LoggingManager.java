package	domain;

import javax.security.auth.login.LoginContext;

import persistence.DAOClient;

public class LoggingManager {
	private DAOClient daoClient;

	public LoggingManager() throws Exception {
		this.daoClient = new DAOClient();
	}

	public boolean login(String user, String password) throws Exception {
		Client cli = new Client(user, password);
		return this.daoClient.readClient(cli);
	}
}
