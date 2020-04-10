package psw.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "select p from Prodotto p"),
        @NamedQuery(name = "findAllProductsByCategoria",
                query = "select p from Prodotto p where p.categoria.id=:catid")
})
public class Prodotto {
    private long id;

    @GeneratedValue
    @Id
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

    private Categoria categoria;

    @ManyToOne(optional = true)
    @JoinColumn
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    private int qta;

    @Basic
    public int getQta() {
        return qta;
    }

    public void setQta(int qta) {
        this.qta = qta;
    }

    private float prezzo;

    @Basic
    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    private long version;

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
