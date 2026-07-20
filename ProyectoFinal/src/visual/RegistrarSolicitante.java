package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class RegistrarSolicitante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarSolicitante dialog = new RegistrarSolicitante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarSolicitante() {
		setTitle("Registrar Solicitante");
		setBounds(100, 100, 1048, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Reg\u00EDstrate para acceder a las ofertas");
				lblNewLabel.setBackground(new Color(0, 0, 51));
				lblNewLabel.setForeground(new Color(255, 153, 0));
				lblNewLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
				lblNewLabel.setBounds(24, 13, 712, 47);
				panel.add(lblNewLabel);
			}
			
			JPanel panel_1 = new JPanel() {
			    @Override
			    protected void paintComponent(Graphics g) {
			        Graphics2D g2 = (Graphics2D) g.create();
			        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        g2.setColor(getBackground());
			        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
			        g2.dispose();
			        setOpaque(false);
			        super.paintComponent(g);
			    }
			};
			panel_1.setBackground(new Color(0, 0, 51));
			panel_1.setBounds(12, 13, 980, 47);
			panel.add(panel_1);
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre");
				lblNewLabel_1.setForeground(new Color(0, 0, 51));
				lblNewLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblNewLabel_1.setBounds(43, 99, 122, 16);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblApellido = new JLabel("Apellido");
				lblApellido.setForeground(new Color(0, 0, 51));
				lblApellido.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblApellido.setBounds(366, 99, 122, 16);
				panel.add(lblApellido);
			}
			{
				JLabel lblSexo = new JLabel("Sexo");
				lblSexo.setForeground(new Color(0, 0, 51));
				lblSexo.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblSexo.setBounds(43, 174, 122, 16);
				panel.add(lblSexo);
			}
			{
				JLabel lblFechaNacim = new JLabel("Fecha Nacim.");
				lblFechaNacim.setForeground(new Color(0, 0, 51));
				lblFechaNacim.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblFechaNacim.setBounds(240, 174, 122, 16);
				panel.add(lblFechaNacim);
			}
			{
				JLabel lblTelfono = new JLabel("Tel\u00E9fono");
				lblTelfono.setForeground(new Color(0, 0, 51));
				lblTelfono.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblTelfono.setBounds(416, 174, 122, 16);
				panel.add(lblTelfono);
			}
			{
				JLabel lblDireccin = new JLabel("Direcci\u00F3n");
				lblDireccin.setForeground(new Color(0, 0, 51));
				lblDireccin.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblDireccin.setBounds(43, 251, 122, 16);
				panel.add(lblDireccin);
			}
			{
				JLabel lblCiudad = new JLabel("Ciudad");
				lblCiudad.setForeground(new Color(0, 0, 51));
				lblCiudad.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblCiudad.setBounds(366, 251, 122, 16);
				panel.add(lblCiudad);
			}
			{
				JLabel lblDisponibilidades = new JLabel("Disponibilidades:");
				lblDisponibilidades.setForeground(new Color(0, 0, 51));
				lblDisponibilidades.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
				lblDisponibilidades.setBounds(43, 322, 147, 16);
				panel.add(lblDisponibilidades);
			}
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("Mudarse");
			chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
			chckbxNewCheckBox.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			chckbxNewCheckBox.setForeground(new Color(0, 0, 51));
			chckbxNewCheckBox.setBounds(43, 347, 113, 25);
			panel.add(chckbxNewCheckBox);
			
			JCheckBox chckbxLicencia = new JCheckBox("Licencia");
			chckbxLicencia.setForeground(new Color(0, 0, 51));
			chckbxLicencia.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			chckbxLicencia.setBackground(Color.WHITE);
			chckbxLicencia.setBounds(188, 347, 113, 25);
			panel.add(chckbxLicencia);
			
			JCheckBox chckbxEmpleo = new JCheckBox("Empleo");
			chckbxEmpleo.setForeground(new Color(0, 0, 51));
			chckbxEmpleo.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			chckbxEmpleo.setBackground(Color.WHITE);
			chckbxEmpleo.setBounds(340, 347, 113, 25);
			panel.add(chckbxEmpleo);
			
			JLabel lblUsuario = new JLabel("Usuario");
			lblUsuario.setForeground(new Color(0, 0, 51));
			lblUsuario.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			lblUsuario.setBounds(43, 453, 122, 16);
			panel.add(lblUsuario);
			
			textField = new JTextField();
			textField.setBackground(new Color(0, 0, 51));
			textField.setForeground(new Color(255, 255, 255));
			textField.setBounds(43, 117, 294, 22);
			panel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setForeground(Color.WHITE);
			textField_1.setColumns(10);
			textField_1.setBackground(new Color(0, 0, 51));
			textField_1.setBounds(366, 117, 294, 22);
			panel.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setForeground(Color.WHITE);
			textField_2.setColumns(10);
			textField_2.setBackground(new Color(0, 0, 51));
			textField_2.setBounds(43, 269, 294, 22);
			panel.add(textField_2);
			
			textField_3 = new JTextField();
			textField_3.setForeground(Color.WHITE);
			textField_3.setColumns(10);
			textField_3.setBackground(new Color(0, 0, 51));
			textField_3.setBounds(366, 269, 294, 22);
			panel.add(textField_3);
			
			textField_4 = new JTextField();
			textField_4.setForeground(Color.WHITE);
			textField_4.setColumns(10);
			textField_4.setBackground(new Color(0, 0, 51));
			textField_4.setBounds(43, 470, 294, 22);
			panel.add(textField_4);
			
			JLabel lblUsuario_1 = new JLabel("Contrase\u00F1a");
			lblUsuario_1.setForeground(new Color(0, 0, 51));
			lblUsuario_1.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			lblUsuario_1.setBounds(366, 453, 122, 16);
			panel.add(lblUsuario_1);
			
			textField_6 = new JTextField();
			textField_6.setForeground(Color.WHITE);
			textField_6.setColumns(10);
			textField_6.setBackground(new Color(0, 0, 51));
			textField_6.setBounds(416, 191, 244, 22);
			panel.add(textField_6);
			
			JButton btnNewButton = new JButton("Registrar");
			btnNewButton.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			btnNewButton.setBackground(new Color(255, 153, 0));
			btnNewButton.setForeground(new Color(0, 0, 51));
			btnNewButton.setBounds(844, 516, 135, 25);
			panel.add(btnNewButton);
			
			passwordField = new JPasswordField();
			passwordField.setForeground(new Color(255, 255, 255));
			passwordField.setBackground(new Color(0, 0, 51));
			passwordField.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			passwordField.setBounds(366, 470, 294, 22);
			panel.add(passwordField);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			comboBox.setBackground(new Color(0, 0, 51));
			comboBox.setForeground(new Color(255, 255, 255));
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"<<Seleccione>>", "Masculino", "Femenino"}));
			comboBox.setBounds(43, 190, 167, 22);
			panel.add(comboBox);
			
			SpinnerDateModel dateModel = new SpinnerDateModel();
			JSpinner spinnerFecha = new JSpinner(dateModel);
			spinnerFecha.setBackground(new Color(0, 0, 51));
			spinnerFecha.setForeground(new Color(255, 255, 255));
			spinnerFecha.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
			JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
			spinnerFecha.setEditor(editor);
			spinnerFecha.setBounds(240, 190, 135, 22);
			panel.add(spinnerFecha);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
