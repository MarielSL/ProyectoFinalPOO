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

public class BarraEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim = getToolkit().getScreenSize();
	private Usuario user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BarraEmpresa dialog = new BarraEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BarraEmpresa() {
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Menu Empresa");
		Utilidades.aplicarIcono(this);
		setBounds(0, 0, 415, dim.height-40);
		//setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(175, 238, 238));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			String nombreUser = "Nombre";
			if (user != null) {
				nombreUser = user.getPersona().getNombre() + user.getPersona().getApellido();
			}
			
			String correoUser = "correoelectronico@hotmail.com";
			if (user != null) {
				correoUser = user.getCorreo();
			}
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel(correoUser);
			lblNewLabel_2.setForeground(new Color(105, 105, 105));
			lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 13));
			lblNewLabel_2.setBounds(162, 930, 194, 30);
			panel.add(lblNewLabel_2);
			
			BotonRedond btnVerPerfil = new BotonRedond(nombreUser,25);
			btnVerPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VerUserEmpresa perfil = new VerUserEmpresa();
					perfil.setVisible(true);
					dispose();
				}
			});
			btnVerPerfil.setBackground(new Color(255, 255, 255));
			btnVerPerfil.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnVerPerfil.setHorizontalAlignment(SwingConstants.LEFT);
			btnVerPerfil.setBounds(146, 880, 228, 80);
			btnVerPerfil.setColorHover(new Color(135, 206, 250));
			panel.add(btnVerPerfil);
			
			JSeparator separator = new JSeparator();
			separator.setForeground(new Color(0, 191, 255));
			separator.setBounds(12, 147, 363, 24);
			panel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(12, 843, 363, 24);
			panel.add(separator_1);
			
			JLabel iconoLogo = new JLabel("");
			iconoLogo.setBounds(12, 28, 244, 77);
			colocarImagen(iconoLogo,"/img/HireLink_logo_full.png");
			panel.add(iconoLogo);
			
			JLabel lblEmpresa = new JLabel(" Empresa");
			lblEmpresa.setForeground(new Color(0, 191, 255));
			lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 20));
			lblEmpresa.setBounds(12, 124, 125, 16);
			panel.add(lblEmpresa);
			
			JPanel panelBotones = new JPanel();
			panelBotones.setBackground(new Color(255, 255, 255));
			panelBotones.setBounds(22, 172, 353, 516);
			panel.add(panelBotones);
			panelBotones.setLayout(null);
			
			BotonConSombra btnDashboard = new BotonConSombra("Dashboard",25);
			btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HomeCandidato soli = new HomeCandidato();
				soli.setVisible(true);
				
		}
	});
			
			btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
			btnDashboard.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnDashboard.setColorHover(new Color(135, 206, 250));
			btnDashboard.setBackground(Color.WHITE);
			btnDashboard.setBounds(120, 35, 221, 45);
			panelBotones.add(btnDashboard);
			
			BotonConSombra btnMisOfertas = new BotonConSombra("Mis Solicitudes", 25);
			btnMisOfertas.setText("Mis Ofertas");
			btnMisOfertas.setHorizontalAlignment(SwingConstants.LEFT);
			btnMisOfertas.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnMisOfertas.setColorHover(new Color(135, 206, 250));
			btnMisOfertas.setBackground(Color.WHITE);
			btnMisOfertas.setBounds(120, 128, 221, 45);
			panelBotones.add(btnMisOfertas);
			
			BotonConSombra btnSolicitudes = new BotonConSombra("Dashboard", 25);
			btnSolicitudes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnSolicitudes.setText("Solicitudes Recibidas");
			btnSolicitudes.setHorizontalAlignment(SwingConstants.LEFT);
			btnSolicitudes.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnSolicitudes.setColorHover(new Color(135, 206, 250));
			btnSolicitudes.setBackground(Color.WHITE);
			btnSolicitudes.setBounds(120, 221, 221, 45);
			panelBotones.add(btnSolicitudes);
			
			JLabel iconoOfertas = new JLabel("");
			iconoOfertas.setBounds(61, 128, 30, 30);
			colocarImagen(iconoOfertas,"/img/maletin.png");
			panelBotones.add(iconoOfertas);
			
			JLabel iconoSolicitudes = new JLabel("");
			iconoSolicitudes.setBounds(61, 221, 30, 30);
			colocarImagen(iconoSolicitudes,"/img/portapapeles.png");
			panelBotones.add(iconoSolicitudes);

			JLabel iconoDashboard = new JLabel("");
			iconoDashboard.setBounds(61, 35, 30, 30);
			colocarImagen(iconoDashboard, "/img/hogar.png");
			panelBotones.add(iconoDashboard);

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
