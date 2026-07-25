package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import logico.EstadoSolicitud;
import java.awt.Window.Type;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class VerSolicitudesAplicadas extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private TableRowSorter <DefaultTableModel> sorter;
	private JTextField txtBuscar;
	private ComboBoxRedond cbxEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerSolicitudesAplicadas frame = new VerSolicitudesAplicadas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VerSolicitudesAplicadas() {
		setTitle("Mis Solicitudes");
		Utilidades.aplicarIcono(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		dim = getToolkit().getScreenSize(); 
		setSize(dim.width,dim.height-55); 
		setLocationRelativeTo(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		PanelConSombra panel_3 = new PanelConSombra(20);
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(406, 88, 1410, 66);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		txtBuscar = new TextFieldConSombra(20);
		txtBuscar.setFont(new Font("Calibri", Font.PLAIN, 22));
		txtBuscar.setBackground(new Color(255, 255, 255));
		txtBuscar.setForeground(new Color(204, 204, 204));
		txtBuscar.setText("Buscar");
		txtBuscar.setBounds(1119, 13, 214, 40);
		panel_3.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		cbxEstado = new ComboBoxRedond(25);
		cbxEstado.setFont(new Font("Calibri", Font.PLAIN, 20));
		cbxEstado.setForeground(new Color(0, 0, 51));
		cbxEstado.setEditable(true);
		cbxEstado.setBackground(new Color(255, 255, 255));
		cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Aceptadas", "Rechazadas", "Pendientes", "En Revisi\u00F3n"}));
		cbxEstado.setSelectedIndex(0);
		
		cbxEstado.setBounds(66, 20, 294, 22);
		panel_3.add(cbxEstado);
		cbxEstado.addActionListener(e -> filtrarPorEstado());
		
		PanelRedond panel_1 = new PanelRedond(25);
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(382, 13, 1452, 97);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mis Solicitudes");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(new Color(255, 153, 0));
		lblNewLabel.setBounds(33, 26, 290, 38);
		panel_1.add(lblNewLabel);
		
		PanelConSombra panel_2 = new PanelConSombra(20);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 13, 329, 983);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(12, 23, 280, 90);
		panel_2.add(lblNewLabel_1);
		colocarImagen(lblNewLabel_1, "/img/HireLink_logo_full.png");
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 147, 305, 19);
		panel_2.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 869, 305, 19);
		panel_2.add(separator_1);
		
		BotonConSombra btnNewButton = new BotonConSombra("Nombre del perfil y su correo", 20);
		btnNewButton.setForeground(new Color(0, 0, 51));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(96, 912, 209, 45);
		panel_2.add(btnNewButton);
		
		BotonConSombra btncnsmbrMenuPrincipal = new BotonConSombra("Nombre del perfil y su correo", 20);
		btncnsmbrMenuPrincipal.setFont(new Font("Calibri", Font.PLAIN, 20));
		btncnsmbrMenuPrincipal.setHorizontalAlignment(SwingConstants.LEFT);
		btncnsmbrMenuPrincipal.setText("Dashboard");
		btncnsmbrMenuPrincipal.setForeground(new Color(0, 0, 51));
		btncnsmbrMenuPrincipal.setBackground(Color.WHITE);
		btncnsmbrMenuPrincipal.setBounds(80, 161, 224, 45);
		panel_2.add(btncnsmbrMenuPrincipal);
		
		JLabel lblNewLabel_2 = new JLabel("Principal");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(12, 129, 98, 16);
		panel_2.add(lblNewLabel_2);
	
		
		
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(382, 222, 1452, 757);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Empresa", "Ubicaci\u00F3n", "Jornada", "Fecha de Aplicaci\u00F3n", "Estado"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.setFont(new Font("Calibri", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		sorter = new TableRowSorter<>(modeloTabla);
		table.setRowSorter(sorter);
		
		txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) { filtrar(); }
		    public void removeUpdate(DocumentEvent e) { filtrar(); }
		    public void changedUpdate(DocumentEvent e) { filtrar(); }
		});
		
		
	}
	
	private void filtrar() {
	    String texto = txtBuscar.getText().trim();
	    if (texto.isEmpty()) {
	        sorter.setRowFilter(null); 
	    } else {
	        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
	    }
	}
	
	private void filtrarPorEstado() {
	    String seleccionado = (String) cbxEstado.getSelectedItem();

	    if (seleccionado == null || seleccionado.equals("Todas")) {
	        sorter.setRowFilter(null);
	    } else {
	        sorter.setRowFilter(RowFilter.regexFilter("^" + Pattern.quote(seleccionado) + "$", 3));
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
