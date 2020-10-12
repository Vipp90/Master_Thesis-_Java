/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Controller.Controller;
import Sklep.SPozycja_zamowienia;
import Sklep.SZamowienie;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Grzesiek
 */
public class Pozycja_zamowieniaController extends Controller<SPozycja_zamowienia> {

    public Pozycja_zamowieniaController(String persistence) {
        super(SPozycja_zamowienia.class);
        sciezka = persistence;
        query2="select c from SPozycja_zamowienia as c";

    }
   /*  private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(sciezka);
        }
        return emf.createEntityManager();
    }*/

  /*  public boolean addPozycja(SPozycja_zamowienia pozycja) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pozycja);
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/
    
  /*   public boolean addPozycja(SPozycja_zamowienia pozycja)
     {
         return Add(pozycja);
     }*/

  /*  public boolean addPozycje(ArrayList<SPozycja_zamowienia> pozycje) {
        EntityManager em = getEntityManager();
        SPozycja_zamowienia nowapozycja = null;
        try {
            Iterator it = pozycje.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                nowapozycja = (SPozycja_zamowienia) it.next();
                if (nowapozycja.getId() == null) {
                    em.persist(nowapozycja);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/

   /*  public boolean addPozycje(ArrayList<SPozycja_zamowienia> pozycje)
     {
         return addList(pozycje);
     }*/
     
    public boolean addPozycje_(ArrayList<SZamowienie> zamowienia) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            for (SZamowienie z : zamowienia) {
                if (z.getId() != null) {
                    for (Object p : z.getPozycje()) {
                        if (((SPozycja_zamowienia)p).getId() == null) {
                            em.persist(p);
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }
    
     public SPozycja_zamowienia[] getPozycje_zamowienia() {
        return (SPozycja_zamowienia[]) gets().toArray(new SPozycja_zamowienia[0]);
    }

  /*  public SPozycja_zamowienia[] getPozycje_zamowienia() {
        return (SPozycja_zamowienia[]) getpozycje().toArray(new SPozycja_zamowienia[0]);
    }

    public List<SPozycja_zamowienia> getpozycje() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("select c from SPozycja_zamowienia as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }*/
}
