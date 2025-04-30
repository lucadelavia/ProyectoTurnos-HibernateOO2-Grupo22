package datos;

import java.time.LocalDateTime;

public class Turno {
	
	private int id;
	private LocalDateTime fechaHora;
	private boolean estado;
	private String codigo;
	private Cliente cliente;
	private PuntoDeAtencion puntoDeAtencion;
	
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public PuntoDeAtencion getPuntoDeAtencion() {
		return puntoDeAtencion;
	}
	public void setPuntoDeAtencion(PuntoDeAtencion puntoDeAtencion) {
		this.puntoDeAtencion = puntoDeAtencion;
	}
	
	
	
	

}
