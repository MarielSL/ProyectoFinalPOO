package visual;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.TipoEmpresa;
import logico.TipoUser;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.text.AbstractDocument;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class RegEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim = getToolkit().getScreenSize();
	private TextFieldRedond txtNombEmpresa;
	private TextFieldRedond txtTelefono;
	private TextFieldRedond txtDireccion;
	private TextFieldRedond txtRnc;
	private ComboBoxRedond<TipoEmpresa> cbxTipo;
	private TextFieldRedond txtCorreo;
	private TextFieldRedond txtUser;
	private JPasswordField passwordField;
	private JLabel lblVerPassword;
	private char caracterOculto;
	private boolean passwordVisible = false;
	private FotoPerfilRedond fotoPerfil;
	private Empresa myEmpresa = null;
	private BotonRedond btnGuardar;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblDatosDeLa;
	private JLabel lblPaso;
	private JLabel lblCreaTuUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEmpresa dialog = new RegEmpresa(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEmpresa(Empresa empresa) {
		setResizable(false);

		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEmpresa.class.getResource("/img/AppIconoFull.png")));

		myEmpresa = empresa;
		if (myEmpresa == null) {
			setTitle("Registrar Empresa");
		} else {
			setTitle("Modificar Datos");
		}

		setBounds(0, 0, dim.width, dim.height-40);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setOpaque(false);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 999, 993);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel labelVolverIcon = new JLabel("");
		labelVolverIcon.setIcon(new ImageIcon(RegEmpresa.class.getResource("/img/arrow-small-right (Navy Blue).png")));
		labelVolverIcon.setVerticalAlignment(SwingConstants.CENTER);
		labelVolverIcon.setHorizontalAlignment(SwingConstants.CENTER);
		labelVolverIcon.setBounds(47, 887, 50, 50);
		colocarImagen(labelVolverIcon, "/img/arrow-small-right (Navy Blue).png");
		panel.add(labelVolverIcon);

		JLabel lblNewLabel_1 = new JLabel("Nombre de la Empresa:");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setBounds(64, 254, 214, 31);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 18));

		txtNombEmpresa = new TextFieldRedond(25);
		txtNombEmpresa.setBounds(63, 282, 214, 26);
		panel.add(txtNombEmpresa);
		txtNombEmpresa.setForeground(new Color(0, 0, 51));
		txtNombEmpresa.setBackground(SystemColor.controlHighlight);
		txtNombEmpresa.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtNombEmpresa.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Correo:");
		lblNewLabel_7.setForeground(new Color(0, 0, 51));
		lblNewLabel_7.setBounds(65, 339, 56, 16);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Calibri", Font.BOLD, 18));

		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setBounds(64, 359, 563, 26);
		panel.add(txtCorreo);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("RNC:");
		lblNewLabel_4.setForeground(new Color(0, 0, 51));
		lblNewLabel_4.setBounds(65, 415, 56, 16);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 18));

		txtRnc = new TextFieldRedond(25);
		txtRnc.setBounds(65, 436, 214, 26);
		panel.add(txtRnc);
		txtRnc.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtRnc.setForeground(new Color(0, 0, 51));
		txtRnc.setBackground(SystemColor.controlHighlight);
		txtRnc.setColumns(10);

		javax.swing.text.AbstractDocument docRnc = (javax.swing.text.AbstractDocument) txtRnc.getDocument();
		docRnc.setDocumentFilter(Validaciones.filtroRncFormateado());

		JLabel lblNewLabel_2 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_2.setForeground(new Color(0, 0, 51));
		lblNewLabel_2.setBounds(62, 497, 92, 16);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 18));

		txtTelefono = new TextFieldRedond(25);
		txtTelefono.setBounds(61, 519, 214, 26);
		panel.add(txtTelefono);

		javax.swing.text.AbstractDocument docTelefono = (javax.swing.text.AbstractDocument) txtTelefono.getDocument();
		docTelefono.setDocumentFilter(Validaciones.filtroTelefonoFormateado());

		txtTelefono.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTelefono.setForeground(new Color(0, 0, 51));
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_3.setForeground(new Color(0, 0, 51));
		lblNewLabel_3.setBounds(414, 497, 85, 16);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 18));

		txtDireccion = new TextFieldRedond(25);
		txtDireccion.setBounds(413, 518, 214, 26);
		panel.add(txtDireccion);
		txtDireccion.setForeground(new Color(0, 0, 51));
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtDireccion.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setForeground(new Color(0, 0, 51));
		lblNewLabel_5.setBounds(415, 259, 56, 16);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 18));

		cbxTipo = new ComboBoxRedond<TipoEmpresa>(25);
		cbxTipo.setBounds(412, 282, 214, 26);
		panel.add(cbxTipo);
		cbxTipo.setForeground(new Color(0, 0, 51));
		cbxTipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxTipo.setBackground(SystemColor.controlHighlight);
		cbxTipo.setModel(new DefaultComboBoxModel<TipoEmpresa>(TipoEmpresa.values()));
		cbxTipo.setSelectedIndex(-1);

		cbxTipo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
			protected javax.swing.plaf.basic.ComboPopup createPopup() {
				javax.swing.plaf.basic.BasicComboPopup popup = new javax.swing.plaf.basic.BasicComboPopup(comboBox) {
					protected javax.swing.JScrollPane createScroller() {
						javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(list, javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 51), 1, true));
						return scroll;
					}
				};
				popup.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 51), 1, true));
				return popup;
			}
		});

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(0, 0, 51));
		lblUsuario.setBounds(61, 649, 134, 16);
		panel.add(lblUsuario);
		lblUsuario.setFont(new Font("Calibri", Font.BOLD, 18));

		txtUser = new TextFieldRedond(25);
		txtUser.setBounds(58, 669, 326, 26);
		panel.add(txtUser);
		txtUser.setForeground(new Color(0, 0, 51));
		txtUser.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtUser.setColumns(10);
		txtUser.setBackground(SystemColor.controlHighlight);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(new Color(0, 0, 51));
		lblContrasea.setBounds(470, 649, 134, 16);
		panel.add(lblContrasea);
		lblContrasea.setFont(new Font("Calibri", Font.BOLD, 18));

		passwordField = new PasswordFieldRedond(25);
		passwordField.setBounds(467, 670, 326, 26);
		panel.add(passwordField);
		passwordField.setBackground(SystemColor.controlHighlight);

		fotoPerfil = new FotoPerfilRedond(120);
		fotoPerfil.setBounds(692, 250, 214, 230);
		panel.add(fotoPerfil);
		btnGuardar = new BotonRedond("Registrar", 25);
		btnGuardar.setColorHover(new Color(210, 105, 30));
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setText("Registrar  \u2192");
		if (myEmpresa != null) {
			btnGuardar.setText("Modificar");
		}
		btnGuardar.setBounds(775, 883, 194, 57);
		panel.add(btnGuardar);
		btnGuardar.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnGuardar.setBackground(new Color(255, 165, 0));

		lblNewLabel = new JLabel("Logo");
		lblNewLabel.setBounds(58, 46, 294, 79);
		colocarImagen(lblNewLabel,"/img/HireLink_logo_full.png");
		panel.add(lblNewLabel);

		lblNewLabel_6 = new JLabel("Crea tu cuenta empresarial");
		lblNewLabel_6.setForeground(new Color(0, 0, 102));
		lblNewLabel_6.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel_6.setBounds(58, 156, 435, 36);
		panel.add(lblNewLabel_6);

		lblNewLabel_8 = new JLabel("Registra tu empresa para publicar ofertas y encontrar el mejor talento.");
		lblNewLabel_8.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblNewLabel_8.setForeground(new Color(102, 102, 102));
		lblNewLabel_8.setBounds(58, 190, 483, 16);
		panel.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("Paso 1");
		lblNewLabel_9.setForeground(new Color(1, 88, 248));
		lblNewLabel_9.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(58, 219, 56, 16);
		panel.add(lblNewLabel_9);

		lblDatosDeLa = new JLabel("Datos de la empresa");
		lblDatosDeLa.setForeground(new Color(102, 102, 102));
		lblDatosDeLa.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblDatosDeLa.setBounds(121, 219, 171, 16);
		panel.add(lblDatosDeLa);

		lblPaso = new JLabel("Paso 2");
		lblPaso.setForeground(new Color(1, 88, 248));
		lblPaso.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPaso.setBounds(58, 620, 56, 16);
		panel.add(lblPaso);

		lblCreaTuUsuario = new JLabel("Crea tu usuario");
		lblCreaTuUsuario.setForeground(new Color(102, 102, 102));
		lblCreaTuUsuario.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblCreaTuUsuario.setBounds(121, 620, 171, 16);
		panel.add(lblCreaTuUsuario);

		lblVerPassword = new JLabel("");
		lblVerPassword.setBounds(760, 673, 18, 18);
		panel.add(lblVerPassword);
		panel.setComponentZOrder(lblVerPassword, 0);
		lblVerPassword.setOpaque(false);
		lblVerPassword.setHorizontalAlignment(JLabel.CENTER);
		lblVerPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				alternarVisibilidadPassword();
			}
		});
		colocarImagen(lblVerPassword, "/img/ver.png");
		
		BotonRedond botonRedond = new BotonRedond("    Volver", 25);
		botonRedond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeleccionTipoUser volver = new SeleccionTipoUser();
				volver.setVisible(true);
				volver.setModal(true);
				dispose();
				
			}
		});
		botonRedond.setVerticalTextPosition(SwingConstants.TOP);
		botonRedond.setText("    Volver");
		botonRedond.setIconTextGap(6);
		botonRedond.setHorizontalTextPosition(SwingConstants.CENTER);
		botonRedond.setForeground(new Color(0, 0, 51));
		botonRedond.setFont(new Font("Calibri", Font.PLAIN, 18));
		botonRedond.setColorHover(new Color(255, 220, 183));
		botonRedond.setBackground(new Color(255, 235, 215));
		botonRedond.setBounds(37, 883, 194, 57);
		panel.add(botonRedond);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validarDatos()) {
					return;
				}
				String contrasena = new String(passwordField.getPassword());
				if (myEmpresa == null) {
					if (BolsaEmpleo.getInstancia().isEmpressRep(txtRnc.getText())) {
						JOptionPane.showMessageDialog(null, "ERROR!: esta empresa ha sido registrada", "Advertenicia", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (BolsaEmpleo.getInstancia().verifUsuario(txtUser.getText(), contrasena)) {
						JOptionPane.showMessageDialog(null, "Usuario en uso.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						return;
					}
					Empresa empresa = new Empresa("E-" + BolsaEmpleo.generadorIdEmpresa, txtRnc.getText(), txtNombEmpresa.getText(), txtTelefono.getText(),
							txtDireccion.getText(), (TipoEmpresa) cbxTipo.getSelectedItem(), null);
					Usuario nuevoUsuario = new Usuario("U-" + BolsaEmpleo.generadorIdUser, txtUser.getText(), contrasena, null, null, null, null, null);
					nuevoUsuario.setEmpresa(empresa);
					nuevoUsuario.setCorreo(txtCorreo.getText());
					nuevoUsuario.setTipoUser(TipoUser.EMPRESA);
					nuevoUsuario.setFotoPerfil(fotoPerfil.getRutaFotoPerfil());
					BolsaEmpleo.getInstancia().regUser(nuevoUsuario);
					BolsaEmpleo.getInstancia().setLoginUser(nuevoUsuario);
					BolsaEmpleo.getInstancia().regEmpresa(empresa);
					empresa.setUser(nuevoUsuario);

					JOptionPane.showMessageDialog(null, "Se ha registrado la empresa.", "Informaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
					clear();
					HomeEmpresa emp = new HomeEmpresa();
					emp.setVisible(true);
					dispose();
				} else {
					myEmpresa.setNombre(txtNombEmpresa.getText());
					myEmpresa.setRnc(txtRnc.getText());
					myEmpresa.setDireccion(txtDireccion.getText());
					myEmpresa.setTelefono(txtTelefono.getText());
					myEmpresa.setTipo((TipoEmpresa) cbxTipo.getSelectedItem());
					Usuario modUser = myEmpresa.getUser();
					modUser.setCorreo(txtCorreo.getText());
					modUser.setFotoPerfil(fotoPerfil.getRutaFotoPerfil());
					modUser.setPassword(contrasena);
					modUser.setUsername(txtUser.getText());
					myEmpresa.setUser(modUser);
					BolsaEmpleo.getInstancia().modEmpresa(myEmpresa);
					VerUserEmpresa verUser = new VerUserEmpresa();
					verUser.setVisible(true);
					dispose();
				}
			}
		});

		javax.swing.text.AbstractDocument docPassword = (javax.swing.text.AbstractDocument) passwordField.getDocument();
		docPassword.setDocumentFilter(Validaciones.filtroLongitudMaxima(14));

		caracterOculto = passwordField.getEchoChar();

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 1902, 993);
		colocarImagen(lblFondo, "/img/Reg_Empresa_Fondo.png");
		contentPanel.add(lblFondo);

		loadEmpresa();
	}

	private boolean validarDatos() {
		if (!Validaciones.camposLlenos(txtRnc.getText(), txtNombEmpresa.getText(), txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText(), txtUser.getText()) || cbxTipo.getSelectedIndex() == -1 || passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		/*if (!Validaciones.soloNumeros(txtRnc.getText())) {
			JOptionPane.showMessageDialog(null, "El RNC solo debe contener n\u00FAmeros.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}*/
		if (!Validaciones.telefonoValido(txtTelefono.getText(), 10)) {
			JOptionPane.showMessageDialog(null, "El tel\u00E9fono debe tener 10 d\u00EDgitos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!Validaciones.correoValido(txtCorreo.getText())) {
			JOptionPane.showMessageDialog(null, "El correo no tiene un formato v\u00E1lido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private void loadEmpresa() {
		if (myEmpresa != null) {
			txtRnc.setText(myEmpresa.getRnc());
			txtCorreo.setText(myEmpresa.getUser().getCorreo());
			txtNombEmpresa.setText(myEmpresa.getNombre());
			txtTelefono.setText(myEmpresa.getTelefono());
			txtDireccion.setText(myEmpresa.getDireccion());
			cbxTipo.setSelectedItem(myEmpresa.getTipo());
			txtUser.setText(myEmpresa.getUser().getUsername());
			passwordField.setText(myEmpresa.getUser().getPassword());
			fotoPerfil.cargarImagen(myEmpresa.getUser().getFotoPerfil()); 
		}
	}

	private void clear() {
		txtRnc.setText("");
		txtCorreo.setText("");
		txtNombEmpresa.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxTipo.setSelectedIndex(-1);
		txtUser.setText("");
		passwordField.setText("");
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