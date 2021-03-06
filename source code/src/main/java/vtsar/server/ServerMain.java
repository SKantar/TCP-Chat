package vtsar.server;

public class ServerMain {

	@SuppressWarnings("unused")
	private int port;
	@SuppressWarnings("unused")
	private Server server;

	public ServerMain(int port) {
		this.port = port;
		try {
			server = new Server(port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int port;
		if (args.length != 1) {
			System.out.println("Usage: java -jar Server.jar [port]");
			return;
		}
		port = Integer.parseInt(args[0]);
		new ServerMain(port);
	}

}
