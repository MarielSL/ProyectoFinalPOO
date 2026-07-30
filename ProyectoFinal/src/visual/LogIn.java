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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import javax.swing.text.AbstractDocument;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class LogIn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TextFieldRedond txtUser;
	private PasswordFieldRedond passwordField;
	private JLabel lblVerPassword;
	private char caracterOculto;
	private boolean passwordVisible = false;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogIn.class.getResource("/img/AppIconoFull.png")));
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
		Utilidades.aplicarIcono(this);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				PanelRedond panel_1 = new PanelRedond(30);
				panel_1.setBounds(199, 38, 299, 395);
				panel_1.setBackground(new Color(255, 255, 255));
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Usuario:");
				lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
				lblNewLabel.setBounds(63, 113, 76, 16);
				panel_1.add(lblNewLabel);
				
				txtUser = new TextFieldRedond(25);
				txtUser.setBackground(SystemColor.controlHighlight);
				txtUser.setForeground(new Color(0, 0, 51));
				txtUser.setFont(new Font("Calibri", Font.PLAIN, 18));
				txtUser.setBounds(63, 142, 173, 26);
				panel_1.add(txtUser);
				txtUser.setColumns(10);
				
				((AbstractDocument) txtUser.getDocument()).setDocumentFilter(new FiltroLongitudMaxima(20));
				
				JLabel lblNewLabel_1 = new JLabel("Password:");
				lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
				lblNewLabel_1.setBounds(63, 195, 117, 16);
				panel_1.add(lblNewLabel_1);
				
				passwordField = new PasswordFieldRedond(25);
				passwordField.setBackground(SystemColor.controlHighlight);
				passwordField.setForeground(new Color(0, 0, 51));
				passwordField.setFont(new Font("Calibri", Font.PLAIN, 18));
				passwordField.setBounds(63, 221, 173, 26);
				
				((AbstractDocument) passwordField.getDocument()).setDocumentFilter(new FiltroLongitudMaxima(14));
				
				caracterOculto = passwordField.getEchoChar();
				
				lblVerPassword = new JLabel("");
				lblVerPassword.setOpaque(false);
				lblVerPassword.setHorizontalAlignment(JLabel.CENTER);
				lblVerPassword.setBounds(210, 225, 18, 18);
				lblVerPassword.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						alternarVisibilidadPassword();
					}
				});
				colocarImagen(lblVerPassword, "/img/ver.png");
				
				panel_1.add(lblVerPassword);
				panel_1.add(passwordField);
				
				BotonConSombra btnLogin = new BotonConSombra("Login",25);
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtUser.getText().trim().isEmpty() || passwordField.getPassword().length == 0) {
							JOptionPane.showMessageDialog(null, "Debe de llenar los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						
						boolean logIn = BolsaEmpleo.getInstancia().confirmLogin(txtUser.getText(), passwordField.getText());
						
						if(!(logIn)) {
							JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos" , "Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
					
						if(BolsaEmpleo.getInstancia().getLoginUser().getEmpresa() != null) {
							HomeEmpresa homeEmpresa = new HomeEmpresa();
							homeEmpresa.setVisible(true);
							dispose();
						}
						if(BolsaEmpleo.getInstancia().getLoginUser().getPersona() != null) {
							HomeCandidato homeCandidato = new HomeCandidato();
							homeCandidato.setVisible(true);
							dispose();
						}
					
					}
				});
				btnLogin.setForeground(new Color(0, 0, 51));
				btnLogin.setBackground(new Color(255, 153, 51));
				btnLogin.setFont(new Font("Calibri", Font.PLAIN, 16));
				btnLogin.setBounds(25, 289, 97, 43);
				panel_1.add(btnLogin);
				
				BotonConSombra btnRegistrarse = new BotonConSombra("Registrarse",25);
				btnRegistrarse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TipoUser nuevoUsuario = new TipoUser();
						nuevoUsuario.setVisible(true);
						dispose();
						
					}
				});
				btnRegistrarse.setForeground(new Color(0, 0, 51));
				btnRegistrarse.setBackground(new Color(255, 153, 51));
				btnRegistrarse.setFont(new Font("Calibri", Font.PLAIN, 16));
				btnRegistrarse.setBounds(156, 289, 117, 43);
				panel_1.add(btnRegistrarse);
				
				JLabel lblNewLabel_3 = new JLabel("Logo");
				lblNewLabel_3.setBounds(28, 28, 242, 72);
				colocarImagen(lblNewLabel_3, "/img/HireLink_logo_full.png");
				panel_1.add(lblNewLabel_3);
				
				JLabel lblNewLabel_2 = new JLabel("New label");
				lblNewLabel_2.setBounds(0, 0, 700, 480);
				panel.add(lblNewLabel_2);
				colocarImagen(lblNewLabel_2,"/img/Fondo-General.png");
		}
		
	}
	private void alternarVisibilidadPassword() {
		if (passwordVisible) {
			passwordField.setEchoChar(caracterOculto);
			colocarImagen(lblVerPassword, "/img/ver.png");
			passwordVisible = false;
		} else {
			passwordField.setEchoChar((char) 0);
			colocarImagen(lblVerPassword, "/img/esconder.png");
			passwordVisible = true;
		}
	}
	
	private void colocarImagen(JLabel label, String ruta) {
		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));

		int anchoImagen = icono.getIconWidth();
		int altoImagen = icono.getIconHeight();

		int anchoLabel = label.getWidth();
		int altoLabel = label.getHeight();

		double escalaAncho = (double) anchoLabel / anchoImagen;
		double escalaAlto = (double) altoLabel / altoImagen;

		double escala = Math.max(escalaAncho, escalaAlto);
		int nuevoAncho = (int) (anchoImagen * escala);
		int nuevoAlto = (int) (altoImagen * escala);

		Image imagenEscalada = icono.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
		label.setIcon(iconoEscalado);
		label.repaint();
	}
}