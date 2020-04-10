package psw.ejb;

import psw.model.DettaglioOrdine;
import psw.model.Ordine;
import psw.model.Prodotto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.Collection;
import java.util.Date;

@Stateless(name = "OrderManagerEJB")
public class OrderManagerBean {

    @EJB
    OrderBean oEJB;

    @EJB
    ProdottoBean pEJB;

    public OrderManagerBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void PlaceOrder(Date d, Collection<DettaglioOrdine> dts) throws OrderException{
        Ordine o = new Ordine();
        o.setData(d);
        oEJB.inserisciOrdine(o);
        for(DettaglioOrdine dt:dts) {
            Prodotto p = pEJB.find(dt.getProdotto().getId());
            if(p==null) System.out.println("prodotto è null");
            if(p.getQta()<dt.getQta()) {
                throw new OrderException(p,false);
            }
            if (p.getPrezzo()!=dt.getPrezzo()) {
                throw new OrderException(p,true);
            }

            dt.setProdotto(p);
            oEJB.inserisciDettaglioOrdine(dt);

            o.getDettagli().add(dt);

            //Aggiorna la quantità disponibile
            p.setQta(p.getQta()-dt.getQta());
        }
    }

}
