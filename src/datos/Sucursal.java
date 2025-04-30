package datos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sucursal {

	private int id;
	private String direccion;
	private String telefono;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private List<Especialidad>lstEspecialidad;
	private List<PuntoDeAtencion>puntosDeAtencion;
	
	public Sucursal(String direccion, String telefono, LocalTime horaApertura, LocalTime horaCierre,
			 List<PuntoDeAtencion> puntosDeAtencion) {
		super();
		this.direccion = direccion;
		this.telefono = telefono;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.lstEspecialidad = new ArrayList<Especialidad>();
		this.puntosDeAtencion = puntosDeAtencion;
	}

	public Sucursal() {
		super();
	}

	//METODOS DE LA CLASE
//	+ altaSucursal(sucursal: Sucursal)
//	+ bajaSucursal(id: int)
//	+ modificarSucursal(sucursal: Sucursal)
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sucursal other = (Sucursal) obj;
		return Objects.equals(direccion, other.direccion) && Objects.equals(horaApertura, other.horaApertura)
				&& Objects.equals(horaCierre, other.horaCierre) && id == other.id
				&& Objects.equals(puntosDeAtencion, other.puntosDeAtencion) && Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", direccion=" + direccion + ", telefono=" + telefono + ", horaApertura="
				+ horaApertura + ", horaCierre=" + horaCierre + ", puntosDeAtencion=" + puntosDeAtencion + "]";
	}
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public LocalTime getHoraApertura() {
		return horaApertura;
	}
	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}
	public LocalTime getHoraCierre() {
		return horaCierre;
	}
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}
	public List<Especialidad> getLstEspecialidad() {
		return lstEspecialidad;
	}
	public void setLstEspecialidad(List<Especialidad> lstEspecialidad) {
		this.lstEspecialidad = lstEspecialidad;
	}
	public List<PuntoDeAtencion> getPuntosDeAtencion() {
		return puntosDeAtencion;
	}
	public void setPuntosDeAtencion(List<PuntoDeAtencion> puntosDeAtencion) {
		this.puntosDeAtencion = puntosDeAtencion;
	}
	
	
	
}
