package psw.ejb;

import psw.model.Categoria;
import psw.model.Prodotto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Stateless(name = "CategoriaEJB")
public class CategoriaBean {
    public CategoriaBean() {
    }

    @PersistenceContext
    EntityManager em;

    public Collection<Categoria> findAll(){
        Query q = em.createNamedQuery("findAllCategorie");
        return q.getResultList();
    }

}
