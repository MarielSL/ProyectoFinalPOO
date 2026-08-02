package visual;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;

import red.ConexionCliente;
import red.DatosGraficasAdmin;
import red.Peticion;
import red.Respuesta;

public class VerGraficas extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private Dimension dim;
	private JPanel panelContenido;
	private int anchoContenido;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerGraficas frame = new VerGraficas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerGraficas() {
		setTitle("Ver Gráficas");
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 55);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setLayout(null);
		layeredPane.add(panel, BorderLayout.CENTER);

		int margen = 40;
		int anchoContenidoLocal = dim.width - (margen * 2);
		this.anchoContenido = anchoContenidoLocal;

		PanelConSombra panelMenu = new PanelConSombra(25);
		panelMenu.setBackground(new Color(0, 0, 51));
		panelMenu.setBounds(40, 20, anchoContenidoLocal, 82);
		panel.add(panelMenu);
		panelMenu.setLayout(null);

		BotonRedond btnAtras = new BotonRedond("", 18);
		btnAtras.setBackground(new Color(0, 0, 51));
		btnAtras.setBounds(20, 12, 46, 46);
		colocarIconoBoton(btnAtras, "/img/menu-dots-vertical(White).png", 25, 25);
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setFocusPainted(false);
		btnAtras.setOpaque(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerReportesAdmin reportes = new VerReportesAdmin();
				reportes.setVisible(true);
				dispose();
			}
		});
		panelMenu.add(btnAtras);

		JLabel lblTitulo = new JLabel("Ver Gráficas");
		lblTitulo.setForeground(new Color(255, 51, 51));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTitulo.setBounds(74, 22, 400, 30);
		panelMenu.add(lblTitulo);

		panelContenido = panel;
		cargarGraficasConHilo();
	}

	private void cargarGraficasConHilo() {
		SwingWorker<DatosGraficasAdmin, Void> hilo = new SwingWorker<DatosGraficasAdmin, Void>() {

			@Override
			protected DatosGraficasAdmin doInBackground() throws Exception {
				Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_GRAFICAS_ADMIN, null);
				Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

				if (!respuesta.isExito()) {
					throw new IllegalArgumentException(respuesta.getDatos().toString());
				}

				return (DatosGraficasAdmin) respuesta.getDatos();
			}

			@Override
			protected void done() {
				try {
					DatosGraficasAdmin datos = get();
					dibujarPantalla(datos);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(VerGraficas.this, "No se pudieron cargar las gráficas.", "Error", JOptionPane.ERROR_MESSAGE);
					dibujarPantallaVacia();
				}
			}
		};

		hilo.execute();
	}

	private void dibujarPantallaVacia() {
		dibujarPantalla(null);
	}

	private void dibujarPantalla(DatosGraficasAdmin datos) {
		panelContenido.removeAll();

		int y = 110;
		int tarjetaKpiW = 420;
		int tarjetaKpiH = 95;
		int gapKpi = 20;

		crearKpi(panelContenido, 40, y, tarjetaKpiW, tarjetaKpiH, "Solicitantes empleados", datos != null ? datos.getSolicitantesEmpleados() : 0);
		crearKpi(panelContenido, 40 + tarjetaKpiW + gapKpi, y, tarjetaKpiW, tarjetaKpiH, "Empresas activas", datos != null ? datos.getEmpresasActivas() : 0);

		int yGraficas = 220;
		int gap = 20;
		int anchoTarjeta = (anchoContenido - (gap * 2)) / 3;
		int altoTarjeta = 340;

		PanelConSombra tarjeta1 = crearTarjeta(panelContenido, 40, yGraficas, anchoTarjeta, altoTarjeta, "Top 5 empresas con más ofertas");
		tarjeta1.add(crearChartPanel(graficoTopEmpresas(datos), anchoTarjeta - 30, 255), BorderLayout.CENTER);

		PanelConSombra tarjeta2 = crearTarjeta(panelContenido, 40 + anchoTarjeta + gap, yGraficas, anchoTarjeta, altoTarjeta, "Actividad mensual de la plataforma");
		tarjeta2.add(crearChartPanel(graficoActividadMensual(datos), anchoTarjeta - 30, 255), BorderLayout.CENTER);

		PanelConSombra tarjeta3 = crearTarjeta(panelContenido, 40 + (anchoTarjeta + gap) * 2, yGraficas, anchoTarjeta, altoTarjeta, "Estado de las decisiones de candidatos");
		tarjeta3.add(crearChartPanel(graficoEstadoDecisiones(datos), anchoTarjeta - 30, 255), BorderLayout.CENTER);

		int y2 = 580;
		int anchoInferior = anchoContenido - 80;

		PanelConSombra tarjeta4 = crearTarjeta(panelContenido, 40, y2, anchoInferior, 300, "Oferta vs. demanda por área laboral");
		tarjeta4.add(crearChartPanel(graficoOfertaVsDemandaArea(datos), anchoInferior - 30, 215), BorderLayout.CENTER);

		int y3 = 900;
		PanelConSombra tarjeta5 = crearTarjeta(panelContenido, 40, y3, anchoInferior, 300, "Distribución de coincidencias");
		tarjeta5.add(crearChartPanel(graficoDistribucionCoincidencias(datos), anchoInferior - 30, 215), BorderLayout.CENTER);

		panelContenido.revalidate();
		panelContenido.repaint();
	}

	private void crearKpi(JPanel padre, int x, int y, int ancho, int alto, String titulo, int valor) {
		PanelConSombra card = new PanelConSombra(18);
		card.setBackground(Color.WHITE);
		card.setBounds(x, y, ancho, alto);
		card.setLayout(null);
		padre.add(card);

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 17));
		lblTitulo.setBounds(20, 14, ancho - 40, 20);
		card.add(lblTitulo);

		JLabel lblValor = new JLabel(String.valueOf(valor));
		lblValor.setForeground(new Color(0, 0, 51));
		lblValor.setFont(new Font("Calibri", Font.BOLD, 34));
		lblValor.setBounds(20, 40, ancho - 40, 35);
		card.add(lblValor);
	}

	private PanelConSombra crearTarjeta(JPanel padre, int x, int y, int ancho, int alto, String titulo) {
		PanelConSombra tarjeta = new PanelConSombra(20);
		tarjeta.setBackground(Color.WHITE);
		tarjeta.setBounds(x, y, ancho, alto);
		tarjeta.setLayout(new BorderLayout());
		padre.add(tarjeta);

		JPanel header = new JPanel();
		header.setOpaque(false);
		header.setLayout(null);
		header.setPreferredSize(new Dimension(ancho, 48));

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setBounds(18, 12, ancho - 36, 24);
		header.add(lblTitulo);

		tarjeta.add(header, BorderLayout.NORTH);

		JPanel body = new JPanel();
		body.setOpaque(false);
		body.setLayout(new BorderLayout());
		tarjeta.add(body, BorderLayout.CENTER);

		return tarjeta;
	}

	private ChartPanel crearChartPanel(JFreeChart chart, int ancho, int alto) {
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(ancho, alto));
		panel.setMouseWheelEnabled(true);
		panel.setDomainZoomable(false);
		panel.setRangeZoomable(false);
		panel.setPopupMenu(null);
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		return panel;
	}

	private JFreeChart graficoTopEmpresas(DatosGraficasAdmin datos) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (datos != null && datos.getNombresEmpresasTop() != null && !datos.getNombresEmpresasTop().isEmpty()) {
			for (int i = 0; i < datos.getNombresEmpresasTop().size() && i < datos.getOfertasPorEmpresaTop().size(); i++) {
				dataset.addValue(datos.getOfertasPorEmpresaTop().get(i), "Ofertas", datos.getNombresEmpresasTop().get(i));
			}
		} else {
			dataset.addValue(12, "Ofertas", "Constructora X");
			dataset.addValue(9, "Ofertas", "Tech RD");
			dataset.addValue(7, "Ofertas", "Servicios SRL");
			dataset.addValue(6, "Ofertas", "Global Soluciones");
			dataset.addValue(5, "Ofertas", "Grupo Norte");
		}

		JFreeChart chart = ChartFactory.createBarChart(null, "Empresa", "Ofertas", dataset, PlotOrientation.HORIZONTAL, false, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(new Color(232, 232, 232));
		plot.setOutlineVisible(false);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(0, 102, 153));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setDrawBarOutline(false);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	private JFreeChart graficoActividadMensual(DatosGraficasAdmin datos) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int solicitudesMes = datos != null ? datos.getSolicitudesMes() : 0;
		int ofertasMes = datos != null ? datos.getOfertasMes() : 0;

		if (solicitudesMes == 0 && ofertasMes == 0) {
			solicitudesMes = 18;
			ofertasMes = 12;
		}

		dataset.addValue(solicitudesMes, "Cantidad", "Solicitudes");
		dataset.addValue(ofertasMes, "Cantidad", "Ofertas");

		JFreeChart chart = ChartFactory.createBarChart(null, "Tipo", "Cantidad", dataset, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(new Color(232, 232, 232));
		plot.setOutlineVisible(false);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(102, 126, 234));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setDrawBarOutline(false);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	private JFreeChart graficoEstadoDecisiones(DatosGraficasAdmin datos) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int recibidas = datos != null ? datos.getSolicitudesRecibidas() : 0;
		int aceptadas = datos != null ? datos.getSolicitudesAceptadas() : 0;

		if (recibidas == 0 && aceptadas == 0) {
			recibidas = 20;
			aceptadas = 8;
		}

		int rechazadas = Math.max(0, recibidas - aceptadas);

		dataset.setValue("Pendientes", Math.max(0, recibidas - aceptadas - rechazadas));
		dataset.setValue("Contratados", aceptadas);
		dataset.setValue("Rechazados", rechazadas);

		JFreeChart chart = ChartFactory.createPieChart(null, dataset, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);
		plot.setLabelFont(new Font("Calibri", Font.PLAIN, 13));
		plot.setLabelBackgroundPaint(new Color(255, 255, 255, 0));
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);
		plot.setSectionPaint("Pendientes", new Color(255, 183, 77));
		plot.setSectionPaint("Contratados", new Color(46, 125, 50));
		plot.setSectionPaint("Rechazados", new Color(198, 40, 40));
		plot.setSimpleLabels(true);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	private JFreeChart graficoOfertaVsDemandaArea(DatosGraficasAdmin datos) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(5, "Ofertas", "Tecnología");
		dataset.addValue(9, "Solicitudes", "Tecnología");
		dataset.addValue(3, "Ofertas", "Salud");
		dataset.addValue(2, "Solicitudes", "Salud");
		dataset.addValue(2, "Ofertas", "Administración");
		dataset.addValue(7, "Solicitudes", "Administración");

		JFreeChart chart = ChartFactory.createBarChart(null, "Área laboral", "Cantidad", dataset, PlotOrientation.HORIZONTAL, true, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(new Color(232, 232, 232));
		plot.setOutlineVisible(false);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(0, 102, 153));
		renderer.setSeriesPaint(1, new Color(255, 183, 77));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setDrawBarOutline(false);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	private JFreeChart graficoDistribucionCoincidencias(DatosGraficasAdmin datos) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(12, "Coincidencias", "0-39% Baja");
		dataset.addValue(23, "Coincidencias", "40-59% Media");
		dataset.addValue(18, "Coincidencias", "60-79% Buena");
		dataset.addValue(9, "Coincidencias", "80-100% Alta");

		JFreeChart chart = ChartFactory.createBarChart(null, "Rango", "Cantidad", dataset, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(new Color(232, 232, 232));
		plot.setOutlineVisible(false);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(76, 175, 80));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setDrawBarOutline(false);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		chart.setBackgroundPaint(Color.WHITE);
		return chart;
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
}