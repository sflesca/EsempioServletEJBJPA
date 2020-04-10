package psw.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class Articolo {

    @GeneratedValue
    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    private String titolo;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Basic
    private String corpo;

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    @OneToMany(mappedBy = "art")
    private Collection<Commento> commenti = new LinkedList<Commento>();

    public Collection<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(Collection<Commento> commenti) {
        this.commenti = commenti;
    }

    @ManyToOne(optional = false)
    @JoinColumn
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
