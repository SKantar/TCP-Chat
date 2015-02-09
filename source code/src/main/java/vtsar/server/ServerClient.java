package vtsar.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerClient {

	public InetAddress address;
	private Socket socket;
	private final int ID;
	public int attempt = 0;
	private PrintWriter out;
	public ServerClient(InetAddress address, final int ID, Socket socket) {
		this.address = address;
		this.ID = ID;
		this.socket = socket;
		try {
			out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getID() {
		return ID;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public PrintWriter getOut() {
		return out;
	}

}