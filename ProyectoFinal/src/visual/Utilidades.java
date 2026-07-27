package visual;

import javax.swing.*;
import java.awt.*;

public class Utilidades {
    public static void aplicarIcono(Window ventana) {
        java.net.URL url = Utilidades.class.getResource("/img/App Icon.png");
        if (url != null) {
            ventana.setIconImage(new ImageIcon(url).getImage());
        }
    }
}