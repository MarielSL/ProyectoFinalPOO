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
import visual.ComboBoxRedond;
import visual.PanelConSombra;
import visual.TextFieldConSombra;
import visual.Utilidades;

import java.awt.Window.Type;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class VerOfertasEmpresa extends JFrame {

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
					VerOfertasEmpresa frame = new VerOfertasEmpresa();
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
	public VerOfertasEmpresa() {
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
		panel_3.setBounds(56, 124, 1760, 66);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		txtBuscar = new TextFieldConSombra(20);
		txtBuscar.setFont(new Font("Calibri", Font.PLAIN, 22));
		txtBuscar.setBackground(new Color(255, 255, 255));
		txtBuscar.setForeground(new Color(204, 204, 204));
		txtBuscar.setText("Buscar");
		txtBuscar.setBounds(1473, 13, 214, 40);
		panel_3.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		cbxEstado = new ComboBoxRedond(25);
		cbxEstado.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxEstado.setForeground(new Color(0, 0, 51));
		cbxEstado.setEditable(true);
		cbxEstado.setBackground(new Color(255, 255, 255));
		cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"Todas", "Activas", "En pausa", "Cerradas"}));
		cbxEstado.setSelectedIndex(0);
		
		cbxEstado.setBounds(66, 20, 294, 22);
		panel_3.add(cbxEstado);
		cbxEstado.addActionListener(e -> filtrarPorEstado());
		
		PanelConSombra panel_1 = new PanelConSombra(25);
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(0, 0, 1892, 95);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mis Ofertas");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBounds(29, 32, 290, 35);
		panel_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 373, 1778, 606);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Puesto", "Modalidad", "Fecha de Publicaci\u00F3n", "Postulantes", "Jornada", "Estado"
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
		
		PanelConSombra panel_2 = new PanelConSombra(25);
		panel_2.setBackground(new Color(234, 241, 253));
		panel_2.setBounds(136, 216, 303, 121);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Total ofertas");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(95, 25, 185, 31);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblCant = new JLabel("Cant");
		lblCant.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblCant.setBounds(95, 69, 185, 31);
		panel_2.add(lblCant);
		
		JLabel lblNewLabel_2 = new JLabel("Icono ");
		lblNewLabel_2.setBounds(12, 25, 60, 60);
		colocarImagen(lblNewLabel_2,"/img/portapapeles-azul.png");
		panel_2.add(lblNewLabel_2);
		
		PanelConSombra panelConSombra = new PanelConSombra(25);
		panelConSombra.setBounds(1014, 216, 303, 121);
		panelConSombra.setBackground(new Color(255, 243, 220));
		panel.add(panelConSombra);
		panelConSombra.setLayout(null);
		
		JLabel lblEnRevisin = new JLabel("En pausa");
		lblEnRevisin.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblEnRevisin.setBounds(91, 25, 185, 31);
		panelConSombra.add(lblEnRevisin);
		
		JLabel label = new JLabel("Cant");
		label.setFont(new Font("Calibri", Font.PLAIN, 20));
		label.setBounds(91, 69, 185, 31);
		panelConSombra.add(label);
		
		JLabel label_3 = new JLabel("Icono ");
		label_3.setBounds(12, 25, 60, 60);
		colocarImagen(label_3,"/img/pausa_naranja.png");
		panelConSombra.add(label_3);
		
		PanelConSombra panelConSombra_1 = new PanelConSombra(25);
		panelConSombra_1.setBounds(575, 216, 303, 121);
		panelConSombra_1.setBackground(new Color(230, 247, 232));
		panel.add(panelConSombra_1);
		panelConSombra_1.setLayout(null);
		
		JLabel lblAceptadas = new JLabel("Activas");
		lblAceptadas.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblAceptadas.setBounds(95, 25, 185, 31);
		panelConSombra_1.add(lblAceptadas);
		
		JLabel label_1 = new JLabel("Cant");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		label_1.setBounds(95, 69, 185, 31);
		panelConSombra_1.add(label_1);
		
		JLabel lblIcono = new JLabel("icono");
		lblIcono.setBounds(16, 25, 60, 60);
		colocarImagen(lblIcono,"/img/check.png");
		panelConSombra_1.add(lblIcono);
		
		
		PanelConSombra panelConSombra_2 = new PanelConSombra(25);
		panelConSombra_2.setBounds(1453, 216, 303, 121);
		panelConSombra_2.setBackground(new Color(254, 239, 239));
		panel.add(panelConSombra_2);
		panelConSombra_2.setLayout(null);
		
		JLabel lblRechazadas = new JLabel("Cerradas");
		lblRechazadas.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblRechazadas.setBounds(106, 25, 185, 31);
		panelConSombra_2.add(lblRechazadas);
		
		JLabel label_2 = new JLabel("Cant");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		label_2.setBounds(106, 69, 185, 31);
		panelConSombra_2.add(label_2);
		
		JLabel label_5 = new JLabel("Icono ");
		label_5.setBounds(22, 25, 60, 60);
		colocarImagen(label_5,"/img/rechazada.png");
		panelConSombra_2.add(label_5);
		
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