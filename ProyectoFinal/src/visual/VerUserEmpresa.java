package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.Usuario;

public class VerUserEmpresa extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel fotoPerfil;
	private TextFieldRedond txtUser;
	private TextFieldRedond txtRnc;
	private TextFieldRedond txtNombre;
	private TextFieldRedond txtTipo;
	private TextFieldRedond txtTelefono;
	private TextFieldRedond txtDireccion;
	private TextFieldRedond txtCorreo;
	private BotonRedond btnModificar;
	private JLabel lblFotoFondo;
	private Dimension dim;
	private BotonRedond btnVolver;
	private JLabel lblFotoPerfil;
	private BotonRedond btnMenu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VerUserEmpresa frame = new VerUserEmpresa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerUserEmpresa() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerUserEmpresa.class.getResource("/img/AppIconoFull.png")));
		setTitle("Ver Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 548);

		dim = getToolkit().getScreenSize();

		setSize(dim.width, dim.height - 55);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnMenu = new BotonRedond("", 30);
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarraEmpresa menu = new BarraEmpresa();
				menu.setVisible(true);
				dispose();
			}
		});
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setBounds(0, 0, 60, 60);
		colocarIconoBoton(btnMenu, "/img/menu-dots-vertical (Blue).png", 25, 25);
		btnMenu.setMargin(new Insets(0, 0, 0, 0));
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setFocusPainted(false);
		btnMenu.setOpaque(false);
		contentPane.add(btnMenu);

		fotoPerfil = new JLabel("");
		Escalador.b(fotoPerfil, 119, 108, 230, 230);
		contentPane.add(fotoPerfil);
		colocarImagen(fotoPerfil, "/img/User Icon.png");

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblUsuario, 462, 155, 81, 17);
		contentPane.add(lblUsuario);

		JLabel lblRnc = new JLabel("RNC");
		lblRnc.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblRnc, 792, 156, 56, 16);
		contentPane.add(lblRnc);

		txtUser = new TextFieldRedond(25);
		txtUser.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtUser.setEditable(false);
		txtUser.setBackground(SystemColor.controlHighlight);
		txtUser.setForeground(new Color(0, 0, 51));
		txtUser.setText("Cargando...");
		Escalador.b(txtUser, 462, 193, 214, 30);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		txtUser.setFocusable(false);

		txtRnc = new TextFieldRedond(25);
		txtRnc.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtRnc.setEditable(false);
		txtRnc.setBackground(SystemColor.controlHighlight);
		txtRnc.setForeground(new Color(0, 0, 51));
		txtRnc.setText("Cargando...");
		Escalador.b(txtRnc, 792, 193, 185, 30);
		contentPane.add(txtRnc);
		txtRnc.setColumns(10);
		txtRnc.setFocusable(false);

		lblFotoPerfil = new JLabel("Foto de Perfil");
		lblFotoPerfil.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblFotoPerfil, 171, 59, 112, 20);
		contentPane.add(lblFotoPerfil);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNombre, 1122, 155, 85, 16);
		contentPane.add(lblNombre);

		txtNombre = new TextFieldRedond(25);
		txtNombre.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtNombre.setEditable(false);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setForeground(new Color(0, 0, 51));
		txtNombre.setText("Cargando...");
		Escalador.b(txtNombre, 1122, 193, 251, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setFocusable(false);

		JLabel lblTipo = new JLabel("Sector Laboral");
		lblTipo.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblTipo, 119, 639, 127, 16);
		contentPane.add(lblTipo);

		txtTipo = new TextFieldRedond(25);
		txtTipo.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtTipo.setEditable(false);
		txtTipo.setBackground(SystemColor.controlHighlight);
		txtTipo.setForeground(new Color(0, 0, 51));
		txtTipo.setText("Cargando...");
		Escalador.b(txtTipo, 119, 674, 200, 30);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		txtTipo.setFocusable(false);

		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblTelefono, 119, 432, 100, 26);
		contentPane.add(lblTelefono);

		txtTelefono = new TextFieldRedond(25);
		txtTelefono.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtTelefono.setEditable(false);
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setForeground(new Color(0, 0, 51));
		txtTelefono.setText("Cargando...");
		Escalador.b(txtTelefono, 119, 467, 194, 30);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		txtTelefono.setFocusable(false);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblCorreo, 462, 437, 81, 16);
		contentPane.add(lblCorreo);

		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblDireccion, 462, 639, 106, 16);
		contentPane.add(lblDireccion);

		txtDireccion = new TextFieldRedond(25);
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtDireccion.setEditable(false);
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setForeground(new Color(0, 0, 51));
		txtDireccion.setText("Cargando...");
		Escalador.b(txtDireccion, 462, 674, 337, 30);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		txtDireccion.setFocusable(false);

		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtCorreo.setEditable(false);
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setText("Cargando...");
		Escalador.b(txtCorreo, 459, 467, 340, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		txtCorreo.setFocusable(false);

		btnModificar = new BotonRedond("Modificar", 30);
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = BolsaEmpleo.getInstancia().getLoginUser();

				if (usuario == null || usuario.getEmpresa() == null) {
					JOptionPane.showMessageDialog(
							VerUserEmpresa.this,
							"No se pudo identificar la empresa.",
							"Error",
							JOptionPane.ERROR_MESSAGE
							);
					return;
				}

				RegEmpresa regEmpresa = new RegEmpresa(usuario.getEmpresa());
				regEmpresa.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBackground(new Color(255, 153, 0));
		btnModificar.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		btnModificar.setForeground(new Color(0, 0, 51));
		Escalador.b(btnModificar, 1707, 891, 159, 47);
		contentPane.add(btnModificar);

		btnVolver = new BotonRedond("   Volver", 30);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HomeEmpresa volver = new HomeEmpresa();
				volver.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(0, 0, 51));
		btnVolver.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnVolver.setColorHover(new Color(255, 220, 183));
		btnVolver.setBackground(new Color(255, 235, 215));
		Escalador.b(btnVolver, 56, 886, 159, 47);
		contentPane.add(btnVolver);

		lblFotoFondo = new JLabel("");
		Escalador.b(lblFotoFondo, 12, 0, 1902, 978);
		contentPane.add(lblFotoFondo);
		colocarImagen(lblFotoFondo, "/img/Fondo-Ver Usuario.png");

		
		contentPane.setComponentZOrder(lblFotoFondo, contentPane.getComponentCount() - 1);

		cargarUsuarioConHilo();
	}

	private void cargarUsuarioConHilo() {
		btnModificar.setEnabled(false);
		btnVolver.setEnabled(false);
		btnMenu.setEnabled(false);

		SwingWorker<Object[], Void> hilo = new SwingWorker<Object[], Void>() {

			@Override
			protected Object[] doInBackground() throws Exception {
				Usuario usuario = BolsaEmpleo.getInstancia().getLoginUser();

				if (usuario == null) {
					throw new IllegalStateException("No hay ningún usuario con la sesión iniciada.");
				}

				Empresa empresa = usuario.getEmpresa();

				if (empresa == null) {
					throw new IllegalStateException("El usuario actual no tiene una empresa asociada.");
				}

				String username = textoSeguro(usuario.getUsername());
				String rnc = textoSeguro(empresa.getRnc());
				String nombre = textoSeguro(empresa.getNombre());
				String tipo = empresa.getTipo() != null ? empresa.getTipo().toString() : "No especificado";
				String telefono = textoSeguro(empresa.getTelefono());
				String correo = textoSeguro(usuario.getCorreo());
				String direccion = textoSeguro(empresa.getDireccion());
				String rutaFoto = usuario.getFotoPerfil();

				
				return new Object[] {
						username,
						rnc,
						nombre,
						tipo,
						telefono,
						correo,
						direccion,
						rutaFoto
				};
			}

			@Override
			protected void done() {
				try {
					Object[] datos = get();

					txtUser.setText((String) datos[0]);
					txtRnc.setText((String) datos[1]);
					txtNombre.setText((String) datos[2]);
					txtTipo.setText((String) datos[3]);
					txtTelefono.setText((String) datos[4]);
					txtCorreo.setText((String) datos[5]);
					txtDireccion.setText((String) datos[6]);

					String rutaFoto = (String) datos[7];
					colocarFotoPerfil(rutaFoto);

				} catch (Exception e) {
					Throwable causa = e.getCause();
					String mensaje = causa != null ? causa.getMessage() : e.getMessage();

					e.printStackTrace();

					limpiarDatos();

					JOptionPane.showMessageDialog(
							VerUserEmpresa.this,
							mensaje != null ? mensaje : "No se pudieron cargar los datos de la empresa.",
									"Error",
									JOptionPane.ERROR_MESSAGE
							);

				} finally {
					btnModificar.setEnabled(BolsaEmpleo.getInstancia().getLoginUser() != null);
					btnVolver.setEnabled(true);
					btnMenu.setEnabled(true);
				}
			}
		};

		hilo.execute();
	}

	private void limpiarDatos() {
		txtUser.setText("No disponible");
		txtRnc.setText("No disponible");
		txtNombre.setText("No disponible");
		txtTipo.setText("No especificado");
		txtTelefono.setText("No disponible");
		txtCorreo.setText("No disponible");
		txtDireccion.setText("No disponible");
		colocarImagen(fotoPerfil, "/img/User Icon.png");
	}

	private void colocarFotoPerfil(String ruta) {
		if (ruta == null || ruta.trim().isEmpty()) {
			colocarImagen(fotoPerfil, "/img/User Icon.png");
			return;
		}

		File archivo = new File(ruta);

		if (archivo.exists() && archivo.isFile()) {
			colocarImagenDesdeArchivo(fotoPerfil, archivo.getAbsolutePath());
		} else {
			java.net.URL recurso = getClass().getResource(ruta);

			if (recurso != null) {
				colocarImagen(fotoPerfil, ruta);
			} else {
				colocarImagen(fotoPerfil, "/img/User Icon.png");
			}
		}
	}

	private String textoSeguro(String texto) {
		return texto == null || texto.trim().isEmpty() ? "No disponible" : texto.trim();
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
		escalarImagen(label, icono);
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

	private void colocarImagenDesdeArchivo(JLabel label, String rutaAbsoluta) {
		if (label == null || rutaAbsoluta == null || rutaAbsoluta.trim().isEmpty()) {
			colocarImagen(label, "/img/User Icon.png");
			return;
		}

		File archivo = new File(rutaAbsoluta);

		if (!archivo.exists() || !archivo.isFile()) {
			colocarImagen(label, "/img/User Icon.png");
			return;
		}

		ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());

		if (icono.getIconWidth() <= 0 || icono.getIconHeight() <= 0) {
			colocarImagen(label, "/img/User Icon.png");
			return;
		}

		escalarImagen(label, icono);
	}

	private void escalarImagen(JLabel label, ImageIcon icono) {
		if (label == null || icono == null) {
			return;
		}

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
}