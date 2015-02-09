package vtsar.actions;

import java.awt.event.ActionEvent;

import vtsar.app.AppCore;
import cipher.CipherUtils;

@SuppressWarnings("serial")
public class SendAction extends AbstractVTSARAction{
	public void actionPerformed(ActionEvent arg0) {
		String plaintext = AppCore.getInstance().getClient().getName()+
				" : " +
				AppCore.getInstance().getMessage().getText();
		try {

			AppCore.getInstance().getClient().getOut().println(CipherUtils.encrypt(plaintext));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
		AppCore.getInstance().getMessage().setText("");
	}

}
