package negocio;

import datos.Sucursal;
import dao.SucursalDao;

import java.sql.Time;

public class SucursalABM {
	
	private final SucursalDao sucursalDao = new SucursalDao();

    public Sucursal altaSucursal(String direccion, String telefono, Time horaApertura, Time horaCierre) {
        Sucursal suc = new Sucursal(direccion, telefono, horaApertura, horaCierre);
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
}