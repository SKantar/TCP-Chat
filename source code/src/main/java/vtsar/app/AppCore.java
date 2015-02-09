package vtsar.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import vtsar.actions.ActionManager;
import vtsar.actions.SendAction;
import vtsar.client.Client;
import vtsar.client.ClientThread;

@SuppressWarnings("serial")
public class AppCore extends JFrame{
	private static AppCore instance = null;
	private JPanel panel;
	
	//private static int port = 1234;
	//private static String ipAddress = "localhost";
	
	private JTextField message;
	private JTextArea conversation;
	private DefaultCaret caret;
	private ActionManager actionManager;
	
	private Client client;
	
	public AppCore() throws Exception{
		client = new Client(Login.getClient());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				actionManager.getCloseConnectionAction().actionPerformed(null);
				System.exit(0);
			}
		});
		
		actionManager = new ActionManager();
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		client.openConnection();
		
		setSize(600,600);
		setTitle("VTSAR Chat");
		setResizable(false);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initialize();
		
		new Thread(new ClientThread()).start();
		
		
		
		
	}
	
	
	private void initialize(){
		panel = new JPanel();
		panel.setLayout(null);
		
		conversation = new JTextArea(5,20);
		conversation.setEditable(false);
		conversation.setLineWrap(true);
		conversation.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(conversation);
		scroll.setBounds(10, 10, 580, 515);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		message = new JTextField(50);
		message.setBounds(10, 530, 500, 30);
		panel.add(message);
		message.setColumns(10);
		
		
		
		message.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					actionManager.getSendAction().actionPerformed(null);
				}
			}
		});
		
		panel.add(message);
		
		JButton send = new JButton(new SendAction());
		send.setBounds(520, 530, 70, 30);
		send.setText("Send");
		panel.add(send);

		add(panel);
		
	}
	
	
	public static AppCore getInstance() {
		
		if(instance == null)
			try {
				instance = new AppCore();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return instance;
	}
	
	public JTextField getMessage() {
		return message;
	}
	
	public Client getClient() {
		return client;
	}
	
	public JTextArea getConversation() {
		return conversation;
	}
}
