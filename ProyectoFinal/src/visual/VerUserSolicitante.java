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
import javax.swing.JButton;

public class VerUserSolicitante extends JFrame {

	private JPanel contentPane;
	private JLabel userIcon;
	private TextFieldRedond txtUsuario;
	private TextFieldRedond txtEstado;
	private TextFieldRedond txtNombre;
	private TextFieldRedond txtApellido;
	private TextFieldRedond txtFechNacim;
	private TextFieldRedond txtTelef;
	private TextFieldRedond txtCiudad;
	private TextFieldRedond txtSexo;
	private BotonRedond btnNewButton;
	private JLabel lblFondo;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 793, 548);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userIcon = new JLabel("New label");
		userIcon.setIcon(new ImageIcon(VerUserSolicitante.class.getResource("/img/User Icon.png")));
		userIcon.setBounds(23, 25, 166, 157);
		contentPane.add(userIcon);
		colocarImagen(userIcon,"/img/User Icon.png");
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel.setBounds(235, 59, 81, 17);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new TextFieldRedond(25);
		txtUsuario.setForeground(new Color(0, 0, 51));
		txtUsuario.setEditable(false);
		txtUsuario.setBackground(SystemColor.controlHighlight);
		txtUsuario.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtUsuario.setBounds(235, 89, 179, 26);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		txtUsuario.setFocusable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Estado");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(513, 59, 56, 16);
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
		
		
		txtEstado.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtEstado.setBounds(513, 89, 179, 26);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		txtEstado.setFocusable(false);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(43, 195, 81, 16);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new TextFieldRedond(25);
		txtNombre.setForeground(new Color(0, 0, 51));
		txtNombre.setEditable(false);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtNombre.setBounds(43, 222, 166, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setFocusable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(247, 195, 81, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fech. Nacimiento");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(43, 277, 166, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tel\u00E9fono");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(247, 277, 100, 16);
		contentPane.add(lblNewLabel_5);
		
		txtApellido = new TextFieldRedond(25);
		txtApellido.setForeground(new Color(0, 0, 51));
		txtApellido.setEditable(false);
		txtApellido.setBackground(SystemColor.controlHighlight);
		txtApellido.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtApellido.setBounds(247, 222, 166, 26);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		txtApellido.setFocusable(false);
		
		txtFechNacim = new TextFieldRedond(25);
		txtFechNacim.setForeground(new Color(0, 0, 51));
		txtFechNacim.setEditable(false);
		txtFechNacim.setBackground(SystemColor.controlHighlight);
		txtFechNacim.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtFechNacim.setBounds(43, 306, 166, 26);
		contentPane.add(txtFechNacim);
		txtFechNacim.setColumns(10);
		txtFechNacim.setFocusable(false);
		
		JLabel lblNewLabel_6 = new JLabel("Ciudad");
		lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(43, 363, 56, 16);
		contentPane.add(lblNewLabel_6);
		
		txtTelef = new TextFieldRedond(25);
		txtTelef.setForeground(new Color(0, 0, 51));
		txtTelef.setEditable(false);
		txtTelef.setBackground(SystemColor.controlHighlight);
		txtTelef.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtTelef.setBounds(247, 306, 166, 26);
		contentPane.add(txtTelef);
		txtTelef.setColumns(10);
		txtTelef.setFocusable(false);
		
		JLabel lblNewLabel_7 = new JLabel("Sexo");
		lblNewLabel_7.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(247, 363, 56, 16);
		contentPane.add(lblNewLabel_7);
		
		txtCiudad = new TextFieldRedond(25);
		txtCiudad.setForeground(new Color(0, 0, 51));
		txtCiudad.setEditable(false);
		txtCiudad.setBackground(SystemColor.controlHighlight);
		txtCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCiudad.setBounds(43, 392, 166, 26);
		contentPane.add(txtCiudad);
		txtCiudad.setColumns(10);
		txtCiudad.setFocusable(false);
		
		txtSexo = new TextFieldRedond(25);
		txtSexo.setForeground(new Color(0, 0, 51));
		txtSexo.setEditable(false);
		txtSexo.setBackground(SystemColor.controlHighlight);
		txtSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtSexo.setBounds(247, 392, 137, 26);
		contentPane.add(txtSexo);
		txtSexo.setColumns(10);
		txtSexo.setFocusable(false);
		
		btnNewButton = new BotonRedond("Modificar", 30);
		btnNewButton.setBackground(new Color(255, 153, 0));
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnNewButton.setBounds(648, 451, 114, 31);
		contentPane.add(btnNewButton);
		
		lblFondo = new JLabel("New label");
		lblFondo.setIcon(new ImageIcon(VerUserSolicitante.class.getResource("/img/Fondo-Ver Usuario.png")));
		lblFondo.setBounds(0, -4, 775, 505);
		contentPane.add(lblFondo);
		colocarImagen(lblFondo,"/img/Fondo-Ver Usuario.png");
		
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
		txtNombre.setText(BolsaEmpleo.getInstancia().getLoginUser().getPersona().getNombre());
		txtApellido.setText(BolsaEmpleo.getInstancia().getLoginUser().getPersona().getApellido());
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


