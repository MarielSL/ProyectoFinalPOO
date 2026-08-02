package visual;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.SystemColor;
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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;

import logico.BolsaEmpleo;
import logico.EstadoOferta;
import logico.Oferta;
import logico.Persona;
import logico.SolicitudEmpleo;
import logico.Usuario;
import red.ConexionCliente;
import red.DatosGraficasCandidato;
import red.DatosEstadisticasCandidato;
import red.Peticion;
import red.Respuesta;

public class HomeCandidato extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private Dimension dim;
	private Persona candidato;
	private JLabel lblEstadoBusquedaValor;
	private JLabel lblOfertasDisponiblesValor;
	private JLabel lblMayorCoincidenciaValor;
	private JPanel panel_Grafica1;
	private JPanel panel_Grafica2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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

	public HomeCandidato() {
		Usuario usuarioLogin = BolsaEmpleo.getInstancia().getLoginUser();

		if (usuarioLogin != null) {
			candidato = usuarioLogin.getPersona();
		}

		setTitle("Home Candidato");
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		dim = getToolkit().getScreenSize();

		setSize(dim.width, dim.height - 55);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		layeredPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		layeredPane.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(245, 245, 245));
		panel.setLayout(null);

		int margen = 40;
		int anchoContenido = dim.width - (margen * 2);

		construirMenu(panel, anchoContenido);

		int anchoTarjeta = (anchoContenido - 48) / 3;

		construirTarjetaEstadoBusqueda(panel, margen, anchoTarjeta);
		construirTarjetaOfertasDisponibles(panel, margen, anchoTarjeta);
		construirTarjetaMayorCoincidencia(panel, margen, anchoTarjeta);
		construirPanelGraficos(panel, margen, anchoContenido);

		cargarDatosHomeConHilo();
		cargarGraficasConHilo();
	}

	private void construirMenu(JPanel panel, int anchoContenido) {
		PanelRedond panelMenu = new PanelRedond(25);
		panelMenu.setBackground(new Color(0, 0, 51));
		panelMenu.setBounds(26, 20, anchoContenido, 70);
		panel.add(panelMenu);
		panelMenu.setLayout(null);

		String nombreCandidato = "Nombre";
		if (candidato != null && candidato.getNombre() != null) {
			nombreCandidato = candidato.getNombre();
		}

		int anchoNombre = 14 * nombreCandidato.length() + 20;

		JLabel lblNombreCandidato = new JLabel(nombreCandidato);
		lblNombreCandidato.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNombreCandidato.setForeground(Color.WHITE);
		lblNombreCandidato.setBounds(anchoContenido - anchoNombre - 120, 25, anchoNombre, 25);
		panelMenu.add(lblNombreCandidato);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(anchoContenido - 100, -9, 114, 88);
		colocarImagen(lblLogo, "/img/iconoLogo_FondoOscuro.png");
		panelMenu.add(lblLogo);

		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblInicio.setForeground(Color.WHITE);
		lblInicio.setBounds(680, 26, 70, 20);
		panelMenu.add(lblInicio);

		JLabel lblVerOfertas = new JLabel("Ver ofertas");
		lblVerOfertas.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVerOfertas.setForeground(Color.WHITE);
		lblVerOfertas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblVerOfertas.setBounds(804, 26, 82, 20);
		lblVerOfertas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirVerOfertas();
			}
		});
		panelMenu.add(lblVerOfertas);

		JLabel lblMiSolicitud = new JLabel("Mi Solicitud Laboral");
		lblMiSolicitud.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblMiSolicitud.setForeground(Color.WHITE);
		lblMiSolicitud.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblMiSolicitud.setBounds(938, 26, 170, 20);
		lblMiSolicitud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario usuario = BolsaEmpleo.getInstancia().getLoginUser();
				if (usuario == null || usuario.getPersona() == null || usuario.getPersona().getSolicitud() == null) {
					SolicitudVacia ventana = new SolicitudVacia();
					ventana.setVisible(true);
					dispose();
				} else {
					abrirMiSolicitudLaboral();
				}
			}
		});
		panelMenu.add(lblMiSolicitud);

		JLabel lblVerPerfil = new JLabel("Ver perfil");
		lblVerPerfil.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVerPerfil.setForeground(Color.WHITE);
		lblVerPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblVerPerfil.setBounds(1130, 26, 90, 20);
		lblVerPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirVerPerfil();
			}
		});
		panelMenu.add(lblVerPerfil);

		BotonRedond btnMenu = new BotonRedond("", 25);
		btnMenu.setBackground(new Color(0, 0, 51));
		btnMenu.setColorHover(new Color(0, 51, 102));
		btnMenu.setBounds(12, 4, 60, 60);
		btnMenu.setMargin(new Insets(0, 0, 0, 0));
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setFocusPainted(false);
		btnMenu.setOpaque(false);
		colocarIconoBoton(btnMenu, "/img/menu-dots-vertical(White).png", 25, 25);
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarraSolicitante menu = new BarraSolicitante();
				menu.setVisible(true);
				dispose();
			}
		});
		panelMenu.add(btnMenu);
	}

	private void construirTarjetaEstadoBusqueda(JPanel panel, int margen, int anchoTarjeta) {
		PanelConSombra panelEstadoBusqueda = new PanelConSombra(18);
		panelEstadoBusqueda.setBackground(new Color(255, 224, 178));
		panelEstadoBusqueda.setBounds(margen, 110, anchoTarjeta, 90);
		panelEstadoBusqueda.setLayout(null);
		panel.add(panelEstadoBusqueda);

		JLabel lblEstadoBusqueda = new JLabel("Estado de Busqueda");
		lblEstadoBusqueda.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblEstadoBusqueda.setForeground(new Color(204, 102, 0));
		lblEstadoBusqueda.setBounds(20, 14, anchoTarjeta - 40, 20);
		panelEstadoBusqueda.add(lblEstadoBusqueda);

		lblEstadoBusquedaValor = new JLabel("...");
		lblEstadoBusquedaValor.setFont(new Font("Calibri", Font.BOLD, 26));
		lblEstadoBusquedaValor.setForeground(new Color(204, 102, 0));
		lblEstadoBusquedaValor.setBounds(20, 38, anchoTarjeta - 40, 36);
		panelEstadoBusqueda.add(lblEstadoBusquedaValor);

		lblEstadoBusquedaValor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblEstadoBusquedaValor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ("Por Crear".equals(lblEstadoBusquedaValor.getText())) {
					RegSolicitud registrar = new RegSolicitud(null);
					registrar.setVisible(true);
					dispose();
				}
			}
		});
	}

	private void construirTarjetaOfertasDisponibles(JPanel panel, int margen, int anchoTarjeta) {
		PanelConSombra panelOfertasDisponibles = new PanelConSombra(18);
		panelOfertasDisponibles.setBackground(new Color(195, 220, 255));
		panelOfertasDisponibles.setBounds(margen + anchoTarjeta + 24, 110, anchoTarjeta, 90);
		panelOfertasDisponibles.setLayout(null);
		panel.add(panelOfertasDisponibles);

		JLabel lblOfertasDisponibles = new JLabel("Ofertas Disponibles");
		lblOfertasDisponibles.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblOfertasDisponibles.setForeground(new Color(65, 95, 170));
		lblOfertasDisponibles.setBounds(20, 14, anchoTarjeta - 40, 20);
		panelOfertasDisponibles.add(lblOfertasDisponibles);

		lblOfertasDisponiblesValor = new JLabel("...");
		lblOfertasDisponiblesValor.setFont(new Font("Calibri", Font.BOLD, 30));
		lblOfertasDisponiblesValor.setForeground(new Color(65, 95, 170));
		lblOfertasDisponiblesValor.setBounds(20, 38, anchoTarjeta - 40, 36);
		panelOfertasDisponibles.add(lblOfertasDisponiblesValor);
	}

	private void construirTarjetaMayorCoincidencia(JPanel panel, int margen, int anchoTarjeta) {
		PanelConSombra panelMayorCoincidencia = new PanelConSombra(18);
		panelMayorCoincidencia.setBackground(new Color(198, 239, 206));
		panelMayorCoincidencia.setBounds(margen + (anchoTarjeta + 24) * 2, 110, anchoTarjeta, 90);
		panelMayorCoincidencia.setLayout(null);
		panel.add(panelMayorCoincidencia);

		JLabel lblMayorCoincidencia = new JLabel("Mayor Coincidencia");
		lblMayorCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblMayorCoincidencia.setForeground(new Color(46, 125, 50));
		lblMayorCoincidencia.setBounds(20, 14, anchoTarjeta - 40, 20);
		panelMayorCoincidencia.add(lblMayorCoincidencia);

		lblMayorCoincidenciaValor = new JLabel("...");
		lblMayorCoincidenciaValor.setFont(new Font("Calibri", Font.BOLD, 30));
		lblMayorCoincidenciaValor.setForeground(new Color(46, 125, 50));
		lblMayorCoincidenciaValor.setBounds(20, 38, anchoTarjeta - 40, 36);
		panelMayorCoincidencia.add(lblMayorCoincidenciaValor);
	}

	private void construirPanelGraficos(JPanel panel, int margen, int anchoContenido) {
		JLabel lblTituloGraficos = new JLabel("Oportunidades Destacadas");
		lblTituloGraficos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloGraficos.setForeground(new Color(0, 0, 51));
		lblTituloGraficos.setFont(new Font("Calibri", Font.BOLD, 26));
		lblTituloGraficos.setBounds(margen, 233, anchoContenido, 25);
		panel.add(lblTituloGraficos);

		PanelConSombra panelGraficos = new PanelConSombra(20);
		panelGraficos.setBackground(Color.WHITE);
		panelGraficos.setBounds(26, 262, anchoContenido, dim.height - 320);
		panelGraficos.setLayout(null);
		panel.add(panelGraficos);

		int mitad = anchoContenido / 2;

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(SystemColor.controlShadow);
		separator.setBackground(SystemColor.controlShadow);
		separator.setBounds(mitad, 67, 1, Math.max(300, dim.height - 450));
		panelGraficos.add(separator);

		panel_Grafica1 = new JPanel();
		panel_Grafica1.setBounds(149, 105, 550, 550);
		panelGraficos.add(panel_Grafica1);

		panel_Grafica2 = new JPanel();
		panel_Grafica2.setBounds(1050, 105, 550, 550);
		panelGraficos.add(panel_Grafica2);
	}

	private void cargarDatosHomeConHilo() {
		lblEstadoBusquedaValor.setText("...");
		lblOfertasDisponiblesValor.setText("...");
		lblMayorCoincidenciaValor.setText("...");

		SwingWorker<DatosEstadisticasCandidato, Void> hilo = new SwingWorker<DatosEstadisticasCandidato, Void>() {
			@Override
			protected DatosEstadisticasCandidato doInBackground() throws Exception {
				Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_ESTADISTICAS_CANDIDATO, null);
				Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);
				if (!respuesta.isExito()) {
					throw new IllegalArgumentException(respuesta.getDatos().toString());
				}
				return (DatosEstadisticasCandidato) respuesta.getDatos();
			}

			@Override
			protected void done() {
				try {
					DatosEstadisticasCandidato datos = get();
					lblEstadoBusquedaValor.setText(datos.getEstadoBusqueda());
					lblOfertasDisponiblesValor.setText(String.valueOf(datos.getOfertasDisponibles()));
					lblMayorCoincidenciaValor.setText(String.format("%.2f%%", datos.getMayorCoincidencia()));
				} catch (Exception e) {
					Throwable causa = e.getCause();
					String mensaje = causa != null ? causa.getMessage() : e.getMessage();
					e.printStackTrace();
					lblEstadoBusquedaValor.setText("No disponible");
					lblOfertasDisponiblesValor.setText("0");
					lblMayorCoincidenciaValor.setText("0%");
					JOptionPane.showMessageDialog(HomeCandidato.this, mensaje != null ? mensaje : "No se pudieron cargar los datos del inicio.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		hilo.execute();
	}

	private void cargarGraficasConHilo() {
		SwingWorker<DatosGraficasCandidato, Void> hilo = new SwingWorker<DatosGraficasCandidato, Void>() {
			@Override
			protected DatosGraficasCandidato doInBackground() throws Exception {
				Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_GRAFICAS_CANDIDATO, null);
				Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);
				if (!respuesta.isExito()) {
					throw new IllegalArgumentException(respuesta.getDatos().toString());
				}
				return (DatosGraficasCandidato) respuesta.getDatos();
			}

			@Override
			protected void done() {
				try {
					DatosGraficasCandidato datos = get();
					dibujarGraficaTopOfertas(panel_Grafica1, datos);
					dibujarGraficaCategoriasLaborales(panel_Grafica2, datos);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(HomeCandidato.this, "No se pudieron cargar las graficas.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		hilo.execute();
	}

	private void dibujarGraficaTopOfertas(JPanel panel, DatosGraficasCandidato datos) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (datos.getOfertasTop() != null && datos.getPorcentajesTop() != null) {
			for (int i = 0; i < datos.getOfertasTop().size() && i < datos.getPorcentajesTop().size(); i++) {
				dataset.addValue(datos.getPorcentajesTop().get(i), "Porcentaje", datos.getOfertasTop().get(i));
			}
		}

		JFreeChart grafica = ChartFactory.createBarChart(
				"Top 5 ofertas",
				"Oferta",
				"%",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				false);

		personalizarGraficaBarrasPorcentaje(grafica);

		ChartPanel graficaPanel = new ChartPanel(grafica);
		graficaPanel.setMouseWheelEnabled(true);
		graficaPanel.setPopupMenu(null);

		panel.removeAll();
		panel.setLayout(new BorderLayout());
		panel.add(graficaPanel, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
	}

	private void dibujarGraficaCategoriasLaborales(JPanel panel, DatosGraficasCandidato datos) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (datos.getCategoriasLaborales() != null && datos.getCantidadPorCategoria() != null) {
			for (int i = 0; i < datos.getCategoriasLaborales().size() && i < datos.getCantidadPorCategoria().size(); i++) {
				dataset.addValue(datos.getCantidadPorCategoria().get(i), "Ofertas", datos.getCategoriasLaborales().get(i));
			}
		}

		JFreeChart grafica = ChartFactory.createBarChart(
				"Ofertas disponibles por categoria laboral",
				"Categoria",
				"Cantidad",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				false);

		personalizarGraficaBarrasCantidad(grafica);

		ChartPanel graficaPanel = new ChartPanel(grafica);
		graficaPanel.setMouseWheelEnabled(true);
		graficaPanel.setPopupMenu(null);

		panel.removeAll();
		panel.setLayout(new BorderLayout());
		panel.add(graficaPanel, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
	}

	private void personalizarGraficaBarrasPorcentaje(JFreeChart grafica) {
		Color colorTexto = Color.decode("#06002c");
		Color colorBarras = Color.decode("#4769ba");
		Color colorFondo = Color.WHITE;
		Color colorLineas = new Color(225, 225, 225);

		grafica.setBackgroundPaint(colorFondo);
		grafica.setPadding(new RectangleInsets(10, 10, 10, 10));
		grafica.getTitle().setFont(new Font("Calibri", Font.BOLD, 22));
		grafica.getTitle().setPaint(colorTexto);

		CategoryPlot plot = grafica.getCategoryPlot();
		plot.setBackgroundPaint(colorFondo);
		plot.setOutlineVisible(false);
		plot.setRangeGridlinePaint(colorLineas);
		plot.setRangeGridlineStroke(new BasicStroke(1f));

		plot.getDomainAxis().setLabelFont(new Font("Calibri", Font.BOLD, 15));
		plot.getDomainAxis().setLabelPaint(colorTexto);
		plot.getDomainAxis().setTickLabelFont(new Font("Calibri", Font.PLAIN, 13));
		plot.getDomainAxis().setTickLabelPaint(colorTexto);

		plot.getRangeAxis().setLabelFont(new Font("Calibri", Font.BOLD, 15));
		plot.getRangeAxis().setLabelPaint(colorTexto);
		plot.getRangeAxis().setTickLabelFont(new Font("Calibri", Font.PLAIN, 13));
		plot.getRangeAxis().setTickLabelPaint(colorTexto);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, colorBarras);
		renderer.setDrawBarOutline(false);
		renderer.setMaximumBarWidth(0.10);
		renderer.setItemMargin(0.15);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(new Font("Calibri", Font.BOLD, 13));
		renderer.setBaseItemLabelPaint(colorTexto);

		if (plot.getRangeAxis() instanceof NumberAxis) {
			NumberAxis ejeCantidad = (NumberAxis) plot.getRangeAxis();
			ejeCantidad.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			ejeCantidad.setLowerBound(0);
		}
	}

	private void personalizarGraficaBarrasCantidad(JFreeChart grafica) {
		Color colorTexto = Color.decode("#06002c");
		Color colorBarras = Color.decode("#fe9703");
		Color colorFondo = Color.WHITE;
		Color colorLineas = new Color(225, 225, 225);

		grafica.setBackgroundPaint(colorFondo);
		grafica.setPadding(new RectangleInsets(10, 10, 10, 10));
		grafica.getTitle().setFont(new Font("Calibri", Font.BOLD, 22));
		grafica.getTitle().setPaint(colorTexto);

		CategoryPlot plot = grafica.getCategoryPlot();
		plot.setBackgroundPaint(colorFondo);
		plot.setOutlineVisible(false);
		plot.setRangeGridlinePaint(colorLineas);
		plot.setRangeGridlineStroke(new BasicStroke(1f));

		plot.getDomainAxis().setLabelFont(new Font("Calibri", Font.BOLD, 15));
		plot.getDomainAxis().setLabelPaint(colorTexto);
		plot.getDomainAxis().setTickLabelFont(new Font("Calibri", Font.PLAIN, 13));
		plot.getDomainAxis().setTickLabelPaint(colorTexto);

		plot.getRangeAxis().setLabelFont(new Font("Calibri", Font.BOLD, 15));
		plot.getRangeAxis().setLabelPaint(colorTexto);
		plot.getRangeAxis().setTickLabelFont(new Font("Calibri", Font.PLAIN, 13));
		plot.getRangeAxis().setTickLabelPaint(colorTexto);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, colorBarras);
		renderer.setDrawBarOutline(false);
		renderer.setMaximumBarWidth(0.10);
		renderer.setItemMargin(0.15);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(new Font("Calibri", Font.BOLD, 13));
		renderer.setBaseItemLabelPaint(colorTexto);

		if (plot.getRangeAxis() instanceof NumberAxis) {
			NumberAxis ejeCantidad = (NumberAxis) plot.getRangeAxis();
			ejeCantidad.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			ejeCantidad.setLowerBound(0);
		}
	}

	private void abrirVerOfertas() {
		VerOfertasCandidato frame = new VerOfertasCandidato();
		frame.setVisible(true);
		dispose();
	}

	private void abrirMiSolicitudLaboral() {
		VerMiSolicitudLaboral frame = new VerMiSolicitudLaboral();
		frame.setVisible(true);
		dispose();
	}

	private void abrirVerPerfil() {
		VerUserSolicitante frame = new VerUserSolicitante();
		frame.setVisible(true);
		dispose();
	}

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		if (boton == null || ruta == null) {
			return;
		}

		java.net.URL recurso = getClass().getResource(ruta);
		if (recurso == null) {
			return;
		}

		ImageIcon icono = new ImageIcon(recurso);
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagenEscalada));
	}

	private void colocarImagen(JLabel label, String ruta) {
		if (label == null || ruta == null) {
			return;
		}

		java.net.URL recurso = getClass().getResource(ruta);
		if (recurso == null) {
			return;
		}

		ImageIcon icono = new ImageIcon(recurso);

		int anchoLabel = label.getWidth();
		int altoLabel = label.getHeight();
		int anchoImagen = icono.getIconWidth();
		int altoImagen = icono.getIconHeight();

		if (anchoLabel <= 0 || altoLabel <= 0 || anchoImagen <= 0 || altoImagen <= 0) {
			return;
		}

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
}