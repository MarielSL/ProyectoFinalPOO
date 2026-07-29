package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Usuario;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BarraAdmin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim = getToolkit().getScreenSize();
	private Usuario user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BarraAdmin dialog = new BarraAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BarraAdmin() {
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Menu Admin");
		Utilidades.aplicarIcono(this);
		setBounds(0, 0, 415, dim.height-40);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 51, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			String nombreUser = "Admin";
			if (user != null) {
				nombreUser = user.getPersona().getNombre() + user.getPersona().getApellido();
			}
			
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblEmpresa = new JLabel("Admin");
			lblEmpresa.setForeground(new Color(255, 51, 51));
			lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 20));
			lblEmpresa.setBounds(12, 124, 125, 16);
			panel.add(lblEmpresa);
			
			JLabel iconoLogo = new JLabel("");
			iconoLogo.setBounds(12, 28, 244, 77);
			colocarImagen(iconoLogo,"/img/HireLink_logo_full.png");
			panel.add(iconoLogo);
			
			BotonRedond btnNewButton = new BotonRedond( nombreUser,25);
			btnNewButton.setText("Admin");
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton.setBounds(147, 908, 228, 80);
			btnNewButton.setColorHover(new Color(255, 153, 102));
			panel.add(btnNewButton);
			
			JSeparator separator = new JSeparator();
			separator.setForeground(new Color(255, 51, 51));
			separator.setBounds(12, 147, 363, 24);
			panel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(24, 884, 363, 24);
			panel.add(separator_1);
			
			JPanel panelBotones = new JPanel();
			panelBotones.setBackground(new Color(255, 255, 255));
			panelBotones.setBounds(12, 161, 375, 648);
			panel.add(panelBotones);
			panelBotones.setLayout(null);
			
			BotonConSombra btnDashboard = new BotonConSombra("Dashboard", 25);
			btnDashboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					HomeAdministrador home = new HomeAdministrador();
					setModal(true);
					home.setVisible(true);
					
				}
			});
			btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
			btnDashboard.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnDashboard.setColorHover(new Color(255, 153, 102));
			btnDashboard.setBackground(Color.WHITE);
			btnDashboard.setBounds(117, 32, 221, 45);
			panelBotones.add(btnDashboard);
			
			BotonConSombra btnOferta = new BotonConSombra("Dashboard", 25);
			btnOferta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					VerOfertasAdmin ver = new VerOfertasAdmin();
					setModal(true);
					ver.setVisible(true);
				}
			});
			btnOferta.setText("Ofertas");
			btnOferta.setHorizontalAlignment(SwingConstants.LEFT);
			btnOferta.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnOferta.setColorHover(new Color(255, 153, 102));
			btnOferta.setBackground(Color.WHITE);
			btnOferta.setBounds(117, 109, 221, 45);
			panelBotones.add(btnOferta);
			
			BotonConSombra btnSolicitantes = new BotonConSombra("Dashboard", 25);
			btnSolicitantes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					VerSolicitantesAdmin ver = new VerSolicitantesAdmin();
					setModal(true);
					ver.setVisible(true);
				}
			});
			btnSolicitantes.setText("Solicitantes");
			btnSolicitantes.setHorizontalAlignment(SwingConstants.LEFT);
			btnSolicitantes.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnSolicitantes.setColorHover(new Color(255, 153, 102));
			btnSolicitantes.setBackground(Color.WHITE);
			btnSolicitantes.setBounds(117, 186, 221, 45);
			panelBotones.add(btnSolicitantes);
			
			BotonConSombra btnEmpresas = new BotonConSombra("Dashboard", 25);
			btnEmpresas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					VerEmpresasAdmin ver = new VerEmpresasAdmin();
					setModal(true);
					ver.setVisible(true);		
					
				}
			});
			btnEmpresas.setText("Empresas");
			btnEmpresas.setHorizontalAlignment(SwingConstants.LEFT);
			btnEmpresas.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnEmpresas.setColorHover(new Color(255, 153, 102));
			btnEmpresas.setBackground(Color.WHITE);
			btnEmpresas.setBounds(117, 263, 221, 45);
			panelBotones.add(btnEmpresas);
			
			BotonConSombra btnReportes = new BotonConSombra("Dashboard", 25);
			btnReportes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					VerReportesAdmin ver = new VerReportesAdmin();
					setModal(true);
					ver.setVisible(true);
				}
			});
			btnReportes.setText("Reportes");
			btnReportes.setHorizontalAlignment(SwingConstants.LEFT);
			btnReportes.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnReportes.setColorHover(new Color(255, 153, 102));
			btnReportes.setBackground(Color.WHITE);
			btnReportes.setBounds(117, 340, 221, 45);
			panelBotones.add(btnReportes);
			
			BotonConSombra btnUsuarios = new BotonConSombra("Dashboard", 25);
			btnUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					VerUsuariosAdmin ver = new VerUsuariosAdmin();
					setModal(true);
					ver.setVisible(true);
				}
			});
			btnUsuarios.setText("Usuarios");
			btnUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
			btnUsuarios.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnUsuarios.setColorHover(new Color(255, 153, 102));
			btnUsuarios.setBackground(Color.WHITE);
			btnUsuarios.setBounds(117, 417, 221, 45);
			panelBotones.add(btnUsuarios);
			
			BotonConSombra btnRespaldo = new BotonConSombra("Dashboard", 25);
			btnRespaldo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnRespaldo.setText("Respaldo");
			btnRespaldo.setHorizontalAlignment(SwingConstants.LEFT);
			btnRespaldo.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnRespaldo.setColorHover(new Color(255, 153, 102));
			btnRespaldo.setBackground(Color.WHITE);
			btnRespaldo.setBounds(117, 494, 221, 45);
			panelBotones.add(btnRespaldo);
			
			BotonConSombra btnLogout = new BotonConSombra("Dashboard", 25);
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					LogIn ver = new LogIn();
					setModal(true);
					ver.setVisible(true);
				}
			});
			btnLogout.setText("Cerrar sessi\u00F3n");
			btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
			btnLogout.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnLogout.setColorHover(new Color(255, 153, 102));
			btnLogout.setBackground(Color.WHITE);
			btnLogout.setBounds(117, 571, 221, 45);
			panelBotones.add(btnLogout);
			
			JLabel iconoDashboard = new JLabel("");
			iconoDashboard.setBounds(53, 45, 30, 30);
			colocarImagen(iconoDashboard,"/img/hogar.png");
			panelBotones.add(iconoDashboard);
			
			JLabel iconoEmpresas = new JLabel("");
			iconoEmpresas.setBounds(53, 270, 30, 30);
			colocarImagen(iconoEmpresas,"/img/maletin.png");
			panelBotones.add(iconoEmpresas);
			
			JLabel iconoSolicitantes = new JLabel("");
			iconoSolicitantes.setBounds(53, 195, 30, 30);
			colocarImagen(iconoSolicitantes,"/img/portapapeles.png");
			panelBotones.add(iconoSolicitantes);
			
			JLabel iconoReportes = new JLabel("");
			iconoReportes.setBounds(53, 345, 30, 30);
			colocarImagen(iconoReportes,"/img/notificacion.png");
			panelBotones.add(iconoReportes);
			
			JLabel iconoUsuarios = new JLabel("");
			iconoUsuarios.setBounds(56, 420, 30, 30);
			colocarImagen(iconoUsuarios, "/img/usuarios.png");
			panelBotones.add(iconoUsuarios);
			
			JLabel iconoGeneralidades = new JLabel("");
			iconoGeneralidades.setBounds(53, 495, 30, 30);
			colocarImagen(iconoGeneralidades, "/img/configuracion.png");
			panelBotones.add(iconoGeneralidades);
			
			JLabel iconoLogout = new JLabel("");
			iconoLogout.setBounds(53, 570, 30, 30);
			colocarImagen(iconoLogout,"/img/log_out.png");
			panelBotones.add(iconoLogout);
			
			JLabel iconoOferta = new JLabel("");
			iconoOferta.setBounds(56, 120, 30, 30);
			colocarImagen(iconoOferta,"/img/oferta-de-trabajo.png");
			panelBotones.add(iconoOferta);
			
			
			
			
		}
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
