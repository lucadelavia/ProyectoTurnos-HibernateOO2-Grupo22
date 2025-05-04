package datos;

import java.util.Objects;

public class Servicio {
	
	private int id;
	private String nombreServicio;
	private int duracion;
	
	public Servicio(String nombreServicio, int duracion) {
		super();
		this.nombreServicio = nombreServicio;
		this.duracion = duracion;
	}

	public Servicio() {
		super();
	}

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombreServicio=" + nombreServicio + ", duracion=" + duracion + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return duracion == other.duracion && id == other.id && Objects.equals(nombreServicio, other.nombreServicio);
	}
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	
	
}
