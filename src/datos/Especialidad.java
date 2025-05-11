package datos;

import java.util.Objects;

public class Especialidad {

	private int id;
	private String nombre;
	
	public Especialidad(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Especialidad() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidad other = (Especialidad) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Especialidad [id=" + id + ", nombre=" + nombre + "]";
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
	
	
	
	
}
