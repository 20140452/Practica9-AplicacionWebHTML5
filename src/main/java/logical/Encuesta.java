package logical;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Encuesta implements Serializable {
    @Id
    @GeneratedValue
    private long idEncuesta;

    @NotNull
    private String nombre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idSector")
    private Sector sector;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idNivel")
    private NivelEducativo nivelEducacion;

    private float latitud;

    private float longitud;

    public Encuesta() {
    }

    public Encuesta(String nombre, Sector sector, NivelEducativo nivelEducacion, float latitud, float longitud) {
        this.nombre = nombre;
        this.sector = sector;
        this.nivelEducacion = nivelEducacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public NivelEducativo getNivelEducacion() {
        return nivelEducacion;
    }

    public void setNivelEducacion(NivelEducativo nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
