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
	    establecimientoDao.agregar(est);
	    return est;
	}
}
	
//    public void bajaEstablecimiento(int id);
//    public Establecimiento modificarEstablecimiento(Establecimiento e);
//    public void asociarSucursalAEstablecimiento(int idEst, int idSuc);
//    public void removerSucursal(int idEst, int idSuc);