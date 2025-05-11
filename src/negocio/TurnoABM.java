package negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import datos.Servicio;
import datos.Sucursal;
import datos.Turno;
import dao.TurnoDao;
import datos.Cliente;
import datos.Empleado;

public class TurnoABM {

    private final TurnoDao turnoDao = new TurnoDao();

    public Turno altaTurno(LocalDateTime fechaHora, boolean estadoActivo, String codigo, Servicio servicio, Cliente cliente, Empleado empleado, Sucursal sucursal){
		Turno t = new Turno(fechaHora, estadoActivo, codigo, servicio, cliente, sucursal, empleado);
		if(turnoDao.traer(t.getId()) != null){
			throw new IllegalArgumentException("Este turno ya existe.");
		}
		turnoDao.agregar(t);

		return t;
	}

    public Turno altaTurno(Turno turno){
		if(turnoDao.traer(turno.getId()) != null){
			throw new IllegalArgumentException("Este turno ya existe.");
		}
		turnoDao.agregar(turno);

		return turno;
	}

    public Turno obtenerTurnoPorId(int id){
        Turno t = turnoDao.traer(id);
        if(t == null){
			throw new IllegalArgumentException("ERROR: No existe turno con ID: " + id);
		}
        return t;
    }

    public void bajaTurno(Turno turno){
        turnoDao.eliminar(turno);
    }

    public Turno modificarServicio(Turno turno){
        Turno actual = turnoDao.traer(turno.getId());
        
        if (actual == null) {
        	throw new IllegalArgumentException("ERROR: No existe turno con ID: " + turno.getId());
        }
        
        actual.setFechaHora(turno.getFechaHora());
        actual.setEstado(turno.isEstado());
        actual.setCodigo(turno.getCodigo());
        actual.setServicio(turno.getServicio());
        actual.setCliente(turno.getCliente());

        turnoDao.actualizar(actual);
        
        return actual;
    }

    public List<Turno> obtenerTurnosPorSucursal(int idSucursal){
        List<Turno> turnos = turnoDao.obtenerTurnosPorSucursal(idSucursal);
        if(turnos == null){
            throw new IllegalArgumentException("ERROR: Esta sucursal no tiene turnos.");
        }
        return turnos;
        }

    public List<Turno> obtenerTurnosPorFecha(LocalDate fecha){
        List<Turno> turnos = turnoDao.obtenerTurnosPorFecha(fecha);
        if(turnos == null){
            throw new IllegalArgumentException("ERROR: No hay turnos en esta fecha: " + fecha);
        }
        return turnos;
        }

    public List<Turno> obtenerTurnosPorRangoFechas(LocalDate desde, LocalDate hasta){
        List<Turno> turnos = turnoDao.obtenerTurnosPorRangoFechas(desde, hasta);
        if(turnos == null){
            throw new IllegalArgumentException("ERROR: No hay turnos en estas fechas: " + desde + " " + hasta);
        }
        return turnos;
        }

}

