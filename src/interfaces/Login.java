package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(42, 89, 72, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(136, 88, 151, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setForeground(new Color(255, 255, 0));
		lblNewLabel_2.setFont(new Font("Harrington", Font.BOLD, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(32, 143, 94, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Iniciar Sesi\u00F3n");
		btnNewButton.setFont(new Font("Harrington", Font.PLAIN, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(159, 185, 111, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 142, 151, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setFont(new Font("Harrington", Font.PLAIN, 13));
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(159, 219, 111, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\javi\\workspace\\JuegoTaller\\src\\interfaces\\The Lord of Souls title1.jpg"));
		label.setBounds(0, 0, 434, 262);
		contentPane.add(label);
	}
}

