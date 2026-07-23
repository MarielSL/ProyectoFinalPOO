package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoOferta;
import logico.Jornada;
import logico.Modalidad;
import logico.Oferta;
import logico.Sexo;
import logico.TipoPersona;

public class RegistrarOferta extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private Empresa empresa;
	private CardLayout stepsLayout;
	private JPanel pnlSteps;
	private int pasoActual = 1;
	private JLabel[] dots = new JLabel[3];
	private BotonRedond btnAtras;
	private BotonRedond btnSiguiente;
	private TextFieldRedond txtPuesto;
	private JSpinner spnCantPuestos;
	private JTextArea txtDescripcion;
	private ComboBoxRedond<Sexo> cbxSexo;
	private ComboBoxRedond<TipoPersona> cbxTipoCandidato;
	private JSpinner spnAniosExp;
	private JCheckBox chkLicencia;
	private JCheckBox chkMudarse;
	private ComboBoxRedond<Jornada> cbxJornada;
	private ComboBoxRedond<Modalidad> cbxModalidad;
	private TextFieldRedond txtCiudad;
	private JSpinner spnSalario;

	public static void main(String[] args) {
		try {
			RegistrarOferta dialog = new RegistrarOferta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegistrarOferta(Empresa empresa) {
		this.empresa = empresa;
		setTitle("Publicar Oferta");
		setBounds(100, 100, 734, 560);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 724, 550);
		contentPanel.add(panel);
		panel.setLayout(null);
		PanelRedond panelTitulo = new PanelRedond(30);
		panelTitulo.setBackground(new Color(0, 0, 51));
		panelTitulo.setBounds(27, 18, 664, 45);
		panel.add(panelTitulo);
		panelTitulo.setLayout(null);
		JLabel lblTitulo = new JLabel("Publica una nueva oportunidad laboral");
		lblTitulo.setForeground(new Color(255, 153, 0));
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
		lblTitulo.setBounds(20, 10, 500, 27);
		panelTitulo.add(lblTitulo);
		PanelRedond cardBlanca = new PanelRedond(20);
		cardBlanca.setBackground(new Color(255, 255, 255));
		cardBlanca.setBounds(27, 75, 664, 330);
		panel.add(cardBlanca);
		cardBlanca.setLayout(null);
		stepsLayout = new CardLayout();
		pnlSteps = new JPanel(stepsLayout);
		pnlSteps.setOpaque(false);
		pnlSteps.setBounds(20, 15, 624, 300);
		cardBlanca.add(pnlSteps);
		pnlSteps.add(crearPaso1(), "paso1");
		pnlSteps.add(crearPaso2(), "paso2");
		pnlSteps.add(crearPaso3(), "paso3");
		int xDot = 312;
		for (int i = 0; i < 3; i++) {
			JLabel dot = new JLabel("\u25CF", JLabel.CENTER);
			dot.setFont(new Font("Calibri", Font.PLAIN, 22));
			dot.setBounds(xDot, 420, 20, 20);
			dots[i] = dot;
			panel.add(dot);
			xDot += 30;
		}
		btnAtras = new BotonRedond("Atr\u00E1s", 25);
		btnAtras.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAtras.setBackground(new Color(220, 220, 220));
		btnAtras.setForeground(new Color(0, 0, 51));
		btnAtras.setBounds(27, 460, 110, 34);
		btnAtras.setVisible(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irAtras();
			}
		});
		panel.add(btnAtras);
		btnSiguiente = new BotonRedond("Continuar", 25);
		btnSiguiente.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSiguiente.setBackground(new Color(255, 153, 0));
		btnSiguiente.setForeground(new Color(0, 0, 51));
		btnSiguiente.setBounds(577, 460, 114, 34);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irSiguiente();
			}
		});
		panel.add(btnSiguiente);
		actualizarDots();
	}

	private void irSiguiente() {
		if (pasoActual == 1) {
			if (!validarPaso1())
				return;
			stepsLayout.show(pnlSteps, "paso2");
			pasoActual = 2;
			btnAtras.setVisible(true);
		} else if (pasoActual == 2) {
			if (!validarPaso2())
				return;
			stepsLayout.show(pnlSteps, "paso3");
			pasoActual = 3;
			btnSiguiente.setText("Publicar");
		} else {
			if (!validarPaso3())
				return;
			registrarOferta();
			return;
		}
		actualizarDots();
	}

	private void irAtras() {
		if (pasoActual == 3) {
			stepsLayout.show(pnlSteps, "paso2");
			pasoActual = 2;
			btnSiguiente.setText("Continuar");
		} else if (pasoActual == 2) {
			stepsLayout.show(pnlSteps, "paso1");
			pasoActual = 1;
			btnAtras.setVisible(false);
		}
		actualizarDots();
	}

	private void actualizarDots() {
		for (int i = 0; i < 3; i++) {
			if (i + 1 == pasoActual) {
				dots[i].setForeground(new Color(255, 153, 0));
			} else {
				dots[i].setForeground(new Color(200, 200, 200));
			}
		}
	}

	private boolean validarPaso1() {
		if (txtPuesto.getText().trim().isEmpty() || txtDescripcion.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe de completar todos los datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validarPaso2() {
		if (cbxSexo.getSelectedIndex() == -1 || cbxTipoCandidato.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar el sexo y el tipo de candidato.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validarPaso3() {
		if (cbxJornada.getSelectedIndex() == -1 || cbxModalidad.getSelectedIndex() == -1 || txtCiudad.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private void registrarOferta() {
		String id = "O-" + BolsaEmpleo.generadorIdOferta;
		Sexo sexo = (Sexo) cbxSexo.getSelectedItem();
		TipoPersona tipoCandidato = (TipoPersona) cbxTipoCandidato.getSelectedItem();
		Jornada jornada = (Jornada) cbxJornada.getSelectedItem();
		Modalidad modalidad = (Modalidad) cbxModalidad.getSelectedItem();
		int aniosExp = (Integer) spnAniosExp.getValue();
		float salario = ((Number) spnSalario.getValue()).floatValue();
		Oferta oferta = new Oferta(id, sexo, tipoCandidato, txtPuesto.getText(), (Integer) spnCantPuestos.getValue(), chkLicencia.isSelected(), chkMudarse.isSelected(), EstadoOferta.PENDIENTE, jornada, txtCiudad.getText(), salario, txtDescripcion.getText(), aniosExp, empresa, modalidad);
		BolsaEmpleo.getInstancia().refOferta(oferta);
		empresa.agregarOferta(oferta);
		JOptionPane.showMessageDialog(null, "Se ha publicado la oferta.", "Informaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
		clear();
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
		stepsLayout.show(pnlSteps, "paso1");
		pasoActual = 1;
		btnAtras.setVisible(false);
		btnSiguiente.setText("Continuar");
		actualizarDots();
	}

	private JPanel crearPaso1() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setForeground(new Color(0, 0, 51));
		lblPuesto.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPuesto.setBounds(0, 0, 200, 20);
		paso.add(lblPuesto);
		txtPuesto = new TextFieldRedond(25);
		txtPuesto.setForeground(new Color(0, 0, 51));
		txtPuesto.setBackground(SystemColor.controlHighlight);
		txtPuesto.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtPuesto.setBounds(0, 25, 294, 30);
		paso.add(txtPuesto);
		JLabel lblCantPuestos = new JLabel("Cantidad de puestos");
		lblCantPuestos.setForeground(new Color(0, 0, 51));
		lblCantPuestos.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCantPuestos.setBounds(330, 0, 220, 20);
		paso.add(lblCantPuestos);
		spnCantPuestos = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spnCantPuestos.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnCantPuestos.setBounds(330, 25, 100, 30);
		paso.add(spnCantPuestos);
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n del puesto");
		lblDescripcion.setForeground(new Color(0, 0, 51));
		lblDescripcion.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDescripcion.setBounds(0, 75, 300, 20);
		paso.add(lblDescripcion);
		txtDescripcion = new JTextArea();
		txtDescripcion.setForeground(new Color(0, 0, 51));
		txtDescripcion.setBackground(SystemColor.controlHighlight);
		txtDescripcion.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
		scrollDescripcion.setBounds(0, 100, 624, 160);
		paso.add(scrollDescripcion);
		return paso;
	}

	private JPanel crearPaso2() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblSexo = new JLabel("Sexo requerido");
		lblSexo.setForeground(new Color(0, 0, 51));
		lblSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSexo.setBounds(0, 0, 200, 20);
		paso.add(lblSexo);
		cbxSexo = new ComboBoxRedond<Sexo>(25);
		cbxSexo.setForeground(new Color(0, 0, 51));
		cbxSexo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxSexo.setBackground(SystemColor.controlHighlight);
		cbxSexo.setBounds(0, 25, 294, 30);
		cbxSexo.setModel(new DefaultComboBoxModel<Sexo>(Sexo.values()));
		cbxSexo.setSelectedIndex(-1);
		paso.add(cbxSexo);
		JLabel lblTipoCandidato = new JLabel("Tipo de candidato");
		lblTipoCandidato.setForeground(new Color(0, 0, 51));
		lblTipoCandidato.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTipoCandidato.setBounds(330, 0, 220, 20);
		paso.add(lblTipoCandidato);
		cbxTipoCandidato = new ComboBoxRedond<TipoPersona>(25);
		cbxTipoCandidato.setForeground(new Color(0, 0, 51));
		cbxTipoCandidato.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxTipoCandidato.setBackground(SystemColor.controlHighlight);
		cbxTipoCandidato.setBounds(330, 25, 294, 30);
		cbxTipoCandidato.setModel(new DefaultComboBoxModel<TipoPersona>(TipoPersona.values()));
		cbxTipoCandidato.setSelectedIndex(-1);
		paso.add(cbxTipoCandidato);
		JLabel lblAniosExp = new JLabel("A\u00F1os de experiencia m\u00EDnima");
		lblAniosExp.setForeground(new Color(0, 0, 51));
		lblAniosExp.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblAniosExp.setBounds(0, 75, 280, 20);
		paso.add(lblAniosExp);
		spnAniosExp = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
		spnAniosExp.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnAniosExp.setBounds(0, 100, 100, 30);
		paso.add(spnAniosExp);
		chkLicencia = new JCheckBox("Requiere licencia de conducir");
		chkLicencia.setForeground(new Color(0, 0, 51));
		chkLicencia.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkLicencia.setOpaque(false);
		chkLicencia.setBounds(0, 150, 280, 25);
		paso.add(chkLicencia);
		chkMudarse = new JCheckBox("Requiere disponibilidad para mudarse");
		chkMudarse.setForeground(new Color(0, 0, 51));
		chkMudarse.setFont(new Font("Calibri", Font.PLAIN, 18));
		chkMudarse.setOpaque(false);
		chkMudarse.setBounds(0, 185, 330, 25);
		paso.add(chkMudarse);
		return paso;
	}

	private JPanel crearPaso3() {
		JPanel paso = new JPanel();
		paso.setOpaque(false);
		paso.setLayout(null);
		JLabel lblJornada = new JLabel("Jornada");
		lblJornada.setForeground(new Color(0, 0, 51));
		lblJornada.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblJornada.setBounds(0, 0, 200, 20);
		paso.add(lblJornada);
		cbxJornada = new ComboBoxRedond<Jornada>(25);
		cbxJornada.setForeground(new Color(0, 0, 51));
		cbxJornada.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxJornada.setBackground(SystemColor.controlHighlight);
		cbxJornada.setBounds(0, 25, 294, 30);
		cbxJornada.setModel(new DefaultComboBoxModel<Jornada>(Jornada.values()));
		cbxJornada.setSelectedIndex(-1);
		paso.add(cbxJornada);
		JLabel lblModalidad = new JLabel("Modalidad");
		lblModalidad.setForeground(new Color(0, 0, 51));
		lblModalidad.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblModalidad.setBounds(330, 0, 220, 20);
		paso.add(lblModalidad);
		cbxModalidad = new ComboBoxRedond<Modalidad>(25);
		cbxModalidad.setForeground(new Color(0, 0, 51));
		cbxModalidad.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxModalidad.setBackground(SystemColor.controlHighlight);
		cbxModalidad.setBounds(330, 25, 294, 30);
		cbxModalidad.setModel(new DefaultComboBoxModel<Modalidad>(Modalidad.values()));
		cbxModalidad.setSelectedIndex(-1);
		paso.add(cbxModalidad);
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setForeground(new Color(0, 0, 51));
		lblCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCiudad.setBounds(0, 75, 200, 20);
		paso.add(lblCiudad);
		txtCiudad = new TextFieldRedond(25);
		txtCiudad.setForeground(new Color(0, 0, 51));
		txtCiudad.setBackground(SystemColor.controlHighlight);
		txtCiudad.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCiudad.setBounds(0, 100, 294, 30);
		paso.add(txtCiudad);
		JLabel lblSalario = new JLabel("Salario ofrecido");
		lblSalario.setForeground(new Color(0, 0, 51));
		lblSalario.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblSalario.setBounds(330, 75, 220, 20);
		paso.add(lblSalario);
		spnSalario = new JSpinner(new SpinnerNumberModel(0, 0, 1000000, 500));
		spnSalario.setFont(new Font("Calibri", Font.PLAIN, 18));
		spnSalario.setBounds(330, 100, 150, 30);
		paso.add(spnSalario);
		return paso;
	}
}