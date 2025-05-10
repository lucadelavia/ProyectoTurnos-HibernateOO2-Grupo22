package test;

import negocio.UsuarioABM;
import datos.Usuario;
import negocio.DiasDeAtencionABM;
import negocio.EmpleadoABM;
import negocio.EspecialidadABM;
import negocio.EstablecimientoABM;
import datos.Cliente;
import datos.DiasDeAtencion;
import datos.Empleado;
import datos.Especialidad;
import datos.Establecimiento;
import negocio.ServicioABM;
import negocio.SucursalABM;
import datos.Turno;
import negocio.TurnoABM;
import datos.Servicio;
import datos.Sucursal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestTurno {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente(
		    "Juan",
		    "Gómez",
		    "juan.gomez@exampl.com",
		    "Calle Falsa 123",
		    40111323,
		    true,
		    LocalDateTime.now(),
		    1001
		);
		
		UsuarioABM usuarioABM = new UsuarioABM();
		cliente = (Cliente) usuarioABM.altaUsuario(cliente);

		TurnoABM turnoABM = new TurnoABM();
		ServicioABM servicioABM = new ServicioABM();
		SucursalABM sucursalABM = new SucursalABM();

		Sucursal sucursal = sucursalABM.traer(1);
		Servicio servicio = servicioABM.obtenerServicioPorNombre("Atencion Medica");
		
		Turno turno = turnoABM.altaTurno(LocalDateTime.of(2025, 5, 10, 17, 0, 0), true, "1a2", servicio, cliente, sucursal);
		System.out.println(turno);

		List<Turno> turnos = turnoABM.obtenerTurnosPorSucursal(1);
		System.out.println(turnos);

		System.out.println("\n== Turnos por Sucursal ==");
		try {
			List<Turno> turnosSucursal = turnoABM.obtenerTurnosPorSucursal(sucursal.getId());
			turnosSucursal.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n== Turnos por Empleado ==");
		try {
			List<Turno> turnosEmpleado = turnoABM.obtenerTurnosPorEmpleado(1); // Asegurarse que exista
			turnosEmpleado.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n== Turnos por Fecha ==");
		try {
			List<Turno> turnosFecha = turnoABM.obtenerTurnosPorFecha(LocalDate.of(2025, 5, 10));
			turnosFecha.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n== Turnos por Rango de Fechas ==");
		try {
			List<Turno> turnosRango = turnoABM.obtenerTurnosPorRangoFechas(
				LocalDate.of(2025, 5, 1),
				LocalDate.of(2025, 5, 15)
			);
			turnosRango.forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
