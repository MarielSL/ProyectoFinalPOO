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
import java.time.format.DateTimeFormatter;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Obrero;
import logico.Persona;
import logico.Sexo;
import logico.Tecnico;
import logico.Universitario;
import logico.Usuario;

public class VerUserSolicitante extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel userIcon;
	private TextFieldRedond txtUsuario;
	private TextFieldRedond txtEstado;
	private TextFieldRedond txtNombre;
	private TextFieldRedond txtCorreo;
	private TextFieldRedond txtFechNacim;
	private TextFieldRedond txtTelef;
	private TextFieldRedond txtCiudad;
	private TextFieldRedond txtSexo;
	private BotonRedond btnNewButton;
	private JLabel lblFondo;
	private Dimension dim;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private TextFieldRedond txtTipo;
	private BotonRedond btnVolver;
	private BotonRedond btnMenu;
	private Persona myCandidato;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VerUserSolicitante frame = new VerUserSolicitante(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VerUserSolicitante(Persona candidato) {
		this.myCandidato = candidato;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VerUserSolicitante.class.getResource("/img/AppIconoFull.png")));
		setForeground(new Color(0, 0, 51));
		setTitle("Ver Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 548);

		dim = getToolkit().getScreenSize();

		setSize(dim.width, dim.height - 55);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userIcon = new JLabel("");
		Escalador.b(userIcon, 119, 108, 230, 230);
		contentPane.add(userIcon);
		colocarImagen(userIcon, "/img/User Icon.png");

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel, 462, 155, 81, 17);
		contentPane.add(lblNewLabel);

		txtUsuario = new TextFieldRedond(25);
		txtUsuario.setForeground(new Color(0, 0, 51));
		txtUsuario.setEditable(false);
		txtUsuario.setBackground(SystemColor.controlHighlight);
		txtUsuario.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtUsuario.setText("Cargando...");
		Escalador.b(txtUsuario, 462, 193, 205, 30);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		txtUsuario.setFocusable(false);

		JLabel lblNewLabel_1 = new JLabel("Estado");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_1, 792, 156, 100, 16);
		contentPane.add(lblNewLabel_1);

		txtEstado = new TextFieldRedond(25);
		txtEstado.setEditable(false);
		txtEstado.setBackground(SystemColor.controlHighlight);
		txtEstado.setForeground(new Color(0, 0, 51));
		txtEstado.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtEstado.setText("Cargando...");
		Escalador.b(txtEstado, 792, 193, 190, 30);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		txtEstado.setFocusable(false);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_2, 1122, 156, 81, 16);
		contentPane.add(lblNewLabel_2);

		txtNombre = new TextFieldRedond(25);
		txtNombre.setForeground(new Color(0, 0, 51));
		txtNombre.setEditable(false);
		txtNombre.setBackground(SystemColor.controlHighlight);
		txtNombre.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtNombre.setText("Cargando...");
		Escalador.b(txtNombre, 1122, 193, 441, 30);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setFocusable(false);

		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_3, 462, 425, 81, 16);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Fech. Nacimiento");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_4, 119, 763, 166, 16);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Teléfono");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_5, 119, 420, 100, 26);
		contentPane.add(lblNewLabel_5);

		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setEditable(false);
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtCorreo.setText("Cargando...");
		Escalador.b(txtCorreo, 459, 462, 340, 30);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		txtCorreo.setFocusable(false);

		txtFechNacim = new TextFieldRedond(25);
		txtFechNacim.setForeground(new Color(0, 0, 51));
		txtFechNacim.setEditable(false);
		txtFechNacim.setBackground(SystemColor.controlHighlight);
		txtFechNacim.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtFechNacim.setText("Cargando...");
		Escalador.b(txtFechNacim, 119, 798, 194, 30);
		contentPane.add(txtFechNacim);
		txtFechNacim.setColumns(10);
		txtFechNacim.setFocusable(false);

		JLabel lblNewLabel_6 = new JLabel("Ciudad");
		lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_6, 462, 594, 137, 16);
		contentPane.add(lblNewLabel_6);

		txtTelef = new TextFieldRedond(25);
		txtTelef.setForeground(new Color(0, 0, 51));
		txtTelef.setEditable(false);
		txtTelef.setBackground(SystemColor.controlHighlight);
		txtTelef.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtTelef.setText("Cargando...");
		Escalador.b(txtTelef, 119, 460, 194, 30);
		contentPane.add(txtTelef);
		txtTelef.setColumns(10);
		txtTelef.setFocusable(false);

		JLabel lblNewLabel_7 = new JLabel("Sexo");
		lblNewLabel_7.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_7, 119, 594, 56, 16);
		contentPane.add(lblNewLabel_7);

		txtCiudad = new TextFieldRedond(25);
		txtCiudad.setForeground(new Color(0, 0, 51));
		txtCiudad.setEditable(false);
		txtCiudad.setBackground(SystemColor.controlHighlight);
		txtCiudad.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtCiudad.setText("Cargando...");
		Escalador.b(txtCiudad, 462, 629, 350, 30);
		contentPane.add(txtCiudad);
		txtCiudad.setColumns(10);
		txtCiudad.setFocusable(false);

		txtSexo = new TextFieldRedond(25);
		txtSexo.setForeground(new Color(0, 0, 51));
		txtSexo.setEditable(false);
		txtSexo.setBackground(SystemColor.controlHighlight);
		txtSexo.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtSexo.setText("Cargando...");
		Escalador.b(txtSexo, 119, 629, 164, 30);
		contentPane.add(txtSexo);
		txtSexo.setColumns(10);
		txtSexo.setFocusable(false);

		lblNewLabel_8 = new JLabel("Foto de Perfil");
		lblNewLabel_8.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_8, 171, 59, 112, 20);
		contentPane.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("Categoría Laboral");
		lblNewLabel_9.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(lblNewLabel_9, 462, 763, 164, 18);
		contentPane.add(lblNewLabel_9);

		txtTipo = new TextFieldRedond(25);
		txtTipo.setEditable(false);
		txtTipo.setForeground(new Color(0, 0, 51));
		txtTipo.setBackground(SystemColor.controlHighlight);
		txtTipo.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		txtTipo.setText("Cargando...");
		Escalador.b(txtTipo, 462, 798, 194, 30);
		txtTipo.setFocusable(false);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);

		btnNewButton = new BotonRedond("Modificar", 30);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = BolsaEmpleo.getInstancia().getLoginUser();

				if (usuario == null || usuario.getPersona() == null) {
					JOptionPane.showMessageDialog(
						VerUserSolicitante.this,
						"No se pudo identificar al solicitante.",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);
					return;
				}

				RegistrarSolicitante regSolicitante = new RegistrarSolicitante(usuario.getPersona());
				regSolicitante.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 153, 0));
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, Escalador.t(20)));
		Escalador.b(btnNewButton, 1707, 891, 159, 47);
		contentPane.add(btnNewButton);

		btnMenu = new BotonRedond("", 30);
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarraSolicitante menu = new BarraSolicitante();
				menu.setVisible(true);
				dispose();
			}
		});
		btnMenu.setBackground(new Color(255, 255, 255));
		btnMenu.setBounds(0, 0, 60, 60);
		colocarIconoBoton(btnMenu, "/img/menu-dots-vertical (Blue).png", 25, 25);
		btnMenu.setMargin(new Insets(0, 0, 0, 0));
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);
		btnMenu.setFocusPainted(false);
		btnMenu.setOpaque(false);
		contentPane.add(btnMenu);

		btnVolver = new BotonRedond("   Volver", 30);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HomeCandidato volver = new HomeCandidato();
				volver.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(0, 0, 51));
		btnVolver.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnVolver.setColorHover(new Color(255, 220, 183));
		btnVolver.setBackground(new Color(255, 235, 215));
		Escalador.b(btnVolver, 46, 891, 159, 47);
		contentPane.add(btnVolver);

		lblFondo = new JLabel("");
		Escalador.b(lblFondo, 0, 0, 1902, 978);
		contentPane.add(lblFondo);
		colocarImagen(lblFondo, "/img/Fondo-Ver Usuario.png");

		contentPane.setComponentZOrder(lblFondo, contentPane.getComponentCount() - 1);

		cargarUsuarioConHilo();
	}

	private void cargarUsuarioConHilo() {
		btnNewButton.setEnabled(false);
		btnMenu.setEnabled(false);
		btnVolver.setEnabled(false);

		SwingWorker<Object[], Void> hilo = new SwingWorker<Object[], Void>() {

			@Override
			protected Object[] doInBackground() throws Exception {
				Usuario usuario = BolsaEmpleo.getInstancia().getLoginUser();

				if (usuario == null) {
					throw new IllegalStateException("No hay ningún usuario con la sesión iniciada.");
				}

				Persona candidato = myCandidato;

				if (candidato == null) {
					throw new IllegalStateException("El usuario actual no tiene un solicitante asociado.");
				}

				String username = textoSeguro(usuario.getUsername());
				boolean contratado = candidato.isEstadoEmpleo();
				String estado = contratado ? "Contratado" : "Desempleado";
				String nombre = textoSeguro(candidato.getNombre()) + " " + textoSeguro(candidato.getApellido());
				String correo = textoSeguro(usuario.getCorreo());

				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				String fechaNacimiento = candidato.getFechNacim() != null
						? candidato.getFechNacim().format(formato)
						: "No disponible";

				String telefono = textoSeguro(candidato.getTelefono());
				String ciudad = textoSeguro(candidato.getCiudad());

				String sexo = candidato.getSexo() != null
						? candidato.getSexo().toString().toLowerCase()
						: "No especificado";

				String tipo = obtenerTipoCandidato(candidato);
				String rutaFoto = usuario.getFotoPerfil();

				

				return new Object[] {
					username,
					estado,
					nombre,
					correo,
					fechaNacimiento,
					telefono,
					ciudad,
					sexo,
					tipo,
					rutaFoto,
					contratado
				};
			}

			@Override
			protected void done() {
				try {
					Object[] datos = get();

					txtUsuario.setText((String) datos[0]);
					txtEstado.setText((String) datos[1]);
					txtNombre.setText((String) datos[2]);
					txtCorreo.setText((String) datos[3]);
					txtFechNacim.setText((String) datos[4]);
					txtTelef.setText((String) datos[5]);
					txtCiudad.setText((String) datos[6]);
					txtSexo.setText((String) datos[7]);
					txtTipo.setText((String) datos[8]);

					String rutaFoto = (String) datos[9];
					boolean contratado = (Boolean) datos[10];

					aplicarColorEstado(contratado);
					colocarFotoPerfil(rutaFoto);

				} catch (Exception e) {
					Throwable causa = e.getCause();
					String mensaje = causa != null ? causa.getMessage() : e.getMessage();

					e.printStackTrace();

					mostrarDatosVacios();

					JOptionPane.showMessageDialog(
						VerUserSolicitante.this,
						mensaje != null
								? mensaje
								: "No se pudieron cargar los datos del solicitante.",
						"Error",
						JOptionPane.ERROR_MESSAGE
					);

				} finally {
					Usuario usuario = BolsaEmpleo.getInstancia().getLoginUser();

					btnNewButton.setEnabled(usuario != null && usuario.getPersona() != null);
					btnMenu.setEnabled(true);
					btnVolver.setEnabled(true);
				}
			}
		};

		hilo.execute();
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

	private void aplicarColorEstado(boolean contratado) {
		if (contratado) {
			txtEstado.setForeground(new Color(0, 102, 0));
			txtEstado.setBackground(new Color(153, 204, 153));
		} else {
			txtEstado.setForeground(new Color(153, 0, 0));
			txtEstado.setBackground(new Color(255, 153, 153));
		}
	}

	private void mostrarDatosVacios() {
		txtUsuario.setText("No disponible");
		txtEstado.setText("No disponible");
		txtNombre.setText("No disponible");
		txtCorreo.setText("No disponible");
		txtFechNacim.setText("No disponible");
		txtTelef.setText("No disponible");
		txtCiudad.setText("No disponible");
		txtSexo.setText("No especificado");
		txtTipo.setText("No especificado");

		txtEstado.setBackground(SystemColor.controlHighlight);
		txtEstado.setForeground(new Color(0, 0, 51));

		colocarImagen(userIcon, "/img/User Icon.png");
	}

	private String textoSeguro(String texto) {
		return texto == null || texto.trim().isEmpty()
				? "No disponible"
				: texto.trim();
	}

	private void colocarFotoPerfil(String ruta) {
		if (ruta == null || ruta.trim().isEmpty()) {
			colocarImagen(userIcon, "/img/User Icon.png");
			return;
		}

		File archivo = new File(ruta);

		if (archivo.exists() && archivo.isFile()) {
			colocarImagenDesdeArchivo(userIcon, archivo.getAbsolutePath());
			return;
		}

		java.net.URL recurso = getClass().getResource(ruta);

		if (recurso != null) {
			colocarImagen(userIcon, ruta);
		} else {
			colocarImagen(userIcon, "/img/User Icon.png");
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