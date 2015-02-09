package vtsar.actions;

import java.awt.event.ActionEvent;

import vtsar.app.AppCore;

@SuppressWarnings("serial")
public class CloseConnectionAction extends AbstractVTSARAction{
	public void actionPerformed(ActionEvent arg0) {
		String plaintext = "exit";
		try {

			AppCore.getInstance().getClient().getOut().println(plaintext);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
		AppCore.getInstance().getMessage().setText("");
	}

}
