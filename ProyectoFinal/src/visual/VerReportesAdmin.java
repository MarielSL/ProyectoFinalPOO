package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoOferta;
import logico.EstadoSolicitud;
import logico.Oferta;
import logico.Persona;
import logico.SolicitudEmpleo;

public class VerReportesAdmin extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerReportesAdmin frame = new VerReportesAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerReportesAdmin() {
		setTitle("Reportes");
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
		construirReportes(panel, margen, anchoContenido);
	}

	private void construirHeader(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelHeader = new PanelConSombra(25);
		panelHeader.setBackground(new Color(0, 0, 51));
		panelHeader.setBounds(0, 0, 1920, 82);
		panel.add(panelHeader);
		panelHeader.setLayout(null);

		BotonRedond btnAtras = new BotonRedond("", 18);
		btnAtras.setBackground(new Color(0, 0, 51));
		btnAtras.setBounds(20, 12, 46, 46);
		colocarIconoBoton(btnAtras, "/img/menu-dots-vertical(White).png",25,25);
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

		JLabel lblTitulo = new JLabel("Reportes");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(255, 51, 51));
		lblTitulo.setBounds(74, 22, 400, 30);
		panelHeader.add(lblTitulo);

		JLabel lblChevron = new JLabel();
		lblChevron.setBounds(dim.width - 40, 26, 18, 18);
		panelHeader.add(lblChevron);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(1784, 0, 114, 88);
		colocarImagen(lblNewLabel_1, "/img/iconoLogo_FondoOscuro.png");
		panelHeader.add(lblNewLabel_1);
	}

	private void construirReportes(JPanel panel, int margen, int anchoContenido) {
		int yInicio = 110;
		int columnas = 3;
		int espacio = 24;
		int anchoTarjeta = (anchoContenido - espacio * (columnas - 1)) / columnas;
		int altoTarjeta = 160;

		String[] titulos = { "Ofertas", "Solicitantes", "Empresas", "Actividad general", "Ver Gr\u00E1ficas" };
		String[] subtitulos = {
			"Resumen de las ofertas publicadas",
			"Resumen de solicitantes registrados",
			"Resumen de empresas asociadas",
			"Vista general de la plataforma",
			"Visualiza los datos en gr\u00E1ficas"
		};
		String[] iconos = {
			"/img/maletin_rojo.png",
			"/img/esquema-de-trabajador-de-oficina.png",
			"/img/ciudad.png",
			"/img/analisis-de-los-datos.png",
			"/img/iconograf.png"
		};

		for (int indice = 0; indice < titulos.length; indice++) {
			int fila = indice / columnas;
			int col = indice % columnas;
			int x = margen + col * (anchoTarjeta + espacio);
			int y = yInicio + fila * (altoTarjeta + espacio);
			crearTarjetaReporte(panel, x, y, anchoTarjeta, altoTarjeta, titulos[indice], subtitulos[indice], iconos[indice], indice);
		}
	}

	private void crearTarjetaReporte(JPanel panel, int x, int y, int ancho, int alto, String titulo, String subtitulo, String rutaIcono, int indiceReporte) {
		PanelConSombra tarjeta = new PanelConSombra(20);
		tarjeta.setBackground(Color.WHITE);
		tarjeta.setBounds(x, y, ancho, alto);
		tarjeta.setLayout(null);
		tarjeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(tarjeta);

		JLabel lblIcono = new JLabel();
		lblIcono.setBounds(24, 24, 40, 40);
		colocarImagen(lblIcono, rutaIcono);
		tarjeta.add(lblIcono);

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setBounds(24, 76, ancho - 48, 26);
		tarjeta.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel(subtitulo);
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblSubtitulo.setForeground(new Color(130, 130, 130));
		lblSubtitulo.setBounds(24, 102, ancho - 48, 20);
		tarjeta.add(lblSubtitulo);

		JLabel lblVerReporte = new JLabel("Ver reporte");
		lblVerReporte.setFont(new Font("Calibri", Font.BOLD, 14));
		lblVerReporte.setForeground(new Color(255, 51, 51));
		lblVerReporte.setBounds(24, alto - 34, ancho - 48, 20);
		tarjeta.add(lblVerReporte);

		tarjeta.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mostrarReporte(indiceReporte, titulo);
			}
		});
	}

	private void mostrarReporte(int indiceReporte, String titulo) {
	    switch (indiceReporte) {
	        case 0:
	            ReporteOfertas dialogoOfertas = new ReporteOfertas();
	            dialogoOfertas.setModal(true);
	            dialogoOfertas.setVisible(true);
	            break;
	        case 1:
	            ReporteSolicitantes dialogoSolicitantes = new ReporteSolicitantes();
	            dialogoSolicitantes.setModal(true);
	            dialogoSolicitantes.setVisible(true);
	            break;
	        case 2:
	            ReporteEmpresas dialogoEmpresas = new ReporteEmpresas();
	            dialogoEmpresas.setModal(true);
	            dialogoEmpresas.setVisible(true);
	            break;
	        case 3:
	            ReporteActividadGeneral dialogoActividad = new ReporteActividadGeneral();
	            dialogoActividad.setModal(true);
	            dialogoActividad.setVisible(true);
	            break;
	        default:
	            VerGraficas verGraficas = new VerGraficas();
	            verGraficas.setVisible(true);
	            break;
	    }
	}

	private int contarTotalOfertas() {
		ArrayList<Oferta> lasOfertas = BolsaEmpleo.getInstancia().getOfertas();
		if (lasOfertas == null) {
			return 0;
		}
		return lasOfertas.size();
	}

	private int contarOfertasActivas() {
		ArrayList<Oferta> lasOfertas = BolsaEmpleo.getInstancia().getOfertas();
		if (lasOfertas == null) {
			return 0;
		}
		int contador = 0;
		for (Oferta oferta : lasOfertas) {
			if (oferta.getEstado() == EstadoOferta.PENDIENTE) {
				contador++;
			}
		}
		return contador;
	}

	private int contarTotalSolicitantes() {
		ArrayList<Persona> lasPersonas = BolsaEmpleo.getInstancia().getPersonas();
		if (lasPersonas == null) {
			return 0;
		}
		return lasPersonas.size();
	}

	private int contarSolicitantesDisponibles() {
		ArrayList<Persona> lasPersonas = BolsaEmpleo.getInstancia().getPersonas();
		if (lasPersonas == null) {
			return 0;
		}
		int contador = 0;
		for (Persona persona : lasPersonas) {
			if (!persona.isEstadoEmpleo()) {
				contador++;
			}
		}
		return contador;
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
	
	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		java.net.URL recurso = getClass().getResource(ruta);
		if (recurso == null) {
			return;
		}
	    ImageIcon icono = new ImageIcon(recurso);
	    Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	    boton.setIcon(new ImageIcon(imagenEscalada));
	}
	
	//metodo colocar img
	private void colocarImagen(JLabel label, String ruta) {
		java.net.URL recurso = getClass().getResource(ruta);
		if (recurso == null) {
			return;
		}
		ImageIcon icono = new ImageIcon(recurso);

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