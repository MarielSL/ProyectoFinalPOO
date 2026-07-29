package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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
import logico.BolsaEmpleo;
import logico.Obrero;
import logico.Persona;
import logico.Sexo;
import logico.Tecnico;
import logico.TipoPersona;
import logico.Universitario;
import logico.Usuario;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class RegistrarSolicitante extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
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
	private CardLayout tipoLayout;
	private JPanel pnlDatosTipo;
	private TextFieldRedond txtCarrera;
	private TextFieldRedond txtAreaTecnico;
	private TextFieldRedond txtHabilidades;
	private JTextField txtUser;
	private JPasswordField passwordField;
	private FotoPerfilRedond fotoPerfil;
	private Persona mySolicitante = null;
	private JSpinner spnExp;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarSolicitante.class.getResource("/img/AppIconoFull.png")));
		persona = mySolicitante;
		if(mySolicitante == null) {
			setTitle("Registrar Solicitante");
		}
		else {
			setTitle("Modificar Solicitante");
		}
		
		setBounds(100, 100, 734, 560);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 724, 550);
		contentPanel.add(panel);
		panel.setLayout(null);
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(0, 0, 51));
		panelTitulo.setBounds(0, 0, 717, 65);
		panel.add(panelTitulo);
		panelTitulo.setLayout(null);

		JLabel lblTitulo = new JLabel("Reg\u00EDstrate para acceder a las ofertas");
		lblTitulo.setForeground(new Color(255, 153, 0));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setBounds(20, 16, 650, 35);
		panelTitulo.add(lblTitulo);
		PanelRedond cardBlanca = new PanelRedond(20);
		cardBlanca.setBackground(new Color(255, 255, 255));
		cardBlanca.setBounds(27, 75, 664, 330);
		panel.add(cardBlanca);
		cardBlanca.setLayout(null);
		stepsLayout = new CardLayout();
		pnlSteps = new JPanel(stepsLayout);
		pnlSteps.setOpaque(false);
		pnlSteps.setBounds(20, 15, 624, 300);
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
		btnAtras = new BotonRedond("Atr\u00E1s", 25);
		btnAtras.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAtras.setBackground(new Color(220, 220, 220));
		btnAtras.setForeground(new Color(0, 0, 51));
		btnAtras.setBounds(27, 460, 110, 34);
		btnAtras.setVisible(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAtras();
			}
		});
		panel.add(btnAtras);
		btnSiguiente = new BotonRedond("Continuar", 25);
		btnSiguiente.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSiguiente.setBackground(new Color(255, 153, 0));
		btnSiguiente.setForeground(new Color(0, 0, 51));
		btnSiguiente.setBounds(577, 460, 114, 34);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irSiguiente();
				if(pasoActual == 4) {
					HomeCandidato home = new HomeCandidato();
					home.setVisible(true);
					dispose();
					
				}
			}
		});
		panel.add(btnSiguiente);
		loadSolicitante();

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RegistrarSolicitante.class.getResource("/img/Fondo-General.png")));
		lblNewLabel.setBounds(0, 0, 724, 513);
		panel.add(lblNewLabel);
		actualizarDots();


	}

	private void loadSolicitante() {
		
		if(mySolicitante == null) {
			return;
		}
		
		txtNombre.setText(mySolicitante.getNombre());
		txtApellido.setText(mySolicitante.getApellido());
		
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
		
		txtCedula.setText(mySolicitante.getCedula());
		txtCiudad.setText(mySolicitante.getCiudad());
		txtCorreo.setText(mySolicitante.getUser().getCorreo());
		txtDireccion.setText(mySolicitante.getDireccion());
		txtTelefono.setText(mySolicitante.getTelefono());
		txtUser.setText(mySolicitante.getUser().getUsername());
		passwordField.setText(mySolicitante.getUser().getPassword());
		cbxSexo.setSelectedItem(mySolicitante.getSexo());
		spnFechaNacim.setValue(mySolicitante.getFechNacim());
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
		} else if (pasoActual == 4) {
			if(!validarPaso4()) return;
			btnSiguiente.setText("Finalizar");

		} else {
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
		String contrasena = new String(passwordField.getPassword());
		if (BolsaEmpleo.getInstancia().verifUsuario(txtUser.getText(), contrasena)) {
			JOptionPane.showConfirmDialog(null, "Usuario en uso.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		Usuario newUser = new Usuario("U-" + BolsaEmpleo.generadorIdUser, txtUser.getText(), contrasena, null, null, null, null, null);
		BolsaEmpleo.getInstancia().regUser(newUser);
		BolsaEmpleo.getInstancia().setLoginUser(newUser);
		dispose();
		return true;
	}

	private void registrarSolicitante() {
		Date fechaSeleccionada = (Date) spnFechaNacim.getValue();
		LocalDate fechaNacim = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		TipoPersona tipo = (TipoPersona) cbxTipo.getSelectedItem();

		if(mySolicitante == null) {
			String id = "P-" + BolsaEmpleo.generadorIdPersona;
			Persona persona;
			if (tipo == TipoPersona.UNIVERSITARIO) {
				persona = new Universitario(id, txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtTelefono.getText(), txtDireccion.getText(), (Sexo) cbxSexo.getSelectedItem(), txtCiudad.getText(), chkMudarse.isSelected(), chkLicencia.isSelected(), chkEmpleado.isSelected(), user,(int) spnExp.getValue(), txtCarrera.getText());

			} else if (tipo == TipoPersona.TECNICO) {
				persona = new Tecnico(id, txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtTelefono.getText(), txtDireccion.getText(), (Sexo) cbxSexo.getSelectedItem(), txtCiudad.getText(), chkMudarse.isSelected(), chkLicencia.isSelected(), chkEmpleado.isSelected(), user, (int) spnExp.getValue(),txtAreaTecnico.getText());

			} else {
				persona = new Obrero(id, txtCedula.getText(), txtNombre.getText(), txtApellido.getText(), fechaNacim, txtTelefono.getText(), txtDireccion.getText(), (Sexo) cbxSexo.getSelectedItem(), txtCiudad.getText(), chkMudarse.isSelected(), chkLicencia.isSelected(), chkEmpleado.isSelected(), user,(int) spnExp.getValue(), txtHabilidades.getText());

			}

			BolsaEmpleo.getInstancia().regPersona(persona);

			if (user != null) {
				user.setCorreo(txtCorreo.getText());
				user.setPersona(persona);
			}

			JOptionPane.showMessageDialog(null, "Se ha registrado el solicitante.", "Informaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
			clear();
			dispose();
			//Aca se le agrega el setVisible del menu para los solicitantes

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
			
			BolsaEmpleo.getInstancia().getLoginUser().setCorreo(txtCorreo.getText());
			BolsaEmpleo.getInstancia().getLoginUser().setUsername(txtUser.getText());
			BolsaEmpleo.getInstancia().getLoginUser().setPassword(new String(passwordField.getPassword()));
			
			
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

	private JPanel crearPaso1() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblCedula = new JLabel("C\u00E9dula");
		lblCedula.setForeground(new Color(0, 0, 51));
		lblCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCedula.setBounds(0, 0, 200, 20);
		paso.add(lblCedula);
		txtCedula = new TextFieldRedond(25);
		txtCedula.setForeground(new Color(0, 0, 51));
		txtCedula.setBackground(SystemColor.controlHighlight);
		txtCedula.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCedula.setBounds(0, 25, 294, 30);
		paso.add(txtCedula);
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setForeground(new Color(0, 0, 51));
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(330, 0, 200, 20);
		paso.add(lblTelefono);
		txtTelefono = new TextFieldRedond(25);
		txtTelefono.setForeground(new Color(0, 0, 51));
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtTelefono.setBounds(330, 25, 294, 30);
		paso.add(txtTelefono);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(0, 0, 51));
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNombre.setBounds(0, 75, 200, 20);
		paso.add(lblNombre);
		txtNombre = new TextFieldRedond(25);
		txtNombre.setForeground(new Color(0, 0, 51));
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtNombre.setBounds(0, 100, 294, 30);
		paso.add(txtNombre);
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(new Color(0, 0, 51));
		lblApellido.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblApellido.setBounds(330, 75, 200, 20);
		paso.add(lblApellido);
		txtApellido = new TextFieldRedond(25);
		txtApellido.setForeground(new Color(0, 0, 51));
		txtApellido.setBackground(SystemColor.controlHighlight);
		txtApellido.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtApellido.setBounds(330, 100, 294, 30);
		paso.add(txtApellido);
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setForeground(new Color(0, 0, 51));
		lblCorreo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCorreo.setBounds(0, 150, 200, 20);
		paso.add(lblCorreo);
		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCorreo.setBounds(0, 175, 624, 30);
		paso.add(txtCorreo);
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setForeground(new Color(0, 0, 51));
		lblSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSexo.setBounds(0, 225, 200, 20);
		paso.add(lblSexo);
		cbxSexo = new ComboBoxRedond<Sexo>(25);
		cbxSexo.setForeground(new Color(0, 0, 51));
		cbxSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxSexo.setBackground(SystemColor.controlHighlight);
		cbxSexo.setBounds(0, 250, 180, 30);
		cbxSexo.setModel(new DefaultComboBoxModel<Sexo>(Sexo.values()));
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
		lblFecha.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblFecha.setBounds(210, 225, 220, 20);
		paso.add(lblFecha);
		Calendar cal = Calendar.getInstance();
		Date hoy = cal.getTime();
		cal.add(Calendar.YEAR, -18);
		Date inicial = cal.getTime();
		SpinnerDateModel dateModel = new SpinnerDateModel(inicial, null, hoy, Calendar.DAY_OF_MONTH);
		spnFechaNacim = new JSpinner(dateModel);
		spnFechaNacim.setEditor(new JSpinner.DateEditor(spnFechaNacim, "dd/MM/yyyy"));
		spnFechaNacim.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnFechaNacim.setBounds(210, 250, 150, 30);
		paso.add(spnFechaNacim);
		return paso;
	}

	private JPanel crearPaso2() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setForeground(new Color(0, 0, 51));
		lblDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDireccion.setBounds(0, 0, 200, 20);
		paso.add(lblDireccion);
		txtDireccion = new TextFieldRedond(25);
		txtDireccion.setForeground(new Color(0, 0, 51));
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDireccion.setBounds(0, 25, 294, 30);
		paso.add(txtDireccion);
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setForeground(new Color(0, 0, 51));
		lblCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCiudad.setBounds(330, 0, 200, 20);
		paso.add(lblCiudad);

		txtCiudad = new TextFieldRedond(25);
		txtCiudad.setForeground(new Color(0, 0, 51));
		txtCiudad.setBackground(SystemColor.controlHighlight);
		txtCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCiudad.setBounds(330, 25, 294, 30);
		paso.add(txtCiudad);

		JLabel lblDisp = new JLabel("Disponibilidad");
		lblDisp.setForeground(new Color(0, 0, 51));
		lblDisp.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDisp.setBounds(0, 85, 200, 20);
		paso.add(lblDisp);

		chkMudarse = new JCheckBox("Disponible para mudarse");
		chkMudarse.setForeground(new Color(0, 0, 51));
		chkMudarse.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkMudarse.setOpaque(false);
		chkMudarse.setBounds(0, 115, 250, 25);
		paso.add(chkMudarse);
		chkLicencia = new JCheckBox("Licencia de conducir");
		chkLicencia.setForeground(new Color(0, 0, 51));
		chkLicencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkLicencia.setOpaque(false);
		chkLicencia.setBounds(0, 150, 250, 25);
		paso.add(chkLicencia);
		chkEmpleado = new JCheckBox("Actualmente empleado");
		chkEmpleado.setForeground(new Color(0, 0, 51));
		chkEmpleado.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkEmpleado.setOpaque(false);
		chkEmpleado.setBounds(0, 185, 250, 25);
		paso.add(chkEmpleado);
		return paso;
	}

	private JPanel crearPaso3() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblTipo = new JLabel("Tipo de solicitante");
		lblTipo.setForeground(new Color(0, 0, 51));
		lblTipo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTipo.setBounds(0, 0, 200, 20);
		paso.add(lblTipo);
		cbxTipo = new ComboBoxRedond<TipoPersona>(25);
		cbxTipo.setForeground(new Color(0, 0, 51));
		cbxTipo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxTipo.setBackground(SystemColor.controlHighlight);
		cbxTipo.setBounds(0, 25, 225, 30);
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
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoPersona seleccionado = (TipoPersona) cbxTipo.getSelectedItem();
				if (seleccionado != null) {
					tipoLayout.show(pnlDatosTipo, seleccionado.name());
				}
			}
		});
		paso.add(cbxTipo);
		tipoLayout = new CardLayout();
		pnlDatosTipo = new JPanel(tipoLayout);
		pnlDatosTipo.setOpaque(false);
		pnlDatosTipo.setBounds(0, 75, 624, 160);
		paso.add(pnlDatosTipo);
		JPanel cardUniversitario = new JPanel();
		cardUniversitario.setOpaque(false);
		cardUniversitario.setLayout(null);
		JLabel lblCarrera = new JLabel("Carrera");
		lblCarrera.setForeground(new Color(0, 0, 51));
		lblCarrera.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCarrera.setBounds(0, 0, 200, 20);
		cardUniversitario.add(lblCarrera);
		txtCarrera = new TextFieldRedond(25);
		txtCarrera.setForeground(new Color(0, 0, 51));
		txtCarrera.setBackground(SystemColor.controlHighlight);
		txtCarrera.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCarrera.setBounds(0, 25, 294, 30);
		cardUniversitario.add(txtCarrera);
		pnlDatosTipo.add(cardUniversitario, TipoPersona.UNIVERSITARIO.name());
		JPanel cardTecnico = new JPanel();
		cardTecnico.setOpaque(false);
		cardTecnico.setLayout(null);
		JLabel lblArea = new JLabel("\u00C1rea t\u00E9cnica");
		lblArea.setForeground(new Color(0, 0, 51));
		lblArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblArea.setBounds(0, 0, 200, 20);
		cardTecnico.add(lblArea);
		txtAreaTecnico = new TextFieldRedond(25);
		txtAreaTecnico.setForeground(new Color(0, 0, 51));
		txtAreaTecnico.setBackground(SystemColor.controlHighlight);
		txtAreaTecnico.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtAreaTecnico.setBounds(0, 25, 294, 30);
		cardTecnico.add(txtAreaTecnico);
		pnlDatosTipo.add(cardTecnico, TipoPersona.TECNICO.name());
		JPanel cardObrero = new JPanel();
		cardObrero.setOpaque(false);
		cardObrero.setLayout(null);
		JLabel lblHabilidades = new JLabel("Habilidades");
		lblHabilidades.setForeground(new Color(0, 0, 51));
		lblHabilidades.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblHabilidades.setBounds(0, 0, 200, 20);
		cardObrero.add(lblHabilidades);
		txtHabilidades = new TextFieldRedond(25);
		txtHabilidades.setForeground(new Color(0, 0, 51));
		txtHabilidades.setBackground(SystemColor.controlHighlight);
		txtHabilidades.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtHabilidades.setBounds(0, 25, 500, 30);
		cardObrero.add(txtHabilidades);
		pnlDatosTipo.add(cardObrero, TipoPersona.OBRERO.name());
		
		JLabel lblNewLabel_2 = new JLabel("A\u00F1os de Experiencia");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(352, 2, 175, 16);
		paso.add(lblNewLabel_2);
		
		spnExp = new JSpinner();
		spnExp.setForeground(new Color(0, 0, 51));
		spnExp.setBackground(SystemColor.controlHighlight);
		spnExp.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnExp.setBounds(352, 25, 85, 30);
		paso.add(spnExp);

		return paso;
	}

	private JPanel crearPaso4() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 40, 200, 20);
		paso.add(lblNewLabel);

		txtUser = new TextFieldRedond(25);
		txtUser.setForeground(new Color(0, 0, 51));
		txtUser.setBackground(SystemColor.controlHighlight);
		txtUser.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtUser.setBounds(0, 73, 294, 30);
		paso.add(txtUser);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(0, 128, 200, 20);
		paso.add(lblNewLabel_1);

		passwordField = new PasswordFieldRedond(25);
		passwordField.setForeground(new Color(0, 0, 51));
		passwordField.setBackground(SystemColor.controlHighlight);
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 18));
		passwordField.setBounds(0, 161, 294, 30);
		paso.add(passwordField);

		fotoPerfil = new FotoPerfilRedond(114);
		fotoPerfil.setBounds(353, 40, 200, 206);
		paso.add(fotoPerfil);

		return paso;
	}
}