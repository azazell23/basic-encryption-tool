package UserInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Cryptographers.RSA;
import Seeders.Seeder;

import java.awt.BorderLayout;
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

public class RSAEncryption extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static RSA rsa;
	private static Seeder seeder;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rsa = new RSA();
					seeder = new Seeder();
					RSAEncryption frame = new RSAEncryption();
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
	public RSAEncryption() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("RSA Keys");
		menuBar.add(mnNewMenu);
		
		JMenuItem copyPublicKeyBtn = new JMenuItem("Copy - Public Key");
		copyPublicKeyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// this functions to copy the public key string to clipboard
				StringSelection stringSelection = new StringSelection(seeder.getPublicKeyString());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				JOptionPane.showMessageDialog(contentPane, "Public Key added to Clipboard.");
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
				JOptionPane.showMessageDialog(contentPane, "Private Key added to Clipboard.");
			}
		});
		mnNewMenu.add(copyPrivateKeyBtn);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		contentPane.add(menuPanel);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel_header = new JPanel();
		menuPanel.add(menuPanel_header, BorderLayout.NORTH);
		
		JLabel headerLabel = new JLabel("RSA CRYPTOGRAPHY ALGORITHM");
		headerLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		menuPanel_header.add(headerLabel);
		
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
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		
		generateKeyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				seeder.Generate();
				seeder.generatePublicKeyString();
				seeder.generatePrivateKeyString();
				JOptionPane.showMessageDialog(contentPane, "Key successfully generated. Check the menubar to copy the keys.");
				
			} catch (Exception seederErr) {
				System.out.println(seederErr);
			};
		}});
		
		menuPanel_wrapper_bottom.add(generateKeyBtn);

	}
}
