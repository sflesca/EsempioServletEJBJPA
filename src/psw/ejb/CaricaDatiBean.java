package psw.ejb;

import psw.model.*;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Random;

@Stateless(name = "CaricaDatiEJB")
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CaricaDatiBean {

    @PersistenceContext
    EntityManager em;

    public CaricaDatiBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void caricaTutto() {
        caricaGruppi();
        caricaCategorie();
        caricaProdotti();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean caricaGruppi(){
        String[] gruppi = {"Parenti", "Amici", "Colleghi"};
        String[][] persone = {{"Ciccio", "Bello", "Brutto"},{"Mario", "Bella", "Brutto", "Bravo"},
                {"Francesco", "Filippo"}};
        for (int i= 0; i< gruppi.length; i++)
            creaGruppo(gruppi[i], persone[i]);
        return true;
    }

    private void creaGruppo(String nome, String[] persone){
        Gruppo g = new Gruppo();
        g.setNome(nome);
        em.persist(g);
        for(String n: persone){
            Persona p1 =  new Persona();
            p1.setNome(n);
            p1.setGruppo(g);
            g.getPersona().add(p1);
            em.persist(p1);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean caricaCategorie(){
        String[] categorie = {"Elettrodomestici", "Notebook", "SmartPhone"};
        for (int i= 0; i< categorie.length; i++)
            em.persist(new Categoria(categorie[i]));
        return true;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean caricaProdotti(){
        List<Categoria> categorie = em.createNamedQuery("findAllCategorie").getResultList();
        for (Categoria c:categorie){
            for(int i= 0; i< 5; i++) {
                Prodotto p = new Prodotto();
                p.setNome(c.getNome() + "-" + i);
                p.setPrezzo((new Random()).nextFloat()*10);
                p.setQta((new Random()).nextInt(20));
                em.persist(p);
                p.setCategoria(c);
//                c.getProdotti().add(p);
            }
        }
        return true;
    }

}
