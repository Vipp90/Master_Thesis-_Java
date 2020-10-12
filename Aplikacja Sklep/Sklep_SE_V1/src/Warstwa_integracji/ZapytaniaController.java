/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Controller.Controller;
import Sklep.SZamowienie;
import Sklep.SZapytania;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.EntityManager;

/**
 *
 * @author Grzesiek
 */
public class ZapytaniaController extends Controller<SZapytania> {

  //  private EntityManagerFactory emf = null;
    //   String sciezka;
    public ZapytaniaController(String persistence) {
        super(SZapytania.class);
        sciezka = persistence;
        query2 = "select c from SZapytania as c";

    }

    /*  private EntityManager getEntityManager() {
     if (emf == null) {
     emf = Persistence.createEntityManagerFactory(sciezka);
     }
     return emf.createEntityManager();
     }*/

    /*  public boolean addZapytaniaList(Collection<SZapytania> zapytania) {
     EntityManager em = getEntityManager();

     SZapytania nowezapytanie;
     try {
     Iterator it = zapytania.iterator();
     em.getTransaction().begin();
     while (it.hasNext()) {
     nowezapytanie = (SZapytania) it.next();
     int miesiac = nowezapytanie.getData().get(GregorianCalendar.MONTH) + 1;
     int rok = nowezapytanie.getData().get(GregorianCalendar.YEAR);
     int dzien = nowezapytanie.getData().get(GregorianCalendar.DAY_OF_MONTH);
     if (nowezapytanie.getId() == null) {
     em.persist(nowezapytanie);
     //nowezamowienie.setNr_zamówienia(nowezamowienie.getId() + "/" + rok + "/" + miesiac + "/" + dzien);
     }
     }
     em.getTransaction().commit();
     } finally {
     em.close();
     return false;
     }
     }*/
  /*  public boolean addZapytaniaList(Collection<SZapytania> zapytania) {
        return addList(zapytania);
    }*/

    public boolean addZamowienie(ArrayList<SZamowienie> zamówienia) {
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
    }

    /*      public SZapytania[] getZapytania() {
     return (SZapytania[]) getzapytania().toArray(new SZapytania[0]);
     }

     public List<SZapytania> getzapytania() {
     EntityManager em = getEntityManager();
     try {
     javax.persistence.Query q
     = em.createQuery("select c from SZapytania as c");
     return q.getResultList();
     } finally {
     em.close();
     }
     }*/
    public SZapytania[] getZapytania() {
        return (SZapytania[]) gets().toArray(new SZapytania[0]);
    }
}
