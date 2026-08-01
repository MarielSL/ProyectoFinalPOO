package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import logico.BolsaEmpleo;
import logico.EstadoOferta;
import logico.Oferta;
import logico.Persona;
import logico.Usuario;

import java.awt.SystemColor;

public class HomeCandidato extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private Persona candidato;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeCandidato frame = new HomeCandidato();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomeCandidato() {
		if (BolsaEmpleo.getInstancia().getLoginUser() != null) {
			candidato = BolsaEmpleo.getInstancia().getLoginUser().getPersona();
		}
		setTitle("Home Candidato");
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height);
		setLocationRelativeTo(null);

		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		layeredPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		layeredPane.add(panel, BorderLayout.CENTER);
		panel.setBackground(new Color(245, 245, 245));
		panel.setLayout(null);

		int margen = 40;
		int anchoContenido = dim.width - (margen * 2);

		{
			PanelRedond panelMenu = new PanelRedond(25);
			panelMenu.setBackground(new Color(0, 0, 51));
			panelMenu.setBounds(26, 20, anchoContenido, 70);
			panel.add(panelMenu);
			panelMenu.setLayout(null);

			JLabel lblInicio = new JLabel("Inicio");
			lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicio.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblInicio.setForeground(Color.WHITE);
			lblInicio.setBounds(680, 26, 70, 20);
			panelMenu.add(lblInicio);

			JLabel lblVerOfertas = new JLabel("Ver ofertas");
			lblVerOfertas.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblVerOfertas.setForeground(Color.WHITE);
			lblVerOfertas.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblVerOfertas.setBounds(804, 26, 82, 20);
			lblVerOfertas.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					abrirVerOfertas();
				}
			});
			panelMenu.add(lblVerOfertas);

			JLabel lblMiSolicitud = new JLabel("Mi Solicitud Laboral");
			lblMiSolicitud.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblMiSolicitud.setForeground(Color.WHITE);
			lblMiSolicitud.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblMiSolicitud.setBounds(938, 26, 170, 20);
			lblMiSolicitud.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					Usuario user = BolsaEmpleo.getInstancia().getLoginUser();
					if(user ==null || user.getPersona() == null || user.getPersona().getSolicitud() == null) {
						SolicitudVacia nueva = new SolicitudVacia();
						nueva.setVisible(true);
						dispose();
					}
					else {
						abrirMiSolicitudLaboral();
					}
				}
			});
			panelMenu.add(lblMiSolicitud);

			JLabel lblVerPerfil = new JLabel("Ver perfil");
			lblVerPerfil.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblVerPerfil.setForeground(Color.WHITE);
			lblVerPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblVerPerfil.setBounds(1130, 26, 90, 20); 
			lblVerPerfil.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					abrirVerPerfil();
				}
			});
			panelMenu.add(lblVerPerfil);

			BotonRedond btnMenu = new BotonRedond("", 25);
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BarraSolicitante menu = new BarraSolicitante();
					menu.setVisible(true);
				}
			});
			btnMenu.setBackground(new Color(0, 0, 51));
			btnMenu.setColorHover(new Color(0, 51, 102));
			btnMenu.setBounds(12, 4, 60, 60);
			colocarIconoBoton(btnMenu, "/img/menu-dots-vertical(White).png", 25, 25);
			btnMenu.setMargin(new Insets(0, 0, 0, 0));
			btnMenu.setBorderPainted(false);
			btnMenu.setContentAreaFilled(false);
			btnMenu.setFocusPainted(false);
			btnMenu.setOpaque(false);
			panelMenu.add(btnMenu);
		}

		int anchoTarjeta = (anchoContenido - 48) / 3;

		{
			PanelConSombra panelEstadoBusqueda = new PanelConSombra(18);
			panelEstadoBusqueda.setBackground(new Color(255, 224, 178));
			panelEstadoBusqueda.setBounds(margen, 110, anchoTarjeta, 90);
			panel.add(panelEstadoBusqueda);
			panelEstadoBusqueda.setLayout(null);

			JLabel lblEstadoBusqueda = new JLabel("Estado de b\u00FAsqueda");
			lblEstadoBusqueda.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblEstadoBusqueda.setForeground(new Color(204, 102, 0));
			lblEstadoBusqueda.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelEstadoBusqueda.add(lblEstadoBusqueda);

			JLabel lblEstadoBusquedaValor = new JLabel("Estado");
			lblEstadoBusquedaValor.setFont(new Font("Calibri", Font.BOLD, 26));
			lblEstadoBusquedaValor.setForeground(new Color(204, 102, 0));
			lblEstadoBusquedaValor.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelEstadoBusqueda.add(lblEstadoBusquedaValor);
		}

		{
			PanelConSombra panelOfertasDisponibles = new PanelConSombra(18);
			panelOfertasDisponibles.setBackground(new Color(195, 220, 255));
			panelOfertasDisponibles.setBounds(margen + anchoTarjeta + 24, 110, anchoTarjeta, 90);
			panel.add(panelOfertasDisponibles);
			panelOfertasDisponibles.setLayout(null);

			JLabel lblOfertasDisponibles = new JLabel("Ofertas Disponibles");
			lblOfertasDisponibles.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblOfertasDisponibles.setForeground(new Color(65, 95, 170));
			lblOfertasDisponibles.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelOfertasDisponibles.add(lblOfertasDisponibles);

			JLabel lblOfertasDisponiblesValor = new JLabel(String.valueOf(contarOfertasDisponibles()));
			lblOfertasDisponiblesValor.setFont(new Font("Calibri", Font.BOLD, 30));
			lblOfertasDisponiblesValor.setForeground(new Color(65, 95, 170));
			lblOfertasDisponiblesValor.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelOfertasDisponibles.add(lblOfertasDisponiblesValor);
		}

		{
			PanelConSombra panelMayorCoincidencia = new PanelConSombra(18);
			panelMayorCoincidencia.setBackground(new Color(198, 239, 206));
			panelMayorCoincidencia.setBounds(margen + (anchoTarjeta + 24) * 2, 110, anchoTarjeta, 90);
			panel.add(panelMayorCoincidencia);
			panelMayorCoincidencia.setLayout(null);

			JLabel lblMayorCoincidencia = new JLabel("Mayor Coincidencia");
			lblMayorCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblMayorCoincidencia.setForeground(new Color(46, 125, 50));
			lblMayorCoincidencia.setBounds(20, 14, anchoTarjeta - 40, 20);
			panelMayorCoincidencia.add(lblMayorCoincidencia);

			JLabel lblMayorCoincidenciaValor = new JLabel("0%");
			lblMayorCoincidenciaValor.setFont(new Font("Calibri", Font.BOLD, 30));
			lblMayorCoincidenciaValor.setForeground(new Color(46, 125, 50));
			lblMayorCoincidenciaValor.setBounds(20, 38, anchoTarjeta - 40, 36);
			panelMayorCoincidencia.add(lblMayorCoincidenciaValor);
		}

		JLabel lblTituloGraficos = new JLabel("Oportunidades Destacadas");
		lblTituloGraficos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloGraficos.setForeground(new Color(0, 0, 51));
		lblTituloGraficos.setFont(new Font("Calibri", Font.BOLD, 26));
		lblTituloGraficos.setBounds(margen, 233, anchoContenido, 25);
		panel.add(lblTituloGraficos);

		{
			PanelConSombra panelGraficos = new PanelConSombra(20);
			panelGraficos.setBackground(Color.WHITE);
			panelGraficos.setBounds(26, 262, 1840, 761);
			panel.add(panelGraficos);
			panelGraficos.setLayout(null);

			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setForeground(SystemColor.controlShadow);
			separator.setBackground(SystemColor.controlShadow);
			separator.setBounds(919, 67, 1, 600);
			panelGraficos.add(separator);

			JLabel lblGrafico1 = new JLabel("[Espacio para gr\u00E1fico 1]");
			lblGrafico1.setHorizontalAlignment(SwingConstants.CENTER);
			lblGrafico1.setFont(new Font("Calibri", Font.PLAIN, 20));
			lblGrafico1.setForeground(new Color(150, 150, 150));
			lblGrafico1.setBounds(0, 0, 919, 761);
			panelGraficos.add(lblGrafico1);

			JLabel lblGrafico2 = new JLabel("[Espacio para gr\u00E1fico 2]");
			lblGrafico2.setHorizontalAlignment(SwingConstants.CENTER);
			lblGrafico2.setFont(new Font("Calibri", Font.PLAIN, 20));
			lblGrafico2.setForeground(new Color(150, 150, 150));
			lblGrafico2.setBounds(920, 0, 920, 761);
			panelGraficos.add(lblGrafico2);
		}
	}

	private int contarOfertasDisponibles() {
		int contador = 0;
		for (Oferta oferta : BolsaEmpleo.getInstancia().getOfertas()) {
			if (oferta.getEstado() == EstadoOferta.PENDIENTE) {
				contador++;
			}
		}
		return contador;
	}

	private void abrirVerOfertas() {
		VerOfertasCandidato frame = new VerOfertasCandidato();
		frame.setVisible(true);
		dispose();
	}

	private void abrirMiSolicitudLaboral() {
		VerMiSolicitudLaboral frame = new VerMiSolicitudLaboral();
		frame.setVisible(true);
		dispose();
	}

	private void abrirVerPerfil() {
		VerUserSolicitante frame = new VerUserSolicitante();
		frame.setVisible(true);
		dispose();
	}

	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
		Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imagenEscalada));
	}
}