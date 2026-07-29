package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoOferta;
import logico.Oferta;

public class VerOfertasAdmin extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private Empresa empresa;
	private TextFieldRedond txtBuscar;
	private ComboBoxRedond<String> cbxEstado;
	private ComboBoxRedond<String> cbxTipoEmpleo;
	private JPanel pnlVacio;
	private JPanel pnlTabla;
	private JLabel lblIlustracion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
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
				HomeAdministrador home = new HomeAdministrador();
				home.setVisible(true);
				dispose();
			}
		});
		panelHeader.add(btnAtras);

		JLabel lblTitulo = new JLabel("Ofertas");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTitulo.setForeground(new Color(255, 51, 51));
		lblTitulo.setBounds(74, 22, 400, 30);
		panelHeader.add(lblTitulo);

		String nombreEmpresa = "Mi Empresa";
		if (empresa != null) {
			nombreEmpresa = empresa.getNombre();
		}
		int anchoNombre = 14 * nombreEmpresa.length() + 20;

		JLabel lblChevron = new JLabel();
		lblChevron.setBounds(dim.width - 40, 26, 18, 18);
		panelHeader.add(lblChevron);
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

		JLabel lblIconoTotal = new JLabel();
		lblIconoTotal.setBounds(16, 16, 40, 40);
		panelTotalOfertas.add(lblIconoTotal);

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

		PanelConSombra panelOfertasActivas = new PanelConSombra(18);
		panelOfertasActivas.setBackground(new Color(198, 239, 206));
		panelOfertasActivas.setBounds(1008, 110, anchoTarjeta, 90);
		panel.add(panelOfertasActivas);
		panelOfertasActivas.setLayout(null);

		JLabel lblIconoActivas = new JLabel();
		lblIconoActivas.setBounds(16, 16, 40, 40);
		panelOfertasActivas.add(lblIconoActivas);

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

		pnlVacio = crearEstadoVacio();
		pnlVacio.setBounds(0, 0, anchoContenido, altoContenido);
		panelContenedor.add(pnlVacio);

		pnlTabla = crearTabla();
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

		JLabel lblTitulo = new JLabel("Aun no has publicado ninguna oferta");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setBounds(0, 220, 1, 1);
		panelVacio.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Cuando publiques una nueva oferta aparecera aqui para que puedas gestionarla facilmente.");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(130, 130, 130));
		lblSubtitulo.setBounds(0, 250, 1, 1);
		panelVacio.add(lblSubtitulo);

		panelVacio.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				int ancho = panelVacio.getWidth();
				lblIlustracion.setBounds((ancho - 220) / 2, 30, 220, 180);
				colocarImagen(lblIlustracion, "/img/ofertasvacias.png");
				lblTitulo.setBounds(0, 226, ancho, 28);
				lblSubtitulo.setBounds((ancho - 520) / 2, 258, 520, 40);
			}
		});

		return panelVacio;
	}

	private JPanel crearTabla() {
		JPanel panelTabla = new JPanel();
		panelTabla.setOpaque(false);
		return panelTabla;
	}

	private void cargarDatos() {
		ArrayList<Oferta> lasOfertas = new ArrayList<Oferta>();
		if (empresa != null) {
			lasOfertas = empresa.getLasOfertas();
		}

		if (lasOfertas.isEmpty()) {
			//btnPublicarOferta.setText("Publicar nueva oferta");
			pnlVacio.setVisible(true);
			pnlTabla.setVisible(false);
			return;
		}
		pnlVacio.setVisible(false);
		pnlTabla.setVisible(true);
	}

	private int contarTotalOfertas() {
		if (empresa == null) {
			return 0;
		}
		return empresa.getLasOfertas().size();
	}

	private int contarOfertasActivas() {
		if (empresa == null) {
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
	
	private ArrayList<Oferta> obtenerOfertasRecientes() {
	    ArrayList<Oferta> todas = new ArrayList<Oferta>();
	    if (empresa == null) {
	        return todas;
	    }
	    for (Oferta oferta : empresa.getLasOfertas()) {
	        todas.add(oferta);
	    }

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
	    for (int i = 0; i < recientes.size(); i++) {
	        recientes.add(todas.get(i));
	    }
	    return recientes;
	}
	
	private DefaultTableModel crearModeloOfertasRecientes() {
	    DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Puesto", "Empresa", "Fecha Publicación", "Solicitudes", "Estado" }) {
	        public boolean isCellEditable(int fila, int columna) {
	            return false;
	        }
	    };
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
	    for (Oferta oferta : obtenerOfertasRecientes()) {
	        String estadoTexto = formatearEstadoOferta(oferta.getEstado());
	        modelo.addRow(new Object[] { oferta.getPuesto(), oferta.getEmpresa(), oferta.getFechaPublicacion().format(formato), oferta.getSolicitudes().size(), estadoTexto });
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
}