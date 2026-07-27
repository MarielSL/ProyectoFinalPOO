package visual;

import javax.swing.*;
import java.awt.*;

public class Utilidades {
    public static void aplicarIcono(Window ventana) {
        java.net.URL url = Utilidades.class.getResource("/img/AppIconoFull.png");
        if (url != null) {
            ventana.setIconImage(new ImageIcon(url).getImage());
        }
    }
}