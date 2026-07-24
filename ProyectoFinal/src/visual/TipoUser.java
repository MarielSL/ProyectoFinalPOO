package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TipoUser extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TipoUser dialog = new TipoUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TipoUser() {
		setTitle("Tipo de Usuario");
		setBounds(100, 100, 671, 424);
		getContentPane().setLayout(new BorderLayout());
		setIconImage(new ImageIcon(getClass().getResource("/img/Sample_User_Icon.png")).getImage());
		{
			JLayeredPane layeredPane = new JLayeredPane();
			getContentPane().add(layeredPane, BorderLayout.CENTER);
			layeredPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				layeredPane.add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				PanelRedond panel_1 = new PanelRedond(30);
				panel_1.setBackground(new Color(255, 255, 255));
				panel_1.setBounds(70, 54, 508, 239);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				PanelRedond panel_2 = new PanelRedond(30);
				panel_2.setBackground(new Color(0, 0, 51));
				panel_2.setBounds(74, 30, 388, 49);
				panel_1.add(panel_2);
				panel_2.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Registrar");
				lblNewLabel.setForeground(new Color(255, 153, 0));
				lblNewLabel.setBackground(new Color(0, 0, 51));
				lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
				lblNewLabel.setBounds(153, 13, 93, 29);
				panel_2.add(lblNewLabel);
				
				BotonRedond btnNewButton = new BotonRedond("Empresa", 25);
				btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
				btnNewButton.setForeground(new Color(0, 0, 51));
				btnNewButton.setBackground(new Color(255, 153, 0));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegEmpresa regEmpresa = new RegEmpresa(null);
				        regEmpresa.setVisible(true);
				        dispose();
						
					}
				});
				btnNewButton.setBounds(116, 136, 119, 49);
				panel_1.add(btnNewButton);
				
				BotonRedond btnNewButton_1 = new BotonRedond("Solicitante", 25);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarSolicitante regSoliictante = new RegistrarSolicitante(null);
						regSoliictante.setVisible(true);
						dispose();
					}
				});
				btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 18));
				btnNewButton_1.setForeground(new Color(0, 0, 51));
				btnNewButton_1.setBackground(new Color(255, 153, 0));
				btnNewButton_1.setBounds(308, 135, 119, 50);
				panel_1.add(btnNewButton_1);
				
				
				JLabel lblNewLabel_2 = new JLabel("New label");
				lblNewLabel_2.setIcon(new ImageIcon(LogIn.class.getResource("/img/Fondo-General.png")));
				lblNewLabel_2.setBounds(0, 0, 700, 480);
				panel.add(lblNewLabel_2);
				colocarImagen(lblNewLabel_2,"/img/Fondo-General.png");
			}
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
