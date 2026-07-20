package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.JEditorPane;

public class LogIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LogIn dialog = new LogIn();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LogIn() {
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		setForeground(new Color(255, 153, 0));
		setBackground(new Color(0, 0, 51));
		setTitle("Iniciar Sesi\u00F3n");
		setBounds(100, 100, 333, 442);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Usuario");
			lblNewLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
			lblNewLabel.setForeground(new Color(0, 0, 51));
			lblNewLabel.setBounds(30, 53, 99, 16);
			panel.add(lblNewLabel);
			
			textField = new JTextField() {
			    @Override
			    protected void paintComponent(Graphics g) {
			        Graphics2D g2 = (Graphics2D) g.create();
			        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        g2.setColor(getBackground());
			        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			        g2.dispose();
			        setOpaque(false);
			        super.paintComponent(g);
			    }

			    @Override
			    protected void paintBorder(Graphics g) {
			        Graphics2D g2 = (Graphics2D) g.create();
			        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        g2.setColor(Color.GRAY);
			        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			        g2.dispose();
			    }
			};
			textField.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			textField.setForeground(new Color(255, 255, 255));
			textField.setBackground(new Color(0, 0, 51));
			textField.setBounds(30, 71, 257, 22);
			panel.add(textField);
			textField.setColumns(10);
			{
				JLabel lblContrasea = new JLabel("Contrase\u00F1a");
				lblContrasea.setForeground(new Color(0, 0, 51));
				lblContrasea.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
				lblContrasea.setBounds(30, 141, 116, 16);
				panel.add(lblContrasea);
			}
			

				passwordField = new JPasswordField() {
				    @Override
				    protected void paintComponent(Graphics g) {
				        Graphics2D g2 = (Graphics2D) g.create();
				        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				        g2.setColor(getBackground());
				        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
				        g2.dispose();
				        setOpaque(false);
				        super.paintComponent(g);
				    }

				    @Override
				    protected void paintBorder(Graphics g) {
				        Graphics2D g2 = (Graphics2D) g.create();
				        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				        g2.setColor(Color.GRAY);
				        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
				        g2.dispose();
				    }
				};
				passwordField.setForeground(new Color(255, 255, 255));
				passwordField.setBackground(new Color(0, 0, 51));
				passwordField.setBounds(30, 159, 257, 22);
				panel.add(passwordField);
				
				JButton btnNewButton = new JButton("Acceder");
				btnNewButton.setBackground(new Color(255, 153, 0));
				btnNewButton.setForeground(new Color(0, 0, 0));
				btnNewButton.setBounds(32, 276, 97, 39);
				panel.add(btnNewButton);
				{
					JButton btnRegistrar = new JButton("Registrar");
					btnRegistrar.setForeground(new Color(0, 0, 51));
					btnRegistrar.setBackground(new Color(255, 153, 0));
					btnRegistrar.setBounds(171, 276, 97, 39);
					panel.add(btnRegistrar);
				}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
