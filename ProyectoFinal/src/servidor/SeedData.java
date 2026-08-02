package servidor;

import java.time.LocalDate;
import java.util.Random;

import logico.*;

public class SeedData {

    public static void main(String[] args) {
        BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
        bolsa.crearAdminPorDefecto();

        Random random = new Random();
        String[] nombres = {"Juan","Maria","Pedro","Ana","Luis","Carla","Jose","Rosa","Miguel","Laura",
                "Carlos","Elena","David","Sofia","Diego","Valeria","Andres","Camila","Rafael","Daniela"};
        String[] apellidos = {"Perez","Gomez","Diaz","Martinez","Lopez","Garcia","Reyes","Cruz","Torres","Ramirez"};
        String[] ciudades = {"Santiago","Santo Domingo","Puerto Plata","La Vega","San Francisco de Macoris"};

        String fotoPlaceholder = null; // deja null para evitar íconos rotos; cámbialo si tienes fotos reales

        // 10 empresas
        for (int i = 1; i <= 10; i++) {
            String idEmpresa = "E-" + BolsaEmpleo.generadorIdEmpresa;
            Empresa empresa = new Empresa(idEmpresa, "RNC-" + (100000 + i), "Empresa " + i,
                    "809555" + String.format("%04d", i), "Direccion " + i,
                    TipoEmpresa.values()[i % TipoEmpresa.values().length], null);

            String idUser = "U-" + BolsaEmpleo.generadorIdUser;
            Usuario user = new Usuario(idUser, "empresa" + i, "1234", "empresa" + i + "@correo.com",
                    empresa, null, TipoUser.EMPRESA, fotoPlaceholder);
            empresa.setUser(user);

            bolsa.regUser(user);
            bolsa.regEmpresa(empresa);

            // 2 ofertas por empresa
            for (int o = 0; o < 2; o++) {
                String idOferta = "O-" + BolsaEmpleo.generadorIdOferta;
                Oferta oferta = new Oferta(idOferta,
                        Sexo.values()[random.nextInt(Sexo.values().length)],
                        TipoPersona.values()[random.nextInt(TipoPersona.values().length)],
                        "Puesto " + i + "-" + o, 2 + random.nextInt(3), random.nextBoolean(), random.nextBoolean(),
                        EstadoOferta.PENDIENTE, Jornada.values()[random.nextInt(Jornada.values().length)],
                        ciudades[random.nextInt(ciudades.length)], 20000f + random.nextInt(30000),
                        "Descripcion de la oferta", random.nextInt(5), empresa,
                        Modalidad.values()[random.nextInt(Modalidad.values().length)], LocalDate.now(),
                        AreaLaboral.values()[random.nextInt(AreaLaboral.values().length)]);

                bolsa.refOferta(oferta);
                empresa.agregarOferta(oferta);
            }
        }

        // 39 candidatos (10 empresas + 39 candidatos + 1 admin = 50 usuarios)
        for (int i = 1; i <= 39; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];

            String idUser = "U-" + BolsaEmpleo.generadorIdUser;
            Usuario user = new Usuario(idUser, "candidato" + i, "1234", "candidato" + i + "@correo.com",
                    null, null, TipoUser.CANDIDATO, fotoPlaceholder);

            String idPersona = "P-" + BolsaEmpleo.generadorIdPersona;
            TipoPersona tipo = TipoPersona.values()[i % TipoPersona.values().length];
            Persona persona;

            LocalDate fechaNacim = LocalDate.now().minusYears(20 + random.nextInt(25));

            if (tipo == TipoPersona.UNIVERSITARIO) {
                persona = new Universitario(idPersona, "CED-" + (400000 + i), nombre, apellido, fechaNacim,
                        "809666" + String.format("%04d", i), "Direccion candidato " + i,
                        Sexo.values()[random.nextInt(Sexo.values().length)], ciudades[random.nextInt(ciudades.length)],
                        random.nextBoolean(), random.nextBoolean(), false, user, random.nextInt(10), "Ingenieria en Sistemas");
            } else if (tipo == TipoPersona.TECNICO) {
                persona = new Tecnico(idPersona, "CED-" + (400000 + i), nombre, apellido, fechaNacim,
                        "809666" + String.format("%04d", i), "Direccion candidato " + i,
                        Sexo.values()[random.nextInt(Sexo.values().length)], ciudades[random.nextInt(ciudades.length)],
                        random.nextBoolean(), random.nextBoolean(), false, user, random.nextInt(10), "Electricidad");
            } else {
                persona = new Obrero(idPersona, "CED-" + (400000 + i), nombre, apellido, fechaNacim,
                        "809666" + String.format("%04d", i), "Direccion candidato " + i,
                        Sexo.values()[random.nextInt(Sexo.values().length)], ciudades[random.nextInt(ciudades.length)],
                        random.nextBoolean(), random.nextBoolean(), false, user, random.nextInt(10), "Construccion");
            }

            user.setPersona(persona);
            bolsa.regPersona(persona);
            bolsa.regUser(user);

            // dale solicitud activa a la mayoria (para que aparezcan en los matches)
            if (random.nextInt(10) < 8) {
                String idSolicitud = "S-" + BolsaEmpleo.generadorIdSolicitud;
                SolicitudEmpleo solicitud = new SolicitudEmpleo(idSolicitud, EstadoSolicitud.ACTIVA, persona,
                        LocalDate.now(), AreaLaboral.values()[random.nextInt(AreaLaboral.values().length)],
                        20000f + random.nextInt(30000), Modalidad.values()[random.nextInt(Modalidad.values().length)],
                        "Puesto deseado " + i, Jornada.values()[random.nextInt(Jornada.values().length)]);
                bolsa.regSolicitud(solicitud, persona);
            }
        }

        bolsa.guardarDatos();
        System.out.println("Listo: 50 usuarios generados (10 empresas + 39 candidatos + 1 admin), con ofertas y solicitudes.");
    }
}