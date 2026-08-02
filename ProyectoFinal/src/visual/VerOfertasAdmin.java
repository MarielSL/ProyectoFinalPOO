package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoOferta;
import logico.Oferta;

public class VerOfertasAdmin extends JFrame {

	private JPanel contentPane;
	private JTable tablaOfertas;
	private Dimension dim;
	private Empresa empresa;
	private TextFieldRedond txtBuscar;
	private ComboBoxRedond<String> cbxEstado;
	private ComboBoxRedond<String> cbxTipoEmpleo;
	private JPanel pnlVacio;
	private JPanel pnlTabla;
	private JLabel lblIlustracion;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerOfertasAdmin frame = new VerOfertasAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerOfertasAdmin() {
		if (BolsaEmpleo.getInstancia().getLoginUser() != null) {
			empresa = BolsaEmpleo.getInstancia().getLoginUser().getEmpresa();
		}

		setTitle("Mis Ofertas");
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

		JPanel main = new JPanel();
		contentPane.add(main, BorderLayout.CENTER);
		main.setBackground(new Color(245, 245, 245));
		main.setLayout(null);

		int margen = 40;
		int anchoContenido = dim.width - (margen * 2);

		construirHeader(main, margen, anchoContenido);
		construirTarjetas(main, margen, anchoContenido);
		construirBusqueda(main, margen, anchoContenido);
		construirContenido(main, margen, anchoContenido);

		cargarDatosConHilo();
	}

	private void construirHeader(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelHeader = new PanelConSombra(25);
		panelHeader.setBackground(new Color(0, 0, 51));
		panelHeader.setBounds(0, 0, 1920, 97);
		panel.add(panelHeader);
		panelHeader.setLayout(null);

		BotonRedond btnAtras = new BotonRedond("", 18);
		btnAtras.setBackground(new Color(0, 0, 51));
		btnAtras.setBounds(12, 26, 46, 46);
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setFocusPainted(false);
		btnAtras.setOpaque(false);
		colocarIconoBoton(btnAtras, "/img/menu-dots-vertical(White).png", 25, 25);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BarraAdmin home = new BarraAdmin();
				home.setVisible(true);
				dispose();
			}
		});
		panelHeader.add(btnAtras);

		JLabel lblTitulo = new JLabel("Ofertas");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 35));
		lblTitulo.setForeground(new Color(255, 51, 51));
		lblTitulo.setBounds(59, 36, 400, 30);
		panelHeader.add(lblTitulo);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(1784, 0, 114, 88);
		colocarImagen(lblNewLabel, "/img/iconoLogo_FondoOscuro.png");
		panelHeader.add(lblNewLabel);
	}

	private void construirTarjetas(JPanel panel, int margen, int anchoContenido) {
		int anchoBoton = 260;
		int anchoCards = anchoContenido - anchoBoton - 40;
		int anchoTarjeta = (anchoCards - 24) / 2;

		PanelConSombra panelTotalOfertas = new PanelConSombra(18);
		panelTotalOfertas.setBackground(new Color(255, 224, 178));
		panelTotalOfertas.setBounds(125, 110, anchoTarjeta, 90);
		panel.add(panelTotalOfertas);
		panelTotalOfertas.setLayout(null);

		JLabel lblTotalOfertas = new JLabel("Total de ofertas");
		lblTotalOfertas.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblTotalOfertas.setForeground(new Color(204, 102, 0));
		lblTotalOfertas.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelTotalOfertas.add(lblTotalOfertas);

		JLabel lblTotalOfertasNum = new JLabel(String.valueOf(contarTotalOfertas()));
		lblTotalOfertasNum.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTotalOfertasNum.setForeground(new Color(204, 102, 0));
		lblTotalOfertasNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelTotalOfertas.add(lblTotalOfertasNum);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-3, 3, 78, 78);
		panelTotalOfertas.add(lblNewLabel);
		colocarImagen(lblNewLabel, "/img/empresa_edificio.png");

		PanelConSombra panelOfertasActivas = new PanelConSombra(18);
		panelOfertasActivas.setBackground(new Color(198, 239, 206));
		panelOfertasActivas.setBounds(1008, 110, anchoTarjeta, 90);
		panel.add(panelOfertasActivas);
		panelOfertasActivas.setLayout(null);

		JLabel lblOfertasActivas = new JLabel("Ofertas activas");
		lblOfertasActivas.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblOfertasActivas.setForeground(new Color(46, 125, 50));
		lblOfertasActivas.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelOfertasActivas.add(lblOfertasActivas);

		JLabel lblOfertasActivasNum = new JLabel(String.valueOf(contarOfertasActivas()));
		lblOfertasActivasNum.setFont(new Font("Calibri", Font.BOLD, 30));
		lblOfertasActivasNum.setForeground(new Color(46, 125, 50));
		lblOfertasActivasNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelOfertasActivas.add(lblOfertasActivasNum);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(12, 20, 42, 42);
		panelOfertasActivas.add(lblNewLabel_2);
		colocarImagen(lblNewLabel_2, "/img/check.png");
	}

	private void construirBusqueda(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelBusqueda = new PanelConSombra(18);
		panelBusqueda.setBackground(Color.WHITE);
		panelBusqueda.setBounds(margen, 220, anchoContenido, 60);
		panel.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblEstado.setForeground(new Color(120, 120, 120));
		lblEstado.setBounds(anchoContenido - 420, 4, 150, 16);
		panelBusqueda.add(lblEstado);

		cbxEstado = new ComboBoxRedond<String>(15);
		cbxEstado.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxEstado.setForeground(Color.BLACK);
		cbxEstado.setBackground(Color.WHITE);
		cbxEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "Todos", "Activas", "En pausa", "Cerradas" }));
		cbxEstado.setSelectedIndex(0);
		cbxEstado.setBounds(anchoContenido - 420, 20, 190, 28);
		panelBusqueda.add(cbxEstado);

		JLabel lblTipoEmpleo = new JLabel("Tipo de empleo");
		lblTipoEmpleo.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblTipoEmpleo.setForeground(new Color(120, 120, 120));
		lblTipoEmpleo.setBounds(anchoContenido - 210, 4, 190, 16);
		panelBusqueda.add(lblTipoEmpleo);

		cbxTipoEmpleo = new ComboBoxRedond<String>(15);
		cbxTipoEmpleo.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxTipoEmpleo.setForeground(Color.BLACK);
		cbxTipoEmpleo.setBackground(Color.WHITE);
		cbxTipoEmpleo.setModel(new DefaultComboBoxModel<String>(new String[] { "Todos", "Tiempo completo", "Medio tiempo", "Remoto" }));
		cbxTipoEmpleo.setSelectedIndex(0);
		cbxTipoEmpleo.setBounds(anchoContenido - 210, 20, 190, 28);
		panelBusqueda.add(cbxTipoEmpleo);
	}

	private void construirContenido(JPanel panel, int margen, int anchoContenido) {
		int yContenido = 296;
		int altoContenido = dim.height - yContenido - 60;

		PanelConSombra panelContenedor = new PanelConSombra(20);
		panelContenedor.setBackground(Color.WHITE);
		panelContenedor.setBounds(margen, yContenido, anchoContenido, altoContenido);
		panel.add(panelContenedor);
		panelContenedor.setLayout(null);

		pnlVacio = crearEstadoVacio(anchoContenido, altoContenido);
		pnlVacio.setBounds(0, 0, anchoContenido, altoContenido);
		panelContenedor.add(pnlVacio);

		pnlTabla = crearTabla(anchoContenido, altoContenido);
		pnlTabla.setBounds(0, 0, anchoContenido, altoContenido);
		pnlTabla.setVisible(false);
		panelContenedor.add(pnlTabla);
	}

	private JPanel crearEstadoVacio(int anchoContenido, int altoContenido) {
		JPanel panelVacio = new JPanel();
		panelVacio.setOpaque(false);
		panelVacio.setLayout(null);

		lblIlustracion = new JLabel();
		lblIlustracion.setHorizontalAlignment(JLabel.CENTER);
		panelVacio.add(lblIlustracion);

		JLabel lblTitulo = new JLabel("Aun no has publicado ninguna oferta");
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 0, 51));
		panelVacio.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Cuando publiques una nueva oferta aparecera aqui para que puedas gestionarla facilmente.");
		lblSubtitulo.setHorizontalAlignment(JLabel.CENTER);
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(130, 130, 130));
		panelVacio.add(lblSubtitulo);

		panelVacio.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				int ancho = panelVacio.getWidth();
				colocarImagen(lblIlustracion, "/img/ofertasvacias.png");
				lblIlustracion.setBounds((ancho - 220) / 2, 30, 220, 180);
				lblTitulo.setBounds(0, 226, ancho, 28);
				lblSubtitulo.setBounds((ancho - 520) / 2, 258, 520, 40);
			}
		});

		return panelVacio;
	}

	private JPanel crearTabla(int anchoContenido, int altoContenido) {
		JPanel panelTabla = new JPanel();
		panelTabla.setOpaque(false);
		panelTabla.setLayout(new BorderLayout());

		tablaOfertas = new JTable(crearModeloOfertasRecientes());
		tablaOfertas.setFont(new Font("Calibri", Font.PLAIN, 15));
		tablaOfertas.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));

		JScrollPane scrollPane = new JScrollPane(tablaOfertas);
		scrollPane.setBorder(null);
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		return panelTabla;
	}
	

	//implementacion de hilos
	private void cargarDatosConHilo() {
		SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
			@Override
			protected DefaultTableModel doInBackground() {
				return crearModeloOfertasRecientes();
			}

			@Override
			protected void done() {
				try {
					DefaultTableModel modelo = get();
					tablaOfertas.setModel(modelo);

					if (modelo.getRowCount() == 0) {
						pnlVacio.setVisible(true);
						pnlTabla.setVisible(false);
					} else {
						pnlVacio.setVisible(false);
						pnlTabla.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(VerOfertasAdmin.this, "No se pudieron cargar las ofertas.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		worker.execute();
	}

	private int contarTotalOfertas() {
		if (empresa == null || empresa.getLasOfertas() == null) {
			return 0;
		}
		return empresa.getLasOfertas().size();
	}

	private int contarOfertasActivas() {
		if (empresa == null || empresa.getLasOfertas() == null) {
			return 0;
		}
		int contador = 0;
		for (Oferta oferta : empresa.getLasOfertas()) {
			if (oferta.getEstado() == EstadoOferta.PENDIENTE) {
				contador++;
			}
		}
		return contador;
	}

	private String formatearEstado(EstadoOferta estado) {
		if (estado == EstadoOferta.PENDIENTE) {
			return "Activa";
		}
		if (estado == EstadoOferta.COMPLETADA) {
			return "Cerrada";
		}
		return "N/A";
	}

	private ArrayList<Oferta> obtenerOfertasRecientes() {
		ArrayList<Oferta> todas = new ArrayList<Oferta>();
		if (empresa == null || empresa.getLasOfertas() == null) {
			return todas;
		}

		todas.addAll(empresa.getLasOfertas());

		for (int i = 0; i < todas.size() - 1; i++) {
			for (int j = 0; j < todas.size() - 1 - i; j++) {
				if (todas.get(j).getFechaPublicacion() != null && todas.get(j + 1).getFechaPublicacion() != null
						&& todas.get(j).getFechaPublicacion().isBefore(todas.get(j + 1).getFechaPublicacion())) {
					Oferta temporal = todas.get(j);
					todas.set(j, todas.get(j + 1));
					todas.set(j + 1, temporal);
				}
			}
		}
		return todas;
	}

	private DefaultTableModel crearModeloOfertasRecientes() {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Puesto", "Empresa", "Fecha Publicación", "Solicitudes", "Estado" }) {
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");

		for (Oferta oferta : obtenerOfertasRecientes()) {
			String fecha = "";
			if (oferta.getFechaPublicacion() != null) {
				fecha = oferta.getFechaPublicacion().format(formato);
			}
			String estadoTexto = formatearEstadoOferta(oferta.getEstado());
			modelo.addRow(new Object[] { oferta.getPuesto(),
					oferta.getEmpresa() != null ? oferta.getEmpresa().getNombre() : "",
					fecha, oferta.cantContratados(), estadoTexto });
		}
		return modelo;
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

	private void colocarImagen(JLabel label, String ruta) {
		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
		int anchoLabel = Math.max(1, label.getWidth());
		int altoLabel = Math.max(1, label.getHeight());
		int anchoImagen = icono.getIconWidth();
		int altoImagen = icono.getIconHeight();
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

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagenEscalada));
	}
}