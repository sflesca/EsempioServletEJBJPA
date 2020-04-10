package psw.ejb;

import psw.model.Prodotto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "ProdottoEJB")
public class ProdottoBean {
    public ProdottoBean() {
    }

    @PersistenceContext
    EntityManager em;


    public List<Prodotto> findAllProducts() {
        Query q = em.createNamedQuery("findAllProducts");
        return q.getResultList();
    }

    public List<Prodotto> findAllProducts(final Integer startPosition,
                                          final Integer maxResult) {
        Query q = em.createNamedQuery("findAllProducts");
        return q.setFirstResult(startPosition).setMaxResults(maxResult).getResultList();
    }

    public List<Prodotto> findAllProductsByCategoria(long catid) {
        Query q = em.createNamedQuery("findAllProductsByCategoria").setParameter("catid", catid);
        return q.getResultList();
    }

    public List<Prodotto> findAllProductsByCategoria(long catid, final Integer startPosition,
                                          final Integer maxResult) {
        Query q = em.createNamedQuery("findAllProductsByCategoria").setParameter("catid", catid);
        return q.setFirstResult(startPosition).setMaxResults(maxResult).getResultList();
    }

    public Prodotto inserisciProdotto(String nome, int qta, float price) {
        Prodotto p = new Prodotto();
        p.setNome(nome);
        p.setQta(qta);
        p.setPrezzo(price);
        em.persist(p);
        return p;
    }

    public Prodotto inserisciProdotto(String nome, float price) {
        Prodotto p = new Prodotto();
        p.setNome(nome);
        p.setQta(0);
        p.setPrezzo(price);
        em.persist(p);
        return p;
    }

    public Prodotto aggiorna(Prodotto p) {
        return em.merge(p);
    }

    public void remove(Prodotto p) {
        em.remove(p);
    }

    public Prodotto inserisciProdotto(Prodotto p) {
        em.persist(p);
        return p;
    }

    public Prodotto find(long pid) {
        Prodotto p = em.find(Prodotto.class, pid);
        return p;
    }

}
