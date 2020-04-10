package psw.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

@Entity
public class Ordine {
    private long id;

    @GeneratedValue
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Cliente cliente;

    @ManyToOne(optional = false)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private Collection<DettaglioOrdine> dettagli = new LinkedList<DettaglioOrdine>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn
    public Collection<DettaglioOrdine> getDettagli() {
        return dettagli;
    }

    public void setDettagli(Collection<DettaglioOrdine> dettagli) {
        this.dettagli = dettagli;
    }

    private Date data;

    @Basic
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
