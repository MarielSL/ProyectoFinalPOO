package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.EstadoSolicitud;
import logico.Oferta;
import logico.SolicitudEmpleo;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ConfirmSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblPuesto;
	private JLabel lblEmpresa;
	private JLabel lblSalario;
	private TextFieldRedond txtCoincidencia;
	private BotonRedond btnCancelar;
	private BotonRedond btnConfirmar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmSolicitud dialog = new ConfirmSolicitud(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfirmSolicitud(Oferta oferta) {
		setTitle("Confirmar Postulaci\u00F3n");
		setBounds(100, 100, 537, 462);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 519, 53);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u00BFDesea Postularse a esta Oferta?");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setForeground(new Color(255, 153, 0));
		lblNewLabel.setBounds(92, 13, 334, 27);
		panel.add(lblNewLabel);
		
		lblPuesto = new JLabel("Puesto de Trabajo: ");
		lblPuesto.setForeground(new Color(0, 0, 51));
		lblPuesto.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblPuesto.setBounds(32, 88, 433, 26);
		contentPanel.add(lblPuesto);
		
		lblEmpresa = new JLabel("Empresa: ");
		lblEmpresa.setForeground(new Color(0, 0, 51));
		lblEmpresa.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblEmpresa.setBounds(32, 141, 433, 26);
		contentPanel.add(lblEmpresa);
		
		lblSalario = new JLabel("Salario:");
		lblSalario.setForeground(new Color(0, 0, 51));
		lblSalario.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblSalario.setBounds(32, 192, 433, 26);
		contentPanel.add(lblSalario);
		
		txtCoincidencia = new TextFieldRedond(25);
		txtCoincidencia.setEditable(false);
		txtCoincidencia.setForeground(new Color(0, 0, 51));
		txtCoincidencia.setBackground(SystemColor.controlHighlight);
		txtCoincidencia.setHorizontalAlignment(SwingConstants.CENTER);
		txtCoincidencia.setFont(new Font("Calibri", Font.PLAIN, 22));
		txtCoincidencia.setBounds(62, 254, 378, 35);
		contentPanel.add(txtCoincidencia);
		txtCoincidencia.setColumns(10);
		txtCoincidencia.setText("Coincidencia con su Perfil: ");
		txtCoincidencia.setFocusable(false);
		
		btnCancelar = new BotonRedond("Cancelar",25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(0, 0, 51));
		btnCancelar.setForeground(new Color(255, 153, 0));
		btnCancelar.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnCancelar.setBounds(73, 335, 110, 35);
		contentPanel.add(btnCancelar);
		
		btnConfirmar = new BotonRedond("Confirmar",25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(oferta!=null ){
					SolicitudEmpleo solicitud = new SolicitudEmpleo("SE-"+BolsaEmpleo.generadorIdSolicitud,EstadoSolicitud.PENDIENTE,BolsaEmpleo.getInstancia().getLoginUser().getPersona(),oferta,100,LocalDate.now());
					BolsaEmpleo.getInstancia().regSolicitud(oferta.getId(), solicitud);
					JOptionPane.showConfirmDialog(null, "Se ha enviado la solicitud", "Información", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else{
					dispose();
					return;
				}
				
			}
		});
		btnConfirmar.setForeground(new Color(0, 0, 51));
		btnConfirmar.setBackground(new Color(255, 153, 0));
		btnConfirmar.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnConfirmar.setBounds(316, 335, 110, 35);
		contentPanel.add(btnConfirmar);
		
		if(oferta != null) {
			SolicitudEmpleo solicitud = new SolicitudEmpleo("SE-"+BolsaEmpleo.generadorIdSolicitud,EstadoSolicitud.PENDIENTE,BolsaEmpleo.getInstancia().getLoginUser().getPersona(),oferta,100,LocalDate.now());
			lblPuesto.setText("Puesto de Trabajo: " + oferta.getPuesto());
			lblEmpresa.setText("Empresa: " + oferta.getEmpresa().getNombre() );
			lblSalario.setText("Salario: " + "$RD " + oferta.getSalario());
			
			txtCoincidencia.setText("Coincidencia con su Perfil: " + solicitud.getPorcentajeCoincidencia() + "%");
			if(solicitud.getPorcentajeCoincidencia()>=75) {
				txtCoincidencia.setForeground(new Color(0, 102, 0));
				txtCoincidencia.setBackground(new Color(153, 204, 153));
			}
			if(solicitud.getPorcentajeCoincidencia()>=50 && solicitud.getPorcentajeCoincidencia()<75) {
				txtCoincidencia.setForeground(new Color(184, 134, 11));
				txtCoincidencia.setBackground(new Color(238, 232, 170));
			}
			if(solicitud.getPorcentajeCoincidencia()>=25 && solicitud.getPorcentajeCoincidencia()<50) {
				txtCoincidencia.setForeground(new Color(160, 82, 45));
				txtCoincidencia.setBackground(new Color(233, 150, 122));
			}
			if(solicitud.getPorcentajeCoincidencia()>=0 && solicitud.getPorcentajeCoincidencia()<25) {
				txtCoincidencia.setForeground(new Color(153, 0, 0));
				txtCoincidencia.setBackground(new Color(255, 153, 153));
			}
		}
		
		
	}

}
