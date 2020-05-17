package psw.ejb;

import psw.model.Articolo;
import psw.model.Categoria;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Stateless(name = "ArticoloSessionEJB")
public class ArticoloSessionBean {

    @PersistenceContext
    EntityManager em;

    public List<Articolo> findAllArticoli(int start, int count){
        Query q = em.createNamedQuery("findAllArticoli").setFirstResult(start).setMaxResults(count);
        return q.getResultList();
    }

    public ArticoloSessionBean() {
    }

    public Articolo inserisciArticolo(Articolo articolo) {
        em.persist(articolo);
        return articolo;
    }

    public Articolo find(Long id) {
        return em.find(Articolo.class,id);
    }

    public Articolo aggiorna(Articolo articolo) {
       return em.merge(articolo);
    }

    public void remove(Articolo a) {
        em.remove(a);
    }
}
