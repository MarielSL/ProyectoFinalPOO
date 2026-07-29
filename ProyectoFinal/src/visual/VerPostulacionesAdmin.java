package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
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
import logico.SolicitudEmpleo;

public class VerPostulacionesAdmin extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private TextFieldRedond txtBuscar;
	private ComboBoxRedond<String> cbxEstado;
	private JPanel pnlVacio;
	private JPanel pnlTabla;
	private JLabel lblIlustracion;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerPostulacionesAdmin frame = new VerPostulacionesAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerPostulacionesAdmin() {
		setTitle("Postulaciones");
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

		construirHeader(panel, margen, anchoContenido);
		construirTarjetas(panel, margen, anchoContenido);
		construirBusqueda(panel, margen, anchoContenido);
		construirContenido(panel, margen, anchoContenido);

		cargarDatos();
	}

	private void construirHeader(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelHeader = new PanelConSombra(25);
		panelHeader.setBackground(new Color(0, 0, 51));
		panelHeader.setBounds(0, 0, dim.width, 70);
		panel.add(panelHeader);
		panelHeader.setLayout(null);

		BotonRedond btnAtras = new BotonRedond("", 18);
		btnAtras.setBackground(new Color(0, 0, 51));
		btnAtras.setBounds(20, 12, 46, 46);
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setFocusPainted(false);
		btnAtras.setOpaque(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BarraAdmin home = new BarraAdmin();
				home.setVisible(true);
				dispose();
			}
		});
		panelHeader.add(btnAtras);

		JLabel lblTitulo = new JLabel("Postulaciones");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(255, 51, 51));
		lblTitulo.setBounds(74, 22, 400, 30);
		panelHeader.add(lblTitulo);

		JLabel lblChevron = new JLabel();
		lblChevron.setBounds(dim.width - 40, 26, 18, 18);
		panelHeader.add(lblChevron);
	}

	private void construirTarjetas(JPanel panel, int margen, int anchoContenido) {
		int anchoBoton = 260;
		int anchoCards = anchoContenido - anchoBoton - 40;
		int anchoTarjeta = (anchoCards - 24) / 2;

		PanelConSombra panelTotalPostulaciones = new PanelConSombra(18);
		panelTotalPostulaciones.setBackground(new Color(255, 224, 178));
		panelTotalPostulaciones.setBounds(125, 110, anchoTarjeta, 90);
		panel.add(panelTotalPostulaciones);
		panelTotalPostulaciones.setLayout(null);

		JLabel lblIconoTotal = new JLabel();
		lblIconoTotal.setBounds(16, 16, 40, 40);
		panelTotalPostulaciones.add(lblIconoTotal);

		JLabel lblTotalPostulaciones = new JLabel("Total de postulaciones");
		lblTotalPostulaciones.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblTotalPostulaciones.setForeground(new Color(204, 102, 0));
		lblTotalPostulaciones.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelTotalPostulaciones.add(lblTotalPostulaciones);

		JLabel lblTotalPostulacionesNum = new JLabel(String.valueOf(contarTotalPostulaciones()));
		lblTotalPostulacionesNum.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTotalPostulacionesNum.setForeground(new Color(204, 102, 0));
		lblTotalPostulacionesNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelTotalPostulaciones.add(lblTotalPostulacionesNum);

		PanelConSombra panelPendientes = new PanelConSombra(18);
		panelPendientes.setBackground(new Color(198, 239, 206));
		panelPendientes.setBounds(1008, 110, anchoTarjeta, 90);
		panel.add(panelPendientes);
		panelPendientes.setLayout(null);

		JLabel lblIconoPendientes = new JLabel();
		lblIconoPendientes.setBounds(16, 16, 40, 40);
		panelPendientes.add(lblIconoPendientes);

		JLabel lblPendientes = new JLabel("Pendientes de revisión");
		lblPendientes.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblPendientes.setForeground(new Color(46, 125, 50));
		lblPendientes.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelPendientes.add(lblPendientes);

		JLabel lblPendientesNum = new JLabel(String.valueOf(contarPostulacionesPendientes()));
		lblPendientesNum.setFont(new Font("Calibri", Font.BOLD, 30));
		lblPendientesNum.setForeground(new Color(46, 125, 50));
		lblPendientesNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelPendientes.add(lblPendientesNum);
	}

	private void construirBusqueda(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelBusqueda = new PanelConSombra(18);
		panelBusqueda.setBackground(Color.WHITE);
		panelBusqueda.setBounds(margen, 220, anchoContenido, 60);
		panel.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		JLabel lblIconoBuscar = new JLabel();
		lblIconoBuscar.setBounds(20, 18, 22, 22);
		panelBusqueda.add(lblIconoBuscar);

		txtBuscar = new TextFieldRedond(18);
		txtBuscar.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtBuscar.setForeground(new Color(0, 0, 51));
		txtBuscar.setBackground(new Color(245, 245, 245));
		txtBuscar.setBounds(50, 14, anchoContenido - 300, 32);
		panelBusqueda.add(txtBuscar);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblEstado.setForeground(new Color(120, 120, 120));
		lblEstado.setBounds(anchoContenido - 220, 4, 190, 16);
		panelBusqueda.add(lblEstado);

		cbxEstado = new ComboBoxRedond<String>(15);
		cbxEstado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxEstado.setForeground(Color.BLACK);
		cbxEstado.setBackground(Color.WHITE);
		cbxEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "Todos", "Pendiente", "Aceptada", "Rechazada" }));
		cbxEstado.setSelectedIndex(0);
		cbxEstado.setBounds(anchoContenido - 220, 20, 190, 28);
		panelBusqueda.add(cbxEstado);
	}

	private void construirContenido(JPanel panel, int margen, int anchoContenido) {
		int yContenido = 296;
		int altoContenido = dim.height - yContenido - 60;

		PanelConSombra panelContenedor = new PanelConSombra(20);
		panelContenedor.setBackground(Color.WHITE);
		panelContenedor.setBounds(margen, yContenido, anchoContenido, altoContenido);
		panel.add(panelContenedor);
		panelContenedor.setLayout(null);

		pnlVacio = crearEstadoVacio();
		pnlVacio.setBounds(0, 0, anchoContenido, altoContenido);
		panelContenedor.add(pnlVacio);

		pnlTabla = crearTabla(anchoContenido, altoContenido);
		pnlTabla.setBounds(0, 0, anchoContenido, altoContenido);
		pnlTabla.setVisible(false);
		panelContenedor.add(pnlTabla);
	}

	private JPanel crearEstadoVacio() {
		JPanel panelVacio = new JPanel();
		panelVacio.setOpaque(false);
		panelVacio.setLayout(null);

		lblIlustracion = new JLabel();
		lblIlustracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIlustracion.setBounds(0, 40, 1, 1);
		panelVacio.add(lblIlustracion);

		JLabel lblTitulo = new JLabel("Aun no hay postulaciones registradas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setBounds(0, 220, 1, 1);
		panelVacio.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Cuando los candidatos postulen a ofertas apareceran aqui.");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(130, 130, 130));
		lblSubtitulo.setBounds(0, 250, 1, 1);
		panelVacio.add(lblSubtitulo);

		panelVacio.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				int ancho = panelVacio.getWidth();
				lblIlustracion.setBounds((ancho - 220) / 2, 30, 220, 180);
				colocarImagen(lblIlustracion, "/img/postulacionesvacias.png");
				lblTitulo.setBounds(0, 226, ancho, 28);
				lblSubtitulo.setBounds((ancho - 520) / 2, 258, 520, 40);
			}
		});

		return panelVacio;
	}

	private JPanel crearTabla(int ancho, int alto) {
		JPanel panelTabla = new JPanel();
		panelTabla.setOpaque(false);
		panelTabla.setLayout(null);

		table = new JTable();
		table.setModel(crearModeloPostulaciones());
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(38);
		table.setForeground(new Color(50, 50, 50));
		table.setSelectionBackground(new Color(240, 240, 245));
		table.setShowGrid(false);
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
		table.getTableHeader().setForeground(new Color(0, 0, 51));
		table.getColumnModel().getColumn(4).setCellRenderer(new RenderEstado());

		JScrollPane scrollTabla = new JScrollPane(table);
		scrollTabla.setBorder(null);
		scrollTabla.setBounds(24, 20, ancho - 48, alto - 40);
		panelTabla.add(scrollTabla);

		return panelTabla;
	}

	private void cargarDatos() {
		ArrayList<SolicitudEmpleo> lasSolicitudes = BolsaEmpleo.getInstancia().getSolicitudes();

		if (lasSolicitudes == null || lasSolicitudes.isEmpty()) {
			pnlVacio.setVisible(true);
			pnlTabla.setVisible(false);
			return;
		}
		pnlVacio.setVisible(false);
		pnlTabla.setVisible(true);
	}

	private int contarTotalPostulaciones() {
		ArrayList<SolicitudEmpleo> lasSolicitudes = BolsaEmpleo.getInstancia().getSolicitudes();
		if (lasSolicitudes == null) {
			return 0;
		}
		return lasSolicitudes.size();
	}

	private int contarPostulacionesPendientes() {
		ArrayList<SolicitudEmpleo> lasSolicitudes = BolsaEmpleo.getInstancia().getSolicitudes();
		if (lasSolicitudes == null) {
			return 0;
		}
		int contador = 0;
		for (SolicitudEmpleo solicitud : lasSolicitudes) {
			if (solicitud.getEstado() == EstadoSolicitud.PENDIENTE) {
				contador++;
			}
		}
		return contador;
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
		return "N/A";
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

	private DefaultTableModel crearModeloPostulaciones() {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Solicitante", "Oferta", "Empresa", "Fecha", "Estado" }) {
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
		ArrayList<SolicitudEmpleo> lasSolicitudes = BolsaEmpleo.getInstancia().getSolicitudes();
		if (lasSolicitudes == null) {
			return modelo;
		}
		for (SolicitudEmpleo solicitud : lasSolicitudes) {
			String nombreCandidato = solicitud.getCandidato().getNombre() + " " + solicitud.getCandidato().getApellido();
			String nombreEmpresa = solicitud.getOferta().getEmpresa().getNombre();
			String estadoTexto = formatearEstado(solicitud.getEstado());
			modelo.addRow(new Object[] { nombreCandidato, solicitud.getOferta().getPuesto(), nombreEmpresa, solicitud.getFechaSolicitud().format(formato), estadoTexto });
		}
		return modelo;
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
}