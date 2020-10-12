/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;


import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Grzesiek
 */
@Entity
@Table
public class Zamowienie extends Warstwa_Biznesowa1.Zamowienie1 {


    @ManyToOne
    private Klient mklient;

    public Klient getKlient() {
        return mklient;
    }

    public void setKlient(Klient val) {
        mklient = val;

    }

    @OneToMany(mappedBy = "mZamowienie")
    private List<Pozycja_zamowienia> pozycje;

    public List<Pozycja_zamowienia> getPozycje() {
        return pozycje;
    }

    public void setPozycje(List<Pozycja_zamowienia> val) {
        pozycje = val;
    }

    public Zamowienie() {
   

    }

    public Zamowienie(Klient klient) {
        //mprodukt = prod;
        mklient = klient;

        pozycje = new ArrayList<>();

    }


    @Transient

    public Pozycja_zamowienia dodaj_pozycje(Produkt produkt, int ilosc) {
        Pozycja_zamowienia nowa = new Pozycja_zamowienia();
        nowa.setMZamowienie(this);
        nowa.setIlosc(ilosc);
        nowa.setMProdukt(produkt);     
        return (addPozycja(nowa));
    }

    Pozycja_zamowienia Szukaj_pozycje(Pozycja_zamowienia pozycja)
    {
        int idx=pozycje.indexOf(pozycja);
        if(idx!=-1)
            return pozycje.get(idx);
        return null;
    }
    
    public Pozycja_zamowienia addPozycja(Pozycja_zamowienia nowa) {
       Pozycja_zamowienia pozycja=Szukaj_pozycje(nowa);
       if(pozycja==null)
           pozycje.add(nowa);
       else
       {
           int ilosc=pozycja.getIlosc()+nowa.getIlosc();
           pozycja.setIlosc(ilosc);
       }
       return pozycja;
    }

   
   

   

}