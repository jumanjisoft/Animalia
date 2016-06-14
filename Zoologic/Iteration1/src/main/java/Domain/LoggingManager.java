package	Domain;

import javax.security.auth.login.LoginContext;

import persistence.DAOClient;

public class LoggingManager {
	private DAOClient daoClient;

	public LoggingManager() throws Exception {
		this.daoClient = new DAOClient();
	}

	public boolean login(Client cli) throws Exception {
		return this.daoClient.readClient(cli);
	}
}
