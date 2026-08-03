package servidor;

import java.time.LocalDate;
import java.util.Random;

import logico.*;

public class SeedData {

    // Cada "rubro" agrupa: área laboral, puestos típicos, y el tipo de persona que normalmente aplica
    private static class Rubro {
        AreaLaboral area;
        String[] puestos;
        TipoPersona tipoTipico;

        Rubro(AreaLaboral area, String[] puestos, TipoPersona tipoTipico) {
            this.area = area;
            this.puestos = puestos;
            this.tipoTipico = tipoTipico;
        }
    }

    public static void main(String[] args) {
        BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
        bolsa.crearAdminPorDefecto();

        Random random = new Random(42); // semilla fija: mismos datos cada vez que lo corras

        String[] nombresM = {"Juan","Pedro","Luis","Jose","Carlos","David","Diego","Andres","Rafael","Miguel"};
        String[] nombresF = {"Maria","Ana","Carla","Rosa","Laura","Elena","Sofia","Valeria","Camila","Daniela"};
        String[] apellidos = {"Perez","Gomez","Diaz","Martinez","Lopez","Garcia","Reyes","Cruz","Torres","Ramirez","Ortiz","Vargas"};
        String[] ciudades = {"Santiago","Santo Domingo","Puerto Plata","La Vega","San Francisco de Macoris","Moca"};

        Rubro[] rubros = {
            new Rubro(AreaLaboral.TECNOLOGIA, new String[]{"Desarrollador de Software","Analista de Sistemas","Soporte Tecnico"}, TipoPersona.UNIVERSITARIO),
            new Rubro(AreaLaboral.SALUD, new String[]{"Enfermero", "Auxiliar de Enfermeria", "Tecnico de Laboratorio"}, TipoPersona.TECNICO),
            new Rubro(AreaLaboral.EDUCACION, new String[]{"Maestro de Primaria", "Profesor de Secundaria"}, TipoPersona.UNIVERSITARIO),
            new Rubro(AreaLaboral.CONSTRUCCION, new String[]{"Albañil", "Electricista", "Plomero"}, TipoPersona.OBRERO),
            new Rubro(AreaLaboral.INGENIERIA, new String[]{"Ingeniero Civil", "Ingeniero Industrial"}, TipoPersona.UNIVERSITARIO)
        };
        // Si AreaLaboral no tiene exactamente estos 6 valores, ajusta este arreglo
        // a los que sí existan en tu enum antes de correr esta clase.

        String fotoPlaceholder = null;

        // ---------- EMPRESAS + OFERTAS ----------
        // 10 empresas, cada una especializada en 1-2 rubros afines a su tipo
        for (int i = 1; i <= 10; i++) {
            Rubro rubroEmpresa = rubros[i % rubros.length];

            String idEmpresa = "E-" + BolsaEmpleo.generadorIdEmpresa;
            Empresa empresa = new Empresa(idEmpresa, "RNC-" + (130000000 + i), nombreEmpresaPara(rubroEmpresa, i),
                    "809555" + String.format("%04d", i), ciudades[i % ciudades.length] + ", Calle " + i,
                    TipoEmpresa.values()[i % TipoEmpresa.values().length], null);

            String idUser = "U-" + BolsaEmpleo.generadorIdUser;
            Usuario user = new Usuario(idUser, "empresa" + i, "1234", "contacto@empresa" + i + ".com",
                    empresa, null, TipoUser.EMPRESA, fotoPlaceholder);
            empresa.setUser(user);

            bolsa.regUser(user);
            bolsa.regEmpresa(empresa);

            // 2 ofertas reales del rubro de la empresa
            for (int o = 0; o < 2; o++) {
                String puesto = rubroEmpresa.puestos[o % rubroEmpresa.puestos.length];
                String idOferta = "O-" + BolsaEmpleo.generadorIdOferta;

                Oferta oferta = new Oferta(idOferta,
                		Sexo.values()[random.nextInt(Sexo.values().length)], // ver nota abajo si tu enum Sexo no tiene "indiferente"
                        rubroEmpresa.tipoTipico,
                        puesto,
                        1 + random.nextInt(3),
                        random.nextBoolean(),
                        random.nextBoolean(),
                        EstadoOferta.PENDIENTE,
                        Jornada.values()[random.nextInt(Jornada.values().length)],
                        ciudades[i % ciudades.length],
                        25000f + random.nextInt(20000),
                        "Se busca " + puesto + " con experiencia comprobable en " + rubroEmpresa.area,
                        1 + random.nextInt(4),
                        empresa,
                        Modalidad.values()[random.nextInt(Modalidad.values().length)],
                        LocalDate.now().minusDays(random.nextInt(30)),
                        rubroEmpresa.area);

                bolsa.refOferta(oferta);
                empresa.agregarOferta(oferta);
            }
        }

        // ---------- CANDIDATOS + SOLICITUDES ----------
        // 39 candidatos, distribuidos entre los mismos rubros que las ofertas,
        // así hay coincidencias reales de área/puesto/ciudad, no solo nombres genéricos.
        for (int i = 1; i <= 39; i++) {
            boolean esMujer = random.nextBoolean();
            String nombre = esMujer ? nombresF[random.nextInt(nombresF.length)] : nombresM[random.nextInt(nombresM.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];
            Sexo sexo = esMujer ? Sexo.FEMENINO : Sexo.MASCULINO;

            Rubro rubroCandidato = rubros[i % rubros.length];
            String ciudadCandidato = ciudades[i % ciudades.length];

            String idUser = "U-" + BolsaEmpleo.generadorIdUser;
            Usuario user = new Usuario(idUser, "candidato" + i, "1234", nombre.toLowerCase() + "." + i + "@correo.com",
                    null, null, TipoUser.CANDIDATO, fotoPlaceholder);

            String idPersona = "P-" + BolsaEmpleo.generadorIdPersona;
            LocalDate fechaNacim = LocalDate.now().minusYears(21 + random.nextInt(30));
            int yearsExp = random.nextInt(8);
            String telefono = "809666" + String.format("%04d", i);
            String direccion = ciudadCandidato + ", Sector " + (i % 15 + 1);

            Persona persona;
            TipoPersona tipoReal = rubroCandidato.tipoTipico;

            if (tipoReal == TipoPersona.UNIVERSITARIO) {
                persona = new Universitario(idPersona, "CED-" + (401000000 + i), nombre, apellido, fechaNacim,
                        telefono, direccion, sexo, ciudadCandidato, random.nextBoolean(), random.nextBoolean(),
                        false, user, yearsExp, carreraPara(rubroCandidato));
            } else if (tipoReal == TipoPersona.TECNICO) {
                persona = new Tecnico(idPersona, "CED-" + (401000000 + i), nombre, apellido, fechaNacim,
                        telefono, direccion, sexo, ciudadCandidato, random.nextBoolean(), random.nextBoolean(),
                        false, user, yearsExp, areaTecnicaPara(rubroCandidato));
            } else {
                persona = new Obrero(idPersona, "CED-" + (401000000 + i), nombre, apellido, fechaNacim,
                        telefono, direccion, sexo, ciudadCandidato, random.nextBoolean(), random.nextBoolean(),
                        false, user, yearsExp, habilidadPara(rubroCandidato));
            }

            user.setPersona(persona);
            bolsa.regPersona(persona);
            bolsa.regUser(user);

            // Solicitud activa alineada al mismo rubro y ciudad que sus ofertas afines,
            // para que el match tenga sentido (mismo puesto/area, ciudad, salario razonable)
            if (random.nextInt(10) < 8) {
                String puestoDeseado = rubroCandidato.puestos[i % rubroCandidato.puestos.length];
                String idSolicitud = "S-" + BolsaEmpleo.generadorIdSolicitud;

                SolicitudEmpleo solicitud = new SolicitudEmpleo(idSolicitud, EstadoSolicitud.ACTIVA, persona,
                        LocalDate.now().minusDays(random.nextInt(20)),
                        rubroCandidato.area,
                        22000f + random.nextInt(22000),
                        Modalidad.values()[random.nextInt(Modalidad.values().length)],
                        puestoDeseado,
                        Jornada.values()[random.nextInt(Jornada.values().length)]);

                bolsa.regSolicitud(solicitud, persona);
            }
        }

        bolsa.guardarDatos();
        System.out.println("Listo: 50 usuarios generados con datos alineados por rubro (área/puesto/ciudad).");
    }

    private static String nombreEmpresaPara(Rubro rubro, int i) {
        switch (rubro.area) {
            case TECNOLOGIA: return "TechSoft " + i;
            case SALUD: return "Clinica San Rafael " + i;
            case EDUCACION: return "Colegio Nuevo Horizonte " + i;
            case CONSTRUCCION: return "Constructora Del Valle " + i;
           
            case INGENIERIA: return "Grupo Ingenia " + i;
            default: return "Empresa " + i;
        }
    }

    private static String carreraPara(Rubro rubro) {
        switch (rubro.area) {
            case TECNOLOGIA: return "Ingenieria en Sistemas";
            case EDUCACION: return "Educacion Basica";
            case INGENIERIA: return "Ingenieria Civil";
            default: return "Administracion de Empresas";
        }
    }

    private static String areaTecnicaPara(Rubro rubro) {
        switch (rubro.area) {
            case SALUD: return "Enfermeria";
           
            default: return "Tecnico General";
        }
    }

    private static String habilidadPara(Rubro rubro) {
        switch (rubro.area) {
            case CONSTRUCCION: return "Albañileria, Electricidad basica";
            default: return "Trabajo manual general";
        }
    }
}