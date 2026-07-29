package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		setModal(true);
		setTitle("Menu Solicitante");
		setBounds(0, 0, 415, dim.height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(255, 255, 255));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("correoelectronico@hotmail.com");
		lblNewLabel_2.setForeground(new Color(105, 105, 105));
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(161, 837, 194, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(33, 27, 321, 95);
		panel.add(lblNewLabel);
		colocarImagen(lblNewLabel,"/img/HireLink_logo_full.png");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 153, 0));
		separator.setBounds(12, 186, 365, 37);
		panel.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Solicitante");
		lblNewLabel_1.setForeground(new Color(255, 153, 0));
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 157, 108, 16);
		panel.add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 781, 365, 37);
		panel.add(separator_1);
		
		BotonConSombra btnDashboard = new BotonConSombra("Dashboard",25);
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeCandidato soli = new HomeCandidato();
				soli.setVisible(true);
				dispose();
			}
		});
		btnDashboard.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btnDashboard.setBounds(109, 223, 189, 49);
		btnDashboard.setColorHover(new Color(255, 153, 0));
		panel.add(btnDashboard);
		
		BotonConSombra btnMisSolicitudes = new BotonConSombra("Mis Solicitudes",25);
		btnMisSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerSolicitudesAplicadas soli = new VerSolicitudesAplicadas();
				soli.setVisible(true);
				dispose();
			}
		});
		btnMisSolicitudes.setHorizontalAlignment(SwingConstants.LEFT);
		btnMisSolicitudes.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnMisSolicitudes.setBounds(109, 310, 189, 49);
		btnMisSolicitudes.setColorHover(new Color(255, 153, 0));
		panel.add(btnMisSolicitudes);
		
		BotonConSombra btnBuscarOfertas = new BotonConSombra("Buscar Ofertas",25);
		btnBuscarOfertas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarOfertas soli = new BuscarOfertas(null);
				soli.setVisible(true);
				dispose();
			}
		});
		btnBuscarOfertas.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscarOfertas.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnBuscarOfertas.setBounds(109, 403, 189, 49);
		btnBuscarOfertas.setColorHover(new Color(255, 153, 0));
		panel.add(btnBuscarOfertas);
		
		BotonConSombra btnMensaje = new BotonConSombra("Mis Solicitudes",25);
		btnMensaje.setText("Mensajes");
		btnMensaje.setHorizontalAlignment(SwingConstants.LEFT);
		btnMensaje.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnMensaje.setBounds(109, 496, 189, 49);
		btnMensaje.setColorHover(new Color(255, 153, 0));
		panel.add(btnMensaje);
		
		BotonConSombra btnNotificaciones = new BotonConSombra("Mis Solicitudes", 25);
		btnNotificaciones.setText("Notificaciones");
		btnNotificaciones.setHorizontalAlignment(SwingConstants.LEFT);
		btnNotificaciones.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnNotificaciones.setBounds(109, 595, 189, 49);
		btnNotificaciones.setColorHover(new Color(255, 153, 0));
		panel.add(btnNotificaciones);
		
		JLabel iconoDashboard = new JLabel("New label");
		iconoDashboard.setBounds(67, 236, 30, 30);
		colocarImagen(iconoDashboard, "/img/hogar.png");
		panel.add(iconoDashboard);
		
		JLabel label = new JLabel("New label");
		label.setBounds(67, 319, 30, 30);
		colocarImagen(label, "/img/documento.png");
		panel.add(label);
		
		JLabel label_1 = new JLabel("New label");
		label_1.setBounds(67, 412, 30, 30);
		colocarImagen(label_1, "/img/correo-electronico.png");
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(67, 505, 30, 30);
		colocarImagen(label_2, "/img/mensaje.png");
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(67, 604, 30, 30);
		colocarImagen(label_3, "/img/notificacion.png");
		panel.add(label_3);
		
		BotonRedond btnNewButton = new BotonRedond("Nombre",25);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(147, 787, 228, 80);
		btnNewButton.setColorHover(new Color(255, 153, 0));
		panel.add(btnNewButton);
		
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
