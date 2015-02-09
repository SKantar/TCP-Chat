package vtsar.actions;

public class ActionManager {
	
	private SendAction sendAction;
	private CloseConnectionAction closeConnectionAction;
	
	public ActionManager() {
		sendAction = new SendAction();
		closeConnectionAction = new CloseConnectionAction();
	}
	
	public SendAction getSendAction() {
		return sendAction;
	}
	
	public CloseConnectionAction getCloseConnectionAction() {
		return closeConnectionAction;
	}
	

}
