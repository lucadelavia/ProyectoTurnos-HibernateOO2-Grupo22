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

//    public List<Turno> consultarTurnosDisponibles(int idServicio);
//    public void elegirSucursal(int idCliente, int idSucursal);
//    public void elegirEspecialidad(int idCliente, int idEspecialidad);
//    public void elegirServicio(int idCliente, int idServicio);
//    public void elegirHorarioTurno(int idCliente, LocalDateTime fechaHora);
//    public Turno pedirTurno(int idCliente);
//    public void cancelarTurno(int idCliente, int idTurno);
//    public List<Turno> consultarTurnosCliente(int idCliente);
}
