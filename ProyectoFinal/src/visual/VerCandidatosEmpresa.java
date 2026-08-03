package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.Obrero;
import logico.Persona;
import logico.Tecnico;
import logico.Universitario;
import red.ConexionCliente;
import red.DatosMejorMatchCandidato;
import red.Peticion;
import red.Respuesta;

public class VerCandidatosEmpresa extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTable table;
	private DefaultTableModel modelo;
	private ArrayList<DatosMejorMatchCandidato> candidatosMostrados = new ArrayList<DatosMejorMatchCandidato>();
	private BotonRedond btnRefresh;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerCandidatosEmpresa frame = new VerCandidatosEmpresa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerCandidatosEmpresa() {
		setTitle("Candidatos");
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

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(245, 245, 245));
		panel.setLayout(null);

		int margen = 40;
		int anchoContenido = dim.width - (margen * 2);

		construirHeader(panel, margen, anchoContenido);
		construirContenido(panel, margen, anchoContenido);

		cargarCandidatosConHilo();
	}

	private void construirHeader(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelHeader = new PanelConSombra(25);
		panelHeader.setBackground(new Color(0, 0, 51));
		panelHeader.setBounds(0, 0, dim.width, 90);
		panel.add(panelHeader);
		panelHeader.setLayout(null);

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
				HomeEmpresa home = new HomeEmpresa();
				home.setVisible(true);
				dispose();
			}
		});
		panelHeader.add(btnAtras);

		JLabel lblTitulo = new JLabel("Candidatos");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTitulo.setForeground(new Color(255, 153, 0));
		lblTitulo.setBounds(74, 28, 400, 30);
		panelHeader.add(lblTitulo);
		
		btnRefresh = new BotonRedond("Actualizar", 30);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCandidatosConHilo();
			}
		});
		btnRefresh.setForeground(new Color(225, 239, 254));
		btnRefresh.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRefresh.setBackground(new Color(0, 0, 51));
		btnRefresh.setBounds(1620, 26, 143, 40);
		panelHeader.add(btnRefresh);
	}

	private void construirContenido(JPanel panel, int margen, int anchoContenido) {
		int yContenido = 110;
		int altoContenido = dim.height - yContenido - 60;

		PanelConSombra panelContenedor = new PanelConSombra(20);
		panelContenedor.setBackground(Color.WHITE);
		panelContenedor.setBounds(margen, yContenido, anchoContenido, altoContenido);
		panel.add(panelContenedor);
		panelContenedor.setLayout(new BorderLayout());

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre", "Fecha de Registro", "Especialidad", "Mejor % Match", "Oferta" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		table = new JTable(modelo);
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(34);
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (fila < 0 || fila >= candidatosMostrados.size()) {
					return;
				}
				DatosMejorMatchCandidato datos = candidatosMostrados.get(fila);
				VerPostulante ventana = new VerPostulante(datos.getCandidato(), datos.getMejorOferta(), datos.getSolicitud(), datos.getMejorPorcentaje());
				ventana.setVisible(true);
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(null);
		panelContenedor.add(scrollPane, BorderLayout.CENTER);
	}

	private void cargarCandidatosConHilo() {
		SwingWorker<ArrayList<DatosMejorMatchCandidato>, Void> worker = new SwingWorker<ArrayList<DatosMejorMatchCandidato>, Void>() {
			@Override
			protected ArrayList<DatosMejorMatchCandidato> doInBackground() throws Exception {
				Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_MEJOR_MATCH_EMPRESA, null);
				Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

				if (!respuesta.isExito()) {
					throw new IllegalArgumentException(respuesta.getDatos().toString());
				}

				return (ArrayList<DatosMejorMatchCandidato>) respuesta.getDatos();
			}

			@Override
			protected void done() {
				try {
					ArrayList<DatosMejorMatchCandidato> candidatos = get();
					candidatosMostrados = candidatos;

					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
					modelo.setRowCount(0);

					for (DatosMejorMatchCandidato datos : candidatos) {
						Persona candidato = datos.getCandidato();
						if (candidato == null) {
							continue;
						}

						String fecha = (candidato.getUser() != null && candidato.getUser().getFechaRegistro() != null)
								? candidato.getUser().getFechaRegistro().format(formato)
								: "N/A";

						modelo.addRow(new Object[] {
								candidato.getNombre() + " " + candidato.getApellido(),
								fecha,
								obtenerEspecialidad(candidato),
								String.format("%.1f%%", datos.getMejorPorcentaje()),
								datos.getMejorOferta() != null ? datos.getMejorOferta().getPuesto() : "N/A"
						});
					}

				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(VerCandidatosEmpresa.this, "No se pudieron cargar los candidatos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		worker.execute();
	}

	private String obtenerEspecialidad(Persona candidato) {
		if (candidato instanceof Universitario) {
			return ((Universitario) candidato).getCarrera();
		}
		if (candidato instanceof Tecnico) {
			return ((Tecnico) candidato).getTecnico();
		}
		if (candidato instanceof Obrero) {
			return ((Obrero) candidato).getHabilidades();
		}
		return "N/A";
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