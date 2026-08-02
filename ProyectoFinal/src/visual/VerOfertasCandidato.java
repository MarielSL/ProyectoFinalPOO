package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logico.BolsaEmpleo;
import logico.Jornada;
import logico.Oferta;
import logico.Persona;
import logico.TipoPersona;
import logico.EstadoOferta;

public class VerOfertasCandidato extends JFrame {

    private JPanel contentPane;
    private Dimension dim;
    private ComboBoxRedond<String> cbxTipoCandidato;
    private ComboBoxRedond<String> cbxJornada;
    private ComboBoxRedond<String> cbxDispMudarse;
    private JPanel pnlVacio;
    private JPanel pnlTabla;
    private JLabel lblIlustracion;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorterOfertas;
    private ArrayList<Oferta> listaOfertas;
    private BotonRedond btnVolver;
    private Persona candidato;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VerOfertasCandidato frame = new VerOfertasCandidato();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VerOfertasCandidato() {
        if (BolsaEmpleo.getInstancia().getLoginUser() != null) {
            candidato = BolsaEmpleo.getInstancia().getLoginUser().getPersona();
        }

        setTitle("Ofertas de Empleo");
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

        construirHeader(panel, margen, anchoContenido);
        construirFiltros(panel, margen, anchoContenido);
        construirContenido(panel, margen, anchoContenido);

        btnVolver = new BotonRedond(" <-  Volver", 30);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomeCandidato volver = new HomeCandidato();
                volver.setVisible(true);
                dispose();
            }
        });
        btnVolver.setForeground(new Color(0, 0, 51));
        btnVolver.setFont(new Font("Calibri", Font.PLAIN, 20));
        btnVolver.setColorHover(new Color(255, 220, 183));
        btnVolver.setBackground(new Color(255, 235, 215));
        btnVolver.setBounds(40, 902, 220, 50);
        panel.add(btnVolver);

        cargarDatosConHilo();
    }

    private void construirHeader(JPanel panel, int margen, int anchoContenido) {
        PanelConSombra panelHeader = new PanelConSombra(25);
        panelHeader.setBackground(new Color(0, 0, 51));
        panelHeader.setBounds(0, 0, 1920, 90);
        panel.add(panelHeader);
        panelHeader.setLayout(null);

        String nombreCondidato = "Nombre";
        if (candidato != null) {
            nombreCondidato = candidato.getNombre();
        }
        int anchoNombre = 14 * nombreCondidato.length() + 20;

        JLabel lblNombreEmpresa = new JLabel(nombreCondidato);
        lblNombreEmpresa.setFont(new Font("Calibri", Font.BOLD, 24));
        lblNombreEmpresa.setForeground(Color.WHITE);
        lblNombreEmpresa.setBounds(1708, 36, anchoNombre, 20);
        panelHeader.add(lblNombreEmpresa);

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
                BarraSolicitante home = new BarraSolicitante();
                home.setVisible(true);
                dispose();
            }
        });
        panelHeader.add(btnAtras);

        JLabel lblTitulo = new JLabel("Ofertas de Empleo");
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 35));
        lblTitulo.setForeground(new Color(255, 153, 0));
        lblTitulo.setBounds(77, 28, 600, 30);
        panelHeader.add(lblTitulo);

        JLabel iconoLogo = new JLabel("");
        iconoLogo.setBounds(1784, 0, 114, 88);
        colocarImagen(iconoLogo, "/img/iconoLogo_FondoOscuro.png");
        panelHeader.add(iconoLogo);
    }

    private void construirFiltros(JPanel panel, int margen, int anchoContenido) {
        PanelConSombra panelFiltros = new PanelConSombra(18);
        panelFiltros.setBackground(Color.WHITE);
        panelFiltros.setBounds(margen, 110, anchoContenido, 60);
        panel.add(panelFiltros);
        panelFiltros.setLayout(null);

        int xBase = 24;
        int anchoCombo = 220;
        int espacio = 40;

        JLabel lblTipoCandidato = new JLabel("Tipo de candidato");
        lblTipoCandidato.setFont(new Font("Calibri", Font.PLAIN, 13));
        lblTipoCandidato.setForeground(new Color(120, 120, 120));
        lblTipoCandidato.setBounds(xBase, 4, anchoCombo, 16);
        panelFiltros.add(lblTipoCandidato);

        cbxTipoCandidato = new ComboBoxRedond<String>(15);
        cbxTipoCandidato.setFont(new Font("Calibri", Font.PLAIN, 15));
        cbxTipoCandidato.setForeground(Color.BLACK);
        cbxTipoCandidato.setBackground(Color.WHITE);
        cbxTipoCandidato.setModel(new DefaultComboBoxModel<String>(new String[] {
                "Todos", "Universitario", "Tecnico", "Obrero"
        }));
        cbxTipoCandidato.setSelectedIndex(0);
        cbxTipoCandidato.setBounds(xBase, 20, anchoCombo, 28);
        panelFiltros.add(cbxTipoCandidato);
        cbxTipoCandidato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        int xJornada = xBase + anchoCombo + espacio;
        JLabel lblJornada = new JLabel("Jornada");
        lblJornada.setFont(new Font("Calibri", Font.PLAIN, 13));
        lblJornada.setForeground(new Color(120, 120, 120));
        lblJornada.setBounds(xJornada, 4, anchoCombo, 16);
        panelFiltros.add(lblJornada);

        cbxJornada = new ComboBoxRedond<String>(15);
        cbxJornada.setFont(new Font("Calibri", Font.PLAIN, 15));
        cbxJornada.setForeground(Color.BLACK);
        cbxJornada.setBackground(Color.WHITE);
        cbxJornada.setModel(new DefaultComboBoxModel<String>(new String[] {
                "Todas", "Matutina", "Vespertina", "Nocturna"
        }));
        cbxJornada.setSelectedIndex(0);
        cbxJornada.setBounds(xJornada, 20, anchoCombo, 28);
        panelFiltros.add(cbxJornada);
        cbxJornada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        int xMudarse = xJornada + anchoCombo + espacio;
        JLabel lblMudarse = new JLabel("Disponibilidad para mudarse");
        lblMudarse.setFont(new Font("Calibri", Font.PLAIN, 13));
        lblMudarse.setForeground(new Color(120, 120, 120));
        lblMudarse.setBounds(xMudarse, 4, anchoCombo + 40, 16);
        panelFiltros.add(lblMudarse);

        cbxDispMudarse = new ComboBoxRedond<String>(15);
        cbxDispMudarse.setFont(new Font("Calibri", Font.PLAIN, 15));
        cbxDispMudarse.setForeground(Color.BLACK);
        cbxDispMudarse.setBackground(Color.WHITE);
        cbxDispMudarse.setModel(new DefaultComboBoxModel<String>(new String[] {
                "Todas", "Si", "No"
        }));
        cbxDispMudarse.setSelectedIndex(0);
        cbxDispMudarse.setBounds(xMudarse, 20, anchoCombo, 28);
        panelFiltros.add(cbxDispMudarse);
        cbxDispMudarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });
    }

    private void construirContenido(JPanel panel, int margen, int anchoContenido) {
        int yContenido = 190;
        int altoContenido = dim.height - yContenido - 60;

        PanelConSombra panelContenedor = new PanelConSombra(20);
        panelContenedor.setBackground(Color.WHITE);
        panelContenedor.setBounds(40, 190, 1840, 699);
        panel.add(panelContenedor);
        panelContenedor.setLayout(null);

        pnlVacio = crearEstadoVacio();
        pnlVacio.setBounds(0, 0, anchoContenido, altoContenido);
        panelContenedor.add(pnlVacio);

        pnlTabla = crearTabla(anchoContenido, altoContenido);
        pnlTabla.setBounds(0, 0, 1840, 686);
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

        JLabel lblTitulo = new JLabel("Aun no hay ofertas publicadas");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(0, 0, 51));
        lblTitulo.setBounds(0, 220, 1, 1);
        panelVacio.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Cuando las empresas publiquen ofertas, apareceran aqui.");
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

    private JPanel crearTabla(int ancho, int alto) {
        JPanel panelTabla = new JPanel();
        panelTabla.setOpaque(false);
        panelTabla.setLayout(null);

        table = new JTable();
        table.setModel(crearModeloOfertas());
        table.setFont(new Font("Calibri", Font.PLAIN, 16));
        table.setRowHeight(38);
        table.setForeground(new Color(50, 50, 50));
        table.setSelectionBackground(new Color(240, 240, 245));
        table.setShowGrid(false);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
        table.getTableHeader().setForeground(new Color(0, 0, 51));
        table.setDefaultRenderer(Object.class, new RenderCentrado());

        table.getColumnModel().getColumn(6).setCellRenderer(new RenderBadgeEstado());

        sorterOfertas = new TableRowSorter<DefaultTableModel>((DefaultTableModel) table.getModel());
        table.setRowSorter(sorterOfertas);

        JScrollPane scrollTabla = new JScrollPane(table);
        scrollTabla.setBorder(null);
        scrollTabla.setBounds(24, 20, ancho - 48, alto - 40);
        panelTabla.add(scrollTabla);

        return panelTabla;
    }

    private void cargarDatosConHilo() {
        SwingWorker<ArrayList<Oferta>, Void> worker = new SwingWorker<ArrayList<Oferta>, Void>() {
            @Override
            protected ArrayList<Oferta> doInBackground() {
                ArrayList<Oferta> ofertas = BolsaEmpleo.getInstancia().getOfertas();
                return ofertas != null ? ofertas : new ArrayList<Oferta>();
            }

            @Override
            protected void done() {
                try {
                    listaOfertas = get();
                    table.setModel(crearModeloOfertas());

                    sorterOfertas = new TableRowSorter<DefaultTableModel>((DefaultTableModel) table.getModel());
                    table.setRowSorter(sorterOfertas);
                    table.getColumnModel().getColumn(6).setCellRenderer(new RenderBadgeEstado());
                    table.setDefaultRenderer(Object.class, new RenderCentrado());

                    boolean hayOfertas = listaOfertas != null && !listaOfertas.isEmpty();
                    pnlVacio.setVisible(!hayOfertas);
                    pnlTabla.setVisible(hayOfertas);

                    aplicarFiltros();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    private DefaultTableModel crearModeloOfertas() {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][] {},
                new String[] { "Puesto", "Empresa", "Ciudad", "Jornada", "Tipo candidato", "Disp. mudarse", "Estado" }
        ) {
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        if (listaOfertas == null || listaOfertas.isEmpty()) {
            return modelo;
        }

        for (Oferta oferta : listaOfertas) {
            if (oferta == null) {
                continue;
            }

            String nombreEmpresa = oferta.getEmpresa() != null ? oferta.getEmpresa().getNombre() : "";
            String jornadaTexto = oferta.getJornada() != null ? formatearJornada(oferta.getJornada()) : "";
            String tipoCandTexto = oferta.getTipoCandidato() != null ? formatearTipoCandidato(oferta.getTipoCandidato()) : "";
            String dispMud = oferta.isDispMudar() ? "Si" : "No";
            String estadoTexto = formatearEstado(oferta.getEstado());

            modelo.addRow(new Object[] {
                    oferta.getPuesto(),
                    nombreEmpresa,
                    oferta.getCiudad(),
                    jornadaTexto,
                    tipoCandTexto,
                    dispMud,
                    estadoTexto
            });
        }
        return modelo;
    }

    private String formatearJornada(Jornada jornada) {
        if (jornada == Jornada.MATUTINA) {
            return "Matutina";
        }
        if (jornada == Jornada.NOCTURNA) {
            return "Nocturna";
        }
        if (jornada == Jornada.VESPERTINA) {
            return "Vespertina";
        }
        return "";
    }

    private String formatearTipoCandidato(TipoPersona tipo) {
        if (tipo == TipoPersona.UNIVERSITARIO) {
            return "Universitario";
        }
        if (tipo == TipoPersona.TECNICO) {
            return "Tecnico";
        }
        if (tipo == TipoPersona.OBRERO) {
            return "Obrero";
        }
        return "";
    }

    private String formatearEstado(EstadoOferta estado) {
        if (estado == null) {
            return "Inactiva";
        }
        switch (estado) {
            case PENDIENTE:
                return "Activa";
            case COMPLETADA:
                return "Inactiva";
            default:
                return "Inactiva";
        }
    }

    private void aplicarFiltros() {
        if (sorterOfertas == null) {
            return;
        }

        java.util.List<RowFilter<Object, Object>> filtros = new java.util.ArrayList<>();

        String tipoSel = (String) cbxTipoCandidato.getSelectedItem();
        if (tipoSel != null && !tipoSel.equals("Todos")) {
            filtros.add(RowFilter.regexFilter("(?i)^" + tipoSel + "$", 4));
        }

        String jornadaSel = (String) cbxJornada.getSelectedItem();
        if (jornadaSel != null && !jornadaSel.equals("Todas")) {
            filtros.add(RowFilter.regexFilter("(?i)^" + jornadaSel + "$", 3));
        }

        String dispSel = (String) cbxDispMudarse.getSelectedItem();
        if (dispSel != null && !dispSel.equals("Todas")) {
            filtros.add(RowFilter.regexFilter("(?i)^" + dispSel + "$", 5));
        }

        if (filtros.isEmpty()) {
            sorterOfertas.setRowFilter(null);
        } else {
            sorterOfertas.setRowFilter(RowFilter.andFilter(filtros));
        }
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

    public class RenderCentrado extends DefaultTableCellRenderer {
        public RenderCentrado() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    private class RenderBadgeEstado extends JLabel implements javax.swing.table.TableCellRenderer {

        public RenderBadgeEstado() {
            setOpaque(false);
            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(new Font("Calibri", Font.BOLD, 14));
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            String estado = value != null ? value.toString() : "Inactiva";
            setText(estado);

            if ("Activa".equals(estado)) {
                setBackground(new Color(198, 239, 206));
                setForeground(new Color(46, 125, 50));
            } else {
                setBackground(new Color(255, 205, 210));
                setForeground(new Color(198, 40, 40));
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
            g2.fillRoundRect(margenHorizontal, margenVertical,
                    getWidth() - margenHorizontal * 2,
                    getHeight() - margenVertical * 2,
                    16, 16);
            g2.dispose();

            super.paintComponent(g);
        }
    }
}