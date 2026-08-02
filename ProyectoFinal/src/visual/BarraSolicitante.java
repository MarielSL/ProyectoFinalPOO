package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BarraSolicitante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Dimension dim = getToolkit().getScreenSize();
	private Usuario user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BarraSolicitante dialog = new BarraSolicitante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BarraSolicitante() {
		setResizable(false);
		setModal(true);
		setTitle("Menu Solicitante");
		setBounds(0, 0, 415, dim.height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		user = BolsaEmpleo.getInstancia().getLoginUser();

		String nombreUser = "Nombre";
		if (user != null && user.getEmpresa() != null) {
		    nombreUser = user.getEmpresa().getNombre();
		}

		String correoUser = "correoelectronico@hotmail.com";
		if (user != null) {
		    correoUser = user.getCorreo();
		}
	
			
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(255, 255, 255));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		BotonRedond btnPerfil = new BotonRedond(nombreUser,25);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VerUserSolicitante ver = new VerUserSolicitante(user.getPersona());
				setModal(true);
				ver.setVisible(true);
				
			}
		});
		if(user.getPersona() != null) {
			String nombre = user.getPersona().getNombre() + " " + user.getPersona().getApellido();
			btnPerfil.setText(nombre);
		}
		
		JLabel lblNewLabel_2 = new JLabel(correoUser);
		lblNewLabel_2.setForeground(new Color(105, 105, 105));
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(160, 907, 194, 30);
		panel.add(lblNewLabel_2);
		btnPerfil.setBackground(new Color(255, 255, 255));
		btnPerfil.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnPerfil.setHorizontalAlignment(SwingConstants.LEFT);
		btnPerfil.setBounds(147, 857, 228, 80);
		btnPerfil.setColorHover(new Color(255, 153, 0));
		panel.add(btnPerfil);
		
		JLabel lblNewLabel_1 = new JLabel("Candidato");
		lblNewLabel_1.setForeground(new Color(255, 153, 0));
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 128, 108, 16);
		panel.add(lblNewLabel_1);
		
		JLabel iconoLogo = new JLabel("");
		iconoLogo.setBounds(33, 27, 321, 95);
		panel.add(iconoLogo);
		colocarImagen(iconoLogo,"/img/HireLink_logo_full.png");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 153, 0));
		separator.setBounds(12, 147, 363, 24);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 843, 363, 24);
		panel.add(separator_1);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(255, 255, 255));
		panelBotones.setBounds(22, 172, 353, 516);
		panel.add(panelBotones);
		panelBotones.setLayout(null);
		
		BotonConSombra btnDashboard = new BotonConSombra("Dashboard", 25);
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomeCandidato ver = new HomeCandidato();
				setModal(true);
				ver.setVisible(true);
			}
		});
		btnDashboard.setBackground(new Color(255, 255, 255));
		btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btnDashboard.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnDashboard.setColorHover(new Color(255, 153, 0));
		btnDashboard.setBounds(117, 31, 189, 49);
		panelBotones.add(btnDashboard);
		
		
		BotonConSombra btnSolicitudes = new BotonConSombra("Mi Solicitud", 25);
		btnSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VerMiSolicitudLaboral soli = new VerMiSolicitudLaboral(user.getPersona().getSolicitud());
				soli.setVisible(true);
				
			}
		});
		btnSolicitudes.setBackground(new Color(255, 255, 255));
		btnSolicitudes.setHorizontalAlignment(SwingConstants.LEFT);
		btnSolicitudes.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnSolicitudes.setColorHover(new Color(255, 153, 0));
		btnSolicitudes.setBounds(117, 111, 189, 49);
		panelBotones.add(btnSolicitudes);
		
		BotonConSombra btnOfertas = new BotonConSombra("Ver Ofertas", 25);
		btnOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VerOfertasCandidato soli = new VerOfertasCandidato();
				soli.setVisible(true);
				
			}
		});
				
		btnOfertas.setBackground(new Color(255, 255, 255));
		btnOfertas.setHorizontalAlignment(SwingConstants.LEFT);
		btnOfertas.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnOfertas.setColorHover(new Color(255, 153, 0));
		btnOfertas.setBounds(117, 191, 189, 49);
		panelBotones.add(btnOfertas);
		
		BotonConSombra btnMensajes = new BotonConSombra("Mis Solicitudes", 25);
		
		JLabel iconoDashboard = new JLabel("");
		iconoDashboard.setBounds(75, 48, 30, 30);
		colocarImagen(iconoDashboard, "/img/hogar.png");
		panelBotones.add(iconoDashboard);
		
		JLabel iconoSolicitudes = new JLabel("");
		iconoSolicitudes.setBounds(75, 126, 30, 30);
		colocarImagen(iconoSolicitudes, "/img/documento.png");
		panelBotones.add(iconoSolicitudes);
		
		JLabel iconoOfertas = new JLabel("");
		iconoOfertas.setBounds(75, 204, 30, 30);
		colocarImagen(iconoOfertas, "/img/correo-electronico.png");
		panelBotones.add(iconoOfertas);
		
		BotonConSombra btnCerrarSessin = new BotonConSombra("Mis Solicitudes", 25);
		btnCerrarSessin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LogIn ver = new LogIn();
				setModal(true);
				ver.setVisible(true);
			}
		});
		btnCerrarSessin.setText("Cerrar Sessi\u00F3n");
		btnCerrarSessin.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarSessin.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnCerrarSessin.setColorHover(new Color(255, 153, 0));
		btnCerrarSessin.setBackground(Color.WHITE);
		btnCerrarSessin.setBounds(117, 271, 189, 49);
		panelBotones.add(btnCerrarSessin);
		
		JLabel iconoCerrarSession = new JLabel("");
		iconoCerrarSession.setBounds(75, 281, 30, 30);
		colocarImagen(iconoCerrarSession, "/img/log_out.png");
		panelBotones.add(iconoCerrarSession);
		
		Utilidades.aplicarIcono(this);
	}
	
	
	
	private void colocarImagen(JLabel label, String ruta) {

		 ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
		 int anchoImagen = icono.getIconWidth();
			int altoImagen = icono.getIconHeight();

			int anchoLabel = label.getWidth();
			int altoLabel = label.getHeight();

			double escalaAncho = (double) anchoLabel / anchoImagen;
			double escalaAlto = (double) altoLabel / altoImagen;

			double escala = Math.max(escalaAncho, escalaAlto);
			int nuevoAncho = (int) (anchoImagen * escala);
			int nuevoAlto = (int) (altoImagen * escala);

			Image imagenEscalada = icono.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
			ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
			label.setIcon(iconoEscalado);
	}
}
