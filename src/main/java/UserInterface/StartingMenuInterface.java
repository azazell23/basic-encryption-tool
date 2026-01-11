package UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Dimension;

public class StartingMenuInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private CardLayout cl_mainPanel;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFrame frame = new StartingMenuInterface();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public StartingMenuInterface() {
		// init		
		setTitle("Java Project : Basic Cryptography Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		cl_mainPanel = new CardLayout();
		mainPanel = new JPanel(cl_mainPanel);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		
		JPanel mainMenuInterface = showMainMenuInterface();
		JPanel announcementAppInterface = new AnnouncementAppInterface().getMainPanel();
		
		mainPanel.add(mainMenuInterface, "mainMenuInterface"); // main panel
		
		mainPanel.add(announcementAppInterface, "announcementAppInterface"); // messaging app 
	}
	
	private JPanel showMainMenuInterface()
	{
		JPanel mainMenuPanel = new JPanel();
		
		JPanel wrapper = new JPanel();
		wrapper.setBorder(new EmptyBorder(200, 0, 0, 0));
		mainMenuPanel.add(wrapper);
		wrapper.setLayout(new BorderLayout(0, 0));
		
		JButton announcementAppButton = new JButton("Simple Announcement App");
		
		wrapper.add(announcementAppButton, BorderLayout.WEST);
		announcementAppButton.setPreferredSize(new Dimension(180, 40));
		
		JButton simpleRSABtn = new JButton("Simple RSA Encryption");
		wrapper.add(simpleRSABtn, BorderLayout.EAST);
		simpleRSABtn.setPreferredSize(new Dimension(180,40));
		
		// divider for the buttons
		JPanel divider = new JPanel();
		divider.setBorder(new EmptyBorder(0, 20, 0, 20));
		wrapper.add(divider, BorderLayout.CENTER);
		
		// action logics
		// rsa page redirect
		simpleRSABtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel simpleRSAInterface = new RSAEncryptionInterface(cl_mainPanel, mainPanel, StartingMenuInterface.this).getMainPanel();
				mainPanel.add(simpleRSAInterface, "simpleRSAInterface"); // simple rsa panel
				cl_mainPanel.show(mainPanel, "simpleRSAInterface");
			}
		});
		
		// simple messaging app page redirect
		announcementAppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_mainPanel.show(mainPanel, "announcementAppInterface");
			}
		});
		return mainMenuPanel;
	}
}
