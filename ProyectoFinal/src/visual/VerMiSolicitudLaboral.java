package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.AreaLaboral;
import logico.BolsaEmpleo;
import logico.EstadoSolicitud;
import logico.Jornada;
import logico.Modalidad;
import logico.Persona;
import logico.SolicitudEmpleo;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerMiSolicitudLaboral extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JPanel panelFondo;
	private JPanel panelEstado;
	private JLabel lblDocumentIcon;
	private JPanel panelPuesto;
	private PanelRedond panelAreaLaboral;
	private PanelRedond panelSueldo;
	private PanelRedond panelModalidad;
	private JSeparator separator;
	private JLabel lblImagen;
	private JLabel lblNewLabel_2;
	private JPanel panelPuestoIcon;
	private JLabel lblBriefCaseIcon;
	private JLabel lblBuildIcon;
	private JLabel lblDollarIcon;
	private JLabel lblLaptopIcon;
	private JLabel lblTimeIcon;
	private JPanel circulo1;
	private JLabel lblEstado;
	private JLabel lblCalendarIcon;
	private JButton btnModificar;
	private JLabel lblPuesto;
	private JLabel lblAreaLaboral;
	private JLabel lblSueldo;
	private JLabel lblModalidad;
	private JLabel lblJornada;
	private Persona candidato = BolsaEmpleo.getInstancia().getLoginUser().getPersona();
	private JLabel lblEstadoIcon;
	private JLabel lblFechaSolicitud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerMiSolicitudLaboral frame = new VerMiSolicitudLaboral();
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
	public VerMiSolicitudLaboral() {
		Utilidades.aplicarIcono(this);
		setTitle("Mi Solicitud Laboral");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-55);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelFondo = new PanelRedond(30);
		panelFondo.setBackground(new Color(255, 255, 255));
		panelFondo.setBounds(21, 24, 1860, 935);
		contentPane.add(panelFondo);
		panelFondo.setLayout(null);

		lblNewLabel_2 = new JLabel("\u00A1Vamos por tu pr\u00F3xima ");
		lblNewLabel_2.setForeground(new Color(0, 0, 51));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 38));
		lblNewLabel_2.setBounds(1216, 661, 401, 53);
		panelFondo.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Mi Solicitud Laboral");
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 50));
		lblNewLabel.setBounds(106, 96, 440, 45);
		panelFondo.add(lblNewLabel);

		panelEstado = new PanelRedond(30);
		panelEstado.setBackground(Color.decode("#e1effe"));
		panelEstado.setBounds(106, 211, 400, 107);
		panelFondo.add(panelEstado);
		panelEstado.setLayout(null);

		circulo1 = new PanelRedond(120);
		circulo1.setBounds(29, 11, 85, 85);
		panelEstado.add(circulo1);
		circulo1.setBackground(Color.decode("#8ea2d2"));
		circulo1.setLayout(null);

		lblEstadoIcon = new JLabel("New label");
		lblEstadoIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/cross.png")));
		lblEstadoIcon.setBounds(17, 17, 50, 50);
		colocarImagen(lblEstadoIcon, "/img/check(lightBlue).png");
		circulo1.add(lblEstadoIcon);

		JLabel lblNewLabel_5 = new JLabel("Estado");
		lblNewLabel_5.setForeground(SystemColor.textInactiveText);
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(142, 28, 100, 21);
		panelEstado.add(lblNewLabel_5);

		lblEstado = new JLabel("Activa");
		lblEstado.setFont(new Font("Calibri", Font.BOLD, 30));
		lblEstado.setBounds(142, 58, 143, 26);
		panelEstado.add(lblEstado);
		lblEstado.setForeground(Color.decode("#4769ba"));

		PanelRedond panelRedond = new PanelRedond(30);
		panelRedond.setBackground(Color.decode("#fef7f0"));
		panelRedond.setBounds(555, 211, 400, 107);
		panelFondo.add(panelRedond);
		panelRedond.setLayout(null);

		PanelRedond panelRedond_10 = new PanelRedond(120);
		panelRedond_10.setBackground(Color.decode("#fde1c4"));
		panelRedond_10.setBounds(29, 11, 85, 85);
		panelRedond.add(panelRedond_10);
		panelRedond_10.setLayout(null);

		lblCalendarIcon = new JLabel("New label");
		lblCalendarIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/calendar(yellow).png")));
		lblCalendarIcon.setBounds(17, 17, 50, 50);
		panelRedond_10.add(lblCalendarIcon);
		colocarImagen(lblCalendarIcon, "/img/calendar(yellow).png");

		JLabel lblFechaDeSolicitud = new JLabel("Fecha de Solicitud");
		lblFechaDeSolicitud.setForeground(SystemColor.textInactiveText);
		lblFechaDeSolicitud.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblFechaDeSolicitud.setBounds(142, 28, 158, 21);
		panelRedond.add(lblFechaDeSolicitud);

		LocalDate Fecha = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = Fecha.format(formato);
		lblFechaSolicitud = new JLabel(fechaFormateada);

		lblFechaSolicitud.setForeground(Color.decode("#f56f07"));
		lblFechaSolicitud.setFont(new Font("Calibri", Font.BOLD, 30));
		lblFechaSolicitud.setBounds(142, 58, 160, 26);
		panelRedond.add(lblFechaSolicitud);

		JLabel lblNewLabel_1 = new JLabel("Detalles de la Solicitud");
		lblNewLabel_1.setForeground(new Color(0, 0, 51));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 28));
		lblNewLabel_1.setBounds(218, 359, 300, 25);
		panelFondo.add(lblNewLabel_1);

		lblDocumentIcon = new JLabel("New label");
		lblDocumentIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/document (1).png")));
		lblDocumentIcon.setBounds(122, 344, 55, 55);
		panelFondo.add(lblDocumentIcon);
		colocarImagen(lblDocumentIcon,"/img/document (1).png");

		PanelRedond panelPuesto = new PanelRedond(30);
		panelPuesto.setBackground(new Color(255, 255, 255));
		panelPuesto.setBounds(106, 426, 863, 70);
		panelFondo.add(panelPuesto);
		panelPuesto.setColorBorde(Color.decode("#f0f0f0"));
		panelPuesto.setGrosorBorde(2);
		panelPuesto.setLayout(null);

		PanelRedond panelRedond_1 = new PanelRedond(120);
		panelRedond_1.setBounds(46, 0, 70, 70);
		panelPuesto.add(panelRedond_1);
		panelRedond_1.setBackground(Color.decode("#e1effe"));
		panelRedond_1.setLayout(null);

		lblBriefCaseIcon = new JLabel("New label");
		lblBriefCaseIcon.setBounds(0, 10, 50, 50);
		panelRedond_1.add(lblBriefCaseIcon);
		lblBriefCaseIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/briefcase(lightBlue).png")));
		colocarImagen(lblBriefCaseIcon, "/img/briefcase(lightBlue).png");

		panelPuestoIcon = new PanelRedond(30);
		panelPuestoIcon.setBounds(0, 0, 83, 70);
		panelPuesto.add(panelPuestoIcon);
		panelPuestoIcon.setLayout(null);
		panelPuestoIcon.setBackground(Color.decode("#e1effe"));

		JLabel lblNewLabel_4 = new JLabel("Puesto Deseado");
		lblNewLabel_4.setForeground(new Color(0, 0, 51));
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel_4.setBounds(145, 27, 191, 25);
		panelPuesto.add(lblNewLabel_4);

		lblPuesto = new JLabel("New label");
		lblPuesto.setForeground(new Color(0, 0, 51));
		lblPuesto.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblPuesto.setBounds(465, 27, 350, 25);
		panelPuesto.add(lblPuesto);

		panelAreaLaboral = new PanelRedond(30);
		panelAreaLaboral.setGrosorBorde(2);
		panelAreaLaboral.setColorBorde(SystemColor.menu);
		panelAreaLaboral.setBackground(Color.WHITE);
		panelAreaLaboral.setBounds(106, 516, 863, 70);
		panelFondo.add(panelAreaLaboral);
		panelAreaLaboral.setLayout(null);

		PanelRedond panelRedond_3 = new PanelRedond(120);
		panelRedond_3.setLayout(null);
		panelRedond_3.setBackground(new Color(225, 239, 254));
		panelRedond_3.setBounds(46, 0, 70, 70);
		panelAreaLaboral.add(panelRedond_3);

		lblBuildIcon = new JLabel("New label");
		lblBuildIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/building (LightBlue).png")));
		lblBuildIcon.setBounds(0, 10, 50, 50);
		panelRedond_3.add(lblBuildIcon);
		colocarImagen(lblBuildIcon, "/img/building (LightBlue).png");

		PanelRedond panelRedond_2 = new PanelRedond(30);
		panelRedond_2.setLayout(null);
		panelRedond_2.setBackground(new Color(225, 239, 254));
		panelRedond_2.setBounds(0, 0, 83, 70);
		panelAreaLaboral.add(panelRedond_2);

		JLabel lbl2 = new JLabel("\u00C1rea Laboral");
		lbl2.setForeground(new Color(0, 0, 51));
		lbl2.setFont(new Font("Calibri", Font.BOLD, 25));
		lbl2.setBounds(145, 23, 143, 27);
		panelAreaLaboral.add(lbl2);

		lblAreaLaboral = new JLabel("New label");
		lblAreaLaboral.setForeground(new Color(0, 0, 51));
		lblAreaLaboral.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblAreaLaboral.setBounds(465, 23, 350, 25);
		panelAreaLaboral.add(lblAreaLaboral);

		panelSueldo = new PanelRedond(30);
		panelSueldo.setGrosorBorde(2);
		panelSueldo.setColorBorde(SystemColor.menu);
		panelSueldo.setBackground(Color.WHITE);
		panelSueldo.setBounds(106, 599, 863, 70);
		panelFondo.add(panelSueldo);
		panelSueldo.setLayout(null);

		PanelRedond panelRedond_5 = new PanelRedond(120);
		panelRedond_5.setLayout(null);
		panelRedond_5.setBackground(new Color(225, 239, 254));
		panelRedond_5.setBounds(46, 0, 70, 70);
		panelSueldo.add(panelRedond_5);

		lblDollarIcon = new JLabel("New label");
		lblDollarIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/dollar.png")));
		lblDollarIcon.setBounds(0, 10, 50, 50);
		panelRedond_5.add(lblDollarIcon);
		colocarImagen(lblDollarIcon, "/img/dollar.png");

		PanelRedond panelRedond_4 = new PanelRedond(30);
		panelRedond_4.setLayout(null);
		panelRedond_4.setBackground(new Color(225, 239, 254));
		panelRedond_4.setBounds(0, 0, 83, 70);
		panelSueldo.add(panelRedond_4);

		JLabel lbl3 = new JLabel("Sueldo Deseado");
		lbl3.setForeground(new Color(0, 0, 51));
		lbl3.setFont(new Font("Calibri", Font.BOLD, 25));
		lbl3.setBounds(145, 23, 185, 25);
		panelSueldo.add(lbl3);

		lblSueldo = new JLabel("New label");
		lblSueldo.setForeground(new Color(0, 0, 51));
		lblSueldo.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblSueldo.setBounds(465, 23, 350, 25);
		panelSueldo.add(lblSueldo);

		panelModalidad = new PanelRedond(30);
		panelModalidad.setGrosorBorde(2);
		panelModalidad.setColorBorde(SystemColor.menu);
		panelModalidad.setBackground(Color.WHITE);
		panelModalidad.setBounds(106, 682, 863, 70);
		panelFondo.add(panelModalidad);
		panelModalidad.setLayout(null);

		PanelRedond panelRedond_7 = new PanelRedond(120);
		panelRedond_7.setLayout(null);
		panelRedond_7.setBackground(new Color(225, 239, 254));
		panelRedond_7.setBounds(46, 0, 70, 70);
		panelModalidad.add(panelRedond_7);

		lblLaptopIcon = new JLabel("New label");
		lblLaptopIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/laptop.png")));
		lblLaptopIcon.setBounds(0, 10, 50, 50);
		panelRedond_7.add(lblLaptopIcon);
		colocarImagen(lblLaptopIcon, "/img/laptop.png");

		PanelRedond panelRedond_6 = new PanelRedond(30);
		panelRedond_6.setLayout(null);
		panelRedond_6.setBackground(new Color(225, 239, 254));
		panelRedond_6.setBounds(0, 0, 83, 70);
		panelModalidad.add(panelRedond_6);

		JLabel lbl4 = new JLabel("Modalidad");
		lbl4.setForeground(new Color(0, 0, 51));
		lbl4.setFont(new Font("Calibri", Font.BOLD, 25));
		lbl4.setBounds(145, 26, 143, 25);
		panelModalidad.add(lbl4);

		lblModalidad = new JLabel("New label");
		lblModalidad.setForeground(new Color(0, 0, 51));
		lblModalidad.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblModalidad.setBounds(465, 26, 350, 25);
		panelModalidad.add(lblModalidad);

		PanelRedond panelJornada = new PanelRedond(30);
		panelJornada.setGrosorBorde(2);
		panelJornada.setColorBorde(SystemColor.menu);
		panelJornada.setBackground(Color.WHITE);
		panelJornada.setBounds(106, 765, 863, 70);
		panelFondo.add(panelJornada);
		panelJornada.setLayout(null);

		PanelRedond panelRedond_9 = new PanelRedond(120);
		panelRedond_9.setLayout(null);
		panelRedond_9.setBackground(new Color(225, 239, 254));
		panelRedond_9.setBounds(46, 0, 70, 70);
		panelJornada.add(panelRedond_9);

		lblTimeIcon = new JLabel("New label");
		lblTimeIcon.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/clock.png")));
		lblTimeIcon.setBounds(0, 10, 50, 50);
		panelRedond_9.add(lblTimeIcon);
		colocarImagen(lblTimeIcon, "/img/clock.png");

		PanelRedond panelRedond_8 = new PanelRedond(30);
		panelRedond_8.setLayout(null);
		panelRedond_8.setBackground(new Color(225, 239, 254));
		panelRedond_8.setBounds(0, 0, 83, 70);
		panelJornada.add(panelRedond_8);

		JLabel lbl5 = new JLabel("Jornada");
		lbl5.setForeground(new Color(0, 0, 51));
		lbl5.setFont(new Font("Calibri", Font.BOLD, 25));
		lbl5.setBounds(145, 26, 143, 25);
		panelJornada.add(lbl5);

		lblJornada = new JLabel("New label");
		lblJornada.setForeground(new Color(0, 0, 51));
		lblJornada.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblJornada.setBounds(465, 26, 350, 25);
		panelJornada.add(lblJornada);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(SystemColor.scrollbar);
		separator.setBounds(1050, 379, 11, 477);
		panelFondo.add(separator);

		lblImagen = new JLabel("New label");
		lblImagen.setIcon(new ImageIcon(VerMiSolicitudLaboral.class.getResource("/img/ImagenVerMiSolicitud.png")));
		lblImagen.setBounds(1134, 139, 564, 539);
		panelFondo.add(lblImagen);
		colocarImagen(lblImagen, "/img/ImagenVerMiSolicitud.png");

		JLabel lblGranOportunidad = new JLabel("gran oportunidad!");
		lblGranOportunidad.setForeground(new Color(0, 0, 51));
		lblGranOportunidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblGranOportunidad.setFont(new Font("Calibri", Font.BOLD, 38));
		lblGranOportunidad.setBounds(1216, 695, 401, 53);
		panelFondo.add(lblGranOportunidad);

		JLabel lblNewLabel_3 = new JLabel("Seguimos buscando las mejores");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(SystemColor.windowBorder);
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(1204, 761, 425, 26);
		panelFondo.add(lblNewLabel_3);

		JLabel lblOpcionesParaTi = new JLabel("opciones para ti.");
		lblOpcionesParaTi.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcionesParaTi.setForeground(SystemColor.windowBorder);
		lblOpcionesParaTi.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblOpcionesParaTi.setBounds(1204, 785, 425, 26);
		panelFondo.add(lblOpcionesParaTi);

		BotonRedond btnModificar = new BotonRedond("Modificar",30);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(candidato != null) {
					RegSolicitud modificar = new RegSolicitud(candidato.getSolicitud());
					modificar.setVisible(true);
					dispose();
				}
				else {
					RegSolicitud modificar = new RegSolicitud(null);
					modificar.setVisible(true);
					dispose();
				}
				
			}
		});
		btnModificar.setFont(new Font("Calibri", Font.BOLD, 22));
		btnModificar.setBounds(1648, 855, 163, 45);
		btnModificar.setBackground(Color.decode("#ffebd7"));
		btnModificar.setForeground(new Color(0, 0, 51));
		btnModificar.setColorHover(Color.decode("#ffdcb7"));
		panelFondo.add(btnModificar);
		
		BotonRedond btnCancelar = new BotonRedond("Cancelar", 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeCandidato home = new HomeCandidato();
				home.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 51));
		btnCancelar.setFont(new Font("Calibri", Font.BOLD, 22));
		btnCancelar.setBackground(Color.decode("#ffc5c5"));
		btnCancelar.setColorHover(Color.decode("#feaaaa"));
		btnCancelar.setBounds(1407, 855, 170, 46);
		panelFondo.add(btnCancelar);

		JLabel lblDefineLoQue = new JLabel("Define lo que buscas y conecta con oportunidades ideales.");
		lblDefineLoQue.setForeground(SystemColor.textInactiveText);
		lblDefineLoQue.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblDefineLoQue.setBounds(106, 155, 611, 25);
		panelFondo.add(lblDefineLoQue);

		if(candidato!= null) {
			SolicitudEmpleo solicitud = candidato.getSolicitud();
			if(solicitud != null) {

				LocalDate fecha = solicitud.getFechaSolicitud();
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String FechaFormateada = Fecha.format(formato); 

				lblPuesto.setText(solicitud.getPuesto());
				lblAreaLaboral.setText(solicitud.getAreaLaboral().toString().toLowerCase());
				lblSueldo.setText("$ " + solicitud.getSueldoEsperado());
				lblModalidad.setText(solicitud.getModalidad().toString().toLowerCase());
				lblJornada.setText(solicitud.getJornada().toString().toLowerCase());
				lblFechaSolicitud.setText(FechaFormateada);
				if(solicitud.getEstado() == EstadoSolicitud.ACTIVA) {
					lblEstado.setText("Activa"); 
					colocarImagen(lblEstadoIcon, "/img/check(lightBlue).png");
				}
				else {
					lblEstado.setText("Inactiva"); 
					colocarImagen(lblEstadoIcon, "/img/cross.png");
				}
			}

		}
		else {
			LocalDate fecha = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String FechaFormateada = Fecha.format(formato); 

			lblPuesto.setText("Desarroladora de Software");
			lblAreaLaboral.setText(AreaLaboral.TECNOLOGIA.toString().toLowerCase());
			lblSueldo.setText("$ " + "80,000");
			lblModalidad.setText(Modalidad.PRESENCIAL.toString().toLowerCase());
			lblJornada.setText(Jornada.MATUTINA.toString().toLowerCase());
			lblFechaSolicitud.setText(FechaFormateada);
			lblEstado.setText("Activa"); 
			colocarImagen(lblEstadoIcon, "/img/check(lightBlue).png");
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
