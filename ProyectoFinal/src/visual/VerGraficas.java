package visual;

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
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.EstadoOferta;
import logico.EstadoSolicitud;
import logico.Oferta;
import logico.Persona;
import logico.SolicitudEmpleo;

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

public class VerGraficas extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

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
		setTitle("Ver Gr\u00E1ficas");
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
		int anchoContenido = dim.width - (margen * 2);

		PanelConSombra panelMenu = new PanelConSombra(25);
		panelMenu.setBackground(new Color(0, 0, 51));
		panelMenu.setBounds(40, 20, 1840, 82);
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

		JLabel lblTitulo = new JLabel("Ver Gr\u00E1ficas");
		lblTitulo.setForeground(new Color(255, 51, 51));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTitulo.setBounds(74, 22, 400, 30);
		panelMenu.add(lblTitulo);

		int y = 110;
		crearKpi(panel, 40, y, 420, 95, "Solicitantes empleados", contarSolicitantesEmpleados());
		crearKpi(panel, 480, y, 420, 95, "Empresas activas", contarEmpresasActivas());

		int yGraficas = 220;
		int gap = 20;
		int anchoTarjeta = (anchoContenido - (gap * 2)) / 3;
		int altoTarjeta = 340;

		PanelConSombra tarjeta1 = crearTarjeta(panel, 40, yGraficas, anchoTarjeta, altoTarjeta, "Top 5 empresas con mas ofertas");
		tarjeta1.add(crearChartPanel(graficoTopEmpresas(), anchoTarjeta - 30, 255), BorderLayout.CENTER);

		PanelConSombra tarjeta2 = crearTarjeta(panel, 60 + anchoTarjeta, yGraficas, anchoTarjeta, altoTarjeta, "Solicitudes del mes vs ofertas del mes");
		tarjeta2.add(crearChartPanel(graficoSolicitudesVsOfertasMes(), anchoTarjeta - 30, 255), BorderLayout.CENTER);

		PanelConSombra tarjeta3 = crearTarjeta(panel, 80 + (anchoTarjeta * 2), yGraficas, anchoTarjeta, altoTarjeta, "Solicitudes recibidas vs aceptadas");
		tarjeta3.add(crearChartPanel(graficoSolicitudesAceptadas(), anchoTarjeta - 30, 255), BorderLayout.CENTER);

		int y2 = 580;
		int anchoInferior = anchoContenido - 80;
		PanelConSombra tarjeta4 = crearTarjeta(panel, 40, y2, anchoInferior, 300, "% de hombres y mujeres empleados");
		tarjeta4.add(crearChartPanel(graficoGeneroEmpleados(), anchoInferior - 30, 215), BorderLayout.CENTER);
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

	private JFreeChart graficoTopEmpresas() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<String, Integer> top = topEmpresasOfertas(5);

		if (top.isEmpty()) {
			dataset.addValue(12, "Ofertas", "Constructora X");
			dataset.addValue(9, "Ofertas", "Tech RD");
			dataset.addValue(7, "Ofertas", "Servicios SRL");
			dataset.addValue(6, "Ofertas", "Global Soluciones");
			dataset.addValue(5, "Ofertas", "Grupo Norte");
		} else {
			for (Map.Entry<String, Integer> e : top.entrySet()) {
				dataset.addValue(e.getValue(), "Ofertas", e.getKey());
			}
		}

		JFreeChart chart = ChartFactory.createBarChart(
				null,
				"Empresa",
				"Ofertas",
				dataset,
				PlotOrientation.HORIZONTAL,
				false,
				true,
				false);

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

	private JFreeChart graficoSolicitudesVsOfertasMes() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int solicitudesMes = 0;
		int ofertasMes = 0;

		ArrayList<SolicitudEmpleo> solicitudes = BolsaEmpleo.getInstancia().getSolicitudes();
		ArrayList<Oferta> ofertas = BolsaEmpleo.getInstancia().getOfertas();

		if (solicitudes != null) {
			solicitudesMes = solicitudes.size();
		}
		if (ofertas != null) {
			ofertasMes = ofertas.size();
		}

		if (solicitudesMes == 0 && ofertasMes == 0) {
			solicitudesMes = 18;
			ofertasMes = 12;
		}

		dataset.addValue(solicitudesMes, "Cantidad", "Solicitudes");
		dataset.addValue(ofertasMes, "Cantidad", "Ofertas");

		JFreeChart chart = ChartFactory.createBarChart(
				null,
				"Tipo",
				"Cantidad",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				false);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(new Color(232, 232, 232));
		plot.setOutlineVisible(false);

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(102, 126, 234));
		renderer.setBarPainter(new StandardBarPainter());
		renderer.setDrawBarOutline(false);

		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	private JFreeChart graficoSolicitudesAceptadas() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		int recibidas = 0;
		int aceptadas = 0;

		ArrayList<SolicitudEmpleo> solicitudes = BolsaEmpleo.getInstancia().getSolicitudes();
		if (solicitudes != null && !solicitudes.isEmpty()) {
			recibidas = solicitudes.size();
			for (SolicitudEmpleo s : solicitudes) {
				if (s.getEstado() == EstadoSolicitud.CERRADA) {
					aceptadas++;
				}
			}
		} else {
			recibidas = 20;
			aceptadas = 8;
		}

		int restantes = Math.max(0, recibidas - aceptadas);
		dataset.setValue("Aceptadas", aceptadas);
		dataset.setValue("En proceso", restantes);

		JFreeChart chart = ChartFactory.createPieChart(null, dataset, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);
		plot.setLabelFont(new Font("Calibri", Font.PLAIN, 13));
		plot.setLabelBackgroundPaint(new Color(255, 255, 255, 0));
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);
		plot.setSectionPaint("Aceptadas", new Color(46, 125, 50));
		plot.setSectionPaint("En proceso", new Color(255, 183, 77));
		plot.setSimpleLabels(true);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	private JFreeChart graficoGeneroEmpleados() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		int hombres = 0;
		int mujeres = 0;

		ArrayList<Persona> personas = new ArrayList<Persona>(BolsaEmpleo.getInstancia().getPersonas());
		if (personas != null && !personas.isEmpty()) {
			for (Persona p : personas) {
				if (p.isEstadoEmpleo()) {
					String sexo = p.getSexo() != null ? p.getSexo().toString().toLowerCase() : "";
					if (sexo.contains("m") && !sexo.contains("f")) {
						hombres++;
					} else if (sexo.contains("f")) {
						mujeres++;
					}
				}
			}
		} else {
			hombres = 6;
			mujeres = 5;
		}

		if (hombres == 0 && mujeres == 0) {
			hombres = 1;
			mujeres = 1;
		}

		dataset.setValue("Hombres", hombres);
		dataset.setValue("Mujeres", mujeres);

		JFreeChart chart = ChartFactory.createPieChart(null, dataset, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setOutlineVisible(false);
		plot.setLabelFont(new Font("Calibri", Font.PLAIN, 13));
		plot.setLabelBackgroundPaint(new Color(255, 255, 255, 0));
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);
		plot.setSectionPaint("Hombres", new Color(102, 126, 234));
		plot.setSectionPaint("Mujeres", new Color(132, 169, 255));
		plot.setSimpleLabels(true);
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	private Map<String, Integer> topEmpresasOfertas(int limite) {
		Map<String, Integer> mapa = new LinkedHashMap<String, Integer>();
		ArrayList<Oferta> ofertas = BolsaEmpleo.getInstancia().getOfertas();
		if (ofertas == null || ofertas.isEmpty()) {
			return mapa;
		}

		Map<String, Integer> conteo = new LinkedHashMap<String, Integer>();
		for (Oferta o : ofertas) {
			String nombre = o.getEmpresa() != null ? o.getEmpresa().getNombre() : "Sin empresa";
			conteo.put(nombre, conteo.getOrDefault(nombre, 0) + 1);
		}

		List<Map.Entry<String, Integer>> lista = new ArrayList<Map.Entry<String, Integer>>(conteo.entrySet());
		lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));

		int max = Math.min(limite, lista.size());
		for (int i = 0; i < max; i++) {
			Map.Entry<String, Integer> e = lista.get(i);
			mapa.put(e.getKey(), e.getValue());
		}
		return mapa;
	}

	private int contarSolicitantesEmpleados() {
		int contador = 0;
		for (Persona p : BolsaEmpleo.getInstancia().getPersonas()) {
			if (p.isEstadoEmpleo()) {
				contador++;
			}
		}
		return contador;
	}

	private int contarEmpresasActivas() {
		int contador = 0;
		for (Oferta o : BolsaEmpleo.getInstancia().getOfertas()) {
			if (o.getEstado() == EstadoOferta.PENDIENTE || o.getEstado() == EstadoOferta.COMPLETADA) {
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
		label.setIcon(new ImageIcon(imagenEscalada));
		label.setText("");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
	}
}