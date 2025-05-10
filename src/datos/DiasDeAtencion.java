package datos;

import java.util.Objects;

public class DiasDeAtencion {

    private int id;
    private String nombre;

    public DiasDeAtencion(String nombre) {
        super();
        this.nombre = nombre;
    }

    public DiasDeAtencion() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DiasDeAtencion other = (DiasDeAtencion) obj;
        return id == other.id && Objects.equals(nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "DiasDeAtencion [id=" + id + ", nombre=" + nombre + "]";
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