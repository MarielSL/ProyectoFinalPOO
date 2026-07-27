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
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class RegEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TextFieldRedond txtNombEmpresa;
	private TextFieldRedond txtTelefono;
	private TextFieldRedond txtDireccion;
	private TextFieldRedond txtRnc;
	private ComboBoxRedond<TipoEmpresa> cbxTipo;
	private TextFieldRedond txtCorreo;
	private TextFieldRedond txtUser;
	private JPasswordField passwordField;
	private FotoPerfilRedond fotoPerfil;
	private Empresa myEmpresa = null;
	private BotonRedond btnGuardar;

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
	public RegEmpresa(Empresa empresa ) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegEmpresa.class.getResource("/img/AppIconoFull.png")));
		myEmpresa = empresa;
		if(myEmpresa == null) {
			setTitle("Registrar Empresa");
		}
		else {
			setTitle("Modificar Datos");
		}
		setBounds(100, 100, 880, 561);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setOpaque(false);
		btnGuardar = new BotonRedond("Registrar",25);
		if(myEmpresa != null) {
			btnGuardar.setText("Modificar");
		}
		btnGuardar.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnGuardar.setBackground(new Color(255, 165, 0));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(myEmpresa == null) {
					if(txtRnc.getText().trim().isEmpty() || txtNombEmpresa.getText().trim().isEmpty()  || txtTelefono.getText().trim().isEmpty() 
							|| txtDireccion.getText().trim().isEmpty()  || cbxTipo.getSelectedIndex()==-1 || txtCorreo.getText().trim().isEmpty()
							|| passwordField.getText().trim().isEmpty() || txtUser.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(BolsaEmpleo.getInstancia().isEmpressRep(txtRnc.getText())) {
						JOptionPane.showMessageDialog(null, "ERROR!: esta empresa ha sido registrada", "Advertenicia", JOptionPane.WARNING_MESSAGE);
						return;
					}

					Empresa empresa = new Empresa ("E-"+BolsaEmpleo.generadorIdEmpresa, txtRnc.getText(), txtNombEmpresa.getText(), txtTelefono.getText(),
							txtDireccion.getText(), (TipoEmpresa) cbxTipo.getSelectedItem(),null);

					if(BolsaEmpleo.getInstancia().verifUsuario(txtUser.getText(), passwordField.getText())) {
						JOptionPane.showMessageDialog(null, "Usuario en uso.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						return;
					}

					Usuario nuevoUsuario = new Usuario("U-"+BolsaEmpleo.generadorIdUser, txtUser.getText(), passwordField.getText(), null, null, null, null, null);
					nuevoUsuario.setEmpresa(empresa);
					nuevoUsuario.setCorreo(txtCorreo.getText());
					nuevoUsuario.setTipoUser(TipoUser.EMPRESA);
					nuevoUsuario.setFotoPerfil(fotoPerfil.getRutaFotoPerfil());
					BolsaEmpleo.getInstancia().regUser(nuevoUsuario);
					BolsaEmpleo.getInstancia().setLoginUser(nuevoUsuario);
					BolsaEmpleo.getInstancia().regEmpresa(empresa);

					JOptionPane.showMessageDialog(null, "Se ha registrado la empresa.", "Informaci\u00F3n", JOptionPane.INFORMATION_MESSAGE);
					clear();
					HomeEmpresa emp = new HomeEmpresa();
					emp.setVisible(true);
					dispose();
				}
				else {
					if(txtRnc.getText().trim().isEmpty() || txtNombEmpresa.getText().trim().isEmpty()  || txtTelefono.getText().trim().isEmpty() 
							|| txtDireccion.getText().trim().isEmpty()  || cbxTipo.getSelectedIndex()==-1 || txtCorreo.getText().trim().isEmpty()
							|| passwordField.getText().trim().isEmpty() || txtUser.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Debe de completar todos los datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
						return;
					}
					myEmpresa.setNombre(txtNombEmpresa.getText());
					myEmpresa.setRnc(txtRnc.getText());
					myEmpresa.setDireccion(txtDireccion.getText());
					myEmpresa.setTelefono(txtTelefono.getText());
					myEmpresa.setTipo((TipoEmpresa) cbxTipo.getSelectedItem());
					Usuario modUser = myEmpresa.getUser();
					modUser.setCorreo(txtCorreo.getText());
					modUser.setFotoPerfil(fotoPerfil.getRutaFotoPerfil());
					modUser.setPassword(passwordField.getText());
					modUser.setUsername(txtUser.getText());
					myEmpresa.setUser(modUser);
					
					BolsaEmpleo.getInstancia().modEmpresa(myEmpresa);
					VerUserEmpresa verUser = new VerUserEmpresa();
					verUser.setVisible(true);
					dispose();
					
				}
			}
		});

		btnGuardar.setBounds(736, 470, 114, 31);
		contentPanel.add(btnGuardar);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 51));
		panel.setForeground(new Color(0, 0, 51));
		panel.setBounds(0, 0, 860, 65);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Registrate para publicar tus vacantes");
		lblNewLabel.setBounds(20, 16, 600, 35);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 153, 51));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));

		JLabel lblNewLabel_1 = new JLabel("Nombre de la Empresa:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(27, 82, 214, 31);
		contentPanel.add(lblNewLabel_1);

		txtNombEmpresa = new TextFieldRedond(25);
		txtNombEmpresa.setForeground(new Color(0, 0, 51));
		txtNombEmpresa.setBackground(SystemColor.controlHighlight);
		txtNombEmpresa.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtNombEmpresa.setBounds(26, 110, 214, 26);
		contentPanel.add(txtNombEmpresa);
		txtNombEmpresa.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(27, 292, 92, 16);
		contentPanel.add(lblNewLabel_2);

		txtTelefono = new TextFieldRedond(25);
		txtTelefono.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtTelefono.setForeground(new Color(0, 0, 51));
		txtTelefono.setBackground(SystemColor.controlHighlight);
		txtTelefono.setBounds(26, 314, 214, 26);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(27, 362, 85, 16);
		contentPanel.add(lblNewLabel_3);

		txtDireccion = new TextFieldRedond(25);
		txtDireccion.setForeground(new Color(0, 0, 51));
		txtDireccion.setBackground(SystemColor.controlHighlight);
		txtDireccion.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtDireccion.setBounds(26, 383, 214, 26);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("RNC:");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(27, 217, 56, 16);
		contentPanel.add(lblNewLabel_4);

		txtRnc = new TextFieldRedond(25);
		txtRnc.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtRnc.setForeground(new Color(0, 0, 51));
		txtRnc.setBackground(SystemColor.controlHighlight);
		txtRnc.setBounds(27, 238, 214, 26);
		contentPanel.add(txtRnc);
		txtRnc.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Tipo:");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(378, 87, 56, 16);
		contentPanel.add(lblNewLabel_5);

		cbxTipo = new ComboBoxRedond<TipoEmpresa>(25);
		cbxTipo.setForeground(new Color(0, 0, 51));
		cbxTipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		cbxTipo.setBackground(SystemColor.controlHighlight);
		cbxTipo.setBounds(375, 110, 214, 26);
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

		JLabel lblNewLabel_7 = new JLabel("Correo:");
		lblNewLabel_7.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(27, 154, 56, 16);
		contentPanel.add(lblNewLabel_7);

		txtCorreo = new TextFieldRedond(25);
		txtCorreo.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtCorreo.setBackground(SystemColor.controlHighlight);
		txtCorreo.setForeground(new Color(0, 0, 51));
		txtCorreo.setBounds(26, 174, 214, 26);
		contentPanel.add(txtCorreo);
		txtCorreo.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblUsuario.setBounds(378, 154, 134, 16);
		contentPanel.add(lblUsuario);

		txtUser = new TextFieldRedond(25);
		txtUser.setForeground(new Color(0, 0, 51));
		txtUser.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtUser.setColumns(10);
		txtUser.setBackground(SystemColor.controlHighlight);
		txtUser.setBounds(375, 174, 214, 26);
		contentPanel.add(txtUser);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblContrasea.setBounds(378, 217, 134, 16);
		contentPanel.add(lblContrasea);

		passwordField = new PasswordFieldRedond(25);
		passwordField.setBackground(SystemColor.controlHighlight);
		passwordField.setBounds(375, 238, 214, 26);
		contentPanel.add(passwordField);


		fotoPerfil = new FotoPerfilRedond(120);
		fotoPerfil.setBounds(655, 78, 142, 186); 
		contentPanel.add(fotoPerfil);
		

		JLabel lblFondo = new JLabel();
		lblFondo.setBounds(0, 0, 862, 514); 
		colocarImagen(lblFondo, "/img/Fondo-Registro-Completa.png");
		contentPanel.add(lblFondo);

		loadEmpresa();


	}

	private void loadEmpresa() {
		if(myEmpresa!= null) {
			txtRnc.setText(myEmpresa.getRnc());
			txtCorreo.setText(myEmpresa.getUser().getCorreo());
			txtNombEmpresa.setText(myEmpresa.getNombre());
			txtTelefono.setText(myEmpresa.getTelefono());
			txtDireccion.setText(myEmpresa.getDireccion());
			cbxTipo.setSelectedItem(myEmpresa.getTipo());
			txtUser.setText(myEmpresa.getUser().getUsername());
			passwordField.setText(myEmpresa.getUser().getPassword());
		}
		
	}

	private void clear() {
		txtRnc.setText("");
		txtCorreo.setText("");
		txtNombEmpresa.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxTipo.setSelectedIndex(-1);
		txtUser.setText("");
		passwordField.setText("");
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
