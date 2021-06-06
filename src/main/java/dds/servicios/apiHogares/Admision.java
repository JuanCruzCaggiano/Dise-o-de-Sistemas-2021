package dds.servicios.apiHogares;

public class Admision {
    private Boolean perro;
    private Boolean gato;

    public Admision(Boolean perro, Boolean gato) {
        this.perro = perro;
        this.gato = gato;
    }

    public Boolean getPerro() {
        return perro;
    }

    public void setPerro(Boolean perro) {
        this.perro = perro;
    }

    public Boolean getGato() {
        return gato;
    }

    public void setGato(Boolean gato) {
        this.gato = gato;
    }
}
