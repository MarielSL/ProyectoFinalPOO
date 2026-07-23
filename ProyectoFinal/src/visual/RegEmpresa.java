package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.TipoEmpresa;
import logico.TipoUser;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class RegEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TextFieldRedond txtNombEmpresa;
	private TextFieldRedond txtTelefono;
	private TextFieldRedond txtDireccion;
	private TextFieldRedond txtRnc;
	private ComboBoxRedond<TipoEmpresa> cbxTipo;
	private TextFieldRedond txtCorreo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEmpresa dialog = new RegEmpresa(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEmpresa(Usuario user) {
		setTitle("Registrar Empresa");
		setBounds(100, 100, 734, 489);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		BotonRedond btnNewButton = new BotonRedond("Registrar",25);
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnNewButton.setBackground(new Color(255, 165, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtRnc.getText().trim().isEmpty() || txtNombEmpresa.getText().trim().isEmpty()  || txtTelefono.getText().trim().isEmpty() 
						|| txtDireccion.getText().trim().isEmpty()  || cbxTipo.getSelectedIndex()==-1 || txtCorreo.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(BolsaEmpleo.getInstancia().isEmpressRep(txtRnc.getText())) {
					JOptionPane.showMessageDialog(null, "ERROR!: esta empresa ha sido registrada", "Advertenicia", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Empresa empresa = new Empresa ("E-"+BolsaEmpleo.generadorIdEmpresa, txtRnc.getText(), txtNombEmpresa.getText(), txtTelefono.getText(),
						txtDireccion.getText(), (TipoEmpresa) cbxTipo.getSelectedItem(), user);
				BolsaEmpleo.getInstancia().regEmpresa(empresa);
				Usuario user = BolsaEmpleo.getInstancia().getLoginUser();
				user.setEmpresa(empresa);
				user.setCorreo(txtCorreo.getText());
				user.setTipoUser(TipoUser.EMPRESA);
				BolsaEmpleo.getInstancia().setLoginUser(user);
				JOptionPane.showMessageDialog(null, "Se ha registrado la empresa.", "Información", JOptionPane.INFORMATION_MESSAGE);
				clear();
			}


		});
		btnNewButton.setBounds(577, 398, 114, 31);
		contentPanel.add(btnNewButton);

		JPanel panel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				Graphics2D g2 = (Graphics2D) g.create();

				g2.setRenderingHint(
						RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON
						);

				g2.setColor(getBackground());

				g2.fillRoundRect(
						0,
						0,
						getWidth(),
						getHeight(),
						30,
						30
						);

				g2.dispose();
			}
		};
		panel.setOpaque(false);

		panel.setBackground(new Color(0, 0, 51));
		panel.setForeground(new Color(0, 0, 51));
		panel.setBounds(27, 20, 664, 37);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Registrate para publicar tus vacantes");
		lblNewLabel.setBounds(12, -13, 344, 63);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 153, 51));
		lblNewLabel.setFont(new Font("Book Antiqua", Font.BOLD, 20));

		JLabel lblNewLabel_1 = new JLabel("Nombre de la Empresa:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(27, 70, 214, 31);
		contentPanel.add(lblNewLabel_1);

		txtNombEmpresa = new TextFieldRedond(25);
		txtNombEmpresa.setForeground(new Color(0, 0, 51));
		txtNombEmpresa.setBackground(SystemColor.controlHighlight);
		txtNombEmpresa.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtNombEmpresa.setBounds(27, 106, 214, 26);
		contentPanel.add(txtNombEmpresa);
		txtNombEmpresa.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(27, 281, 92, 16);
		contentPanel.add(lblNewLabel_2);

		txtTelefono = new TextFieldRedond(25);
		txtTelefono.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTelefono.setForeground(new Color(0, 0, 51));
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setBounds(27, 310, 214, 26);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(27, 352, 85, 16);
		contentPanel.add(lblNewLabel_3);

		txtDireccion = new TextFieldRedond(25);
		txtDireccion.setForeground(new Color(0, 0, 51));
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtDireccion.setBounds(27, 377, 214, 26);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("RNC:");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(27, 206, 56, 16);
		contentPanel.add(lblNewLabel_4);

		txtRnc = new TextFieldRedond(25);
		txtRnc.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtRnc.setForeground(new Color(0, 0, 51));
		txtRnc.setBackground(SystemColor.controlHighlight);
		txtRnc.setBounds(27, 235, 214, 26);
		contentPanel.add(txtRnc);
		txtRnc.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(375, 75, 56, 16);
		contentPanel.add(lblNewLabel_5);

		cbxTipo = new ComboBoxRedond<TipoEmpresa>(25);
		cbxTipo.setForeground(new Color(0, 0, 51));
		cbxTipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxTipo.setBackground(SystemColor.controlHighlight);
		cbxTipo.setBounds(375, 106, 214, 26);
		contentPanel.add(cbxTipo);
		cbxTipo.setModel(new DefaultComboBoxModel<TipoEmpresa>(TipoEmpresa.values()));
		cbxTipo.setSelectedIndex(-1);

		cbxTipo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {

			@Override
			protected javax.swing.plaf.basic.ComboPopup createPopup() {

				javax.swing.plaf.basic.BasicComboPopup popup =
						new javax.swing.plaf.basic.BasicComboPopup(comboBox) {

					@Override
					protected javax.swing.JScrollPane createScroller() {

						javax.swing.JScrollPane scroll =
								new javax.swing.JScrollPane(
										list,
										javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
										);

						scroll.setBorder(
								javax.swing.BorderFactory.createLineBorder(
										new Color(0, 0, 51),
										1,
										true
										)
								);

						return scroll;
					}
				};

				popup.setBorder(
						javax.swing.BorderFactory.createLineBorder(
								new Color(0, 0, 51),
								1,
								true
								)
						);

				return popup;
			}
		});

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon(RegEmpresa.class.getResource("/img/Fondo-Registro-Completa.png")));
		lblNewLabel_6.setBounds(0, 0, 716, 442);
		contentPanel.add(lblNewLabel_6);
		colocarImagen(lblNewLabel_6,"/img/Fondo-Registro-Completa.png");
		
		JLabel lblNewLabel_7 = new JLabel("Correo:");
		lblNewLabel_7.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(27, 145, 56, 16);
		contentPanel.add(lblNewLabel_7);
		
		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setBounds(27, 169, 214, 26);
		contentPanel.add(txtCorreo);
		txtCorreo.setColumns(10);
	}
	
	private void clear() {
		txtRnc.setText("");
		txtNombEmpresa.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxTipo.setSelectedIndex(-1);
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
