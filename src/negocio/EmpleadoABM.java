package negocio;

import datos.Empleado;
import datos.Especialidad;

import java.time.LocalDateTime;
import java.util.List;

import dao.EmpleadoDao;
import dao.EspecialidadDao;

public class EmpleadoABM {

	private final EmpleadoDao empleadoDao = new EmpleadoDao();
	private final EspecialidadDao especialidadDao = new EspecialidadDao();

	
	public Empleado altaEmpleado(String nombre, String apellido, String email, String direccion, int dni, boolean estado,
            LocalDateTime fechaAlta, int cuil, String matricula){

		Empleado e = new Empleado(nombre, apellido, email, direccion, dni, estado, fechaAlta, cuil, matricula);
		
		if (empleadoDao.traerPorDni(e.getDni()) != null) {
			throw new IllegalArgumentException("ERROR: Ya existe un empleado con ese DNI");
		}

		if (empleadoDao.traerPorCuil(e.getCuil()) != null) {
			throw new IllegalArgumentException("ERROR: Ya existe un empleado con ese CUIL");
		}

		empleadoDao.agregar(e);

		return e;
	}	
	
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
	
	public Empleado obtenerEmpleadoPorMatricula(String matricula) {
		
	    Empleado e = empleadoDao.traerPorMatricula(matricula);
	    
	    if (e == null) {
	        throw new IllegalArgumentException("No existe un empleado con esa matrícula");
	    }
	    
	    return e;
	}
	
	public void asignarEspecialidad(int idEmpleado, Especialidad esp) {
		
		Empleado e = empleadoDao.traer(idEmpleado);
		Especialidad especialidad = especialidadDao.traer(esp.getId());
		
	    if (e == null || especialidad == null) {
	        throw new IllegalArgumentException("Empleado o Especialidad no encontrado");
	    }
		
	    e.getLstEspecialidades().add(especialidad);
	    
	    empleadoDao.actualizar(e);
	    
	}
	
	public void removerEspecialidad(int idEmpleado, Especialidad esp) {
		
		Empleado e = empleadoDao.traer(idEmpleado);
		Especialidad especialidad = especialidadDao.traer(esp.getId());
		
	    if (e == null || especialidad == null) {
	        throw new IllegalArgumentException("Empleado o Especialidad no encontrado");
	    }
	    
	    e.getLstEspecialidades().remove(especialidad);
	    
	    empleadoDao.actualizar(e);
		
	}
	
	public List<Empleado> traerEmpleados() {
	    return empleadoDao.traerTodos();
	}





}