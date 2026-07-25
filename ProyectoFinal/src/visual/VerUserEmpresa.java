package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerUserEmpresa extends JFrame {

	private JPanel contentPane;
	private JLabel fotoPerfil;
	private TextFieldRedond txtUser;
	private TextFieldRedond txtRnc;
	private TextFieldRedond txtNombre;
	private TextFieldRedond txtTipo;
	private TextFieldRedond txtTelefono;
	private TextFieldRedond txtDireccion;
	private TextFieldRedond txtCorreo;
	private BotonRedond btnNewButton;
	private JLabel lblFotoFondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerUserEmpresa frame = new VerUserEmpresa();
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
	public VerUserEmpresa() {
		setTitle("Ver Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 548);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fotoPerfil = new JLabel("New label");
		fotoPerfil.setIcon(new ImageIcon(VerUserEmpresa.class.getResource("/img/User Icon.png")));
		fotoPerfil.setBounds(23, 25, 166, 157);
		contentPane.add(fotoPerfil);
		if(BolsaEmpleo.getInstancia().getLoginUser() == null) {
			colocarImagen(fotoPerfil,"/img/User Icon.png");
		}
		else {
			colocarImagen(fotoPerfil,BolsaEmpleo.getInstancia().getLoginUser().getFotoPerfil());
		}

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel.setBounds(235, 59, 79, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("RNC");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(456, 59, 56, 16);
		contentPane.add(lblNewLabel_1);

		txtUser = new TextFieldRedond(25);
		txtUser.setEditable(false);
		txtUser.setBackground(SystemColor.controlHighlight);
		txtUser.setForeground(new Color(0, 0, 51));
		txtUser.setBounds(235, 85, 166, 26);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		txtUser.setFocusable(false);

		txtRnc = new TextFieldRedond(25);
		txtRnc.setEditable(false);
		txtRnc.setBackground(SystemColor.controlHighlight);
		txtRnc.setForeground(new Color(0, 0, 51));
		txtRnc.setBounds(456, 85, 166, 26);
		contentPane.add(txtRnc);
		txtRnc.setColumns(10);
		txtRnc.setFocusable(false);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(23, 216, 85, 16);
		contentPane.add(lblNewLabel_2);

		txtNombre = new TextFieldRedond(25);
		txtNombre.setEditable(false);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setForeground(new Color(0, 0, 51));
		txtNombre.setBounds(23, 242, 188, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setFocusable(false);

		JLabel lblNewLabel_3 = new JLabel("Tipo");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(259, 216, 56, 16);
		contentPane.add(lblNewLabel_3);

		txtTipo = new TextFieldRedond(25);
		txtTipo.setEditable(false);
		txtTipo.setBackground(SystemColor.controlHighlight);
		txtTipo.setForeground(new Color(0, 0, 51));
		txtTipo.setBounds(259, 242, 196, 26);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		txtTipo.setFocusable(false);

		JLabel lblNewLabel_4 = new JLabel("Tel\u00E9fono");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(23, 306, 106, 16);
		contentPane.add(lblNewLabel_4);

		txtTelefono = new TextFieldRedond(25);
		txtTelefono.setEditable(false);
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setForeground(new Color(0, 0, 51));
		txtTelefono.setText("");
		txtTelefono.setBounds(23, 332, 188, 26);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		txtTelefono.setFocusable(false);

		JLabel lblNewLabel_5 = new JLabel("Correo");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(259, 306, 56, 16);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Direcci\u00F3n");
		lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(23, 390, 106, 16);
		contentPane.add(lblNewLabel_6);

		txtDireccion = new TextFieldRedond(25);
		txtDireccion.setEditable(false);
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setForeground(new Color(0, 0, 51));
		txtDireccion.setBounds(23, 414, 291, 26);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		txtDireccion.setFocusable(false);

		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setEditable(false);
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setBounds(259, 332, 196, 26);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		txtCorreo.setFocusable(false);

		btnNewButton = new BotonRedond("Modificar",30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BolsaEmpleo.getInstancia().getLoginUser()==null) {
					RegEmpresa regEmpresa = new RegEmpresa(null);
					regEmpresa.setVisible(true);
					dispose();
					return;
				}
				RegEmpresa regEmpresa = new RegEmpresa(BolsaEmpleo.getInstancia().getLoginUser().getEmpresa());
				regEmpresa.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 153, 0));
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.setBounds(648, 451, 114, 31);
		contentPane.add(btnNewButton);

		lblFotoFondo = new JLabel("New label");
		lblFotoFondo.setIcon(new ImageIcon(VerUserEmpresa.class.getResource("/img/Fondo-Ver Usuario.png")));
		lblFotoFondo.setBounds(0, 0, 775, 501);
		contentPane.add(lblFotoFondo);
		colocarImagen(lblFotoFondo,"/img/Fondo-Ver Usuario.png");
		
		loadUsuario();

	}
	private void loadUsuario() {
		if(BolsaEmpleo.getInstancia().getLoginUser() == null) {
			return;
		}
		else {
			txtUser.setText(BolsaEmpleo.getInstancia().getLoginUser().getUsername());
			txtRnc.setText(BolsaEmpleo.getInstancia().getLoginUser().getEmpresa().getRnc());
			txtNombre.setText(BolsaEmpleo.getInstancia().getLoginUser().getEmpresa().getNombre());
			txtTipo.setText(BolsaEmpleo.getInstancia().getLoginUser().getEmpresa().getTipo().toString());
			txtTelefono.setText(BolsaEmpleo.getInstancia().getLoginUser().getEmpresa().getTelefono());
			txtCorreo.setText(BolsaEmpleo.getInstancia().getLoginUser().getCorreo());
			txtDireccion.setText(BolsaEmpleo.getInstancia().getLoginUser().getEmpresa().getDireccion());
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
