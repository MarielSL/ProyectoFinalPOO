package logico;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {

		BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();

		Empresa empresa1 = new Empresa("E1", "130-12345-6", "Grupo Ramos", "8095551234", "Santiago", TipoEmpresa.TECNOLOGIA,null);
		bolsa.regEmpresa(empresa1);

		Oferta oferta1 = new Oferta("O1", Sexo.FEMENINO, TipoPersona.UNIVERSITARIO, "Analista de Sistemas", 2, false,
				true, EstadoOferta.PENDIENTE, Jornada.MATUTINA, "Santiago", 45000f, "Analista jr", 1, empresa1,
				Modalidad.PRESENCIAL);
		empresa1.agregarOferta(oferta1);
		bolsa.refOferta(oferta1);

		Usuario user1 = new Usuario("U1", "manuela02", "1234", "manuela@gmail.com", null, null, TipoUser.CANDIDATO);
		bolsa.regUser(user1);
		Universitario candidato1 = new Universitario("P1", "40212345678", "Manuela", "Gomez", LocalDate.of(2004, 5, 20),
				"8095559876", "Santiago", Sexo.FEMENINO, "Santiago", true, true, false, user1, "Ing. Sistemas");
		bolsa.regPersona(candidato1);

		Usuario user2 = new Usuario("U2", "juan22", "1234", "juan@gmail.com", null, null, TipoUser.CANDIDATO);
		bolsa.regUser(user2);
		Tecnico candidato2 = new Tecnico("P2", "40298765432", "Juan", "Perez", LocalDate.of(2001, 3, 10), "8095551111",
				"Santo Domingo", Sexo.MASCULINO, "Santo Domingo", false, false, false, user2, "Electricidad", 0);
		bolsa.regPersona(candidato2);

		SolicitudEmpleo solicitud1 = new SolicitudEmpleo("S1", "Analista de Sistemas", "Tecnologia", "Santiago",
				Modalidad.PRESENCIAL, Jornada.MATUTINA, EstadoSolicitud.PENDIENTE, candidato1, oferta1, 0f, true, 2,
				LocalDate.now(), true);
		bolsa.regSolicitud("O1", solicitud1);
		solicitud1.setPorcentajeCoincidencia(bolsa.calcCoincidencia("O1", "S1"));

		SolicitudEmpleo solicitud2 = new SolicitudEmpleo("S2", "Electricista", "Mantenimiento", "Santo Domingo",
				Modalidad.REMOTO, Jornada.NOCTURNA, EstadoSolicitud.PENDIENTE, candidato2, oferta1, 0f, false, 0,
				LocalDate.now(), false);
		bolsa.regSolicitud("O1", solicitud2);
		solicitud2.setPorcentajeCoincidencia(bolsa.calcCoincidencia("O1", "S2"));

		System.out.println("Solicitudes para la oferta " + oferta1.getPuesto() + ":");
		System.out.println(candidato1.getNombre() + " - " + solicitud1.getPorcentajeCoincidencia() + "%");
		System.out.println(candidato2.getNombre() + " - " + solicitud2.getPorcentajeCoincidencia() + "%");

		solicitud1.setEstado(EstadoSolicitud.ACEPTADA);
		solicitud2.setEstado(EstadoSolicitud.RECHAZADA);


	}

}