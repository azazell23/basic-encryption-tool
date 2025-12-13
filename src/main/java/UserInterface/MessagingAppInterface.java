package UserInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Cryptographers.RSA;
import Seeders.Seeder;

public class MessagingAppInterface {

	private static final long serialVersionUID = 1L;
	private JPanel messagingAppPanel;
	private CardLayout cl_messagingAppPanel;
	private static RSA rsa;
	private static Seeder seeder;
	private CardLayout cl_parentPanel;
	private JPanel parentPanel;
	private JFrame parentFrame;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MessagingAppInterface frame = new MessagingAppInterface();
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
	public MessagingAppInterface(CardLayout cl_parentPanel, JPanel parentPanel, Seeder seeder, RSA rsa,
			JFrame frame) {
		this.cl_parentPanel = cl_parentPanel;
		this.parentPanel = parentPanel;
		this.seeder = seeder;
		this.rsa = rsa;
		this.parentFrame = frame;
	}
	
	public JPanel getMainPanel() {
		cl_messagingAppPanel = new CardLayout();
		messagingAppPanel = new JPanel(cl_messagingAppPanel);
		messagingAppPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel authMenuPanel = showAuthMenuPanel();
		JPanel registerMenuPanel = showAuthRegisterPanel();
		JPanel loginMenuPanel = showAuthLoginPanel();
		
		messagingAppPanel.add(authMenuPanel, "authMenuPanel"); // main panel
		
		messagingAppPanel.add(loginMenuPanel, "loginMenuPanel");
		messagingAppPanel.add(registerMenuPanel, "registerMenuPanel"); // register panel
		
		return messagingAppPanel;
	}

	public JPanel showAuthMenuPanel() {
		JPanel authMenu = new JPanel();
		
		JPanel wrapper = new JPanel();
		wrapper.setBorder(new EmptyBorder(200, 0, 0, 0));
		authMenu.add(wrapper);
		wrapper.setLayout(new BorderLayout(0, 0));
		
		JButton registerBtn = new JButton("Register");
		
		registerBtn.setPreferredSize(new Dimension(120, 40));
		wrapper.add(registerBtn, BorderLayout.EAST);
		
		JButton loginBtn = new JButton("Log In");
		loginBtn.setPreferredSize(new Dimension(120, 40));
		
		wrapper.add(loginBtn, BorderLayout.WEST);
		
		JPanel divider = new JPanel();
		divider.setBorder(new EmptyBorder(0, 30, 0, 30));
		wrapper.add(divider, BorderLayout.CENTER);
		
		// action logics
		// login page redirect
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_messagingAppPanel.show(messagingAppPanel, "loginMenuPanel");
			}
		});
		
		// register page redirect
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_messagingAppPanel.show(messagingAppPanel, "registerMenuPanel");
			}
		});
		return authMenu;
	}
	
	public JPanel showAuthLoginPanel() {
		JPanel loginMenu = new JPanel();
		loginMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		loginMenu.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		JPanel panel = new JPanel();
		loginMenu.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Log In");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_1.add(usernamePanel);
		usernamePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		usernamePanel.add(lblNewLabel_1, BorderLayout.WEST);
		
		JTextField username = new JTextField();
		usernamePanel.add(username, BorderLayout.EAST);
		username.setColumns(10);
		
		JPanel divider_1 = new JPanel();
		divider_1.setBorder(new EmptyBorder(0, 20, 0, 0));
		usernamePanel.add(divider_1, BorderLayout.CENTER);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_1.add(passwordPanel);
		passwordPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		passwordPanel.add(lblNewLabel_2, BorderLayout.WEST);
		
		JTextField password = new JTextField();
		passwordPanel.add(password, BorderLayout.EAST);
		password.setColumns(10);
		
		JPanel divider_2 = new JPanel();
		divider_2.setBorder(new EmptyBorder(0, 20, 0, 0));
		passwordPanel.add(divider_2, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		panel_1.add(panel_5);
		
		JButton submitBtn = new JButton("Log In");
		submitBtn.setPreferredSize(new Dimension(115, 30));
		
		panel_5.add(submitBtn);
		
		return loginMenu;
	}
	
	public JPanel showAuthRegisterPanel() {
		JPanel registerMenu = new JPanel();
		
		registerMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		registerMenu.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		JPanel panel = new JPanel();
		registerMenu.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_1.add(usernamePanel);
		usernamePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		usernamePanel.add(lblNewLabel_1, BorderLayout.WEST);
		
		JTextField username = new JTextField();
		usernamePanel.add(username, BorderLayout.EAST);
		username.setColumns(10);
		
		JPanel divider_1 = new JPanel();
		divider_1.setBorder(new EmptyBorder(0, 20, 0, 0));
		usernamePanel.add(divider_1, BorderLayout.CENTER);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_1.add(passwordPanel);
		passwordPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		passwordPanel.add(lblNewLabel_2, BorderLayout.WEST);
		
		JTextField password = new JTextField();
		passwordPanel.add(password, BorderLayout.EAST);
		password.setColumns(10);
		
		JPanel divider_2 = new JPanel();
		divider_2.setBorder(new EmptyBorder(0, 20, 0, 0));
		passwordPanel.add(divider_2, BorderLayout.CENTER);
		
		JPanel confPasswordPanel = new JPanel();
		confPasswordPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_1.add(confPasswordPanel);
		confPasswordPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Confirm Password:");
		confPasswordPanel.add(lblNewLabel_3, BorderLayout.WEST);
		
		JTextField conf_password = new JTextField();
		confPasswordPanel.add(conf_password, BorderLayout.EAST);
		conf_password.setColumns(10);
		
		JPanel divider_3 = new JPanel();
		divider_3.setBorder(new EmptyBorder(0, 20, 0, 0));
		confPasswordPanel.add(divider_3, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		panel_1.add(panel_5);
		
		JButton submitBtn = new JButton("Register");
		submitBtn.setPreferredSize(new Dimension(115, 30));
		
		panel_5.add(submitBtn);
		
		return registerMenu;
	}
	
	public JPanel showSendMessagePanel() {
		return new JPanel();
	}
	
	public JPanel showReceivedMessagePanel() {
		return new JPanel();
	}
}
