package psw.beans;

import psw.model.DettaglioOrdine;
import psw.model.Prodotto;

import java.util.Collection;
import java.util.HashMap;

public class Carrello {

    private HashMap<Long, DettaglioOrdine> contenuto = new HashMap<Long, DettaglioOrdine>(10);

    public Carrello() {
    }

    public int size(){
        return contenuto.size();
    }

    public void addProdotto(Prodotto p){
        if(!contenuto.containsKey(p.getId())){
            DettaglioOrdine dt = new DettaglioOrdine();
            dt.setProdotto(p);
            dt.setPrezzo(p.getPrezzo());
            dt.setQta(1);
            contenuto.put(p.getId(),dt);
        }else{
            DettaglioOrdine dt = contenuto.get(p.getId());
            dt.setQta(dt.getQta()+1);
        }
    }

    public void incQta(Prodotto p, int qta){
        if(!contenuto.containsKey(p.getId())){
            DettaglioOrdine dt = new DettaglioOrdine();
            dt.setProdotto(p);
            dt.setPrezzo(p.getPrezzo());
            dt.setQta(qta);
            contenuto.put(p.getId(),dt);
        }else{
            DettaglioOrdine dt = contenuto.get(p.getId());
            dt.setQta(dt.getQta()+qta);
        }
    }

    public void remQta(Prodotto p, int qta){
        if(contenuto.containsKey(p.getId())){
            DettaglioOrdine dt = contenuto.get(p.getId());
            dt.setQta(dt.getQta()+qta);
            if(dt.getQta()<=0){
                contenuto.remove(p.getId());
            }
        }
    }

    public void remProdotto(Prodotto p){
        contenuto.remove(p.getId());
    }

    public Collection<DettaglioOrdine> getContenuto(){
        return contenuto.values();
    }

    public void svuota(){
        contenuto.clear();
    }
}
