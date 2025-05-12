package test;

import negocio.*;
import datos.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestSistema {
	public static void main(String[] args) {
		System.out.println("=== INICIO DE PRUEBAS MANUALES ===");

		LocalDate today = LocalDate.now(); // Fecha base dinámica

		// Instancias ABM
		ClienteABM clienteABM = new ClienteABM();
		EmpleadoABM empleadoABM = new EmpleadoABM();
		EspecialidadABM especialidadABM = new EspecialidadABM();
		ServicioABM servicioABM = new ServicioABM();
		SucursalABM sucursalABM = new SucursalABM();
		TurnoABM turnoABM = new TurnoABM();
		DiasDeAtencionABM diasABM = new DiasDeAtencionABM();
		EstablecimientoABM establecimientoABM = new EstablecimientoABM();
		UsuarioABM usuarioABM = new UsuarioABM();

		// Alta Sucursal y Establecimiento
		Time apertura = Time.valueOf("08:00:00");
		Time cierre = Time.valueOf("16:00:00");
		Sucursal suc = sucursalABM.altaSucursal("Mitre 123", "1122334455", apertura, cierre, 10);
		DiasDeAtencion dia = diasABM.altaDiaDeAtencion("Lunes");
		sucursalABM.asociarDiaDeAtencion(suc.getId(), dia.getId());
		Establecimiento est = establecimientoABM.altaEstablecimiento("Clínica Norte", "20-12345678-9", "San Martín 456",
				"Clínica general");
		establecimientoABM.asociarSucursalAEstablecimiento(est.getId(), suc.getId());

		// Especialidades
		Especialidad esp1 = especialidadABM.altaEspecialidad(new Especialidad("Cardiología"));
		Especialidad esp2 = especialidadABM.altaEspecialidad(new Especialidad("Dermatología"));
		Especialidad esp3 = especialidadABM.altaEspecialidad(new Especialidad("Neurología"));

		sucursalABM.asociarEspecialidad(suc.getId(), esp1);
		sucursalABM.asociarEspecialidad(suc.getId(), esp2);
		sucursalABM.asociarEspecialidad(suc.getId(), esp3);

		// Clientes
		Cliente c1 = clienteABM.altaCliente("Juan", "Perez", "juan@mail.com", "Calle 1", 10000001, true,
				LocalDateTime.now(), 1);
		Cliente c2 = clienteABM.altaCliente("Maria", "Lopez", "maria@mail.com", "Calle 2", 10000002, true,
				LocalDateTime.now(), 2);
		Cliente c3 = clienteABM.altaCliente("Carlos", "Diaz", "carlos@mail.com", "Calle 3", 10000003, true,
				LocalDateTime.now(), 3);

		// Empleados
		Empleado e1 = empleadoABM.altaEmpleado("Ana", "Torres", "ana@mail.com", "Calle 4", 20000001, true,
				LocalDateTime.now(), 201000001, "MAT-A");
		Empleado e2 = empleadoABM.altaEmpleado("Luis", "Mendez", "luis@mail.com", "Calle 5", 20000002, true,
				LocalDateTime.now(), 201000002, "MAT-B");
		Empleado e3 = empleadoABM.altaEmpleado("Laura", "Fernandez", "laura@mail.com", "Calle 6", 20000003, true,
				LocalDateTime.now(), 201000003, "MAT-C");

		// Asignar especialidades a empleados
		empleadoABM.asignarEspecialidad(e1.getId(), esp1);
		empleadoABM.asignarEspecialidad(e2.getId(), esp2);
		empleadoABM.asignarEspecialidad(e3.getId(), esp3);

		// Servicios
		Servicio s1 = servicioABM.altaServicio("Consulta Cardiológica", 30);
		Servicio s2 = servicioABM.altaServicio("Consulta Dermatológica", 25);
		Servicio s3 = servicioABM.altaServicio("Consulta Neurológica", 40);

		// Turnos
		Turno t1 = turnoABM.altaTurno(today.plusDays(1).atTime(9, 0), true, "T001", s1, c1, e1, suc);
		@SuppressWarnings("unused")
		Turno t2 = turnoABM.altaTurno(today.plusDays(2).atTime(10, 0), true, "T002", s2, c2, e2, suc);
		@SuppressWarnings("unused")
		Turno t3 = turnoABM.altaTurno(today.plusDays(3).atTime(11, 0), true, "T003", s3, c3, e3, suc);

		// === MODIFICACIONES ===
		System.out.println("\n=== MODIFICACIONES ===");

		// Cliente
		c1.setDireccion("Nueva Dirección 123");
		clienteABM.modificarCliente(c1);
		System.out.println("Cliente modificado: " + c1.getNombre() + " -> " + c1.getDireccion());

		// Empleado
		e1.setEmail("nuevo.email@clinica.com");
		empleadoABM.modificarEmpleado(e1);
		System.out.println("Empleado modificado: " + e1.getNombre() + " -> " + e1.getEmail());

		// Especialidad
		esp1.setNombre("Cardiología Avanzada");
		especialidadABM.modificarEspecialidad(esp1);
		System.out.println("Especialidad modificada: " + esp1.getNombre());

		// Servicio
		s1.setNombreServicio("Consulta Cardiológica Pro");
		s1.setDuracion(45);
		servicioABM.modificarServicio(s1);
		System.out.println("Servicio modificado: " + s1.getNombreServicio() + " - " + s1.getDuracion() + " min");

		// Sucursal
		suc.setTelefono("999999999");
		sucursalABM.modificarSucursal(suc);
		System.out.println("Sucursal modificada: nuevo teléfono " + suc.getTelefono());

		// Establecimiento
		est.setDescripcion("Clínica integral con atención 24 hs");
		establecimientoABM.modificarEstablecimiento(est);
		System.out.println("Establecimiento modificado: " + est.getDescripcion());

		// Turno
		t1.setCodigo("T001-EDIT");
		t1.setEstado(false);
		turnoABM.modificarServicio(t1);
		System.out.println("Turno modificado: código " + t1.getCodigo() + ", estado " + t1.isEstado());

		// Día de atención
		dia.setNombre("Lunes extendido");
		diasABM.modificarDiaDeAtencion(dia);
		System.out.println("Día de atención modificado: " + dia.getNombre());

		System.out.println("\n=== CONSULTA GENERAL DE TODAS LAS ENTIDADES ===");

		System.out.println("Clientes:");
		clienteABM.traerClientes().forEach(System.out::println);

		System.out.println("Empleados:");
		empleadoABM.traerEmpleados().forEach(System.out::println);

		System.out.println("Especialidades:");
		especialidadABM.traerEspecialidades().forEach(System.out::println);

		System.out.println("Servicios:");
		servicioABM.traerServicios().forEach(System.out::println);

		System.out.println("Sucursales:");
		sucursalABM.traerSucursales().forEach(System.out::println);

		System.out.println("Establecimientos:");
		establecimientoABM.traerEstablecimientos().forEach(System.out::println);

		System.out.println("Días de Atención:");
		diasABM.traerDiasDeAtencion().forEach(System.out::println);

		System.out.println("Turnos:");
		turnoABM.traerTurnos().forEach(System.out::println);

		System.out.println("\n=== CONSULTAS POR OBJETO ===");

		// Turnos por Cliente
		System.out.println("Turnos del cliente " + c1.getNombre() + ":");
		turnoABM.obtenerTurnosPorCliente(c1.getId()).forEach(System.out::println);

		// Turnos por Empleado
		System.out.println("Turnos del empleado " + e1.getNombre() + ":");
		turnoABM.obtenerTurnosPorEmpleado(e1.getId()).forEach(System.out::println);

		// Turnos por Sucursal
		System.out.println("Turnos en la sucursal " + suc.getDireccion() + ":");
		turnoABM.obtenerTurnosPorSucursal(suc.getId()).forEach(System.out::println);

		// Turnos por Servicio
		System.out.println("Turnos para el servicio " + s1.getNombreServicio() + ":");
		turnoABM.obtenerTurnosPorServicio(s1.getId()).forEach(System.out::println);

		System.out.println("\n=== CONSULTAS POR ATRIBUTOS DE LA SUBCLASE ===");

		// 1. Buscar cliente por número de cliente
		try {
			Cliente clientePorNro = clienteABM.traerClienteNroCliente(c1.getNroCliente());
			System.out.println("Cliente por nroCliente: " + clientePorNro);
		} catch (Exception e) {
			System.out.println("No se encontró cliente con ese nroCliente");
		}

		// 2. Buscar empleado por matrícula
		try {
			Empleado empPorMatricula = empleadoABM.obtenerEmpleadoPorMatricula(e1.getMatricula());
			System.out.println("Empleado por matrícula: " + empPorMatricula);
		} catch (Exception e) {
			System.out.println("No se encontró empleado con esa matrícula");
		}

		// 3. Buscar empleado por CUIL
		try {
			Empleado empPorCuil = empleadoABM.obtenerEmpleadoPorCuil(e1.getCuil());
			System.out.println("Empleado por CUIL: " + empPorCuil);
		} catch (Exception e) {
			System.out.println("No se encontró empleado con ese CUIL");
		}

		System.out.println("\n=== CONSULTA ENTRE INTERVALOS DE FECHAS ===");

		// 4. Buscar Usuarios creados en una fecha activos/inactivos
		List<Usuario> usuarios = usuarioABM.obtenerUsuariosPorFecha(today, true);
		System.out.println("Usuarios creados el " + today + ": " + usuarios);

		// 5. Buscar Usuarios creados entre fechas
		usuarios = usuarioABM.obtenerUsuariosPorRangoFechas(today, today);
		System.out.println("Usuarios creados entre " + today + " y " + today + ": " + usuarios);

		// 6. Buscar Turnos en una fecha activos/cancelados
		List<Turno> turnos = turnoABM.obtenerTurnosPorRangoFechas(today.plusDays(2), today.plusDays(3));
		System.out.println("Turnos entre " + today.plusDays(2) + " y " + today.plusDays(3) + ": " + turnos);

		// 7. Buscar Turnos entre dos fechas
		turnos = turnoABM.obtenerTurnosPorRangoFechas(LocalDate.of(2025, 5, 13), LocalDate.of(2025, 5, 14));
		System.out.println(
				"Turnos entre: " + LocalDate.of(2025, 5, 13) + " y " + LocalDate.of(2025, 5, 14) + ": " + turnos);

		System.out.println("\n=== CONSULTAS POR FECHA Y ATRIBUTO DE CLASE ===");

		// 1. Turnos por Fecha y Sucursal
		System.out.println("Turnos del " + today.plusDays(1) + " en sucursal " + suc.getDireccion() + ":");
		turnoABM.traerTurnosPorFechaYSucursal(today.plusDays(1), suc.getId()).forEach(System.out::println);

		// 2. Turnos por Fecha y Servicio
		System.out.println("Turnos del " + today.plusDays(1) + " para servicio " + s1.getNombreServicio() + ":");
		turnoABM.traerTurnosPorFechaYServicio(today.plusDays(1), s1.getId()).forEach(System.out::println);

		// 3. Turnos por Fecha y Cliente
		System.out.println("Turnos del " + today.plusDays(1) + " para cliente " + c1.getNombre() + ":");
		turnoABM.traerTurnosPorFechaYCliente(today.plusDays(1), c1.getId()).forEach(System.out::println);

		// 4. Turnos por Fecha y Empleado
		System.out.println("Turnos del " + today.plusDays(1) + " para empleado " + e1.getNombre() + ":");
		turnoABM.traerTurnosPorFechaYEmpleado(today.plusDays(1), e1.getId()).forEach(System.out::println);

		// DESCONMENTAR SI SE QUIERE TESTEAR / LOS DEMAS METODOS DE BAJA SE ENCUENTRAN
		// EN SUS CLASES
		// === BAJAS ===
//        System.out.println("\n=== BAJAS ===");
//
//     // 1. Eliminar turno
//        turnoABM.bajaTurno(t3);
//        System.out.println("Turno dado de baja: " + t3.getCodigo());
//
//        // 2. Eliminar servicio
//        servicioABM.bajaServicio(s3);
//        System.out.println("Servicio dado de baja: " + s3.getNombreServicio());
//
//        // 3. Eliminar cliente
//        clienteABM.bajaCliente(c3.getId());
//        System.out.println("Cliente dado de baja: " + c3.getNombre());

		System.out.println("\n=== FIN DE PRUEBAS ===");
	}
}
