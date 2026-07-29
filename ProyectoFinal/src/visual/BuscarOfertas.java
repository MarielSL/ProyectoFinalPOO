package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import logico.BolsaEmpleo;
import logico.EstadoOferta;
import logico.Jornada;
import logico.Oferta;
import logico.Persona;
import logico.TipoPersona;

public class BuscarOfertas extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private Persona candidato;
	private static final int LIST_FULL = 860;
	private static final int LIST_SPLIT = 400;
	private static final int DETALLE_X = 457;
	private static final int DETALLE_ANCHO = 430;
	private int listWidth = LIST_FULL;
	private ArrayList<Oferta> resultados = new ArrayList<Oferta>();
	private ArrayList<PanelRedond> tarjetas = new ArrayList<PanelRedond>();
	private Oferta ofertaSeleccionada;
	private TextFieldRedond txtPuesto;
	private ComboBoxRedond<String> cbxCiudad;
	private BotonRedond btnBuscar;
	private ComboBoxRedond<String> cbxMudarse;
	private ComboBoxRedond<TipoPersona> cbxTipoCandidato;
	private ComboBoxRedond<Jornada> cbxJornada;
	private JPanel pnlListado;
	private JScrollPane scrollListado;
	private PanelRedond pnlDetalle;
	private JLabel lblDetPuesto;
	private JLabel lblDetSub;
	private JTextArea txtDetDescripcion;
	private BotonRedond btnPostularme;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscarOfertas frame = new BuscarOfertas(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public BuscarOfertas(Persona candidato) {
		this.candidato = candidato;
		setTitle("Buscar Ofertas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 924, 640);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 914, 630);
		contentPanel.add(panel);
		panel.setLayout(null);

		{
			PanelConSombra panelBuscar = new PanelConSombra(0);
			panelBuscar.setBackground(new Color(0, 0, 51));
			panelBuscar.setBounds(0, 0, 917, 78);
			panel.add(panelBuscar);
			panelBuscar.setLayout(null);
			
			BotonRedond btnMenu = new BotonRedond("",25);
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BarraSolicitante menu = new BarraSolicitante();
					menu.setVisible(true);
				}
			});
			btnMenu.setBackground(new Color(0, 0, 51));
			btnMenu.setColorHover(new Color(0, 51, 102));
			btnMenu.setBounds(12, 5, 60, 60);
			colocarIconoBoton(btnMenu,"/img/menu-dots-vertical(White).png",25,25);
			btnMenu.setMargin(new Insets(0, 0, 0, 0));
			btnMenu.setBorderPainted(false);
			btnMenu.setContentAreaFilled(false);
			btnMenu.setFocusPainted(false);
			btnMenu.setOpaque(false);
			panelBuscar.add(btnMenu);

			txtPuesto = new TextFieldRedond(25);
			txtPuesto.setForeground(new Color(0, 0, 51));
			txtPuesto.setBackground(new Color(255, 255, 255));
			txtPuesto.setFont(new Font("Calibri", Font.PLAIN, 18));
			txtPuesto.setBounds(126, 23, 280, 30);
			panelBuscar.add(txtPuesto);

			cbxCiudad = new ComboBoxRedond<String>(25);
			cbxCiudad.setForeground(new Color(0, 0, 51));
			cbxCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
			cbxCiudad.setBackground(new Color(255, 255, 255));
			cbxCiudad.setBounds(434, 22, 220, 30);
			panelBuscar.add(cbxCiudad);

			btnBuscar = new BotonRedond("Buscar", 25);
			btnBuscar.setFont(new Font("Calibri", Font.PLAIN, 18));
			btnBuscar.setBackground(new Color(255, 153, 0));
			btnBuscar.setForeground(new Color(0, 0, 51));
			btnBuscar.setBounds(700, 23, 140, 30);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscar();
				}
			});
			panelBuscar.add(btnBuscar);
		}

		{
			JLabel lblMudarse = new JLabel("Disponibilidad para mudarse");
			lblMudarse.setForeground(new Color(0, 0, 51));
			lblMudarse.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblMudarse.setBounds(27, 90, 250, 18);
			panel.add(lblMudarse);

			JLabel lblTipoCandidato = new JLabel("Tipo de candidato");
			lblTipoCandidato.setForeground(new Color(0, 0, 51));
			lblTipoCandidato.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblTipoCandidato.setBounds(307, 90, 220, 18);
			panel.add(lblTipoCandidato);

			JLabel lblJornada = new JLabel("Jornada");
			lblJornada.setForeground(new Color(0, 0, 51));
			lblJornada.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblJornada.setBounds(587, 90, 220, 18);
			panel.add(lblJornada);

			cbxMudarse = new ComboBoxRedond<String>(15);
			cbxMudarse.setForeground(new Color(0, 0, 51));
			cbxMudarse.setFont(new Font("Calibri", Font.PLAIN, 16));
			cbxMudarse.setBackground(SystemColor.controlHighlight);
			cbxMudarse.setBounds(27, 110, 220, 30);
			cbxMudarse.setModel(new DefaultComboBoxModel<String>(new String[] { "S\u00ED", "No" }));
			cbxMudarse.setSelectedIndex(-1);
			panel.add(cbxMudarse);

			cbxTipoCandidato = new ComboBoxRedond<TipoPersona>(15);
			cbxTipoCandidato.setForeground(new Color(0, 0, 51));
			cbxTipoCandidato.setFont(new Font("Calibri", Font.PLAIN, 16));
			cbxTipoCandidato.setBackground(SystemColor.controlHighlight);
			cbxTipoCandidato.setBounds(307, 110, 220, 30);
			cbxTipoCandidato.setModel(new DefaultComboBoxModel<TipoPersona>(TipoPersona.values()));
			cbxTipoCandidato.setSelectedIndex(-1);
			panel.add(cbxTipoCandidato);

			cbxJornada = new ComboBoxRedond<Jornada>(15);
			cbxJornada.setForeground(new Color(0, 0, 51));
			cbxJornada.setFont(new Font("Calibri", Font.PLAIN, 16));
			cbxJornada.setBackground(SystemColor.controlHighlight);
			cbxJornada.setBounds(587, 110, 220, 30);
			cbxJornada.setModel(new DefaultComboBoxModel<Jornada>(Jornada.values()));
			cbxJornada.setSelectedIndex(-1);
			panel.add(cbxJornada);
		}

		{
			pnlListado = new JPanel();
			pnlListado.setBackground(new Color(255, 255, 255));
			pnlListado.setLayout(null);

			scrollListado = new JScrollPane(pnlListado);
			scrollListado.setBorder(null);
			scrollListado.setBounds(27, 175, LIST_FULL, 435);
			panel.add(scrollListado);
		}

		{
			pnlDetalle = new PanelRedond(15);
			pnlDetalle.setBackground(new Color(255, 255, 255));
			pnlDetalle.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
			pnlDetalle.setBounds(DETALLE_X, 175, DETALLE_ANCHO, 435);
			pnlDetalle.setLayout(null);
			pnlDetalle.setVisible(false);
			panel.add(pnlDetalle);

			lblDetPuesto = new JLabel();
			lblDetPuesto.setForeground(new Color(0, 0, 51));
			lblDetPuesto.setFont(new Font("Calibri", Font.BOLD, 20));
			lblDetPuesto.setBounds(20, 20, 390, 28);
			pnlDetalle.add(lblDetPuesto);

			lblDetSub = new JLabel();
			lblDetSub.setForeground(new Color(120, 120, 120));
			lblDetSub.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblDetSub.setBounds(20, 50, 390, 22);
			pnlDetalle.add(lblDetSub);

			txtDetDescripcion = new JTextArea();
			txtDetDescripcion.setForeground(new Color(60, 60, 60));
			txtDetDescripcion.setFont(new Font("Calibri", Font.PLAIN, 16));
			txtDetDescripcion.setLineWrap(true);
			txtDetDescripcion.setWrapStyleWord(true);
			txtDetDescripcion.setEditable(false);
			txtDetDescripcion.setOpaque(false);
			txtDetDescripcion.setBounds(20, 90, 390, 260);
			pnlDetalle.add(txtDetDescripcion);

			btnPostularme = new BotonRedond("Postularme", 25);
			btnPostularme.setFont(new Font("Calibri", Font.PLAIN, 18));
			btnPostularme.setBackground(new Color(255, 153, 0));
			btnPostularme.setForeground(new Color(0, 0, 51));
			btnPostularme.setBounds(20, 370, 160, 36);
			pnlDetalle.add(btnPostularme);
		}

		cargarCiudadesDisponibles();
		buscar();
	}

	private void cargarCiudadesDisponibles() {
		ArrayList<String> ciudadesEncontradas = new ArrayList<String>();

		for (Oferta oferta : BolsaEmpleo.getInstancia().getOfertas()) {
			if (!ciudadesEncontradas.contains(oferta.getCiudad())) {
				ciudadesEncontradas.add(oferta.getCiudad());
			}
		}

		cbxCiudad.setModel(new DefaultComboBoxModel<String>(ciudadesEncontradas.toArray(new String[0])));
		cbxCiudad.setSelectedIndex(-1);
	}

	private void buscar() {
		ArrayList<Oferta> resultado = new ArrayList<Oferta>();

		for (Oferta oferta : BolsaEmpleo.getInstancia().getOfertas()) {
			if (cumpleFiltros(oferta)) {
				resultado.add(oferta);
			}
		}

		resultados = resultado;
		cerrarDetalle();
	}

	private boolean cumpleFiltros(Oferta oferta) {
		if (oferta.getEstado() != EstadoOferta.PENDIENTE) {
			return false;
		}

		String puestoBuscado = txtPuesto.getText().trim();
		if (!puestoBuscado.isEmpty() && !oferta.getPuesto().toLowerCase().contains(puestoBuscado.toLowerCase())) {
			return false;
		}

		if (cbxCiudad.getSelectedIndex() != -1 && !oferta.getCiudad().equalsIgnoreCase((String) cbxCiudad.getSelectedItem())) {
			return false;
		}

		if (cbxMudarse.getSelectedIndex() != -1) {
			boolean requiereMudar = cbxMudarse.getSelectedItem().equals("S\u00ED");
			if (oferta.isDispMudar() != requiereMudar) {
				return false;
			}
		}

		if (cbxTipoCandidato.getSelectedIndex() != -1 && oferta.getTipoCandidato() != cbxTipoCandidato.getSelectedItem()) {
			return false;
		}

		if (cbxJornada.getSelectedIndex() != -1 && oferta.getJornada() != cbxJornada.getSelectedItem()) {
			return false;
		}

		return true;
	}

	private void actualizarListado() {
		pnlListado.removeAll();
		tarjetas.clear();

		int y = 0;
		for (Oferta oferta : resultados) {
			PanelRedond tarjeta = crearTarjeta(oferta, y);
			pnlListado.add(tarjeta);
			tarjetas.add(tarjeta);
			y += 80;
		}

		pnlListado.setPreferredSize(new java.awt.Dimension(listWidth, y));
		pnlListado.revalidate();
		pnlListado.repaint();
	}

	private PanelRedond crearTarjeta(final Oferta oferta, int y) {
		PanelRedond tarjeta = new PanelRedond(10);
		tarjeta.setBackground(new Color(255, 255, 255));
		tarjeta.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
		tarjeta.setBounds(0, y, listWidth, 70);
		tarjeta.setLayout(null);

		JLabel lblPuesto = new JLabel(oferta.getPuesto());
		lblPuesto.setForeground(new Color(0, 0, 51));
		lblPuesto.setFont(new Font("Calibri", Font.BOLD, 16));
		lblPuesto.setBounds(15, 10, listWidth - 140, 20);
		tarjeta.add(lblPuesto);

		JLabel lblSub = new JLabel(oferta.getEmpresa().getNombre() + " - " + oferta.getCiudad() + " - " + oferta.getJornada());
		lblSub.setForeground(new Color(130, 130, 130));
		lblSub.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSub.setBounds(15, 34, listWidth - 140, 20);
		tarjeta.add(lblSub);

		BotonRedond btnLeerMas = new BotonRedond("Leer m\u00E1s", 15);
		btnLeerMas.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnLeerMas.setBackground(new Color(255, 153, 0));
		btnLeerMas.setForeground(new Color(0, 0, 51));
		btnLeerMas.setBounds(listWidth - 110, 20, 90, 30);
		btnLeerMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDetalle(oferta);
			}
		});
		tarjeta.add(btnLeerMas);

		return tarjeta;
	}

	private void mostrarDetalle(Oferta oferta) {
		ofertaSeleccionada = oferta;
		listWidth = LIST_SPLIT;
		scrollListado.setBounds(27, 175, LIST_SPLIT, 435);
		actualizarListado();

		for (int i = 0; i < tarjetas.size(); i++) {
			if (resultados.get(i) == oferta) {
				tarjetas.get(i).setBorder(new LineBorder(new Color(255, 87, 87), 2, true));
			} else {
				tarjetas.get(i).setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
			}
		}

		lblDetPuesto.setText(oferta.getPuesto());
		lblDetSub.setText(oferta.getEmpresa().getNombre() + " - " + oferta.getCiudad() + " - " + oferta.getJornada());
		txtDetDescripcion.setText(oferta.getDescripPuesto());
		pnlDetalle.setVisible(true);
	}

	private void cerrarDetalle() {
		ofertaSeleccionada = null;
		listWidth = LIST_FULL;
		scrollListado.setBounds(27, 175, LIST_FULL, 435);
		pnlDetalle.setVisible(false);
		actualizarListado();
	}
	
	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
	    ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
	    Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	    boton.setIcon(new ImageIcon(imagenEscalada));
	}
}