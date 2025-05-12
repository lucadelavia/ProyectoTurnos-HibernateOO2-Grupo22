package negocio;

import datos.Sucursal;
import datos.DiasDeAtencion;
import datos.Especialidad;
import dao.SucursalDao;

import java.sql.Time;
import java.util.List;

public class SucursalABM {
	
	private final SucursalDao sucursalDao = new SucursalDao();
	private DiasDeAtencionABM diasAtencionABM = new DiasDeAtencionABM();

    public Sucursal altaSucursal(String direccion, String telefono, Time horaApertura, Time horaCierre, int espacio) {
	    if (sucursalDao.traerPorTelefono(telefono) != null) {
	        throw new IllegalArgumentException("ERROR: ya existe una sucursal con el telefono: " + telefono);
	    }
	    Sucursal suc = new Sucursal(direccion, telefono, horaApertura, horaCierre, espacio);
	    sucursalDao.agregar(suc);
	    return suc;
	}
	
	public Sucursal altaSucursal(Sucursal suc) {
        if (sucursalDao.traerPorTelefono(suc.getTelefono()) != null) {
            throw new IllegalArgumentException("ERROR: ya existe una sucursal con el telefono: " + suc.getTelefono());
        }
        sucursalDao.agregar(suc);
        return suc;
    }

    public void bajaSucursal(int id) {
        Sucursal suc = sucursalDao.traer(id);

        if (suc == null) {
            throw new IllegalArgumentException("ERROR: no existe sucursal con ID: " + id);
        }

        sucursalDao.eliminar(suc);
    }

    public Sucursal modificarSucursal(Sucursal suc) {
        Sucursal actual = sucursalDao.traer(suc.getId());

        if (actual == null) {
            throw new IllegalArgumentException("ERROR: no existe sucursal con ID: " + suc.getId());
        }
        actual.setDireccion(suc.getDireccion());
        actual.setTelefono(suc.getTelefono());
        actual.setHoraApertura(suc.getHoraApertura());
        actual.setHoraCierre(suc.getHoraCierre());

        sucursalDao.actualizar(actual);
        return actual;
    }

    public Sucursal traer(int idSucursal) {
        Sucursal suc = sucursalDao.traer(idSucursal);
        
        if (suc == null) {
            throw new IllegalArgumentException("ERROR: no existe una sucursal con ID: " + idSucursal);
        }
        return suc;
    }
    
    public void asociarDiaDeAtencion(int idSucursal, int idDiasAtencion) {
        Sucursal suc = sucursalDao.traer(idSucursal);
        
        if (suc == null) {
            throw new IllegalArgumentException("ERROR: no existe una sucursal con ID: " + idSucursal);
        }
        DiasDeAtencion dia = diasAtencionABM.traer(idDiasAtencion);
        
        if (dia == null) {
            throw new IllegalArgumentException("ERROR: no existe un dia de atencion con ID: " + idDiasAtencion);
        }
        if (!suc.getLstDiasDeAtencion().contains(dia)) {
            suc.getLstDiasDeAtencion().add(dia);
            sucursalDao.actualizar(suc);
        } else {
            System.out.println("La sucursal ya tiene asignado ese dia de atencion.");
        }
    }

    public void removerDiaDeAtencion(int idSucursal, int idDiasAtencion) {
        Sucursal suc = sucursalDao.traer(idSucursal);
        
        if (suc == null) {
            throw new IllegalArgumentException("ERROR: no existe una sucursal con ID: " + idSucursal);
        }
        DiasDeAtencion dia = diasAtencionABM.traer(idDiasAtencion);
        
        if (dia == null) {
            throw new IllegalArgumentException("ERROR: no existe un dia de atencion con ID: " + idDiasAtencion);
        }
        if (suc.getLstDiasDeAtencion().contains(dia)) {
            suc.getLstDiasDeAtencion().remove(dia);
            sucursalDao.actualizar(suc);
        } else {
            System.out.println("La sucursal no tiene asignado ese dia de atencion.");
        }
    }
    

	public void asociarEspecialidad(int idSucursal, Especialidad especialidad) {
	    Sucursal suc = sucursalDao.traer(idSucursal);
	    
	    if (suc == null) {
	        throw new IllegalArgumentException("ERROR: no existe una sucursal con ID: " + idSucursal);
	    }
	
	    if (!suc.getLstEspecialidad().contains(especialidad)) {
	        suc.getLstEspecialidad().add(especialidad);
	        sucursalDao.actualizar(suc);
	    } else {
	        System.out.println("La sucursal ya tiene asignada esa especialidad.");
	    }
	}
	
	public void removerEspecialidad(int idSucursal, Especialidad especialidad) {
	    Sucursal suc = sucursalDao.traer(idSucursal);
	
	    if (suc == null) {
	        throw new IllegalArgumentException("ERROR: no existe una sucursal con ID: " + idSucursal);
	    }
	
	    if (suc.getLstEspecialidad().contains(especialidad)) {
	        suc.getLstEspecialidad().remove(especialidad);
	        sucursalDao.actualizar(suc);
	    } else {
	        System.out.println("La sucursal no tiene esa especialidad.");
	    }
	}
	
	public List<Sucursal> traerSucursales() {
	    return sucursalDao.traerTodos();
	}
    
}