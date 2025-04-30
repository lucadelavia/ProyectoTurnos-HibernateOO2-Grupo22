package datos;

import java.time.LocalDateTime;
public class Cliente extends Usuario {

	private int nroCliente;

	public Cliente() {

	}

	public Cliente(String nombre, String apellido, String email, String direccion, int dni, boolean estado,
			LocalDateTime fechaAlta, int nroCliente) {
		super(nombre, apellido, email, direccion, dni, estado, fechaAlta);
		this.nroCliente = nroCliente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return nroCliente == other.nroCliente;
	}

	@Override
	public String toString() {
		return "Cliente [nroCliente=" + nroCliente + ", toString()=" + super.toString() + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido() + ", getEmail()=" + getEmail()
				+ ", getDireccion()=" + getDireccion() + ", getDni()=" + getDni() + ", isEstado()=" + isEstado()
				+ ", getFechaAlta()=" + getFechaAlta() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	public int getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(int nroCliente) {
		this.nroCliente = nroCliente;
	}
	
	
	
}
