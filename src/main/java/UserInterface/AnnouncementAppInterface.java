package UserInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.AnnouncementDAO;
import Model.Announcement;
import Model.User;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Authentication.AuthLogin;
import Authentication.AuthRegister;

public class AnnouncementAppInterface extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel announcementAppPanel;
	private CardLayout cl_announcementAppPanel;
	private JPanel testPanel; // testing purposes
	private JTable table;
	
	// panels
	JPanel sendMessagePanel_;
	JPanel announcementPanel_;
	JPanel mainMenuPanel_;
	JPanel registerMenuPanel_;
	JPanel loginMenuPanel_;
	
	// data sections
	private User user;
	ArrayList<Announcement> announcements;
	// file purposes
	Path filePath = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AnnouncementAppInterface frame = new AnnouncementAppInterface();
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
	public AnnouncementAppInterface() {
		
	}
	
	public JPanel getMainPanel() {
		cl_announcementAppPanel = new CardLayout();
		announcementAppPanel = new JPanel(cl_announcementAppPanel);
		announcementAppPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
//		testing purposes
		testPanel = new JPanel(new BorderLayout());
		testPanel.add(announcementAppPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setContentPane(testPanel);

		loginMenuPanel_ = showAuthLoginPanel();
		announcementAppPanel.add(loginMenuPanel_, "loginMenuPanel");
		
		return announcementAppPanel;
	}
	
	private JPanel showAuthRegisterPanel() {
		JPanel registerMenu = new JPanel();
		
		JPanel panel = new JPanel();
		registerMenu.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(105, 0, 0, 0));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		
		panel_9.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(255, 255, 255), 30));
		panel_11.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_11.add(panel_7);
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new EmptyBorder(0, 0, 30, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Register");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_3 = new JPanel();
		panel_11.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Username:");
		panel_4.add(lblNewLabel_6, BorderLayout.WEST);
		
		JTextField username = new JTextField();
		username.setBackground(new Color(235, 235, 235));
		panel_4.add(username, BorderLayout.EAST);
		username.setColumns(10);
		
		JPanel divider_1 = new JPanel();
		divider_1.setBackground(new Color(255, 255, 255));
		divider_1.setBorder(new EmptyBorder(0, 20, 0, 20));
		panel_4.add(divider_1, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("Password:");
		panel_5.add(lblNewLabel_7, BorderLayout.WEST);
		
		JPasswordField pass = new JPasswordField();
		pass.setBackground(new Color(235, 235, 235));
		panel_5.add(pass, BorderLayout.EAST);
		pass.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(255, 255, 255));
		panel_12.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_3.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_10 = new JLabel("Confirm Password:");
		panel_12.add(lblNewLabel_10, BorderLayout.WEST);
		
		JPasswordField conf_pass = new JPasswordField();
		conf_pass.setBackground(new Color(235, 235, 235));
		panel_12.add(conf_pass, BorderLayout.EAST);
		conf_pass.setColumns(10);
		
		JPanel divider_2 = new JPanel();
		divider_2.setBackground(new Color(255, 255, 255));
		divider_2.setBorder(new EmptyBorder(0, 20, 0, 20));
		panel_5.add(divider_2, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_8.add(panel_6, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("Register");
		panel_6.add(btnNewButton_1);
		
		JPanel panel_10 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_10.setBackground(new Color(255, 255, 255));
		panel_8.add(panel_10, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Already have an account?");
		panel_10.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginMenuPanel_ = showAuthLoginPanel();
				announcementAppPanel.add(loginMenuPanel_, "loginMenuPanel");
				cl_announcementAppPanel.show(announcementAppPanel, "loginMenuPanel");
				
				if (registerMenuPanel_ != null) {
		            announcementAppPanel.remove(registerMenuPanel_);
		            registerMenuPanel_ = null;
		           
		            announcementAppPanel.revalidate();
		            announcementAppPanel.repaint();
		        }
			}
		});
		panel_10.add(btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					user = AuthRegister.attempt(username.getText(), new String(pass.getPassword()).trim(), new String(conf_pass.getPassword()));
					
					mainMenuPanel_ = showMainMenuPanel();
					announcementAppPanel.add(mainMenuPanel_, "mainMenuPanel");
					cl_announcementAppPanel.show(announcementAppPanel, "mainMenuPanel");
					
					if (registerMenuPanel_ != null) {
			            announcementAppPanel.remove(registerMenuPanel_);
			            registerMenuPanel_ = null;
			           
			            announcementAppPanel.revalidate();
			            announcementAppPanel.repaint();
			        }
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Error: " + err.getMessage());
				}
			}
		});
		
		return registerMenu;
	}
	
	private JPanel showAuthLoginPanel() {
		JPanel loginMenu = new JPanel();
		
		JPanel panel = new JPanel();
		loginMenu.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(125, 0, 0, 0));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		
		panel_9.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(255, 255, 255), 30));
		panel_11.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_11.add(panel_7);
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new EmptyBorder(0, 0, 30, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Log In");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_3 = new JPanel();
		panel_11.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("Username:");
		panel_4.add(lblNewLabel_6, BorderLayout.WEST);
		
		JTextField username_login = new JTextField();
		username_login.setBackground(new Color(235, 235, 235));
		panel_4.add(username_login, BorderLayout.EAST);
		username_login.setColumns(10);
		
		JPanel divider_1 = new JPanel();
		divider_1.setBackground(new Color(255, 255, 255));
		divider_1.setBorder(new EmptyBorder(0, 20, 0, 20));
		panel_4.add(divider_1, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("Password:");
		panel_5.add(lblNewLabel_7, BorderLayout.WEST);
		
		JPasswordField pass_login = new JPasswordField();
		pass_login.setBackground(new Color(235, 235, 235));
		panel_5.add(pass_login, BorderLayout.EAST);
		pass_login.setColumns(10);
		
		JPanel divider_2 = new JPanel();
		divider_2.setBackground(new Color(255, 255, 255));
		divider_2.setBorder(new EmptyBorder(0, 20, 0, 20));
		panel_5.add(divider_2, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_8.add(panel_6, BorderLayout.CENTER);
		
		JButton btnNewButton_1 = new JButton("Log In");
		panel_6.add(btnNewButton_1);
		
		JPanel panel_10 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_10.setBackground(new Color(255, 255, 255));
		panel_8.add(panel_10, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Do not have account?");
		panel_10.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Register");
		
		// action logics
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerMenuPanel_ = showAuthRegisterPanel();
				announcementAppPanel.add(registerMenuPanel_, "registerMenuPanel");
				cl_announcementAppPanel.show(announcementAppPanel, "registerMenuPanel");
				
		        
		        if (loginMenuPanel_ != null) {
		            announcementAppPanel.remove(loginMenuPanel_);
		            loginMenuPanel_ = null;
		           
		            announcementAppPanel.revalidate();
		            announcementAppPanel.repaint();
		        }
			}
		});
		panel_10.add(btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					user = AuthLogin.attempt(username_login.getText(), new String(pass_login.getPassword()));
					
					mainMenuPanel_ = showMainMenuPanel();
					announcementAppPanel.add(mainMenuPanel_, "mainMenuPanel");
					cl_announcementAppPanel.show(announcementAppPanel, "mainMenuPanel");
					
					if (loginMenuPanel_ != null) {
			            announcementAppPanel.remove(loginMenuPanel_);
			            loginMenuPanel_ = null;
			           
			            announcementAppPanel.revalidate();
			            announcementAppPanel.repaint();
			        }
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Error: " + err.getMessage());
				}
			}
		});
		
		return loginMenu;
	}
	
	private JPanel showMainMenuPanel() {
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel wrapper = new JPanel();
		mainMenuPanel.add(wrapper, BorderLayout.CENTER);
		wrapper.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		wrapper.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Welcome back, " + user.getUsername() + "!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBorder(new EmptyBorder(30, 30, 0, 0));
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		wrapper.add(panel_2, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(80, 0, 0, 0));
		panel_2.add(panel_3);
		
		JButton sendMsgBtn = new JButton("Send Announcement");
		sendMsgBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendMessagePanel_ = showSendMessagePanel();
				announcementAppPanel.add(sendMessagePanel_, "sendAnnouncementPanel");
				cl_announcementAppPanel.show(announcementAppPanel, "sendAnnouncementPanel");
			}
		});
		sendMsgBtn.setPreferredSize(new Dimension(120, 40));
		panel_3.add(sendMsgBtn);
		
		JButton checkInboxBtn = new JButton("Check Announcements");
		checkInboxBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// show announcements panel
				announcementPanel_ = showAnnouncementsPanel();
				announcementAppPanel.add(announcementPanel_, "announcementPanel");
				cl_announcementAppPanel.show(announcementAppPanel, "announcementPanel");
			}
		});
		checkInboxBtn.setPreferredSize(new Dimension(120, 40));
		panel_3.add(checkInboxBtn);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		mainMenuPanel.add(panel, BorderLayout.NORTH);
		
		JButton logoutBtn = new JButton("Log Out");
		logoutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        cl_announcementAppPanel.show(announcementAppPanel, "loginMenuPanel");
		        
		        if (mainMenuPanel_ != null) {
		            announcementAppPanel.remove(mainMenuPanel_);
		            mainMenuPanel_ = null;
		            // 3. Refresh UI
		            announcementAppPanel.revalidate();
		            announcementAppPanel.repaint();
		        }
		        
		        user = null;
			}
		});
		logoutBtn.setPreferredSize(new Dimension(100, 40));
		panel.add(logoutBtn);

		return mainMenuPanel;
	}
	
	private JPanel showSendMessagePanel() {
		JPanel sendMessagePanel = new JPanel();
		sendMessagePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel_12.add(panel);
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new EmptyBorder(80, 0, 0, 0));
		sendMessagePanel.add(panel_13);
		panel_13.add(panel_12);
		
		JPanel panel_7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_7.setBackground(new Color(255, 255, 255));
		panel.add(panel_7);
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 1. Go back to main menu
		        cl_announcementAppPanel.show(announcementAppPanel, "mainMenuPanel");
		        
		        // 2. Remove this panel to save memory and ensure reset next time
		        if (sendMessagePanel_ != null) {
		            announcementAppPanel.remove(sendMessagePanel_);
		            sendMessagePanel_ = null;
		            // 3. Refresh UI
		            announcementAppPanel.revalidate();
		            announcementAppPanel.repaint();
		        }
			}
		});
		panel_7.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 0, 20, 0));
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Create New Announcement");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_6.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_6);
		
		JLabel lblNewLabel_4 = new JLabel("Subject :");
		panel_6.add(lblNewLabel_4);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBackground(new Color(235, 235, 235));
		panel_6.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_9, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("Message :");
		panel_9.add(lblNewLabel_2);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		panel_4.add(panel_10, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(196, 196, 196)));
		panel_10.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.X_AXIS));
		
		JTextArea textArea = new JTextArea();
		panel_11.add(textArea);
		textArea.setBackground(new Color(235, 235, 235));
		textArea.setPreferredSize(new Dimension(400, 40));
		
		JPanel panel_5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_5.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_5);
		
		JLabel lblNewLabel_3 = new JLabel("Choose File :");
		panel_5.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Select File");
		panel_5.add(btnNewButton);
		
		JLabel fileNameLbl = new JLabel();
		panel_5.add(fileNameLbl);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					filePath = selectedFile.toPath();
					
					String fileName = filePath.getFileName().toString();
					
					fileNameLbl.setText(fileName);
					panel_5.revalidate();
					panel_5.repaint();
				}
			}
		});
		
		JPanel panel_8 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel_8.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_8);
		
		JButton btnNewButton_2 = new JButton("Send");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String subject = textField_1.getText();
				String message = textArea.getText();
				String filepathView = filePath.toString();
				
				if (subject.equals(null) && filepathView.equals(null)) {
					JOptionPane.showMessageDialog(null, "Atleast one of the content must be filled.");
				}
				else if (!message.equals(null) && subject.equals(null)) {
					JOptionPane.showMessageDialog(null, "Enter the subject of the message.");
				}
				else {
					Timestamp sentAt = new Timestamp(new Date().getTime());
					try {
						new AnnouncementDAO().insert(new Announcement(user, subject, message, filePath, sentAt));
						JOptionPane.showMessageDialog(null, "Announcement successfully published.");
					} catch (Exception err) {
						JOptionPane.showMessageDialog(null, "Error: " + err.getMessage());
					}
				}
			}
		});
		panel_8.add(btnNewButton_2);
		
		return sendMessagePanel;				
	}

	private JPanel showAnnouncementsPanel() {
		JPanel announcementPanel = new JPanel();
		announcementPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		announcementPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		
		String[] columns = {"No", "Author", "Title", "File", "Date"};
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		DefaultTableModel model = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
		        return false; // This makes ALL cells non-editable
		    }
		}; 
		table = new JTable(model);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel_2.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel lblNewLabel_4 = new JLabel("Date");
		panel_9.add(lblNewLabel_4);
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(100, 20));
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setDate(new java.util.Date());
		updateTable(dateChooser.getDate());
		
		dateChooser.addPropertyChangeListener("date", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        // This code runs whenever the date property changes
		        if (evt.getNewValue() != null) {
		        		updateTable(dateChooser.getDate());
		        }
		    }
		});
		
		panel_9.add(dateChooser);
		panel_1.add(panel_9);
		
		JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("View Announcement");
		panel_3.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("View");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select an announcement first.");
					return;
				}
				AnnouncementDialog dialog = new AnnouncementDialog(AnnouncementAppInterface.this, announcements.get(selectedRow));
				dialog.setVisible(true);
			}
		});
		panel_3.add(btnNewButton_1);
		
		JPanel panel_7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Download File");
		
		panel_7.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Download");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select an announcement first.");
					return;
				}
				else if (announcements.get(selectedRow).getDecryptedFile() == null) {
					JOptionPane.showMessageDialog(null, "Announcement doesn't have attached file.");
					return;
				}
				try {
					announcements.get(selectedRow).getDecryptedFile().downloadFile();
					JOptionPane.showMessageDialog(null, "File successfully downloaded.");
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Error: Failed to download file.");
				}
			}
		});
		panel_7.add(btnNewButton_2);
		
		JPanel panel_8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.add(panel_8);
		
//		JLabel lblNewLabel_3 = new JLabel("Delete Announcement");
//		panel_8.add(lblNewLabel_3);
//		
//		JButton btnNewButton_3 = new JButton("Delete");
//		panel_8.add(btnNewButton_3);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_4.add(panel_5);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl_announcementAppPanel.show(announcementAppPanel, "mainMenuPanel");
		        
		        // 2. Remove this panel
		        if (announcementPanel_ != null) {
		            announcementAppPanel.remove(announcementPanel_);
		            announcementPanel_ = null;
		            // 3. Refresh UI
		            announcementAppPanel.revalidate();
		            announcementAppPanel.repaint();
		        }
			}
		});
		panel_5.add(btnNewButton);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("Announcements");
		panel_6.add(lblNewLabel_1);
		
		return announcementPanel;
	}

	private void updateTable(Date date) {
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			announcements = new AnnouncementDAO().searchByDate(date);
			int index = 1;
			for (Announcement ann : announcements) {
				Object[] data = {
						index,
						ann.getAuthor().getUsername(),
						ann.getTitle(),
						ann.getDecryptedFile().getFileName(),
						ann.getSentAtStr()
				};
				
				model.addRow(data);
				index++;
			}
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "Error: Failed to update table." + err);
		}
	}

}
