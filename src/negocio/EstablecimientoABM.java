package negocio;

import datos.Establecimiento;
import dao.EstablecimientoDao;

public class EstablecimientoABM {
	
	private final EstablecimientoDao establecimientoDao = new EstablecimientoDao ();
	
	public Establecimiento altaEstablecimiento(String nombre, String cuit, String direccion, String descripcion) {
		Establecimiento est = new Establecimiento(nombre, cuit, direccion, descripcion);
		
	    if (establecimientoDao.traerPorNombreEstablecimiento(nombre) != null) {
	        throw new IllegalArgumentException("Ya existe un establecimiento con el nombre: " + nombre);
	    }	    
	    if (establecimientoDao.traerPorCuitEstablecimiento(cuit) != null) {
	        throw new IllegalArgumentException("Ya existe un establecimiento con el CUIT: " + cuit);
	    }
	    establecimientoDao.agregar(est);
	    return est;
	}
	
	public void bajaEstablecimiento(int id) {
	    Establecimiento est = establecimientoDao.traer(id);
	    
	    if (est == null) {
	        throw new IllegalArgumentException("ERROR: no existe establecimiento con ID: " + id);
	    }
	    establecimientoDao.eliminar(est);
	}
	
	public Establecimiento modificarEstablecimiento(Establecimiento est) {
	    Establecimiento actual = establecimientoDao.traer(est.getId());
	    
	    if (actual == null) {
	        throw new IllegalArgumentException("ERROR: no existe establecimiento con ID: " + est.getId());
	    }
	    actual.setNombre(est.getNombre());
	    actual.setCuit(est.getCuit());
	    actual.setDireccion(est.getDireccion());
	    actual.setDescripcion(est.getDescripcion());
	    
	    establecimientoDao.actualizar(actual);

	    return actual; 
	}
}

//    public void asociarSucursalAEstablecimiento(int idEst, int idSuc);
//    public void removerSucursal(int idEst, int idSuc);