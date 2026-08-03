package visual;

import java.awt.BasicStroke;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
import logico.Obrero;
import logico.Oferta;
import logico.Persona;
import logico.Tecnico;
import logico.Universitario;
import red.ConexionCliente;
import red.DatosDashboardAdmin;
import red.Peticion;
import red.Respuesta;

public class HomeAdministrador extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private Dimension dim;

    private JTable tableSolicitantes;
    private JTable tableOfertas;

    private DefaultTableModel modeloSolicitantes;
    private DefaultTableModel modeloOfertas;

    private JLabel lblOfertasActivasNum;
    private JLabel lblEmpresasNum;
    private JLabel lblContratadosNum;
    private JLabel lblSolicitantesNum;

    private JPanel panel_Grafica1;
    private JPanel panel_Grafica2;
    private JPanel panel_Grafica3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    HomeAdministrador frame = new HomeAdministrador();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HomeAdministrador() {
        setTitle("Inicio");
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

        JLayeredPane layeredPane = new JLayeredPane();
        contentPane.add(layeredPane, BorderLayout.CENTER);
        layeredPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setLayout(null);
        layeredPane.add(panel, BorderLayout.CENTER);

        construirMenu(panel);
        construirTarjetas(panel);
        construirGraficas(panel);
        construirTablaSolicitantes(panel);
        construirTablaOfertas(panel);

        cargarDatosHomeConHilo();
    }

    private void construirMenu(JPanel panel) {
        PanelRedond panelMenu = new PanelRedond(25);
        panelMenu.setBackground(new Color(0, 0, 51));
        panelMenu.setBounds(40, 20, 1840, 103);
        panelMenu.setLayout(null);
        panel.add(panelMenu);

        BotonRedond btnMenu = new BotonRedond("", 25);
        btnMenu.setBackground(new Color(0, 0, 51));
        btnMenu.setColorHover(new Color(0, 51, 102));
        btnMenu.setBounds(12, 30, 60, 60);
        btnMenu.setMargin(new Insets(0, 0, 0, 0));
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setOpaque(false);
        colocarIconoBoton(btnMenu, "/img/menu-dots-vertical(White).png", 25, 25);
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BarraAdmin menu = new BarraAdmin();
                menu.setVisible(true);
                dispose();
            }
        });
        panelMenu.add(btnMenu);

        JLabel lblLogo = new JLabel("");
        lblLogo.setBounds(0, 28, 400, 75);
        colocarImagen(lblLogo, "/img/HireLink_FondoOscuro.png");
        panelMenu.add(lblLogo);

        JLabel lblInicio = new JLabel("Inicio");
        lblInicio.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblInicio.setForeground(Color.WHITE);
        lblInicio.setBounds(700, 35, 70, 20);
        panelMenu.add(lblInicio);

        JLabel lblVerReportes = new JLabel("Ver reportes");
        lblVerReportes.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblVerReportes.setForeground(Color.WHITE);
        lblVerReportes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVerReportes.setBounds(830, 35, 100, 20);
        lblVerReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                VerReportesAdmin reporte = new VerReportesAdmin();
                reporte.setVisible(true);
                dispose();
            }
        });
        panelMenu.add(lblVerReportes);

        JLabel lblVerOfertas = new JLabel("Ver ofertas");
        lblVerOfertas.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblVerOfertas.setForeground(Color.WHITE);
        lblVerOfertas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVerOfertas.setBounds(960, 35, 100, 20);
        lblVerOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                VerOfertasAdmin ofertas = new VerOfertasAdmin();
                ofertas.setVisible(true);
                dispose();
            }
        });
        panelMenu.add(lblVerOfertas);

        JLabel lblVerEmpresas = new JLabel("Ver empresas");
        lblVerEmpresas.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblVerEmpresas.setForeground(Color.WHITE);
        lblVerEmpresas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVerEmpresas.setBounds(1090, 35, 110, 20);
        lblVerEmpresas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                VerEmpresasAdmin empresas = new VerEmpresasAdmin();
                empresas.setVisible(true);
                dispose();
            }
        });
        panelMenu.add(lblVerEmpresas);

        JLabel lblVerUsuarios = new JLabel("Ver usuarios");
        lblVerUsuarios.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblVerUsuarios.setForeground(Color.WHITE);
        lblVerUsuarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVerUsuarios.setBounds(1235, 35, 100, 20);
        lblVerUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                VerUsuariosAdmin usuarios = new VerUsuariosAdmin();
                usuarios.setVisible(true);
                dispose();
            }
        });
        panelMenu.add(lblVerUsuarios);
    }

    private void construirTarjetas(JPanel panel) {
        construirTarjetaOfertasActivas(panel);
        construirTarjetaSolicitantes(panel);
        construirTarjetaEmpresas(panel);
        construirTarjetaContratados(panel);
    }

    private void construirTarjetaOfertasActivas(JPanel panel) {
        PanelConSombra panelOfertasActivas = new PanelConSombra(18);
        panelOfertasActivas.setBackground(Color.WHITE);
        panelOfertasActivas.setBounds(140, 136, 298, 90);
        panelOfertasActivas.setLayout(null);
        panel.add(panelOfertasActivas);

        JLabel lblTitulo = new JLabel("Ofertas Activas");
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 17));
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setBounds(20, 14, 202, 20);
        panelOfertasActivas.add(lblTitulo);

        lblOfertasActivasNum = new JLabel("...");
        lblOfertasActivasNum.setFont(new Font("Calibri", Font.BOLD, 33));
        lblOfertasActivasNum.setForeground(new Color(0, 0, 51));
        lblOfertasActivasNum.setBounds(20, 38, 202, 36);
        panelOfertasActivas.add(lblOfertasActivasNum);
    }

    private void construirTarjetaSolicitantes(JPanel panel) {
        PanelConSombra panelSolicitantes = new PanelConSombra(18);
        panelSolicitantes.setBackground(Color.WHITE);
        panelSolicitantes.setBounds(578, 136, 298, 90);
        panelSolicitantes.setLayout(null);
        panel.add(panelSolicitantes);

        JLabel lblTitulo = new JLabel("Solicitantes Registrados");
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 17));
        lblTitulo.setBounds(20, 14, 220, 20);
        panelSolicitantes.add(lblTitulo);

        lblSolicitantesNum = new JLabel("...");
        lblSolicitantesNum.setForeground(new Color(0, 0, 51));
        lblSolicitantesNum.setFont(new Font("Calibri", Font.BOLD, 33));
        lblSolicitantesNum.setBounds(20, 38, 188, 36);
        panelSolicitantes.add(lblSolicitantesNum);
    }

    private void construirTarjetaEmpresas(JPanel panel) {
        PanelConSombra panelEmpresas = new PanelConSombra(18);
        panelEmpresas.setBackground(Color.WHITE);
        panelEmpresas.setBounds(1016, 136, 298, 90);
        panelEmpresas.setLayout(null);
        panel.add(panelEmpresas);

        JLabel lblTitulo = new JLabel("Empresas Asociadas");
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 17));
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setBounds(20, 14, 202, 20);
        panelEmpresas.add(lblTitulo);

        lblEmpresasNum = new JLabel("...");
        lblEmpresasNum.setForeground(new Color(0, 0, 51));
        lblEmpresasNum.setFont(new Font("Calibri", Font.BOLD, 33));
        lblEmpresasNum.setBounds(20, 38, 202, 36);
        panelEmpresas.add(lblEmpresasNum);
    }

    private void construirTarjetaContratados(JPanel panel) {
        PanelConSombra panelContratados = new PanelConSombra(18);
        panelContratados.setBackground(Color.WHITE);
        panelContratados.setBounds(1454, 136, 298, 90);
        panelContratados.setLayout(null);
        panel.add(panelContratados);

        JLabel lblTitulo = new JLabel("Candidatos Seleccionados");
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 17));
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setBounds(20, 14, 230, 20);
        panelContratados.add(lblTitulo);

        lblContratadosNum = new JLabel("...");
        lblContratadosNum.setFont(new Font("Calibri", Font.BOLD, 33));
        lblContratadosNum.setForeground(new Color(0, 0, 51));
        lblContratadosNum.setBounds(20, 38, 202, 36);
        panelContratados.add(lblContratadosNum);
    }

    private void construirGraficas(JPanel panel) {
        JPanel panelGrafico1 = new JPanel();
        panelGrafico1.setBackground(Color.WHITE);
        panelGrafico1.setBounds(86, 239, 515, 301);
        panelGrafico1.setLayout(null);
        panel.add(panelGrafico1);

        JLabel lblGrafico1 = new JLabel("Solicitantes por tipo");
        lblGrafico1.setFont(new Font("Calibri", Font.BOLD, 20));
        lblGrafico1.setBounds(33, 23, 255, 25);
        panelGrafico1.add(lblGrafico1);

        panel_Grafica1 = new JPanel();
        panel_Grafica1.setBounds(15, 55, 485, 230);
        panelGrafico1.add(panel_Grafica1);

        JPanel panelGrafico2 = new JPanel();
        panelGrafico2.setBackground(Color.WHITE);
        panelGrafico2.setBounds(687, 239, 515, 301);
        panelGrafico2.setLayout(null);
        panel.add(panelGrafico2);

        JLabel lblGrafico2 = new JLabel("Estado de las ofertas");
        lblGrafico2.setFont(new Font("Calibri", Font.BOLD, 20));
        lblGrafico2.setBounds(28, 13, 255, 25);
        panelGrafico2.add(lblGrafico2);

        panel_Grafica2 = new JPanel();
        panel_Grafica2.setBounds(15, 45, 485, 240);
        panelGrafico2.add(panel_Grafica2);

        JPanel panelGrafico3 = new JPanel();
        panelGrafico3.setBackground(Color.WHITE);
        panelGrafico3.setBounds(1288, 239, 515, 301);
        panelGrafico3.setLayout(null);
        panel.add(panelGrafico3);

        JLabel lblGrafico3 = new JLabel("Estado laboral de solicitantes");
        lblGrafico3.setFont(new Font("Calibri", Font.BOLD, 20));
        lblGrafico3.setBounds(12, 13, 320, 25);
        panelGrafico3.add(lblGrafico3);

        panel_Grafica3 = new JPanel();
        panel_Grafica3.setBounds(15, 45, 485, 240);
        panelGrafico3.add(panel_Grafica3);
    }

    private void construirTablaSolicitantes(JPanel panel) {
        PanelConSombra panelSolicitudesRecientes = new PanelConSombra(20);
        panelSolicitudesRecientes.setBackground(Color.WHITE);
        panelSolicitudesRecientes.setBounds(969, 574, 877, 425);
        panelSolicitudesRecientes.setLayout(null);
        panel.add(panelSolicitudesRecientes);

        JLabel lblTitulo = new JLabel("Solicitantes Registrados Recientemente");
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setBounds(24, 20, 400, 28);
        panelSolicitudesRecientes.add(lblTitulo);

        modeloSolicitantes = crearModeloSolicitantesVacio();

        tableSolicitantes = new JTable();
        tableSolicitantes.setModel(modeloSolicitantes);

        configurarTablaSolicitantes();

        JScrollPane scrollSolicitudes = new JScrollPane(tableSolicitantes);
        scrollSolicitudes.setBorder(null);
        scrollSolicitudes.setBounds(24, 60, 841, 352);
        panelSolicitudesRecientes.add(scrollSolicitudes);
    }

    private void construirTablaOfertas(JPanel panel) {
        PanelConSombra panelOfertas = new PanelConSombra(20);
        panelOfertas.setBackground(Color.WHITE);
        panelOfertas.setBounds(46, 574, 877, 425);
        panelOfertas.setLayout(null);
        panel.add(panelOfertas);

        JLabel lblTitulo = new JLabel("Ofertas Publicadas Recientemente");
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitulo.setBounds(24, 20, 400, 28);
        panelOfertas.add(lblTitulo);

        modeloOfertas = crearModeloOfertasVacio();

        tableOfertas = new JTable();
        tableOfertas.setModel(modeloOfertas);

        configurarTablaOfertas();

        JScrollPane scrollOfertas = new JScrollPane(tableOfertas);
        scrollOfertas.setBorder(null);
        scrollOfertas.setBounds(24, 60, 841, 352);
        panelOfertas.add(scrollOfertas);
    }

    private DefaultTableModel crearModeloSolicitantesVacio() {
        return new DefaultTableModel(
                new Object[][] {},
                new String[] { "Solicitante", "Profesión", "Registro", "Estado" }
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
    }

    private DefaultTableModel crearModeloOfertasVacio() {
        return new DefaultTableModel(
                new Object[][] {},
                new String[] { "Puesto", "Empresa", "Fecha Publicación", "Vacantes Disp.", "Estado" }
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
    }

    private void configurarTablaSolicitantes() {
        tableSolicitantes.setFont(new Font("Calibri", Font.PLAIN, 16));
        tableSolicitantes.setRowHeight(38);
        tableSolicitantes.setForeground(new Color(50, 50, 50));
        tableSolicitantes.setSelectionBackground(new Color(240, 240, 245));
        tableSolicitantes.setShowGrid(false);
        tableSolicitantes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableSolicitantes.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
        tableSolicitantes.getTableHeader().setForeground(new Color(0, 0, 51));

        tableSolicitantes.setDefaultRenderer(Object.class, new RenderCentrado());
        tableSolicitantes.getColumnModel().getColumn(3).setCellRenderer(new RenderEstado());

        tableSolicitantes.getColumnModel().getColumn(0).setPreferredWidth(180);
        tableSolicitantes.getColumnModel().getColumn(1).setPreferredWidth(260);
        tableSolicitantes.getColumnModel().getColumn(2).setPreferredWidth(90);
        tableSolicitantes.getColumnModel().getColumn(3).setPreferredWidth(140);
    }

    private void configurarTablaOfertas() {
        tableOfertas.setFont(new Font("Calibri", Font.PLAIN, 16));
        tableOfertas.setRowHeight(38);
        tableOfertas.setForeground(new Color(50, 50, 50));
        tableOfertas.setSelectionBackground(new Color(240, 240, 245));
        tableOfertas.setShowGrid(false);
        tableOfertas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableOfertas.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
        tableOfertas.getTableHeader().setForeground(new Color(0, 0, 51));

        tableOfertas.setDefaultRenderer(Object.class, new RenderCentrado());
        tableOfertas.getColumnModel().getColumn(4).setCellRenderer(new RenderEstado());

        tableOfertas.getColumnModel().getColumn(0).setPreferredWidth(170);
        tableOfertas.getColumnModel().getColumn(1).setPreferredWidth(180);
        tableOfertas.getColumnModel().getColumn(2).setPreferredWidth(130);
        tableOfertas.getColumnModel().getColumn(3).setPreferredWidth(100);
        tableOfertas.getColumnModel().getColumn(4).setPreferredWidth(120);
    }

    private void cargarDatosHomeConHilo() {
        prepararPantallaParaCarga();

        SwingWorker<DatosDashboardAdmin, Void> hilo = new SwingWorker<DatosDashboardAdmin, Void>() {
            @Override
            protected DatosDashboardAdmin doInBackground() throws Exception {
                Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_DASHBOARD_ADMIN, null);
                Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

                if (!respuesta.isExito()) {
                    throw new IllegalArgumentException(respuesta.getDatos().toString());
                }

                return (DatosDashboardAdmin) respuesta.getDatos();
            }

            @Override
            protected void done() {
                try {
                    DatosDashboardAdmin datosDashboard = get();

                    ArrayList<Oferta> ofertas = datosDashboard.getOfertas();
                    ArrayList<Persona> personas = datosDashboard.getPersonas();

                    int ofertasActivas = contarOfertasActivas(ofertas);
                    int empresas = datosDashboard.getTotalEmpresas();
                    int contratados = contarContratados(ofertas);
                    int solicitantes = personas != null ? personas.size() : 0;

                    DefaultTableModel nuevoModeloSolicitantes = crearModeloSolicitantesRecientes(personas);
                    DefaultTableModel nuevoModeloOfertas = crearModeloOfertasRecientes(ofertas);

                    lblOfertasActivasNum.setText(String.valueOf(ofertasActivas));
                    lblEmpresasNum.setText(String.valueOf(empresas));
                    lblContratadosNum.setText(String.valueOf(contratados));
                    lblSolicitantesNum.setText(String.valueOf(solicitantes));

                    modeloSolicitantes = nuevoModeloSolicitantes;
                    modeloOfertas = nuevoModeloOfertas;

                    tableSolicitantes.setModel(modeloSolicitantes);
                    tableOfertas.setModel(modeloOfertas);

                    configurarTablaSolicitantes();
                    configurarTablaOfertas();

                    mostrarGraficaSolicitantesPorTipo(personas);
                    mostrarGraficaEstadoOfertas(ofertas);
                    mostrarGraficaEstadoLaboralSolicitantes(personas);

                    tableSolicitantes.revalidate();
                    tableSolicitantes.repaint();
                    tableOfertas.revalidate();
                    tableOfertas.repaint();

                } catch (Exception e) {
                    Throwable causa = e.getCause();
                    String mensaje = causa != null ? causa.getMessage() : e.getMessage();

                    e.printStackTrace();
                    mostrarDatosVacios();

                    JOptionPane.showMessageDialog(
                            HomeAdministrador.this,
                            mensaje != null ? mensaje : "No se pudieron cargar los datos del administrador.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                } finally {
                    tableSolicitantes.setEnabled(true);
                    tableOfertas.setEnabled(true);
                }
            }
        };

        hilo.execute();
    }

    private void prepararPantallaParaCarga() {
        lblOfertasActivasNum.setText("...");
        lblEmpresasNum.setText("...");
        lblContratadosNum.setText("...");
        lblSolicitantesNum.setText("...");

        modeloSolicitantes.setRowCount(0);
        modeloOfertas.setRowCount(0);

        tableSolicitantes.setEnabled(false);
        tableOfertas.setEnabled(false);
    }

    private void mostrarDatosVacios() {
        lblOfertasActivasNum.setText("0");
        lblEmpresasNum.setText("0");
        lblContratadosNum.setText("0");
        lblSolicitantesNum.setText("0");

        modeloSolicitantes.setRowCount(0);
        modeloOfertas.setRowCount(0);

        mostrarGraficasVacias();
    }

    private int contarOfertasActivas(ArrayList<Oferta> ofertas) {
        if (ofertas == null) {
            return 0;
        }

        int contador = 0;
        for (Oferta oferta : ofertas) {
            if (oferta != null && oferta.getEstado() == EstadoOferta.PENDIENTE) {
                contador++;
            }
        }
        return contador;
    }

    private int contarContratados(ArrayList<Oferta> ofertas) {
        if (ofertas == null) {
            return 0;
        }

        int contador = 0;
        for (Oferta oferta : ofertas) {
            if (oferta != null) {
                contador += oferta.cantContratados();
            }
        }
        return contador;
    }

    private DefaultTableModel crearModeloSolicitantesRecientes(ArrayList<Persona> personas) {
        DefaultTableModel modelo = crearModeloSolicitantesVacio();
        ArrayList<Persona> ordenadas = new ArrayList<Persona>();

        if (personas == null) {
            return modelo;
        }

        for (Persona persona : personas) {
            if (persona != null && persona.getUser() != null && persona.getUser().getFechaRegistro() != null) {
                ordenadas.add(persona);
            }
        }

        Collections.sort(ordenadas, new Comparator<Persona>() {
            @Override
            public int compare(Persona persona1, Persona persona2) {
                return persona2.getUser().getFechaRegistro().compareTo(persona1.getUser().getFechaRegistro());
            }
        });

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
        int limite = Math.min(5, ordenadas.size());

        for (int i = 0; i < limite; i++) {
            Persona persona = ordenadas.get(i);

            String nombre = textoSeguro(persona.getNombre()) + " " + textoSeguro(persona.getApellido());
            String profesion = obtenerProfesion(persona);
            String fecha = persona.getUser().getFechaRegistro().format(formato);
            String estado = persona.isEstadoEmpleo() ? "Empleado" : "Buscando empleo";

            modelo.addRow(new Object[] { nombre, profesion, fecha, estado });
        }

        return modelo;
    }

    private DefaultTableModel crearModeloOfertasRecientes(ArrayList<Oferta> ofertas) {
        DefaultTableModel modelo = crearModeloOfertasVacio();
        ArrayList<Oferta> ordenadas = new ArrayList<Oferta>();

        if (ofertas == null) {
            return modelo;
        }

        for (Oferta oferta : ofertas) {
            if (oferta != null && oferta.getFechaPublicacion() != null) {
                ordenadas.add(oferta);
            }
        }

        Collections.sort(ordenadas, new Comparator<Oferta>() {
            @Override
            public int compare(Oferta oferta1, Oferta oferta2) {
                return oferta2.getFechaPublicacion().compareTo(oferta1.getFechaPublicacion());
            }
        });

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
        int limite = Math.min(5, ordenadas.size());

        for (int i = 0; i < limite; i++) {
            Oferta oferta = ordenadas.get(i);

            String puesto = textoSeguro(oferta.getPuesto());
            String empresa = oferta.getEmpresa() != null ? textoSeguro(oferta.getEmpresa().getNombre()) : "Sin empresa";
            String fecha = oferta.getFechaPublicacion().format(formato);
            int cantidadPuestos = oferta.getCantPuestos();
            String estado = formatearEstadoOferta(oferta.getEstado());

            modelo.addRow(new Object[] { puesto, empresa, fecha, cantidadPuestos, estado });
        }

        return modelo;
    }

    private String obtenerProfesion(Persona persona) {
        if (persona instanceof Universitario) {
            return textoSeguro(((Universitario) persona).getCarrera());
        }

        if (persona instanceof Tecnico) {
            return textoSeguro(((Tecnico) persona).getTecnico());
        }

        if (persona instanceof Obrero) {
            return textoSeguro(((Obrero) persona).getHabilidades());
        }

        return "N/A";
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

    private String textoSeguro(String texto) {
        return texto == null || texto.trim().isEmpty() ? "No disponible" : texto.trim();
    }

    private class RenderCentrado extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 1L;

        public RenderCentrado() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private class RenderEstado extends JLabel implements TableCellRenderer {
        private static final long serialVersionUID = 1L;

        public RenderEstado() {
            setOpaque(false);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("Calibri", Font.BOLD, 14));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            String estado = value == null ? "" : value.toString();
            setText(estado);

            if (estado.equals("Empleado")
                    || estado.equals("Completada")
                    || estado.equals("Aceptada")) {

                setBackground(new Color(198, 239, 206));
                setForeground(new Color(46, 125, 50));

            } else if (estado.equals("Rechazada")) {

                setBackground(new Color(255, 205, 210));
                setForeground(new Color(198, 40, 40));

            } else if (estado.equals("Buscando empleo")) {

                setBackground(new Color(195, 220, 255));
                setForeground(new Color(65, 95, 170));

            } else {

                setBackground(new Color(255, 224, 178));
                setForeground(new Color(204, 102, 0));
            }

            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());

            int margenVertical = 6;
            int margenHorizontal = 10;
            int ancho = Math.max(0, getWidth() - margenHorizontal * 2);
            int alto = Math.max(0, getHeight() - margenVertical * 2);

            g2.fillRoundRect(margenHorizontal, margenVertical, ancho, alto, 16, 16);
            g2.dispose();

            super.paintComponent(g);
        }
    }

    private void mostrarGraficasVacias() {
        DefaultPieDataset pieVacio = new DefaultPieDataset();
        pieVacio.setValue("Sin datos", 1);

        DefaultCategoryDataset barVacio = new DefaultCategoryDataset();
        barVacio.addValue(0, "Cantidad", "Sin datos");

        mostrarGraficaCircular(panel_Grafica1, "Solicitantes por tipo", pieVacio);
        mostrarGraficaBarras(panel_Grafica2, "Estado de las ofertas", barVacio);
        mostrarGraficaCircular(panel_Grafica3, "Estado laboral de solicitantes", pieVacio);
    }

    private void mostrarGraficaSolicitantesPorTipo(ArrayList<Persona> personas) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        int universitarios = 0;
        int tecnicos = 0;
        int obreros = 0;

        if (personas != null) {
            for (Persona persona : personas) {
                if (persona instanceof Universitario) {
                    universitarios++;
                } else if (persona instanceof Tecnico) {
                    tecnicos++;
                } else if (persona instanceof Obrero) {
                    obreros++;
                }
            }
        }

        if (universitarios == 0 && tecnicos == 0 && obreros == 0) {
            dataset.setValue("Sin datos", 1);
        } else {
            if (universitarios > 0) dataset.setValue("Universitario", universitarios);
            if (tecnicos > 0) dataset.setValue("Técnico", tecnicos);
            if (obreros > 0) dataset.setValue("Obrero", obreros);
        }

        mostrarGraficaCircular(panel_Grafica1, "Solicitantes por tipo", dataset);
    }

    private void mostrarGraficaEstadoOfertas(ArrayList<Oferta> ofertas) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int pendientes = 0;
        int completadas = 0;
        int enProceso = 0;

        if (ofertas != null) {
            for (Oferta oferta : ofertas) {
                if (oferta == null) continue;

                if (oferta.getEstado() == EstadoOferta.PENDIENTE) {
                    pendientes++;
                } else if (oferta.getEstado() == EstadoOferta.COMPLETADA) {
                    completadas++;
                } else {
                    enProceso++;
                }
            }
        }

        dataset.addValue(pendientes, "Ofertas", "Pendientes");
        dataset.addValue(completadas, "Ofertas", "Completadas");
        dataset.addValue(enProceso, "Ofertas", "En proceso");

        mostrarGraficaBarras(panel_Grafica2, "Estado de las ofertas", dataset);
    }

    private void mostrarGraficaEstadoLaboralSolicitantes(ArrayList<Persona> personas) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        int empleados = 0;
        int buscando = 0;

        if (personas != null) {
            for (Persona persona : personas) {
                if (persona == null) continue;

                if (persona.isEstadoEmpleo()) {
                    empleados++;
                } else {
                    buscando++;
                }
            }
        }

        if (empleados == 0 && buscando == 0) {
            dataset.setValue("Sin datos", 1);
        } else {
            if (empleados > 0) dataset.setValue("Empleado", empleados);
            if (buscando > 0) dataset.setValue("Buscando empleo", buscando);
        }

        mostrarGraficaCircular(panel_Grafica3, "Estado laboral de solicitantes", dataset);
    }

    private void mostrarGraficaCircular(JPanel panel, String titulo, DefaultPieDataset dataset) {
        panel.removeAll();

        JFreeChart grafica = ChartFactory.createPieChart(titulo, dataset, true, true, false);
        personalizarGraficaCircular(grafica);

        ChartPanel chartPanel = new ChartPanel(grafica);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setPopupMenu(null);

        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void mostrarGraficaBarras(JPanel panel, String titulo, DefaultCategoryDataset dataset) {
        panel.removeAll();

        JFreeChart grafica = ChartFactory.createBarChart(
                titulo,
                "Estado",
                "Cantidad",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        personalizarGraficaBarras(grafica);

        ChartPanel chartPanel = new ChartPanel(grafica);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setPopupMenu(null);

        panel.setLayout(new BorderLayout());
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void personalizarGraficaBarras(JFreeChart grafica) {
        Color colorTexto = Color.decode("#06002c");

        grafica.setBackgroundPaint(Color.WHITE);
        grafica.setPadding(new RectangleInsets(10, 10, 10, 10));

        grafica.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
        grafica.getTitle().setPaint(colorTexto);

        CategoryPlot plot = grafica.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setRangeGridlinePaint(new Color(225, 225, 225));
        plot.setRangeGridlineStroke(new BasicStroke(1f));

        plot.getDomainAxis().setLabelFont(new Font("Calibri", Font.BOLD, 14));
        plot.getDomainAxis().setTickLabelFont(new Font("Calibri", Font.PLAIN, 13));
        plot.getDomainAxis().setTickLabelPaint(colorTexto);

        plot.getRangeAxis().setLabelFont(new Font("Calibri", Font.BOLD, 14));
        plot.getRangeAxis().setTickLabelFont(new Font("Calibri", Font.PLAIN, 13));
        plot.getRangeAxis().setTickLabelPaint(colorTexto);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(65, 95, 170));
        renderer.setDrawBarOutline(false);
        renderer.setMaximumBarWidth(0.10);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelFont(new Font("Calibri", Font.BOLD, 13));
        renderer.setBaseItemLabelPaint(colorTexto);
    }

    private void personalizarGraficaCircular(JFreeChart grafica) {
        Color colorTexto = Color.decode("#06002c");

        grafica.setBackgroundPaint(Color.WHITE);
        grafica.setPadding(new RectangleInsets(10, 10, 10, 10));

        grafica.getTitle().setFont(new Font("Calibri", Font.BOLD, 20));
        grafica.getTitle().setPaint(colorTexto);

        PiePlot plot = (PiePlot) grafica.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);

        plot.setSectionPaint("Universitario", Color.decode("#4769ba"));
        plot.setSectionPaint("Técnico", Color.decode("#fe9703"));
        plot.setSectionPaint("Obrero", Color.decode("#58a65c"));
        plot.setSectionPaint("Empleado", Color.decode("#4769ba"));
        plot.setSectionPaint("Buscando empleo", Color.decode("#fe9703"));
        plot.setSectionPaint("Sin datos", Color.decode("#d9d9d9"));

        plot.setLabelFont(new Font("Calibri", Font.BOLD, 14));
        plot.setLabelPaint(colorTexto);
        plot.setLabelBackgroundPaint(new Color(255, 255, 255, 220));
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        if (grafica.getLegend() != null) {
            grafica.getLegend().setItemFont(new Font("Calibri", Font.PLAIN, 14));
            grafica.getLegend().setItemPaint(colorTexto);
            grafica.getLegend().setBackgroundPaint(Color.WHITE);
        }
    }

    private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
        if (boton == null || ruta == null) {
            return;
        }

        java.net.URL recurso = getClass().getResource(ruta);

        if (recurso == null) {
            System.err.println("No se encontró la imagen: " + ruta);
            return;
        }

        ImageIcon icono = new ImageIcon(recurso);
        Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(imagenEscalada));
    }

    //metodo imagenes
    private void colocarImagen(JLabel label, String ruta) {
        if (label == null || ruta == null) {
            return;
        }

        java.net.URL recurso = getClass().getResource(ruta);

        if (recurso == null) {
            System.err.println("No se encontró la imagen: " + ruta);
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