package vtsar.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	private Socket socket;

	private String name, address;
	private int port;
	private InetAddress ip;
	private Thread send;
	private int ID = -1;
	private PrintWriter out;
	private BufferedReader in;
	
	public Client(Client client){
		this.name = client.name;
		this.address = client.address;
		this.port = client.port;
	}

	public Client(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public boolean openConnection() {
			try {
				socket = new Socket(address, port);
				ip = InetAddress.getByName(address);
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}

	public String receive() {
		String message = "";
		try {
			message = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	public void send(final String message) {
		send = new Thread("Send") {
			public void run() {
				out.println(message);
			}
		};
		send.start();
	}

	public void close() {
		new Thread() {
			public void run() {
				synchronized (socket) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	public InetAddress getIp() {
		return ip;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	
	public void setID(int ID) {
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}
	
	public BufferedReader getIn() {
		return in;
	}
	
	public PrintWriter getOut() {
		return out;
	}
	
	public Socket getSocket() {
		return socket;
	}
}
