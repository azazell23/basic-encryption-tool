package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Announcement;

public class AnnouncementDialog extends JDialog {

    private final JPanel contentPane;

    public AnnouncementDialog(JFrame parentFrame, Announcement announcement) {
        super(parentFrame, "View Announcement", true); 
        setSize(450, 350);
        setLocationRelativeTo(parentFrame);
        
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
        
        JLabel authorLabel = new JLabel("Author: " + announcement.getAuthor().getUsername());
        authorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        panel.add(authorLabel, BorderLayout.WEST);
        
        JLabel dateLabel = new JLabel(announcement.getSentAtStr());
        panel.add(dateLabel, BorderLayout.EAST);
        
        JPanel panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_1.setBackground(new Color(255, 255, 255));
        msgPanel.add(panel_1);
        
        JLabel titleLabel = new JLabel(announcement.getTitle());
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_1.add(titleLabel);
        
        JPanel panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_2.setBackground(new Color(255, 255, 255));
        msgPanel.add(panel_2);
        
        String fileName = (announcement.getDecryptedFile() != null) 
                ? announcement.getDecryptedFile().getFileName() 
                : "";
        JLabel fileLabel = new JLabel("File: " + fileName);
        if(announcement.getDecryptedFile() != null) {
            fileLabel.setForeground(Color.BLUE);
        }
        panel_2.add(fileLabel);
        
        JPanel panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setPreferredSize(new Dimension(300, 100));
        msgPanel.add(panel_3);
        
        JLabel messageLbl = new JLabel(announcement.getMessage());
        panel_3.add(messageLbl);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(255, 255, 255));
        msgPanel.add(panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));
        
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel_4.add(btnClose, BorderLayout.EAST);
        
        JButton btnDownload = new JButton("Download File");
        btnDownload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (announcement.getDecryptedFile() == null) {
					JOptionPane.showMessageDialog(null, "Announcement doesn't have attached file.");
					return;
				}
				try {
					announcement.getDecryptedFile().downloadFile();
					JOptionPane.showMessageDialog(null, "File successfully downloaded.");
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Error: Failed to download file.");
				}
			}
		});
        
        panel_4.add(btnDownload, BorderLayout.WEST);
    }
}