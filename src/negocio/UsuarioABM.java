package negocio;

import datos.Usuario;
import dao.UsuarioDao;

import java.time.LocalDateTime;

public class UsuarioABM {

	private final UsuarioDao usuarioDao = new UsuarioDao();
	
    public Usuario altaUsuario(Usuario u) {
    	
    	if (usuarioDao.traerPorDni(u.getDni()) != null) {
    		throw new IllegalArgumentException("ERROR: Ya existe un usuario con ese DNI");
    	}
    	
    	if (usuarioDao.traerPorEmail(u.getEmail()) != null) {
    		throw new IllegalArgumentException("ERROR: Ya existe un usuario con ese EMAIL");
    	}
    	
    	usuarioDao.agregar(u);
    	
		return u;
    }
    
    public Usuario altaUsuario(String nombre, String apellido, String email, String direccion, int dni) {
    	
    	Usuario u = new Usuario(nombre, apellido, email, direccion, dni);
    	
    	if (usuarioDao.traerPorDni(u.getDni()) != null) {
    		throw new IllegalArgumentException("ERROR: Ya existe un usuario con ese DNI");
    	}
    	
    	if (usuarioDao.traerPorEmail(u.getEmail()) != null) {
    		throw new IllegalArgumentException("ERROR: Ya existe un usuario con ese EMAIL");
    	}
    	
    	u.setFechaAlta(LocalDateTime.now());
    	u.setEstado(true);
    	
    	usuarioDao.agregar(u);
    	
		return u;
    }
    
    public void bajaUsuario(int id) {
    	
    	if (usuarioDao.traer(id) == null) {
    		throw new IllegalArgumentException("ERROR: No existe usuario con ID: " + id);
    	}
    	
    	usuarioDao.eliminar(usuarioDao.traer(id));
    }
    
    public Usuario modificarUsuario(Usuario u) {
    	
        Usuario actual = usuarioDao.traer(u.getId());
        
        if (actual == null) {
        	throw new IllegalArgumentException("ERROR: No existe usuario con ID: " + u.getId());
        }
        
        if (!actual.getEmail().equals(u.getEmail())
            && usuarioDao.traerPorEmail(u.getEmail()) != null) {
        	throw new IllegalArgumentException("ERROR: Ya existe un usuario con ese EMAIL");
        }
        
        actual.setNombre(u.getNombre());
        actual.setApellido(u.getApellido());
        actual.setEmail(u.getEmail());
        actual.setDireccion(u.getDireccion());
        
        usuarioDao.actualizar(actual);
        
        return actual;
    }
    
    public Usuario obtenerUsuarioPorId(int id) throws IllegalArgumentException {
    	
        Usuario u = usuarioDao.traer(id);
        
        if (u == null) {
        	throw new IllegalArgumentException("ERROR: No existe un usuario con ese ID");
        }
        
        return u;
    }

    public Usuario obtenerUsuarioPorDNI(int dni) throws IllegalArgumentException {
    	
        Usuario u = usuarioDao.traerPorDni(dni);
        
        if (u == null) {
        	throw new IllegalArgumentException("ERROR: No existe un usuario con ese DNI");
        }
        
        return u;
    }

    public Usuario obtenerUsuarioPorEmail(String email) throws IllegalArgumentException {
    	
        Usuario u = usuarioDao.traerPorEmail(email);
        
        if (u == null) {
        	throw new IllegalArgumentException("ERROR: No existe un usuario con EMAIL");
        }
        
        return u;
    }
}
