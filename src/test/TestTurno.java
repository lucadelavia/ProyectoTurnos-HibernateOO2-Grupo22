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

import java.time.LocalDateTime;

import java.util.List;

public class TestTurno {

	public static void main(String[] args) {
		TurnoABM turnoABM = new TurnoABM();
		UsuarioABM usuarioABM = new UsuarioABM();
		ServicioABM servicioABM = new ServicioABM();
		SucursalABM sucursalABM = new SucursalABM();

		Cliente cliente = (Cliente) usuarioABM.obtenerUsuarioPorId(3);
		Sucursal sucursal = sucursalABM.traer(1);
		Servicio servicio = servicioABM.obtenerServicioPorNombre("Atencion Medica");
		

		Turno turno = turnoABM.altaTurno(LocalDateTime.of(2025, 5, 10, 17, 0, 0), true, "1a2", servicio, cliente, sucursal);
		System.out.println(turno);

		List <Turno> turnos = turnoABM.obtenerTurnosPorSucursal(1);
		System.out.println(turnos);
	}
}
