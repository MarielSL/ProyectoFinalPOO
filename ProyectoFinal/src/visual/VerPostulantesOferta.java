package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logico.BolsaEmpleo;
import logico.DecisionCandidato;
import logico.EstadoDecision;
import logico.EstadoSolicitud;
import logico.Obrero;
import logico.Oferta;
import logico.Persona;
import logico.ResultMatch;
import logico.Sexo;
import logico.SolicitudEmpleo;
import logico.Tecnico;
import logico.Universitario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerPostulantesOferta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim;
	private JPanel panel_Info;
	private JLabel lblBriefCaseIcon;
	private JLabel lblEmpresaIcon;
	private JLabel lblEmpresa;
	private JLabel lblOferta;
	private JLabel lblPostulantes;
	private JPanel panel_Top2;
	private JPanel panel_Top3;
	private TextFieldRedond txtTopTwo;
	private JLabel lblTop2Foto;
	private JLabel lblTopTwoName;
	private TextFieldRedond txtTopTwoTipo;
	private JLabel lblTopTwoCoincidencia;
	private BotonRedond btnTopTwoVerPerfil;
	private JLabel lblFotoTopOne;
	private TextFieldRedond txtTopOneTipo;
	private JLabel lblTopOneName;
	private JLabel lblTopOneCoinci;
	private BotonRedond btnTopOneVerPerfil;
	private JLabel lblTop3Foto;
	private TextFieldRedond txtTop3Tipo;
	private JLabel lblTop3Name;
	private JPanel panel_OtrosPostu;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] row;
	private ArrayList<ResultMatch> candidatosMostrados = new ArrayList<>();
	private ArrayList<ResultMatch> resultSolicitantes =  new ArrayList<>();
	private JButton btnVerAplicantes;
	private BotonRedond btnVerPerfilTop3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VerPostulantesOferta dialog = new VerPostulantesOferta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VerPostulantesOferta(Oferta oferta) {
		setResizable(false);
		if(oferta!= null) {
			resultSolicitantes = BolsaEmpleo.getInstancia().calcularMatch(oferta);
			
			Persona topUno = resultSolicitantes.get(0).getSolicitud().getCandidato();
			Persona topDos = resultSolicitantes.get(1).getSolicitud().getCandidato();
			Persona topTres = resultSolicitantes.get(2).getSolicitud().getCandidato();
		}
		
		setTitle("Postulantes de la Oferta");
		Utilidades.aplicarIcono(this);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height - 55);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(":");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 35));
		lblNewLabel.setBounds(30, 13, 50, 50);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ranking de Candidatos");
		lblNewLabel_1.setForeground(new Color(255, 153, 0));
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 40));
		lblNewLabel_1.setBounds(751, 15, 400, 49);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(30, 89, 1840, 889);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			panel_Info = new PanelRedond(30);
			panel_Info.setBackground(SystemColor.control);
			panel_Info.setBounds(22, 13, 966, 76);
			panel.add(panel_Info);
			panel_Info.setLayout(null);
			
			lblBriefCaseIcon = new JLabel("New label");
			lblBriefCaseIcon.setIcon(new ImageIcon(VerPostulantesOferta.class.getResource("/img/briefcase.png")));
			lblBriefCaseIcon.setBounds(12, 10, 56, 56);
			panel_Info.add(lblBriefCaseIcon);
			colocarImagen(lblBriefCaseIcon,"/img/briefcase.png");
			
			JLabel lblNewLabel_2 = new JLabel("Oferta");
			lblNewLabel_2.setForeground(SystemColor.controlDkShadow);
			lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblNewLabel_2.setBounds(93, 10, 49, 20);
			panel_Info.add(lblNewLabel_2);
			
			lblOferta = new JLabel("Desarrollo de Software");
			lblOferta.setForeground(new Color(0, 0, 51));
			lblOferta.setFont(new Font("Calibri", Font.BOLD, 22));
			lblOferta.setBounds(93, 30, 249, 32);
			panel_Info.add(lblOferta);
			
			JSeparator separator = new JSeparator();
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setForeground(SystemColor.controlShadow);
			separator.setBackground(SystemColor.controlShadow);
			separator.setBounds(370, 10, 1, 56);
			panel_Info.add(separator);
			
			JLabel lblSubTitle2 = new JLabel("Empresa");
			lblSubTitle2.setForeground(SystemColor.controlDkShadow);
			lblSubTitle2.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblSubTitle2.setBounds(471, 10, 69, 20);
			panel_Info.add(lblSubTitle2);
			
			lblEmpresa = new JLabel("New Label");
			lblEmpresa.setForeground(new Color(0, 0, 51));
			lblEmpresa.setFont(new Font("Calibri", Font.BOLD, 22));
			lblEmpresa.setBounds(471, 30, 249, 32);
			panel_Info.add(lblEmpresa);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setOrientation(SwingConstants.VERTICAL);
			separator_1.setForeground(SystemColor.controlShadow);
			separator_1.setBackground(SystemColor.controlShadow);
			separator_1.setBounds(747, 10, 1, 56);
			panel_Info.add(separator_1);
			
			lblEmpresaIcon = new JLabel("");
			lblEmpresaIcon.setIcon(new ImageIcon(VerPostulantesOferta.class.getResource("/img/building.png")));
			lblEmpresaIcon.setBounds(390, 10, 56, 56);
			panel_Info.add(lblEmpresaIcon);
			lblEmpresaIcon.setVerticalAlignment(SwingConstants.CENTER);
			lblEmpresaIcon.setHorizontalAlignment(SwingConstants.CENTER);
			colocarImagen(lblEmpresaIcon,"/img/building.png");
			
			JLabel label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon(VerPostulantesOferta.class.getResource("/img/users.png")));
			label_1.setVerticalAlignment(SwingConstants.CENTER);
			label_1.setHorizontalAlignment(SwingConstants.CENTER);
			label_1.setBounds(767, 10, 56, 56);
			panel_Info.add(label_1);
			colocarImagen(label_1, "/img/users.png");
			
			JLabel lblSubTitle3 = new JLabel("Candidatos");
			lblSubTitle3.setForeground(SystemColor.controlDkShadow);
			lblSubTitle3.setFont(new Font("Calibri", Font.PLAIN, 18));
			lblSubTitle3.setBounds(848, 11, 94, 20);
			panel_Info.add(lblSubTitle3);
			
			lblPostulantes = new JLabel("new Label");
			lblPostulantes.setForeground(new Color(0, 0, 51));
			lblPostulantes.setFont(new Font("Calibri", Font.BOLD, 22));
			lblPostulantes.setBounds(848, 30, 94, 32);
			panel_Info.add(lblPostulantes);
			
			JLabel lblTrophyIcon = new JLabel("New label");
			lblTrophyIcon.setIcon(new ImageIcon(VerPostulantesOferta.class.getResource("/img/trophy.png")));
			lblTrophyIcon.setBounds(22, 117, 70, 70);
			panel.add(lblTrophyIcon);
			colocarImagen(lblTrophyIcon,"/img/trophy.png");
			
			JLabel lblTopPostulantes = new JLabel("Top 3 Candidatos");
			lblTopPostulantes.setForeground(new Color(0, 0, 51));
			lblTopPostulantes.setFont(new Font("Calibri", Font.BOLD, 30));
			lblTopPostulantes.setBounds(119, 135, 234, 32);
			panel.add(lblTopPostulantes);
			
			PanelRedond panel_Top2 = new PanelRedond(30);
			panel_Top2.setBounds(249, 191, 350, 350);
			panel.add(panel_Top2);
			panel_Top2.setLayout(null);
			panel_Top2.setColorBorde(new Color(65, 95, 170));
			panel_Top2.setBackground(Color.decode("#f7faff"));
			panel_Top2.setGrosorBorde(1);
			
			JLabel lblNewLabel_3 = new JLabel("2\r\n");
			lblNewLabel_3.setLabelFor(txtTopTwo);
			lblNewLabel_3.setForeground(new Color(153, 204, 255));
			lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 35));
			lblNewLabel_3.setBounds(35, 32, 18, 36);
			panel_Top2.add(lblNewLabel_3);
			
			
			lblTop2Foto = new JLabel("New label");
			lblTop2Foto.setIcon(new ImageIcon(VerPostulantesOferta.class.getResource("/img/User Icon.png")));
			lblTop2Foto.setBounds(125, 25, 100, 100);
			panel_Top2.add(lblTop2Foto);
			
			txtTopTwo = new TextFieldRedond(120);
			txtTopTwo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTopTwo.setEditable(false);
			txtTopTwo.setFont(new Font("Calibri", Font.BOLD, 35));
			txtTopTwo.setBounds(22, 25, 45, 45);
			panel_Top2.add(txtTopTwo);
			txtTopTwo.setColumns(10);
			txtTopTwo.setForeground(new Color(195, 220, 255));
			txtTopTwo.setBackground(new Color(65, 95, 170));
			txtTopTwo.setFocusable(false);
			
			lblTopTwoName = new JLabel("New label");
			lblTopTwoName.setForeground(new Color(0, 0, 51));
			lblTopTwoName.setHorizontalAlignment(SwingConstants.CENTER);
			lblTopTwoName.setFont(new Font("Calibri", Font.BOLD, 20));
			lblTopTwoName.setBounds(79, 138, 192, 20);
			panel_Top2.add(lblTopTwoName);
			
			txtTopTwoTipo = new TextFieldRedond(30);
			txtTopTwoTipo.setEditable(false);
			txtTopTwoTipo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTopTwoTipo.setFont(new Font("Calibri", Font.PLAIN, 20));
			txtTopTwoTipo.setBounds(109, 170, 132, 26);
			panel_Top2.add(txtTopTwoTipo);
			txtTopTwoTipo.setColumns(10);
			txtTopTwoTipo.setForeground(new Color(65, 95, 170));
			txtTopTwoTipo.setBackground(new Color(195, 220, 255));
			txtTopTwoTipo.setFocusable(false);
			
			JLabel lbl1 = new JLabel("Coincidencia");
			lbl1.setForeground(new Color(0, 0, 51));
			lbl1.setFont(new Font("Calibri", Font.PLAIN, 18));
			lbl1.setBounds(129, 210, 93, 20);
			panel_Top2.add(lbl1);
			
			lblTopTwoCoincidencia = new JLabel("89%");
			lblTopTwoCoincidencia.setForeground(new Color(65, 95, 170));
			lblTopTwoCoincidencia.setHorizontalAlignment(SwingConstants.CENTER);
			lblTopTwoCoincidencia.setFont(new Font("Calibri", Font.BOLD, 35));
			lblTopTwoCoincidencia.setBounds(129, 245, 93, 30);
			panel_Top2.add(lblTopTwoCoincidencia);
			
			BotonRedond btnTopTwoVerPerfil = new BotonRedond("Ver Perfil",30);
			btnTopTwoVerPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(oferta !=null) {
						String IdSolicitud = BolsaEmpleo.getInstancia().idSolicitud(resultSolicitantes.get(1).getSolicitud().getCandidato());
						SolicitudEmpleo solicitud = resultSolicitantes.get(1).getSolicitud().getCandidato().getSolicitud();
						VerPostulante verPostulante = new VerPostulante(resultSolicitantes.get(1).getSolicitud().getCandidato(),oferta,solicitud);
						verPostulante.setVisible(true);
					}
					else {
						VerPostulante verPostulante = new VerPostulante(null,null,null);
						verPostulante.setVisible(true);
					}
					
				}
			});
			btnTopTwoVerPerfil.setBackground(Color.decode("#e3ebfe"));
			btnTopTwoVerPerfil.setForeground(new Color(51, 102, 204));
			btnTopTwoVerPerfil.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnTopTwoVerPerfil.setBounds(99, 290, 152, 35);
			btnTopTwoVerPerfil.setColorHover(Color.decode("#d9e4ff"));
			panel_Top2.add(btnTopTwoVerPerfil);
			
			PanelRedond panel_Top3 = new PanelRedond(30);
			panel_Top3.setBounds(1206, 191, 350, 350);
			panel.add(panel_Top3);
			panel_Top3.setLayout(null);
			panel_Top3.setColorBorde(Color.decode("#ff5757"));
			panel_Top3.setGrosorBorde(1);
			panel_Top3.setBackground(Color.decode("#fff8f8"));
			
			JLabel label_7 = new JLabel("3");
			label_7.setForeground(Color.decode("#fde7e7"));
			label_7.setFont(new Font("Calibri", Font.BOLD, 35));
			label_7.setBounds(35, 32, 18, 36);
			panel_Top3.add(label_7);
			
			lblTop3Foto = new JLabel("New label");
			lblTop3Foto.setBounds(125, 25, 100, 100);
			panel_Top3.add(lblTop3Foto);
			
			lblTop3Name = new JLabel("New label");
			lblTop3Name.setHorizontalAlignment(SwingConstants.CENTER);
			lblTop3Name.setForeground(new Color(0, 0, 51));
			lblTop3Name.setFont(new Font("Calibri", Font.BOLD, 20));
			lblTop3Name.setBounds(79, 136, 192, 20);
			panel_Top3.add(lblTop3Name);
			
			txtTop3Tipo = new TextFieldRedond(30);
			txtTop3Tipo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTop3Tipo.setForeground(Color.decode("#ff5757"));
			txtTop3Tipo.setFont(new Font("Calibri", Font.PLAIN, 20));
			txtTop3Tipo.setFocusable(false);
			txtTop3Tipo.setEditable(false);
			txtTop3Tipo.setColumns(10);
			txtTop3Tipo.setBackground(Color.decode("#fde7e7"));
			txtTop3Tipo.setBounds(109, 168, 132, 26);
			panel_Top3.add(txtTop3Tipo);
			
			JLabel label_5 = new JLabel("Coincidencia");
			label_5.setForeground(new Color(0, 0, 51));
			label_5.setFont(new Font("Calibri", Font.PLAIN, 18));
			label_5.setBounds(129, 208, 93, 20);
			panel_Top3.add(label_5);
			
			JLabel label_6 = new JLabel("80%");
			label_6.setHorizontalAlignment(SwingConstants.CENTER);
			label_6.setForeground(Color.decode("#ff5757"));
			label_6.setFont(new Font("Calibri", Font.BOLD, 35));
			label_6.setBounds(129, 243, 93, 30);
			panel_Top3.add(label_6);
			
			BotonRedond btnVerPerfilTop3 = new BotonRedond("Ver Perfil", 30);
			btnVerPerfilTop3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(oferta !=null) {
						String IdSolicitud = BolsaEmpleo.getInstancia().idSolicitud(resultSolicitantes.get(2).getSolicitud().getCandidato());
						SolicitudEmpleo solicitud = resultSolicitantes.get(2).getSolicitud().getCandidato().getSolicitud();
						VerPostulante verPostulante = new VerPostulante(resultSolicitantes.get(2).getSolicitud().getCandidato(),oferta,solicitud);
						verPostulante.setVisible(true);
					}
					else {
						VerPostulante verPostulante = new VerPostulante(null,null,null);
						verPostulante.setVisible(true);
					}
				}
			});
			btnVerPerfilTop3.setForeground(Color.decode("#ff5757"));
			btnVerPerfilTop3.setFont(new Font("Calibri", Font.PLAIN, 20));
			btnVerPerfilTop3.setBackground(Color.decode("#fde7e7"));
			btnVerPerfilTop3.setColorHover(Color.decode("#fbdada"));
			btnVerPerfilTop3.setBounds(99, 288, 152, 35);
			panel_Top3.add(btnVerPerfilTop3);
			
			TextFieldRedond txtTop3 = new TextFieldRedond(120);
			txtTop3.setHorizontalAlignment(SwingConstants.CENTER);
			txtTop3.setForeground(new Color(195, 220, 255));
			txtTop3.setFont(new Font("Calibri", Font.BOLD, 35));
			txtTop3.setFocusable(false);
			txtTop3.setEditable(false);
			txtTop3.setColumns(10);
			txtTop3.setBackground(Color.decode("#ff5757"));
			txtTop3.setBounds(22, 25, 45, 45);
			panel_Top3.add(txtTop3);
			
			PanelRedond panel_Top1 = new PanelRedond(30);
			panel_Top1.setBounds(704, 164, 400, 400);
			panel.add(panel_Top1);
			panel_Top1.setLayout(null);
			panel_Top1.setBackground(Color.decode("#fffbf5"));
			panel_Top1.setColorBorde(Color.decode("#fe9703"));
			panel_Top1.setGrosorBorde(1);
			
			JLabel label = new JLabel("1");
			label.setForeground(Color.decode("#ffecd0"));
			label.setFont(new Font("Calibri", Font.BOLD, 35));
			label.setBounds(38, 36, 18, 36);
			panel_Top1.add(label);
			
			TextFieldRedond txtTopOne = new TextFieldRedond(120);
			txtTopOne.setHorizontalAlignment(SwingConstants.CENTER);
			txtTopOne.setForeground(new Color(195, 220, 255));
			txtTopOne.setFont(new Font("Calibri", Font.BOLD, 35));
			txtTopOne.setFocusable(false);
			txtTopOne.setEditable(false);
			txtTopOne.setColumns(10);
			txtTopOne.setBackground(Color.decode("#fe9703"));
			txtTopOne.setBounds(25, 30, 45, 45);
			panel_Top1.add(txtTopOne);
			
			lblFotoTopOne = new JLabel("New label");
			lblFotoTopOne.setIcon(new ImageIcon(VerPostulantesOferta.class.getResource("/img/User Icon.png")));
			lblFotoTopOne.setBounds(137, 36, 125, 125);
			panel_Top1.add(lblFotoTopOne);
			
			txtTopOneTipo = new TextFieldRedond(30);
			txtTopOneTipo.setHorizontalAlignment(SwingConstants.CENTER);
			txtTopOneTipo.setForeground(Color.decode("#fe9703"));
			txtTopOneTipo.setFont(new Font("Calibri", Font.PLAIN, 23));
			txtTopOneTipo.setFocusable(false);
			txtTopOneTipo.setEditable(false);
			txtTopOneTipo.setColumns(10);
			txtTopOneTipo.setBackground(Color.decode("#ffecd0"));
			txtTopOneTipo.setBounds(125, 207, 148, 28);
			panel_Top1.add(txtTopOneTipo);
			
			lblTopOneName = new JLabel("New label");
			lblTopOneName.setHorizontalAlignment(SwingConstants.CENTER);
			lblTopOneName.setForeground(new Color(0, 0, 51));
			lblTopOneName.setFont(new Font("Calibri", Font.BOLD, 20));
			lblTopOneName.setBounds(103, 175, 192, 20);
			panel_Top1.add(lblTopOneName);
			
			JLabel label_2 = new JLabel("Coincidencia");
			label_2.setForeground(new Color(0, 0, 51));
			label_2.setFont(new Font("Calibri", Font.PLAIN, 21));
			label_2.setBounds(144, 253, 110, 20);
			panel_Top1.add(label_2);
			
			lblTopOneCoinci = new JLabel("100%");
			lblTopOneCoinci.setHorizontalAlignment(SwingConstants.CENTER);
			lblTopOneCoinci.setForeground(Color.decode("#fe9703"));
			lblTopOneCoinci.setFont(new Font("Calibri", Font.BOLD, 38));
			lblTopOneCoinci.setBounds(153, 288, 93, 32);
			panel_Top1.add(lblTopOneCoinci);
			
			BotonRedond btnTopOneVerPerfil = new BotonRedond("Ver Perfil", 30);
			btnTopOneVerPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(oferta !=null) {
						String IdSolicitud = BolsaEmpleo.getInstancia().idSolicitud(resultSolicitantes.get(0).getSolicitud().getCandidato());
						SolicitudEmpleo solicitud = resultSolicitantes.get(0).getSolicitud().getCandidato().getSolicitud();
						VerPostulante verPostulante = new VerPostulante(resultSolicitantes.get(0).getSolicitud().getCandidato(),oferta,solicitud);
						verPostulante.setVisible(true);
					}
					else {
						VerPostulante verPostulante = new VerPostulante(null,null,null);
						verPostulante.setVisible(true);
					}
				}
			});
			btnTopOneVerPerfil.setForeground(Color.decode("#fe9703"));
			btnTopOneVerPerfil.setFont(new Font("Calibri", Font.PLAIN, 23));
			btnTopOneVerPerfil.setBackground(Color.decode("#ffecd0"));
			btnTopOneVerPerfil.setColorHover(Color.decode("#fee4c0"));
			btnTopOneVerPerfil.setBounds(113, 336, 172, 38);
			panel_Top1.add(btnTopOneVerPerfil);
			
			PanelRedond panel_OtrosPostu = new PanelRedond(30);
			panel_OtrosPostu.setBackground(new Color(255, 255, 255));
			panel_OtrosPostu.setBounds(42, 591, 1367, 285);
			panel.add(panel_OtrosPostu);
			panel_OtrosPostu.setColorBorde(Color.LIGHT_GRAY);
			panel_OtrosPostu.setGrosorBorde(1);
			panel_OtrosPostu.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("Resto de Candidatos");
			lblNewLabel_4.setForeground(new Color(0, 0, 51));
			lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 22));
			lblNewLabel_4.setBounds(34, 13, 212, 22);
			panel_OtrosPostu.add(lblNewLabel_4);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setForeground(SystemColor.scrollbar);
			separator_2.setBounds(18, 40, 1315, 2);
			panel_OtrosPostu.add(separator_2);
			
			JPanel panel_Tabla = new JPanel();
			panel_Tabla.setBackground(SystemColor.text);
			panel_Tabla.setBounds(34, 62, 1297, 210);
			panel_OtrosPostu.add(panel_Tabla);
			panel_Tabla.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel_Tabla.add(scrollPane, BorderLayout.CENTER);
			String[] headers = {"Nombre", "Cat. Laboral","Coincidencia", "Estado"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(headers);
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					loadRestoPostulantes(oferta);
					int index = table.getSelectedRow();
					if(index>=0) {
						if(oferta == null) {
							VerPostulante verPostulante = new VerPostulante(null,null,null);
							setVisible(true);
						}
						else {
							String IdSolicitud = BolsaEmpleo.getInstancia().idSolicitud(candidatosMostrados.get(index).getSolicitud().getCandidato());
							SolicitudEmpleo solicitud = candidatosMostrados.get(index).getSolicitud().getCandidato().getSolicitud();
							VerPostulante verPostulante = new VerPostulante(candidatosMostrados.get(index).getSolicitud().getCandidato(),oferta,solicitud);
							setVisible(true);
						}
					}
					
				}
			});
			table.setModel(model);
			table.setFont(new Font("Calibri", Font.PLAIN, 16));
			scrollPane.setViewportView(table);
			table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
			table.setForeground(new Color(0, 0, 51));
			
			BotonRedond btnVerAplicantes = new BotonRedond("Ver Todos los Aplicantes",30);
			btnVerAplicantes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VerTodosPostulantesOferta todos = new VerTodosPostulantesOferta(oferta);
					todos.setVisible(true);
					dispose();
				}
			});
			btnVerAplicantes.setFont(new Font("Calibri", Font.BOLD, 20));
			btnVerAplicantes.setBounds(1482, 716, 272, 42);
			panel.add(btnVerAplicantes);
			btnVerAplicantes.setBackground(Color.decode("#ffecd0"));
			btnVerAplicantes.setForeground(Color.decode("#fe9703"));
			btnVerAplicantes.setColorHover(Color.decode("#fee4c0"));
			table.getTableHeader().setForeground(new Color(0, 0, 51));
		}
		{
			PanelRedond panel = new PanelRedond(25);
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(30, 75, 1840, 25);
			contentPanel.add(panel);
			panel.setLayout(null);
		}
		if(oferta!=null) {
			int cantSolicitantes = BolsaEmpleo.getInstancia().getSolicitudes().size();
			
			lblEmpresa.setText(oferta.getEmpresa().getNombre());
			lblOferta.setText(oferta.getPuesto());
			lblPostulantes.setText(resultSolicitantes.size()+"");
			
			Persona topUno = resultSolicitantes.get(0).getSolicitud().getCandidato();
			Persona topDos = resultSolicitantes.get(1).getSolicitud().getCandidato();
			Persona topTres = resultSolicitantes.get(2).getSolicitud().getCandidato();
			
			lblTopOneName.setText(topUno.getNombre() + " " + topUno.getApellido());
			if(topUno instanceof Universitario) {
				if(topUno.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Universitaria");
				}
				else {
					txtTopOneTipo.setText("Universitario");
				}
			}
			if(topUno instanceof Tecnico) {
				if(topUno.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Técnica");
				}
				else {
					txtTopOneTipo.setText("Técnico");
				}
			}
			if(topUno instanceof Obrero) {
				if(topUno.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Obrera");
				}
				else {
					txtTopOneTipo.setText("Obrero");
				}
			}
			//colocarImagen(lblFotoTopOne,topUno.getUser().getFotoPerfil());
			colocarImagen(lblFotoTopOne,"/img/User Icon.png");
			
			lblTopTwoName.setText(topDos.getNombre() + " " + topDos.getApellido());
			if(topDos instanceof Universitario) {
				if(topDos.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Universitaria");
				}
				else {
					txtTopOneTipo.setText("Universitario");
				}
			}
			if(topDos instanceof Tecnico) {
				if(topDos.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Técnica");
				}
				else {
					txtTopOneTipo.setText("Técnico");
				}
			}
			if(topDos instanceof Obrero) {
				if(topDos.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Obrera");
				}
				else {
					txtTopOneTipo.setText("Obrero");
				}
			}
			//colocarImagen(lblTop2Foto,topDos.getUser().getFotoPerfil());
			colocarImagen(lblTop2Foto,"/img/User Icon.png");
			
			lblTop3Name.setText(topTres.getNombre() + " " + topTres.getApellido());
			if(topTres instanceof Universitario) {
				if(topTres.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Universitaria");
				}
				else {
					txtTopOneTipo.setText("Universitario");
				}
			}
			if(topTres instanceof Tecnico) {
				if(topTres.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Técnica");
				}
				else {
					txtTopOneTipo.setText("Técnico");
				}
			}
			if(topTres instanceof Obrero) {
				if(topTres.getSexo() == Sexo.FEMENINO) {
					txtTopOneTipo.setText("Obrera");
				}
				else {
					txtTopOneTipo.setText("Obrero");
				}
			}
			//colocarImagen(lblTop3Foto,topTres.getUser().getFotoPerfil());
			colocarImagen(lblTop3Foto,"/img/User Icon.png");

			
		}
		else {
			lblEmpresa.setText("Apple");
			lblOferta.setText("Desarrollo de Software");
			lblPostulantes.setText("7");
			colocarImagen(lblTop2Foto,"/img/User Icon.png");
			
			lblTopTwoName.setText("Leslie Aracena");
			txtTopTwoTipo.setText("Obrera");
			colocarImagen(lblTop2Foto,"/img/User Icon.png");
			
			txtTopOneTipo.setText("Universitaria");
			lblTopOneName.setText("Mariel Sánchez");
			colocarImagen(lblFotoTopOne,"/img/User Icon.png");

			colocarImagen(lblTop3Foto,"/img/User Icon.png");
			txtTop3Tipo.setText("Técnica");
			lblTop3Name.setText("Avril Acosta");
			
			
			
		}
		loadRestoPostulantes(oferta);
	}
	private void loadRestoPostulantes(Oferta oferta) {
		if(oferta == null) {
			return;
		}
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		for (ResultMatch match : resultSolicitantes) {
			if((match.getSolicitud().getCandidato() != resultSolicitantes.get(0).getSolicitud().getCandidato()) && (match.getSolicitud().getCandidato() != resultSolicitantes.get(1).getSolicitud().getCandidato()) && (match.getSolicitud().getCandidato() != resultSolicitantes.get(2).getSolicitud().getCandidato()) ) {
				Persona aux = match.getSolicitud().getCandidato();
				candidatosMostrados.add(match);
				row[0] = aux.getNombre() + " " + aux.getApellido();
				if(aux instanceof Universitario) {
					if(aux.getSexo() == Sexo.FEMENINO) {
						row[1] = "Universitaria";

					}
					else {
						row[1] = "Universitario";
					}
				}
				if(aux instanceof Tecnico) {
					if(aux.getSexo() == Sexo.FEMENINO) {
						row[1] = "Técnica";

					}
					else {
						row[1] = "Técnico";
					}
				}
				if(aux instanceof Obrero) {
					if(aux.getSexo() == Sexo.FEMENINO) {
						row[1] = "Obrera";

					}
					else {
						row[1] = "Obrero";
					}
				}
				DecisionCandidato decision = oferta.buscarDecision(match.getSolicitud().getCandidato());
				if(decision == null) {
					row[2] = "Pendiente";
				}
				if( decision.getEstado() == EstadoDecision.RECHAZADO) {
					row[2] = "Rechazada";
				}
				if(decision.getEstado() == EstadoDecision.CONTRATADO ) {
					row[2] = "Aceptada";
				}
				
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
