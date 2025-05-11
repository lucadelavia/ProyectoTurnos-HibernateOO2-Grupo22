package negocio;

import java.time.LocalDateTime;
import dao.ClienteDao;

import datos.Cliente;

public class ClienteABM {

    private final ClienteDao clienteDao = new ClienteDao();

    public Cliente altaCliente(String nombre, String apellido, String email, String direccion, int dni, boolean estado, LocalDateTime fechaAlta, int nroCliente){
		Cliente c = new Cliente(nombre, apellido, email, direccion, dni, estado, fechaAlta, nroCliente);
		if(clienteDao.traer(c.getId()) != null){
			throw new IllegalArgumentException("Este cliente ya existe.");
		}
		clienteDao.agregar(c);

		return c;
	}

	public Cliente altaCliente(Cliente cliente){
		if(clienteDao.traer(cliente.getId()) != null){
			throw new IllegalArgumentException("Este cliente ya existe.");
		}
		clienteDao.agregar(cliente);

		return cliente;
	}

	public Cliente obtenerClientePorId(int id){

		Cliente c = clienteDao.traer(id);

		if (c == null) {
			throw new IllegalArgumentException("ERROR: No existe el cliente solicitado");
		}

		return c;

	}

	public void bajaCliente(int id) {

		clienteDao.eliminar(clienteDao.traer(id));;

	}
	
	public Cliente modificarCliente(Cliente c) {
		
		Cliente actual = obtenerClientePorId(c.getId());
		
		actual.setNombre(c.getNombre());
		actual.setApellido(c.getApellido());
		actual.setEmail(c.getEmail());
		actual.setDireccion(c.getDireccion());
		actual.setDni(c.getDni());
		actual.setEstado(c.isEstado());
		actual.setNroCliente(c.getNroCliente());
		
		clienteDao.actualizar(actual);
		
		return actual;
	}

//    public void elegirSucursal(int idCliente, int idSucursal);
//    public void elegirEspecialidad(int idCliente, int idEspecialidad);
//    public void elegirServicio(int idCliente, int idServicio);
//    public void elegirHorarioTurno(int idCliente, LocalDateTime fechaHora);
//    public Turno pedirTurno(int idCliente);
//    public void cancelarTurno(int idCliente, int idTurno);

}
