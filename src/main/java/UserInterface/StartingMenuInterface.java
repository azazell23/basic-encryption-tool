package UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cryptographers.RSA;
import Seeders.Seeder;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Dimension;

public class StartingMenuInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private CardLayout cl_mainPanel;
	private Seeder seeder;
	private JMenuBar menuBar;
	private RSA rsa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new StartingMenuInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartingMenuInterface() {
		seeder = new Seeder();
		rsa = new RSA();
		setTitle("Java Project : Basic Cryptography Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		cl_mainPanel = new CardLayout();
		mainPanel = new JPanel(cl_mainPanel);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		
		JPanel mainMenuInterface = showMainMenuInterface();
		JPanel messagingAppInterface = new MessagingAppInterface(cl_mainPanel, mainPanel, seeder, rsa, this).getMainPanel();
		JPanel simpleRSAInterface = new RSAEncryptionInterface(cl_mainPanel, mainPanel, seeder, rsa, this).getMainPanel();
		
		mainPanel.add(mainMenuInterface, "mainMenuInterface"); // main panel
		
		
		mainPanel.add(messagingAppInterface, "messagingAppInterface"); // messaging app panel
		mainPanel.add(simpleRSAInterface, "simpleRSAInterface"); // simple rsa panel
		
		// JMenuBar
		menuBar = new JMenuBar();
		
		JMenu mnNewMenu = new JMenu("RSA Keys");
		menuBar.add(mnNewMenu);
		
		JMenuItem copyPublicKeyBtn = new JMenuItem("Copy - Public Key");
		copyPublicKeyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// this functions to copy the public key string to
				StringSelection stringSelection = new StringSelection(seeder.getPublicKeyString());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				JOptionPane.showMessageDialog(mainPanel, "Public Key added to Clipboard.");
			}
		});
		mnNewMenu.add(copyPublicKeyBtn);
		
		JMenuItem copyPrivateKeyBtn = new JMenuItem("Copy - Private Key");
		copyPrivateKeyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// this functions to copy the private key string to clipboard
				StringSelection stringSelection = new StringSelection(seeder.getPrivateKeyString());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				JOptionPane.showMessageDialog(mainPanel, "Private Key added to Clipboard.");
			}
		});
		mnNewMenu.add(copyPrivateKeyBtn);
	}
	
	private JPanel showMainMenuInterface()
	{
		JPanel mainMenuPanel = new JPanel();
		
		JPanel wrapper = new JPanel();
		wrapper.setBorder(new EmptyBorder(200, 0, 0, 0));
		mainMenuPanel.add(wrapper);
		wrapper.setLayout(new BorderLayout(0, 0));
		
		JButton messagingAppBtn = new JButton("Simple Messaging App");
		
		wrapper.add(messagingAppBtn, BorderLayout.WEST);
		messagingAppBtn.setPreferredSize(new Dimension(180, 40));
		
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
				cl_mainPanel.show(mainPanel, "simpleRSAInterface");
				setJMenuBar(menuBar);
			}
		});
		
		// simple messaging app page redirect
		messagingAppBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_mainPanel.show(mainPanel, "messagingAppInterface");
			}
		});
		return mainMenuPanel;
	}
}
