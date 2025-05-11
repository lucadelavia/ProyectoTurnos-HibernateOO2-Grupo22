package datos;

import java.sql.Time;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Sucursal {

	private int id;
	private String direccion;
	private String telefono;
	private Time horaApertura;
	private Time horaCierre;
	private Integer espacio;
	private Set<Especialidad> lstEspecialidad;
	private Set<DiasDeAtencion> lstDiasDeAtencion;
	private Establecimiento establecimiento;
	
	public Sucursal(String direccion, String telefono, Time horaApertura, Time horaCierre, int espacio) {
		super();
		this.direccion = direccion;
		this.telefono = telefono;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.espacio = espacio;
		this.lstEspecialidad = new HashSet<>();
		this.lstDiasDeAtencion = new HashSet<>();
	}

	public Sucursal() {
		super();
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Sucursal)) return false;
	    Sucursal that = (Sucursal) o;
	    return id == that.id;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", direccion=" + direccion + ", telefono=" + telefono + ", horaApertura="
				+ horaApertura + ", horaCierre=" + horaCierre + ", espacio=" + espacio + ", diasDeAtencion="
				+ lstDiasDeAtencion + "]";
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
	public Time getHoraApertura() {
		return horaApertura;
	}
	public void setHoraApertura(Time horaApertura) {
		this.horaApertura = horaApertura;
	}
	public Time getHoraCierre() {
		return horaCierre;
	}
	public void setHoraCierre(Time horaCierre) {
		this.horaCierre = horaCierre;
	}
	public int getEspacio() {
		return espacio;
	}
	public void setEspacio(int espacio) {
		this.espacio = espacio;
	}
	public Set<Especialidad> getLstEspecialidad() {
		return lstEspecialidad;
	}
	public void setLstEspecialidad(Set<Especialidad> lstEspecialidad) {
		this.lstEspecialidad = lstEspecialidad;
	}
	public Set<DiasDeAtencion> getLstDiasDeAtencion() {
		return lstDiasDeAtencion;
	}
	public void setLstDiasDeAtencion(Set<DiasDeAtencion> lstDiasDeAtencion) {
		this.lstDiasDeAtencion = lstDiasDeAtencion;
	}
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
}
