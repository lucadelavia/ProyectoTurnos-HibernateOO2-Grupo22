package datos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Empleado extends Usuario {

	private int cuil;
	private String matricula;
	private List<Especialidad>lstEspecialidades;
	
	public Empleado(String nombre, String apellido, String email, String direccion, int dni, boolean estado,
            LocalDateTime fechaAlta, int cuil, String matricula) {
			super(nombre, apellido, email, direccion, dni, estado, fechaAlta);
			this.cuil = cuil;
			this.matricula = matricula;
			this.lstEspecialidades = new ArrayList<Especialidad>();
	}

	public Empleado() {
		super();
	}

	//METODO DE LA CLASE
//	+ verTurnosAsignados(): List<Turnos>
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(cuil, other.cuil) && Objects.equals(lstEspecialidades, other.lstEspecialidades)
				&& Objects.equals(matricula, other.matricula);
	}
	
	@Override
	public String toString() {
		return "Empleado [cuil=" + cuil + ", matricula=" + matricula + ", lstEspecialidades=" + lstEspecialidades
				+ ", toString()=" + super.toString() + ", getId()=" + getId() + ", getNombre()=" + getNombre()
				+ ", getApellido()=" + getApellido() + ", getEmail()=" + getEmail() + ", getDireccion()="
				+ getDireccion() + ", getDni()=" + getDni() + ", isEstado()=" + isEstado() + ", getFechaAlta()="
				+ getFechaAlta() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public int getCuil() {
		return cuil;
	}
	public void setCuil(int cuil) {
		this.cuil = cuil;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public List<Especialidad> getLstEspecialidades() {
		return lstEspecialidades;
	}
	public void setLstEspecialidades(List<Especialidad> lstEspecialidades) {
		this.lstEspecialidades = lstEspecialidades;
	}
	
	
	
	
}
