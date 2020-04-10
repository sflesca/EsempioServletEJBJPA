package psw.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllPersons", query = "select p from Persona p"),
        @NamedQuery(name = "findPersonsByGroupId", query = "select p from Persona p where p.gruppo.id = :groupid")
})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    public Persona() {
    }

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    @ManyToOne
    private Gruppo gruppo;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String param) {
        this.nome = param;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setGruppo(Gruppo param) {
        this.gruppo = param;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
