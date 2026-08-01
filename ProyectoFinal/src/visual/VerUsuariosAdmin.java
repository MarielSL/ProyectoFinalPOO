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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import logico.BolsaEmpleo;
import logico.TipoUser;
import logico.Usuario;
import visual.BotonRedond;
import visual.ComboBoxRedond;
import visual.HomeAdministrador;
import visual.PanelConSombra;
import visual.TextFieldRedond;
import visual.Utilidades;

public class VerUsuariosAdmin extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private ComboBoxRedond<String> cbxRol;
	private JPanel pnlVacio;
	private JPanel pnlTabla;
	private JLabel lblIlustracion;
	private JTable table;
	private javax.swing.table.TableRowSorter<DefaultTableModel> sorterUsuarios; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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

		PanelConSombra panelConSombra = new PanelConSombra(18);
		panelConSombra.setLayout(null);
		panelConSombra.setBackground(new Color(255, 224, 178));
		panelConSombra.setBounds(1301, 110, 477, 90);
		panel.add(panelConSombra);

		JLabel label = new JLabel();
		label.setBounds(12, 23, 40, 40);
		colocarImagen(label,"/img/candidatos_naranja.png");
		panelConSombra.add(label);

		JLabel lblCandidatos = new JLabel("Candidatos");
		lblCandidatos.setForeground(new Color(204, 102, 0));
		lblCandidatos.setFont(new Font("Calibri", Font.BOLD, 17));
		lblCandidatos.setBounds(64, 12, 674, 20);
		panelConSombra.add(lblCandidatos);

		JLabel label_2 = new JLabel(String.valueOf(contarCandidatos()));
		label_2.setForeground(new Color(204, 102, 0));
		label_2.setFont(new Font("Calibri", Font.BOLD, 35));
		label_2.setBounds(64, 34, 674, 36);
		panelConSombra.add(label_2);

		cargarDatos();
	}

	private void construirHeader(JPanel panel, int margen, int anchoContenido) {
		PanelConSombra panelHeader = new PanelConSombra(25);
		panelHeader.setBackground(new Color(0, 0, 51));
		panelHeader.setBounds(0, 0, 1920, 90);
		panel.add(panelHeader);
		panelHeader.setLayout(null);

		BotonRedond btnAtras = new BotonRedond("", 18);
		btnAtras.setBackground(new Color(0, 0, 51));
		btnAtras.setBounds(12, 20, 46, 46);
		btnAtras.setBorderPainted(false);
		btnAtras.setContentAreaFilled(false);
		btnAtras.setFocusPainted(false);
		btnAtras.setOpaque(false);
		colocarIconoBoton(btnAtras,"/img/menu-dots-vertical(White).png",25,25);
		btnAtras.addActionListener(new ActionListener() {
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
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(1805, 0, 86, 90);
		colocarImagen(lblNewLabel,"/img/iconoLogo_FondoOscuro.png");
		panelHeader.add(lblNewLabel);


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

		JLabel lblTotalUsuariosNum = new JLabel(String.valueOf(contarTotalUsuarios()));
		lblTotalUsuariosNum.setFont(new Font("Calibri", Font.BOLD, 35));
		lblTotalUsuariosNum.setForeground(new Color(65, 95, 170));
		lblTotalUsuariosNum.setBounds(64, 34, 72, 36);
		panelTotalUsuarios.add(lblTotalUsuariosNum);

		PanelConSombra panelAdministradores = new PanelConSombra(18);
		panelAdministradores.setBackground(new Color(198, 239, 206));
		panelAdministradores.setBounds(711, 110, 477, 90);
		panel.add(panelAdministradores);
		panelAdministradores.setLayout(null);

		JLabel lblIconoAdmins = new JLabel();
		lblIconoAdmins.setBounds(12, 23, 40, 40);
		colocarImagen(lblIconoAdmins, "/img/maletin_verde.png");
		panelAdministradores.add(lblIconoAdmins);

		JLabel lblAdministradores = new JLabel("Empresas");
		lblAdministradores.setFont(new Font("Calibri", Font.BOLD, 17));
		lblAdministradores.setForeground(new Color(46, 125, 50));
		lblAdministradores.setBounds(64, 12, anchoTarjeta - 84, 20);
		panelAdministradores.add(lblAdministradores);

		JLabel lblAdministradoresNum = new JLabel(String.valueOf(contarEmpresas()));
		lblAdministradoresNum.setFont(new Font("Calibri", Font.BOLD, 35));
		lblAdministradoresNum.setForeground(new Color(46, 125, 50));
		lblAdministradoresNum.setBounds(64, 34, anchoTarjeta - 84, 36);
		panelAdministradores.add(lblAdministradoresNum);
		
		JLabel icono = new JLabel("");
		icono.setBounds(1784, 0, 114, 88);
		colocarImagen(icono, "/img/iconoLogo_FondoOscuro.png");
		panelAdministradores.add(icono);
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
		cbxRol.setModel(new DefaultComboBoxModel<String>(new String[] { "Todos", "Candidato", "Empresa"}));
		cbxRol.setSelectedIndex(0);
		cbxRol.setBounds(anchoContenido - 220, 20, 190, 28);
		panelBusqueda.add(cbxRol);	
		cbxRol.addActionListener(new ActionListener() {  
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

		JLabel lblTitulo = new JLabel("Aun no hay usuarios registrados");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setBounds(0, 220, 1, 1);
		panelVacio.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Cuando se registren usuarios en la plataforma apareceran aqui.");
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSubtitulo.setForeground(new Color(130, 130, 130));
		lblSubtitulo.setBounds(0, 250, 1, 1);
		panelVacio.add(lblSubtitulo);

		panelVacio.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent e) {
				int ancho = panelVacio.getWidth();
				lblIlustracion.setBounds((ancho - 220) / 2, 30, 220, 180);
				colocarImagen(lblIlustracion, "/img/usuariosvacios.png");
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
		table.setModel(crearModeloUsuarios());
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(38);
		table.setForeground(new Color(50, 50, 50));
		table.setSelectionBackground(new Color(240, 240, 245));
		table.setShowGrid(false);
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 15));
		table.getTableHeader().setForeground(new Color(0, 0, 51));
		table.setDefaultRenderer(Object.class, new RenderCentrado());
		table.getColumnModel().getColumn(2).setCellRenderer(new RenderBadge());

		sorterUsuarios = new javax.swing.table.TableRowSorter<DefaultTableModel>((DefaultTableModel) table.getModel()); 
		table.setRowSorter(sorterUsuarios);

		JScrollPane scrollTabla = new JScrollPane(table);
		scrollTabla.setBorder(null);
		scrollTabla.setBounds(24, 20, ancho - 48, alto - 40);
		panelTabla.add(scrollTabla);

		return panelTabla;
	}

	private void cargarDatos() {
		ArrayList<Usuario> losUsuarios = BolsaEmpleo.getInstancia().getUsuarios();

		if (losUsuarios == null || losUsuarios.isEmpty()) {
			pnlVacio.setVisible(true);
			pnlTabla.setVisible(false);
			return;
		}
		pnlVacio.setVisible(false);
		pnlTabla.setVisible(true);
	}

	private int contarTotalUsuarios() {
		ArrayList<Usuario> losUsuarios = BolsaEmpleo.getInstancia().getUsuarios();
		if (losUsuarios == null) {
			return 0;
		}
		return losUsuarios.size();
	}

	private int contarCandidatos() {
		ArrayList<Usuario> losUsuarios = BolsaEmpleo.getInstancia().getUsuarios();
		if (losUsuarios == null) {
			return 0;
		}
		int contador = 0;
		for (Usuario usuario : losUsuarios) {
			if (usuario.getTipoUser() == TipoUser.CANDIDATO) {
				contador++;
			}
		}
		return contador;
	}

	private int contarEmpresas() {
		ArrayList<Usuario> losUsuarios = BolsaEmpleo.getInstancia().getUsuarios();
		if (losUsuarios == null) {
			return 0;
		}
		int contador = 0;
		for (Usuario usuario : losUsuarios) {
			if (usuario.getTipoUser() == TipoUser.EMPRESA) {
				contador++;
			}
		}
		return contador;
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

	private DefaultTableModel crearModeloUsuarios() {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Usuario", "Correo", "Rol", "Registro" }) {
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
		ArrayList<Usuario> losUsuarios = BolsaEmpleo.getInstancia().getUsuarios();
		if (losUsuarios == null) {
			return modelo;
		}
		for (Usuario usuario : losUsuarios) {
			modelo.addRow(new Object[] { usuario.getUsername(), usuario.getCorreo(), formatearRol(usuario.getTipoUser()), usuario.getFechaRegistro().format(formato) });
		}
		return modelo;
	}
	
	public class RenderCentrado extends DefaultTableCellRenderer {
		public RenderCentrado() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	private class RenderBadge extends JLabel implements TableCellRenderer {

		public RenderBadge() {
			setOpaque(false);
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("Calibri", Font.BOLD, 14));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
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

		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(getBackground());

			int margenVertical = 6;
			int margenHorizontal = 10;
			g2.fillRoundRect(margenHorizontal, margenVertical,
					getWidth() - margenHorizontal * 2, getHeight() - margenVertical * 2, 16, 16);
			g2.dispose();

			super.paintComponent(g);
		}
	}

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagenEscalada));
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
}