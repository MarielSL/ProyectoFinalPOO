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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import logico.BolsaEmpleo;
import logico.EstadoSolicitud;
import logico.Persona;
import logico.SolicitudEmpleo;

public class HomeCandidato extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private Persona candidato;
	private JTable tablaRecientes;
	private DefaultTableModel modeloRecientes;
	private JLabel lblNumPendientes;
	private JLabel lblNumAceptadas;
	private JLabel lblNumRechazadas;
	private static final int MAX_RECIENTES = 6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeCandidato frame = new HomeCandidato();
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
	public HomeCandidato() {
		candidato = null;
		if (BolsaEmpleo.getInstancia().getLoginUser() != null) {
			candidato = BolsaEmpleo.getInstancia().getLoginUser().getPersona();
		}

		setTitle("Home Candidato");
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			PanelRedond panelNav = new PanelRedond(25);
			panelNav.setBackground(new Color(0, 0, 51));
			panelNav.setBounds(margen, 20, anchoContenido, 70);
			panel.add(panelNav);
			panelNav.setLayout(null);
			
			BotonRedond btnMenu = new BotonRedond("",25);
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BarraSolicitante menu = new BarraSolicitante();
					menu.setVisible(true);
				}
			});
			btnMenu.setBackground(new Color(0, 0, 51));
			btnMenu.setColorHover(new Color(0, 51, 102));
			btnMenu.setBounds(13, 4, 60, 60);
			colocarIconoBoton(btnMenu,"/img/menu-dots-vertical(White).png",25,25);
			btnMenu.setMargin(new Insets(0, 0, 0, 0));
			btnMenu.setBorderPainted(false);
			btnMenu.setContentAreaFilled(false);
			btnMenu.setFocusPainted(false);
			btnMenu.setOpaque(false);
			panelNav.add(btnMenu);

			JLabel lblInicio = new JLabel("Inicio");
			lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicio.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblInicio.setForeground(Color.WHITE);
			lblInicio.setBounds(685, 26, 70, 20);
			panelNav.add(lblInicio);

			JLabel lblBusqueda = new JLabel("B\u00FAsqueda");
			lblBusqueda.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblBusqueda.setForeground(Color.WHITE);
			lblBusqueda.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblBusqueda.setBounds(799, 26, 80, 20);
			lblBusqueda.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					abrirBusquedaOfertas();
				}
			});
			panelNav.add(lblBusqueda);

			JLabel lblMisSolicitudes = new JLabel("Mis solicitudes");
			lblMisSolicitudes.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblMisSolicitudes.setForeground(Color.WHITE);
			lblMisSolicitudes.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblMisSolicitudes.setBounds(926, 26, 107, 20);
			lblMisSolicitudes.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					abrirMisSolicitudes();
				}
			});
			panelNav.add(lblMisSolicitudes);

			JLabel lblVerPerfil = new JLabel("Ver perfil");
			lblVerPerfil.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblVerPerfil.setForeground(Color.WHITE);
			lblVerPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblVerPerfil.setBounds(1087, 26, 80, 20);
			lblVerPerfil.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					abrirVerPerfil();
				}
			});
			panelNav.add(lblVerPerfil);
		}

		int anchoTarjeta = (anchoContenido - 48) / 3;

		{
			PanelConSombra panelPendientes = new PanelConSombra(18);
			panelPendientes.setBackground(new Color(255, 224, 178));
			panelPendientes.setBounds(margen, 110, anchoTarjeta, 90);
			panel.add(panelPendientes);
			panelPendientes.setLayout(null);

			JLabel lblTitulo = new JLabel("Solicitudes pendientes");
			lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblTitulo.setForeground(new Color(204, 102, 0));
			lblTitulo.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelPendientes.add(lblTitulo);

			lblNumPendientes = new JLabel("0");
			lblNumPendientes.setFont(new Font("Calibri", Font.BOLD, 30));
			lblNumPendientes.setForeground(new Color(204, 102, 0));
			lblNumPendientes.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelPendientes.add(lblNumPendientes);
		}

		{
			PanelConSombra panelAceptadas = new PanelConSombra(18);
			panelAceptadas.setBackground(new Color(198, 239, 206));
			panelAceptadas.setBounds(margen + anchoTarjeta + 24, 110, anchoTarjeta, 90);
			panel.add(panelAceptadas);
			panelAceptadas.setLayout(null);

			JLabel lblTitulo = new JLabel("Solicitudes aceptadas");
			lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblTitulo.setForeground(new Color(46, 125, 50));
			lblTitulo.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelAceptadas.add(lblTitulo);

			lblNumAceptadas = new JLabel("0");
			lblNumAceptadas.setFont(new Font("Calibri", Font.BOLD, 30));
			lblNumAceptadas.setForeground(new Color(46, 125, 50));
			lblNumAceptadas.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelAceptadas.add(lblNumAceptadas);
		}

		{
			PanelConSombra panelRechazadas = new PanelConSombra(18);
			panelRechazadas.setBackground(new Color(255, 205, 210));
			panelRechazadas.setBounds(margen + (anchoTarjeta + 24) * 2, 110, anchoTarjeta, 90);
			panel.add(panelRechazadas);
			panelRechazadas.setLayout(null);

			JLabel lblTitulo = new JLabel("Solicitudes rechazadas");
			lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblTitulo.setForeground(new Color(198, 40, 40));
			lblTitulo.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelRechazadas.add(lblTitulo);

			lblNumRechazadas = new JLabel("0");
			lblNumRechazadas.setFont(new Font("Calibri", Font.BOLD, 30));
			lblNumRechazadas.setForeground(new Color(198, 40, 40));
			lblNumRechazadas.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelRechazadas.add(lblNumRechazadas);
		}

		{
			int yTabla = 220;
			int altoTabla = dim.height - yTabla - 60;

			PanelConSombra panelTabla = new PanelConSombra(20);
			panelTabla.setBackground(Color.WHITE);
			panelTabla.setBounds(margen, yTabla, anchoContenido, altoTabla);
			panel.add(panelTabla);
			panelTabla.setLayout(null);

			JLabel lblTitulo = new JLabel("Mis solicitudes recientes");
			lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
			lblTitulo.setForeground(new Color(0, 0, 51));
			lblTitulo.setBounds(24, 20, 400, 28);
			panelTabla.add(lblTitulo);

			modeloRecientes = new DefaultTableModel(new Object[][] {},new String[] { "Oferta", "Fecha", "Estado" }) {
				public boolean isCellEditable(int fila, int columna) {
					return false;
				}
			};

			tablaRecientes = new JTable(modeloRecientes);
			tablaRecientes.setFont(new Font("Calibri", Font.PLAIN, 16));
			tablaRecientes.setRowHeight(38);
			tablaRecientes.setForeground(new Color(50, 50, 50));
			tablaRecientes.setSelectionBackground(new Color(240, 240, 245));
			tablaRecientes.setShowGrid(false);
			tablaRecientes.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
			tablaRecientes.getTableHeader().setForeground(new Color(0, 0, 51));
			tablaRecientes.getColumnModel().getColumn(2).setCellRenderer(new RenderEstado());

			JScrollPane scrollTabla = new JScrollPane(tablaRecientes);
			scrollTabla.setBorder(null);
			scrollTabla.setBounds(24, 60, anchoContenido - 48, altoTabla - 90);
			panelTabla.add(scrollTabla);
		}

		cargarDatos();
	}

	private void cargarDatos() {
		ArrayList<SolicitudEmpleo> lasSolicitudes = new ArrayList<SolicitudEmpleo>();
		if (candidato != null) {
			lasSolicitudes = candidato.getSolicitudes();
		}

		int pendientes = 0;
		int aceptadas = 0;
		int rechazadas = 0;

		for (SolicitudEmpleo solicitud : lasSolicitudes) {
			if (solicitud.getEstado() == EstadoSolicitud.PENDIENTE) {
				pendientes++;
			} else if (solicitud.getEstado() == EstadoSolicitud.ACEPTADA) {
				aceptadas++;
			} else if (solicitud.getEstado() == EstadoSolicitud.RECHAZADA) {
				rechazadas++;
			}
		}

		lblNumPendientes.setText(String.valueOf(pendientes));
		lblNumAceptadas.setText(String.valueOf(aceptadas));
		lblNumRechazadas.setText(String.valueOf(rechazadas));

		ArrayList<SolicitudEmpleo> solicitudesOrdenadas = new ArrayList<SolicitudEmpleo>(lasSolicitudes);
		ordenarPorFechaDescendente(solicitudesOrdenadas);

		modeloRecientes.setRowCount(0);
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");

		int cantidad = solicitudesOrdenadas.size();
		if (cantidad > MAX_RECIENTES) {
			cantidad = MAX_RECIENTES;
		}

		for (int i = 0; i < cantidad; i++) {
			SolicitudEmpleo solicitud = solicitudesOrdenadas.get(i);
			modeloRecientes.addRow(new Object[] {
				solicitud.getPuesto(),
				solicitud.getFechaSolicitud().format(formato),
				formatearEstado(solicitud.getEstado())
			});
		}
	}

	private void ordenarPorFechaDescendente(ArrayList<SolicitudEmpleo> lista) {
		for (int i = 0; i < lista.size() - 1; i++) {
			for (int j = 0; j < lista.size() - 1 - i; j++) {
				SolicitudEmpleo actual = lista.get(j);
				SolicitudEmpleo siguiente = lista.get(j + 1);
				if (actual.getFechaSolicitud().isBefore(siguiente.getFechaSolicitud())) {
					lista.set(j, siguiente);
					lista.set(j + 1, actual);
				}
			}
		}
	}

	private String formatearEstado(EstadoSolicitud estado) {
		String texto = "Pendiente";
		if (estado == EstadoSolicitud.ACEPTADA) {
			texto = "Aceptada";
		} else if (estado == EstadoSolicitud.RECHAZADA) {
			texto = "Rechazada";
		}
		return texto;
	}

	private void abrirBusquedaOfertas() {
		BuscarOfertas frame = new BuscarOfertas(candidato);
		frame.setVisible(true);
	}

	private void abrirMisSolicitudes() {
		VerSolicitudesAplicadas frame = new VerSolicitudesAplicadas();
		frame.setVisible(true);
	}

	private void abrirVerPerfil() {
		VerUserSolicitante frame = new VerUserSolicitante();
		frame.setVisible(true);
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
}