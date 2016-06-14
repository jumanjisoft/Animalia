package presentation;

import domain.Client;
import domain.LoggingManager;

public class GestorLogin {
	LoggingManager loggingManager;

	public GestorLogin() throws Exception {
		this.loggingManager = new LoggingManager();
	}

	public boolean login(String name, String password) throws Exception {
		Client client = new Client(name, password);
		return loggingManager.login(client);

	}
}
