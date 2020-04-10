package psw.ejb;

import psw.model.DettaglioOrdine;
import psw.model.Ordine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "OrderEJB")
public class OrderBean {

    @PersistenceContext
    EntityManager em;

    public OrderBean() {
    }

    public List<Ordine> findAllOrders() {
        Query q = em.createNamedQuery("findAllOrders");
        return q.getResultList();
    }

    public Ordine inserisciOrdine(Ordine o) {
        em.persist(o);
        return o;
    }


    public Ordine aggiorna(Ordine o) {
        return em.merge(o);
    }

    public void remove(Ordine o) {
        em.remove(o);
    }

    public DettaglioOrdine inserisciDettaglioOrdine(DettaglioOrdine dt) {
        em.persist(dt);
        return dt;
    }

    public Ordine find(long oid) {
        Ordine o = em.find(Ordine.class, oid);
        return o;
    }

    public Ordine findWithDEttagli(long oid) {
        Ordine o = em.find(Ordine.class, oid);
        o.getDettagli().size();
        return o;
    }

}
