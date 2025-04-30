package datos;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {

	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String direccion;
	private int dni;
	private boolean estado;
	private LocalDateTime fechaAlta;
		
	public Usuario() {
		
	}

	public Usuario(String nombre, String apellido, String email, String direccion, int dni, boolean estado,
			LocalDateTime fechaAlta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.dni = dni;
		this.estado = estado;
		this.fechaAlta = fechaAlta;
	}
	
	//METODOS DE LA CLASE
	
	//public boolean altaUsuario(Usuario usuario);
	//public boolean bajaUsuario(int id);
	//public boolean modificarUsuario(Usuario usuario);
	//public Usuario obtenerUsuarioPorId(int id);
	//public Usuario obtenerUsuarioPorDNI(int dni);
	//public Usuario obtenerUsuarioPorEmail(String email);
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(direccion, other.direccion)
				&& dni == other.dni && Objects.equals(email, other.email) && estado == other.estado
				&& Objects.equals(fechaAlta, other.fechaAlta) && id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", direccion=" + direccion + ", dni=" + dni + ", estado=" + estado + ", fechaAlta=" + fechaAlta + "]";
	}

	public int getId() {
		return id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getDni() {
		return dni;
	}
	
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public boolean isEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
	
	
}
