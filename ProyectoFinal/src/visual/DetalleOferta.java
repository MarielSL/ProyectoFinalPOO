package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logico.Oferta;

public class DetalleOferta extends JDialog {

    private JPanel contentPane;
    private Oferta oferta;

    public DetalleOferta(Oferta oferta) {
        super((Frame) null, true);
        this.oferta = oferta;
        init();
    }

    private void init() {
        setTitle("Detalle de la Oferta");
        Utilidades.aplicarIcono(this);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 15));

        PanelConSombra panelHeader = new PanelConSombra(18);
        panelHeader.setBackground(new Color(0, 0, 51));
        contentPane.add(panelHeader, BorderLayout.NORTH);
        panelHeader.setLayout(null);

        String titulo = oferta.getPuesto() != null ? oferta.getPuesto() : "Oferta";
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(20, 15, 620, 30);
        panelHeader.add(lblTitulo);

        JPanel panelDatos = new JPanel();
        panelDatos.setOpaque(false);
        panelDatos.setLayout(null);
        contentPane.add(panelDatos, BorderLayout.CENTER);

        int y = 10;
        int xLabel = 10;
        int anchoLabel = 140;
        int altoFila = 30;

        JLabel lblEmpresaTitulo = new JLabel("Empresa:");
        lblEmpresaTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
        lblEmpresaTitulo.setBounds(xLabel, y, anchoLabel, altoFila);
        panelDatos.add(lblEmpresaTitulo);

        String nombreEmpresa = "";
        if (oferta.getEmpresa() != null && oferta.getEmpresa().getNombre() != null) {
            nombreEmpresa = oferta.getEmpresa().getNombre();
        }
        JLabel lblEmpresa = new JLabel(nombreEmpresa);
        lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblEmpresa.setBounds(xLabel + anchoLabel, y, 400, altoFila);
        panelDatos.add(lblEmpresa);
        y += altoFila + 10;

        JLabel lblCiudadTitulo = new JLabel("Ciudad:");
        lblCiudadTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
        lblCiudadTitulo.setBounds(xLabel, y, anchoLabel, altoFila);
        panelDatos.add(lblCiudadTitulo);

        String ciudad = oferta.getCiudad() != null ? oferta.getCiudad() : "";
        JLabel lblCiudad = new JLabel(ciudad);
        lblCiudad.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblCiudad.setBounds(xLabel + anchoLabel, y, 400, altoFila);
        panelDatos.add(lblCiudad);
        y += altoFila + 10;

        JLabel lblJornadaTitulo = new JLabel("Jornada:");
        lblJornadaTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
        lblJornadaTitulo.setBounds(xLabel, y, anchoLabel, altoFila);
        panelDatos.add(lblJornadaTitulo);

        String jornada = oferta.getJornada() != null ? formatearJornada(oferta.getJornada()) : "";
        JLabel lblJornada = new JLabel(jornada);
        lblJornada.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblJornada.setBounds(xLabel + anchoLabel, y, 400, altoFila);
        panelDatos.add(lblJornada);
        y += altoFila + 10;

        JLabel lblModalidadTitulo = new JLabel("Modalidad:");
        lblModalidadTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
        lblModalidadTitulo.setBounds(xLabel, y, anchoLabel, altoFila);
        panelDatos.add(lblModalidadTitulo);

        String modalidad = oferta.getModalidad() != null ? oferta.getModalidad().toString() : "";
        JLabel lblModalidad = new JLabel(modalidad);
        lblModalidad.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblModalidad.setBounds(xLabel + anchoLabel, y, 400, altoFila);
        panelDatos.add(lblModalidad);
        y += altoFila + 10;

        JLabel lblSalarioTitulo = new JLabel("Salario:");
        lblSalarioTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
        lblSalarioTitulo.setBounds(xLabel, y, anchoLabel, altoFila);
        panelDatos.add(lblSalarioTitulo);

        String salario = "RD$ " + String.format("%,.2f", oferta.getSalario());
        JLabel lblSalario = new JLabel(salario);
        lblSalario.setFont(new Font("Calibri", Font.PLAIN, 16));
        lblSalario.setBounds(xLabel + anchoLabel, y, 400, altoFila);
        panelDatos.add(lblSalario);
        y += altoFila + 15;

        JLabel lblDescripcionTitulo = new JLabel("Descripción:");
        lblDescripcionTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
        lblDescripcionTitulo.setBounds(xLabel, y, anchoLabel, altoFila);
        panelDatos.add(lblDescripcionTitulo);
        y += altoFila;

        String descripcion = oferta.getDescripPuesto() != null ? oferta.getDescripPuesto() : "";
        JTextArea txtDescripcion = new JTextArea(descripcion);
        txtDescripcion.setFont(new Font("Calibri", Font.PLAIN, 15));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setOpaque(false);
        txtDescripcion.setBounds(xLabel + anchoLabel, y, 400, 120);

        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBorder(null);
        scrollDescripcion.setBounds(xLabel + anchoLabel, y, 400, 120);
        panelDatos.add(scrollDescripcion);

        PanelConSombra panelFondo = new PanelConSombra(20);
        panelFondo.setBackground(new Color(255, 255, 255));
        panelFondo.setBounds(0, 0, 660, 440);
        panelDatos.add(panelFondo);
        panelFondo.setComponentZOrder(scrollDescripcion, 0);
        panelFondo.setComponentZOrder(lblDescripcionTitulo, 0);
        panelFondo.setComponentZOrder(lblSalario, 0);
        panelFondo.setComponentZOrder(lblSalarioTitulo, 0);
        panelFondo.setComponentZOrder(lblModalidad, 0);
        panelFondo.setComponentZOrder(lblModalidadTitulo, 0);
        panelFondo.setComponentZOrder(lblJornada, 0);
        panelFondo.setComponentZOrder(lblJornadaTitulo, 0);
        panelFondo.setComponentZOrder(lblCiudad, 0);
        panelFondo.setComponentZOrder(lblCiudadTitulo, 0);
        panelFondo.setComponentZOrder(lblEmpresa, 0);
        panelFondo.setComponentZOrder(lblEmpresaTitulo, 0);
        panelFondo.setComponentZOrder(panelDatos, 0);
    }

    private String formatearJornada(logico.Jornada jornada) {
        if (jornada == logico.Jornada.MATUTINA) {
            return "Matutina";
        }
        if (jornada == logico.Jornada.NOCTURNA) {
            return "Nocturna";
        }
        if (jornada == logico.Jornada.VESPERTINA) {
            return "Vespertina";
        }
        return jornada.toString();
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
}