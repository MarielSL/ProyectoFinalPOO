package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerUserSolicitante extends JFrame {

	private JPanel contentPane;
	private JLabel userIcon;
	private TextFieldRedond txtUsuario;
	private TextFieldRedond txtEstado;
	private TextFieldRedond txtNombre;
	private TextFieldRedond txtCorreo;
	private TextFieldRedond txtFechNacim;
	private TextFieldRedond txtTelef;
	private TextFieldRedond txtCiudad;
	private TextFieldRedond txtSexo;
	private BotonRedond btnNewButton;
	private JLabel lblFondo;
	private Dimension dim;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private TextFieldRedond txtTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerUserSolicitante frame = new VerUserSolicitante();
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
	public VerUserSolicitante() {
		setForeground(new Color(0, 0, 51));
		setFont(new Font("Calibri", Font.PLAIN, 16));
		setTitle("Ver Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 548);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height -55);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userIcon = new JLabel("New label");
		userIcon.setIcon(new ImageIcon(VerUserSolicitante.class.getResource("/img/User Icon.png")));
		userIcon.setBounds(119, 108, 230, 230);
		contentPane.add(userIcon);
		colocarImagen(userIcon,"/img/User Icon.png");
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel.setBounds(462, 155, 81, 17);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new TextFieldRedond(25);
		txtUsuario.setForeground(new Color(0, 0, 51));
		txtUsuario.setEditable(false);
		txtUsuario.setBackground(SystemColor.controlHighlight);
		txtUsuario.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtUsuario.setBounds(462, 193, 205, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		txtUsuario.setFocusable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Estado");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(792, 156, 100, 16);
		contentPane.add(lblNewLabel_1);
		
		txtEstado = new TextFieldRedond(25);
		if(BolsaEmpleo.getInstancia().getLoginUser() == null) {
			txtEstado.setBackground(SystemColor.controlHighlight);
			txtEstado.setForeground(new Color(0, 0, 51));
		}
		else {
			if(BolsaEmpleo.getInstancia().getLoginUser().getPersona().isEstadoEmpleo()) {
				txtEstado.setForeground(new Color(0, 102, 0));
				txtEstado.setBackground(new Color(153, 204, 153));
			}
			else {
				txtEstado.setForeground(new Color(153, 0, 0));
				txtEstado.setBackground(new Color(255, 153, 153));
			}
		}
		
		
		txtEstado.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtEstado.setBounds(792, 193, 190, 30);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		txtEstado.setFocusable(false);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(1122, 156, 81, 16);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new TextFieldRedond(25);
		txtNombre.setForeground(new Color(0, 0, 51));
		txtNombre.setEditable(false);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtNombre.setBounds(1122, 193, 441, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setFocusable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(462, 425, 81, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fech. Nacimiento");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(119, 763, 166, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tel\u00E9fono");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(119, 420, 100, 26);
		contentPane.add(lblNewLabel_5);
		
		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setEditable(false);
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtCorreo.setBounds(459, 460, 340, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		txtCorreo.setFocusable(false);
		
		txtFechNacim = new TextFieldRedond(25);
		txtFechNacim.setForeground(new Color(0, 0, 51));
		txtFechNacim.setEditable(false);
		txtFechNacim.setBackground(SystemColor.controlHighlight);
		txtFechNacim.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtFechNacim.setBounds(119, 798, 194, 30);
		contentPane.add(txtFechNacim);
		txtFechNacim.setColumns(10);
		txtFechNacim.setFocusable(false);
		
		JLabel lblNewLabel_6 = new JLabel("Ciudad");
		lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(119, 594, 137, 16);
		contentPane.add(lblNewLabel_6);
		
		txtTelef = new TextFieldRedond(25);
		txtTelef.setForeground(new Color(0, 0, 51));
		txtTelef.setEditable(false);
		txtTelef.setBackground(SystemColor.controlHighlight);
		txtTelef.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtTelef.setBounds(119, 460, 194, 30);
		contentPane.add(txtTelef);
		txtTelef.setColumns(10);
		txtTelef.setFocusable(false);
		
		JLabel lblNewLabel_7 = new JLabel("Sexo");
		lblNewLabel_7.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(462, 594, 56, 16);
		contentPane.add(lblNewLabel_7);
		
		txtCiudad = new TextFieldRedond(25);
		txtCiudad.setForeground(new Color(0, 0, 51));
		txtCiudad.setEditable(false);
		txtCiudad.setBackground(SystemColor.controlHighlight);
		txtCiudad.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtCiudad.setBounds(119, 629, 194, 30);
		contentPane.add(txtCiudad);
		txtCiudad.setColumns(10);
		txtCiudad.setFocusable(false);
		
		txtSexo = new TextFieldRedond(25);
		txtSexo.setForeground(new Color(0, 0, 51));
		txtSexo.setEditable(false);
		txtSexo.setBackground(SystemColor.controlHighlight);
		txtSexo.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtSexo.setBounds(462, 629, 164, 30);
		contentPane.add(txtSexo);
		txtSexo.setColumns(10);
		txtSexo.setFocusable(false);
		
		btnNewButton = new BotonRedond("Modificar", 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BolsaEmpleo.getInstancia().getLoginUser()==null) {
					RegistrarSolicitante regSolicitante = new RegistrarSolicitante(null);
					regSolicitante.setVisible(true);
					dispose();
					return;
				}
				RegistrarSolicitante regSolicitante = new RegistrarSolicitante(BolsaEmpleo.getInstancia().getLoginUser().getPersona());
				regSolicitante.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 153, 0));
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnNewButton.setBounds(1707, 891, 159, 47);
		contentPane.add(btnNewButton);
		
		lblFondo = new JLabel("New label");
		lblFondo.setIcon(new ImageIcon(VerUserSolicitante.class.getResource("/img/Fondo-Ver Usuario.png")));
		lblFondo.setBounds(0, 0, 1902, 978);
		contentPane.add(lblFondo);
		
		lblNewLabel_8 = new JLabel("Foto de Perfil");
		lblNewLabel_8.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(171, 59, 112, 20);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Categor\u00EDa Laboral");
		lblNewLabel_9.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(462, 763, 164, 18);
		contentPane.add(lblNewLabel_9);
		
		txtTipo = new TextFieldRedond(25);
		txtTipo.setEditable(false);
		txtTipo.setForeground(new Color(0, 0, 51));
		txtTipo.setBackground(SystemColor.controlHighlight);
		txtTipo.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtTipo.setBounds(462, 798, 194, 30);
		txtTipo.setFocusable(false);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		if(BolsaEmpleo.getInstancia().getLoginUser() == null ) {
			colocarImagen(lblFondo,"/img/Fondo-Ver Usuario.png");
		}
		else {
			colocarImagen(lblFondo,BolsaEmpleo.getInstancia().getLoginUser().getFotoPerfil());
		}
		
		loadUsuario();
	}
	
	private void loadUsuario() {
		if(BolsaEmpleo.getInstancia().getLoginUser() == null) {
			return;
		}
		txtUsuario.setText(BolsaEmpleo.getInstancia().getLoginUser().getUsername());
		if(BolsaEmpleo.getInstancia().getLoginUser().getPersona().isEstadoEmpleo()) {
			txtEstado.setText("Contratado");
		}
		else {
			txtEstado.setText("Desempleado");
		}
		txtNombre.setText(BolsaEmpleo.getInstancia().getLoginUser().getPersona().getNombre() + " " + BolsaEmpleo.getInstancia().getLoginUser().getPersona().getApellido());
		txtCorreo.setText(BolsaEmpleo.getInstancia().getLoginUser().getCorreo());
		txtFechNacim.setText(BolsaEmpleo.getInstancia().getLoginUser().getPersona().getFechNacim().toString());
		txtTelef.setText(BolsaEmpleo.getInstancia().getLoginUser().getPersona().getTelefono());
		txtCiudad.setText(BolsaEmpleo.getInstancia().getLoginUser().getPersona().getCiudad());
		txtSexo.setText(BolsaEmpleo.getInstancia().getLoginUser().getPersona().getSexo().toString());
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


