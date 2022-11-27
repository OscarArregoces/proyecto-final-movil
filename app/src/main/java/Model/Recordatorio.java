package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Recordatorio {

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

    @SerializedName("fecha_inicio")
    @Expose
    private String fecha_inicio;

    @SerializedName("fecha_final")
    @Expose
    private String fecha_final;



    public Recordatorio(int id, String nombre, String descripcion, boolean estado, String fecha_inicio, String fecha_final) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
    }

    public Recordatorio() {

    }


    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id;  }


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


    public String getFecha_inicio() { return fecha_inicio;  }
    public void setFecha_inicio(String fecha_inicio) { this.fecha_inicio = fecha_inicio; }


    public String getFecha_final() { return fecha_final; }
    public void setFecha_final(String fecha_final) { this.fecha_final = fecha_final; }
}
