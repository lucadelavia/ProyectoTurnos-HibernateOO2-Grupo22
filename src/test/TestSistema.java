package test;

import negocio.UsuarioABM;
import datos.Usuario;
import negocio.DiasDeAtencionABM;
import negocio.EspecialidadABM;
import negocio.EstablecimientoABM;
import datos.DiasDeAtencion;
import datos.Especialidad;
import datos.Establecimiento;
import negocio.ServicioABM;
import negocio.SucursalABM;
import datos.Servicio;
import datos.Sucursal;

import java.sql.Time;

public class TestSistema {
    public static void main(String[] args) {
    	
        UsuarioABM usuarioABM = new UsuarioABM();

        Usuario usuario = usuarioABM.altaUsuario(
            "Luca", 
            "De la Via", 
            "luca.dlv@example.com", 
            "Av. Siempre Viva 742", 
            38123456
        );
        System.out.println("Usuario creado: " + usuario);

        Usuario porId = usuarioABM.obtenerUsuarioPorId(usuario.getId());
        System.out.println("Recuperado por ID: " + porId);

        Usuario porDni = usuarioABM.obtenerUsuarioPorDNI(usuario.getDni());
        System.out.println("Recuperado por DNI: " + porDni);

        Usuario porEmail = usuarioABM.obtenerUsuarioPorEmail(usuario.getEmail());
        System.out.println("Recuperado por email: " + porEmail);

        usuario.setDireccion("Calle Real 1234");
        Usuario modificado = usuarioABM.modificarUsuario(usuario);
        System.out.println("Usuario modificado: " + modificado);

        EspecialidadABM abm = new EspecialidadABM();

        Especialidad e = new Especialidad("Cardiología");
        e = abm.altaEspecialidad(e);
        System.out.println("Especialidad creada: " + e);

        Especialidad porId1 = abm.obtenerEspecialidadPorId(e.getId());
        System.out.println("Recuperado por ID: " + porId1);

        Especialidad porNombre = abm.obtenerEspecialidadPorNombre("Cardiología");
        System.out.println("Recuperado por nombre: " + porNombre);

        e.setNombre("Neurología");
        Especialidad mod = abm.modificarEspecialidad(e);
        System.out.println("Especialidad modificada: " + mod);

//        ServicioABM servicioABM = new ServicioABM();
//        Servicio servicio = servicioABM.altaServicio("Atencion Medica", 1);
//        System.out.println("Servicio Creado: " + servicio);
//
//        servicio.setDuracion(2);
//        Servicio servicio_modificado = servicioABM.modificarServicio(servicio);
//        System.out.println("Servicio modificado: " + servicio_modificado);

        
        //        abm.bajaEspecialidad(mod.getId());
//        System.out.println("Especialidad eliminada con ID: " + mod.getId());
        
        EstablecimientoABM establecimientoABM = new EstablecimientoABM();

        Establecimiento est = establecimientoABM.altaEstablecimiento(
            "Supermercado Central",
            "20-12345678-9",
            "Av. Siempre Viva 247",
            "Supermercado de barrio"
        );
        System.out.println("Establecimiento creado: " + est);
        
        est.setNombre("Supermercado Norte");
        est.setDireccion("Av. Siempre Viva 248");
        est.setDescripcion("Nuevo supermercado de barrio");

        Establecimiento estModificado = establecimientoABM.modificarEstablecimiento(est);
        System.out.println("Establecimiento modificado: " + estModificado);
        
        SucursalABM sucursalABM = new SucursalABM();
        
        Sucursal suc = sucursalABM.altaSucursal(
                "Av. Oeste 500", 
                "3811234567", 
                Time.valueOf("09:00:00"),  
                Time.valueOf("18:00:00")
            );
        System.out.println("Sucursal: " + suc);
        
        establecimientoABM.asociarSucursalAEstablecimiento(est.getId(), suc.getId());
        
        Sucursal sucRecuperada = sucursalABM.traer(suc.getId());
        System.out.println("Establecimiento con sucursal asociada: " + sucRecuperada.getEstablecimiento());
        
        suc.setDireccion("Av. Este 500");
        Sucursal sucModificada = sucursalABM.modificarSucursal(suc);
        System.out.println("Sucursal modificada: " + sucModificada);
        
        // establecimientoABM.removerSucursalDeEstablecimiento(est.getId(), suc.getId());
        // Establecimiento estSinSucursal = establecimientoABM.traer(est.getId());
        // System.out.println("Establecimiento sin sucursal: " + estSinSucursal);
        // Sucursal sucDesasociada = sucursalABM.traer(suc.getId());
        // System.out.println("Sucursal: " + sucDesasociada);
        
        DiasDeAtencionABM diasDeAtencionABM = new DiasDeAtencionABM();

        DiasDeAtencion dia1 = diasDeAtencionABM.altaDiaDeAtencion("Lunes");
        System.out.println("Dia de atencion creado: " + dia1);

        dia1.setNombre("Martes");
        DiasDeAtencion diaModificado = diasDeAtencionABM.modificarDiaDeAtencion(dia1);
        System.out.println("Dia de atencion modificado: " + diaModificado);
    }
}