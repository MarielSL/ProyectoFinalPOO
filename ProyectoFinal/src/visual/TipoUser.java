package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
		setLocationRelativeTo(null);
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
				
				BotonRedond btnEmpresa = new BotonRedond("Empresa", 25);
				colocarIconoBoton(btnEmpresa, "/img/icono_empresa.png", 25, 25);
				btnEmpresa.setVerticalTextPosition(SwingConstants.TOP);       
				btnEmpresa.setHorizontalTextPosition(SwingConstants.CENTER); 
				btnEmpresa.setIconTextGap(6);
				btnEmpresa.setFont(new Font("Calibri", Font.PLAIN, 18));
				btnEmpresa.setForeground(new Color(0, 0, 51));
				btnEmpresa.setBackground(new Color(255, 153, 0));
				btnEmpresa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegEmpresa regEmpresa = new RegEmpresa(null);
				        regEmpresa.setVisible(true);
				        dispose();
						
					}
				});
				btnEmpresa.setBounds(116, 136, 138, 71);
				panel_1.add(btnEmpresa);
				
				BotonRedond btnSolicitante = new BotonRedond("Solicitante", 25);
				colocarIconoBoton(btnSolicitante, "/img/icono_solicitante.png", 25, 25);
				btnSolicitante.setVerticalTextPosition(SwingConstants.TOP);       
				btnSolicitante.setHorizontalTextPosition(SwingConstants.CENTER); 
				btnSolicitante.setIconTextGap(6);
				btnSolicitante.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarSolicitante regSoliictante = new RegistrarSolicitante(null);
						regSoliictante.setVisible(true);
						dispose();
					}
				});
				btnSolicitante.setFont(new Font("Calibri", Font.PLAIN, 18));
				btnSolicitante.setForeground(new Color(0, 0, 51));
				btnSolicitante.setBackground(new Color(255, 153, 0));
				btnSolicitante.setBounds(308, 135, 138, 71);
				panel_1.add(btnSolicitante);
				
				
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
	
	private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
	    ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
	    Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
	    boton.setIcon(new ImageIcon(imagenEscalada));
	}
}
