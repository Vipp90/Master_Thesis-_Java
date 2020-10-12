/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DostawcaWarstwa_integracji;


import Controller.Controller;
import Warstwa_biznesowa.Produkt;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Grzesiek
 */
public class DProduktController extends Controller<Produkt> {

     public DProduktController(String persistence) {
        super(Produkt.class);
        sciezka = persistence;
        query1="from Produkt k where k.id";
        query2="select c from Produkt as c";

    }

    

    

    public Produkt[] getTProducts_() {
        return (Produkt[]) gets().toArray(new Produkt[0]);
    }



  /*  public boolean addTProduktydostawcy(ArrayList<Dostawca> dostawcy) {
        EntityManager em = getEntityManager();
        Produkt nowyprodukt = null;
        Iterator it = dostawcy.iterator();
        em.getTransaction().begin();
        try {
            while (it.hasNext()) {
                Dostawca nowydostawca = (Dostawca) it.next();
                if (nowydostawca.getId() == null) {
                    continue;
                }
                Iterator it_ = nowydostawca.getProdukt().iterator();
                while (it_.hasNext()) {
                    nowyprodukt = (Produkt) it_.next();
                    if (nowyprodukt.getId() == null) {
                        em.persist(nowyprodukt);
                    }
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }
    /*
     public void savetytul (Tytul_filmu current){
     EntityManager em = getEntityManager();
     try{
     em.getTransaction().begin();
     Tytul_filmu kk = em.find(Tytul_filmu.class, current.getId());
     kk.setTytul_polski(current.getTytul_polski());
     kk.setRezyser(current.getRezyser());
     kk.setRok_produkcji(current.getRok_produkcji());
     em.persist(kk);
     em.getTransaction().commit();
     }finally {
     em.close();
     }
     }
     public void deletetytul(Long id){
     EntityManager em = getEntityManager();
     try{
     em.getTransaction().begin();
     Tytul_filmu kk = em.find(Tytul_filmu.class, id);
     em.remove(kk);
     em.getTransaction().commit();
     }finally {
     em.close();
     }
     }
     */

    
}
