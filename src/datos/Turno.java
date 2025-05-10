package datos;

import java.time.LocalDateTime;

public class Turno {
	
	private int id;
	private LocalDateTime fechaHora;
	private boolean estado;
	private String codigo;
	private Servicio servicio;
	private Cliente cliente;
	private Sucursal sucursal;

	public Turno(LocalDateTime fechaHora, boolean estado, String codigo, Servicio servicio, Cliente cliente, Sucursal sucursal) {
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.codigo = codigo;
		this.servicio = servicio;
		this.cliente = cliente;
		this.sucursal = sucursal;
	}

	public Turno(){
		
	}

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

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return "Turno [id=" + id + ", fechaHora=" + fechaHora + ", estado=" + estado + ", codigo=" + codigo
				+ ", servicio=" + servicio + ", cliente=" + cliente + ", sucursal=" + sucursal + "]";
	}

	
	
	

}
