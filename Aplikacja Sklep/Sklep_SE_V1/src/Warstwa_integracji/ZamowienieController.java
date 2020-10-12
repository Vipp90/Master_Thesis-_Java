/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Controller.Controller;
import Sklep.SZamowienie;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;

/**
 *
 * @author Grzesiek
 */
public class ZamowienieController extends Controller<SZamowienie> {

    //String sciezka;

    public ZamowienieController(String persistence) {
        super(SZamowienie.class);
        sciezka = persistence;
        query2="select c from SZamowienie as c";

    }
   /* private EntityManagerFactory emf = null;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(sciezka);
        }
        return emf.createEntityManager();
    }*/

    public boolean addZamowienie(SZamowienie zamowienie) {
        EntityManager em = getEntityManager();
        int miesiac = zamowienie.getData().get(GregorianCalendar.MONTH) + 1;
        int rok = zamowienie.getData().get(GregorianCalendar.YEAR);
        int dzien = zamowienie.getData().get(GregorianCalendar.DAY_OF_MONTH);
        try {

            em.getTransaction().begin();
            em.persist(zamowienie);
            zamowienie.setNr_zamówienia(zamowienie.getId() + "/" + rok + "/" + miesiac + "/" + dzien);
            em.getTransaction().commit();
        } finally {
            em.close();

            return false;
        }

    }

  /*  public boolean addZamowieniaList(Collection<SZamowienie> zamowienia) {
        EntityManager em = getEntityManager();

        SZamowienie nowezamowienie;
        try {
            Iterator it = zamowienia.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                nowezamowienie = (SZamowienie) it.next();
                int miesiac = nowezamowienie.getData().get(GregorianCalendar.MONTH) + 1;
                int rok = nowezamowienie.getData().get(GregorianCalendar.YEAR);
                int dzien = nowezamowienie.getData().get(GregorianCalendar.DAY_OF_MONTH);
                if (nowezamowienie.getId() == null) {
                    em.persist(nowezamowienie);
                    //nowezamowienie.setNr_zamówienia(nowezamowienie.getId() + "/" + rok + "/" + miesiac + "/" + dzien);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/
 /*   public boolean addZamowieniaList(Collection<SZamowienie> zamowienia) {
        return addList(zamowienia);
    }
    
    public boolean addZamowienie(ArrayList<SZamowienie> zamówienia) {
        return addList(zamówienia);
    }*/

   /* public boolean addZamowienie(ArrayList<SZamowienie> zamówienia) {
        EntityManager em = getEntityManager();
        SZamowienie nowezamowienie;
        try {
            Iterator it = zamówienia.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                nowezamowienie = (SZamowienie) it.next();
                if (nowezamowienie.getId() == null) {//to będdzie działać, jeśli zamowienia w aplikacji będą inicjowane zawartością bazy danych
                    em.persist(nowezamowienie);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/
    
    public SZamowienie[] getZamówienia() {
        return (SZamowienie[]) gets().toArray(new SZamowienie[0]);
    }

  /*  public SZamowienie[] getZamówienia() {
        return (SZamowienie[]) getzamowienia().toArray(new SZamowienie[0]);
    }

    public List<SZamowienie> getzamowienia() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("select c from SZamowienie as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }*/
}
