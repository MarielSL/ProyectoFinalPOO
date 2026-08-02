package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoDecision;
import logico.EstadoOferta;
import logico.EstadoSolicitud;
import logico.Obrero;
import logico.Oferta;
import logico.Persona;
import logico.Sexo;
import logico.SolicitudEmpleo;
import logico.Tecnico;
import logico.Universitario;
import red.ConexionCliente;
import red.DatosDecidirCandidato;
import red.Peticion;
import red.Respuesta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.time.format.DateTimeFormatter;

public class VerPostulante extends JFrame {

	private JPanel contentPane;
	private JLabel lblFotoPerfil;
	private JLabel lblNombre;
	private TextFieldRedond txtTipoSolicitante;
	private PanelRedond panelCoincidencia;
	private JLabel lblTectCoincidencia;
	private JLabel lblPorcent;
	private PanelRedond panelFechaNacim;
	private JLabel lblFechaNacim;
	private BotonRedond btnRechazar;
	private BotonRedond btnContratar;
	private JLabel lblCalendarIcon;
	private JLabel lblLocationIcon;
	private JLabel lblUserIcon;
	private JLabel lblBriefCaseIcon;
	private JLabel lblBirreteIcon;
	private JLabel lblUserIcon2;
	private JLabel lblSolCiudad;
	private JLabel lblTelefono;
	private JLabel lblSolSexo;
	private JLabel lbluser;
	private JLabel lblEmail;
	private JLabel lblTecnico;
	private JLabel lblLicencia;
	private JLabel lblDispMud;
	private JLabel lblExp;
	private JLabel lblHabilid;
	private JLabel lblCarr;
	private JPanel panel_Tecnico;
	private JPanel panel_Obrero;
	private JPanel panel_universitario;
	private Empresa empresa;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerPostulante frame = new VerPostulante(null, null, null, 0f);
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
	public VerPostulante(Persona solicitante, Oferta oferta, SolicitudEmpleo solicitud, float porcentaje) { 
		if (BolsaEmpleo.getInstancia().getLoginUser() != null) {
			empresa = BolsaEmpleo.getInstancia().getLoginUser().getEmpresa();
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage(VerPostulante.class.getResource("/img/AppIconoFull.png")));
		Utilidades.aplicarIcono(this);
		setTitle("Ver Postulante");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 888, 860);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 888, 85);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ver Candidato");
		lblNewLabel.setForeground(new Color(255, 153, 0));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setBounds(333, 31, 187, 32);
		panel.add(lblNewLabel);

		String nombreEmpresa = "Mi Empresa";
		if (empresa != null) {
			nombreEmpresa = empresa.getNombre();
		}
		int anchoNombre = 14 * nombreEmpresa.length() + 20;

		JLabel iconoLogo = new JLabel("");
		iconoLogo.setBounds(762, 0, 114, 88);
		colocarImagen(iconoLogo, "/img/iconoLogo_FondoOscuro.png");
		panel.add(iconoLogo);

		BotonRedond btnMenu = new BotonRedond("",25);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BarraEmpresa menu = new BarraEmpresa();
				menu.setVisible(true);
			}
		});
		btnMenu.setBackground(new Color(0, 0, 51));
		btnMenu.setColorHover(new Color(0, 51, 102));
		btnMenu.setBounds(12, 12, 60, 60);
		colocarIconoBoton(btnMenu,"/img/menu-dots-vertical(White).png",25,25);
		btnMenu.setMargin(new Insets(0, 0, 0, 0));
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setFocusPainted(false);
		btnMenu.setOpaque(false);
		panel.add(btnMenu);



		lblFotoPerfil = new JLabel("New label");
		lblFotoPerfil.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/User Icon.png")));
		lblFotoPerfil.setBounds(119, 98, 150, 150);
		contentPane.add(lblFotoPerfil);

		lblNombre = new JLabel("Mariel Sánchez");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(0, 0, 51));
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNombre.setBounds(93, 270, 203, 35);
		contentPane.add(lblNombre);

		txtTipoSolicitante = new TextFieldRedond(30);
		txtTipoSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoSolicitante.setForeground(new Color(65, 95, 170));
		txtTipoSolicitante.setBackground(new Color(195, 220, 255));
		txtTipoSolicitante.setFont(new Font("Calibri", Font.BOLD, 20));
		txtTipoSolicitante.setBounds(119, 318, 150, 30);
		contentPane.add(txtTipoSolicitante);
		txtTipoSolicitante.setColumns(10);
		txtTipoSolicitante.setFocusable(false);

		panelCoincidencia = new PanelRedond(30);
		panelCoincidencia.setBackground(new Color(153, 204, 153));
		panelCoincidencia.setBounds(93, 361, 203, 76);
		contentPane.add(panelCoincidencia);
		panelCoincidencia.setLayout(null);

		lblTectCoincidencia = new JLabel("Coincidencia con la Oferta:");
		lblTectCoincidencia.setForeground(new Color(0, 102, 51));
		lblTectCoincidencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTectCoincidencia.setFont(new Font("Calibri", Font.BOLD, 16));
		lblTectCoincidencia.setBounds(0, 15, 203, 16);
		panelCoincidencia.add(lblTectCoincidencia);

		lblPorcent = new JLabel("New label");
		lblPorcent.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcent.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPorcent.setBounds(37, 30, 126, 42);
		panelCoincidencia.add(lblPorcent);

		panelFechaNacim = new PanelRedond(25);
		panelFechaNacim.setBackground(new Color(195, 220, 255));
		panelFechaNacim.setBounds(24, 474, 45, 45);
		contentPane.add(panelFechaNacim);
		panelFechaNacim.setLayout(null);

		lblCalendarIcon = new JLabel("New label");
		lblCalendarIcon.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/calendar.png")));
		lblCalendarIcon.setBounds(7, 7, 30, 30);
		panelFechaNacim.add(lblCalendarIcon);
		colocarImagen(lblCalendarIcon,"/img/calendar.png");

		PanelRedond panelRedond = new PanelRedond(25);
		panelRedond.setBackground(new Color(195, 220, 255));
		panelRedond.setBounds(24, 532, 45, 45);
		contentPane.add(panelRedond);
		panelRedond.setLayout(null);

		JLabel lblTelefIcon = new JLabel("");
		lblTelefIcon.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/phone-flip.png")));
		lblTelefIcon.setVerticalAlignment(SwingConstants.CENTER);
		lblTelefIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefIcon.setBounds(7, 7, 30, 30);
		panelRedond.add(lblTelefIcon);
		colocarImagen(lblTelefIcon,"/img/phone-flip.png");

		PanelRedond panelRedond_1 = new PanelRedond(25);
		panelRedond_1.setBackground(new Color(195, 220, 255));
		panelRedond_1.setBounds(24, 590, 45, 45);
		contentPane.add(panelRedond_1);
		panelRedond_1.setLayout(null);

		lblLocationIcon = new JLabel("");
		lblLocationIcon.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/marker.png")));
		lblLocationIcon.setVerticalAlignment(SwingConstants.CENTER);
		lblLocationIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocationIcon.setBounds(7, 7, 30, 30);
		panelRedond_1.add(lblLocationIcon);
		colocarImagen(lblLocationIcon,"/img/marker.png");

		PanelRedond panelRedond_2 = new PanelRedond(25);
		panelRedond_2.setBackground(new Color(195, 220, 255));
		panelRedond_2.setBounds(24, 648, 45, 45);
		contentPane.add(panelRedond_2);
		panelRedond_2.setLayout(null);

		lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/user2.png")));
		lblUserIcon.setVerticalAlignment(SwingConstants.CENTER);
		lblUserIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserIcon.setBounds(7, 7, 30, 30);
		panelRedond_2.add(lblUserIcon);
		colocarImagen(lblUserIcon,"/img/user2.png");


		JLabel lblNewLabel_1 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(85, 488, 166, 20);
		contentPane.add(lblNewLabel_1);

		lblFechaNacim = new JLabel("25/10/2007");
		lblFechaNacim.setForeground(new Color(0, 0, 51));
		lblFechaNacim.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblFechaNacim.setBounds(263, 488, 111, 20);
		contentPane.add(lblFechaNacim);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setForeground(new Color(0, 0, 51));
		lblTelfono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelfono.setBounds(85, 545, 84, 20);
		contentPane.add(lblTelfono);

		lblTelefono = new JLabel("New label");
		lblTelefono.setForeground(new Color(0, 0, 51));
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(181, 545, 155, 20);
		contentPane.add(lblTelefono);

		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setForeground(new Color(0, 0, 51));
		lblCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCiudad.setBounds(85, 602, 84, 20);
		contentPane.add(lblCiudad);

		lblSolCiudad = new JLabel("New label");
		lblSolCiudad.setForeground(new Color(0, 0, 51));
		lblSolCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSolCiudad.setBounds(158, 602, 216, 20);
		contentPane.add(lblSolCiudad);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setForeground(new Color(0, 0, 51));
		lblSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSexo.setBounds(85, 659, 61, 20);
		contentPane.add(lblSexo);

		lblSolSexo = new JLabel("New label");
		lblSolSexo.setForeground(new Color(0, 0, 51));
		lblSolSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSolSexo.setBounds(158, 659, 155, 20);
		contentPane.add(lblSolSexo);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(SystemColor.controlShadow);
		separator.setBackground(SystemColor.controlShadow);
		separator.setBounds(396, 98, 1, 609);
		contentPane.add(separator);

		PanelRedond panelRedond_3 = new PanelRedond(25);
		panelRedond_3.setBackground(new Color(195, 220, 255));
		panelRedond_3.setBounds(436, 114, 60, 60);
		contentPane.add(panelRedond_3);
		panelRedond_3.setLayout(null);

		lblBriefCaseIcon = new JLabel("New label");
		lblBriefCaseIcon.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/briefcase(blue).png")));
		lblBriefCaseIcon.setBounds(7, 7, 45, 45);
		panelRedond_3.add(lblBriefCaseIcon);
		colocarImagen(lblBriefCaseIcon,"/img/briefcase(blue).png");

		JLabel lblInformacinLaboral = new JLabel("Informaci\u00F3n Laboral");
		lblInformacinLaboral.setForeground(new Color(0, 0, 51));
		lblInformacinLaboral.setFont(new Font("Calibri", Font.BOLD, 24));
		lblInformacinLaboral.setBounds(508, 132, 238, 22);
		contentPane.add(lblInformacinLaboral);

		JLabel lblNewLabel_2 = new JLabel("A\u00F1os de Experiencia:");
		lblNewLabel_2.setForeground(new Color(0, 0, 51));
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_2.setBounds(436, 213, 260, 20);
		contentPane.add(lblNewLabel_2);

		lblExp = new JLabel("New label");
		lblExp.setForeground(new Color(0, 0, 51));
		lblExp.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblExp.setBounds(708, 213, 139, 20);
		contentPane.add(lblExp);

		JLabel lblDisponibilidadParaMudarse = new JLabel("Disponibilidad para Mudarse:");
		lblDisponibilidadParaMudarse.setForeground(new Color(0, 0, 51));
		lblDisponibilidadParaMudarse.setFont(new Font("Calibri", Font.BOLD, 20));
		lblDisponibilidadParaMudarse.setBounds(436, 252, 260, 20);
		contentPane.add(lblDisponibilidadParaMudarse);

		lblDispMud = new JLabel("New label");
		lblDispMud.setForeground(new Color(0, 0, 51));
		lblDispMud.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDispMud.setBounds(708, 252, 119, 20);
		contentPane.add(lblDispMud);

		JLabel lblLicenciaDeConducir = new JLabel("Licencia de Conducir:");
		lblLicenciaDeConducir.setForeground(new Color(0, 0, 51));
		lblLicenciaDeConducir.setFont(new Font("Calibri", Font.BOLD, 20));
		lblLicenciaDeConducir.setBounds(436, 291, 260, 20);
		contentPane.add(lblLicenciaDeConducir);

		lblLicencia = new JLabel("New label");
		lblLicencia.setForeground(new Color(0, 0, 51));
		lblLicencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblLicencia.setBounds(708, 291, 119, 20);
		contentPane.add(lblLicencia);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.controlShadow);
		separator_1.setBackground(SystemColor.controlShadow);
		separator_1.setBounds(436, 350, 392, 1);
		contentPane.add(separator_1);

		PanelRedond panelRedond_4 = new PanelRedond(25);
		panelRedond_4.setBackground(new Color(195, 220, 255));
		panelRedond_4.setBounds(436, 377, 60, 60);
		contentPane.add(panelRedond_4);
		panelRedond_4.setLayout(null);

		lblBirreteIcon = new JLabel("New label");
		lblBirreteIcon.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/graduation-cap.png")));
		lblBirreteIcon.setBounds(7, 7, 45, 45);
		panelRedond_4.add(lblBirreteIcon);
		colocarImagen(lblBirreteIcon,"/img/graduation-cap.png");

		JLabel lblInformacinAcadmica = new JLabel("Informaci\u00F3n Acad\u00E9mica /  T\u00E9cnica");
		lblInformacinAcadmica.setForeground(new Color(0, 0, 51));
		lblInformacinAcadmica.setFont(new Font("Calibri", Font.BOLD, 24));
		lblInformacinAcadmica.setBounds(508, 396, 338, 22);
		contentPane.add(lblInformacinAcadmica);

		JPanel panel_TiposSolicitantes = new JPanel();
		panel_TiposSolicitantes.setBounds(424, 450, 420, 76);
		contentPane.add(panel_TiposSolicitantes);
		panel_TiposSolicitantes.setLayout(new CardLayout(0, 0));

		panel_Tecnico = new JPanel();
		panel_TiposSolicitantes.add(panel_Tecnico, "Tecnico");
		panel_Tecnico.setLayout(null);

		JLabel lblTcnico = new JLabel("T\u00E9cnico:");
		lblTcnico.setForeground(new Color(0, 0, 51));
		lblTcnico.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTcnico.setBounds(12, 25, 83, 20);
		panel_Tecnico.add(lblTcnico);

		lblTecnico = new JLabel("New label");
		lblTecnico.setForeground(new Color(0, 0, 51));
		lblTecnico.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTecnico.setBounds(98, 25, 310, 20);
		panel_Tecnico.add(lblTecnico);

		panel_Obrero = new JPanel();
		panel_TiposSolicitantes.add(panel_Obrero, "Obrero");
		panel_Obrero.setLayout(null);

		JLabel lblHabilidades = new JLabel("Habilidades:");
		lblHabilidades.setBounds(12, 16, 123, 26);
		lblHabilidades.setForeground(new Color(0, 0, 51));
		lblHabilidades.setFont(new Font("Calibri", Font.BOLD, 20));
		panel_Obrero.add(lblHabilidades);

		lblHabilid = new JLabel("New label");
		lblHabilid.setVerticalAlignment(SwingConstants.TOP);
		lblHabilid.setForeground(new Color(0, 0, 51));
		lblHabilid.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblHabilid.setBounds(132, 16, 276, 47);
		panel_Obrero.add(lblHabilid);

		panel_universitario = new JPanel();
		panel_TiposSolicitantes.add(panel_universitario, "Universitario");
		panel_universitario.setLayout(null);

		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(12, 20, 89, 26);
		panel_universitario.add(lblCarrera);
		lblCarrera.setForeground(new Color(0, 0, 51));
		lblCarrera.setFont(new Font("Calibri", Font.BOLD, 20));

		lblCarr = new JLabel("New label");
		lblCarr.setVerticalAlignment(SwingConstants.TOP);
		lblCarr.setForeground(new Color(0, 0, 51));
		lblCarr.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCarr.setBounds(132, 22, 276, 20);
		panel_universitario.add(lblCarr);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.controlShadow);
		separator_2.setBackground(SystemColor.controlShadow);
		separator_2.setBounds(436, 555, 392, 1);
		contentPane.add(separator_2);

		PanelRedond panelRedond_5 = new PanelRedond(25);
		panelRedond_5.setBackground(new Color(195, 220, 255));
		panelRedond_5.setBounds(436, 575, 60, 60);
		contentPane.add(panelRedond_5);
		panelRedond_5.setLayout(null);

		lblUserIcon2 = new JLabel("New label");
		lblUserIcon2.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/user2.png")));
		lblUserIcon2.setBounds(7, 7, 45, 45);
		panelRedond_5.add(lblUserIcon2);
		colocarImagen(lblUserIcon2,"/img/user2.png");

		JLabel lblUsuarioAsociado = new JLabel("Usuario Asociado");
		lblUsuarioAsociado.setForeground(new Color(0, 0, 51));
		lblUsuarioAsociado.setFont(new Font("Calibri", Font.BOLD, 24));
		lblUsuarioAsociado.setBounds(508, 596, 192, 22);
		contentPane.add(lblUsuarioAsociado);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setForeground(new Color(0, 0, 51));
		lblCorreo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCorreo.setBounds(436, 661, 74, 20);
		contentPane.add(lblCorreo);

		lblEmail = new JLabel("New label");
		lblEmail.setForeground(new Color(0, 0, 51));
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblEmail.setBounds(549, 661, 279, 20);
		contentPane.add(lblEmail);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(0, 0, 51));
		lblUsuario.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsuario.setBounds(436, 698, 74, 20);
		contentPane.add(lblUsuario);

		lbluser = new JLabel("New label");
		lbluser.setForeground(new Color(0, 0, 51));
		lbluser.setFont(new Font("Calibri", Font.PLAIN, 18));
		lbluser.setBounds(549, 694, 182, 20);
		contentPane.add(lbluser);

		btnRechazar = new BotonRedond("Rechazar",30);
		btnRechazar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rechazarConHilo(oferta, solicitud);
			}
		});
		btnRechazar.setBackground(new Color(255, 102, 102));
		btnRechazar.setForeground(new Color(255, 255, 255));
		btnRechazar.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRechazar.setBounds(182, 748, 192, 40);
		contentPane.add(btnRechazar);

		btnContratar = new BotonRedond("Contratar",30);
		btnContratar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contratarConHilo(oferta, solicitud);
			}
		});
		btnContratar.setForeground(new Color(255, 255, 255));
		btnContratar.setBackground(new Color(153, 204, 153));
		btnContratar.setFont(new Font("Calibri", Font.BOLD, 20));
		btnContratar.setBounds(486, 748, 192, 40);
		contentPane.add(btnContratar);
		CardLayout cardLayout = (CardLayout) panel_TiposSolicitantes.getLayout();
		cargarDatosConHilo(solicitante, oferta, solicitud, porcentaje, panel_TiposSolicitantes, cardLayout);

	}
	
	//le quite el socket y el hilo como ya venia cargado el porcentaje 

	private void cargarDatosConHilo(Persona solicitante, Oferta oferta, SolicitudEmpleo solicitud, float porcentaje, JPanel panelTiposSolicitantes, CardLayout cardLayout) {
	    mostrarDatosSolicitante(solicitante, porcentaje, panelTiposSolicitantes, cardLayout);

	    boolean datosValidos = solicitante != null && oferta != null && solicitud != null;
	    btnRechazar.setEnabled(datosValidos);
	    btnContratar.setEnabled(datosValidos && oferta.getCantPuestos() > 0);
	}

	private void mostrarDatosSolicitante(Persona solicitante, float porcentaje, JPanel panelTiposSolicitantes, CardLayout cardLayout) {
		if (solicitante == null) {
			mostrarDatosVacios(panelTiposSolicitantes, cardLayout);
			return;
		}

		String rutaFotoPerfil = solicitante.getUser() != null ? solicitante.getUser().getFotoPerfil() : null;
		colocarImagen(lblFotoPerfil, rutaFotoPerfil);
		lblNombre.setText(textoSeguro(solicitante.getNombre()) + " " + textoSeguro(solicitante.getApellido()));

		if (solicitante instanceof Universitario) {
			Universitario universitario = (Universitario) solicitante;
			txtTipoSolicitante.setText(solicitante.getSexo() == Sexo.FEMENINO ? "Universitaria" : "Universitario");
			lblCarr.setText(textoSeguro(universitario.getCarrera()));
			cardLayout.show(panelTiposSolicitantes, "Universitario");
		} else if (solicitante instanceof Tecnico) {
			Tecnico tecnico = (Tecnico) solicitante;
			txtTipoSolicitante.setText(solicitante.getSexo() == Sexo.FEMENINO ? "Técnica" : "Técnico");
			lblTecnico.setText(textoSeguro(tecnico.getTecnico()));
			cardLayout.show(panelTiposSolicitantes, "Tecnico");
		} else if (solicitante instanceof Obrero) {
			Obrero obrero = (Obrero) solicitante;
			txtTipoSolicitante.setText(solicitante.getSexo() == Sexo.FEMENINO ? "Obrera" : "Obrero");
			lblHabilid.setText(textoSeguro(obrero.getHabilidades()));
			cardLayout.show(panelTiposSolicitantes, "Obrero");
		} else {
			txtTipoSolicitante.setText("No especificado");
		}

		aplicarEstiloCoincidencia(porcentaje);

		if (solicitante.getFechNacim() != null) {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			lblFechaNacim.setText(solicitante.getFechNacim().format(formato));
		} else {
			lblFechaNacim.setText("No especificada");
		}

		lblTelefono.setText(textoSeguro(solicitante.getTelefono()));
		lblSolCiudad.setText(textoSeguro(solicitante.getCiudad()));
		lblSolSexo.setText(solicitante.getSexo() != null ? solicitante.getSexo().toString() : "No especificado");
		lblDispMud.setText(solicitante.isDispParaMudarse() ? "Sí" : "No");
		lblLicencia.setText(solicitante.isLicenciaConducir() ? "Sí" : "No");

		if (solicitante.getYearsExp() > 1) {
			lblExp.setText(solicitante.getYearsExp() + " años");
		} else if (solicitante.getYearsExp() == 1) {
			lblExp.setText("1 año");
		} else {
			lblExp.setText("No tiene experiencia");
		}

		if (solicitante.getUser() != null) {
			lblEmail.setText(textoSeguro(solicitante.getUser().getCorreo()));
			lbluser.setText(textoSeguro(solicitante.getUser().getUsername()));
		} else {
			lblEmail.setText("No disponible");
			lbluser.setText("No disponible");
		}
	}

	private void mostrarDatosVacios(JPanel panelTiposSolicitantes, CardLayout cardLayout) {
		colocarImagen(lblFotoPerfil, "/img/User Icon.png");
		lblNombre.setText("Sin candidato");
		txtTipoSolicitante.setText("No especificado");
		lblFechaNacim.setText("No especificada");
		lblTelefono.setText("No disponible");
		lblSolCiudad.setText("No disponible");
		lblSolSexo.setText("No especificado");
		lblDispMud.setText("No");
		lblLicencia.setText("No");
		lblExp.setText("No disponible");
		lblEmail.setText("No disponible");
		lbluser.setText("No disponible");
		lblCarr.setText("");
		lblTecnico.setText("");
		lblHabilid.setText("");
		aplicarEstiloCoincidencia(0f);
		cardLayout.show(panelTiposSolicitantes, "Universitario");
		btnRechazar.setEnabled(false);
		btnContratar.setEnabled(false);
	}

	private void aplicarEstiloCoincidencia(float porcentaje) {
		lblPorcent.setText(String.format("%.1f%%", porcentaje));

		if (porcentaje >= 75) {
			lblTectCoincidencia.setForeground(new Color(0, 102, 0));
			lblPorcent.setForeground(new Color(0, 102, 0));
			panelCoincidencia.setBackground(new Color(153, 204, 153));
		} else if (porcentaje >= 50) {
			lblTectCoincidencia.setForeground(new Color(184, 134, 11));
			lblPorcent.setForeground(new Color(184, 134, 11));
			panelCoincidencia.setBackground(new Color(238, 232, 170));
		} else if (porcentaje >= 25) {
			lblTectCoincidencia.setForeground(new Color(160, 82, 45));
			lblPorcent.setForeground(new Color(160, 82, 45));
			panelCoincidencia.setBackground(new Color(233, 150, 122));
		} else {
			lblTectCoincidencia.setForeground(new Color(153, 0, 0));
			lblPorcent.setForeground(new Color(153, 0, 0));
			panelCoincidencia.setBackground(new Color(255, 153, 153));
		}
	}

	private void rechazarConHilo(Oferta oferta, SolicitudEmpleo solicitud) {
		if (oferta == null || solicitud == null || solicitud.getCandidato() == null) {
			JOptionPane.showMessageDialog(VerPostulante.this, "No se pudo identificar la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int seleccion = JOptionPane.showConfirmDialog(VerPostulante.this, "¿Está seguro de rechazar la solicitud?", "Advertencia", JOptionPane.YES_NO_OPTION);

		if (seleccion != JOptionPane.YES_OPTION) {
			return;
		}

		ejecutarDecisionConHilo(oferta, solicitud, EstadoDecision.RECHAZADO);
	}

	private void contratarConHilo(Oferta oferta, SolicitudEmpleo solicitud) {
		if (oferta == null || solicitud == null || solicitud.getCandidato() == null) {
			JOptionPane.showMessageDialog(VerPostulante.this, "No se pudo identificar la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (oferta.getCantPuestos() <= 0) {
			JOptionPane.showMessageDialog(VerPostulante.this, "La oferta ya no tiene puestos disponibles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int seleccion = JOptionPane.showConfirmDialog(VerPostulante.this, "¿Está seguro de contratar al candidato?", "Confirmación", JOptionPane.YES_NO_OPTION);

		if (seleccion != JOptionPane.YES_OPTION) {
			return;
		}

		ejecutarDecisionConHilo(oferta, solicitud, EstadoDecision.CONTRATADO);
	}

	private void ejecutarDecisionConHilo(Oferta oferta, SolicitudEmpleo solicitud, EstadoDecision decision) {
	    btnRechazar.setEnabled(false);
	    btnContratar.setEnabled(false);
	    btnRechazar.setText("Procesando...");
	    btnContratar.setText("Procesando...");

	    SwingWorker<Void, Void> hilo = new SwingWorker<Void, Void>() {
	        @Override
	        protected Void doInBackground() throws Exception {
	            DatosDecidirCandidato datos = new DatosDecidirCandidato(
	                    oferta.getId(), solicitud.getCandidato().getId(), decision);

	            Peticion peticion = new Peticion(Peticion.Tipo.DECIDIR_CANDIDATO, datos);
	            Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

	            if (!respuesta.isExito()) {
	                throw new IllegalArgumentException(respuesta.getDatos().toString());
	            }

	            return null;
	        }

	        @Override
	        protected void done() {
	            try {
	                get();
	                String mensaje = decision == EstadoDecision.CONTRATADO? "El candidato fue contratado correctamente.": "La solicitud fue rechazada correctamente.";
	                JOptionPane.showMessageDialog(VerPostulante.this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
	                dispose();
	            } catch (Exception e) {
	                Throwable causa = e.getCause();
	                String mensaje = causa != null ? causa.getMessage() : e.getMessage();
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(VerPostulante.this, mensaje != null ? mensaje : "No se pudo guardar la decisión.", "Error", JOptionPane.ERROR_MESSAGE);
	                btnRechazar.setEnabled(true);
	                btnContratar.setEnabled(true);
	                btnRechazar.setText("Rechazar");
	                btnContratar.setText("Contratar");
	            }
	        }
	    };

	    hilo.execute();
	}

	private String textoSeguro(String texto) {
		return texto == null || texto.trim().isEmpty() ? "No disponible" : texto.trim();
	}

	private void colocarImagen(JLabel label, String ruta) {
		if (label == null) {
			return;
		}

		ImageIcon icono = cargarIcono(ruta);

		if (icono == null) {
			label.setIcon(null);
			label.setText("Sin foto");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			return;
		}

		int anchoLabel = label.getWidth();
		int altoLabel = label.getHeight();
		int anchoImagen = icono.getIconWidth();
		int altoImagen = icono.getIconHeight();

		if (anchoLabel <= 0 || altoLabel <= 0 || anchoImagen <= 0 || altoImagen <= 0) {
			return;
		}

		double escalaAncho = (double) anchoLabel / anchoImagen;
		double escalaAlto = (double) altoLabel / altoImagen;
		double escala = Math.max(escalaAncho, escalaAlto);

		int nuevoAncho = (int) (anchoImagen * escala);
		int nuevoAlto = (int) (altoImagen * escala);

		Image imagenEscalada = icono.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);

		label.setIcon(new ImageIcon(imagenEscalada));
		label.setText("");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
	}

	private ImageIcon cargarIcono(String ruta) {
		String rutaFinal = ruta;

		if (rutaFinal == null || rutaFinal.trim().isEmpty()) {
			rutaFinal = "/img/User Icon.png";
		}

		java.net.URL recurso = getClass().getResource(rutaFinal);

		if (recurso != null) {
			return new ImageIcon(recurso);
		}

		java.io.File archivo = new java.io.File(rutaFinal);

		if (archivo.exists() && archivo.isFile()) {
			ImageIcon iconoArchivo = new ImageIcon(archivo.getAbsolutePath());

			if (iconoArchivo.getIconWidth() > 0) {
				return iconoArchivo;
			}
		}

		java.net.URL recursoPredeterminado = getClass().getResource("/img/User Icon.png");

		if (recursoPredeterminado != null) {
			return new ImageIcon(recursoPredeterminado);
		}

		System.err.println("No se encontró la imagen: " + rutaFinal);
		return null;
	}

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		if (boton == null) {
			return;
		}

		ImageIcon icono = cargarIcono(ruta);

		if (icono == null) {
			return;
		}

		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagenEscalada));
	}
}