package test;

import negocio.UsuarioABM;
import datos.Usuario;
import negocio.DiasDeAtencionABM;
import negocio.EmpleadoABM;
import negocio.EspecialidadABM;
import negocio.EstablecimientoABM;
import datos.DiasDeAtencion;
import datos.Empleado;
import datos.Especialidad;
import datos.Establecimiento;
import negocio.ServicioABM;
import negocio.SucursalABM;
import datos.Servicio;
import datos.Sucursal;

import java.sql.Time;

public class TestSucursal {
    public static void main(String[] args) {

        SucursalABM sucursalABM = new SucursalABM();
        EstablecimientoABM establecimientoABM = new EstablecimientoABM();

        Establecimiento est = establecimientoABM.traer(1);
        
        Sucursal suc = sucursalABM.altaSucursal(
                "Av. Oeste 900", 
                "3811234111", 
                Time.valueOf("10:00:00"),  
                Time.valueOf("17:00:00"),
                1
            );
        System.out.println("Sucursal: " + suc);
        
        establecimientoABM.asociarSucursalAEstablecimiento(est.getId(), suc.getId());
        
        DiasDeAtencionABM diasDeAtencionABM = new DiasDeAtencionABM();

        DiasDeAtencion dia1 = diasDeAtencionABM.traer(1);
        System.out.println("Dia de atencion creado: " + dia1);
        
        sucursalABM.asociarDiaDeAtencion(suc.getId(), dia1.getId());
        Sucursal sucursalConDia = sucursalABM.traer(suc.getId());
        System.out.println("Sucursal con dia de atencion asociado: " + sucursalConDia);
    }
}