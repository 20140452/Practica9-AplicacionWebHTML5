package logical;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Sector sector;

    @NotNull
    private NivelEducativo nivelEducacion;

    private int altitud;

    private int longitud;

    public Encuesta() {
    }

    public Encuesta(String nombre, Sector sector, NivelEducativo nivelEducacion, int altitud, int longitud) {
        this.nombre = nombre;
        this.sector = sector;
        this.nivelEducacion = nivelEducacion;
        this.altitud = altitud;
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

    public int getAltitud() {
        return altitud;
    }

    public void setAltitud(int altitud) {
        this.altitud = altitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
}
