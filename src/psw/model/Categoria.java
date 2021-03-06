package psw.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.LinkedList;

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "findAllCategorie", query = "select c from Categoria c")
})
public class Categoria {
    private long id;

    public Categoria() {
    }

    public Categoria(String nome) {
        setNome(nome);
    }


    @Id @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String nome;

    @Basic
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

/*
    @JsonIgnore

    private Collection<Prodotto> prodotti = new LinkedList<Prodotto>();


    @OneToMany(mappedBy = "categoria")
    public Collection<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(Collection<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

 */
}
