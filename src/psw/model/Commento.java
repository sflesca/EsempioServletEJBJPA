package psw.model;

import javax.persistence.*;

@Entity
public class Commento {
    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    private int voto;

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    @Basic
    private String testo;

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    @ManyToOne(optional = false)
    @JoinColumn
    private Articolo art;

    public Articolo getArt() {
        return art;
    }

    public void setArt(Articolo art) {
        this.art = art;
    }
}
