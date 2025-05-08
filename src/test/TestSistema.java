package test;

import negocio.UsuarioABM;
import datos.Usuario;
import negocio.EspecialidadABM;
import negocio.EstablecimientoABM;
import datos.Especialidad;
import datos.Establecimiento;
import negocio.ServicioABM;
import datos.Servicio;

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

        ServicioABM servicioABM = new ServicioABM();
        Servicio servicio = servicioABM.altaServicio("Atencion Medica", 1);
        System.out.println("Servicio Creado: " + servicio);

        servicio.setDuracion(2);
        Servicio servicio_modificado = servicioABM.modificarServicio(servicio);
        System.out.println("Servicio modificado: " + servicio_modificado);

        
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
    }
}
