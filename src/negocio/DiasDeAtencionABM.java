package negocio;

import datos.DiasDeAtencion;
import dao.DiasDeAtencionDao;

public class DiasDeAtencionABM {
    
    private final DiasDeAtencionDao diasDeAtencionDao = new DiasDeAtencionDao();

    public DiasDeAtencion altaDiaDeAtencion(String nombre) {
        DiasDeAtencion dia = new DiasDeAtencion(nombre);
        diasDeAtencionDao.agregar(dia);
        return dia;
    }

    public void bajaDiaDeAtencion(int id) {
        DiasDeAtencion dia = diasDeAtencionDao.traer(id);

        if (dia == null) {
            throw new IllegalArgumentException("ERROR: no existe un dia de atencion con ID: " + id);
        }
        diasDeAtencionDao.eliminar(dia);
    }

    public DiasDeAtencion modificarDiaDeAtencion(DiasDeAtencion dia) {
        DiasDeAtencion actual = diasDeAtencionDao.traer(dia.getId());

        if (actual == null) {
            throw new IllegalArgumentException("ERROR: no existe un dia de atencion con ID: " + dia.getId());
        }
        actual.setNombre(dia.getNombre());

        diasDeAtencionDao.actualizar(actual);
        return actual;
    }

    public DiasDeAtencion traer(int idDiaDeAtencion) {
        DiasDeAtencion dia = diasDeAtencionDao.traer(idDiaDeAtencion);
        
        if (dia == null) {
            throw new IllegalArgumentException("ERROR: no existe un dia de atencion con ID: " + idDiaDeAtencion);
        }
        return dia;
    }
}