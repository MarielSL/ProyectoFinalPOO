package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import logico.AreaLaboral;
import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoOferta;
import logico.Jornada;
import logico.Modalidad;
import logico.Oferta;
import logico.Sexo;
import logico.TipoPersona;
import red.ConexionCliente;
import red.DatosPublicarOferta;
import red.Peticion;
import red.Respuesta;

public class RegistrarOferta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim = getToolkit().getScreenSize();
	private Empresa empresa;
	private JLabel lblLogo;
	private JLabel lblFondo;
	private TextFieldRedond txtPuesto;
	private SpinnerRedond spnCantPuestos;
	private JTextArea txtDescripcion;
	private ComboBoxRedond<Sexo> cbxSexo;
	private ComboBoxRedond<TipoPersona> cbxTipoCandidato;
	private SpinnerRedond spnAniosExp;
	private JCheckBox chkLicencia;
	private JCheckBox chkMudarse;
	private ComboBoxRedond<Jornada> cbxJornada;
	private ComboBoxRedond<Modalidad> cbxModalidad;
	private TextFieldRedond txtCiudad;
	private SpinnerRedond spnSalario;
	private BotonRedond btnPublicar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarOferta dialog = new RegistrarOferta(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegistrarOferta(Empresa empresa) {
		this.empresa = empresa;
		setResizable(false);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarOferta.class.getResource("/img/AppIconoFull.png")));
		setTitle("Publicar Oferta");
		setBounds(0, 0, dim.width, dim.height - 55);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 950, 1005);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblLogo = new JLabel("New label");
		lblLogo.setIcon(new ImageIcon(RegistrarOferta.class.getResource("/img/HireLink_logo_full.png")));
		lblLogo.setBounds(58, 40, 320, 90);
		panel.add(lblLogo);
		colocarImagen(lblLogo, "/img/HireLink_logo_full.png");

		JLabel lblTitulo = new JLabel("Publicar Oferta");
		lblTitulo.setForeground(new Color(0, 0, 51));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 38));
		lblTitulo.setBounds(58, 150, 500, 44);
		panel.add(lblTitulo);

		JLabel lblSubtitulo = new JLabel("Publica tu vacante y encuentra el talento ideal.");
		lblSubtitulo.setForeground(new Color(102, 102, 102));
		lblSubtitulo.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblSubtitulo.setBounds(58, 198, 650, 28);
		panel.add(lblSubtitulo);

		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.activeCaptionBorder);
		separator.setBounds(58, 244, 873, 2);
		panel.add(separator);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setForeground(new Color(0, 0, 51));
		lblPuesto.setFont(new Font("Calibri", Font.BOLD, 20));
		lblPuesto.setBounds(58, 275, 300, 24);
		panel.add(lblPuesto);

		txtPuesto = new TextFieldRedond(25);
		txtPuesto.setForeground(new Color(0, 0, 51));
		txtPuesto.setBackground(SystemColor.controlHighlight);
		txtPuesto.setFont(new Font("Calibri", Font.PLAIN, 20));
		txtPuesto.setBounds(58, 305, 873, 36);
		panel.add(txtPuesto);

		JLabel lblTipoCandidato = new JLabel("Tipo de candidato");
		lblTipoCandidato.setForeground(new Color(0, 0, 51));
		lblTipoCandidato.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTipoCandidato.setBounds(58, 365, 275, 24);
		panel.add(lblTipoCandidato);

		cbxTipoCandidato = new ComboBoxRedond<TipoPersona>(25);
		cbxTipoCandidato.setForeground(new Color(0, 0, 51));
		cbxTipoCandidato.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxTipoCandidato.setBackground(SystemColor.controlHighlight);
		cbxTipoCandidato.setBounds(58, 395, 275, 36);
		cbxTipoCandidato.setModel(new DefaultComboBoxModel<TipoPersona>(TipoPersona.values()));
		cbxTipoCandidato.setSelectedIndex(-1);
		panel.add(cbxTipoCandidato);

		JLabel lblSexo = new JLabel("Sexo requerido");
		lblSexo.setForeground(new Color(0, 0, 51));
		lblSexo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblSexo.setBounds(357, 365, 275, 24);
		panel.add(lblSexo);

		cbxSexo = new ComboBoxRedond<Sexo>(25);
		cbxSexo.setForeground(new Color(0, 0, 51));
		cbxSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxSexo.setBackground(SystemColor.controlHighlight);
		cbxSexo.setBounds(357, 395, 275, 36);
		cbxSexo.setModel(new DefaultComboBoxModel<Sexo>(Sexo.values()));
		cbxSexo.setSelectedIndex(-1);
		panel.add(cbxSexo);

		JLabel lblCantPuestos = new JLabel("Cantidad de puestos");
		lblCantPuestos.setForeground(new Color(0, 0, 51));
		lblCantPuestos.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCantPuestos.setBounds(656, 365, 275, 24);
		panel.add(lblCantPuestos);

		spnCantPuestos = new SpinnerRedond(25);
		spnCantPuestos.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spnCantPuestos.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnCantPuestos.setForeground(new Color(0, 0, 51));
		spnCantPuestos.setBackground(SystemColor.controlHighlight);
		spnCantPuestos.setBounds(656, 395, 200, 36);
		panel.add(spnCantPuestos);
		spnCantPuestos.aplicarColorSpinner(spnCantPuestos, SystemColor.controlHighlight);

		JLabel lblJornada = new JLabel("Jornada");
		lblJornada.setForeground(new Color(0, 0, 51));
		lblJornada.setFont(new Font("Calibri", Font.BOLD, 20));
		lblJornada.setBounds(58, 455, 275, 24);
		panel.add(lblJornada);

		cbxJornada = new ComboBoxRedond<Jornada>(25);
		cbxJornada.setForeground(new Color(0, 0, 51));
		cbxJornada.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxJornada.setBackground(SystemColor.controlHighlight);
		cbxJornada.setBounds(58, 485, 275, 36);
		cbxJornada.setModel(new DefaultComboBoxModel<Jornada>(Jornada.values()));
		cbxJornada.setSelectedIndex(-1);
		panel.add(cbxJornada);

		JLabel lblModalidad = new JLabel("Modalidad");
		lblModalidad.setForeground(new Color(0, 0, 51));
		lblModalidad.setFont(new Font("Calibri", Font.BOLD, 20));
		lblModalidad.setBounds(357, 455, 275, 24);
		panel.add(lblModalidad);

		cbxModalidad = new ComboBoxRedond<Modalidad>(25);
		cbxModalidad.setForeground(new Color(0, 0, 51));
		cbxModalidad.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxModalidad.setBackground(SystemColor.controlHighlight);
		cbxModalidad.setBounds(357, 485, 275, 36);
		cbxModalidad.setModel(new DefaultComboBoxModel<Modalidad>(Modalidad.values()));
		cbxModalidad.setSelectedIndex(-1);
		panel.add(cbxModalidad);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setForeground(new Color(0, 0, 51));
		lblCiudad.setFont(new Font("Calibri", Font.BOLD, 20));
		lblCiudad.setBounds(656, 455, 275, 24);
		panel.add(lblCiudad);

		txtCiudad = new TextFieldRedond(25);
		txtCiudad.setForeground(new Color(0, 0, 51));
		txtCiudad.setBackground(SystemColor.controlHighlight);
		txtCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCiudad.setBounds(656, 485, 275, 36);
		panel.add(txtCiudad);

		JLabel lblSalario = new JLabel("Salario ofrecido");
		lblSalario.setForeground(new Color(0, 0, 51));
		lblSalario.setFont(new Font("Calibri", Font.BOLD, 20));
		lblSalario.setBounds(58, 555, 275, 24);
		panel.add(lblSalario);

		spnSalario = new SpinnerRedond(25);
		spnSalario.setModel(new SpinnerNumberModel(0, 0, 1000000, 500));
		spnSalario.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnSalario.setForeground(new Color(0, 0, 51));
		spnSalario.setBackground(SystemColor.controlHighlight);
		spnSalario.setBounds(58, 585, 275, 36);
		panel.add(spnSalario);
		spnSalario.aplicarColorSpinner(spnSalario, SystemColor.controlHighlight);

		JLabel lblAniosExp = new JLabel("A\u00F1os de experiencia");
		lblAniosExp.setForeground(new Color(0, 0, 51));
		lblAniosExp.setFont(new Font("Calibri", Font.BOLD, 20));
		lblAniosExp.setBounds(357, 555, 275, 24);
		panel.add(lblAniosExp);

		spnAniosExp = new SpinnerRedond(25);
		spnAniosExp.setModel(new SpinnerNumberModel(0, 0, 50, 1));
		spnAniosExp.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnAniosExp.setForeground(new Color(0, 0, 51));
		spnAniosExp.setBackground(SystemColor.controlHighlight);
		spnAniosExp.setBounds(357, 585, 200, 36);
		panel.add(spnAniosExp);
		spnAniosExp.aplicarColorSpinner(spnAniosExp, SystemColor.controlHighlight);

		chkLicencia = new JCheckBox("Requiere licencia de conducir");
		chkLicencia.setForeground(new Color(0, 0, 51));
		chkLicencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkLicencia.setOpaque(false);
		chkLicencia.setBounds(656, 590, 320, 30);
		panel.add(chkLicencia);

		chkMudarse = new JCheckBox("Requiere disponibilidad para mudarse");
		chkMudarse.setForeground(new Color(0, 0, 51));
		chkMudarse.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkMudarse.setOpaque(false);
		chkMudarse.setBounds(58, 640, 420, 30);
		panel.add(chkMudarse);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n del puesto");
		lblDescripcion.setForeground(new Color(0, 0, 51));
		lblDescripcion.setFont(new Font("Calibri", Font.BOLD, 20));
		lblDescripcion.setBounds(58, 695, 300, 24);
		panel.add(lblDescripcion);

		txtDescripcion = new JTextArea();
		txtDescripcion.setForeground(new Color(0, 0, 51));
		txtDescripcion.setBackground(SystemColor.controlHighlight);
		txtDescripcion.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
		scrollDescripcion.setBounds(58, 725, 873, 160);
		panel.add(scrollDescripcion);

		btnPublicar = new BotonRedond("Publicar", 25);
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!validarDatos()) {
					return;
				}
				registrarOfertaConHilo();
			}
		});
		btnPublicar.setBackground(new Color(255, 153, 0));
		btnPublicar.setForeground(new Color(0, 0, 51));
		btnPublicar.setFont(new Font("Calibri", Font.BOLD, 20));
		btnPublicar.setBounds(711, 912, 220, 50);
		panel.add(btnPublicar);
		
		BotonRedond botonRedond = new BotonRedond(" \u2190  Volver", 30);
		botonRedond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeEmpresa volver = new HomeEmpresa();
				volver.setVisible(true);
				dispose();
			}
		});
		botonRedond.setForeground(new Color(0, 0, 51));
		botonRedond.setFont(new Font("Calibri", Font.PLAIN, 20));
		botonRedond.setColorHover(new Color(255, 220, 183));
		botonRedond.setBackground(new Color(255, 235, 215));
		botonRedond.setBounds(58, 912, 220, 50);
		panel.add(botonRedond);

		lblFondo = new JLabel("New label");
		lblFondo.setIcon(new ImageIcon(RegistrarOferta.class.getResource("/img/Fondo_Registrar_Oferta.png")));
		lblFondo.setBounds(0, 0, 1914, 1005);
		contentPanel.add(lblFondo);
		colocarImagen(lblFondo, "/img/Fondo_Registrar_Oferta.png");
	}

	private boolean validarDatos() {
		if (!Validaciones.camposLlenos(txtPuesto.getText(), txtDescripcion.getText(), txtCiudad.getText())
				|| cbxSexo.getSelectedIndex() == -1
				|| cbxTipoCandidato.getSelectedIndex() == -1
				|| cbxJornada.getSelectedIndex() == -1
				|| cbxModalidad.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!Validaciones.soloLetras(txtCiudad.getText())) {
			JOptionPane.showMessageDialog(null, "La ciudad solo debe contener letras.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	//implementacion de hilos y socket
	private void registrarOfertaConHilo() {

	    String puesto = txtPuesto.getText().trim();
	    String ciudad = txtCiudad.getText().trim();
	    String descripcion = txtDescripcion.getText().trim();
	    Sexo sexo = (Sexo) cbxSexo.getSelectedItem();
	    TipoPersona tipoCandidato = (TipoPersona) cbxTipoCandidato.getSelectedItem();
	    Jornada jornada = (Jornada) cbxJornada.getSelectedItem();
	    Modalidad modalidad = (Modalidad) cbxModalidad.getSelectedItem();
	    int cantPuestos = (Integer) spnCantPuestos.getValue();
	    int aniosExp = (Integer) spnAniosExp.getValue();
	    float salario = ((Number) spnSalario.getValue()).floatValue();
	    boolean licencia = chkLicencia.isSelected();
	    boolean mudarse = chkMudarse.isSelected();

	    btnPublicar.setEnabled(false);
	    btnPublicar.setText("Publicando...");

	    SwingWorker<Oferta, Void> hilo = new SwingWorker<Oferta, Void>() {

	        @Override
	        protected Oferta doInBackground() throws Exception {

	            DatosPublicarOferta datos = new DatosPublicarOferta(sexo, tipoCandidato, puesto, cantPuestos,
	                    licencia, mudarse, jornada, ciudad, salario, descripcion, aniosExp, modalidad,
	                    AreaLaboral.INGENIERIA);

	            Peticion peticion = new Peticion(Peticion.Tipo.PUBLICAR_OFERTA, datos);
	            Respuesta respuesta = ConexionCliente.getInstancia().enviarPeticion(peticion);

	            if (!respuesta.isExito()) {
	                throw new IllegalArgumentException(respuesta.getDatos().toString());
	            }

	            return (Oferta) respuesta.getDatos();
	        }

	        protected void done() {

	            try {
	                get();

	                JOptionPane.showMessageDialog(RegistrarOferta.this, "Se ha publicado la oferta.", "Información", JOptionPane.INFORMATION_MESSAGE);
	                clear();

	            } catch (Exception e) {
	                Throwable causa = e.getCause();
	                String mensaje = causa != null ? causa.getMessage() : e.getMessage();
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(RegistrarOferta.this, mensaje != null ? mensaje : "No se pudo publicar la oferta.", "Error", JOptionPane.ERROR_MESSAGE);

	            } finally {
	                btnPublicar.setEnabled(true);
	                btnPublicar.setText("Publicar");
	            }
	        }

	    };
	    hilo.execute();
	}

	private void clear() {
		txtPuesto.setText("");
		spnCantPuestos.setValue(1);
		txtDescripcion.setText("");
		cbxSexo.setSelectedIndex(-1);
		cbxTipoCandidato.setSelectedIndex(-1);
		spnAniosExp.setValue(0);
		chkLicencia.setSelected(false);
		chkMudarse.setSelected(false);
		cbxJornada.setSelectedIndex(-1);
		cbxModalidad.setSelectedIndex(-1);
		txtCiudad.setText("");
		spnSalario.setValue(0);
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