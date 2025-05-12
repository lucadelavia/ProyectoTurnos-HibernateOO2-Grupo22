package negocio;

import datos.Servicio;

import java.util.List;

import dao.ServicioDao;

public class ServicioABM {

    private final ServicioDao servicioDao = new ServicioDao();

    public Servicio altaServicio(String nombreServicio, int duracion){
		Servicio s = new Servicio(nombreServicio, duracion);
		if(servicioDao.traerPorNombreServicio(s.getNombreServicio()) != null){
			throw new IllegalArgumentException("Este Servicio ya existe.");
		}
		servicioDao.agregar(s);

		return s;
	}

    public Servicio altaServicio(Servicio servicio){
		if(servicioDao.traerPorNombreServicio(servicio.getNombreServicio()) != null){
			throw new IllegalArgumentException("Este Servicio ya existe.");
		}
		servicioDao.agregar(servicio);

		return servicio;
	}

    public Servicio obtenerServicioPorNombre(String nombreServicio){
        Servicio s = servicioDao.traerPorNombreServicio(nombreServicio);
        if(s == null){
			throw new IllegalArgumentException("ERROR: No existe servicio con Nombre: " + nombreServicio);
		}
        return s;
    }

    public Servicio obtenerServicioPorId(int id){
        Servicio s = servicioDao.traer(id);
        if(s == null){
			throw new IllegalArgumentException("ERROR: No existe servicio con ID: " + id);
		}
        return s;
    }

    public void bajaServicio(Servicio servicio){
        servicioDao.eliminar(servicio);
    }

    public Servicio modificarServicio(Servicio servicio){
        Servicio actual = servicioDao.traer(servicio.getId());
        
        if (actual == null) {
        	throw new IllegalArgumentException("ERROR: No existe servicio con ID: " + servicio.getId());
        }
        
        actual.setNombreServicio(servicio.getNombreServicio());
        actual.setDuracion(servicio.getDuracion());

        servicioDao.actualizar(actual);
        
        return actual;
    }
    
    public List<Servicio> traerServicios() {
        return servicioDao.traerTodos();
    }
    
}
