/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DostawcaWarstwa_integracji;


import Controller.Controller;
import Warstwa_biznesowa.Pozycja_zamowienia;
import Warstwa_biznesowa.Zamowienie;
import java.util.ArrayList;
import javax.persistence.EntityManager;

/**
 *
 * @author Grzesiek
 */
public class DPozycja_zamowieniaController extends Controller<Pozycja_zamowienia> {

    public DPozycja_zamowieniaController(String persistence) {
        super(Pozycja_zamowienia.class);
        sciezka = persistence;
        query2="select c from Pozycja_zamowienia as c";

    }
    

   

    public boolean addPozycje_(ArrayList<Zamowienie> zamowienia) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            for (Zamowienie z : zamowienia) {
                if (z.getId() != null) {
                    for (Pozycja_zamowienia p : z.getPozycje()) {
                        if (p.getId() == null) {
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

    public Pozycja_zamowienia[] getPozycje_zamowienia() {
        return (Pozycja_zamowienia[]) gets().toArray(new Pozycja_zamowienia[0]);
    }


}
