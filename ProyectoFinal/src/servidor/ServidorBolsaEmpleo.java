package servidor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

import logico.BolsaEmpleo;
import logico.DecisionCandidato;
import logico.Empresa;
import logico.EstadoDecision;
import logico.EstadoOferta;
import logico.EstadoSolicitud;
import logico.Obrero;
import logico.Oferta;
import logico.Persona;
import logico.ResultMatch;
import logico.SolicitudEmpleo;
import logico.Tecnico;
import logico.TipoPersona;
import logico.TipoUser;
import logico.Universitario;
import logico.Usuario;
import red.DatosDashboardAdmin;
import red.DatosDecidirCandidato;
import red.DatosEstadisticas;
import red.DatosEstadisticasCandidato;
import red.DatosEstadisticasEmpresa;
import red.DatosLogin;
import red.DatosObtenerMatch;
import red.DatosPublicarOferta;
import red.DatosRegistrarSolicitud;
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

            case REGISTRAR_SOLICITUD:
                return procesarRegistrarSolicitud(bolsa, (DatosRegistrarSolicitud) peticion.getDatos());
            
            case OBTENER_MATCH:
                return procesarObtenerMatch(bolsa, (DatosObtenerMatch) peticion.getDatos());

            case DECIDIR_CANDIDATO:
                return procesarDecidirCandidato(bolsa, (DatosDecidirCandidato) peticion.getDatos());
                
            case OBTENER_ESTADISTICAS:
                return procesarObtenerEstadisticas(bolsa);
                
            case OBTENER_OFERTAS:
                return procesarObtenerOfertas(bolsa);

            case OBTENER_OFERTAS_EMPRESA:
                return procesarObtenerOfertasEmpresa(bolsa);

            case OBTENER_ESTADISTICAS_CANDIDATO:
                return procesarEstadisticasCandidato(bolsa);

            case OBTENER_ESTADISTICAS_EMPRESA:
                return procesarEstadisticasEmpresa(bolsa);
            
            case OBTENER_TODAS_EMPRESAS:
                return new Respuesta(true, bolsa.getEmpresas() != null ? bolsa.getEmpresas() : new ArrayList<Empresa>());

            case OBTENER_TODOS_USUARIOS:
                return new Respuesta(true, bolsa.getUsuarios() != null ? bolsa.getUsuarios() : new ArrayList<Usuario>());

            case OBTENER_DASHBOARD_ADMIN:
                return procesarDashboardAdmin(bolsa);
                
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
    
    private static Respuesta procesarRegistrarSolicitud(BolsaEmpleo bolsa, DatosRegistrarSolicitud datos) {
        Usuario loginUser = bolsa.getLoginUser();
        if (loginUser == null || loginUser.getPersona() == null) {
            return new Respuesta(false, "Debe iniciar sesión como candidato para crear una solicitud.");
        }

        String id = "S-" + BolsaEmpleo.generadorIdSolicitud;
        LocalDate fechaHoy = LocalDate.now();

        SolicitudEmpleo solicitud = new SolicitudEmpleo(id, EstadoSolicitud.ACTIVA, loginUser.getPersona(),
                fechaHoy, datos.getAreaLaboral(), datos.getSueldoEsperado(), datos.getModalidad(),
                datos.getPuesto(), datos.getJornada());

        bolsa.regSolicitud(solicitud, loginUser.getPersona());

        return new Respuesta(true, solicitud);
    }
    
    private static Respuesta procesarObtenerMatch(BolsaEmpleo bolsa, DatosObtenerMatch datos) {
        System.out.println("=== OBTENER_MATCH ===");
        System.out.println("ID de oferta solicitado: " + datos.getOfertaId());

        Oferta oferta = bolsa.buscarOfertaPorId(datos.getOfertaId());
        System.out.println("Oferta encontrada: " + (oferta != null));

        if (oferta == null) {
            return new Respuesta(false, "No se encontró la oferta.");
        }

        System.out.println("Total de solicitudes en el sistema: " + bolsa.getSolicitudes().size());
        for (SolicitudEmpleo s : bolsa.getSolicitudes()) {
            System.out.println("  - Solicitud id=" + s.getId() + " candidato=" + (s.getCandidato() != null ? s.getCandidato().getNombre() : "null") + " estado=" + s.getEstado());
        }

        ArrayList<ResultMatch> resultados = bolsa.calcularMatch(oferta);
        System.out.println("Cantidad de resultados del match: " + resultados.size());

        return new Respuesta(true, resultados);
    }
    private static Respuesta procesarDecidirCandidato(BolsaEmpleo bolsa, DatosDecidirCandidato datos) {
        Oferta oferta = bolsa.buscarOfertaPorId(datos.getOfertaId());
        if (oferta == null) {
            return new Respuesta(false, "No se encontró la oferta.");
        }

        Persona candidato = bolsa.buscarPersonaPorId(datos.getCandidatoId());
        if (candidato == null) {
            return new Respuesta(false, "No se encontró el candidato.");
        }

        if (datos.getDecision() == EstadoDecision.CONTRATADO && oferta.getCantPuestos() <= 0) {
            return new Respuesta(false, "La oferta ya no tiene puestos disponibles.");
        }

        oferta.guardarDecision(candidato, datos.getDecision());

        if (datos.getDecision() == EstadoDecision.CONTRATADO) {
            candidato.setEstadoEmpleo(true);
            if (candidato.getSolicitud() != null) {
                candidato.getSolicitud().setEstado(EstadoSolicitud.CERRADA);
            }
            oferta.setCantPuestos(Math.max(0, oferta.getCantPuestos() - 1));
            if (oferta.getCantPuestos() == 0) {
                oferta.setEstado(EstadoOferta.COMPLETADA);
            }
        }

        bolsa.guardarDatos();
        return new Respuesta(true, null);
    }
    
    private static Respuesta procesarObtenerEstadisticas(BolsaEmpleo bolsa) {
        ArrayList<Oferta> lasOfertas = bolsa.getOfertas();
        ArrayList<Persona> lasPersonas = bolsa.getPersonas();
        ArrayList<SolicitudEmpleo> lasSolicitudes = bolsa.getSolicitudes();
        ArrayList<Empresa> lasEmpresas = bolsa.getEmpresas();

        int totalOfertas = lasOfertas == null ? 0 : lasOfertas.size();
        int ofertasActivas = 0;
        if (lasOfertas != null) {
            for (Oferta oferta : lasOfertas) {
                if (oferta != null && oferta.getEstado() == EstadoOferta.PENDIENTE) {
                    ofertasActivas++;
                }
            }
        }

        int totalSolicitantes = lasPersonas == null ? 0 : lasPersonas.size();
        int solicitantesDisponibles = 0;
        if (lasPersonas != null) {
            for (Persona persona : lasPersonas) {
                if (persona != null && !persona.isEstadoEmpleo()) {
                    solicitantesDisponibles++;
                }
            }
        }

        int totalPostulaciones = lasSolicitudes == null ? 0 : lasSolicitudes.size();
        int postulacionesPendientes = 0;
        if (lasSolicitudes != null) {
            for (SolicitudEmpleo solicitud : lasSolicitudes) {
                if (solicitud != null && solicitud.getEstado() == EstadoSolicitud.ACTIVA) {
                    postulacionesPendientes++;
                }
            }
        }

        int totalEmpresas = lasEmpresas == null ? 0 : lasEmpresas.size();
        int empresasActivas = 0;
        if (lasEmpresas != null) {
            for (Empresa empresa : lasEmpresas) {
                if (empresa != null && empresa.isEstado()) {
                    empresasActivas++;
                }
            }
        }

        DatosEstadisticas resultado = new DatosEstadisticas(totalOfertas, ofertasActivas,
                totalSolicitantes, solicitantesDisponibles, totalPostulaciones, postulacionesPendientes,
                totalEmpresas, empresasActivas);

        return new Respuesta(true, resultado);
    }
    
    private static Respuesta procesarObtenerOfertas(BolsaEmpleo bolsa) {
        ArrayList<Oferta> ofertas = bolsa.getOfertas();
        return new Respuesta(true, ofertas != null ? ofertas : new ArrayList<Oferta>());
    }

    private static Respuesta procesarObtenerOfertasEmpresa(BolsaEmpleo bolsa) {
        Usuario loginUser = bolsa.getLoginUser();
        if (loginUser == null || loginUser.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa.");
        }

        ArrayList<Oferta> ofertas = loginUser.getEmpresa().getLasOfertas();
        return new Respuesta(true, ofertas != null ? ofertas : new ArrayList<Oferta>());
    }

    private static Respuesta procesarEstadisticasCandidato(BolsaEmpleo bolsa) {
        Usuario loginUser = bolsa.getLoginUser();

        String estadoBusqueda = "Por Crear";
        float mayorCoincidencia = 0f;

        if (loginUser != null && loginUser.getPersona() != null && loginUser.getPersona().getSolicitud() != null) {
            SolicitudEmpleo solicitud = loginUser.getPersona().getSolicitud();
            estadoBusqueda = solicitud.getEstado() == EstadoSolicitud.ACTIVA ? "Activa" : "Inactiva";
            mayorCoincidencia = bolsa.CalcMayorCoincidenciaSolicitud(solicitud);
        }

        int ofertasDisponibles = 0;
        ArrayList<Oferta> ofertas = bolsa.getOfertas();
        if (ofertas != null) {
            for (Oferta oferta : ofertas) {
                if (oferta != null && oferta.getEstado() == EstadoOferta.PENDIENTE) {
                    ofertasDisponibles++;
                }
            }
        }

        DatosEstadisticasCandidato resultado = new DatosEstadisticasCandidato(estadoBusqueda, ofertasDisponibles, mayorCoincidencia);
        return new Respuesta(true, resultado);
    }

    private static Respuesta procesarEstadisticasEmpresa(BolsaEmpleo bolsa) {
        Usuario loginUser = bolsa.getLoginUser();
        if (loginUser == null || loginUser.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa.");
        }

        Empresa empresa = loginUser.getEmpresa();
        ArrayList<Oferta> ofertasEmpresa = empresa.getLasOfertas();
        if (ofertasEmpresa == null) {
            ofertasEmpresa = new ArrayList<Oferta>();
        }

        int ofertasActivas = 0;
        int candidatosCompatibles = 0;
        for (Oferta oferta : ofertasEmpresa) {
            if (oferta != null && oferta.getEstado() == EstadoOferta.PENDIENTE) {
                ofertasActivas++;
                candidatosCompatibles += bolsa.cantCandidatosCompatibles(oferta);
            }
        }

        int contratadosEsteMes = 0;
        ArrayList<SolicitudEmpleo> solicitudes = bolsa.getSolicitudes();
        LocalDate hoy = LocalDate.now();

        if (solicitudes != null) {
            for (SolicitudEmpleo solicitud : solicitudes) {
                if (solicitud == null || solicitud.getFechaSolicitud() == null) {
                    continue;
                }
                if (solicitud.getEstado() != EstadoSolicitud.CERRADA) {
                    continue;
                }
                if (solicitud.getFechaSolicitud().getMonthValue() != hoy.getMonthValue()
                        || solicitud.getFechaSolicitud().getYear() != hoy.getYear()) {
                    continue;
                }

                boolean contratadoPorEmpresa = false;
                for (Oferta oferta : ofertasEmpresa) {
                    if (oferta == null || solicitud.getCandidato() == null) {
                        continue;
                    }
                    DecisionCandidato decision = oferta.buscarDecision(solicitud.getCandidato());
                    if (decision != null && decision.getEstado() == EstadoDecision.CONTRATADO) {
                        contratadoPorEmpresa = true;
                        break;
                    }
                }

                if (contratadoPorEmpresa) {
                    contratadosEsteMes++;
                }
            }
        }

        DatosEstadisticasEmpresa resultado = new DatosEstadisticasEmpresa(ofertasActivas, candidatosCompatibles, contratadosEsteMes);
        return new Respuesta(true, resultado);
    }
    
    private static Respuesta procesarDashboardAdmin(BolsaEmpleo bolsa) {
        ArrayList<Oferta> ofertas = bolsa.getOfertas() != null ? bolsa.getOfertas() : new ArrayList<Oferta>();
        ArrayList<Persona> personas = bolsa.getPersonas() != null ? bolsa.getPersonas() : new ArrayList<Persona>();
        int totalEmpresas = bolsa.getEmpresas() != null ? bolsa.getEmpresas().size() : 0;

        DatosDashboardAdmin resultado = new DatosDashboardAdmin(ofertas, personas, totalEmpresas);
        return new Respuesta(true, resultado);
    }
    
    
}