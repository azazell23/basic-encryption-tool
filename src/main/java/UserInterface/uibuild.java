package UserInterface;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;

public class uibuild extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JTextField conf_password;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					uibuild frame = new uibuild();
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
	public uibuild() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
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
		panel_9.add(dateChooser);
		panel_1.add(panel_9);
		
		JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("View Announcement");
		panel_3.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("View");
		panel_3.add(btnNewButton_1);
		
		JPanel panel_7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Download File");
		panel_7.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("Download");
		panel_7.add(btnNewButton_2);
		
		JPanel panel_8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.add(panel_8);
		
		JLabel lblNewLabel_3 = new JLabel("Delete Announcement");
		panel_8.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Delete");
		panel_8.add(btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		
		String[] columns = {"Date", "Author", "Title", "Message", "File"};
		Object[][] data = {};
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel_2.add(scrollPane);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_4.add(panel_5);
		
		JButton btnNewButton = new JButton("Return");
		panel_5.add(btnNewButton);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel lblNewLabel_1 = new JLabel("Announcements");
		panel_6.add(lblNewLabel_1);
		
	}

}
