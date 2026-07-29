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

public class BarraEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim = getToolkit().getScreenSize();

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
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Menu Empresa");
		Utilidades.aplicarIcono(this);
		setBounds(0, 0, 415, dim.height);
		//setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(175, 238, 238));
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
			separator.setForeground(new Color(0, 191, 255));
			separator.setBounds(12, 147, 363, 24);
			panel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(12, 708, 363, 24);
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
			btnDashboard.setColorHover(new Color(135,206,250));
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
			btnMisOfertas.setText("Mis Ofertas");
			btnMisOfertas.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnMisOfertas.setColorHover(new Color(135, 206, 250));
			btnMisOfertas.setBounds(111, 279, 221, 45);
			panel.add(btnMisOfertas);
			
			BotonConSombra btncnsmbrSolicitudesRecibidas = new BotonConSombra("Dashboard", 25);
			btncnsmbrSolicitudesRecibidas.setBackground(new Color(255, 255, 255));
			btncnsmbrSolicitudesRecibidas.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrSolicitudesRecibidas.setText("Solicitudes Recibidas");
			btncnsmbrSolicitudesRecibidas.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrSolicitudesRecibidas.setColorHover(new Color(135, 206, 250));
			btncnsmbrSolicitudesRecibidas.setBounds(111, 363, 221, 45);
			panel.add(btncnsmbrSolicitudesRecibidas);
			
			BotonConSombra btncnsmbrMensajes = new BotonConSombra("Dashboard", 25);
			btncnsmbrMensajes.setBackground(new Color(255, 255, 255));
			btncnsmbrMensajes.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrMensajes.setText("Mensajes");
			btncnsmbrMensajes.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrMensajes.setColorHover(new Color(135, 206, 250));
			btncnsmbrMensajes.setBounds(111, 442, 221, 45);
			panel.add(btncnsmbrMensajes);
			
			BotonConSombra btncnsmbrNotificaciones = new BotonConSombra("Dashboard", 25);
			btncnsmbrNotificaciones.setBackground(new Color(255, 255, 255));
			btncnsmbrNotificaciones.setHorizontalAlignment(SwingConstants.LEFT);
			btncnsmbrNotificaciones.setText("Notificaciones");
			btncnsmbrNotificaciones.setFont(new Font("Calibri", Font.PLAIN, 20));
			btncnsmbrNotificaciones.setColorHover(new Color(135, 206, 250));
			btncnsmbrNotificaciones.setBounds(111, 526, 221, 45);
			panel.add(btncnsmbrNotificaciones);
			
			JLabel iconoDashboard = new JLabel("");
			iconoDashboard.setBounds(66, 195, 30, 30);
			colocarImagen(iconoDashboard,"/img/hogar.png");
			panel.add(iconoDashboard);
			
			JLabel label = new JLabel("");
			label.setBounds(66, 293, 30, 30);
			colocarImagen(label,"/img/maletin.png");
			panel.add(label);
			
			JLabel label_1 = new JLabel("");
			label_1.setBounds(66, 377, 30, 30);
			colocarImagen(label_1,"/img/portapapeles.png");
			panel.add(label_1);
			
			JLabel label_2 = new JLabel("");
			label_2.setBounds(66, 456, 30, 30);
			colocarImagen(label_2,"/img/mensaje.png");
			panel.add(label_2);
			
			JLabel label_3 = new JLabel("");
			label_3.setBounds(66, 540, 30, 30);
			colocarImagen(label_3,"/img/notificacion.png");
			panel.add(label_3);
			
			BotonRedond btnNewButton = new BotonRedond("Nombre",25);
			btnNewButton.setBackground(new Color(255, 255, 255));
			btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			btnNewButton.setBounds(147, 787, 228, 80);
			btnNewButton.setColorHover(new Color(135, 206, 250));
			panel.add(btnNewButton);
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
