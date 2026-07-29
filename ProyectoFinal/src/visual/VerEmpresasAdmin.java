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
import logico.Empresa;
import logico.TipoEmpresa;

public class VerEmpresasAdmin extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private TextFieldRedond txtBuscar;
	private ComboBoxRedond<String> cbxEstado;
	private ComboBoxRedond<String> cbxTipo;
	private JPanel pnlVacio;
	private JPanel pnlTabla;
	private JLabel lblIlustracion;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerEmpresasAdmin frame = new VerEmpresasAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerEmpresasAdmin() {
		setTitle("Empresas");
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

		JLabel lblTitulo = new JLabel("Empresas");
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

		PanelConSombra panelTotalEmpresas = new PanelConSombra(18);
		panelTotalEmpresas.setBackground(new Color(255, 224, 178));
		panelTotalEmpresas.setBounds(125, 110, anchoTarjeta, 90);
		panel.add(panelTotalEmpresas);
		panelTotalEmpresas.setLayout(null);

		JLabel lblIconoTotal = new JLabel();
		lblIconoTotal.setBounds(16, 16, 40, 40);
		panelTotalEmpresas.add(lblIconoTotal);

		JLabel lblTotalEmpresas = new JLabel("Total de empresas");
		lblTotalEmpresas.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblTotalEmpresas.setForeground(new Color(204, 102, 0));
		lblTotalEmpresas.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelTotalEmpresas.add(lblTotalEmpresas);

		JLabel lblTotalEmpresasNum = new JLabel(String.valueOf(contarTotalEmpresas()));
		lblTotalEmpresasNum.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTotalEmpresasNum.setForeground(new Color(204, 102, 0));
		lblTotalEmpresasNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelTotalEmpresas.add(lblTotalEmpresasNum);

		PanelConSombra panelEmpresasActivas = new PanelConSombra(18);
		panelEmpresasActivas.setBackground(new Color(198, 239, 206));
		panelEmpresasActivas.setBounds(1008, 110, anchoTarjeta, 90);
		panel.add(panelEmpresasActivas);
		panelEmpresasActivas.setLayout(null);

		JLabel lblIconoActivas = new JLabel();
		lblIconoActivas.setBounds(16, 16, 40, 40);
		panelEmpresasActivas.add(lblIconoActivas);

		JLabel lblEmpresasActivas = new JLabel("Empresas activas");
		lblEmpresasActivas.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblEmpresasActivas.setForeground(new Color(46, 125, 50));
		lblEmpresasActivas.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelEmpresasActivas.add(lblEmpresasActivas);

		JLabel lblEmpresasActivasNum = new JLabel(String.valueOf(contarEmpresasActivas()));
		lblEmpresasActivasNum.setFont(new Font("Calibri", Font.BOLD, 30));
		lblEmpresasActivasNum.setForeground(new Color(46, 125, 50));
		lblEmpresasActivasNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelEmpresasActivas.add(lblEmpresasActivasNum);
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
		txtBuscar.setBounds(50, 14, anchoContenido - 480, 32);
		panelBusqueda.add(txtBuscar);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblEstado.setForeground(new Color(120, 120, 120));
		lblEstado.setBounds(anchoContenido - 420, 4, 150, 16);
		panelBusqueda.add(lblEstado);

		cbxEstado = new ComboBoxRedond<String>(15);
		cbxEstado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxEstado.setForeground(Color.BLACK);
		cbxEstado.setBackground(Color.WHITE);
		cbxEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "Todas", "Activas", "Inactivas" }));
		cbxEstado.setSelectedIndex(0);
		cbxEstado.setBounds(anchoContenido - 420, 20, 190, 28);
		panelBusqueda.add(cbxEstado);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblTipo.setForeground(new Color(120, 120, 120));
		lblTipo.setBounds(anchoContenido - 210, 4, 190, 16);
		panelBusqueda.add(lblTipo);

		cbxTipo = new ComboBoxRedond<String>(15);
		cbxTipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxTipo.setForeground(Color.BLACK);
		cbxTipo.setBackground(Color.WHITE);
		ArrayList<String> opcionesTipo = new ArrayList<String>();
		opcionesTipo.add("Todos");
		for (TipoEmpresa tipo : TipoEmpresa.values()) {
			opcionesTipo.add(capitalizar(tipo.name()));
		}
		cbxTipo.setModel(new DefaultComboBoxModel<String>(opcionesTipo.toArray(new String[0])));
		cbxTipo.setSelectedIndex(0);
		cbxTipo.setBounds(anchoContenido - 210, 20, 190, 28);
		panelBusqueda.add(cbxTipo);
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

		JLabel lblTitulo = new JLabel("Aun no hay empresas asociadas");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setBounds(0, 220, 1, 1);
		panelVacio.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Cuando se registren empresas en la plataforma apareceran aqui.");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(130, 130, 130));
		lblSubtitulo.setBounds(0, 250, 1, 1);
		panelVacio.add(lblSubtitulo);

		panelVacio.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				int ancho = panelVacio.getWidth();
				lblIlustracion.setBounds((ancho - 220) / 2, 30, 220, 180);
				colocarImagen(lblIlustracion, "/img/empresasvacias.png");
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
		table.setModel(crearModeloEmpresas());
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
		ArrayList<Empresa> lasEmpresas = BolsaEmpleo.getInstancia().getEmpresas();

		if (lasEmpresas == null || lasEmpresas.isEmpty()) {
			pnlVacio.setVisible(true);
			pnlTabla.setVisible(false);
			return;
		}
		pnlVacio.setVisible(false);
		pnlTabla.setVisible(true);
	}

	private int contarTotalEmpresas() {
		ArrayList<Empresa> lasEmpresas = BolsaEmpleo.getInstancia().getEmpresas();
		if (lasEmpresas == null) {
			return 0;
		}
		return lasEmpresas.size();
	}

	private int contarEmpresasActivas() {
		ArrayList<Empresa> lasEmpresas = BolsaEmpleo.getInstancia().getEmpresas();
		if (lasEmpresas == null) {
			return 0;
		}
		int contador = 0;
		for (Empresa empresa : lasEmpresas) {
			if (empresa.isEstado()) {
				contador++;
			}
		}
		return contador;
	}

	private String capitalizar(String texto) {
		if (texto == null || texto.isEmpty()) {
			return texto;
		}
		return texto.charAt(0) + texto.substring(1).toLowerCase();
	}

	private String formatearEstadoEmpresa(boolean estado) {
		if (estado) {
			return "Activa";
		}
		return "Inactiva";
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

	private DefaultTableModel crearModeloEmpresas() {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Empresa", "Contacto", "Tipo", "Registro", "Estado" }) {
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
		ArrayList<Empresa> lasEmpresas = BolsaEmpleo.getInstancia().getEmpresas();
		if (lasEmpresas == null) {
			return modelo;
		}
		for (Empresa empresa : lasEmpresas) {
			String tipo = capitalizar(empresa.getTipo().name());
			String estadoTexto = formatearEstadoEmpresa(empresa.isEstado());
			modelo.addRow(new Object[] { empresa.getNombre(), empresa.getTelefono(), tipo, empresa.getUser().getFechaRegistro().format(formato), estadoTexto });
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

			if (estado.equals("Activa")) {
				setBackground(new Color(198, 239, 206));
				setForeground(new Color(46, 125, 50));
			} else {
				setBackground(new Color(255, 205, 210));
				setForeground(new Color(198, 40, 40));
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