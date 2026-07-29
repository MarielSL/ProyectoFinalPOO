package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		setBounds(0, 0, 415, dim.height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 51, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("correoelectronico@hotmail.com");
			lblNewLabel_2.setForeground(new Color(105, 105, 105));
			lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 13));
			lblNewLabel_2.setBounds(161, 837, 194, 30);
			panel.add(lblNewLabel_2);
			
			JSeparator separator = new JSeparator();
			separator.setForeground(new Color(255, 51, 51));
			separator.setBounds(12, 147, 363, 24);
			panel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(12, 782, 363, 24);
			panel.add(separator_1);
			
			JLabel iconoLogo = new JLabel("");
			iconoLogo.setBounds(12, 28, 244, 77);
			colocarImagen(iconoLogo,"/img/HireLink_logo_full.png");
			panel.add(iconoLogo);
			
			JLabel lblEmpresa = new JLabel("Admin");
			lblEmpresa.setForeground(new Color(255, 51, 51));
			lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 20));
			lblEmpresa.setBounds(12, 124, 125, 16);
			panel.add(lblEmpresa);
			
			BotonConSombra btnDashboard = new BotonConSombra("Dashboard",25);
			btnDashboard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HomeEmpresa emp = new HomeEmpresa();
					emp.setVisible(true);
					dispose();
				}
			});
			btnDashboard.setBackground(new Color(255, 255, 255));
			btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
			btnDashboard.setColorHover(new Color(255, 153, 102));
			btnDashboard.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnDashboard.setBounds(111, 195, 221, 45);
			panel.add(btnDashboard);
			
			BotonConSombra btnMisOfertas = new BotonConSombra("Dashboard", 25);
			btnMisOfertas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VerOfertasEmpresa emp = new VerOfertasEmpresa();
					emp.setVisible(true);
					dispose();
				}
			});
			btnMisOfertas.setBackground(new Color(255, 255, 255));
			btnMisOfertas.setHorizontalAlignment(SwingConstants.LEFT);
			btnMisOfertas.setText("Ofertas");
			btnMisOfertas.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnMisOfertas.setColorHover(new Color(255, 153, 102));
			btnMisOfertas.setBounds(111, 253, 221, 45);
			panel.add(btnMisOfertas);
			
			BotonConSombra btncnsmbrSolicitudesRecibidas = new BotonConSombra("Dashboard", 25);
			btncnsmbrSolicitudesRecibidas.setBackground(new Color(255, 255, 255));
			btncnsmbrSolicitudesRecibidas.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrSolicitudesRecibidas.setText("Solicitantes");
			btncnsmbrSolicitudesRecibidas.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrSolicitudesRecibidas.setColorHover(new Color(255, 153, 102));
			btncnsmbrSolicitudesRecibidas.setBounds(111, 311, 221, 45);
			panel.add(btncnsmbrSolicitudesRecibidas);
			
			BotonConSombra btncnsmbrMensajes = new BotonConSombra("Dashboard", 25);
			btncnsmbrMensajes.setBackground(new Color(255, 255, 255));
			btncnsmbrMensajes.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrMensajes.setText("Reportes");
			btncnsmbrMensajes.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrMensajes.setColorHover(new Color(255, 153, 102));
			btncnsmbrMensajes.setBounds(111, 485, 221, 45);
			panel.add(btncnsmbrMensajes);
			
			BotonConSombra btncnsmbrNotificaciones = new BotonConSombra("Dashboard", 25);
			btncnsmbrNotificaciones.setBackground(new Color(255, 255, 255));
			btncnsmbrNotificaciones.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrNotificaciones.setText("Usuarios");
			btncnsmbrNotificaciones.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrNotificaciones.setColorHover(new Color(255, 153, 102));
			btncnsmbrNotificaciones.setBounds(111, 543, 221, 45);
			panel.add(btncnsmbrNotificaciones);
			
			JLabel iconoDashboard = new JLabel("icono");
			iconoDashboard.setBounds(66, 195, 30, 30);
			colocarImagen(iconoDashboard,"/img/hogar.png");
			panel.add(iconoDashboard);
			
			JLabel lblIcono = new JLabel("");
			lblIcono.setBounds(66, 376, 30, 30);
			colocarImagen(lblIcono,"/img/maletin.png");
			panel.add(lblIcono);
			
			JLabel lblIcono_1 = new JLabel("icono");
			lblIcono_1.setBounds(66, 311, 30, 30);
			colocarImagen(lblIcono_1,"/img/portapapeles.png");
			panel.add(lblIcono_1);
			
			JLabel lblIcono_3 = new JLabel("");
			lblIcono_3.setBounds(66, 492, 30, 30);
			colocarImagen(lblIcono_3,"/img/notificacion.png");
			panel.add(lblIcono_3);
			
			BotonRedond btnNewButton = new BotonRedond("Nombre",25);
			btnNewButton.setText("Admin");
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton.setBounds(147, 787, 228, 80);
			btnNewButton.setColorHover(new Color(135, 206, 250));
			panel.add(btnNewButton);
			
			BotonConSombra btncnsmbrEmpresas = new BotonConSombra("Dashboard", 25);
			btncnsmbrEmpresas.setText("Empresas");
			btncnsmbrEmpresas.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrEmpresas.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrEmpresas.setColorHover(new Color(255, 153, 102));
			btncnsmbrEmpresas.setBackground(Color.WHITE);
			btncnsmbrEmpresas.setBounds(111, 369, 221, 45);
			panel.add(btncnsmbrEmpresas);
			
			BotonConSombra btncnsmbrPostulaciones = new BotonConSombra("Dashboard", 25);
			btncnsmbrPostulaciones.setText("Postulaciones");
			btncnsmbrPostulaciones.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrPostulaciones.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrPostulaciones.setColorHover(new Color(255, 153, 102));
			btncnsmbrPostulaciones.setBackground(Color.WHITE);
			btncnsmbrPostulaciones.setBounds(111, 427, 221, 45);
			panel.add(btncnsmbrPostulaciones);
			
			BotonConSombra btncnsmbrConfiguracion = new BotonConSombra("Dashboard", 25);
			btncnsmbrConfiguracion.setText("Configuraci\u00F3n");
			btncnsmbrConfiguracion.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrConfiguracion.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrConfiguracion.setColorHover(new Color(255, 153, 102));
			btncnsmbrConfiguracion.setBackground(Color.WHITE);
			btncnsmbrConfiguracion.setBounds(111, 601, 221, 45);
			panel.add(btncnsmbrConfiguracion);
			
			JLabel label = new JLabel("icono");
			label.setBounds(66, 434, 30, 30);
			panel.add(label);
			
			JLabel label_1 = new JLabel("icono");
			label_1.setBounds(69, 543, 30, 30);
			panel.add(label_1);
			
			JLabel label_2 = new JLabel("icono");
			label_2.setBounds(66, 601, 30, 30);
			panel.add(label_2);
			
			BotonConSombra btncnsmbrCerrarSessin = new BotonConSombra("Dashboard", 25);
			btncnsmbrCerrarSessin.setText("Cerrar sessi\u00F3n");
			btncnsmbrCerrarSessin.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrCerrarSessin.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrCerrarSessin.setColorHover(new Color(255, 153, 102));
			btncnsmbrCerrarSessin.setBackground(Color.WHITE);
			btncnsmbrCerrarSessin.setBounds(111, 659, 221, 45);
			panel.add(btncnsmbrCerrarSessin);
			
			JLabel label_3 = new JLabel("icono");
			label_3.setBounds(66, 659, 30, 30);
			panel.add(label_3);
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
