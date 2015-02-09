package vtsar.client;

import java.io.IOException;

import vtsar.app.AppCore;
import cipher.CipherUtils;

public class ClientThread implements Runnable {
	
	
	
	public void run() {
		while(true){
			try {
				//System.out.println(AppCore.getInstance().getClient().getIn().readLine());
				String string = AppCore.getInstance().getClient().getIn().readLine();
			
				if(string.equals("exit")){
					break;
				}
				
			     String decrypted = CipherUtils.decrypt(string);
			      //System.out.println(decrypted);
				
				AppCore.getInstance().getConversation().append(decrypted + "\n");
				AppCore.getInstance().getConversation().setCaretPosition(AppCore.getInstance().getConversation().getDocument().getLength());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			AppCore.getInstance().getClient().getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
