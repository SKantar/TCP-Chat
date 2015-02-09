package vtsar.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private static ArrayList<ServerClient> clients;
	
	public Server(int port) throws Exception {
		// TODO Auto-generated constructor stub
		ServerSocket serverSocket = new ServerSocket(port);
		clients = new ArrayList<ServerClient>();
		
		while(true){
			Socket socket = serverSocket.accept();
			new Thread(new ServerThread(socket)).start();
			
		}
		
		
	}
	
	public static ArrayList<ServerClient> getClients() {
		return clients;
	}

}
