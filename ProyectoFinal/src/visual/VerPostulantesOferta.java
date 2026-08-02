package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.DecisionCandidato;
import logico.EstadoDecision;
import logico.Obrero;
import logico.Oferta;
import logico.Persona;
import logico.ResultMatch;
import logico.Sexo;
import logico.SolicitudEmpleo;
import logico.Tecnico;
import logico.Universitario;
import red.ConexionCliente;
import red.DatosObtenerMatch;
import red.Peticion;
import red.Respuesta;

public class VerPostulantesOferta extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private final ArrayList<ResultMatch> candidatosMostrados = new ArrayList<ResultMatch>();
	private Dimension dim;
	private Oferta oferta;
	private ArrayList<ResultMatch> resultadosMatch = new ArrayList<ResultMatch>();
	private PanelRedond panelInfo;
	private PanelRedond panelTop1;
	private PanelRedond panelTop2;
	private PanelRedond panelTop3;
	private PanelRedond panelOtrosPostulantes;
	private JLabel lblEmpresa;
	private JLabel lblOferta;
	private JLabel lblPostulantes;
	private JLabel lblFotoTop1;
	private JLabel lblFotoTop2;
	private JLabel lblFotoTop3;
	private JLabel lblNombreTop1;
	private JLabel lblNombreTop2;
	private JLabel lblNombreTop3;
	private TextFieldRedond txtTipoTop1;
	private TextFieldRedond txtTipoTop2;
	private TextFieldRedond txtTipoTop3;
	private JLabel lblCoincidenciaTop1;
	private JLabel lblCoincidenciaTop2;
	private JLabel lblCoincidenciaTop3;
	private BotonRedond btnPerfilTop1;
	private BotonRedond btnPerfilTop2;
	private BotonRedond btnPerfilTop3;
	private BotonRedond btnVerTodos;
	private JTable table;
	private DefaultTableModel model;

	public static void main(String[] args) {
		try {
			VerPostulantesOferta dialog = new VerPostulantesOferta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VerPostulantesOferta(Oferta oferta) {
		this.oferta = oferta;

		setResizable(false);
		setTitle("Postulantes de la Oferta");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		Utilidades.aplicarIcono(this);

		dim = getToolkit().getScreenSize();

		setSize(dim.width, dim.height - 55);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBackground(new Color(0, 0, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		construirEncabezado();
		construirContenido();

		cargarInformacionConHilo();
	}

	private void construirEncabezado() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(dim.width - 115, -9, 114, 88);
		colocarImagen(lblLogo, "/img/iconoLogo_FondoOscuro.png");
		contentPanel.add(lblLogo);

		JLabel lblTitulo = new JLabel("Ranking de Candidatos");
		lblTitulo.setForeground(new Color(255, 153, 0));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 40));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds((dim.width - 500) / 2, 15, 500, 49);
		contentPanel.add(lblTitulo);

		BotonRedond btnMenu = new BotonRedond("", 30);
		btnMenu.setOpaque(false);
		btnMenu.setMargin(new Insets(0, 0, 0, 0));
		btnMenu.setFocusPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setBorderPainted(false);
		btnMenu.setBackground(new Color(0, 0, 51));
		btnMenu.setBounds(11, 12, 60, 60);

		colocarIconoBoton(btnMenu, "/img/menu-dots-vertical(White).png", 25, 25);

		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarraEmpresa menu = new BarraEmpresa();
				menu.setVisible(true);
				dispose();
			}
		});

		contentPanel.add(btnMenu);
	}

	private void construirContenido() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBounds(30, 89, dim.width - 80, dim.height - 145);
		panelPrincipal.setLayout(null);
		contentPanel.add(panelPrincipal);

		construirPanelInformacion(panelPrincipal);
		construirTituloTop(panelPrincipal);
		construirTop2(panelPrincipal);
		construirTop3(panelPrincipal);
		construirTop1(panelPrincipal);
		construirTablaResto(panelPrincipal);
		construirBotonVerTodos(panelPrincipal);
	}

	private void construirPanelInformacion(JPanel panelPrincipal) {
		panelInfo = new PanelRedond(30);
		panelInfo.setBackground(SystemColor.control);
		panelInfo.setBounds(22, 13, 966, 76);
		panelInfo.setLayout(null);
		panelPrincipal.add(panelInfo);

		JLabel lblBriefcase = new JLabel("");
		lblBriefcase.setBounds(12, 10, 56, 56);
		colocarImagen(lblBriefcase, "/img/briefcase.png");
		panelInfo.add(lblBriefcase);

		JLabel lblSubOferta = new JLabel("Oferta");
		lblSubOferta.setForeground(SystemColor.controlDkShadow);
		lblSubOferta.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSubOferta.setBounds(93, 10, 80, 20);
		panelInfo.add(lblSubOferta);

		lblOferta = new JLabel("Sin oferta");
		lblOferta.setForeground(new Color(0, 0, 51));
		lblOferta.setFont(new Font("Calibri", Font.BOLD, 22));
		lblOferta.setBounds(93, 30, 249, 32);
		panelInfo.add(lblOferta);

		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setForeground(SystemColor.controlShadow);
		separator1.setBounds(370, 10, 1, 56);
		panelInfo.add(separator1);

		JLabel lblEmpresaIcon = new JLabel("");
		lblEmpresaIcon.setBounds(390, 10, 56, 56);
		colocarImagen(lblEmpresaIcon, "/img/building.png");
		panelInfo.add(lblEmpresaIcon);

		JLabel lblSubEmpresa = new JLabel("Empresa");
		lblSubEmpresa.setForeground(SystemColor.controlDkShadow);
		lblSubEmpresa.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSubEmpresa.setBounds(471, 10, 80, 20);
		panelInfo.add(lblSubEmpresa);

		lblEmpresa = new JLabel("Sin empresa");
		lblEmpresa.setForeground(new Color(0, 0, 51));
		lblEmpresa.setFont(new Font("Calibri", Font.BOLD, 22));
		lblEmpresa.setBounds(471, 30, 249, 32);
		panelInfo.add(lblEmpresa);

		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setForeground(SystemColor.controlShadow);
		separator2.setBounds(747, 10, 1, 56);
		panelInfo.add(separator2);

		JLabel lblUsersIcon = new JLabel("");
		lblUsersIcon.setBounds(767, 10, 56, 56);
		colocarImagen(lblUsersIcon, "/img/users.png");
		panelInfo.add(lblUsersIcon);

		JLabel lblSubCandidatos = new JLabel("Candidatos");
		lblSubCandidatos.setForeground(SystemColor.controlDkShadow);
		lblSubCandidatos.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSubCandidatos.setBounds(848, 11, 100, 20);
		panelInfo.add(lblSubCandidatos);

		lblPostulantes = new JLabel("0");
		lblPostulantes.setForeground(new Color(0, 0, 51));
		lblPostulantes.setFont(new Font("Calibri", Font.BOLD, 22));
		lblPostulantes.setBounds(848, 30, 94, 32);
		panelInfo.add(lblPostulantes);
	}

	private void construirTituloTop(JPanel panelPrincipal) {
		JLabel lblTrophy = new JLabel("");
		lblTrophy.setBounds(22, 117, 70, 70);
		colocarImagen(lblTrophy, "/img/trophy.png");
		panelPrincipal.add(lblTrophy);

		JLabel lblTop = new JLabel("Top 3 Candidatos");
		lblTop.setForeground(new Color(0, 0, 51));
		lblTop.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTop.setBounds(119, 135, 300, 32);
		panelPrincipal.add(lblTop);
	}

	private void construirTop1(JPanel panelPrincipal) {
		panelTop1 = new PanelRedond(30);
		panelTop1.setBounds(704, 164, 400, 400);
		panelTop1.setLayout(null);
		panelTop1.setBackground(Color.decode("#fffbf5"));
		panelTop1.setColorBorde(Color.decode("#fe9703"));
		panelTop1.setGrosorBorde(1);
		panelPrincipal.add(panelTop1);

		TextFieldRedond txtPosicion = new TextFieldRedond(120);
		txtPosicion.setText("1");
		txtPosicion.setHorizontalAlignment(SwingConstants.CENTER);
		txtPosicion.setEditable(false);
		txtPosicion.setFocusable(false);
		txtPosicion.setFont(new Font("Calibri", Font.BOLD, 35));
		txtPosicion.setForeground(Color.decode("#ffecd0"));
		txtPosicion.setBackground(Color.decode("#fe9703"));
		txtPosicion.setBounds(25, 30, 45, 45);
		panelTop1.add(txtPosicion);

		lblFotoTop1 = new JLabel("");
		lblFotoTop1.setBounds(137, 36, 125, 125);
		panelTop1.add(lblFotoTop1);

		lblNombreTop1 = new JLabel("");
		lblNombreTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTop1.setForeground(new Color(0, 0, 51));
		lblNombreTop1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNombreTop1.setBounds(75, 175, 250, 25);
		panelTop1.add(lblNombreTop1);

		txtTipoTop1 = new TextFieldRedond(30);
		txtTipoTop1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoTop1.setForeground(Color.decode("#fe9703"));
		txtTipoTop1.setFont(new Font("Calibri", Font.PLAIN, 23));
		txtTipoTop1.setFocusable(false);
		txtTipoTop1.setEditable(false);
		txtTipoTop1.setBackground(Color.decode("#ffecd0"));
		txtTipoTop1.setBounds(125, 207, 148, 28);
		panelTop1.add(txtTipoTop1);

		JLabel lblCoincidencia = new JLabel("Coincidencia");
		lblCoincidencia.setForeground(new Color(0, 0, 51));
		lblCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 21));
		lblCoincidencia.setBounds(144, 253, 120, 20);
		panelTop1.add(lblCoincidencia);

		lblCoincidenciaTop1 = new JLabel("0%");
		lblCoincidenciaTop1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoincidenciaTop1.setForeground(Color.decode("#fe9703"));
		lblCoincidenciaTop1.setFont(new Font("Calibri", Font.BOLD, 38));
		lblCoincidenciaTop1.setBounds(130, 288, 140, 40);
		panelTop1.add(lblCoincidenciaTop1);

		btnPerfilTop1 = new BotonRedond("Ver Perfil", 30);
		btnPerfilTop1.setForeground(Color.decode("#fe9703"));
		btnPerfilTop1.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnPerfilTop1.setBackground(Color.decode("#ffecd0"));
		btnPerfilTop1.setColorHover(Color.decode("#fee4c0"));
		btnPerfilTop1.setBounds(113, 336, 172, 38);
		btnPerfilTop1.addActionListener(e -> abrirPerfilTop(0));
		panelTop1.add(btnPerfilTop1);
	}

	private void construirTop2(JPanel panelPrincipal) {
		panelTop2 = new PanelRedond(30);
		panelTop2.setBounds(249, 191, 350, 350);
		panelTop2.setLayout(null);
		panelTop2.setBackground(Color.decode("#f7faff"));
		panelTop2.setColorBorde(new Color(65, 95, 170));
		panelTop2.setGrosorBorde(1);
		panelPrincipal.add(panelTop2);

		TextFieldRedond txtPosicion = new TextFieldRedond(120);
		txtPosicion.setText("2");
		txtPosicion.setHorizontalAlignment(SwingConstants.CENTER);
		txtPosicion.setEditable(false);
		txtPosicion.setFocusable(false);
		txtPosicion.setFont(new Font("Calibri", Font.BOLD, 35));
		txtPosicion.setForeground(new Color(195, 220, 255));
		txtPosicion.setBackground(new Color(65, 95, 170));
		txtPosicion.setBounds(22, 25, 45, 45);
		panelTop2.add(txtPosicion);

		lblFotoTop2 = new JLabel("");
		lblFotoTop2.setBounds(125, 25, 100, 100);
		panelTop2.add(lblFotoTop2);

		lblNombreTop2 = new JLabel("");
		lblNombreTop2.setForeground(new Color(0, 0, 51));
		lblNombreTop2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTop2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNombreTop2.setBounds(50, 138, 250, 25);
		panelTop2.add(lblNombreTop2);

		txtTipoTop2 = new TextFieldRedond(30);
		txtTipoTop2.setEditable(false);
		txtTipoTop2.setFocusable(false);
		txtTipoTop2.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoTop2.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtTipoTop2.setForeground(new Color(65, 95, 170));
		txtTipoTop2.setBackground(new Color(195, 220, 255));
		txtTipoTop2.setBounds(109, 170, 132, 26);
		panelTop2.add(txtTipoTop2);

		JLabel lblCoincidencia = new JLabel("Coincidencia");
		lblCoincidencia.setForeground(new Color(0, 0, 51));
		lblCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCoincidencia.setBounds(129, 210, 110, 20);
		panelTop2.add(lblCoincidencia);

		lblCoincidenciaTop2 = new JLabel("0%");
		lblCoincidenciaTop2.setForeground(new Color(65, 95, 170));
		lblCoincidenciaTop2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoincidenciaTop2.setFont(new Font("Calibri", Font.BOLD, 35));
		lblCoincidenciaTop2.setBounds(110, 245, 130, 35);
		panelTop2.add(lblCoincidenciaTop2);

		btnPerfilTop2 = new BotonRedond("Ver Perfil", 30);
		btnPerfilTop2.setBackground(Color.decode("#e3ebfe"));
		btnPerfilTop2.setForeground(new Color(51, 102, 204));
		btnPerfilTop2.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnPerfilTop2.setBounds(99, 290, 152, 35);
		btnPerfilTop2.setColorHover(Color.decode("#d9e4ff"));
		btnPerfilTop2.addActionListener(e -> abrirPerfilTop(1));
		panelTop2.add(btnPerfilTop2);
	}

	private void construirTop3(JPanel panelPrincipal) {
		panelTop3 = new PanelRedond(30);
		panelTop3.setBounds(1206, 191, 350, 350);
		panelTop3.setLayout(null);
		panelTop3.setBackground(Color.decode("#fff8f8"));
		panelTop3.setColorBorde(Color.decode("#ff5757"));
		panelTop3.setGrosorBorde(1);
		panelPrincipal.add(panelTop3);

		TextFieldRedond txtPosicion = new TextFieldRedond(120);
		txtPosicion.setText("3");
		txtPosicion.setHorizontalAlignment(SwingConstants.CENTER);
		txtPosicion.setEditable(false);
		txtPosicion.setFocusable(false);
		txtPosicion.setFont(new Font("Calibri", Font.BOLD, 35));
		txtPosicion.setForeground(Color.decode("#fde7e7"));
		txtPosicion.setBackground(Color.decode("#ff5757"));
		txtPosicion.setBounds(22, 25, 45, 45);
		panelTop3.add(txtPosicion);

		lblFotoTop3 = new JLabel("");
		lblFotoTop3.setBounds(125, 25, 100, 100);
		panelTop3.add(lblFotoTop3);

		lblNombreTop3 = new JLabel("");
		lblNombreTop3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTop3.setForeground(new Color(0, 0, 51));
		lblNombreTop3.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNombreTop3.setBounds(50, 136, 250, 25);
		panelTop3.add(lblNombreTop3);

		txtTipoTop3 = new TextFieldRedond(30);
		txtTipoTop3.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoTop3.setForeground(Color.decode("#ff5757"));
		txtTipoTop3.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtTipoTop3.setFocusable(false);
		txtTipoTop3.setEditable(false);
		txtTipoTop3.setBackground(Color.decode("#fde7e7"));
		txtTipoTop3.setBounds(109, 168, 132, 26);
		panelTop3.add(txtTipoTop3);

		JLabel lblCoincidencia = new JLabel("Coincidencia");
		lblCoincidencia.setForeground(new Color(0, 0, 51));
		lblCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCoincidencia.setBounds(129, 208, 110, 20);
		panelTop3.add(lblCoincidencia);

		lblCoincidenciaTop3 = new JLabel("0%");
		lblCoincidenciaTop3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoincidenciaTop3.setForeground(Color.decode("#ff5757"));
		lblCoincidenciaTop3.setFont(new Font("Calibri", Font.BOLD, 35));
		lblCoincidenciaTop3.setBounds(110, 243, 130, 35);
		panelTop3.add(lblCoincidenciaTop3);

		btnPerfilTop3 = new BotonRedond("Ver Perfil", 30);
		btnPerfilTop3.setForeground(Color.decode("#ff5757"));
		btnPerfilTop3.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnPerfilTop3.setBackground(Color.decode("#fde7e7"));
		btnPerfilTop3.setColorHover(Color.decode("#fbdada"));
		btnPerfilTop3.setBounds(99, 288, 152, 35);
		btnPerfilTop3.addActionListener(e -> abrirPerfilTop(2));
		panelTop3.add(btnPerfilTop3);
	}

	private void construirTablaResto(JPanel panelPrincipal) {
		panelOtrosPostulantes = new PanelRedond(30);
		panelOtrosPostulantes.setBackground(Color.WHITE);
		panelOtrosPostulantes.setBounds(42, 591, 1367, 285);
		panelOtrosPostulantes.setLayout(null);
		panelOtrosPostulantes.setColorBorde(Color.LIGHT_GRAY);
		panelOtrosPostulantes.setGrosorBorde(1);
		panelPrincipal.add(panelOtrosPostulantes);

		JLabel lblResto = new JLabel("Resto de Candidatos");
		lblResto.setForeground(new Color(0, 0, 51));
		lblResto.setFont(new Font("Calibri", Font.BOLD, 22));
		lblResto.setBounds(34, 13, 250, 22);
		panelOtrosPostulantes.add(lblResto);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.scrollbar);
		separator.setBounds(18, 40, 1315, 2);
		panelOtrosPostulantes.add(separator);

		JPanel panelTabla = new JPanel();
		panelTabla.setBackground(Color.WHITE);
		panelTabla.setBounds(34, 62, 1297, 210);
		panelTabla.setLayout(new BorderLayout());
		panelOtrosPostulantes.add(panelTabla);

		String[] encabezados = {"Nombre", "Cat. Laboral", "Coincidencia", "Estado"};

		model = new DefaultTableModel(new Object[][] {}, encabezados) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};

		table = new JTable(model);
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(30);
		table.setForeground(new Color(0, 0, 51));
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
		table.getTableHeader().setForeground(new Color(0, 0, 51));

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirPerfilTabla();
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelTabla.add(scrollPane, BorderLayout.CENTER);
	}

	private void construirBotonVerTodos(JPanel panelPrincipal) {
		btnVerTodos = new BotonRedond("Ver Todos los Aplicantes", 30);
		btnVerTodos.setFont(new Font("Calibri", Font.BOLD, 20));
		btnVerTodos.setBounds(1482, 716, 272, 42);
		btnVerTodos.setBackground(Color.decode("#ffecd0"));
		btnVerTodos.setForeground(Color.decode("#fe9703"));
		btnVerTodos.setColorHover(Color.decode("#fee4c0"));

		btnVerTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (oferta == null) {
					return;
				}

				VerTodosPostulantesOferta ventana = new VerTodosPostulantesOferta(oferta);
				ventana.setVisible(true);
				dispose();
			}
		});

		panelPrincipal.add(btnVerTodos);
	}

	private void cargarInformacionConHilo() {
		prepararPantallaParaCarga();

		SwingWorker<ArrayList<ResultMatch>, Void> hilo = new SwingWorker<ArrayList<ResultMatch>, Void>() {
			@Override
			protected ArrayList<ResultMatch> doInBackground() throws Exception {
			    if (oferta == null) {
			        return new ArrayList<ResultMatch>();
			    }

			    DatosObtenerMatch datos = new DatosObtenerMatch(oferta.getId());
			    Peticion peticion = new Peticion(Peticion.Tipo.OBTENER_MATCH, datos);
			    Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

			    if (!respuesta.isExito()) {
			        throw new IllegalArgumentException(respuesta.getDatos().toString());
			    }

			    return (ArrayList<ResultMatch>) respuesta.getDatos();
			}

			@Override
			protected void done() {
				try {
					resultadosMatch = get();

					if (resultadosMatch == null) {
						resultadosMatch = new ArrayList<ResultMatch>();
					}

					mostrarInformacionOferta();
					mostrarTopCandidatos();
					cargarRestoPostulantes();

				} catch (Exception e) {
					Throwable causa = e.getCause();
					String mensaje = causa != null ? causa.getMessage() : e.getMessage();

					e.printStackTrace();

					mostrarEstadoSinResultados();

					JOptionPane.showMessageDialog(
							VerPostulantesOferta.this,
							mensaje != null ? mensaje : "No se pudieron cargar los candidatos.",
									"Error",
									JOptionPane.ERROR_MESSAGE
							);

				} finally {
					habilitarControles(true);
				}
			}
		};

		hilo.execute();
	}

	private void prepararPantallaParaCarga() {
		resultadosMatch.clear();
		candidatosMostrados.clear();

		if (model != null) {
			model.setRowCount(0);
		}

		lblPostulantes.setText("...");
		panelTop1.setVisible(false);
		panelTop2.setVisible(false);
		panelTop3.setVisible(false);

		habilitarControles(false);
	}

	private void habilitarControles(boolean habilitado) {
		if (btnPerfilTop1 != null) {
			btnPerfilTop1.setEnabled(habilitado);
		}

		if (btnPerfilTop2 != null) {
			btnPerfilTop2.setEnabled(habilitado);
		}

		if (btnPerfilTop3 != null) {
			btnPerfilTop3.setEnabled(habilitado);
		}

		if (btnVerTodos != null) {
			btnVerTodos.setEnabled(habilitado);
		}

		if (table != null) {
			table.setEnabled(habilitado);
		}
	}

	private void mostrarInformacionOferta() {
		if (oferta == null) {
			mostrarEstadoSinResultados();
			return;
		}

		if (oferta.getEmpresa() != null && oferta.getEmpresa().getNombre() != null) {
			lblEmpresa.setText(oferta.getEmpresa().getNombre());
		} else {
			lblEmpresa.setText("Sin empresa");
		}

		if (oferta.getPuesto() != null) {
			lblOferta.setText(oferta.getPuesto());
		} else {
			lblOferta.setText("Sin puesto");
		}

		lblPostulantes.setText(String.valueOf(resultadosMatch.size()));
	}

	private void mostrarTopCandidatos() {
		panelTop1.setVisible(resultadosMatch.size() >= 1);
		panelTop2.setVisible(resultadosMatch.size() >= 2);
		panelTop3.setVisible(resultadosMatch.size() >= 3);

		if (resultadosMatch.size() >= 1) {
			cargarPosicionTop(0, lblNombreTop1, txtTipoTop1, lblCoincidenciaTop1, lblFotoTop1);
		}

		if (resultadosMatch.size() >= 2) {
			cargarPosicionTop(1, lblNombreTop2, txtTipoTop2, lblCoincidenciaTop2, lblFotoTop2);
		}

		if (resultadosMatch.size() >= 3) {
			cargarPosicionTop(2, lblNombreTop3, txtTipoTop3, lblCoincidenciaTop3, lblFotoTop3);
		}
	}

	private void cargarPosicionTop(int posicion, JLabel lblNombre, TextFieldRedond txtTipo, JLabel lblCoincidencia, JLabel lblFoto) {
		if (posicion < 0 || posicion >= resultadosMatch.size()) {
			return;
		}

		ResultMatch resultado = resultadosMatch.get(posicion);

		if (resultado == null || resultado.getSolicitud() == null || resultado.getSolicitud().getCandidato() == null) {
			return;
		}

		Persona candidato = resultado.getSolicitud().getCandidato();

		lblNombre.setText(candidato.getNombre() + " " + candidato.getApellido());
		txtTipo.setText(obtenerTipoCandidato(candidato));
		lblCoincidencia.setText(String.format("%.1f%%", resultado.getPorcentaje()));

		colocarImagen(lblFoto, "/img/User Icon.png");
	}

	private void cargarRestoPostulantes() {
		model.setRowCount(0);
		candidatosMostrados.clear();

		if (oferta == null || resultadosMatch == null || resultadosMatch.size() <= 3) {
			return;
		}

		for (int i = 3; i < resultadosMatch.size(); i++) {
			ResultMatch resultado = resultadosMatch.get(i);

			if (resultado == null || resultado.getSolicitud() == null || resultado.getSolicitud().getCandidato() == null) {
				continue;
			}

			Persona candidato = resultado.getSolicitud().getCandidato();
			DecisionCandidato decision = oferta.buscarDecision(candidato);
			String estado = "Pendiente";

			if (decision != null) {
				if (decision.getEstado() == EstadoDecision.RECHAZADO) {
					estado = "Rechazado";
				} else if (decision.getEstado() == EstadoDecision.CONTRATADO) {
					estado = "Contratado";
				}
			}

			candidatosMostrados.add(resultado);

			model.addRow(new Object[] {
					candidato.getNombre() + " " + candidato.getApellido(),
					obtenerTipoCandidato(candidato),
					String.format("%.1f%%", resultado.getPorcentaje()),
					estado
			});
		}

		table.revalidate();
		table.repaint();
	}

	private void abrirPerfilTop(int posicion) {
	    if (oferta == null || posicion < 0 || posicion >= resultadosMatch.size()) {
	        return;
	    }

	    ResultMatch resultado = resultadosMatch.get(posicion);

	    if (resultado == null || resultado.getSolicitud() == null || resultado.getSolicitud().getCandidato() == null) {
	        JOptionPane.showMessageDialog(VerPostulantesOferta.this, "No se pudo identificar al candidato.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    SolicitudEmpleo solicitud = resultado.getSolicitud();

	    VerPostulante ventana = new VerPostulante(solicitud.getCandidato(), oferta, solicitud, resultado.getPorcentaje());
	    ventana.setVisible(true);
	}

	private void abrirPerfilTabla() {
	    int filaVista = table.getSelectedRow();

	    if (filaVista < 0 || oferta == null) {
	        return;
	    }

	    int filaModelo = table.convertRowIndexToModel(filaVista);

	    if (filaModelo < 0 || filaModelo >= candidatosMostrados.size()) {
	        return;
	    }

	    ResultMatch resultado = candidatosMostrados.get(filaModelo);

	    if (resultado == null || resultado.getSolicitud() == null || resultado.getSolicitud().getCandidato() == null) {
	        return;
	    }

	    SolicitudEmpleo solicitud = resultado.getSolicitud();

	    VerPostulante ventana = new VerPostulante(solicitud.getCandidato(), oferta, solicitud, resultado.getPorcentaje());
	    ventana.setVisible(true);
	}

	private void mostrarEstadoSinResultados() {
		if (oferta != null && oferta.getEmpresa() != null && oferta.getEmpresa().getNombre() != null) {
			lblEmpresa.setText(oferta.getEmpresa().getNombre());
		} else {
			lblEmpresa.setText("Sin empresa");
		}

		if (oferta != null && oferta.getPuesto() != null) {
			lblOferta.setText(oferta.getPuesto());
		} else {
			lblOferta.setText("Sin oferta");
		}

		lblPostulantes.setText("0");

		panelTop1.setVisible(false);
		panelTop2.setVisible(false);
		panelTop3.setVisible(false);

		candidatosMostrados.clear();

		if (model != null) {
			model.setRowCount(0);
		}
	}

	private String obtenerTipoCandidato(Persona candidato) {
		if (candidato == null) {
			return "No especificado";
		}

		boolean femenino = candidato.getSexo() == Sexo.FEMENINO;

		if (candidato instanceof Universitario) {
			return femenino ? "Universitaria" : "Universitario";
		}

		if (candidato instanceof Tecnico) {
			return femenino ? "Técnica" : "Técnico";
		}

		if (candidato instanceof Obrero) {
			return femenino ? "Obrera" : "Obrero";
		}

		return "No especificado";
	}

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		java.net.URL recurso = getClass().getResource(ruta);

		if (recurso == null) {
			System.err.println("No se encontró la imagen: " + ruta);
			return;
		}

		ImageIcon icono = new ImageIcon(recurso);
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

		boton.setIcon(new ImageIcon(imagenEscalada));
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
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
	}
}