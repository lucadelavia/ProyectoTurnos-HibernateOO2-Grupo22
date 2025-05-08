package negocio;

import datos.Especialidad;
import dao.EspecialidadDao;


public class EspecialidadABM {

	private final EspecialidadDao especialidadDao = new EspecialidadDao();
	
    public Especialidad altaEspecialidad(Especialidad e) {
    	
    	if (especialidadDao.traerPorNombre(e.getNombre()) != null) {
    		throw new IllegalArgumentException("ERROR: Ya existe una especialidad con ese nombre");
    	}
    	
    	especialidadDao.agregar(e);
    	
    	return e;
    }
    
    public void bajaEspecialidad(int id) {
    	
    	Especialidad actual = especialidadDao.traer(id);
    	
    	if (actual == null) {
    		throw new IllegalArgumentException("ERROR: No existe especialidad con ID: " + id);
    	}
    	
    	especialidadDao.eliminar(actual);;
    	
    }
    
    public Especialidad modificarEspecialidad(Especialidad e) {
        
    	Especialidad actual = especialidadDao.traer(e.getId());
    	
    	if (actual == null) {
    		throw new IllegalArgumentException("ERROR: No existe esa especialidad");
    	}
   
    	actual.setNombre(e.getNombre());
    	
    	especialidadDao.actualizar(actual);
    	
    	return actual;
    	
    }
    
    public Especialidad obtenerEspecialidadPorNombre(String nombre) {
    	
        Especialidad e = especialidadDao.traerPorNombre(nombre);
        
        if (e == null) {
            throw new IllegalArgumentException("ERROR: No existe especialidad con ese nombre");
        }
        
        return e;
    }

    
    public Especialidad obtenerEspecialidadPorId(int id) {
    	
        Especialidad e = especialidadDao.traer(id);
        
        if (e == null) {
        	throw new IllegalArgumentException("ERROR: No existe especialidad con ese ID");
        }
        
        return e;
    	
    }
    
}
