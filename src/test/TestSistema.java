package test;

import negocio.*;
import datos.*;

import java.sql.Time;
import java.time.LocalDateTime;

public class TestSistema {
    public static void main(String[] args) {
        System.out.println("=== INICIO DE PRUEBAS MANUALES ===");

        // Instancias ABM
        ClienteABM clienteABM = new ClienteABM();
        EmpleadoABM empleadoABM = new EmpleadoABM();
        EspecialidadABM especialidadABM = new EspecialidadABM();
        ServicioABM servicioABM = new ServicioABM();
        SucursalABM sucursalABM = new SucursalABM();
        TurnoABM turnoABM = new TurnoABM();
        DiasDeAtencionABM diasABM = new DiasDeAtencionABM();
        EstablecimientoABM establecimientoABM = new EstablecimientoABM(); // ✅ agregado

        // Alta Sucursal y Establecimiento
        Time apertura = Time.valueOf("08:00:00");
        Time cierre = Time.valueOf("16:00:00");
        Sucursal suc = sucursalABM.altaSucursal("Mitre 123", "1122334455", apertura, cierre, 10);
        DiasDeAtencion dia = diasABM.altaDiaDeAtencion("Lunes");
        sucursalABM.asociarDiaDeAtencion(suc.getId(), dia.getId());
        Establecimiento est = establecimientoABM.altaEstablecimiento("Clínica Norte", "20-12345678-9", "San Martín 456", "Clínica general");
        establecimientoABM.asociarSucursalAEstablecimiento(est.getId(), suc.getId());

        // Especialidades
        Especialidad esp1 = especialidadABM.altaEspecialidad(new Especialidad("Cardiología"));
        Especialidad esp2 = especialidadABM.altaEspecialidad(new Especialidad("Dermatología"));
        Especialidad esp3 = especialidadABM.altaEspecialidad(new Especialidad("Neurología"));

        sucursalABM.asociarEspecialidad(suc.getId(), esp1);
        sucursalABM.asociarEspecialidad(suc.getId(), esp2);
        sucursalABM.asociarEspecialidad(suc.getId(), esp3);

        // Clientes
        Cliente c1 = clienteABM.altaCliente("Juan", "Perez", "juan@mail.com", "Calle 1", 10000001, true, LocalDateTime.now(), 1);
        Cliente c2 = clienteABM.altaCliente("Maria", "Lopez", "maria@mail.com", "Calle 2", 10000002, true, LocalDateTime.now(), 2);
        Cliente c3 = clienteABM.altaCliente("Carlos", "Diaz", "carlos@mail.com", "Calle 3", 10000003, true, LocalDateTime.now(), 3);

        // Empleados
        Empleado e1 = empleadoABM.altaEmpleado("Ana", "Torres", "ana@mail.com", "Calle 4", 20000001, true, LocalDateTime.now(), 201000001, "MAT-A");
        Empleado e2 = empleadoABM.altaEmpleado("Luis", "Mendez", "luis@mail.com", "Calle 5", 20000002, true, LocalDateTime.now(), 201000002, "MAT-B");
        Empleado e3 = empleadoABM.altaEmpleado("Laura", "Fernandez", "laura@mail.com", "Calle 6", 20000003, true, LocalDateTime.now(), 201000003, "MAT-C");

        // Asignar especialidades a empleados
        empleadoABM.asignarEspecialidad(e1.getId(), esp1);
        empleadoABM.asignarEspecialidad(e2.getId(), esp2);
        empleadoABM.asignarEspecialidad(e3.getId(), esp3);

        // Servicios
        Servicio s1 = servicioABM.altaServicio("Consulta Cardiológica", 30);
        Servicio s2 = servicioABM.altaServicio("Consulta Dermatológica", 25);
        Servicio s3 = servicioABM.altaServicio("Consulta Neurológica", 40);

        // Turnos
        Turno t1 = turnoABM.altaTurno(LocalDateTime.now().plusDays(1).withHour(9), true, "T001", s1, c1, e1, suc);
        Turno t2 = turnoABM.altaTurno(LocalDateTime.now().plusDays(2).withHour(10), true, "T002", s2, c2, e2, suc);
        Turno t3 = turnoABM.altaTurno(LocalDateTime.now().plusDays(3).withHour(11), true, "T003", s3, c3, e3, suc);

        // === MODIFICACIONES ===
        System.out.println("\n=== MODIFICACIONES ===");

        // Cliente
        c1.setDireccion("Nueva Dirección 123");
        // clienteABM.modificarCliente(c1); // Habilitá si tenés este método
        System.out.println("Cliente modificado: " + c1.getNombre() + " -> " + c1.getDireccion());

        // Empleado
        e1.setEmail("nuevo.email@clinica.com");
        empleadoABM.modificarEmpleado(e1);
        System.out.println("Empleado modificado: " + e1.getNombre() + " -> " + e1.getEmail());

        // Especialidad
        esp1.setNombre("Cardiología Avanzada");
        especialidadABM.modificarEspecialidad(esp1);
        System.out.println("Especialidad modificada: " + esp1.getNombre());

        // Servicio
        s1.setNombreServicio("Consulta Cardiológica Pro");
        s1.setDuracion(45);
        servicioABM.modificarServicio(s1);
        System.out.println("Servicio modificado: " + s1.getNombreServicio() + " - " + s1.getDuracion() + " min");

        // Sucursal
        suc.setTelefono("999999999");
        sucursalABM.modificarSucursal(suc);
        System.out.println("Sucursal modificada: nuevo teléfono " + suc.getTelefono());

        // Establecimiento
        est.setDescripcion("Clínica integral con atención 24 hs");
        establecimientoABM.modificarEstablecimiento(est);
        System.out.println("Establecimiento modificado: " + est.getDescripcion());

        // Turno
        t1.setCodigo("T001-EDIT");
        t1.setEstado(false);
        turnoABM.modificarServicio(t1);
        System.out.println("Turno modificado: código " + t1.getCodigo() + ", estado " + t1.isEstado());

        // Día de atención
        dia.setNombre("Lunes extendido");
        diasABM.modificarDiaDeAtencion(dia);
        System.out.println("Día de atención modificado: " + dia.getNombre());
        
//        System.out.println("\n=== BAJAS ===");
//
//     // 1. Turno (se debe eliminar antes que cliente/empleado/sucursal/servicio)
//     turnoABM.bajaTurno(t3);
//     System.out.println("Turno dado de baja: " + t3.getCodigo());
//
//     // 2. Remover especialidad de sucursal
//     sucursalABM.removerEspecialidad(suc.getId(), esp3);
//     System.out.println("Especialidad removida de sucursal.");
//
//     // 3. Eliminar especialidad (ya sin referencias)
//     especialidadABM.bajaEspecialidad(esp3.getId());
//     System.out.println("Especialidad dada de baja: " + esp3.getNombre());
//
//     // 4. Eliminar servicio
//     servicioABM.bajaServicio(s3);
//     System.out.println("Servicio dado de baja: " + s3.getNombreServicio());
//
////     // 5. Eliminar cliente
////     clienteABM.bajaCliente(c3.getId());
////     System.out.println("Cliente dado de baja: " + c3.getNombre());
//
//     // 6. Eliminar empleado (ya sin especialidad referida)
//     empleadoABM.bajaEmpleado(e3.getId());
//     System.out.println("Empleado dado de baja: " + e3.getNombre());
//
//     // 7. Remover día de atención de la sucursal
//     sucursalABM.removerDiaDeAtencion(suc.getId(), dia.getId());
//     System.out.println("Día de atención removido de la sucursal.");
//
//     // 8. Eliminar día de atención
//     diasABM.bajaDiaDeAtencion(dia.getId());
//     System.out.println("Día de atención dado de baja: " + dia.getNombre());
//
//     // 9. Remover sucursal del establecimiento
//     establecimientoABM.removerSucursalDeEstablecimiento(est.getId(), suc.getId());
//     System.out.println("Sucursal removida del establecimiento.");
//
//     // 10. Eliminar sucursal
//     sucursalABM.bajaSucursal(suc.getId());
//     System.out.println("Sucursal dada de baja: " + suc.getDireccion());
//
//     // 11. Eliminar establecimiento
//     establecimientoABM.bajaEstablecimiento(est.getId());
//     System.out.println("Establecimiento dado de baja: " + est.getNombre());


        System.out.println("\n=== FIN DE PRUEBAS ===");
    }
}

    	
//        UsuarioABM usuarioABM = new UsuarioABM();
//
//        Usuario usuario = usuarioABM.altaUsuario(
//            "Luca", 
//            "De la Via", 
//            "luca.dlv@example.com", 
//            "Av. Siempre Viva 742", 
//            38123456
//        );
//        System.out.println("Usuario creado: " + usuario);
//
//        Usuario porId = usuarioABM.obtenerUsuarioPorId(usuario.getId());
//        System.out.println("Recuperado por ID: " + porId);
//
//        Usuario porDni = usuarioABM.obtenerUsuarioPorDNI(usuario.getDni());
//        System.out.println("Recuperado por DNI: " + porDni);
//
//        Usuario porEmail = usuarioABM.obtenerUsuarioPorEmail(usuario.getEmail());
//        System.out.println("Recuperado por email: " + porEmail);
//
//        usuario.setDireccion("Calle Real 1234");
//        Usuario modificado = usuarioABM.modificarUsuario(usuario);
//        System.out.println("Usuario modificado: " + modificado);
//
//        EspecialidadABM abm = new EspecialidadABM();
//
//        Especialidad e = new Especialidad("Cardiología");
//        e = abm.altaEspecialidad(e);
//        System.out.println("Especialidad creada: " + e);
//
//        Especialidad porId1 = abm.obtenerEspecialidadPorId(e.getId());
//        System.out.println("Recuperado por ID: " + porId1);
//
//        Especialidad porNombre = abm.obtenerEspecialidadPorNombre("Cardiología");
//        System.out.println("Recuperado por nombre: " + porNombre);
//
////        e.setNombre("Neurología");
////        Especialidad mod = abm.modificarEspecialidad(e);
////        System.out.println("Especialidad modificada: " + mod);
//        
//        EmpleadoABM empleadoABM = new EmpleadoABM();
//
//        Especialidad esp1 = abm.obtenerEspecialidadPorNombre("Cardiología");
//        if (esp1 == null) {
//            esp1 = abm.altaEspecialidad(new Especialidad("Cardiología"));
//            System.out.println("Especialidad creada: " + esp1);
//        }
// 
//        Especialidad esp2 = abm.altaEspecialidad(new Especialidad("Pediatría"));
//        System.out.println("Especialidad creada: " + esp2);
//
//        Empleado empleado = new Empleado(
//            "María",
//            "López",
//            "maria.lopez@example.com",
//            "Calle Salud 789",
//            40999888,              // DNI
//            true,                  // estado
//            java.time.LocalDateTime.now(), // fechaAlta
//            272223334,             // CUIL
//            "MAT5678"              // Matrícula
//        );
//
//        empleado.getLstEspecialidades().add(esp1);
//        empleado.getLstEspecialidades().add(esp2);
//
//        empleado = empleadoABM.altaEmpleado(empleado);
//        System.out.println("Empleado creado con especialidades: " + empleado);
//
//        ServicioABM servicioABM = new ServicioABM();
//        Servicio servicio = servicioABM.altaServicio("Atencion Medica", 1);
//        System.out.println("Servicio Creado: " + servicio);
//
//        servicio.setDuracion(2);
//        Servicio servicio_modificado = servicioABM.modificarServicio(servicio);
//        System.out.println("Servicio modificado: " + servicio_modificado);
//
//        
////        abm.bajaEspecialidad(mod.getId());
////        System.out.println("Especialidad eliminada con ID: " + mod.getId());
//        
//        EstablecimientoABM establecimientoABM = new EstablecimientoABM();
//
//        Establecimiento est = establecimientoABM.altaEstablecimiento(
//            "Supermercado Central",
//            "20-12345678-9",
//            "Av. Siempre Viva 247",
//            "Supermercado de barrio"
//        );
//        System.out.println("Establecimiento creado: " + est);
//        
//        est.setNombre("Supermercado Norte");
//        est.setDireccion("Av. Siempre Viva 248");
//        est.setDescripcion("Nuevo supermercado de barrio");
//
//        Establecimiento estModificado = establecimientoABM.modificarEstablecimiento(est);
//        System.out.println("Establecimiento modificado: " + estModificado);
//        
//        SucursalABM sucursalABM = new SucursalABM();
//        
//        Sucursal suc = sucursalABM.altaSucursal(
//                "Av. Oeste 500", 
//                "3811234567", 
//                Time.valueOf("09:00:00"),  
//                Time.valueOf("18:00:00"),
//                3
//            );
//        System.out.println("Sucursal: " + suc);
//        
//        establecimientoABM.asociarSucursalAEstablecimiento(est.getId(), suc.getId());
//        
//        Sucursal sucRecuperada = sucursalABM.traer(suc.getId());
//        System.out.println("Establecimiento con sucursal asociada: " + sucRecuperada.getEstablecimiento());
//        
//        suc.setDireccion("Av. Este 500");
//        Sucursal sucModificada = sucursalABM.modificarSucursal(suc);
//        System.out.println("Sucursal modificada: " + sucModificada);
//        
//        establecimientoABM.removerSucursalDeEstablecimiento(est.getId(), suc.getId());
//        Establecimiento estSinSucursal = establecimientoABM.traer(est.getId());
//        System.out.println("Establecimiento sin sucursal: " + estSinSucursal);
//        Sucursal sucDesasociada = sucursalABM.traer(suc.getId());
//        System.out.println("Sucursal: " + sucDesasociada);
//        
//        DiasDeAtencionABM diasDeAtencionABM = new DiasDeAtencionABM();
//
//        DiasDeAtencion dia1 = diasDeAtencionABM.altaDiaDeAtencion("Lunes");
//        System.out.println("Dia de atencion creado: " + dia1);
//
//        dia1.setNombre("Martes");
//        DiasDeAtencion diaModificado = diasDeAtencionABM.modificarDiaDeAtencion(dia1);
//        System.out.println("Dia de atencion modificado: " + diaModificado);
//        
//        sucursalABM.asociarDiaDeAtencion(suc.getId(), dia1.getId());
//        Sucursal sucursalConDia = sucursalABM.traer(suc.getId());
//        System.out.println("Sucursal con dia de atencion asociado: " + sucursalConDia);
//        
//        sucursalABM.removerDiaDeAtencion(suc.getId(), dia1.getId());
//        Sucursal sucursalSinDia = sucursalABM.traer(suc.getId());
//        System.out.println("Sucursal despues de remover dia de atencion: " + sucursalSinDia);
//    }
//}