package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.Obrero;
import logico.Oferta;
import logico.Persona;
import logico.ResultMatch;
import logico.SolicitudEmpleo;
import logico.Tecnico;
import logico.Universitario;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerTodosPostulantesOferta extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JButton btnAtras;
	private JComboBox cbxFiltEstado;
	private ComboBoxRedond cbxFiltPerfil;
	private JButton btnActualizar;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTable table;
	private ComboBoxRedond cbxFiltrFecha;
	private ComboBoxRedond cbxFiltCoincidencia;
	private Oferta oferta;
	private ArrayList<Persona> postulantesMostrados =  new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerTodosPostulantesOferta frame = new VerTodosPostulantesOferta(null);
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
	public VerTodosPostulantesOferta(Oferta oferta) {
		this.oferta = oferta;
		setTitle("Ver Todos los Postulantes");
		setResizable(false);
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 600);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-55);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 51));
		contentPane.setForeground(new Color(0, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		PanelRedond panel = new PanelRedond(30);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(42, 86, 1830, 917);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(VerTodosPostulantesOferta.class.getResource("/img/arrow-small-right.png")));
		lblNewLabel.setBounds(78, 106, 56, 16);
		panel.add(lblNewLabel);

		PanelRedond panel_1 = new PanelRedond(30);
		panel_1.setBounds(38, 28, 1739, 99);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setForeground(SystemColor.windowText);
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(150, 23, 66, 20);
		panel_1.add(lblNewLabel_2);

		cbxFiltEstado = new ComboBoxRedond(30);
		cbxFiltEstado.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxFiltEstado.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Pendientes", "Rechazados", "Aceptados"}));
		cbxFiltEstado.setBounds(150, 53, 141, 28);
		panel_1.add(cbxFiltEstado);

		JLabel lblPerfilLaboral = new JLabel("Perfil Laboral");
		lblPerfilLaboral.setForeground(Color.BLACK);
		lblPerfilLaboral.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPerfilLaboral.setBounds(410, 23, 116, 20);
		panel_1.add(lblPerfilLaboral);

		cbxFiltPerfil = new ComboBoxRedond(30);
		cbxFiltPerfil.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Universitario", "T\u00E9cnico", "Obrero"}));
		cbxFiltPerfil.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxFiltPerfil.setBounds(411, 53, 161, 28);
		panel_1.add(cbxFiltPerfil);

		JLabel lblOrdenarPor = new JLabel("Ordenar Por");
		lblOrdenarPor.setForeground(Color.BLACK);
		lblOrdenarPor.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblOrdenarPor.setBounds(1011, 23, 116, 20);
		panel_1.add(lblOrdenarPor);

		cbxFiltCoincidencia = new ComboBoxRedond(30);
		cbxFiltCoincidencia.setModel(new DefaultComboBoxModel(new String[] {"Mayor Coincidencia", "Menor Coincidencia"}));
		cbxFiltCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxFiltCoincidencia.setBounds(1004, 53, 199, 28);
		panel_1.add(cbxFiltCoincidencia);

		JLabel lblFechaDeAplicacin = new JLabel("Fecha de Aplicaci\u00F3n");
		lblFechaDeAplicacin.setForeground(Color.BLACK);
		lblFechaDeAplicacin.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblFechaDeAplicacin.setBounds(697, 23, 184, 20);
		panel_1.add(lblFechaDeAplicacin);

		cbxFiltrFecha = new ComboBoxRedond(30);
		cbxFiltrFecha.setModel(new DefaultComboBoxModel(new String[] {"Todas las Fechas", "Hoy", "Ultimos 7 D\u00EDas"}));
		cbxFiltrFecha.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxFiltrFecha.setBounds(697, 53, 199, 28);
		panel_1.add(cbxFiltrFecha);

		BotonRedond btnActualizar = new BotonRedond("Actualizar",30);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estado =cbxFiltEstado.getSelectedItem().toString();
				String perfil =cbxFiltPerfil.getSelectedItem().toString();
				String fecha =cbxFiltrFecha.getSelectedItem().toString();
				String coincidencia =cbxFiltCoincidencia.getSelectedItem().toString();
				loadPostulantes(estado, perfil, fecha, coincidencia);

			}
		});
		btnActualizar.setFont(new Font("Calibri", Font.BOLD, 20));
		btnActualizar.setBounds(1385, 29, 150, 40);
		btnActualizar.setForeground(Color.decode("#6b92fc"));
		btnActualizar.setBackground(Color.decode("#e3ebfe"));
		btnActualizar.setColorHover(Color.decode("#d9e4ff"));
		panel_1.add(btnActualizar);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(37, 157, 1756, 722);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.add(scrollPane, BorderLayout.NORTH);
		String[] headers = {"#", "Nombre del Postulante", "Perfil Laboral", "Coincidencia", "Fecha de Aplicación", "Estado"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				if(index>=0) {
					if(oferta == null) {
						VerPostulante verPostulante = new VerPostulante(null,null,null);
						setVisible(true);
					}
					else {
						String IdSolicitud = BolsaEmpleo.getInstancia().idSolicitud(postulantesMostrados.get(index));
						SolicitudEmpleo solicitud = postulantesMostrados.get(index).buscarSolicitud(IdSolicitud);
						VerPostulante verPostulante = new VerPostulante(postulantesMostrados.get(index),oferta,solicitud);
						setVisible(true);
					}
				}
			}
		});
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(5); 
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(25);
		table.getColumnModel().getColumn(4).setPreferredWidth(75);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		scrollPane.setViewportView(table);

		BotonRedond btnAtras = new BotonRedond("New button",30);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerPostulantesOferta anterior = new VerPostulantesOferta(oferta);
				anterior.setVisible(true);
				dispose();
			}
		});
		btnAtras.setForeground(new Color(0, 0, 51));
		btnAtras.setBackground(new Color(0, 0, 51));
		btnAtras.setBounds(54, 9, 70, 70);
		btnAtras.setMargin(new Insets(0, 0, 0, 0));
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setFocusPainted(false);
		btnAtras.setOpaque(false);
		btnAtras.setColorHover(Color.decode("#0a0047"));
		contentPane.add(btnAtras);
		colocarIconoBoton(btnAtras,"/img/arrow-small-right.png", 70,70);

		JLabel lblNewLabel_1 = new JLabel("Todos los Postulantes");
		lblNewLabel_1.setForeground(new Color(255, 153, 0));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 40));
		lblNewLabel_1.setBounds(776, 23, 362, 37);
		contentPane.add(lblNewLabel_1);
		loadPostulantes("Todos","Todos","Todas las Fechas", "Mayor Coincidencia");
	}

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagenEscalada));
	}
	private void loadPostulantes(String estado,String perfil,String fecha,String ordenCoincidencia) {
		model.setRowCount(0);
		if (oferta == null) {
			return;
		}

		ArrayList<ResultMatch> matcheo = BolsaEmpleo.getInstancia().calcularMatch(oferta);
		ArrayList<ResultMatch> solicitudesFiltradas = new ArrayList<ResultMatch>();
		ArrayList<Persona> mostrados =  new ArrayList<>();
		LocalDate hoy = LocalDate.now();

		for (ResultMatch resultado : matcheo) {

			Persona candidato = resultado.getSolicitud().getCandidato();

			boolean cumpleEstado = estado.equalsIgnoreCase("Todos") || coincideEstado(resultado.getSolicitud(),estado );
			boolean cumplePerfil = perfil.equalsIgnoreCase("Todos") || obtenerPerfilLaboral(candidato).equalsIgnoreCase(perfil);
			boolean cumpleFecha = cumpleFiltroFecha(resultado.getSolicitud().getFechaSolicitud(),fecha,hoy);

			if (cumpleEstado && cumplePerfil && cumpleFecha) {
				solicitudesFiltradas.add(resultado);
			}
		}

		Collections.sort(solicitudesFiltradas,new Comparator<ResultMatch>() {

			public int compare(ResultMatch soli1,ResultMatch soli2) {
				if (ordenCoincidencia.equalsIgnoreCase("Menor Coincidencia")) {
					return Float.compare(soli1.getPorcentaje(),soli2.getPorcentaje());
				}
				return Float.compare(soli2.getPorcentaje(),soli1.getPorcentaje());
			}
		}
				);

		int numero = 1;

		for (ResultMatch match : solicitudesFiltradas) {

			Persona candidato = match.getSolicitud().getCandidato();

			Object[] fila =new Object[model.getColumnCount()];

			fila[0] = numero;
			fila[1] = candidato.getNombre();
			fila[2] = obtenerPerfilLaboral(candidato);
			fila[3] = match.getPorcentaje() +"%";
			fila[4] = match.getSolicitud().getFechaSolicitud();
			
			model.addRow(fila);
			mostrados.add(match.getSolicitud().getCandidato());
			numero++;
		}
		
		postulantesMostrados.clear();
		postulantesMostrados.addAll(mostrados);
		
	}

	private String obtenerPerfilLaboral(Persona candidato) {
		if (candidato instanceof Universitario) {
			return "Universitario";
		}

		if (candidato instanceof Tecnico) {
			return "Técnico";
		}

		if (candidato instanceof Obrero) {
			return "Obrero";

		}
		return null;
	}

	private boolean cumpleFiltroFecha(LocalDate fechaSolicitud, String filtro, LocalDate hoy) {
		if (filtro.equalsIgnoreCase("Todas las Fechas")) {
			return true;
		}

		if (fechaSolicitud == null) {
			return false;
		}

		if (filtro.equalsIgnoreCase("Hoy")) {
			return fechaSolicitud.equals(hoy);
		}

		if (filtro.equalsIgnoreCase("Ultimos 7 Días")|| filtro.equalsIgnoreCase("Últimos 7 Días")) {
			LocalDate fechaLimite =hoy.minusDays(7);
			return !fechaSolicitud.isBefore(fechaLimite)&& !fechaSolicitud.isAfter(hoy);
		}

		return true;
	}

	private boolean coincideEstado(SolicitudEmpleo solicitud, String estadoSel) {
		if (solicitud.getEstado() == null) {
			return false;
		}

		String estadoSolicitud =solicitud.getEstado().toString();
		if (estadoSel.equalsIgnoreCase("Pendientes")) {
			return estadoSolicitud.equalsIgnoreCase("PENDIENTE");
		}

		if (estadoSel.equalsIgnoreCase("Contratados")) {
			return estadoSolicitud.equalsIgnoreCase("CONTRATADO")
					|| estadoSolicitud.equalsIgnoreCase("ACEPTADA");
		}

		if (estadoSel.equalsIgnoreCase("Rechazados")) {
			return estadoSolicitud.equalsIgnoreCase("RECHAZADO") 
					|| estadoSolicitud.equalsIgnoreCase("RECHAZADA");
		}

		return false;
	}
}
