package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SolicitudVacia extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JPanel circulo;
	private JButton btnReg;
	private BotonRedond btnrdndVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SolicitudVacia frame = new SolicitudVacia();
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
	public SolicitudVacia() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-55);
		Utilidades.aplicarIcono(this);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_11 = new JLabel("gran oportunidad!");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(new Color(0, 0, 51));
		label_11.setFont(new Font("Calibri", Font.BOLD, 38));
		label_11.setBounds(1216, 695, 401, 53);
		contentPane.add(label_11);
		
		JLabel label_9 = new JLabel("\u00A1Vamos por tu pr\u00F3xima ");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(new Color(0, 0, 51));
		label_9.setFont(new Font("Calibri", Font.BOLD, 38));
		label_9.setBounds(1216, 661, 401, 53);
		contentPane.add(label_9);
		
		JLabel label = new JLabel("Mi Solicitud Laboral");
		label.setForeground(new Color(0, 0, 51));
		label.setFont(new Font("Calibri", Font.BOLD, 45));
		label.setBounds(106, 131, 440, 45);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Define lo que buscas y conecta con oportunidades ideales.");
		label_1.setForeground(SystemColor.textInactiveText);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		label_1.setBounds(106, 173, 611, 25);
		contentPane.add(label_1);
		
		PanelRedond panelRedond = new PanelRedond(30);
		panelRedond.setLayout(null);
		panelRedond.setBackground(new Color(225, 239, 254));
		panelRedond.setBounds(106, 211, 400, 107);
		contentPane.add(panelRedond);
		
		PanelRedond panelRedond_1 = new PanelRedond(120);
		panelRedond_1.setLayout(null);
		panelRedond_1.setBackground(new Color(142, 162, 210));
		panelRedond_1.setBounds(29, 11, 85, 85);
		panelRedond.add(panelRedond_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setIcon(new ImageIcon(SolicitudVacia.class.getResource("/img/document (Very LightBlue).png")));
		label_2.setBounds(17, 17, 50, 50);
		panelRedond_1.add(label_2);
		colocarImagen(label_2, "/img/document (Very LightBlue).png");
		
		JLabel label_3 = new JLabel("Estado");
		label_3.setForeground(SystemColor.textInactiveText);
		label_3.setFont(new Font("Calibri", Font.PLAIN, 20));
		label_3.setBounds(142, 28, 100, 21);
		panelRedond.add(label_3);
		
		JLabel label_4 = new JLabel("Sin Solicitud");
		label_4.setForeground(new Color(71, 105, 186));
		label_4.setFont(new Font("Calibri", Font.BOLD, 30));
		label_4.setBounds(142, 58, 202, 26);
		panelRedond.add(label_4);
		
		PanelRedond panelRedond_2 = new PanelRedond(30);
		panelRedond_2.setLayout(null);
		panelRedond_2.setBackground(new Color(254, 247, 240));
		panelRedond_2.setBounds(555, 211, 400, 107);
		contentPane.add(panelRedond_2);
		
		PanelRedond panelRedond_3 = new PanelRedond(120);
		panelRedond_3.setLayout(null);
		panelRedond_3.setBackground(new Color(253, 225, 196));
		panelRedond_3.setBounds(29, 11, 85, 85);
		panelRedond_2.add(panelRedond_3);
		
		JLabel label_5 = new JLabel("New label");
		label_5.setIcon(new ImageIcon(SolicitudVacia.class.getResource("/img/calendar(yellow).png")));
		label_5.setBounds(17, 17, 50, 50);
		panelRedond_3.add(label_5);
		colocarImagen(label_5, "/img/calendar(yellow).png");
		
		JLabel label_6 = new JLabel("Fecha de Solicitud");
		label_6.setForeground(SystemColor.textInactiveText);
		label_6.setFont(new Font("Calibri", Font.PLAIN, 20));
		label_6.setBounds(142, 28, 158, 21);
		panelRedond_2.add(label_6);
		
		JLabel label_7 = new JLabel("-- / -- / ----");
		label_7.setForeground(new Color(245, 111, 7));
		label_7.setFont(new Font("Calibri", Font.BOLD, 30));
		label_7.setBounds(142, 58, 160, 26);
		panelRedond_2.add(label_7);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(SystemColor.scrollbar);
		separator.setBounds(1057, 377, 11, 477);
		contentPane.add(separator);
		
		JLabel label_8 = new JLabel("New label");
		label_8.setIcon(new ImageIcon(SolicitudVacia.class.getResource("/img/ImagenVerMiSolicitud.png")));
		label_8.setBounds(1134, 139, 564, 539);
		contentPane.add(label_8);
		colocarImagen(label_8, "/img/ImagenVerMiSolicitud.png");
		
		JLabel label_10 = new JLabel("Seguimos buscando las mejores");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(SystemColor.windowBorder);
		label_10.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_10.setBounds(1204, 761, 425, 26);
		contentPane.add(label_10);
		
		JLabel label_12 = new JLabel("opciones para ti.");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setForeground(SystemColor.windowBorder);
		label_12.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_12.setBounds(1204, 785, 425, 26);
		contentPane.add(label_12);
		
		btnrdndVolver = new BotonRedond("Volver", 30);
		btnrdndVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeCandidato home = new HomeCandidato();
				home.setVisible(true);
				dispose();
			}
		});
		btnrdndVolver.setText("Volver");
		btnrdndVolver.setForeground(new Color(0, 0, 51));
		btnrdndVolver.setFont(new Font("Calibri", Font.BOLD, 22));
		btnrdndVolver.setColorHover(new Color(255, 220, 183));
		btnrdndVolver.setBackground(new Color(255, 235, 215));
		btnrdndVolver.setBounds(1648, 855, 163, 45);
		contentPane.add(btnrdndVolver);
		
		circulo = new PanelRedond(450);
		circulo.setBounds(341, 388, 275, 275);
		contentPane.add(circulo);
		circulo.setBackground(Color.decode("#e1effe"));
		circulo.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SolicitudVacia.class.getResource("/img/NoSolicitudesIcon.png")));
		lblNewLabel.setBounds(0, 0, 275, 275);
		circulo.add(lblNewLabel);
		colocarImagen(lblNewLabel, "/img/NoSolicitudesIcon.png");
		
		JLabel lblAnNoHas = new JLabel("A\u00FAn no has publicado una solicitud");
		lblAnNoHas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnNoHas.setForeground(new Color(0, 0, 51));
		lblAnNoHas.setFont(new Font("Calibri", Font.BOLD, 38));
		lblAnNoHas.setBounds(206, 718, 545, 53);
		contentPane.add(lblAnNoHas);
		
		JLabel lblCompletaTuSolicitud = new JLabel("Completa tu solicitud laboral para que las empresas");
		lblCompletaTuSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompletaTuSolicitud.setForeground(SystemColor.windowBorder);
		lblCompletaTuSolicitud.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblCompletaTuSolicitud.setBounds(196, 782, 564, 26);
		contentPane.add(lblCompletaTuSolicitud);
		
		JLabel lblPuedanEncontrarteY = new JLabel("puedan encontrarte y contactar tu perfil.");
		lblPuedanEncontrarteY.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuedanEncontrarteY.setForeground(SystemColor.windowBorder);
		lblPuedanEncontrarteY.setFont(new Font("Calibri", Font.PLAIN, 25));
		lblPuedanEncontrarteY.setBounds(246, 815, 465, 26);
		contentPane.add(lblPuedanEncontrarteY);
		
		BotonRedond btnReg = new BotonRedond("Crear una Solicitud",30);
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegSolicitud registrar = new RegSolicitud(null);
				registrar.setVisible(true);
				dispose();
			}
		});
		btnReg.setBackground(Color.decode("#4769ba"));
		btnReg.setForeground(Color.decode("#e1effe"));
		btnReg.setColorHover(Color.decode("#4967ae"));
		btnReg.setFont(new Font("Calibri", Font.BOLD, 26));
		btnReg.setBounds(318, 878, 321, 50);
		contentPane.add(btnReg);
		
		JLabel lblNewLabel_1 = new JLabel("Logo");
		lblNewLabel_1.setBounds(108, 13, 277, 84);
		colocarImagen(lblNewLabel_1,"/img/HireLink_logo_full.png");
		contentPane.add(lblNewLabel_1);
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
		label.repaint();
	}
}
