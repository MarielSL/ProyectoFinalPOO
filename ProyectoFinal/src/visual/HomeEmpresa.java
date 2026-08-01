package visual;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoOferta;
import logico.EstadoSolicitud;
import logico.Oferta;
import logico.SolicitudEmpleo;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class HomeEmpresa extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private Empresa empresa;
	private JLabel lblCandidatosComp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeEmpresa frame = new HomeEmpresa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeEmpresa() {
		if (BolsaEmpleo.getInstancia().getLoginUser() != null) {
			empresa = BolsaEmpleo.getInstancia().getLoginUser().getEmpresa();
		}
		setTitle("Home Empresa");
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

		{
			PanelRedond panelMenu = new PanelRedond(25);
			panelMenu.setBackground(new Color(0, 0, 51));
			panelMenu.setBounds(26, 20, anchoContenido, 70);
			panel.add(panelMenu);
			panelMenu.setLayout(null);


			String[] textosMenu = { "Inicio", "Publicar oferta", "Mis ofertas", "Ver perfil" };
			int[] anchosMenu = { 70, 150, 110, 90 };
			int espacioEntreItems = 40;

			int anchoTotalMenu = 0;
			for (int i = 0; i < anchosMenu.length; i++) {
				anchoTotalMenu += anchosMenu[i];
			}
			anchoTotalMenu += espacioEntreItems * (anchosMenu.length - 1);

			int xMenu = anchoContenido - anchoTotalMenu - 40;

			JLabel lblInicio = new JLabel(textosMenu[0]);
			lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicio.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblInicio.setForeground(Color.WHITE);
			lblInicio.setBounds(680, 26, anchosMenu[0], 20);
			panelMenu.add(lblInicio);
			xMenu += anchosMenu[0] + espacioEntreItems;

			JLabel lblPublicarOferta = new JLabel(textosMenu[1]);
			lblPublicarOferta.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblPublicarOferta.setForeground(Color.WHITE);
			lblPublicarOferta.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblPublicarOferta.setBounds(781, 26, 118, 20);
			lblPublicarOferta.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (empresa != null) {
						RegistrarOferta registrarOferta = new RegistrarOferta(empresa);
						registrarOferta.setVisible(true);
					}
				}
			});
			panelMenu.add(lblPublicarOferta);
			xMenu += anchosMenu[1] + espacioEntreItems;

			JLabel lblMisOfertas = new JLabel(textosMenu[2]);
			lblMisOfertas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(empresa!= null) {
						VerOfertasEmpresa verOfertas = new VerOfertasEmpresa();
						verOfertas.setVisible(true);
						dispose();
					}

				}
			});
			lblMisOfertas.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblMisOfertas.setForeground(Color.WHITE);
			lblMisOfertas.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblMisOfertas.setBounds(938, 26, 90, 20);
			panelMenu.add(lblMisOfertas);
			xMenu += anchosMenu[2] + espacioEntreItems;

			JLabel lblVerPerfil = new JLabel(textosMenu[3]);
			lblVerPerfil.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblVerPerfil.setForeground(Color.WHITE);
			lblVerPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblVerPerfil.setBounds(1080, 26, anchosMenu[3], 20);
			lblVerPerfil.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if(empresa != null) {
						VerUserEmpresa verUserEmpresa = new VerUserEmpresa();
						verUserEmpresa.setVisible(true);
						dispose();
					}

				}
			});
			panelMenu.add(lblVerPerfil);

			BotonRedond btnMenu = new BotonRedond("",25);
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BarraEmpresa menu = new BarraEmpresa();
					menu.setVisible(true);
					dispose();
				}
			});
			btnMenu.setBackground(new Color(0, 0, 51));
			btnMenu.setColorHover(new Color(0, 51, 102));
			btnMenu.setBounds(12, 4, 60, 60);
			colocarIconoBoton(btnMenu,"/img/menu-dots-vertical(White).png",25,25);
			btnMenu.setMargin(new Insets(0, 0, 0, 0));
			btnMenu.setBorderPainted(false);
			btnMenu.setContentAreaFilled(false);
			btnMenu.setFocusPainted(false);
			btnMenu.setOpaque(false);
			panelMenu.add(btnMenu);
			
			String nombreEmpresa = "Mi Empresa";
			if (empresa != null) {
				nombreEmpresa = empresa.getNombre();
			}
			int anchoNombre = 14 * nombreEmpresa.length() + 20;

			JLabel lblNombreEmpresa = new JLabel(nombreEmpresa);
			lblNombreEmpresa.setFont(new Font("Calibri", Font.BOLD, 24));
			lblNombreEmpresa.setForeground(Color.WHITE);
			lblNombreEmpresa.setBounds(1668, 25, anchoNombre, 20);
			panelMenu.add(lblNombreEmpresa);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(1741, -9, 114, 88);
			colocarImagen(lblNewLabel, "/img/iconoLogo_FondoOscuro.png");
			panelMenu.add(lblNewLabel);
		}

		int anchoTarjeta = (anchoContenido - 48) / 3;

		{
			PanelConSombra panelOfertasActivas = new PanelConSombra(18);
			panelOfertasActivas.setBackground(new Color(255, 224, 178));
			panelOfertasActivas.setBounds(margen, 110, anchoTarjeta, 90);
			panel.add(panelOfertasActivas);
			panelOfertasActivas.setLayout(null);

			JLabel lblOfertasActivas = new JLabel("Ofertas Activas");
			lblOfertasActivas.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblOfertasActivas.setForeground(new Color(204, 102, 0));
			lblOfertasActivas.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelOfertasActivas.add(lblOfertasActivas);

			JLabel lblOfertasActivasNum = new JLabel(String.valueOf(contarOfertasActivas()));
			lblOfertasActivasNum.setFont(new Font("Calibri", Font.BOLD, 30));
			lblOfertasActivasNum.setForeground(new Color(204, 102, 0));
			lblOfertasActivasNum.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelOfertasActivas.add(lblOfertasActivasNum);
		}

		{
			PanelConSombra panelSolicitudesPendientes = new PanelConSombra(18);
			panelSolicitudesPendientes.setBackground(new Color(195, 220, 255));
			panelSolicitudesPendientes.setBounds(margen + anchoTarjeta + 24, 110, anchoTarjeta, 90);
			panel.add(panelSolicitudesPendientes);
			panelSolicitudesPendientes.setLayout(null);

			JLabel lblSolicitudesPendientes = new JLabel("Candidatos Compatibles");
			lblSolicitudesPendientes.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblSolicitudesPendientes.setForeground(new Color(65, 95, 170));
			lblSolicitudesPendientes.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelSolicitudesPendientes.add(lblSolicitudesPendientes);

			lblCandidatosComp = new JLabel(String.valueOf(ContCandCompatibles()));
			lblCandidatosComp.setFont(new Font("Calibri", Font.BOLD, 30));
			lblCandidatosComp.setForeground(new Color(65, 95, 170));
			lblCandidatosComp.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelSolicitudesPendientes.add(lblCandidatosComp);
		}

		{
			PanelConSombra panelContratados = new PanelConSombra(18);
			panelContratados.setBackground(new Color(255, 205, 210));
			panelContratados.setBounds(margen + (anchoTarjeta + 24) * 2, 110, anchoTarjeta, 90);
			panel.add(panelContratados);
			panelContratados.setLayout(null);

			JLabel lblContratados = new JLabel("Contratados Este Mes");
			lblContratados.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblContratados.setForeground(new Color(198, 40, 40));
			lblContratados.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelContratados.add(lblContratados);

			JLabel lblContratadosNum = new JLabel(String.valueOf(contarContratadosEsteMes()));
			lblContratadosNum.setFont(new Font("Calibri", Font.BOLD, 30));
			lblContratadosNum.setForeground(new Color(198, 40, 40));
			lblContratadosNum.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelContratados.add(lblContratadosNum);
		}

		{
			int yTabla = 220;
			int altoTabla = dim.height - yTabla - 60;

			PanelConSombra panelSolicitudesRecientes = new PanelConSombra(20);
			panelSolicitudesRecientes.setBackground(Color.WHITE);
			panelSolicitudesRecientes.setBounds(26, 262, 1840, 761);
			panel.add(panelSolicitudesRecientes);
			panelSolicitudesRecientes.setLayout(null);
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setForeground(SystemColor.controlShadow);
			separator.setBackground(SystemColor.controlShadow);
			separator.setBounds(919, 67, 1, 600);
			panelSolicitudesRecientes.add(separator);
		}
		
		JLabel lblNewLabel = new JLabel("An\u00E1lisis de Coincidencias");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 26));
		lblNewLabel.setBounds(809, 233, 274, 25);
		panel.add(lblNewLabel);
	}

	private int contarOfertasActivas() {
		if (empresa == null) {
			return 0;
		}
		int contador = 0;
		for (Oferta oferta : empresa.getLasOfertas()) {
			if (oferta.getEstado() == EstadoOferta.PENDIENTE) {
				contador++;
			}
		}
		return contador;
	}

	private int ContCandCompatibles() {
		if (empresa == null) {
			return 0;
		}
		int cant = 0;
		for (Oferta oferta : empresa.getLasOfertas()) {
			cant += BolsaEmpleo.getInstancia().cantCandidatosCompatibles(oferta);
		}
		return cant;
	}

	private int contarContratadosEsteMes() {
		if (empresa == null) {
			return 0;
		}
		int contador = 0;
		LocalDate hoy = LocalDate.now();
		for (SolicitudEmpleo solicitud : BolsaEmpleo.getInstancia().getSolicitudes()) {
			if (solicitud.getEstado() == EstadoSolicitud.ACTIVA && solicitud.getFechaSolicitud().getMonthValue() == hoy.getMonthValue() && solicitud.getFechaSolicitud().getYear() == hoy.getYear()) {
				boolean compatible = false;
				for (Oferta oferta : empresa.getLasOfertas()) {
					if (BolsaEmpleo.getInstancia().calcCoincidencia(oferta, solicitud) >= 60) {
						compatible = true;
					}
				}
				if (compatible) {
					contador++;
				}
			}
		}
		return contador;
	}

	private ArrayList<SolicitudEmpleo> obtenerSolicitudesRecientes() {
		ArrayList<SolicitudEmpleo> todas = new ArrayList<SolicitudEmpleo>();
		if (empresa == null) {
			return todas;
		}
		for (SolicitudEmpleo solicitud : BolsaEmpleo.getInstancia().getSolicitudes()) {
			boolean compatible = false;
			for (Oferta oferta : empresa.getLasOfertas()) {
				if (oferta.getEstado() == EstadoOferta.PENDIENTE && BolsaEmpleo.getInstancia().calcCoincidencia(oferta, solicitud) >= 60) {
					compatible = true;
				}
			}
			if (compatible) {
				todas.add(solicitud);
			}
		}
		for (int i = 0; i < todas.size() - 1; i++) {
			for (int j = 0; j < todas.size() - 1 - i; j++) {
				if (todas.get(j).getFechaSolicitud().isBefore(todas.get(j + 1).getFechaSolicitud())) {
					SolicitudEmpleo temporal = todas.get(j);
					todas.set(j, todas.get(j + 1));
					todas.set(j + 1, temporal);
				}
			}
		}
		ArrayList<SolicitudEmpleo> recientes = new ArrayList<SolicitudEmpleo>();
		int limite = Math.min(5, todas.size());
		for (int i = 0; i < limite; i++) {
			recientes.add(todas.get(i));
		}
		return recientes;
	}

	/*private DefaultTableModel crearModeloSolicitudesRecientes() {
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Oferta", "Fecha", "Estado" }) {
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
		for (SolicitudEmpleo solicitud : obtenerSolicitudesRecientes()) {
			String nombreCandidato = solicitud.getCandidato().getNombre() + " " + solicitud.getCandidato().getApellido();
			String estadoTexto = formatearEstado(solicitud.getEstado());
			modelo.addRow(new Object[] { nombreCandidato, solicitud.getPuesto(), solicitud.getFechaSolicitud().format(formato), estadoTexto });
		}
		return modelo;
	}*/

	/*private String formatearEstado(EstadoSolicitud estado) {
		if (estado == EstadoSolicitud.CERRADA) {
			return "Aceptada";
		}
		if (estado == EstadoSolicitud.RECHAZADA) {
			return "Rechazada";
		}
		return "En Revisi\u00F3n";
	}*/

	private class RenderEstado extends JLabel implements TableCellRenderer {

		public RenderEstado() {
			setOpaque(false);
			setHorizontalAlignment(SwingConstants.CENTER);
			setFont(new Font("Calibri", Font.BOLD, 14));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
				boolean hasFocus, int row, int column) {
			String estado = "";
			if (value != null) {
				estado = value.toString();
			}
			setText(estado);

			if (estado.equals("Aceptada")) {
				setBackground(new Color(198, 239, 206));
				setForeground(new Color(46, 125, 50));
			} else if (estado.equals("Rechazada")) {
				setBackground(new Color(255, 205, 210));
				setForeground(new Color(198, 40, 40));
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

		Image imagenEscalada = icono.getImage().getScaledInstance(
				nuevoAncho,
				nuevoAlto,
				Image.SCALE_SMOOTH
				);

		ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

		label.setIcon(iconoEscalado);
		label.setText("");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
	}
}