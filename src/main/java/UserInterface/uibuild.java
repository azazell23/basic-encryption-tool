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
		
		JPanel msgPanel = new JPanel();
		contentPane.add(msgPanel);
		msgPanel.setLayout(new BoxLayout(msgPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		msgPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel authorLabel = new JLabel("Author");
		authorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(authorLabel, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("datetime");
		panel.add(lblNewLabel_2, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.setBackground(new Color(255, 255, 255));
		msgPanel.add(panel_1);
		
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(titleLabel);
		
		JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_2.setBackground(new Color(255, 255, 255));
		msgPanel.add(panel_2);
		
		JLabel fileLabel = new JLabel("File");
		panel_2.add(fileLabel);
		
		JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setPreferredSize(new Dimension(300, 100));
		msgPanel.add(panel_3);
		
		JLabel messageLbl = new JLabel("Lorem Ipsum");
		panel_3.add(messageLbl);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		msgPanel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Close");
		panel_4.add(btnNewButton, BorderLayout.EAST);
		
		JButton btnNewButton_1 = new JButton("Download File");
		panel_4.add(btnNewButton_1, BorderLayout.WEST);
	}

}
