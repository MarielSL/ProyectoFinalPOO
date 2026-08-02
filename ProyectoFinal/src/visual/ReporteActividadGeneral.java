package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.Oferta;
import logico.Persona;
import logico.SolicitudEmpleo;

public class ReporteActividadGeneral extends JDialog {

	private JLabel lblTotalOfertas;
	private JLabel lblTotalSolicitantes;
	private JLabel lblTotalPostulaciones;
	private JLabel lblTotalEmpresas;
	private JLabel lblEstado;
	private JButton cerrarButton;

	public static void main(String[] args) {
		try {
			ReporteActividadGeneral dialog = new ReporteActividadGeneral();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReporteActividadGeneral() {
		setTitle("Actividad General");
		setBounds(100, 100, 427, 293);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setLayout(new BorderLayout(0, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("Vista general de la plataforma:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 16));
		contentPanel.add(lblTitulo, BorderLayout.NORTH);

		lblTotalOfertas = crearLabelValor(new Color(0, 120, 0));
		lblTotalSolicitantes = crearLabelValor(new Color(204, 102, 0));
		lblTotalPostulaciones = crearLabelValor(new Color(65, 95, 170));
		lblTotalEmpresas = crearLabelValor(new Color(198, 40, 40));

		JPanel panelStats = new JPanel();
		panelStats.setLayout(new GridLayout(2, 2, 20, 16));
		panelStats.add(crearBloque("Ofertas", lblTotalOfertas));
		panelStats.add(crearBloque("Solicitantes", lblTotalSolicitantes));
		panelStats.add(crearBloque("Postulaciones", lblTotalPostulaciones));
		panelStats.add(crearBloque("Empresas", lblTotalEmpresas));
		contentPanel.add(panelStats, BorderLayout.CENTER);

		lblEstado = new JLabel("Cargando datos...");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setFont(new Font("Calibri", Font.ITALIC, 15));
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

		SwingWorker<int[], Void> hilo = new SwingWorker<int[], Void>() {

			@Override
			protected int[] doInBackground() throws Exception {
				ArrayList<Oferta> lasOfertas = BolsaEmpleo.getInstancia().getOfertas();
				ArrayList<Persona> lasPersonas = BolsaEmpleo.getInstancia().getPersonas();
				ArrayList<SolicitudEmpleo> lasSolicitudes = BolsaEmpleo.getInstancia().getSolicitudes();
				ArrayList<Empresa> lasEmpresas = BolsaEmpleo.getInstancia().getEmpresas();

				int totalOfertas = lasOfertas == null ? 0 : lasOfertas.size();
				int totalSolicitantes = lasPersonas == null ? 0 : lasPersonas.size();
				int totalPostulaciones = lasSolicitudes == null ? 0 : lasSolicitudes.size();
				int totalEmpresas = lasEmpresas == null ? 0 : lasEmpresas.size();

				return new int[] {totalOfertas, totalSolicitantes, totalPostulaciones, totalEmpresas};
			}

			@Override
			protected void done() {
				try {
					int[] datos = get();

					lblTotalOfertas.setText(String.valueOf(datos[0]));
					lblTotalSolicitantes.setText(String.valueOf(datos[1]));
					lblTotalPostulaciones.setText(String.valueOf(datos[2]));
					lblTotalEmpresas.setText(String.valueOf(datos[3]));

					lblEstado.setText("Basado en los datos actuales de la plataforma");

				} catch (Exception e) {
					Throwable causa = e.getCause();
					String mensaje = causa != null ? causa.getMessage() : e.getMessage();

					e.printStackTrace();

					lblEstado.setText("No se pudieron cargar los datos");

					JOptionPane.showMessageDialog(
						ReporteActividadGeneral.this,
						mensaje != null ? mensaje : "No se pudo generar el reporte.",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);

				} finally {
					cerrarButton.setEnabled(true);
				}
			}
		};

		hilo.execute();
	}

	private JLabel crearLabelValor(Color color) {
		JLabel label = new JLabel("...");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Calibri", Font.BOLD, 26));
		label.setForeground(color);
		return label;
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