package test;

import negocio.UsuarioABM;
import negocio.ServicioABM;
import negocio.SucursalABM;
import negocio.TurnoABM;
import negocio.EmpleadoABM;

import datos.Cliente;
import datos.Servicio;
import datos.Sucursal;
import datos.Turno;
import datos.Empleado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestTurno {

    public static void main(String[] args) {

        // Crear y guardar un cliente
        Cliente cliente = new Cliente(
            "Juan",
            "Gómez",
            "yuangomez@example.com.",
            "Calle Falsa 123",
            12345622,
            true,
            LocalDateTime.now(),
            1001
        );
        UsuarioABM usuarioABM = new UsuarioABM();
        cliente = (Cliente) usuarioABM.altaUsuario(cliente);

        // Obtener sucursal y servicio ya existentes
        SucursalABM sucursalABM = new SucursalABM();
        Sucursal sucursal = sucursalABM.traer(1); // Asegurate que exista

        ServicioABM servicioABM = new ServicioABM();
        Servicio servicio = servicioABM.obtenerServicioPorNombre("Atencion Medica"); // Asegurate que exista

        // Crear y guardar un empleado
        EmpleadoABM empleadoABM = new EmpleadoABM();
        Empleado empleado = new Empleado(
            "Carlos",
            "Pérez",
            "carlo.perez@example.com",
            "Av. Trabajo 456",
            33838999,
            true,
            LocalDateTime.now(),
            871234297,
            "MAT1234"
        );
        empleado = empleadoABM.altaEmpleado(empleado);

        // Crear y guardar un turno
        TurnoABM turnoABM = new TurnoABM();
        Turno turno = new Turno(
            LocalDateTime.of(2025, 5, 10, 17, 0),
            true,
            "1a2",
            servicio,
            cliente,
            sucursal,
            empleado
        );
        turno = turnoABM.altaTurno(turno);
        System.out.println("Turno creado:\n" + turno);

        // === Consultas ===

        System.out.println("\n== Turnos por Sucursal ==");
        try {
            List<Turno> turnosSucursal = turnoABM.obtenerTurnosPorSucursal(sucursal.getId());
            turnosSucursal.forEach(System.out::println);
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
