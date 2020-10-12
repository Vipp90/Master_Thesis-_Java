/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_Biznesowa1;

import java.beans.Transient;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Grzesiek
 */
@Entity
public class Zamowienie1 extends Encja {

   // private Class<T> entityClass; //może się przydać, w obecnym kodzie nie jest używany ten atrybut

  //  public Zamowienie1(Class<T> entityClass) {
   //     this.entityClass = entityClass;
  //  }
    /*@Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     public Long getId() {
     return id;
     }

     public void setId(Long id) {
     this.id = id;
     }
     */

    private String nr_zamówienia;
    @Temporal(javax.persistence.TemporalType.DATE)
    private GregorianCalendar data;

    /*
    @OneToMany(mappedBy = "mZamowienie")
    public List<Pozycja_zamowienia1> pozycje;

    public void setPozycje(List<Pozycja_zamowienia1> pozycje) {
        this.pozycje = pozycje;
    }

    public List<Pozycja_zamowienia1> getPozycje() {
        return pozycje;
    }

    Pozycja_zamowienia1 Szukaj_pozycje(Pozycja_zamowienia1 pozycja) {
        int idx = pozycje.indexOf(pozycja);
        if (idx != -1) {
            return pozycje.get(idx);
        }
        return null;
    }

    public Pozycja_zamowienia1 addPozycja(Pozycja_zamowienia1 nowa) {
        Pozycja_zamowienia1 pozycja = Szukaj_pozycje(nowa);
        if (pozycja == null) {
            pozycje.add(nowa);
        } else {
            int ilosc =  pozycja.getIlosc() + nowa.getIlosc();
            pozycja.setIlosc(ilosc);
        }
        return pozycja;
    }
*/
    public Zamowienie1() {
        setId(null);

    }

    public Zamowienie1(String nr_zam) {
        //mprodukt = prod;

        nr_zamówienia = nr_zam;

    }

    @Transient

    public GregorianCalendar getData() {
        return data;
    }

    public void setData1(GregorianCalendar data1) {
        this.data = data1;
    }

    public String getNr_zamówienia() {
        return nr_zamówienia;
    }

    public void setNr_zamówienia(String nr_zamówienia) {
        this.nr_zamówienia = nr_zamówienia;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zamowienie1 other = (Zamowienie1) obj;

        if (getNr_zamówienia().equals(other.getNr_zamówienia())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Zamowienie{" + "nr_zamowienia=" + nr_zamówienia;
    }

}
