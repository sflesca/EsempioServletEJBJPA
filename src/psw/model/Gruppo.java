package psw.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Gruppo")
@NamedQuery(name = "findAllGroups", query = "select g from Gruppo g")
public class Gruppo implements Serializable {

    private static final long serialVersionUID = 1L;

    public Gruppo() {
    }

    @Id
    @GeneratedValue
    private long id;
    private String nome;
    @OneToMany(mappedBy = "gruppo")
    private List<Persona> persona = new ArrayList<Persona>();
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

    public List<Persona> getPersona() {
        return persona;
    }

    public void setPersona(List<Persona> param) {
        this.persona = param;
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
        Gruppo other = (Gruppo) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
