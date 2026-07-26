package visual;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FotoPerfilRedond extends JPanel {

    private Image imagen;
    private String rutaFotoPerfil;
    private BotonRedond btnCambiar;

    public FotoPerfilRedond(int diametro) {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(diametro, diametro + 35));

        // Imagen por defecto
        java.net.URL urlDefault = getClass().getResource("/img/avatar-default.png");
        if (urlDefault != null) {
            imagen = new ImageIcon(urlDefault).getImage();
        }

        btnCambiar = new BotonRedond("Añadir Foto", 15);
        btnCambiar.setFont(new Font("Calibri", Font.PLAIN, 12));
        btnCambiar.setBackground(new Color(255, 153, 0));
        btnCambiar.setForeground(new Color(0, 0, 51));
        btnCambiar.addActionListener(e -> seleccionarFoto());
        add(btnCambiar);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int diametro = getWidth();
            Ellipse2D.Double circulo = new Ellipse2D.Double(0, 0, diametro, diametro);
            g2.setClip(circulo);
            g2.drawImage(imagen, 0, 0, diametro, diametro, this);
            g2.dispose();
        }
    }

    private void seleccionarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona tu foto de perfil");
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "Imágenes (jpg, jpeg, png)", "jpg", "jpeg", "png"));

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado != JFileChooser.APPROVE_OPTION) return;

        File archivoSeleccionado = fileChooser.getSelectedFile();

        try {
            File carpetaDestino = new File(
                    System.getProperty("user.home") + File.separator + "BolsaEmpleoFotos");
            if (!carpetaDestino.exists()) carpetaDestino.mkdirs();

            String nombreOriginal = archivoSeleccionado.getName();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.') + 1);
            String nombreArchivo = "perfil_" + System.currentTimeMillis() + "." + extension;
            File archivoDestino = new File(carpetaDestino, nombreArchivo);

            Files.copy(archivoSeleccionado.toPath(), archivoDestino.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            rutaFotoPerfil = archivoDestino.getAbsolutePath();
            imagen = new ImageIcon(rutaFotoPerfil).getImage();
            repaint();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo cargar la imagen: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga una imagen ya existente desde una ruta absoluta (por ejemplo, al editar un perfil). 
    public void cargarImagen(String rutaAbsoluta) {
        if (rutaAbsoluta == null) return;
        File archivo = new File(rutaAbsoluta);
        if (archivo.exists()) {
            this.rutaFotoPerfil = rutaAbsoluta;
            this.imagen = new ImageIcon(rutaAbsoluta).getImage();
            repaint();
        }
    }

    public String getRutaFotoPerfil() {
        return rutaFotoPerfil;
    }
    
    
    public void doLayout() {
        super.doLayout();

        int anchoBoton = getWidth() / 2;
        int altoBoton = 28;
        int x = (getWidth() - anchoBoton) / 2;
        int y = getHeight() - altoBoton;

        btnCambiar.setBounds(x, y, anchoBoton, altoBoton);
    }
}