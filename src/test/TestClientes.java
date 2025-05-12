package test;

import negocio.UsuarioABM;
import datos.Cliente;
//import negocio.ClienteABM;

public class TestClientes {

	public static void main(String[] args) {
		//ClienteABM clienteABM = new ClienteABM();

		
//		Cliente cliente = clienteABM.altaCliente(
//            "Sev", 
//            "Sdru", 
//            "sevsdru@example.com", 
//            "Tucu 1630", 
//            40945930,
//			true,
//			java.time.LocalDateTime.now(),
//			18
//        );

		UsuarioABM usuarioABM = new UsuarioABM();

		Cliente c1 = (Cliente) usuarioABM.obtenerUsuarioPorId(1);
		System.out.println(c1);


	}
}
