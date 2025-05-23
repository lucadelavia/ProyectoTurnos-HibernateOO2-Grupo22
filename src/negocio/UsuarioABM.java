package negocio;

import datos.Usuario;
import dao.UsuarioDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    	
        Usuario actual = obtenerUsuarioPorId(u.getId());
        
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

    public List<Usuario> obtenerUsuariosPorFecha(LocalDate fecha, Boolean estado){
        List<Usuario> usuarios = usuarioDao.obtenerUsuariosPorFecha(fecha, estado);
        if(usuarios == null){
            throw new IllegalArgumentException("ERROR: No hay usuarios creados en esta fecha: " + fecha);
        }
        return usuarios;
        }

    public List<Usuario> obtenerUsuariosPorRangoFechas(LocalDate desde, LocalDate hasta){
        List<Usuario> usuarios = usuarioDao.obtenerUsuariosPorRangoFechas(desde, hasta);
        if(usuarios == null){
            throw new IllegalArgumentException("ERROR: No hay usuarios creados en estas fechas: " + desde + " " + hasta);
        }
        return usuarios;
        }
}
