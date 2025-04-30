package datos;

import java.util.Objects;

public class PuntoDeAtencion {
	
	private int id;
	private int numeroPuntoDeAtencion;
	private Empleado empleado;
	
	public PuntoDeAtencion(int numeroPuntoDeAtencion, Empleado empleado) {
		super();
		this.numeroPuntoDeAtencion = numeroPuntoDeAtencion;
		this.empleado = empleado;
	}

	public PuntoDeAtencion() {
		super();
	}
	
	//METODOS DE LA CLASE
//	+ altaConsultorio(consultorio: Consultorio)
//	+ bajaConsultorio(id: int)
//	+ modificarConsultorio(consultorio: Consultorio)
//	+ obtenerConsultorioPorId(id: int): Consultorio
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntoDeAtencion other = (PuntoDeAtencion) obj;
		return Objects.equals(empleado, other.empleado) && id == other.id
				&& numeroPuntoDeAtencion == other.numeroPuntoDeAtencion;
	}

	@Override
	public String toString() {
		return "PuntoDeAtencion [id=" + id + ", numeroPuntoDeAtencion=" + numeroPuntoDeAtencion + ", empleado="
				+ empleado + "]";
	}
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public int getNumeroPuntoDeAtencion() {
		return numeroPuntoDeAtencion;
	}
	public void setNumeroPuntoDeAtencion(int numeroPuntoDeAtencion) {
		this.numeroPuntoDeAtencion = numeroPuntoDeAtencion;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	

}
