package impl;

/*
 * 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR 
 * MORPION BONNET CELINE, BOUKRA IMANE, LANSEUR ZOUBIR
 * 
 */

import impl.ThreadedClientM.miseAJour;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import api.ISocketControlerM;
import api.ISocketViewM;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class ClientSocketGuiM implements ISocketViewM {

	private JFrame frame;
	private JTextField textHost;
	private JTextField textPort;
	private String host;
	private int port;
	private ISocketControlerM socketControlerM_;
	JButton btnNewButton_11 = new JButton("");
	JButton btnNewButton_21 = new JButton("");
	JButton btnNewButton_31 = new JButton("");
	JButton btnNewButton_12 = new JButton("");
	JButton btnNewButton_22 = new JButton("");
	JButton btnNewButton_32 = new JButton("");
	JButton btnNewButton_13 = new JButton("");
	JButton btnNewButton_23 = new JButton("");
	JButton btnNewButton_33 = new JButton("");
	JTextPane textPane;
	TextArea textArea_1;
	TextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSocketGuiM window = new ClientSocketGuiM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientSocketGuiM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 713, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		socketControlerM_ = new ThreadedClientM(this);
		
		
		
		//JButton btnNewButton = new JButton("B11");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				socketControlerM_.requeteId("B11");
			}
		});
		btnNewButton_11.setBounds(10, 11, 120, 120);
		frame.getContentPane().add(btnNewButton_11);
		btnNewButton_11.setBackground(new Color(255,255,255));
		
		
		//JButton btnNewButton_1 = new JButton("B21");
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requeteId("B21");
			}
		});
		btnNewButton_21.setBounds(10, 136, 120, 120);
		frame.getContentPane().add(btnNewButton_21);
		btnNewButton_21.setBackground(new Color(255,255,255));
		
		
		
		//JButton btnNewButton_2 = new JButton("B31");
		btnNewButton_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requeteId("B31");
			}
		});
		btnNewButton_31.setBounds(10, 261, 120, 120);
		frame.getContentPane().add(btnNewButton_31);
		btnNewButton_31.setBackground(new Color(255,255,255));
		
		
		//JButton btnNewButton_3 = new JButton("B12");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				socketControlerM_.requeteId("B12");
			}
		});
		btnNewButton_12.setBounds(133, 11, 120, 120);
		frame.getContentPane().add(btnNewButton_12);
		btnNewButton_12.setBackground(new Color(255,255,255));
		
		
		//JButton btnNewButton_4 = new JButton("B22");
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requeteId("B22");
			}
		});
		btnNewButton_22.setBounds(133, 136, 120, 120);
		frame.getContentPane().add(btnNewButton_22);
		btnNewButton_22.setBackground(new Color(255,255,255));
		
		
		//JButton btnNewButton_5 = new JButton("B32");
		btnNewButton_32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requeteId("B32");
			}
		});
		btnNewButton_32.setBounds(133, 261, 120, 120);
		frame.getContentPane().add(btnNewButton_32);
		btnNewButton_32.setBackground(new Color(255,255,255));
		
		
		
		//JButton btnNewButton_6 = new JButton("B13");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requeteId("B13");
			}
		});
		btnNewButton_13.setBounds(256, 11, 120, 120);
		frame.getContentPane().add(btnNewButton_13);
		btnNewButton_13.setBackground(new Color(255,255,255));
		
		
		
		//JButton btnNewButton_7 = new JButton("B23");
		btnNewButton_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requeteId("B23");
			}
		});
		btnNewButton_23.setBounds(256, 136, 120, 120);
		frame.getContentPane().add(btnNewButton_23);
		btnNewButton_23.setBackground(new Color(255,255,255));
		
		
		//JButton btnNewButton_8 = new JButton("B33");
		btnNewButton_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requeteId("B33");
			}
		});
		btnNewButton_33.setBounds(256, 261, 120, 120);
		frame.getContentPane().add(btnNewButton_33);
		btnNewButton_33.setBackground(new Color(255,255,255));
		
		JLabel lblNewLabel = new JLabel("Adresse");
		lblNewLabel.setBounds(386, 327, 52, 20);
		frame.getContentPane().add(lblNewLabel);
		
		textHost = new JTextField();
		textHost.setBounds(444, 327, 86, 20);
		frame.getContentPane().add(textHost);
		textHost.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Port");
		lblNewLabel_1.setBounds(558, 330, 27, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textPort = new JTextField("8051");
		textPort.setBounds(595, 327, 86, 20);
		frame.getContentPane().add(textPort);
		textPort.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					socketControlerM_.connect(textHost.getText(),Integer.parseInt(textPort.getText()));
					
					if(socketControlerM_.getIdClient()==0){
						frame.setTitle("JOUEUR 0 EN BLEU");
					} else {
						frame.setTitle("JOUEUR 1 EN VERT");}
					
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnConnect.setBounds(386, 358, 144, 23);
		frame.getContentPane().add(btnConnect);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					socketControlerM_.requeteId("stop-server");
					socketControlerM_.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}});
		btnDisconnect.setBounds(539, 358, 142, 23);
		frame.getContentPane().add(btnDisconnect);
		
		textPane = new JTextPane();
		textPane.setBounds(386, 11, 295, 23);
		frame.getContentPane().add(textPane);
		
		textPane.setOpaque(true);
		StyledDocument styledocument = textPane.getStyledDocument();
		
		MutableAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		styledocument.setParagraphAttributes(0, 0, center, true);
		
		JButton btnRejouer = new JButton("Rejouer");
		btnRejouer.setBounds(571, 43, 110, 23);
		frame.getContentPane().add(btnRejouer);
		btnRejouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socketControlerM_.requete("clear");
			}
		});
		
		JButton btnSend = new JButton("Send"); // le bouton send permet d'envoyer un message pour le tchat
		btnSend.setBounds(571, 283, 110, 23);
		frame.getContentPane().add(btnSend);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			socketControlerM_.requete("joueur "+socketControlerM_.getIdClient()+" : "+textArea_1.getText());//il envoie la requete contenant le mot "joueur"
			textArea_1.setText("");	
			}
		});
		
		textArea = new TextArea();
		textArea.setBounds(382, 72, 299, 101);
		frame.getContentPane().add(textArea);
		textArea.setBackground(new Color(100,180,230));
		
		textArea_1 = new TextArea();
		textArea_1.setBounds(382, 179, 299, 98);
		frame.getContentPane().add(textArea_1);

	}
	
	public void sendMessage(String s)
	{
		textArea.setText(textArea.getText()+"\n"+s);	//
	};
	
	
	public void effaceTextVictoire(){textPane.setText("");};
	
	public void setTextVictoire(String str)
	{
		textPane.setText(str);
	}
	
	public void setlibelle(JButton btnNewButton)
	{
		btnNewButton.setBackground(new Color(0,0,255));
	};
	
	public void setlibelle1(JButton btnNewButton)
	{
		btnNewButton.setBackground(new Color(0,255,0));
	};
	
	public void setlibelle2(JButton btnNewButton)
	{
		btnNewButton.setBackground(new Color(255,255,255));
	};
	
	public JButton getButton11()	{return btnNewButton_11;}	
	public JButton getButton21()	{return btnNewButton_21;}
	public JButton getButton31()	{return btnNewButton_31;}
	public JButton getButton12()	{return btnNewButton_12;}
	public JButton getButton22()	{return btnNewButton_22;}
	public JButton getButton32()	{return btnNewButton_32;}
	public JButton getButton13()	{return btnNewButton_13;}
	public JButton getButton23()	{return btnNewButton_23;}
	public JButton getButton33()	{return btnNewButton_33;}
	
	@Override
	public boolean isDisposed() {
		// TODO Auto-generated method stub
		return false;
	}

}
