package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RegSolicitud extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private Dimension dim = getToolkit().getScreenSize();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegSolicitud dialog = new RegSolicitud();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RegSolicitud() {
        setResizable(false);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("Registrar Solicitud");
        setIconImage(Toolkit.getDefaultToolkit().getImage(RegSolicitud.class.getResource("/img/AppIconoFull.png")));
        setBounds(0, 0, dim.width, dim.height - 40);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Registrar Solicitud");
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 28));
        lblTitulo.setBounds(60, 11, 300, 35);
        panel.add(lblTitulo);

        JButton btnMenu = new JButton("");
        btnMenu.setBounds(0, 0, 50, 50);
        btnMenu.setBackground(Color.WHITE);
        btnMenu.setMargin(new Insets(0, 0, 0, 0));
        btnMenu.setBorderPainted(false);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setFocusPainted(false);
        btnMenu.setOpaque(false);
        colocarIconoBoton(btnMenu, "/img/menu-dots-vertical (Blue).png", 25, 25);
        panel.add(btnMenu);

        JLabel lblFondo = new JLabel("");
        lblFondo.setBounds(0, 0, dim.width, dim.height - 40);
        panel.add(lblFondo);
    }

    private void colocarIconoBoton(AbstractButton boton, String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(getClass().getResource(ruta));
        Image imagenEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(imagenEscalada));
    }
}