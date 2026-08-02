package servidor;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import red.DatosGraficasAdmin;
import red.DatosGraficasEmpresa;
import red.DatosLogin;
import red.DatosMejorMatchCandidato;
import red.DatosModificarEmpresa;
import red.DatosModificarSolicitante;
import red.DatosModificarSolicitud;
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

                Thread hiloCliente = new Thread(() -> atenderCliente(nsfd));
                hiloCliente.start();

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

            Usuario usuarioSesion = null; 

            while (true) {
                Peticion peticion = (Peticion) entrada.readObject();
                Respuesta respuesta = procesar(peticion, usuarioSesion);

                if (respuesta.isExito() && esOperacionDeSesion(peticion.getTipo())) {
                    usuarioSesion = (Usuario) respuesta.getDatos();
                }

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

    private static boolean esOperacionDeSesion(Peticion.Tipo tipo) {
        return tipo == Peticion.Tipo.LOGIN
                || tipo == Peticion.Tipo.REGISTRAR_EMPRESA
                || tipo == Peticion.Tipo.REGISTRAR_SOLICITANTE;
    }

    private static synchronized Respuesta procesar(Peticion peticion, Usuario usuarioSesion) {
        BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();

        switch (peticion.getTipo()) {

            case LOGIN:
                return procesarLogin(bolsa, (DatosLogin) peticion.getDatos());

            case REGISTRAR_EMPRESA:
                return procesarRegistroEmpresa(bolsa, (DatosRegistroEmpresa) peticion.getDatos());

            case REGISTRAR_SOLICITANTE:
                return procesarRegistroSolicitante(bolsa, (DatosRegistroSolicitante) peticion.getDatos());

            case PUBLICAR_OFERTA:
                return procesarPublicarOferta(bolsa, usuarioSesion, (DatosPublicarOferta) peticion.getDatos());

            case REGISTRAR_SOLICITUD:
                return procesarRegistrarSolicitud(bolsa, usuarioSesion, (DatosRegistrarSolicitud) peticion.getDatos());

            case OBTENER_MATCH:
                return procesarObtenerMatch(bolsa, (DatosObtenerMatch) peticion.getDatos());

            case DECIDIR_CANDIDATO:
                return procesarDecidirCandidato(bolsa, (DatosDecidirCandidato) peticion.getDatos());

            case OBTENER_ESTADISTICAS:
                return procesarObtenerEstadisticas(bolsa);

            case OBTENER_OFERTAS:
                return procesarObtenerOfertas(bolsa);

            case OBTENER_OFERTAS_EMPRESA:
                return procesarObtenerOfertasEmpresa(bolsa, usuarioSesion);

            case OBTENER_ESTADISTICAS_CANDIDATO:
                return procesarEstadisticasCandidato(bolsa, usuarioSesion);

            case OBTENER_ESTADISTICAS_EMPRESA:
                return procesarEstadisticasEmpresa(bolsa, usuarioSesion);

            case OBTENER_TODAS_EMPRESAS:
                return new Respuesta(true, bolsa.getEmpresas() != null ? bolsa.getEmpresas() : new ArrayList<Empresa>());

            case OBTENER_TODOS_USUARIOS:
                return new Respuesta(true, bolsa.getUsuarios() != null ? bolsa.getUsuarios() : new ArrayList<Usuario>());

            case OBTENER_DASHBOARD_ADMIN:
                return procesarDashboardAdmin(bolsa);

            case OBTENER_TODOS_CANDIDATOS:
                return new Respuesta(true, bolsa.getPersonas() != null ? bolsa.getPersonas() : new ArrayList<Persona>());

            case OBTENER_MEJOR_MATCH_EMPRESA:
                return procesarMejorMatchEmpresa(bolsa, usuarioSesion);

            case MODIFICAR_EMPRESA:
                return procesarModificarEmpresa(bolsa, usuarioSesion, (DatosModificarEmpresa) peticion.getDatos());

            case MODIFICAR_SOLICITANTE:
                return procesarModificarSolicitante(bolsa, usuarioSesion, (DatosModificarSolicitante) peticion.getDatos());

            case MODIFICAR_SOLICITUD:
                return procesarModificarSolicitud(bolsa, usuarioSesion, (DatosModificarSolicitud) peticion.getDatos());

            case CREAR_RESPALDO:
                return procesarCrearRespaldo();

            case OBTENER_GRAFICAS_EMPRESA:
                return procesarGraficasEmpresa(bolsa, usuarioSesion);

            case OBTENER_GRAFICAS_ADMIN:
                return procesarGraficasAdmin(bolsa);

            default:
                return new Respuesta(false, "Operación no reconocida");
        }
    }

    // ==================== LOGIN ====================

    private static Respuesta procesarLogin(BolsaEmpleo bolsa, DatosLogin datos) {
        boolean ok = bolsa.confirmLogin(datos.getUsername(), datos.getPassword());
        if (ok) {
            return new Respuesta(true, bolsa.getLoginUser());
        }
        return new Respuesta(false, "Usuario o contraseña incorrectos");
    }

    // ==================== REGISTRO ====================

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

        return new Respuesta(true, nuevoUsuario);
    }

    // ==================== OFERTAS ====================

    private static Respuesta procesarPublicarOferta(BolsaEmpleo bolsa, Usuario usuarioSesion, DatosPublicarOferta datos) {
        if (usuarioSesion == null || usuarioSesion.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa para publicar una oferta.");
        }

        Empresa empresa = usuarioSesion.getEmpresa();
        String id = "O-" + BolsaEmpleo.generadorIdOferta;

        Oferta oferta = new Oferta(id, datos.getSexo(), datos.getTipoCandidato(), datos.getPuesto(),
                datos.getCantPuestos(), datos.isLicencia(), datos.isDispMudar(), EstadoOferta.PENDIENTE,
                datos.getJornada(), datos.getCiudad(), datos.getSalario(), datos.getDescripPuesto(),
                datos.getAniosExp(), empresa, datos.getModalidad(), LocalDate.now(), datos.getAreaLaboral());

        bolsa.refOferta(oferta);
        empresa.agregarOferta(oferta);

        return new Respuesta(true, oferta);
    }

    private static Respuesta procesarObtenerOfertas(BolsaEmpleo bolsa) {
        ArrayList<Oferta> ofertas = bolsa.getOfertas();
        return new Respuesta(true, ofertas != null ? ofertas : new ArrayList<Oferta>());
    }

    private static Respuesta procesarObtenerOfertasEmpresa(BolsaEmpleo bolsa, Usuario usuarioSesion) {
        if (usuarioSesion == null || usuarioSesion.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa.");
        }

        ArrayList<Oferta> ofertas = usuarioSesion.getEmpresa().getLasOfertas();
        return new Respuesta(true, ofertas != null ? ofertas : new ArrayList<Oferta>());
    }

    // ==================== SOLICITUDES ====================

    private static Respuesta procesarRegistrarSolicitud(BolsaEmpleo bolsa, Usuario usuarioSesion, DatosRegistrarSolicitud datos) {
        if (usuarioSesion == null || usuarioSesion.getPersona() == null) {
            return new Respuesta(false, "Debe iniciar sesión como candidato para crear una solicitud.");
        }

        String id = "S-" + BolsaEmpleo.generadorIdSolicitud;
        LocalDate fechaHoy = LocalDate.now();

        SolicitudEmpleo solicitud = new SolicitudEmpleo(id, EstadoSolicitud.ACTIVA, usuarioSesion.getPersona(),
                fechaHoy, datos.getAreaLaboral(), datos.getSueldoEsperado(), datos.getModalidad(),
                datos.getPuesto(), datos.getJornada());

        bolsa.regSolicitud(solicitud, usuarioSesion.getPersona());

        return new Respuesta(true, solicitud);
    }

    private static Respuesta procesarModificarSolicitud(BolsaEmpleo bolsa, Usuario usuarioSesion, DatosModificarSolicitud datos) {
        if (usuarioSesion == null || usuarioSesion.getPersona() == null || usuarioSesion.getPersona().getSolicitud() == null) {
            return new Respuesta(false, "No se encontró una solicitud activa para este candidato.");
        }

        SolicitudEmpleo solicitud = usuarioSesion.getPersona().getSolicitud();

        solicitud.setPuesto(datos.getPuesto());
        solicitud.setAreaLaboral(datos.getAreaLaboral());
        solicitud.setJornada(datos.getJornada());
        solicitud.setModalidad(datos.getModalidad());
        solicitud.setSueldoEsperado(datos.getSueldoEsperado());

        bolsa.modSolicitud(solicitud);

        return new Respuesta(true, solicitud);
    }

    // ==================== MATCH Y DECISIONES ====================

    private static Respuesta procesarObtenerMatch(BolsaEmpleo bolsa, DatosObtenerMatch datos) {
        Oferta oferta = bolsa.buscarOfertaPorId(datos.getOfertaId());
        if (oferta == null) {
            return new Respuesta(false, "No se encontró la oferta.");
        }

        ArrayList<ResultMatch> resultados = bolsa.calcularMatch(oferta);
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

    private static Respuesta procesarMejorMatchEmpresa(BolsaEmpleo bolsa, Usuario usuarioSesion) {
        if (usuarioSesion == null || usuarioSesion.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa.");
        }

        ArrayList<Oferta> misOfertas = usuarioSesion.getEmpresa().getLasOfertas();
        if (misOfertas == null || misOfertas.isEmpty()) {
            return new Respuesta(true, new ArrayList<DatosMejorMatchCandidato>());
        }

        ArrayList<DatosMejorMatchCandidato> resultado = new ArrayList<DatosMejorMatchCandidato>();

        for (SolicitudEmpleo solicitud : bolsa.getSolicitudes()) {
            if (solicitud == null || solicitud.getCandidato() == null || solicitud.getEstado() != EstadoSolicitud.ACTIVA) {
                continue;
            }

            Oferta mejorOferta = null;
            float mejorPorcentaje = -1f;

            for (Oferta oferta : misOfertas) {
                if (oferta == null) {
                    continue;
                }
                float porcentaje = bolsa.calcCoincidencia(oferta, solicitud);
                if (porcentaje > mejorPorcentaje) {
                    mejorPorcentaje = porcentaje;
                    mejorOferta = oferta;
                }
            }

            if (mejorOferta != null) {
                resultado.add(new DatosMejorMatchCandidato(solicitud.getCandidato(), solicitud, mejorOferta, mejorPorcentaje));
            }
        }

        return new Respuesta(true, resultado);
    }

    // ==================== ESTADÍSTICAS ====================

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

    private static Respuesta procesarEstadisticasCandidato(BolsaEmpleo bolsa, Usuario usuarioSesion) {
        String estadoBusqueda = "Por Crear";
        float mayorCoincidencia = 0f;

        if (usuarioSesion != null && usuarioSesion.getPersona() != null && usuarioSesion.getPersona().getSolicitud() != null) {
            SolicitudEmpleo solicitud = usuarioSesion.getPersona().getSolicitud();
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

    private static Respuesta procesarEstadisticasEmpresa(BolsaEmpleo bolsa, Usuario usuarioSesion) {
        if (usuarioSesion == null || usuarioSesion.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa.");
        }

        Empresa empresa = usuarioSesion.getEmpresa();
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

    // ==================== ADMIN ====================

    private static Respuesta procesarDashboardAdmin(BolsaEmpleo bolsa) {
        ArrayList<Oferta> ofertas = bolsa.getOfertas() != null ? bolsa.getOfertas() : new ArrayList<Oferta>();
        ArrayList<Persona> personas = bolsa.getPersonas() != null ? bolsa.getPersonas() : new ArrayList<Persona>();
        int totalEmpresas = bolsa.getEmpresas() != null ? bolsa.getEmpresas().size() : 0;

        int actividadPlataforma = calcularActividadPlataforma(ofertas, personas, bolsa);
        int ofertasActivas = contarOfertasActivas(ofertas);
        int accionesPendientes = contarAccionesPendientes(ofertas, personas, bolsa);

        DatosDashboardAdmin resultado = new DatosDashboardAdmin(
                ofertas,
                personas,
                totalEmpresas,
                actividadPlataforma,
                ofertasActivas,
                accionesPendientes
        );

        return new Respuesta(true, resultado);
    }
    
    private static int calcularActividadPlataforma(ArrayList<Oferta> ofertas, ArrayList<Persona> personas, BolsaEmpleo bolsa) {
        int actividad = 0;

        if (ofertas != null) {
            actividad += ofertas.size();
        }

        if (personas != null) {
            actividad += personas.size();
        }

        if (bolsa != null && bolsa.getEmpresas() != null) {
            actividad += bolsa.getEmpresas().size();
        }

        return actividad;
    }

    private static int contarOfertasActivas(ArrayList<Oferta> ofertas) {
        if (ofertas == null) {
            return 0;
        }

        int contador = 0;
        for (Oferta oferta : ofertas) {
            if (oferta != null && oferta.getEstado() == logico.EstadoOferta.PENDIENTE) {
                contador++;
            }
        }
        return contador;
    }

    private static int contarAccionesPendientes(ArrayList<Oferta> ofertas, ArrayList<Persona> personas, BolsaEmpleo bolsa) {
        int pendientes = 0;

        if (ofertas != null) {
            for (Oferta oferta : ofertas) {
                if (oferta != null && oferta.getEstado() == logico.EstadoOferta.PENDIENTE) {
                    pendientes += oferta.getCantPuestos();
                }
            }
        }

        if (personas != null) {
            for (Persona persona : personas) {
                if (persona != null && !persona.isEstadoEmpleo()) {
                    pendientes++;
                }
            }
        }

        return pendientes;
    }

    // ==================== MODIFICAR ====================

    private static Respuesta procesarModificarEmpresa(BolsaEmpleo bolsa, Usuario usuarioSesion, DatosModificarEmpresa datos) {
        if (usuarioSesion == null || usuarioSesion.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa.");
        }

        Empresa empresa = usuarioSesion.getEmpresa();

        empresa.setNombre(datos.getNombre());
        empresa.setRnc(datos.getRnc());
        empresa.setDireccion(datos.getDireccion());
        empresa.setTelefono(datos.getTelefono());
        empresa.setTipo(datos.getTipo());

        Usuario user = empresa.getUser();
        user.setCorreo(datos.getCorreo());
        user.setUsername(datos.getUsername());
        user.setPassword(datos.getPassword());
        if (datos.getFotoPerfil() != null) {
            user.setFotoPerfil(datos.getFotoPerfil());
        }

        bolsa.modEmpresa(empresa);

        return new Respuesta(true, empresa);
    }

    private static Respuesta procesarModificarSolicitante(BolsaEmpleo bolsa, Usuario usuarioSesion, DatosModificarSolicitante datos) {
        if (usuarioSesion == null || usuarioSesion.getPersona() == null) {
            return new Respuesta(false, "Debe iniciar sesión como candidato.");
        }

        Persona candidato = usuarioSesion.getPersona();

        candidato.setNombre(datos.getNombre());
        candidato.setApellido(datos.getApellido());
        candidato.setCedula(datos.getCedula());
        candidato.setFechNacim(datos.getFechaNacim());
        candidato.setTelefono(datos.getTelefono());
        candidato.setDireccion(datos.getDireccion());
        candidato.setSexo(datos.getSexo());
        candidato.setCiudad(datos.getCiudad());
        candidato.setDispParaMudarse(datos.isDispMudarse());
        candidato.setLicenciaConducir(datos.isLicenciaConducir());
        candidato.setEstadoEmpleo(datos.isEstadoEmpleo());
        candidato.setYearsExp(datos.getYearsExp());

        Usuario user = candidato.getUser();
        user.setCorreo(datos.getCorreo());
        user.setUsername(datos.getUsername());
        user.setPassword(datos.getPassword());
        if (datos.getFotoPerfil() != null) {
            user.setFotoPerfil(datos.getFotoPerfil());
        }

        if (candidato instanceof Universitario) {
            ((Universitario) candidato).setCarrera(datos.getCampoExtra());
        } else if (candidato instanceof Tecnico) {
            ((Tecnico) candidato).setTecnico(datos.getCampoExtra());
        } else if (candidato instanceof Obrero) {
            ((Obrero) candidato).setHabilidades(datos.getCampoExtra());
        }

        bolsa.modSolicitante(candidato);
        bolsa.modUsuario(user);

        return new Respuesta(true, candidato);
    }

    // ==================== RESPALDO ====================

    private static Respuesta procesarCrearRespaldo() {
        try {
            File carpetaBackups = new File("backups");
            if (!carpetaBackups.exists()) {
                carpetaBackups.mkdirs();
            }

            File origen = new File("BolsaEmpleo.dat");
            if (!origen.exists()) {
                return new Respuesta(false, "No hay datos guardados todavía para respaldar.");
            }

            String marcaTiempo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File destino = new File(carpetaBackups, "BolsaEmpleo_" + marcaTiempo + ".dat");

            Files.copy(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return new Respuesta(true, destino.getName());

        } catch (IOException e) {
            e.printStackTrace();
            return new Respuesta(false, "No se pudo crear el respaldo: " + e.getMessage());
        }
    }

    // ==================== GRÁFICAS ====================

    private static Respuesta procesarGraficasEmpresa(BolsaEmpleo bolsa, Usuario usuarioSesion) {
        if (usuarioSesion == null || usuarioSesion.getEmpresa() == null) {
            return new Respuesta(false, "Debe iniciar sesión como empresa.");
        }

        Empresa empresa = usuarioSesion.getEmpresa();
        ArrayList<Oferta> misOfertas = empresa.getLasOfertas();
        if (misOfertas == null) {
            misOfertas = new ArrayList<Oferta>();
        }

        ArrayList<String> puestos = new ArrayList<String>();
        ArrayList<Integer> candidatosPorOferta = new ArrayList<Integer>();

        for (Oferta oferta : misOfertas) {
            if (oferta == null) {
                continue;
            }
            puestos.add(oferta.getPuesto());
            candidatosPorOferta.add(bolsa.cantCandidatosCompatibles(oferta));
        }

        DatosGraficasEmpresa resultado = new DatosGraficasEmpresa(
                puestos, candidatosPorOferta, empresa.cantOfertasActivas(), empresa.cantOfertasCompletadas());

        return new Respuesta(true, resultado);
    }

    private static Respuesta procesarGraficasAdmin(BolsaEmpleo bolsa) {
        ArrayList<Persona> personas = bolsa.getPersonas() != null ? bolsa.getPersonas() : new ArrayList<Persona>();
        ArrayList<Oferta> ofertas = bolsa.getOfertas() != null ? bolsa.getOfertas() : new ArrayList<Oferta>();
        ArrayList<SolicitudEmpleo> solicitudes = bolsa.getSolicitudes() != null ? bolsa.getSolicitudes() : new ArrayList<SolicitudEmpleo>();

        int solicitantesEmpleados = 0;
        int hombresEmpleados = 0;
        int mujeresEmpleadas = 0;
        for (Persona p : personas) {
            if (p != null && p.isEstadoEmpleo()) {
                solicitantesEmpleados++;
                String sexo = p.getSexo() != null ? p.getSexo().toString().toLowerCase() : "";
                if (sexo.contains("m") && !sexo.contains("f")) {
                    hombresEmpleados++;
                } else if (sexo.contains("f")) {
                    mujeresEmpleadas++;
                }
            }
        }

        int empresasActivas = 0;
        for (Oferta o : ofertas) {
            if (o != null && (o.getEstado() == EstadoOferta.PENDIENTE || o.getEstado() == EstadoOferta.COMPLETADA)) {
                empresasActivas++;
            }
        }

        Map<String, Integer> conteoEmpresas = new LinkedHashMap<String, Integer>();
        for (Oferta o : ofertas) {
            if (o == null) {
                continue;
            }
            String nombre = o.getEmpresa() != null ? o.getEmpresa().getNombre() : "Sin empresa";
            conteoEmpresas.put(nombre, conteoEmpresas.getOrDefault(nombre, 0) + 1);
        }
        List<Map.Entry<String, Integer>> listaEmpresas = new ArrayList<Map.Entry<String, Integer>>(conteoEmpresas.entrySet());
        listaEmpresas.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        ArrayList<String> nombresEmpresasTop = new ArrayList<String>();
        ArrayList<Integer> ofertasPorEmpresaTop = new ArrayList<Integer>();
        int maxEmpresas = Math.min(5, listaEmpresas.size());
        for (int i = 0; i < maxEmpresas; i++) {
            nombresEmpresasTop.add(listaEmpresas.get(i).getKey());
            ofertasPorEmpresaTop.add(listaEmpresas.get(i).getValue());
        }

        int solicitudesMes = solicitudes.size();
        int ofertasMes = ofertas.size();

        int solicitudesRecibidas = solicitudes.size();
        int solicitudesAceptadas = 0;
        for (SolicitudEmpleo s : solicitudes) {
            if (s != null && s.getEstado() == EstadoSolicitud.CERRADA) {
                solicitudesAceptadas++;
            }
        }

        DatosGraficasAdmin resultado = new DatosGraficasAdmin(
                solicitantesEmpleados, empresasActivas,
                nombresEmpresasTop, ofertasPorEmpresaTop,
                solicitudesMes, ofertasMes,
                solicitudesRecibidas, solicitudesAceptadas,
                hombresEmpleados, mujeresEmpleadas);

        return new Respuesta(true, resultado);
    }
}