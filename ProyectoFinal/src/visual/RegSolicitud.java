package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logico.AreaLaboral;
import logico.BolsaEmpleo;
import logico.EstadoSolicitud;
import logico.Jornada;
import logico.Modalidad;
import logico.SolicitudEmpleo;

import javax.swing.JSeparator;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RegSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim = getToolkit().getScreenSize();
	private JLabel lblFondo;
	private JLabel lblLogo;
	private JLabel lblTitulo;
	private JLabel lblNewLabel;
	private TextFieldRedond txtPuesto;
	private JLabel lblAreaLaboral;
	private ComboBoxRedond cbxAreaLaboral;
	private JLabel lblSueldoEsperado;
	private JSpinner spnSueldo;
	private JLabel lblModalidad;
	private JPanel panel1;
	private JPanel panel_1;
	private JLabel lblLupaIcon;
	private JLabel lblBrainIcon;
	private JLabel lblNewLabel_2;
	private JLabel lblCoincidenciaInteligente;
	private JLabel lblNewLabel_3;
	private JLabel lblLasEmpresasRegistradas;
	private JLabel lblTeConectamosCon;
	private JLabel lblQueSeAjustan;
	private JButton btnCrear;
	private SpinnerRedond spnSueldo_1;
	private ComboBoxRedond cbxJornada;
	private ComboBoxRedond cbxModalidad;
	private BotonRedond btnCancelar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegSolicitud dialog = new RegSolicitud(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegSolicitud(SolicitudEmpleo solicitud) {
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Registrar Solicitud");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegSolicitud.class.getResource("/img/AppIconoFull.png")));
		setBounds(0, 0, dim.width, dim.height - 55);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 950, 1005);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblLogo = new JLabel("New label");
		lblLogo.setIcon(new ImageIcon(RegSolicitud.class.getResource("/img/HireLink_logo_full.png")));
		lblLogo.setBounds(58, 97, 346, 107);
		panel.add(lblLogo);
		colocarImagen(lblLogo, "/img/HireLink_logo_full.png");

		lblTitulo = new JLabel("Crea tu Solicitud de Empleo");
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 40));
		lblTitulo.setBounds(58, 216, 477, 40);
		panel.add(lblTitulo);

		lblNewLabel = new JLabel("Define tus preferencias laborales.");
		lblNewLabel.setForeground(new Color(102, 102, 102));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel.setBounds(58, 265, 355, 32);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.activeCaptionBorder);
		separator.setBounds(57, 311, 500, 2);
		panel.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Puesto deseado");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 22));
		lblNewLabel_1.setBounds(58, 533, 163, 20);
		panel.add(lblNewLabel_1);

		txtPuesto = new TextFieldRedond(25);
		txtPuesto.setForeground(new Color(0, 0, 51));
		txtPuesto.setBackground(SystemColor.controlHighlight);
		txtPuesto.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtPuesto.setBounds(58, 573, 377, 30);
		panel.add(txtPuesto);
		txtPuesto.setColumns(10);

		lblAreaLaboral = new JLabel("\u00C1rea laboral");
		lblAreaLaboral.setForeground(new Color(0, 0, 51));
		lblAreaLaboral.setFont(new Font("Calibri", Font.BOLD, 22));
		lblAreaLaboral.setBounds(58, 659, 163, 20);
		panel.add(lblAreaLaboral);

		cbxAreaLaboral = new ComboBoxRedond<AreaLaboral>(25);
		cbxAreaLaboral.setForeground(new Color(0, 0, 51));
		cbxAreaLaboral.setBackground(SystemColor.controlHighlight);
		cbxAreaLaboral.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbxAreaLaboral.setBounds(58, 706, 230, 30);
		panel.add(cbxAreaLaboral);
		cbxAreaLaboral.setModel(new DefaultComboBoxModel<>(AreaLaboral.values()));
		cbxAreaLaboral.setSelectedIndex(-1);

		lblSueldoEsperado = new JLabel("Sueldo esperado");
		lblSueldoEsperado.setForeground(new Color(0, 0, 51));
		lblSueldoEsperado.setFont(new Font("Calibri", Font.BOLD, 22));
		lblSueldoEsperado.setBounds(58, 786, 163, 20);
		panel.add(lblSueldoEsperado);

		spnSueldo_1 = new SpinnerRedond(25);
		spnSueldo_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(500)));
		spnSueldo_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		spnSueldo_1.setForeground(new Color(0, 0, 51));
		spnSueldo_1.setBackground(SystemColor.controlHighlight);
		spnSueldo_1.setBounds(58, 831, 230, 30);
		panel.add(spnSueldo_1);
		spnSueldo_1.aplicarColorSpinner(spnSueldo_1,SystemColor.controlHighlight);

		lblModalidad = new JLabel("Modalidad");
		lblModalidad.setForeground(new Color(0, 0, 51));
		lblModalidad.setFont(new Font("Calibri", Font.BOLD, 22));
		lblModalidad.setBounds(449, 659, 163, 20);
		panel.add(lblModalidad);

		cbxModalidad = new ComboBoxRedond<Modalidad>(25);
		cbxModalidad.setForeground(new Color(0, 0, 51));
		cbxModalidad.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbxModalidad.setBackground(SystemColor.controlHighlight);
		cbxModalidad.setBounds(445, 706, 238, 30);
		panel.add(cbxModalidad);
		cbxModalidad.setModel(new DefaultComboBoxModel<>(Modalidad.values()));
		cbxModalidad.setSelectedIndex(-1);

		JLabel lblJornada = new JLabel("Jornada");
		lblJornada.setForeground(new Color(0, 0, 51));
		lblJornada.setFont(new Font("Calibri", Font.BOLD, 22));
		lblJornada.setBounds(449, 786, 163, 20);
		panel.add(lblJornada);

		cbxJornada = new ComboBoxRedond<Jornada>(25);
		cbxJornada.setForeground(new Color(0, 0, 51));
		cbxJornada.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbxJornada.setBackground(SystemColor.controlHighlight);
		cbxJornada.setBounds(449, 831, 230, 30);
		panel.add(cbxJornada);
		cbxJornada.setModel(new DefaultComboBoxModel<>(Jornada.values()));
		cbxJornada.setSelectedIndex(-1);

		panel1 = new PanelRedond(20);
		panel1.setBounds(58, 349, 355, 110);
		panel.add(panel1);
		panel1.setBackground(Color.decode("#e0f7e3"));
		panel1.setLayout(null);

		panel_1 = new PanelRedond(120);
		panel_1.setBounds(24, 15, 80, 80);
		panel1.add(panel_1);
		panel_1.setBackground(Color.decode("#bfe7b6"));
		panel_1.setLayout(null);

		lblLupaIcon = new JLabel("New label");
		lblLupaIcon.setIcon(new ImageIcon(RegSolicitud.class.getResource("/img/search.png")));
		lblLupaIcon.setBounds(15, 13, 50, 50);
		panel_1.add(lblLupaIcon);
		colocarImagen(lblLupaIcon,"/img/search.png");

		lblNewLabel_2 = new JLabel("B\u00FAsqueda Activa");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_2.setBounds(120, 21, 159, 20);
		panel1.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.decode("#25aa30"));

		lblNewLabel_3 = new JLabel("Tu solicitud ser\u00E1 visible para ");
		lblNewLabel_3.setForeground(SystemColor.textInactiveText);
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(120, 48, 195, 20);
		panel1.add(lblNewLabel_3);

		lblLasEmpresasRegistradas = new JLabel("las empresas registradas.");
		lblLasEmpresasRegistradas.setForeground(SystemColor.textInactiveText);
		lblLasEmpresasRegistradas.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblLasEmpresasRegistradas.setBounds(120, 67, 195, 20);
		panel1.add(lblLasEmpresasRegistradas);

		PanelRedond panelRedond = new PanelRedond(20);
		panelRedond.setBounds(492, 349, 355, 110);
		panel.add(panelRedond);
		panelRedond.setBackground(Color.decode("#e4ebff"));
		panelRedond.setLayout(null);

		PanelRedond panelRedond_1 = new PanelRedond(120);
		panelRedond_1.setBounds(24, 15, 80, 80);
		panelRedond.add(panelRedond_1);
		panelRedond_1.setBackground(Color.decode("#cadaff"));
		panelRedond_1.setLayout(null);

		lblBrainIcon = new JLabel("New label");
		lblBrainIcon.setIcon(new ImageIcon(RegSolicitud.class.getResource("/img/brain.png")));
		lblBrainIcon.setBounds(14, 15, 50, 50);
		panelRedond_1.add(lblBrainIcon);
		colocarImagen(lblBrainIcon,"/img/brain.png");

		lblCoincidenciaInteligente = new JLabel("Coincidencia Inteligente");
		lblCoincidenciaInteligente.setForeground(new Color(37, 170, 48));
		lblCoincidenciaInteligente.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCoincidenciaInteligente.setBounds(120, 21, 201, 20);
		panelRedond.add(lblCoincidenciaInteligente);
		lblCoincidenciaInteligente.setForeground(Color.decode("#4769ba"));

		lblTeConectamosCon = new JLabel("Te conectamos con oportunidades");
		lblTeConectamosCon.setForeground(SystemColor.textInactiveText);
		lblTeConectamosCon.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTeConectamosCon.setBounds(120, 48, 223, 20);
		panelRedond.add(lblTeConectamosCon);

		lblQueSeAjustan = new JLabel("que se ajustan a tu perfil.");
		lblQueSeAjustan.setForeground(SystemColor.textInactiveText);
		lblQueSeAjustan.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblQueSeAjustan.setBounds(120, 67, 223, 20);
		panelRedond.add(lblQueSeAjustan);

		btnCrear = new BotonRedond("Crear",35);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Validaciones.camposLlenos(txtPuesto.getText()) || cbxAreaLaboral.getSelectedIndex() == -1 || cbxJornada.getSelectedIndex() == -1 || cbxModalidad.getSelectedIndex() == -1 || (int) spnSueldo_1.getValue() == 0) {
					JOptionPane.showConfirmDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!Validaciones.soloLetras(txtPuesto.getText())) {
					JOptionPane.showConfirmDialog(null, "Puesto no debe de contener números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(solicitud == null) {
					String id = "S-"+BolsaEmpleo.generadorIdSolicitud;
					LocalDate fechaHoy = LocalDate.now();
					SolicitudEmpleo solicitud = new SolicitudEmpleo(id, EstadoSolicitud.ACTIVA, BolsaEmpleo.getInstancia().getLoginUser().getPersona(), fechaHoy, (AreaLaboral) cbxAreaLaboral.getSelectedItem(), ((float) (Integer) spnSueldo_1.getValue()), (Modalidad) cbxModalidad.getSelectedItem(), txtPuesto.getText(), (Jornada) cbxJornada.getSelectedItem());
					BolsaEmpleo.getInstancia().regSolicitud(solicitud,BolsaEmpleo.getInstancia().getLoginUser().getPersona());
					
					VerMiSolicitudLaboral ver = new VerMiSolicitudLaboral();
					ver.setVisible(true);
					dispose();
				}
				else {
					solicitud.setPuesto(txtPuesto.getText());
					solicitud.setAreaLaboral((AreaLaboral) cbxAreaLaboral.getSelectedItem());
					solicitud.setJornada((Jornada) cbxJornada.getSelectedItem());
					solicitud.setModalidad( (Modalidad) cbxModalidad.getSelectedItem());
					solicitud.setSueldoEsperado((float)((Integer) spnSueldo_1.getValue()));
					BolsaEmpleo.getInstancia().modSolicitud(solicitud);
					
					VerMiSolicitudLaboral ver = new VerMiSolicitudLaboral();
					ver.setVisible(true);
					dispose();
				}
				
			}
		});
		btnCrear.setBackground(new Color(255, 153, 0));
		btnCrear.setForeground(new Color(0, 0, 51));
		btnCrear.setFont(new Font("Calibri", Font.BOLD, 22));
		btnCrear.setBounds(677, 903, 170, 46);
		panel.add(btnCrear);
		
		BotonRedond btnCancelar = new BotonRedond("Cancelar", 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(solicitud == null) {
					HomeCandidato home = new HomeCandidato();
					dispose();

					home.setVisible(true);
					home.toFront();
		            home.requestFocus();
					dispose();
				}
				else {
					VerMiSolicitudLaboral ver = new VerMiSolicitudLaboral();
					dispose();
					ver.setVisible(true);
					ver.toFront();
					ver.requestFocus();
				}
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 51));
		btnCancelar.setFont(new Font("Calibri", Font.BOLD, 22));
		btnCancelar.setBackground(Color.decode("#ffc5c5"));
		btnCancelar.setColorHover(Color.decode("#feaaaa"));
		btnCancelar.setBounds(58, 903, 170, 46);
		panel.add(btnCancelar);
		

		lblFondo = new JLabel("New label");
		lblFondo.setIcon(new ImageIcon(RegSolicitud.class.getResource("/img/Fondo-Registrar-Solicitud.png")));
		lblFondo.setBounds(0, 0, 1914, 1005);
		contentPanel.add(lblFondo);
		colocarImagen(lblFondo,"/img/Fondo-Registrar-Solicitud.png");
		
		loadSolicitud(solicitud);
		
		if(solicitud != null) {
			lblTitulo.setText("Modifica tu Solicitud Empleo.");
			btnCrear.setText("Modificar");
		}

	}

	private void loadSolicitud(SolicitudEmpleo solicitud) {
		if(solicitud == null) {
			return;
		}
		txtPuesto.setText(solicitud.getPuesto());
		cbxAreaLaboral.setSelectedItem(solicitud.getAreaLaboral());
		cbxJornada.setSelectedItem(solicitud.getJornada());
		cbxModalidad.setSelectedItem(solicitud.getModalidad());
		spnSueldo_1.setValue((int) solicitud.getSueldoEsperado());
	}

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
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