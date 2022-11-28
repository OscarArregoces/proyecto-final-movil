package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notas {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("estado")
    @Expose
    private boolean estado;

    @SerializedName("propietario")
    @Expose
    private String propietario;



    public Notas(int id, String nombre, String descripcion, boolean estado, String propietario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.propietario = propietario;
    }

    public Notas() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id;  }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) { this.propietario = propietario;  }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre;  }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;

    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}