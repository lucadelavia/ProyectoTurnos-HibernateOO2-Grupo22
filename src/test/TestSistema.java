package test;

import negocio.UsuarioABM;
import datos.Usuario;

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

      
    }
}
