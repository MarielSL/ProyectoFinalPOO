package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import logico.BolsaEmpleo;
import logico.TipoUser;
import logico.Usuario;

public class VerUsuariosAdmin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private Dimension dim;
	private ComboBoxRedond<String> cbxRol;
	private JPanel pnlVacio;
	private JPanel pnlTabla;
	private JLabel lblIlustracion;
	private JTable table;
	private TableRowSorter<DefaultTableModel> sorterUsuarios;
	private DefaultTableModel modeloUsuarios;
	private JLabel lblTotalUsuariosNum;
	private JLabel lblEmpresasNum;
	private JLabel lblCandidatosNum;
	private BotonRedond btnAtras;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VerUsuariosAdmin frame = new VerUsuariosAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerUsuariosAdmin() {
		setTitle("Usuarios");
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		setLocationRelativeTo(null);

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
		construirTarjetas(panel, margen, anchoContenido);
		construirBusqueda(panel, margen, anchoContenido);
		construirContenido(panel, margen, anchoContenido);

		cargarUsuariosConHilo();
	}

	private void construirHeader(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelHeader = new PanelConSombra(25);
		panelHeader.setBackground(new Color(0, 0, 51));
		panelHeader.setBounds(0, 0, 1920, 90);
		panel.add(panelHeader);
		panelHeader.setLayout(null);

		btnAtras = new BotonRedond("", 18);
		btnAtras.setBackground(new Color(0, 0, 51));
		btnAtras.setBounds(12, 20, 46, 46);
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setFocusPainted(false);
		btnAtras.setOpaque(false);
		colocarIconoBoton(btnAtras, "/img/menu-dots-vertical(White).png", 25, 25);

		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarraAdmin home = new BarraAdmin();
				home.setVisible(true);
				dispose();
			}
		});

		panelHeader.add(btnAtras);

		JLabel lblTitulo = new JLabel("Usuarios");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 35));
		lblTitulo.setForeground(new Color(255, 51, 51));
		lblTitulo.setBounds(77, 28, 400, 30);
		panelHeader.add(lblTitulo);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(1805, 0, 86, 90);
		colocarImagen(lblLogo, "/img/iconoLogo_FondoOscuro.png");
		panelHeader.add(lblLogo);
	}

	private void construirTarjetas(JPanel panel, int margen, int anchoContenido) {
		int anchoBoton = 260;
		int anchoCards = anchoContenido - anchoBoton - 40;
		int anchoTarjeta = (anchoCards - 24) / 2;

		PanelConSombra panelTotalUsuarios = new PanelConSombra(18);
		panelTotalUsuarios.setBackground(new Color(153, 204, 255));
		panelTotalUsuarios.setBounds(113, 110, 485, 90);
		panel.add(panelTotalUsuarios);
		panelTotalUsuarios.setLayout(null);

		JLabel lblIconoTotal = new JLabel();
		lblIconoTotal.setBounds(12, 23, 40, 40);
		colocarImagen(lblIconoTotal, "/img/user_azul.png");
		panelTotalUsuarios.add(lblIconoTotal);

		JLabel lblTotalUsuarios = new JLabel("Total de Usuarios");
		lblTotalUsuarios.setFont(new Font("Calibri", Font.BOLD, 17));
		lblTotalUsuarios.setForeground(new Color(65, 95, 170));
		lblTotalUsuarios.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelTotalUsuarios.add(lblTotalUsuarios);

		lblTotalUsuariosNum = new JLabel("...");
		lblTotalUsuariosNum.setFont(new Font("Calibri", Font.BOLD, 35));
		lblTotalUsuariosNum.setForeground(new Color(65, 95, 170));
		lblTotalUsuariosNum.setBounds(64, 34, 72, 36);
		panelTotalUsuarios.add(lblTotalUsuariosNum);

		PanelConSombra panelEmpresas = new PanelConSombra(18);
		panelEmpresas.setBackground(new Color(198, 239, 206));
		panelEmpresas.setBounds(711, 110, 477, 90);
		panel.add(panelEmpresas);
		panelEmpresas.setLayout(null);

		JLabel lblIconoEmpresas = new JLabel();
		lblIconoEmpresas.setBounds(12, 23, 40, 40);
		colocarImagen(lblIconoEmpresas, "/img/maletin_verde.png");
		panelEmpresas.add(lblIconoEmpresas);

		JLabel lblEmpresas = new JLabel("Empresas");
		lblEmpresas.setFont(new Font("Calibri", Font.BOLD, 17));
		lblEmpresas.setForeground(new Color(46, 125, 50));
		lblEmpresas.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelEmpresas.add(lblEmpresas);

		lblEmpresasNum = new JLabel("...");
		lblEmpresasNum.setFont(new Font("Calibri", Font.BOLD, 35));
		lblEmpresasNum.setForeground(new Color(46, 125, 50));
		lblEmpresasNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelEmpresas.add(lblEmpresasNum);

		PanelConSombra panelCandidatos = new PanelConSombra(18);
		panelCandidatos.setLayout(null);
		panelCandidatos.setBackground(new Color(255, 224, 178));
		panelCandidatos.setBounds(1301, 110, 477, 90);
		panel.add(panelCandidatos);

		JLabel lblIconoCandidatos = new JLabel();
		lblIconoCandidatos.setBounds(12, 23, 40, 40);
		colocarImagen(lblIconoCandidatos, "/img/candidatos_naranja.png");
		panelCandidatos.add(lblIconoCandidatos);

		JLabel lblCandidatos = new JLabel("Candidatos");
		lblCandidatos.setForeground(new Color(204, 102, 0));
		lblCandidatos.setFont(new Font("Calibri", Font.BOLD, 17));
		lblCandidatos.setBounds(64, 12, 674, 20);
		panelCandidatos.add(lblCandidatos);

		lblCandidatosNum = new JLabel("...");
		lblCandidatosNum.setForeground(new Color(204, 102, 0));
		lblCandidatosNum.setFont(new Font("Calibri", Font.BOLD, 35));
		lblCandidatosNum.setBounds(64, 34, 674, 36);
		panelCandidatos.add(lblCandidatosNum);
	}

	private void construirBusqueda(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelBusqueda = new PanelConSombra(18);
		panelBusqueda.setBackground(Color.WHITE);
		panelBusqueda.setBounds(margen, 220, anchoContenido, 60);
		panel.add(panelBusqueda);
		panelBusqueda.setLayout(null);

		JLabel lblRol = new JLabel("Rol");
		lblRol.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblRol.setForeground(new Color(120, 120, 120));
		lblRol.setBounds(anchoContenido - 220, 4, 190, 16);
		panelBusqueda.add(lblRol);

		cbxRol = new ComboBoxRedond<String>(15);
		cbxRol.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxRol.setForeground(Color.BLACK);
		cbxRol.setBackground(Color.WHITE);
		cbxRol.setModel(new DefaultComboBoxModel<String>(new String[] {"Todos", "Candidato", "Empresa"}));
		cbxRol.setSelectedIndex(0);
		cbxRol.setBounds(anchoContenido - 220, 20, 190, 28);
		panelBusqueda.add(cbxRol);

		cbxRol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aplicarFiltroRol();
			}
		});
	}

	private void construirContenido(JPanel panel, int margen, int anchoContenido) {
		int yContenido = 296;
		int altoContenido = dim.height - yContenido - 60;

		PanelConSombra panelContenedor = new PanelConSombra(20);
		panelContenedor.setBackground(Color.WHITE);
		panelContenedor.setBounds(margen, yContenido, anchoContenido, altoContenido);
		panel.add(panelContenedor);
		panelContenedor.setLayout(null);

		pnlVacio = crearEstadoVacio();
		pnlVacio.setBounds(0, 0, anchoContenido, altoContenido);
		panelContenedor.add(pnlVacio);

		pnlTabla = crearTabla(anchoContenido, altoContenido);
		pnlTabla.setBounds(0, 0, anchoContenido, altoContenido);
		pnlTabla.setVisible(false);
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

		JLabel lblTitulo = new JLabel("Aún no hay usuarios registrados");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setBounds(0, 220, 1, 1);
		panelVacio.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Cuando se registren usuarios en la plataforma aparecerán aquí.");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(130, 130, 130));
		lblSubtitulo.setBounds(0, 250, 1, 1);
		panelVacio.add(lblSubtitulo);

		panelVacio.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
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

		modeloUsuarios = crearModeloUsuariosVacio();

		table = new JTable();
		table.setModel(modeloUsuarios);
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(38);
		table.setForeground(new Color(50, 50, 50));
		table.setSelectionBackground(new Color(240, 240, 245));
		table.setShowGrid(false);
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
		table.getTableHeader().setForeground(new Color(0, 0, 51));
		table.setDefaultRenderer(Object.class, new RenderCentrado());
		table.getColumnModel().getColumn(2).setCellRenderer(new RenderBadge());

		sorterUsuarios = new TableRowSorter<DefaultTableModel>(modeloUsuarios);
		table.setRowSorter(sorterUsuarios);

		JScrollPane scrollTabla = new JScrollPane(table);
		scrollTabla.setBorder(null);
		scrollTabla.setBounds(24, 20, ancho - 48, alto - 40);
		panelTabla.add(scrollTabla);

		return panelTabla;
	}

	private void cargarUsuariosConHilo() {
		lblTotalUsuariosNum.setText("...");
		lblEmpresasNum.setText("...");
		lblCandidatosNum.setText("...");

		cbxRol.setEnabled(false);
		table.setEnabled(false);
		btnAtras.setEnabled(false);

		modeloUsuarios.setRowCount(0);
		pnlVacio.setVisible(false);
		pnlTabla.setVisible(true);

		SwingWorker<Object[], Void> hilo = new SwingWorker<Object[], Void>() {

			@Override
			protected Object[] doInBackground() throws Exception {
				ArrayList<Usuario> usuariosOriginales = BolsaEmpleo.getInstancia().getUsuarios();
				ArrayList<Usuario> usuarios = usuariosOriginales == null ? new ArrayList<Usuario>() : new ArrayList<Usuario>(usuariosOriginales);

				int totalUsuarios = usuarios.size();
				int totalEmpresas = 0;
				int totalCandidatos = 0;

				ArrayList<Object[]> filas = new ArrayList<Object[]>();
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");

				for (Usuario usuario : usuarios) {
					if (usuario == null) {
						continue;
					}

					if (usuario.getTipoUser() == TipoUser.EMPRESA) {
						totalEmpresas++;
					}

					if (usuario.getTipoUser() == TipoUser.CANDIDATO) {
						totalCandidatos++;
					}

					String username = textoSeguro(usuario.getUsername());
					String correo = textoSeguro(usuario.getCorreo());
					String rol = formatearRol(usuario.getTipoUser());

					String fechaRegistro = usuario.getFechaRegistro() != null
							? usuario.getFechaRegistro().format(formato)
									: "No disponible";

							filas.add(new Object[] {
									username,
									correo,
									rol,
									fechaRegistro
							});
				}

				

				return new Object[] {
						totalUsuarios,
						totalEmpresas,
						totalCandidatos,
						filas
				};
			}

			@Override
			protected void done() {
				try {
					Object[] datos = get();

					int totalUsuarios = (Integer) datos[0];
					int totalEmpresas = (Integer) datos[1];
					int totalCandidatos = (Integer) datos[2];

					@SuppressWarnings("unchecked")
					ArrayList<Object[]> filas = (ArrayList<Object[]>) datos[3];

					lblTotalUsuariosNum.setText(String.valueOf(totalUsuarios));
					lblEmpresasNum.setText(String.valueOf(totalEmpresas));
					lblCandidatosNum.setText(String.valueOf(totalCandidatos));

					modeloUsuarios.setRowCount(0);

					for (Object[] fila : filas) {
						modeloUsuarios.addRow(fila);
					}

					boolean hayUsuarios = totalUsuarios > 0;

					pnlVacio.setVisible(!hayUsuarios);
					pnlTabla.setVisible(hayUsuarios);

					table.revalidate();
					table.repaint();

					aplicarFiltroRol();

				} catch (Exception e) {
					Throwable causa = e.getCause();
					String mensaje = causa != null ? causa.getMessage() : e.getMessage();

					e.printStackTrace();

					mostrarDatosVacios();

					JOptionPane.showMessageDialog(
							VerUsuariosAdmin.this,
							mensaje != null ? mensaje : "No se pudieron cargar los usuarios.",
									"Error",
									JOptionPane.ERROR_MESSAGE
							);

				} finally {
					cbxRol.setEnabled(true);
					table.setEnabled(true);
					btnAtras.setEnabled(true);
				}
			}
		};

		hilo.execute();
	}

	private void mostrarDatosVacios() {
		lblTotalUsuariosNum.setText("0");
		lblEmpresasNum.setText("0");
		lblCandidatosNum.setText("0");

		modeloUsuarios.setRowCount(0);

		pnlVacio.setVisible(true);
		pnlTabla.setVisible(false);
	}

	private DefaultTableModel crearModeloUsuariosVacio() {
		return new DefaultTableModel(new Object[][] {}, new String[] {"Usuario", "Correo", "Rol", "Registro"}) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
	}

	private String formatearRol(TipoUser tipo) {
		if (tipo == TipoUser.ADMINISTRADOR) {
			return "Administrador";
		}

		if (tipo == TipoUser.EMPRESA) {
			return "Empresa";
		}

		if (tipo == TipoUser.CANDIDATO) {
			return "Candidato";
		}

		return "N/A";
	}

	private String textoSeguro(String texto) {
		return texto == null || texto.trim().isEmpty() ? "No disponible" : texto.trim();
	}

	private void aplicarFiltroRol() {
		if (sorterUsuarios == null) {
			return;
		}

		String seleccionado = (String) cbxRol.getSelectedItem();

		if (seleccionado == null || seleccionado.equals("Todos")) {
			sorterUsuarios.setRowFilter(null);
		} else {
			sorterUsuarios.setRowFilter(RowFilter.regexFilter("(?i)^" + seleccionado + "$", 2));
		}
	}

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

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		if (boton == null || ruta == null) {
			return;
		}

		java.net.URL recurso = getClass().getResource(ruta);

		if (recurso == null) {
			System.err.println("No se encontró el icono: " + ruta);
			return;
		}

		ImageIcon icono = new ImageIcon(recurso);
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

		boton.setIcon(new ImageIcon(imagenEscalada));
	}

	public class RenderCentrado extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public RenderCentrado() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	private class RenderBadge extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public RenderBadge() {
			setOpaque(false);
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("Calibri", Font.BOLD, 14));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			String rol = "";

			if (value != null) {
				rol = value.toString();
			}

			setText(rol);

			if (rol.equals("Administrador")) {
				setBackground(new Color(255, 205, 210));
				setForeground(new Color(198, 40, 40));
			} else if (rol.equals("Empresa")) {
				setBackground(new Color(198, 239, 206));
				setForeground(new Color(46, 125, 50));
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
}