package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import logico.BolsaEmpleo;
import logico.EstadoOferta;
import logico.EstadoSolicitud;
import logico.Oferta;
import logico.Persona;
import logico.SolicitudEmpleo;
import javax.swing.JButton;

public class HomeAdministrador extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAdministrador frame = new HomeAdministrador();
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
	public HomeAdministrador() {
		setTitle("Inicio");
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		layeredPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		layeredPane.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(245, 245, 245));
		panel.setLayout(null);
		
		

		int margen = 40;
		int anchoContenido = dim.width - (margen * 2);

		{
			PanelRedond panelMenu = new PanelRedond(25);
			panelMenu.setBackground(new Color(0, 0, 51));
			panelMenu.setBounds(40, 20, 1840, 103);
			panel.add(panelMenu);
			panelMenu.setLayout(null);
			

			String[] textosMenu = { "Inicio", "Publicar oferta", "Mis ofertas", "Ver perfil" };
			int[] anchosMenu = { 70, 150, 110, 90 };
			int espacioEntreItems = 40;

			int anchoTotalMenu = 0;
			for (int i = 0; i < anchosMenu.length; i++) {
				anchoTotalMenu += anchosMenu[i];
			}
			anchoTotalMenu += espacioEntreItems * (anchosMenu.length - 1);

			int xMenu = anchoContenido - anchoTotalMenu - 40;
			xMenu += anchosMenu[0] + espacioEntreItems;
			xMenu += anchosMenu[1] + espacioEntreItems;
			xMenu += anchosMenu[2] + espacioEntreItems;
			
			BotonRedond btnMenu = new BotonRedond("",25);
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					BarraAdmin menu = new BarraAdmin();
					menu.setModal(true);
					menu.setVisible(true);
				}
			});
			btnMenu.setBackground(new Color(0, 0, 51));
			btnMenu.setColorHover(new Color(0, 51, 102));
			btnMenu.setBounds(12, 30, 60, 60);
			colocarIconoBoton(btnMenu,"/img/menu-dots-vertical(White).png",25,25);
			btnMenu.setMargin(new Insets(0, 0, 0, 0));
			btnMenu.setBorderPainted(false);
			btnMenu.setContentAreaFilled(false);
			btnMenu.setFocusPainted(false);
			btnMenu.setOpaque(false);
			panelMenu.add(btnMenu);
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(0, 28, 400, 75);
			colocarImagen(lblNewLabel_1, "/img/HireLink_FondoOscuro.png");
			panelMenu.add(lblNewLabel_1);
			
		
		}

		int anchoTarjeta = (anchoContenido - 48) / 3;

		{
			PanelConSombra panelOfertasActivas = new PanelConSombra(18);
			panelOfertasActivas.setBackground(new Color(255, 255, 255));
			panelOfertasActivas.setBounds(140, 136, 298, 90);
			panel.add(panelOfertasActivas);
			panelOfertasActivas.setLayout(null);

			JLabel lblOfertasActivas = new JLabel("Ofertas Activas");
			lblOfertasActivas.setFont(new Font("Calibri", Font.BOLD, 17));
			lblOfertasActivas.setForeground(new Color(0, 0, 51));
			lblOfertasActivas.setBounds(20, 14, 202, 20);
			panelOfertasActivas.add(lblOfertasActivas);

			JLabel lblOfertasActivasNum = new JLabel(String.valueOf(contarOfertasActivas()));
			lblOfertasActivasNum.setFont(new Font("Calibri", Font.BOLD, 33));
			lblOfertasActivasNum.setForeground(new Color(0, 0, 51));
			lblOfertasActivasNum.setBounds(20, 38, 202, 36);
			panelOfertasActivas.add(lblOfertasActivasNum);
		}

		{
			PanelConSombra panelEmpresasAsociadas = new PanelConSombra(18);
			panelEmpresasAsociadas.setBackground(new Color(255, 255, 255));
			panelEmpresasAsociadas.setBounds(1016, 136, 298, 90);
			panel.add(panelEmpresasAsociadas);
			panelEmpresasAsociadas.setLayout(null);

			JLabel lblSolicitudesPendientes = new JLabel("Empresas Asociadas");
			lblSolicitudesPendientes.setFont(new Font("Calibri", Font.BOLD, 17));
			lblSolicitudesPendientes.setForeground(new Color(0, 0, 51));
			lblSolicitudesPendientes.setBounds(20, 14, 202, 20);
			panelEmpresasAsociadas.add(lblSolicitudesPendientes);
			
			JLabel label = new JLabel(String.valueOf(contarEmpresas()));
			label.setForeground(new Color(0, 0, 51));
			label.setFont(new Font("Calibri", Font.BOLD, 33));
			label.setBounds(20, 41, 202, 36);
			panelEmpresasAsociadas.add(label);
		}

		{
			PanelConSombra panelCandidatosSeleciconados = new PanelConSombra(18);
			panelCandidatosSeleciconados.setBackground(new Color(255, 255, 255));
			panelCandidatosSeleciconados.setBounds(1454, 136, 298, 90);
			panel.add(panelCandidatosSeleciconados);
			panelCandidatosSeleciconados.setLayout(null);

			JLabel lblContratados = new JLabel("Candidatos Seleccionados");
			lblContratados.setFont(new Font("Calibri", Font.BOLD, 17));
			lblContratados.setForeground(new Color(0, 0, 51));
			lblContratados.setBounds(20, 14, 202, 20);
			panelCandidatosSeleciconados.add(lblContratados);

			JLabel lblContratadosNum = new JLabel(String.valueOf(contarContratados()));
			lblContratadosNum.setFont(new Font("Calibri", Font.BOLD, 33));
			lblContratadosNum.setForeground(new Color(0, 0, 51));
			lblContratadosNum.setBounds(20, 38, 202, 36);
			panelCandidatosSeleciconados.add(lblContratadosNum);
		}

		{
			int yTabla = 220;
			int altoTabla = dim.height - yTabla - 60;

			PanelConSombra panelSolicitudesRecientes = new PanelConSombra(20);
			panelSolicitudesRecientes.setBackground(Color.WHITE);
			panelSolicitudesRecientes.setBounds(969, 574, 877, 425);
			panel.add(panelSolicitudesRecientes);
			panelSolicitudesRecientes.setLayout(null);

			JLabel lblSolicitudesRecientes = new JLabel("Solicitantes Registrados Recientemente");
			lblSolicitudesRecientes.setFont(new Font("Calibri", Font.BOLD, 20));
			lblSolicitudesRecientes.setForeground(new Color(0, 0, 51));
			lblSolicitudesRecientes.setBounds(24, 20, 400, 28);
			panelSolicitudesRecientes.add(lblSolicitudesRecientes);

			table = new JTable();
			table.setModel(crearModeloSolicitudesRecientes());
			table.setFont(new Font("Calibri", Font.PLAIN, 16));
			table.setRowHeight(38);
			table.setForeground(new Color(50, 50, 50));
			table.setSelectionBackground(new Color(240, 240, 245));
			table.setShowGrid(false);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
			table.getTableHeader().setForeground(new Color(0, 0, 51));
			table.setDefaultRenderer(Object.class, new RenderCentrado());
			table.getColumnModel().getColumn(3).setCellRenderer(new RenderEstado());
			table.getColumnModel().getColumn(0).setPreferredWidth(180);
			table.getColumnModel().getColumn(1).setPreferredWidth(260);
			table.getColumnModel().getColumn(2).setPreferredWidth(90);
			table.getColumnModel().getColumn(3).setPreferredWidth(140);

			JScrollPane scrollSolicitudes = new JScrollPane(table);
			scrollSolicitudes.setBorder(null);
			scrollSolicitudes.setBounds(24, 60, 841, 352);
			panelSolicitudesRecientes.add(scrollSolicitudes);
		}
		
		PanelConSombra panelOfertas = new PanelConSombra(20);
		panelOfertas.setLayout(null);
		panelOfertas.setBackground(Color.WHITE);
		panelOfertas.setBounds(46, 574, 877, 425);
		panel.add(panelOfertas);
		
		JLabel lblOfertasPublicadasRecientemente = new JLabel("Ofertas Publicadas Recientemente");
		lblOfertasPublicadasRecientemente.setForeground(new Color(0, 0, 51));
		lblOfertasPublicadasRecientemente.setFont(new Font("Calibri", Font.BOLD, 20));
		lblOfertasPublicadasRecientemente.setBounds(24, 20, 400, 28);
		panelOfertas.add(lblOfertasPublicadasRecientemente);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBorder(null);
		scrollPane.setBounds(24, 60, 841, 352);
		panelOfertas.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(crearModeloOfertasRecientes());
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setRowHeight(38);
		table_1.setForeground(new Color(50, 50, 50));
		table_1.setSelectionBackground(new Color(240, 240, 245));
		table_1.setShowGrid(false);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_1.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
		table_1.getTableHeader().setForeground(new Color(0, 0, 51));
		table_1.setDefaultRenderer(Object.class, new RenderCentrado());
		table_1.getColumnModel().getColumn(4).setCellRenderer(new RenderEstado());
		scrollPane.setViewportView(table_1);
		
		JPanel panelGrafico1 = new JPanel();
		panelGrafico1.setBackground(new Color(255, 255, 255));
		panelGrafico1.setBounds(86, 239, 515, 301);
		panel.add(panelGrafico1);
		panelGrafico1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Actividad en la Plataforma");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(33, 23, 255, 16);
		panelGrafico1.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(687, 239, 515, 301);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEstadoDeLas = new JLabel("Estado de las Ofertas");
		lblEstadoDeLas.setFont(new Font("Calibri", Font.BOLD, 20));
		lblEstadoDeLas.setBounds(28, 13, 255, 16);
		panel_1.add(lblEstadoDeLas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(1288, 239, 515, 301);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblAccionesPendientes = new JLabel("Acciones pendientes");
		lblAccionesPendientes.setFont(new Font("Calibri", Font.BOLD, 20));
		lblAccionesPendientes.setBounds(12, 13, 255, 16);
		panel_2.add(lblAccionesPendientes);
		
		PanelConSombra panelSolicitantesRegistrados = new PanelConSombra(18);
		panelSolicitantesRegistrados.setLayout(null);
		panelSolicitantesRegistrados.setBackground(new Color(255, 255, 255));
		panelSolicitantesRegistrados.setBounds(578, 136, 298, 90);
		panel.add(panelSolicitantesRegistrados);
		
		JLabel lblSolicitantesRegistrados = new JLabel("Solicitantes Registrados");
		lblSolicitantesRegistrados.setForeground(new Color(0, 0, 51));
		lblSolicitantesRegistrados.setFont(new Font("Calibri", Font.BOLD, 17));
		lblSolicitantesRegistrados.setBounds(20, 14, 188, 20);
		panelSolicitantesRegistrados.add(lblSolicitantesRegistrados);
		
		JLabel label_1 = new JLabel(String.valueOf(BolsaEmpleo.getInstancia().getPersonas().size()));
		label_1.setForeground(new Color(0, 0, 51));
		label_1.setFont(new Font("Calibri", Font.BOLD, 33));
		label_1.setBounds(20, 38, 188, 36);
		panelSolicitantesRegistrados.add(label_1);
		
		JLabel label = new JLabel("0");
		label.setForeground(new Color(65, 95, 170));
		label.setFont(new Font("Calibri", Font.BOLD, 30));
		label.setBounds(844, 150, 44, 36);
		panel.add(label);
	}

	private int contarOfertasActivas() {
		int contador = 0;
		for (Oferta oferta : BolsaEmpleo.getInstancia().getOfertas()) {
			if (oferta.getEstado() == EstadoOferta.PENDIENTE) {
				contador++;
			}
		}
		return contador;
	}

	private int contarSolicitudes() {
		return BolsaEmpleo.getInstancia().getSolicitudes().size();
	}
	
	private int contarEmpresas() {
		return BolsaEmpleo.getInstancia().getEmpresas().size();
	}

	private int contarContratados() {
		int contador = 0;
		for (SolicitudEmpleo solicitud : BolsaEmpleo.getInstancia().getSolicitudes()) {
			if (solicitud.getEstado() == EstadoSolicitud.ACEPTADA) {
				contador++;
			}
		}
		return contador;
	}

	private int contarCandidatosCompatibles(Oferta oferta) {
		int contador = 0;
		for (SolicitudEmpleo solicitud : BolsaEmpleo.getInstancia().getSolicitudes()) {
			if (BolsaEmpleo.getInstancia().calcCoincidencia(oferta, solicitud) >= 60) {
				contador++;
			}
		}
		return contador;
	}

	private ArrayList<Persona> obtenerSolicitantesRecientes() {
	    ArrayList<Persona> todas = new ArrayList<Persona>(BolsaEmpleo.getInstancia().getPersonas());
	    for (int i = 0; i < todas.size() - 1; i++) {
	        for (int j = 0; j < todas.size() - 1 - i; j++) {
	            if (todas.get(j).getUser().getFechaRegistro().isBefore(todas.get(j + 1).getUser().getFechaRegistro())) {
	                Persona temporal = todas.get(j);
	                todas.set(j, todas.get(j + 1));
	                todas.set(j + 1, temporal);
	            }
	        }
	    }

	    ArrayList<Persona> recientes = new ArrayList<Persona>();
	    int limite = Math.min(5, todas.size());
	    for (int i = 0; i < limite; i++) {
	        recientes.add(todas.get(i));
	    }
	    return recientes;
	}
	
	private ArrayList<Oferta> obtenerOfertasRecientes() {
	    ArrayList<Oferta> todas = new ArrayList<Oferta>(BolsaEmpleo.getInstancia().getOfertas());

	    for (int i = 0; i < todas.size() - 1; i++) {
	        for (int j = 0; j < todas.size() - 1 - i; j++) {
	            if (todas.get(j).getFechaPublicacion().isBefore(todas.get(j + 1).getFechaPublicacion())) {
	                Oferta temporal = todas.get(j);
	                todas.set(j, todas.get(j + 1));
	                todas.set(j + 1, temporal);
	            }
	        }
	    }

	    ArrayList<Oferta> recientes = new ArrayList<Oferta>();
	    int limite = Math.min(5, todas.size());
	    for (int i = 0; i < limite; i++) {
	        recientes.add(todas.get(i));
	    }
	    return recientes;
	}

	private DefaultTableModel crearModeloSolicitudesRecientes() {
	    DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Solicitante", "Profesi\u00F3n", "Registro", "Estado" }) {
	        public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
	    };
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
	    for (Persona persona : obtenerSolicitantesRecientes()) {
	        String nombreCandidato = persona.getNombre() + " " + persona.getApellido();
	        String estadoTexto = persona.isEstadoEmpleo() ? "Empleado" : "Buscando empleo";
	        modelo.addRow(new Object[] { nombreCandidato, obtenerProfesion(persona), persona.getUser().getFechaRegistro().format(formato), estadoTexto });
	    }
	    return modelo;
	}

	private String obtenerProfesion(Persona persona) {
	    if (persona instanceof logico.Universitario) {
	        return ((logico.Universitario) persona).getCarrera();
	    }
	    if (persona instanceof logico.Tecnico) {
	        return ((logico.Tecnico) persona).getTecnico();
	    }
	    if (persona instanceof logico.Obrero) {
	        return ((logico.Obrero) persona).getHabilidades();
	    }
	    return "N/A";
	}
	
	private DefaultTableModel crearModeloOfertasRecientes() {
	    DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Puesto", "Empresa", "Fecha Publicaci\u00F3n", "Solicitudes", "Estado" }) {
	        public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
	    };
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
	    for (Oferta oferta : obtenerOfertasRecientes()) {
	        String estadoTexto = formatearEstadoOferta(oferta.getEstado());
	        modelo.addRow(new Object[] { oferta.getPuesto(), oferta.getEmpresa().getNombre(), oferta.getFechaPublicacion().format(formato), contarCandidatosCompatibles(oferta), estadoTexto });
	    }
	    return modelo;
	}

	private String formatearEstado(EstadoSolicitud estado) {
		if (estado == EstadoSolicitud.PENDIENTE) {
			return "Pendiente";
		}
		if (estado == EstadoSolicitud.ACEPTADA) {
			return "Aceptada";
		}
		if (estado == EstadoSolicitud.RECHAZADA) {
			return "Rechazada";
		}
		return "En Revisi\u00F3n";
	}
	
	private String formatearEstadoOferta(EstadoOferta estado) {
		if (estado == EstadoOferta.PENDIENTE) {
			return "Pendiente";
		}
		if (estado == EstadoOferta.COMPLETADA) {
			return "Completada";
		}

		return "En Proceso";
	}

	private class RenderCentrado extends DefaultTableCellRenderer {
		public RenderCentrado() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	private class RenderEstado extends JLabel implements TableCellRenderer {

		public RenderEstado() {
			setOpaque(false);
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("Calibri", Font.BOLD, 14));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			String estado = "";
			if (value != null) {
				estado = value.toString();
			}
			setText(estado);

			if (estado.equals("Aceptada")) {
				setBackground(new Color(198, 239, 206));
				setForeground(new Color(46, 125, 50));
			} else if (estado.equals("Rechazada")) {
				setBackground(new Color(255, 205, 210));
				setForeground(new Color(198, 40, 40));
			} else {
				setBackground(new Color(255, 224, 178));
				setForeground(new Color(204, 102, 0));
			}

			return this;
		}

		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());

			int margenVertical = 6;
			int margenHorizontal = 10;
			g2.fillRoundRect(margenHorizontal, margenVertical,
				getWidth() - margenHorizontal * 2, getHeight() - margenVertical * 2, 16, 16);
			g2.dispose();

			super.paintComponent(g);
		}
	}
	
	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
	    ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
	    Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	    boton.setIcon(new ImageIcon(imagenEscalada));
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

		Image imagenEscalada = icono.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

		label.setIcon(iconoEscalado);
		label.setText("");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
	}
}