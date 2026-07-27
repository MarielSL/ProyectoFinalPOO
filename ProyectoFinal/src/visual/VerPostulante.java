package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Obrero;
import logico.Persona;
import logico.Sexo;
import logico.Tecnico;
import logico.Universitario;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class VerPostulante extends JFrame {

	private JPanel contentPane;
	private JLabel lblFotoPerfil;
	private JLabel lblNombre;
	private TextFieldRedond txtTipoSolicitante;
	private TextFieldRedond txtCoincidencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerPostulante frame = new VerPostulante(null);
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
	public VerPostulante(Persona solicitante) {
		Utilidades.aplicarIcono(this);
		setTitle("Ver Postulante");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 700, 65);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ver Postulante");
		lblNewLabel.setForeground(new Color(255, 153, 0));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 40));
		lblNewLabel.setBounds(214, 16, 271, 32);
		panel.add(lblNewLabel);
		
		lblFotoPerfil = new JLabel("New label");
		lblFotoPerfil.setIcon(new ImageIcon(VerPostulante.class.getResource("/img/User Icon.png")));
		lblFotoPerfil.setBounds(36, 98, 150, 150);
		contentPane.add(lblFotoPerfil);
		
		lblNombre = new JLabel("Mariel Sánchez");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setForeground(new Color(0, 0, 51));
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNombre.setBounds(10, 280, 203, 35);
		contentPane.add(lblNombre);
		
		txtTipoSolicitante = new TextFieldRedond(30);
		txtTipoSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		txtTipoSolicitante.setForeground(new Color(65, 95, 170));
		txtTipoSolicitante.setBackground(new Color(195, 220, 255));
		txtTipoSolicitante.setFont(new Font("Calibri", Font.BOLD, 20));
		txtTipoSolicitante.setBounds(36, 322, 150, 30);
		contentPane.add(txtTipoSolicitante);
		txtTipoSolicitante.setColumns(10);
		txtTipoSolicitante.setFocusable(false);
		
		txtCoincidencia = new TextFieldRedond(30);
		txtCoincidencia.setHorizontalAlignment(SwingConstants.CENTER);
		txtCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCoincidencia.setEditable(false);
		txtCoincidencia.setBounds(12, 365, 201, 90);
		contentPane.add(txtCoincidencia);
		txtCoincidencia.setColumns(10);
		txtCoincidencia.setFocusable(false);
		
		if(solicitante != null) {
			String rutaFotoPerfil = solicitante.getUser().getFotoPerfil();
			colocarImagen(lblFotoPerfil, rutaFotoPerfil);
			lblNombre.setText(solicitante.getNombre() + " " + solicitante.getApellido());
			if(solicitante instanceof Universitario) {
				if(solicitante.getSexo() == Sexo.FEMENINO) {
					txtTipoSolicitante.setText("Universitaria");
				}
				else {
					txtTipoSolicitante.setText("Universitario");
				}
			}
			if(solicitante instanceof Tecnico) {
				if(solicitante.getSexo() == Sexo.FEMENINO) {
					txtTipoSolicitante.setText("Técnica");
				}
				else {
					txtTipoSolicitante.setText("Técnico");
				}
			}
			if(solicitante instanceof Obrero) {
				if(solicitante.getSexo() == Sexo.FEMENINO) {
					txtTipoSolicitante.setText("Obrera");
				}
				else {
					txtTipoSolicitante.setText("Obrero");
				}
			}
		}
		else {
			colocarImagen(lblFotoPerfil, "/img/User Icon.png");
			txtTipoSolicitante.setText("Universitaria");
			txtCoincidencia.setText("");
		}
		
	}
	
	private void colocarImagen(JLabel label, String ruta) {

		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));

		int anchoLabel = label.getWidth();
		int altoLabel = label.getHeight();

		int anchoImagen = icono.getIconWidth();
		int altoImagen = icono.getIconHeight();

		double escalaAncho = (double) anchoLabel / anchoImagen;
		double escalaAlto = (double) altoLabel / altoImagen;

		double escala = Math.max(escalaAncho, escalaAlto);

		int nuevoAncho = (int) (anchoImagen * escala);
		int nuevoAlto = (int) (altoImagen * escala);

		Image imagenEscalada = icono.getImage().getScaledInstance(
				nuevoAncho,
				nuevoAlto,
				Image.SCALE_SMOOTH
				);

		ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

		label.setIcon(iconoEscalado);
		label.setText("");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
	}
}
