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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Persona;

public class ReporteSolicitantes extends JDialog {

	public static void main(String[] args) {
		try {
			ReporteSolicitantes dialog = new ReporteSolicitantes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ReporteSolicitantes() {
		setTitle("Reporte de Solicitantes");
		setBounds(100, 100, 427, 225);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPanel.setLayout(new BorderLayout(0, 10));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("Resumen de solicitantes registrados:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPanel.add(lblTitulo, BorderLayout.NORTH);

		ArrayList<Persona> lasPersonas = BolsaEmpleo.getInstancia().getPersonas();
		int total = lasPersonas == null ? 0 : lasPersonas.size();
		int disponibles = 0;
		if (lasPersonas != null) {
			for (Persona persona : lasPersonas) {
				if (!persona.isEstadoEmpleo()) {
					disponibles++;
				}
			}
		}

		JPanel panelStats = new JPanel();
		panelStats.setLayout(new GridLayout(1, 2, 20, 0));
		panelStats.add(crearBloque("Total de solicitantes", String.valueOf(total), new Color(0, 120, 0)));
		panelStats.add(crearBloque("Buscando empleo", String.valueOf(disponibles), new Color(204, 102, 0)));
		contentPanel.add(panelStats, BorderLayout.CENTER);

		JLabel lblCantidad = new JLabel("Basado en los datos actuales de la plataforma");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setFont(new Font("Arial", Font.ITALIC, 11));
		contentPanel.add(lblCantidad, BorderLayout.SOUTH);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cerrarButton);
	}

	private JPanel crearBloque(String titulo, String valor, Color color) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 5));

		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
		panel.add(lblTitulo, BorderLayout.NORTH);

		JLabel lblValor = new JLabel(valor);
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Arial", Font.BOLD, 28));
		lblValor.setForeground(color);
		panel.add(lblValor, BorderLayout.CENTER);

		return panel;
	}
}