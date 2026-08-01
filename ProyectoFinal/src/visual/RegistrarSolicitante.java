package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import logico.BolsaEmpleo;
import logico.Obrero;
import logico.Persona;
import logico.Sexo;
import logico.Tecnico;
import logico.TipoPersona;
import logico.TipoUser;
import logico.Universitario;
import logico.Usuario;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class RegistrarSolicitante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim = getToolkit().getScreenSize();
	private Usuario user;
	private CardLayout stepsLayout;
	private JPanel pnlSteps;
	private int pasoActual = 1;
	private JLabel[] dots = new JLabel[4];
	private BotonRedond btnAtras;
	private BotonRedond btnSiguiente;
	private TextFieldRedond txtCedula;
	private TextFieldRedond txtNombre;
	private TextFieldRedond txtApellido;
	private TextFieldRedond txtTelefono;
	private TextFieldRedond txtCorreo;
	private ComboBoxRedond<Sexo> cbxSexo;
	private JSpinner spnFechaNacim;
	private TextFieldRedond txtDireccion;
	private TextFieldRedond txtCiudad;
	private JCheckBox chkMudarse;
	private JCheckBox chkLicencia;
	private JCheckBox chkEmpleado;
	private ComboBoxRedond<TipoPersona> cbxTipo;
	private JTextField txtUser;
	private JPasswordField passwordField;
	private JLabel lblVerPassword;
	private char caracterOculto;
	private boolean passwordVisible = false;
	private FotoPerfilRedond fotoPerfil;
	private Persona mySolicitante = null;
	private JSpinner spnExp;
	private TextFieldRedond txtCarrera;
	private TextFieldRedond txtAreaTecnico;
	private TextFieldRedond txtHabilidades;

	public static void main(String[] args) {
		try {
			RegistrarSolicitante dialog = new RegistrarSolicitante(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegistrarSolicitante(Persona persona) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarSolicitante.class.getResource("/img/AppIconoFull.png")));
		this.mySolicitante = persona;
		if(mySolicitante == null) {
			setTitle("Registrar Solicitante");
		}
		else {
			setTitle("Modificar Solicitante");
		}

		setBounds(0, 0, dim.width, dim.height-40);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1902, 993);
		contentPanel.add(panel);
		panel.setLayout(null);
		JPanel cardBlanca = new JPanel();
		cardBlanca.setBackground(new Color(255, 255, 255));
		cardBlanca.setBounds(1118, 183, 744, 491);
		panel.add(cardBlanca);
		cardBlanca.setLayout(null);
		stepsLayout = new CardLayout();
		pnlSteps = new JPanel(stepsLayout);
		pnlSteps.setOpaque(false);
		pnlSteps.setBounds(20, 15, 693, 412);
		cardBlanca.add(pnlSteps);
		pnlSteps.add(crearPaso1(), "paso1");
		pnlSteps.add(crearPaso2(), "paso2");
		pnlSteps.add(crearPaso3(), "paso3");
		pnlSteps.add(crearPaso4(), "paso4");

		int xDot = 304;
		for (int i = 0; i < 4; i++) {
			JLabel dot = new JLabel("\u25CF", JLabel.CENTER);
			dot.setFont(new Font("Calibri", Font.PLAIN, 22));
			dot.setBounds(xDot, 420, 20, 20);
			dots[i] = dot;
			panel.add(dot);
			xDot += 30;
		}
		loadSolicitante();
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(1031, 0, 871, 993);
		panel.add(panel_1);
		panel_1.setLayout(null);
		btnSiguiente = new BotonRedond("Continuar", 25);
		btnSiguiente.setColorHover(new Color(210, 105, 30));
		btnSiguiente.setText("Continuar  \u2192");
		btnSiguiente.setBounds(647, 805, 212, 57);
		panel_1.add(btnSiguiente);
		btnSiguiente.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnSiguiente.setBackground(new Color(255, 153, 0));
		btnSiguiente.setForeground(new Color(255, 255, 255));
		btnAtras = new BotonRedond("Atr\u00E1s", 25);
		btnAtras.setColorHover(new Color(30, 144, 255));
		btnAtras.setText(" \u2190  Atr\u00E1s");
		BorderFactory.createLineBorder(new Color(255, 153, 0));
		btnAtras.setBounds(109, 805, 212, 57);
		panel_1.add(btnAtras);
		btnAtras.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnAtras.setBackground(Color.decode("#9bceff"));
		btnAtras.setForeground(new Color(0, 0, 51));
		
		JLabel lblNewLabel_3 = new JLabel("Logo");
		lblNewLabel_3.setBounds(109, 38, 277, 84);
		colocarImagen(lblNewLabel_3,"/img/HireLink_logo_full.png");
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Crea tu cuenta");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 28));
		lblNewLabel_4.setForeground(new Color(0, 0, 51));
		lblNewLabel_4.setBounds(109, 123, 212, 49);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblRegistrareParaAcceder = new JLabel("Registrare para acceder a las ofertas laborales");
		lblRegistrareParaAcceder.setForeground(SystemColor.controlDkShadow);
		lblRegistrareParaAcceder.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblRegistrareParaAcceder.setBounds(109, 166, 387, 16);
		panel_1.add(lblRegistrareParaAcceder);
		
				JLabel lblNewLabel = new JLabel("New label");
				lblNewLabel.setBounds(0, 0, 1902, 993);
				colocarImagen(lblNewLabel,"/img/Registrar_Solicitante_Fondo.png");
				panel.add(lblNewLabel);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(0, 0, 10, 10);
				panel.add(panel_2);
		btnAtras.setVisible(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAtras();
			}
		});
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irSiguiente();
			}
		});
		actualizarDots();
		if (mySolicitante != null) {
		    cbxTipo.setEnabled(false);
		}


	}

	private void loadSolicitante() {

		if(mySolicitante == null) {
			return;
		}

		txtNombre.setText("Ingresa tu nombre");
		txtApellido.setText("Ingresa tu apellido");

		if(mySolicitante instanceof Universitario) {
			Universitario aux = (Universitario) mySolicitante;
			txtCarrera.setText(aux.getCarrera());
			cbxTipo.setSelectedItem(TipoPersona.UNIVERSITARIO);
		}

		if(mySolicitante instanceof Tecnico) {
			Tecnico aux = (Tecnico) mySolicitante;
			txtAreaTecnico.setText(aux.getTecnico());
			cbxTipo.setSelectedItem(TipoPersona.TECNICO);
		}

		if(mySolicitante instanceof Obrero) {
			Obrero aux = (Obrero) mySolicitante;
			txtHabilidades.setText(aux.getHabilidades());
			cbxTipo.setSelectedItem(TipoPersona.OBRERO);
		}

		txtCedula.setText("Ingresa tu c\u00E9dula");
		txtCiudad.setText(mySolicitante.getCiudad());
		txtCorreo.setText("ejemplo@correo.com");
		txtDireccion.setText(mySolicitante.getDireccion());
		txtTelefono.setText("Ingresa tu tel\u00E9fono");
		txtUser.setText(mySolicitante.getUser().getUsername());
		passwordField.setText(mySolicitante.getUser().getPassword());
		cbxSexo.setSelectedItem(mySolicitante.getSexo());
		Date fecha = Date.from(mySolicitante.getFechNacim().atStartOfDay(ZoneId.systemDefault()).toInstant());
		spnFechaNacim.setValue(fecha);		
		spnExp.setValue(mySolicitante.getYearsExp());

		if(mySolicitante.isDispParaMudarse()) {
			chkMudarse.setSelected(true);
		}

		if(mySolicitante.isLicenciaConducir()) {
			chkLicencia.setSelected(true);
		}

		if(mySolicitante.isEstadoEmpleo()) {
			chkEmpleado.setSelected(true);
		}
		
		fotoPerfil.cargarImagen(mySolicitante.getUser().getFotoPerfil());
	
	}

	
	private void irSiguiente() {
		if (pasoActual == 1) {
			if (!validarPaso1()) return;
			stepsLayout.show(pnlSteps, "paso2");
			pasoActual = 2;
			btnAtras.setVisible(true);

		} else if (pasoActual == 2) {
			if (!validarPaso2()) return;
			stepsLayout.show(pnlSteps, "paso3");
			pasoActual = 3;

		} else if (pasoActual == 3) {
			if (!validarPaso3()) return;
			stepsLayout.show(pnlSteps, "paso4");
			pasoActual = 4;
			btnSiguiente.setText("Finalizar");

		} else if (pasoActual == 4) {
			if (!validarPaso4()) return;
			registrarSolicitante();
			return;
		}
		actualizarDots();
	}

	private void irAtras() {
		if (pasoActual == 4) {
			stepsLayout.show(pnlSteps, "paso3");
			pasoActual = 3;
			btnSiguiente.setText("Continuar");
		} else if (pasoActual == 3) {
			stepsLayout.show(pnlSteps, "paso2");
			pasoActual = 2;
		} else if (pasoActual == 2) {
			stepsLayout.show(pnlSteps, "paso1");
			pasoActual = 1;
			btnAtras.setVisible(false);
		}
		actualizarDots();
	}

	private void actualizarDots() {
		for (int i = 0; i < 4; i++) {
			if (i + 1 == pasoActual) {
				dots[i].setForeground(new Color(255, 153, 0));
			} else {
				dots[i].setForeground(new Color(200, 200, 200));
			}
		}
	}

	private boolean validarPaso1() {
		if (!Validaciones.camposLlenos(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(), txtCorreo.getText()) || cbxSexo.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!Validaciones.soloNumeros(txtCedula.getText())) {
			JOptionPane.showMessageDialog(null, "La c\u00E9dula solo debe contener n\u00FAmeros.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!Validaciones.soloLetras(txtNombre.getText())) {
			JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!Validaciones.soloLetras(txtApellido.getText())) {
			JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
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

	private boolean validarPaso2() {
		if (!Validaciones.camposLlenos(txtDireccion.getText(), txtCiudad.getText())) {
			JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!Validaciones.soloLetras(txtCiudad.getText())) {
			JOptionPane.showMessageDialog(null, "La ciudad solo debe contener letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validarPaso3() {
		if (cbxTipo.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar el tipo de solicitante.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		TipoPersona tipo = (TipoPersona) cbxTipo.getSelectedItem();
		if (tipo == TipoPersona.UNIVERSITARIO && !Validaciones.camposLlenos(txtCarrera.getText())) {
			JOptionPane.showMessageDialog(null, "Debe indicar la carrera.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (tipo == TipoPersona.TECNICO && !Validaciones.camposLlenos(txtAreaTecnico.getText())) {
			JOptionPane.showMessageDialog(null, "Debe indicar el \u00E1rea t\u00E9cnica.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (tipo == TipoPersona.OBRERO && !Validaciones.camposLlenos(txtHabilidades.getText())) {
			JOptionPane.showMessageDialog(null, "Debe indicar las habilidades.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validarPaso4() {
		if (!Validaciones.camposLlenos(txtUser.getText()) || passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Debe de llenar los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (mySolicitante == null) {
			String contrasena = new String(passwordField.getPassword());
			if (BolsaEmpleo.getInstancia().verifUsuario(txtUser.getText(), contrasena)) {
				JOptionPane.showMessageDialog(null, "Usuario en uso.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}

	private void registrarSolicitante() {
		Date fechaSeleccionada = (Date) spnFechaNacim.getValue();
		LocalDate fechaNacim = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		TipoPersona tipo = (TipoPersona) cbxTipo.getSelectedItem();

		if(mySolicitante == null) {
			String contrasena = new String(passwordField.getPassword());
			Usuario newUser = new Usuario("U-" + BolsaEmpleo.generadorIdUser, txtUser.getText(), contrasena, txtCorreo.getText(), null, null, TipoUser.CANDIDATO, null);
			newUser.setFotoPerfil(fotoPerfil.getRutaFotoPerfil());
			this.user = newUser;

			String id = "P-" + BolsaEmpleo.generadorIdPersona;
			Persona persona;
			if (tipo == TipoPersona.UNIVERSITARIO) {
				persona = new Universitario(id, txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtTelefono.getText(), txtDireccion.getText(), (Sexo) cbxSexo.getSelectedItem(), txtCiudad.getText(), chkMudarse.isSelected(), chkLicencia.isSelected(), chkEmpleado.isSelected(), user,(int) spnExp.getValue(), txtCarrera.getText());

			} else if (tipo == TipoPersona.TECNICO) {
				persona = new Tecnico(id, txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtTelefono.getText(), txtDireccion.getText(), (Sexo) cbxSexo.getSelectedItem(), txtCiudad.getText(), chkMudarse.isSelected(), chkLicencia.isSelected(), chkEmpleado.isSelected(), user, (int) spnExp.getValue(),txtAreaTecnico.getText());

			} else {
				persona = new Obrero(id, txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtTelefono.getText(), txtDireccion.getText(), (Sexo) cbxSexo.getSelectedItem(), txtCiudad.getText(), chkMudarse.isSelected(), chkLicencia.isSelected(), chkEmpleado.isSelected(), user,(int) spnExp.getValue(), txtHabilidades.getText());

			}

			newUser.setPersona(persona);
			persona.setUser(newUser);

			BolsaEmpleo.getInstancia().regPersona(persona);
			BolsaEmpleo.getInstancia().regUser(newUser);
			BolsaEmpleo.getInstancia().setLoginUser(newUser);

			JOptionPane.showMessageDialog(null, "Se ha registrado el solicitante.", "Informaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
			clear();

			HomeCandidato homeCandidato = new HomeCandidato();
			homeCandidato.setVisible(true);
			dispose();

		}
		else {
			mySolicitante.setNombre(txtNombre.getText());
			mySolicitante.setApellido(txtApellido.getText());
			mySolicitante.setCedula(txtCedula.getText());
			mySolicitante.setCiudad(txtCiudad.getText());
			mySolicitante.setDireccion(txtDireccion.getText());
			mySolicitante.setDispParaMudarse(chkMudarse.isSelected());
			mySolicitante.setEstadoEmpleo(chkEmpleado.isSelected());
			mySolicitante.setFechNacim(fechaNacim);
			mySolicitante.setLicenciaConducir(chkLicencia.isSelected());
			mySolicitante.setSexo((Sexo) cbxSexo.getSelectedItem());
			mySolicitante.setTelefono(txtTelefono.getText());
			mySolicitante.setYearsExp((int) spnExp.getValue());

			
			Usuario user = mySolicitante.getUser();
			
			user.setCorreo(txtCorreo.getText());
			user.setUsername(txtUser.getText());
			user.setPassword(new String (passwordField.getPassword()));
			if (fotoPerfil.getRutaFotoPerfil() != null) {          
			    user.setFotoPerfil(fotoPerfil.getRutaFotoPerfil()); 
			}    

			if(tipo == TipoPersona.UNIVERSITARIO) {
				Universitario uni = (Universitario) mySolicitante;
				uni.setCarrera(txtCarrera.getText());
				BolsaEmpleo.getInstancia().modSolicitante(uni);
			}

			if(tipo == TipoPersona.TECNICO) {
				Tecnico tecnico = (Tecnico) mySolicitante;
				tecnico.setTecnico(txtAreaTecnico.getText());
				BolsaEmpleo.getInstancia().modSolicitante(tecnico);
			}
			if(tipo == TipoPersona.OBRERO) {
				Obrero obrero = (Obrero) mySolicitante;
				obrero.setHabilidades(txtHabilidades.getText());
				BolsaEmpleo.getInstancia().modSolicitante(obrero);

			}
			BolsaEmpleo.getInstancia().modUsuario(BolsaEmpleo.getInstancia().getLoginUser());
			BolsaEmpleo.getInstancia().modSolicitante(mySolicitante);
			
			VerUserSolicitante verUser = new VerUserSolicitante();
			verUser.setVisible(true);
			dispose();
		}

	}
	private void clear() {
		txtCedula.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		cbxSexo.setSelectedIndex(-1);
		txtDireccion.setText("");
		txtCiudad.setText("");
		chkMudarse.setSelected(false);
		chkLicencia.setSelected(false);
		chkEmpleado.setSelected(false);
		cbxTipo.setSelectedIndex(-1);
		txtCarrera.setText("");
		txtAreaTecnico.setText("");
		txtHabilidades.setText("");
		stepsLayout.show(pnlSteps, "paso1");
		pasoActual = 1;
		btnAtras.setVisible(false);
		btnSiguiente.setText("Continuar");
		spnExp.setValue(0);
		actualizarDots();
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

	private JPanel crearPaso1() {
		JPanel paso = new JPanel();
		paso.setBounds(10, 0, 672, 401);
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblCedula = new JLabel("C\u00E9dula");
		lblCedula.setForeground(new Color(0, 0, 51));
		lblCedula.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCedula.setBounds(0, 43, 200, 20);
		paso.add(lblCedula);
		txtCedula = new TextFieldRedond(25);
		txtCedula.setForeground(new Color(0, 0, 51));
		txtCedula.setBackground(SystemColor.controlHighlight);
		txtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCedula.setBounds(0, 68, 294, 30);
		paso.add(txtCedula);
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setForeground(new Color(0, 0, 51));
		lblTelefono.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTelefono.setBounds(330, 43, 200, 20);
		paso.add(lblTelefono);
		txtTelefono = new TextFieldRedond(25);
		((AbstractDocument) txtTelefono.getDocument()).setDocumentFilter(Validaciones.filtroTelefonoFormateado());
		txtTelefono.setForeground(new Color(0, 0, 51));
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtTelefono.setBounds(330, 68, 294, 30);
		paso.add(txtTelefono);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(0, 0, 51));
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNombre.setBounds(0, 133, 200, 20);
		paso.add(lblNombre);
		txtNombre = new TextFieldRedond(25);
		txtNombre.setForeground(new Color(0, 0, 51));
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtNombre.setBounds(0, 158, 294, 30);
		paso.add(txtNombre);
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(new Color(0, 0, 51));
		lblApellido.setFont(new Font("Calibri", Font.BOLD, 20));
		lblApellido.setBounds(330, 133, 200, 20);
		paso.add(lblApellido);
		txtApellido = new TextFieldRedond(25);
		txtApellido.setForeground(new Color(0, 0, 51));
		txtApellido.setBackground(SystemColor.controlHighlight);
		txtApellido.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtApellido.setBounds(330, 158, 294, 30);
		paso.add(txtApellido);
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(new Color(0, 0, 51));
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCorreo.setBounds(0, 221, 200, 20);
		paso.add(lblCorreo);
		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCorreo.setBounds(0, 246, 624, 30);
		paso.add(txtCorreo);
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setForeground(new Color(0, 0, 51));
		lblSexo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblSexo.setBounds(0, 307, 200, 20);
		paso.add(lblSexo);
		cbxSexo = new ComboBoxRedond<Sexo>(25);
		cbxSexo.setForeground(new Color(0, 0, 51));
		cbxSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxSexo.setBackground(SystemColor.controlHighlight);
		cbxSexo.setBounds(0, 332, 180, 30);
		cbxSexo.removeAll();
		
		for (Sexo sexo : Sexo.values()) {
			if(sexo != Sexo.CUALQUIERA) {
				cbxSexo.addItem(sexo);
			}
		}
		
		cbxSexo.setSelectedIndex(-1);
		cbxSexo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
			protected javax.swing.plaf.basic.ComboPopup createPopup() {
				javax.swing.plaf.basic.BasicComboPopup popup = new javax.swing.plaf.basic.BasicComboPopup(comboBox) {
					protected JScrollPane createScroller() {
						JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 51), 1, true));
						return scroll;
					}
				};
				popup.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 51), 1, true));
				return popup;
			}
		});
		paso.add(cbxSexo);
		JLabel lblFecha = new JLabel("Fecha de nacimiento");
		lblFecha.setForeground(new Color(0, 0, 51));
		lblFecha.setFont(new Font("Calibri", Font.BOLD, 20));
		lblFecha.setBounds(210, 307, 220, 20);
		paso.add(lblFecha);
		Calendar cal = Calendar.getInstance();
		Date hoy = cal.getTime();
		cal.add(Calendar.YEAR, -18);
		Date inicial = cal.getTime();
		SpinnerDateModel dateModel = new SpinnerDateModel(inicial, null, hoy, Calendar.DAY_OF_MONTH);
		spnFechaNacim = new JSpinner(dateModel);
		spnFechaNacim.setEditor(new JSpinner.DateEditor(spnFechaNacim, "dd/MM/yyyy"));
		spnFechaNacim.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnFechaNacim.setBounds(210, 332, 150, 30);
		paso.add(spnFechaNacim);
		
		JLabel lblNewLabel_5 = new JLabel("Paso 1 de 4");
		lblNewLabel_5.setBounds(0, 0, 105, 16);
		paso.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new Color(1, 88, 248));
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 18));
		
		JLabel lblNewLabel_6 = new JLabel("Datos personales");
		lblNewLabel_6.setBounds(109, 1, 130, 16);
		paso.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblNewLabel_6.setForeground(new Color(105, 105, 105));
		return paso;
	}

	private JPanel crearPaso2() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setForeground(new Color(0, 0, 51));
		lblDireccion.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDireccion.setBounds(0, 51, 200, 20);
		paso.add(lblDireccion);
		txtDireccion = new TextFieldRedond(25);
		txtDireccion.setForeground(new Color(0, 0, 51));
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDireccion.setBounds(0, 76, 294, 30);
		paso.add(txtDireccion);
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setForeground(new Color(0, 0, 51));
		lblCiudad.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCiudad.setBounds(330, 51, 200, 20);
		paso.add(lblCiudad);

		txtCiudad = new TextFieldRedond(25);
		txtCiudad.setForeground(new Color(0, 0, 51));
		txtCiudad.setBackground(SystemColor.controlHighlight);
		txtCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCiudad.setBounds(330, 76, 294, 30);
		paso.add(txtCiudad);

		JLabel lblDisp = new JLabel("Disponibilidad");
		lblDisp.setForeground(new Color(0, 0, 51));
		lblDisp.setFont(new Font("Calibri", Font.BOLD, 18));
		lblDisp.setBounds(0, 173, 200, 20);
		paso.add(lblDisp);

		chkMudarse = new JCheckBox("Disponible para mudarse");
		chkMudarse.setForeground(new Color(0, 0, 51));
		chkMudarse.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkMudarse.setOpaque(false);
		chkMudarse.setBounds(0, 203, 250, 25);
		paso.add(chkMudarse);
		chkLicencia = new JCheckBox("Licencia de conducir");
		chkLicencia.setForeground(new Color(0, 0, 51));
		chkLicencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkLicencia.setOpaque(false);
		chkLicencia.setBounds(0, 238, 250, 25);
		paso.add(chkLicencia);
		chkEmpleado = new JCheckBox("Actualmente empleado");
		chkEmpleado.setForeground(new Color(0, 0, 51));
		chkEmpleado.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkEmpleado.setOpaque(false);
		chkEmpleado.setBounds(0, 273, 250, 25);
		paso.add(chkEmpleado);
		
		JLabel lblPasoDe = new JLabel("Paso 2 de 4");
		lblPasoDe.setForeground(new Color(1, 88, 248));
		lblPasoDe.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPasoDe.setBounds(0, 0, 105, 16);
		paso.add(lblPasoDe);
		
		JLabel lblLocalizacin = new JLabel("Localizaci\u00F3n");
		lblLocalizacin.setForeground(SystemColor.controlDkShadow);
		lblLocalizacin.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblLocalizacin.setBounds(109, 1, 130, 16);
		paso.add(lblLocalizacin);
		return paso;
	}

	private JPanel crearPaso3() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblTipo = new JLabel("Tipo de solicitante");
		lblTipo.setBounds(0, 45, 200, 20);
		lblTipo.setForeground(new Color(0, 0, 51));
		lblTipo.setFont(new Font("Calibri", Font.BOLD, 18));
		paso.add(lblTipo);
		cbxTipo = new ComboBoxRedond<TipoPersona>(25);
		cbxTipo.setBounds(0, 70, 225, 30);
		cbxTipo.setForeground(new Color(0, 0, 51));
		cbxTipo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxTipo.setBackground(SystemColor.controlHighlight);
		cbxTipo.setModel(new DefaultComboBoxModel<TipoPersona>(new TipoPersona[] { TipoPersona.UNIVERSITARIO, TipoPersona.TECNICO, TipoPersona.OBRERO }));
		cbxTipo.setSelectedIndex(-1);
		cbxTipo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
			protected javax.swing.plaf.basic.ComboPopup createPopup() {
				javax.swing.plaf.basic.BasicComboPopup popup = new javax.swing.plaf.basic.BasicComboPopup(comboBox) {
					protected JScrollPane createScroller() {
						JScrollPane scroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 51), 1, true));
						return scroll;
					}
				};
				popup.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 51), 1, true));
				return popup;
			}
		});
		paso.add(cbxTipo);

		JLabel lblNewLabel_2 = new JLabel("A\u00F1os de Experiencia");
		lblNewLabel_2.setForeground(new Color(0, 0, 51));
		lblNewLabel_2.setBounds(352, 47, 175, 16);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 18));
		paso.add(lblNewLabel_2);

		spnExp = new JSpinner();
		spnExp.setBounds(352, 70, 85, 30);
		spnExp.setForeground(new Color(0, 0, 51));
		spnExp.setBackground(SystemColor.controlHighlight);
		spnExp.setFont(new Font("Calibri", Font.PLAIN, 18));
		paso.add(spnExp);
		
		JLabel lblPasode = new JLabel("Paso 3 de 4");
		lblPasode.setForeground(new Color(1, 88, 248));
		lblPasode.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPasode.setBounds(0, 0, 105, 16);
		paso.add(lblPasode);
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n");
		lblInformacin.setForeground(SystemColor.controlDkShadow);
		lblInformacin.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblInformacin.setBounds(109, 1, 130, 16);
		paso.add(lblInformacin);
		
		txtCarrera = new TextFieldRedond(25);
		txtCarrera.setForeground(new Color(0, 0, 51));
		txtCarrera.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCarrera.setBackground(SystemColor.controlHighlight);
		txtCarrera.setBounds(0, 159, 294, 30);
		paso.add(txtCarrera);
		
		JLabel label = new JLabel("Carrera");
		label.setForeground(new Color(0, 0, 51));
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		label.setBounds(0, 136, 200, 20);
		paso.add(label);
		
		txtHabilidades = new TextFieldRedond(25);
		txtHabilidades.setForeground(new Color(0, 0, 51));
		txtHabilidades.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtHabilidades.setBackground(SystemColor.controlHighlight);
		txtHabilidades.setBounds(0, 333, 500, 50);
		paso.add(txtHabilidades);
		
		JLabel label_3 = new JLabel("Habilidades");
		label_3.setForeground(new Color(0, 0, 51));
		label_3.setFont(new Font("Calibri", Font.BOLD, 18));
		label_3.setBounds(0, 308, 200, 20);
		paso.add(label_3);
		
		JLabel label_4 = new JLabel("\u00C1rea t\u00E9cnica");
		label_4.setForeground(new Color(0, 0, 51));
		label_4.setFont(new Font("Calibri", Font.BOLD, 18));
		label_4.setBounds(0, 218, 200, 20);
		paso.add(label_4);
		
		txtAreaTecnico = new TextFieldRedond(25);
		txtAreaTecnico.setForeground(new Color(0, 0, 51));
		txtAreaTecnico.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtAreaTecnico.setBackground(SystemColor.controlHighlight);
		txtAreaTecnico.setBounds(0, 243, 294, 30);
		paso.add(txtAreaTecnico);

		return paso;
	}

	private JPanel crearPaso4() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel.setBounds(0, 53, 200, 20);
		paso.add(lblNewLabel);

		txtUser = new TextFieldRedond(25);
		txtUser.setForeground(new Color(0, 0, 51));
		txtUser.setBackground(SystemColor.controlHighlight);
		txtUser.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtUser.setBounds(0, 86, 294, 30);
		paso.add(txtUser);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel_1.setBounds(0, 141, 200, 20);
		paso.add(lblNewLabel_1);

		passwordField = new PasswordFieldRedond(25);
		passwordField.setForeground(new Color(0, 0, 51));
		passwordField.setBackground(SystemColor.controlHighlight);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField.setBounds(0, 174, 294, 30);

		((AbstractDocument) passwordField.getDocument()).setDocumentFilter(Validaciones.filtroLongitudMaxima(14));

		caracterOculto = passwordField.getEchoChar();

		lblVerPassword = new JLabel("aaa");
		lblVerPassword.setOpaque(false);
		lblVerPassword.setHorizontalAlignment(JLabel.CENTER);
		lblVerPassword.setBounds(262, 179, 18, 18);
		lblVerPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				alternarVisibilidadPassword();
			}
		});
		colocarImagen(lblVerPassword, "/img/ver.png");

		paso.add(lblVerPassword);
		paso.add(passwordField);

		fotoPerfil = new FotoPerfilRedond(114);
		fotoPerfil.setBounds(449, 38, 200, 206);
		paso.add(fotoPerfil);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(SystemColor.controlDkShadow);
		lblUsuario.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblUsuario.setBounds(109, 1, 130, 16);
		paso.add(lblUsuario);
		
		JLabel label_1 = new JLabel("Paso 3 de 4");
		label_1.setForeground(new Color(1, 88, 248));
		label_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		label_1.setBounds(0, 0, 105, 16);
		paso.add(label_1);

		return paso;
	}
}