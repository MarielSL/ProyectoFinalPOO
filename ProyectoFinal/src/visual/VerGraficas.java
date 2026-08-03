package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import red.ConexionCliente;
import red.DatosGraficasAdmin;
import red.Peticion;
import red.Respuesta;
import javax.swing.SwingConstants;

public class VerGraficas extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel panelMenu;
    private JPanel panelContenido;

    private Dimension dim;
    private int anchoContenido;

    private JLabel lblSolicitantesEmpleados;
    private JLabel lblEmpresasActivas;

    private DefaultCategoryDataset datasetTopEmpresas;
    private DefaultCategoryDataset datasetActividadMensual;
    private DefaultPieDataset datasetEstadoDecisiones;
    private DefaultCategoryDataset datasetOfertaVsDemanda;
    private DefaultCategoryDataset datasetCoincidencias;
    private BotonRedond btnVolver;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VerGraficas frame = new VerGraficas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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

        anchoContenido = dim.width - 80;

        construirInterfaz();
        cargarGraficasConHilo();
    }

    private void construirInterfaz() {
        construirMenu();
        construirPanelContenido();
    }

    private void construirMenu() {
        panelMenu = new PanelConSombra(25);
        panelMenu.setBackground(new Color(0, 0, 51));
        panelMenu.setPreferredSize(new Dimension(anchoContenido, 82));
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

        JLabel lblTitulo = new JLabel("Ver Gráficas");
        lblTitulo.setForeground(new Color(255, 51, 51));
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 30));
        lblTitulo.setBounds(74, 22, 400, 30);

        panelMenu.add(btnAtras);
        panelMenu.add(lblTitulo);

        contentPane.add(panelMenu, BorderLayout.NORTH);
    }

    private void construirPanelContenido() {
        panelContenido = new JPanel();
        panelContenido.setBackground(new Color(245, 245, 245));
        panelContenido.setLayout(null);
        contentPane.add(panelContenido, BorderLayout.CENTER);

        construirKPIs();
        construirGraficas();
    }

    private void construirKPIs() {
        int y = 20;
        int tarjetaKpiW = (anchoContenido - 100) / 2;
        int tarjetaKpiH = 95;
        int gapKpi = 20;

        lblSolicitantesEmpleados = crearKpi(panelContenido, 40, y, tarjetaKpiW, tarjetaKpiH, "Solicitantes empleados", "0");
        lblEmpresasActivas = crearKpi(panelContenido, 40 + tarjetaKpiW + gapKpi, y, tarjetaKpiW, tarjetaKpiH, "Empresas activas", "0");
    }

    private void construirGraficas() {
        int yInicio = 140;
        int gap = 20;

        int ancho3 = (anchoContenido - 80 - (gap * 2)) / 3;
        int altoTarjeta = 340;

        PanelConSombra tarjeta1 = crearTarjeta(panelContenido, 40, yInicio, ancho3, altoTarjeta, "Top 5 empresas con más ofertas");
        datasetTopEmpresas = new DefaultCategoryDataset();
        tarjeta1.add(crearChartPanel(graficoTopEmpresas(datasetTopEmpresas), ancho3 - 30, 250), BorderLayout.CENTER);

        PanelConSombra tarjeta2 = crearTarjeta(panelContenido, 40 + ancho3 + gap, yInicio, ancho3, altoTarjeta, "Actividad mensual de la plataforma");
        datasetActividadMensual = new DefaultCategoryDataset();
        tarjeta2.add(crearChartPanel(graficoActividadMensual(datasetActividadMensual), ancho3 - 30, 250), BorderLayout.CENTER);

        PanelConSombra tarjeta3 = crearTarjeta(panelContenido, 40 + (ancho3 + gap) * 2, yInicio, ancho3, altoTarjeta, "Estado de decisiones");
        datasetEstadoDecisiones = new DefaultPieDataset();
        tarjeta3.add(crearChartPanel(graficoEstadoDecisiones(datasetEstadoDecisiones), ancho3 - 30, 250), BorderLayout.CENTER);

        int yAbajo = 500;
        int ancho2 = (anchoContenido - 100 - gap) / 2;
        int altoAbajo = 320;

        PanelConSombra tarjeta4 = crearTarjeta(panelContenido, 40, yAbajo, ancho2, altoAbajo, "Oferta vs. demanda por área laboral");
        datasetOfertaVsDemanda = new DefaultCategoryDataset();
        tarjeta4.add(crearChartPanel(graficoOfertaVsDemandaArea(datasetOfertaVsDemanda), ancho2 - 30, 225), BorderLayout.CENTER);

        PanelConSombra tarjeta5 = crearTarjeta(panelContenido, 40 + ancho2 + gap, yAbajo, ancho2, altoAbajo, "Distribución de coincidencias");
        datasetCoincidencias = new DefaultCategoryDataset();
        tarjeta5.add(crearChartPanel(graficoDistribucionCoincidencias(datasetCoincidencias), ancho2 - 30, 225), BorderLayout.CENTER);

        panelContenido.setPreferredSize(new Dimension(anchoContenido, 850));
        
        btnVolver = new BotonRedond("    Volver", 25);
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VerReportesAdmin volver = new VerReportesAdmin();
        		volver.setVisible(true);
        		dispose();
        	}
        });
        btnVolver.setVerticalTextPosition(SwingConstants.TOP);
        btnVolver.setText(" Volver");
        btnVolver.setIconTextGap(6);
        btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolver.setForeground(new Color(0, 0, 51));
        btnVolver.setFont(new Font("Calibri", Font.PLAIN, 18));
        btnVolver.setColorHover(new Color(255, 220, 183));
        btnVolver.setBackground(new Color(255, 235, 215));
        btnVolver.setBounds(40, 830, 194, 57);
        panelContenido.add(btnVolver);
    }

    private JLabel crearKpi(JPanel padre, int x, int y, int ancho, int alto, String titulo, String valor) {
        PanelConSombra card = new PanelConSombra(18);
        card.setBackground(Color.WHITE);
        card.setBounds(x, y, ancho, alto);
        card.setLayout(null);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 17));
        lblTitulo.setBounds(20, 14, ancho - 40, 20);

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(new Color(0, 0, 51));
        lblValor.setFont(new Font("Calibri", Font.BOLD, 34));
        lblValor.setBounds(20, 40, ancho - 40, 35);

        card.add(lblTitulo);
        card.add(lblValor);
        padre.add(card);

        return lblValor;
    }

    private PanelConSombra crearTarjeta(JPanel padre, int x, int y, int ancho, int alto, String titulo) {
        PanelConSombra tarjeta = new PanelConSombra(20);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBounds(x, y, ancho, alto);
        tarjeta.setLayout(new BorderLayout());

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

        padre.add(tarjeta);
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

    private void cargarGraficasConHilo() {
        SwingWorker<DatosGraficasAdmin, Void> hilo = new SwingWorker<DatosGraficasAdmin, Void>() {
            @Override
            protected DatosGraficasAdmin doInBackground() throws Exception {
                Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_GRAFICAS_ADMIN, null);
                Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

                if (!respuesta.isExito()) {
                    throw new IllegalArgumentException(String.valueOf(respuesta.getDatos()));
                }

                return (DatosGraficasAdmin) respuesta.getDatos();
            }

            @Override
            protected void done() {
                try {
                    DatosGraficasAdmin datos = get();
                    actualizarGraficas(datos);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(
                            VerGraficas.this,
                            "No se pudieron cargar las gráficas.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        };
        hilo.execute();
    }

    private void actualizarGraficas(DatosGraficasAdmin datos) {
        if (datos == null) return;

        lblSolicitantesEmpleados.setText(String.valueOf(datos.getSolicitantesEmpleados()));
        lblEmpresasActivas.setText(String.valueOf(datos.getEmpresasActivas()));

        datasetTopEmpresas.clear();
        if (datos.getNombresEmpresasTop() != null && datos.getOfertasPorEmpresaTop() != null) {
            for (int i = 0; i < datos.getNombresEmpresasTop().size() && i < datos.getOfertasPorEmpresaTop().size(); i++) {
                datasetTopEmpresas.addValue(datos.getOfertasPorEmpresaTop().get(i), "Ofertas", datos.getNombresEmpresasTop().get(i));
            }
        }

        datasetActividadMensual.clear();
        datasetActividadMensual.addValue(datos.getSolicitudesMes(), "Cantidad", "Solicitudes");
        datasetActividadMensual.addValue(datos.getOfertasMes(), "Cantidad", "Ofertas");
        datasetActividadMensual.addValue(datos.getContratadosMes(), "Cantidad", "Contrataciones");

        datasetEstadoDecisiones.clear();
        datasetEstadoDecisiones.setValue("Pendientes", Math.max(0, datos.getPendientes()));
        datasetEstadoDecisiones.setValue("Contratados", Math.max(0, datos.getContratados()));
        datasetEstadoDecisiones.setValue("Rechazados", Math.max(0, datos.getRechazados()));

        datasetOfertaVsDemanda.clear();
        if (datos.getNombresAreasLaborales() != null && datos.getOfertasPorAreaLaboral() != null && datos.getSolicitudesPorAreaLaboral() != null) {
            for (int i = 0; i < datos.getNombresAreasLaborales().size(); i++) {
                String area = datos.getNombresAreasLaborales().get(i);
                int ofertas = i < datos.getOfertasPorAreaLaboral().size() ? datos.getOfertasPorAreaLaboral().get(i) : 0;
                int solicitudes = i < datos.getSolicitudesPorAreaLaboral().size() ? datos.getSolicitudesPorAreaLaboral().get(i) : 0;
                datasetOfertaVsDemanda.addValue(ofertas, "Ofertas", area);
                datasetOfertaVsDemanda.addValue(solicitudes, "Solicitudes", area);
            }
        }

        datasetCoincidencias.clear();
        if (datos.getRangosCoincidencia() != null && datos.getCantidadCoincidencias() != null) {
            for (int i = 0; i < datos.getRangosCoincidencia().size() && i < datos.getCantidadCoincidencias().size(); i++) {
                datasetCoincidencias.addValue(datos.getCantidadCoincidencias().get(i), "Coincidencias", datos.getRangosCoincidencia().get(i));
            }
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private JFreeChart graficoTopEmpresas(DefaultCategoryDataset dataset) {
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

    private JFreeChart graficoActividadMensual(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(null, "Tipo", "Cantidad", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(232, 232, 232));
        plot.setOutlineVisible(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(102, 126, 234));
        renderer.setSeriesPaint(1, new Color(0, 153, 153));
        renderer.setSeriesPaint(2, new Color(46, 125, 50));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(false);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        chart.setBackgroundPaint(Color.WHITE);
        return chart;
    }

    private JFreeChart graficoEstadoDecisiones(DefaultPieDataset dataset) {
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

    private JFreeChart graficoOfertaVsDemandaArea(DefaultCategoryDataset dataset) {
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

    private JFreeChart graficoDistribucionCoincidencias(DefaultCategoryDataset dataset) {
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
        if (recurso == null) return;
        ImageIcon icono = new ImageIcon(recurso);
        Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(imagenEscalada));
    }
}