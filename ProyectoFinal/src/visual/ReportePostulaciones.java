package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import red.ConexionCliente;
import red.DatosEstadisticas;
import red.Peticion;
import red.Respuesta;

public class ReportePostulaciones extends JDialog {

    private JLabel lblTotalPostulaciones;
    private JLabel lblPendientes;
    private JLabel lblEstado;
    private JButton cerrarButton;

    public static void main(String[] args) {
        try {
            ReportePostulaciones dialog = new ReportePostulaciones();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReportePostulaciones() {
        setTitle("Reporte de Postulaciones");
        setBounds(100, 100, 427, 293);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(new BorderLayout(0, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("Resumen de postulaciones realizadas:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
        contentPanel.add(lblTitulo, BorderLayout.NORTH);

        lblTotalPostulaciones = crearLabelValor(new Color(0, 120, 0));
        lblPendientes = crearLabelValor(new Color(204, 102, 0));

        JPanel panelStats = new JPanel();
        panelStats.setLayout(new GridLayout(1, 2, 20, 0));
        panelStats.add(crearBloque("Total de postulaciones", lblTotalPostulaciones));
        panelStats.add(crearBloque("Pendientes", lblPendientes));
        contentPanel.add(panelStats, BorderLayout.CENTER);

        lblEstado = new JLabel("Cargando datos...");
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setFont(new Font("Calibri", Font.ITALIC, 14));
        contentPanel.add(lblEstado, BorderLayout.SOUTH);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPane.add(cerrarButton);

        cargarReporteConHilo();
    }

    private void cargarReporteConHilo() {
        cerrarButton.setEnabled(false);
        lblTotalPostulaciones.setText("...");
        lblPendientes.setText("...");
        lblEstado.setText("Cargando datos...");

        SwingWorker<int[], Void> hilo = new SwingWorker<int[], Void>() {

            @Override
            protected int[] doInBackground() throws Exception {
                Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_ESTADISTICAS, null);
                Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

                if (!respuesta.isExito()) {
                    throw new IllegalArgumentException(respuesta.getDatos().toString());
                }

                DatosEstadisticas datos = (DatosEstadisticas) respuesta.getDatos();
                return new int[] { datos.getTotalPostulaciones(), datos.getPostulacionesPendientes() };
            }

            @Override
            protected void done() {
                try {
                    int[] datos = get();

                    lblTotalPostulaciones.setText(String.valueOf(datos[0]));
                    lblPendientes.setText(String.valueOf(datos[1]));
                    lblEstado.setText("Basado en los datos actuales de la plataforma");

                } catch (Exception e) {
                    Throwable causa = e.getCause();
                    String mensaje = causa != null ? causa.getMessage() : e.getMessage();

                    e.printStackTrace();

                    lblTotalPostulaciones.setText("0");
                    lblPendientes.setText("0");
                    lblEstado.setText("No se pudieron cargar los datos");

                    JOptionPane.showMessageDialog(ReportePostulaciones.this, mensaje != null ? mensaje : "No se pudo generar el reporte de postulaciones.", "Error", JOptionPane.ERROR_MESSAGE);

                } finally {
                    cerrarButton.setEnabled(true);
                }
            }
        };

        hilo.execute();
    }

    private JLabel crearLabelValor(Color color) {
        JLabel lblValor = new JLabel("...");
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);
        lblValor.setFont(new Font("Calibri", Font.BOLD, 28));
        lblValor.setForeground(color);
        return lblValor;
    }

    private JPanel crearBloque(String titulo, JLabel lblValor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 5));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 14));
        panel.add(lblTitulo, BorderLayout.NORTH);

        panel.add(lblValor, BorderLayout.CENTER);

        return panel;
    }
}