package negocio;

import java.util.List;

import datos.Empleado;
import datos.Turno;
import dao.EmpleadoDao;
import dao.TurnoDao;
import org.hibernate.SessionFactory;
import dao.HibernateUtil;

public class EmpleadoABM {

	private final EmpleadoDao empleadoDao = new EmpleadoDao();

	public Empleado altaEmpleado(Empleado e){

		if (empleadoDao.traerPorDni(e.getDni()) != null) {
			throw new IllegalArgumentException("ERROR: Ya existe un empleado con ese DNI");
		}

		if (empleadoDao.traerPorCuil(e.getCuil()) != null) {
			throw new IllegalArgumentException("ERROR: Ya existe un empleado con ese CUIL");
		}

		empleadoDao.agregar(e);

		return e;
	}	

	public void bajaEmpleado(int id) {

		empleadoDao.eliminar(obtenerEmpleadoPorId(id));

	}
	
	   public Empleado modificarEmpleado(Empleado e) {
	    	
	        Empleado actual = obtenerEmpleadoPorId(e.getId());
	        
	        actual.setNombre(e.getNombre());
	        actual.setApellido(e.getApellido());
	        actual.setEmail(e.getEmail());
	        actual.setDireccion(e.getDireccion());
	        actual.setCuil(e.getCuil());
	        actual.setMatricula(e.getMatricula());
	        
	        empleadoDao.actualizar(actual);
	        
	        return actual;
	    }

	public Empleado obtenerEmpleadoPorId(int id){

		Empleado e = empleadoDao.traer(id);

		if (e == null) {
			throw new IllegalArgumentException("ERROR: No existe el empleado solicitado");
		}

		return e;

	}
	
	public Empleado obtenerEmpleadoPorCuil(int cuil) {
		
		Empleado e = empleadoDao.traerPorCuil(cuil);

		if (e == null) {
			throw new IllegalArgumentException("ERROR: No existe el empleado solicitado");
		}

		return e;
		
	}
	

	//  public void asignarEspecialidad(int idEmpleado, Especialidad esp);
	//  public void removerEspecialidad(int idEmpleado, Especialidad esp);
	//  public List<Especialidad> listarEspecialidades(int idEmpleado);
	//
	//  public List<Turno> verTurnosAsignados(int idEmpleado);

}