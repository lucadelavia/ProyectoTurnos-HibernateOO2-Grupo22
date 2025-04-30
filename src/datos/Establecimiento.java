package datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Establecimiento {

	private int id;	
	private String nombre;
	private String cuit;
	private String direccion;
	private String descripcion;
	private List<Sucursal>sucursales;
	
	public Establecimiento() {
		super();
	}

	public Establecimiento(String nombre, String cuit, String direccion, String descripcion) {
		super();
		this.nombre = nombre;
		this.cuit = cuit;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.sucursales = new ArrayList<Sucursal>();
	}

	//METODOS DE LA CLASE
//	+ altaEstablecimiento(establecimiento: Establecimiento)
//	+ bajaEstablecimiento(id: int)
//	+ modificarEstablecimiento(establecimiento: Establecimiento)
//	+ asociarSucursalAEstablecimiento(idSucursal: int, establecimiento: Establecimiento)
//	+ removerSucursal(establecimiento: Establecimiento, idSucursal: int)
	
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
	public List<Sucursal> getSucursales() {
		return sucursales;
	}
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}
	
	
	
	
}
