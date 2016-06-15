package Domain;

public class UserManager {
	LoggingManager loggingManager;

	public UserManager() throws Exception {
		this.loggingManager = new LoggingManager();
	}

	public boolean login(String name, String password) throws Exception {
		return loggingManager.login(name, password);

	}
}
