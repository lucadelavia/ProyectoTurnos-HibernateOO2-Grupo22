package datos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Establecimiento {

	private int id;	
	private String nombre;
	private String cuit;
	private String direccion;
	private String descripcion;
	private Set<Sucursal> sucursales;
	
	public Establecimiento() {
		super();
	}

	public Establecimiento(String nombre, String cuit, String direccion, String descripcion) {
		super();
		this.nombre = nombre;
		this.cuit = cuit;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.sucursales = new HashSet<Sucursal>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Establecimiento other = (Establecimiento) obj;
		return Objects.equals(cuit, other.cuit) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(direccion, other.direccion) && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(sucursales, other.sucursales);
	}

	@Override
	public String toString() {
		return "Establecimiento [id=" + id + ", nombre=" + nombre + ", cuit=" + cuit + ", direccion=" + direccion
				+ ", descripcion=" + descripcion + ", sucursales=" + sucursales + "]";
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
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Set<Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(Set<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
}