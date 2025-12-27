package UserInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cryptographers.RSA;
import Seeders.Seeder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextArea;

public class RSAEncryptionInterface {
	private CardLayout cl_simpleRSAPanel;
	private JPanel simpleRSAPanel;
	private CardLayout cl_parentPanel;
	private JPanel parentPanel;
	private JFrame parentFrame;
	private Seeder seeder;
//	private JPanel testPanel; // testing purposes

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RSAEncryptionInterface frame = new RSAEncryptionInterface();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public RSAEncryptionInterface(CardLayout cl_parentPanel, JPanel parentPanel, JFrame frame) {
		this.cl_parentPanel = cl_parentPanel;
		this.parentPanel = parentPanel;
		this.parentFrame = frame;
		
	}
	
	public void addMenuBar() {
		// JMenuBar
				JMenuBar menuBar = new JMenuBar();
				
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
						JOptionPane.showMessageDialog(null, "Public Key added to Clipboard.");
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
						JOptionPane.showMessageDialog(null, "Private Key added to Clipboard.");
					}
				});
				mnNewMenu.add(copyPrivateKeyBtn);
				parentFrame.setJMenuBar(menuBar);
	}
	
	/**
	 * Create the main frame.
	 */
	public JPanel getMainPanel() {
		cl_simpleRSAPanel = new CardLayout();
		simpleRSAPanel = new JPanel(cl_simpleRSAPanel);

//		// testing purposes
//		testPanel = new JPanel(new BorderLayout());
//		testPanel.add(simpleRSAPanel, BorderLayout.CENTER);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 800, 600);
//		setContentPane(testPanel);
		
		JPanel mainMenu = showMainMenuPanel();
		JPanel encryptPage = showEncryptPanel();
		JPanel decryptPage = showDecryptPanel();
		
		simpleRSAPanel.add(mainMenu, "mainMenu");
		simpleRSAPanel.add(encryptPage, "encryptMenu");
		simpleRSAPanel.add(decryptPage, "decryptMenu");
		
		addMenuBar();
		
		return simpleRSAPanel;
	}
	
	// main menu view
	private JPanel showMainMenuPanel() {
		JPanel mainMenu = new JPanel();
		mainMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainMenu.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		mainMenu.add(menuPanel);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel_header = new JPanel();
		menuPanel.add(menuPanel_header, BorderLayout.NORTH);
		menuPanel_header.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		menuPanel_header.add(panel, BorderLayout.NORTH);
		
		JButton returnBtn = new JButton("Return");
		
		returnBtn.setPreferredSize(new Dimension(100, 40));
		panel.add(returnBtn);
		
		JPanel panel_1 = new JPanel();
		menuPanel_header.add(panel_1, BorderLayout.CENTER);
		
		JLabel headerLabel = new JLabel("RSA CRYPTOGRAPHY ALGORITHM");
		panel_1.add(headerLabel);
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JPanel menuPanel_body = new JPanel();
		menuPanel.add(menuPanel_body, BorderLayout.CENTER);
		
		JPanel menuPanel_wrapper = new JPanel();
		menuPanel_body.add(menuPanel_wrapper);
		menuPanel_wrapper.setLayout(new BoxLayout(menuPanel_wrapper, BoxLayout.Y_AXIS));
		
		JPanel menuPanel_wrapper_top = new JPanel();
		menuPanel_wrapper.add(menuPanel_wrapper_top);
		menuPanel_wrapper_top.setLayout(new BorderLayout(0, 0));
		
		JButton decryptBtn = new JButton("Decrypt");
		
		decryptBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		decryptBtn.setPreferredSize(new Dimension(150,50));
		menuPanel_wrapper_top.add(decryptBtn, BorderLayout.EAST);
		
		JButton encryptBtn = new JButton("Encrypt");
		encryptBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		encryptBtn.setPreferredSize(new Dimension(150,50));
		menuPanel_wrapper_top.add(encryptBtn, BorderLayout.WEST);
		
		JPanel filler_1 = new JPanel();
		menuPanel_wrapper_top.add(filler_1, BorderLayout.CENTER);
		
		JPanel filler_2 = new JPanel();
		menuPanel_wrapper.add(filler_2);
		
		JPanel menuPanel_wrapper_bottom = new JPanel();
		menuPanel_wrapper.add(menuPanel_wrapper_bottom);
		menuPanel_wrapper_bottom.setLayout(new BorderLayout(0, 0));
		
		JButton generateKeyBtn = new JButton("Generate Key");
		generateKeyBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		generateKeyBtn.setPreferredSize(new Dimension(300, 50));
		
		menuPanel_wrapper_bottom.add(generateKeyBtn);
		
		
		// action logics
		// return to main home
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parentFrame.setJMenuBar(null);
				parentFrame.revalidate();
				cl_parentPanel.show(parentPanel, "mainMenuInterface");
			}
		});
		
		// key generation logics
		generateKeyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Seeder seeder = new Seeder();
				seeder.Generate();
				seeder.generatePublicKeyString();
				seeder.generatePrivateKeyString();
				JOptionPane.showMessageDialog(mainMenu, "Key successfully generated. Check the menubar to copy the keys.");
				
			} catch (Exception seederErr) {
				System.out.println(seederErr);
			};
		}});
		
		// encryption page redirect
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl_simpleRSAPanel.show(simpleRSAPanel, "encryptMenu");
			}
		});
		
		// decryption page redirect
		decryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_simpleRSAPanel.show(simpleRSAPanel, "decryptMenu");
			}
		});
		
		return mainMenu;
	}
	
	// encryption view
	private JPanel showEncryptPanel() {
		JPanel encryptPanel = new JPanel();
		encryptPanel.setBorder(new EmptyBorder(10,10,10,10));
		encryptPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		encryptPanel.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Return");
		
		btnNewButton.setPreferredSize(new Dimension(100, 40));
		
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel_5.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel_6.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPublicKey = new JLabel("Public Key");
		panel_1.add(lblPublicKey, BorderLayout.NORTH);
		
		JTextArea publicKey = new JTextArea();
		publicKey.setLineWrap(true);
		panel_1.add(publicKey, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Message Text");
		panel_2.add(lblNewLabel, BorderLayout.NORTH);
		
		JTextArea messageText = new JTextArea();
		messageText.setLineWrap(true);
		panel_2.add(messageText, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Ciphertext");
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JTextArea cipherText = new JTextArea();
		cipherText.setLineWrap(true);
		panel_4.add(cipherText, BorderLayout.CENTER);
		
		JButton encryptBtn = new JButton("Encrypt");
		
		encryptBtn.setBounds(278, 32, 213, 60);
		panel_3.add(encryptBtn);
		
		JPanel panel_7 = new JPanel();
		encryptPanel.add(panel_7, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		
		JLabel lblNewLabel_2 = new JLabel("Encryption");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_8.add(lblNewLabel_2);
		
		// encrypt logics
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RSA rsa = new RSA();
					rsa.readPublicKeyString(publicKey.getText());
					String cipherText_ = rsa.encrypt(messageText.getText());
					cipherText.setText(cipherText_);
					JOptionPane.showMessageDialog(encryptPanel, "Encryption successful.");
				} catch (Exception encryptErr) {
					JOptionPane.showMessageDialog(encryptPanel, "Fail to encrypt:\n" + encryptErr);
				}
			}
		});
		
		// action logics
		// return btn logic
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				publicKey.setText("");
				messageText.setText("");
				cipherText.setText("");
				cl_simpleRSAPanel.show(simpleRSAPanel, "mainMenu");
			}
		});
		
		return encryptPanel;
	}
	
	// decryption view
	private JPanel showDecryptPanel() {
		
		// main panel
		JPanel decryptPanel = new JPanel();
		decryptPanel.setBorder(new EmptyBorder(10,10,10,10));
		decryptPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		decryptPanel.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.setPreferredSize(new Dimension(100, 40));
		
		
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel_5.add(btnNewButton);
		
		// body panel
		JPanel panel = new JPanel();
		panel_6.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPrivateKey = new JLabel("Private Key");
		panel_1.add(lblPrivateKey, BorderLayout.NORTH);
		
		JTextArea privateKey = new JTextArea();
		privateKey.setLineWrap(true);
		panel_1.add(privateKey, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Ciphertext");
		panel_2.add(lblNewLabel, BorderLayout.NORTH);
		
		JTextArea cipherText = new JTextArea();
		cipherText.setLineWrap(true);
		panel_2.add(cipherText, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Original Message");
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JTextArea originalMessage = new JTextArea();
		originalMessage.setLineWrap(true);
		panel_4.add(originalMessage, BorderLayout.CENTER);
		
		JButton decryptBtn = new JButton("Decrypt");
		
		decryptBtn.setBounds(278, 32, 213, 60);
		panel_3.add(decryptBtn);
		
		JPanel panel_7 = new JPanel();
		decryptPanel.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("Decryption");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_7.add(lblNewLabel_3);
		
		// decryption handling
		decryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RSA rsa = new RSA();
					rsa.readPrivateKeyString(privateKey.getText());
					String originalMessage_ = rsa.decrypt(cipherText.getText());
					originalMessage.setText(originalMessage_);
					JOptionPane.showMessageDialog(decryptPanel, "Decryption successful.");
				} catch (Exception decryptErr) {
					JOptionPane.showMessageDialog(decryptPanel, "Fail to decrypt.\n" + decryptErr);
				}
			}
		});
		
		// action logics
		// return button handling event
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// returns all input to empty
				privateKey.setText("");
				cipherText.setText("");
				originalMessage.setText("");
				
				cl_simpleRSAPanel.show(simpleRSAPanel, "mainMenu");
			}
		});
		
		return decryptPanel;
	}
}