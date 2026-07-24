package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.BolsaEmpleo;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class LogIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TextFieldRedond txtUser;
	private PasswordFieldRedond passwordField;

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
		setBounds(100, 100, 720, 524);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
				
				PanelRedond panel_1 = new PanelRedond(30);
				panel_1.setBackground(new Color(245, 245, 245));
				panel_1.setBounds(198, 78, 299, 339);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Usuario:");
				lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
				lblNewLabel.setBounds(57, 70, 76, 16);
				panel_1.add(lblNewLabel);
				
				txtUser = new TextFieldRedond(25);
				txtUser.setBackground(SystemColor.controlHighlight);
				txtUser.setForeground(new Color(0, 0, 51));
				txtUser.setFont(new Font("Calibri", Font.PLAIN, 18));
				txtUser.setBounds(57, 99, 173, 26);
				panel_1.add(txtUser);
				txtUser.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("Password:");
				lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
				lblNewLabel_1.setBounds(57, 152, 117, 16);
				panel_1.add(lblNewLabel_1);
				
				passwordField = new PasswordFieldRedond(25);
				passwordField.setBackground(SystemColor.controlHighlight);
				passwordField.setForeground(new Color(0, 0, 51));
				passwordField.setFont(new Font("Calibri", Font.PLAIN, 18));
				passwordField.setBounds(57, 178, 173, 26);
				panel_1.add(passwordField);
				
				BotonRedond btnNewButton = new BotonRedond("Login",25);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtUser.getText().trim().isEmpty() || passwordField.getPassword().length == 0) {
							JOptionPane.showMessageDialog(null, "Debe de llenar los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						if(!(BolsaEmpleo.getInstancia().confirmLogin(txtUser.getText(), passwordField.getText()))) {
							JOptionPane.showMessageDialog(null,"El usuario no existe, debe de crear una cuenta." , "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
					
						if(!(BolsaEmpleo.getInstancia().validUserPassword(txtUser.getText(),passwordField.getText()))) {
							JOptionPane.showMessageDialog(null,"Contraseña Incorrecta." , "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						dispose();
					
					}
				});
				btnNewButton.setForeground(new Color(0, 0, 51));
				btnNewButton.setBackground(new Color(255, 153, 51));
				btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 16));
				btnNewButton.setBounds(22, 271, 97, 25);
				panel_1.add(btnNewButton);
				
				BotonRedond btnNewButton_1 = new BotonRedond("Registrarse",25);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TipoUser nuevoUsuario = new TipoUser();
						nuevoUsuario.setVisible(true);
						dispose();
						
					}
				});
				btnNewButton_1.setForeground(new Color(0, 0, 51));
				btnNewButton_1.setBackground(new Color(255, 153, 51));
				btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 16));
				btnNewButton_1.setBounds(147, 271, 126, 25);
				panel_1.add(btnNewButton_1);
				
				JLabel lblNewLabel_2 = new JLabel("New label");
				lblNewLabel_2.setIcon(new ImageIcon(LogIn.class.getResource("/img/Fondo-General.png")));
				lblNewLabel_2.setBounds(0, 0, 700, 480);
				panel.add(lblNewLabel_2);
				colocarImagen(lblNewLabel_2,"/img/Fondo-General.png");
		}
		
	}
	private void colocarImagen(JLabel label, String ruta) {

		 ImageIcon icono = new ImageIcon(getClass().getResource(ruta));

		    int anchoLabel = label.getWidth();
		    int altoLabel = label.getHeight();

		    int anchoImagen = icono.getIconWidth();
		    int altoImagen = icono.getIconHeight();

		    double escalaAncho = (double) anchoLabel / anchoImagen;
		    double escalaAlto = (double) altoLabel / altoImagen;

		    double escala = Math.max(escalaAncho, escalaAlto);

		    int nuevoAncho = (int) (anchoImagen * escala);
		    int nuevoAlto = (int) (altoImagen * escala);

		    Image imagenEscalada = icono.getImage().getScaledInstance(
		            nuevoAncho,
		            nuevoAlto,
		            Image.SCALE_SMOOTH
		    );

		    ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

		    label.setIcon(iconoEscalado);
		    label.setText("");
		    label.setHorizontalAlignment(JLabel.CENTER);
		    label.setVerticalAlignment(JLabel.CENTER);
	}
}
