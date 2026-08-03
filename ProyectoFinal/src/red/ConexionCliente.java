package red;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexionCliente {

    private static ConexionCliente instancia;
    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;

    private ConexionCliente() throws IOException {
        socket = new Socket();
        socket.connect(new java.net.InetSocketAddress("127.0.0.1", 7000), 5000); 
        salida = new ObjectOutputStream(socket.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(socket.getInputStream());
    }


    public static ConexionCliente getInstancia() throws IOException {
        if (instancia == null) {
            instancia = new ConexionCliente();
        }
        return instancia;
    }

    public synchronized Respuesta enviarPeticion(Peticion peticion) throws IOException, ClassNotFoundException {
        salida.writeObject(peticion);
        salida.flush();
        salida.reset(); 
        return (Respuesta) entrada.readObject();
    }
}