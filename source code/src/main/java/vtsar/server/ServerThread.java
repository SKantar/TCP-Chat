package vtsar.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread implements Runnable{
	private Socket socket;
	private BufferedReader in;
	//private boolean first = false;
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			Server.getClients().add(new ServerClient(socket.getInetAddress(), UniqueIdentifier.getIdentifier(), socket));

			
			
			while(true){
				String msg = in.readLine();
				
				System.out.println(msg);
				if(msg.equals("exit")){
					for(int i = 0; i < Server.getClients().size(); i++){
						if(socket.getInetAddress() == Server.getClients().get(i).getSocket().getInetAddress())
							Server.getClients().get(i).getOut().println("exit");
					}
					break; 
				}
				
				for(ServerClient client : Server.getClients()){
					System.out.println(client.getID() + " : " + msg);
					client.getOut().println(msg);
					
				}
			}
			
		
		for(int i = 0; i < Server.getClients().size(); i++){
			if(socket.getInetAddress() == Server.getClients().get(i).getSocket().getInetAddress())
				Server.getClients().remove(i);
		}

		socket.close();

		} catch (Exception e) {
			System.out.println("kurac");
		}
		
		
	}
	
	public BufferedReader getIn() {
		return in;
	}
	

}
