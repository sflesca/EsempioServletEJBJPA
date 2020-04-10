package psw.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@NamedQueries({@NamedQuery(name="getClienti", query="select c from Cliente c")})
public class Cliente {
    private long id;
    private String nome;

    @Id @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id &&
                Objects.equals(nome, cliente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }


    private Collection<Ordine> ordini;

    @OneToMany(mappedBy = "cliente")
    public Collection<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(Collection<Ordine> ordini) {
        this.ordini = ordini;
    }
}
