package psw.ejb;

import psw.model.Prodotto;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class OrderException extends Exception {

    private Prodotto p;
    private boolean tipo; //false per la quantità e true per il prezzo


    public OrderException() {
        // TODO Auto-generated constructor stub
    }

    public OrderException(Prodotto p, boolean tipo) {
        this.p=p;
        this.tipo=tipo;
    }

    public Prodotto getP() {
        return p;
    }

    public boolean isTipo() {
        return tipo;
    }

}
