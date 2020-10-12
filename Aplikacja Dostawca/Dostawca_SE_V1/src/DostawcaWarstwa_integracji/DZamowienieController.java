/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DostawcaWarstwa_integracji;

import Controller.Controller;
import Warstwa_biznesowa.Zamowienie;
import java.util.GregorianCalendar;
import javax.persistence.EntityManager;

/**
 *
 * @author Grzesiek
 */
public class DZamowienieController extends Controller<Zamowienie> {

 public DZamowienieController(String persistence) {
        super(Zamowienie.class);
        sciezka = persistence;
        query2="select c from Zamowienie as c";

    }
    public boolean addZamowienie(Zamowienie zamowienie) {
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

   

    

    public Zamowienie[] getZamówienia() {
        return (Zamowienie[]) gets().toArray(new Zamowienie[0]);
    }

    
}
