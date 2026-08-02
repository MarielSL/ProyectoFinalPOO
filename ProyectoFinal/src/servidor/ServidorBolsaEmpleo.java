package servidor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

import logico.BolsaEmpleo;
import logico.Empresa;
import logico.EstadoOferta;
import logico.Obrero;
import logico.Oferta;
import logico.Persona;
import logico.Tecnico;
import logico.TipoPersona;
import logico.TipoUser;
import logico.Universitario;
import logico.Usuario;
import red.DatosLogin;
import red.DatosPublicarOferta;
import red.DatosRegistroEmpresa;
import red.DatosRegistroSolicitante;
import red.Peticion;
import red.Respuesta;

public class ServidorBolsaEmpleo {

    public static void main(String[] args) {
        BolsaEmpleo.getInstancia().crearAdminPorDefecto();

        ServerSocket sfd = null;
        try {
            sfd = new ServerSocket(7000);
            System.out.println("Servidor esperando conexiones en el puerto 7000...");
        } catch (IOException ioe) {
            System.out.println("No se pudo iniciar el servidor: " + ioe);
            System.exit(1);
        }

        while (true) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Cliente conectado: " + nsfd.getInetAddress());
                atenderCliente(nsfd);
            } catch (IOException ioe) {
                System.out.println("Error aceptando conexión: " + ioe);
            }
        }
    }

    private static void atenderCliente(Socket socket) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            salida.flush();
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

            while (true) {
                Peticion peticion = (Peticion) entrada.readObject();
                Respuesta respuesta = procesar(peticion);

                salida.writeObject(respuesta);
                salida.flush();
                salida.reset();
            }

        } catch (EOFException eof) {
            System.out.println("Cliente desconectado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error con el cliente: " + e);
        }
    }

    private static Respuesta procesar(Peticion peticion) {
        BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();

        switch (peticion.getTipo()) {

            case LOGIN:
                return procesarLogin(bolsa, (DatosLogin) peticion.getDatos());

            case REGISTRAR_EMPRESA:
                return procesarRegistroEmpresa(bolsa, (DatosRegistroEmpresa) peticion.getDatos());

            case REGISTRAR_SOLICITANTE:
                return procesarRegistroSolicitante(bolsa, (DatosRegistroSolicitante) peticion.getDatos());

            case PUBLICAR_OFERTA:
                return procesarPublicarOferta(bolsa, (DatosPublicarOferta) peticion.getDatos());

            default:
                return new Respuesta(false, "Operación no reconocida");
        }
    }

    private static Respuesta procesarLogin(BolsaEmpleo bolsa, DatosLogin datos) {
        boolean ok = bolsa.confirmLogin(datos.getUsername(), datos.getPassword());
        if (ok) {
            return new Respuesta(true, bolsa.getLoginUser());
        }
        return new Respuesta(false, "Usuario o contraseña incorrectos");
    }

    private static Respuesta procesarRegistroEmpresa(BolsaEmpleo bolsa, DatosRegistroEmpresa datos) {
        if (bolsa.isEmpressRep(datos.getRnc())) {
            return new Respuesta(false, "ERROR!: esta empresa ha sido registrada");
        }
        if (!bolsa.dispUsername(datos.getUsername())) {
            return new Respuesta(false, "Usuario en uso.");
        }

        String idEmpresa = "E-" + BolsaEmpleo.generadorIdEmpresa;
        Empresa empresa = new Empresa(idEmpresa, datos.getRnc(), datos.getNombre(), datos.getTelefono(),
                datos.getDireccion(), datos.getTipo(), null);

        String idUser = "U-" + BolsaEmpleo.generadorIdUser;
        Usuario nuevoUsuario = new Usuario(idUser, datos.getUsername(), datos.getPassword(), datos.getCorreo(),
                empresa, null, TipoUser.EMPRESA, datos.getFotoPerfil());

        empresa.setUser(nuevoUsuario);

        bolsa.regUser(nuevoUsuario);
        bolsa.regEmpresa(empresa);
        bolsa.setLoginUser(nuevoUsuario);

        return new Respuesta(true, nuevoUsuario);
    }

    private static Respuesta procesarRegistroSolicitante(BolsaEmpleo bolsa, DatosRegistroSolicitante datos) {
        if (!bolsa.dispUsername(datos.getUsername())) {
            return new Respuesta(false, "Usuario en uso.");
        }

        String idUser = "U-" + BolsaEmpleo.generadorIdUser;
        Usuario nuevoUsuario = new Usuario(idUser, datos.getUsername(), datos.getPassword(), datos.getCorreo(),
                null, null, TipoUser.CANDIDATO, datos.getFotoPerfil());

        String idPersona = "P-" + BolsaEmpleo.generadorIdPersona;
        Persona persona;

        if (datos.getTipo() == TipoPersona.UNIVERSITARIO) {
            persona = new Universitario(idPersona, datos.getCedula(), datos.getNombre(), datos.getApellido(),
                    datos.getFechaNacim(), datos.getTelefono(), datos.getDireccion(), datos.getSexo(),
                    datos.getCiudad(), datos.isDispMudarse(), datos.isLicenciaConducir(), datos.isEstadoEmpleo(),
                    nuevoUsuario, datos.getYearsExp(), datos.getCampoExtra());

        } else if (datos.getTipo() == TipoPersona.TECNICO) {
            persona = new Tecnico(idPersona, datos.getCedula(), datos.getNombre(), datos.getApellido(),
                    datos.getFechaNacim(), datos.getTelefono(), datos.getDireccion(), datos.getSexo(),
                    datos.getCiudad(), datos.isDispMudarse(), datos.isLicenciaConducir(), datos.isEstadoEmpleo(),
                    nuevoUsuario, datos.getYearsExp(), datos.getCampoExtra());

        } else {
            persona = new Obrero(idPersona, datos.getCedula(), datos.getNombre(), datos.getApellido(),
                    datos.getFechaNacim(), datos.getTelefono(), datos.getDireccion(), datos.getSexo(),
                    datos.getCiudad(), datos.isDispMudarse(), datos.isLicenciaConducir(), datos.isEstadoEmpleo(),
                    nuevoUsuario, datos.getYearsExp(), datos.getCampoExtra());
        }

        nuevoUsuario.setPersona(persona);

        bolsa.regPersona(persona);
        bolsa.regUser(nuevoUsuario);
        bolsa.setLoginUser(nuevoUsuario);

        return new Respuesta(true, nuevoUsuario);
    }

    private static Respuesta procesarPublicarOferta(BolsaEmpleo bolsa, DatosPublicarOferta datos) {
        Usuario loginUser = bolsa.getLoginUser();
        if (loginUser == null || loginUser.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa para publicar una oferta.");
        }

        Empresa empresa = loginUser.getEmpresa();
        String id = "O-" + BolsaEmpleo.generadorIdOferta;

        Oferta oferta = new Oferta(id, datos.getSexo(), datos.getTipoCandidato(), datos.getPuesto(),
                datos.getCantPuestos(), datos.isLicencia(), datos.isDispMudar(), EstadoOferta.PENDIENTE,
                datos.getJornada(), datos.getCiudad(), datos.getSalario(), datos.getDescripPuesto(),
                datos.getAniosExp(), empresa, datos.getModalidad(), LocalDate.now(), datos.getAreaLaboral());

        bolsa.refOferta(oferta);
        empresa.agregarOferta(oferta);

        return new Respuesta(true, oferta);
    }
}