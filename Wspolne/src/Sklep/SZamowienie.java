/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sklep;

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
public class SZamowienie extends Warstwa_Biznesowa1.Zamowienie1{



    @ManyToOne
    private SDostawca mdostawca;

    public SDostawca getDostawca() {
        return mdostawca;
    }

    public void setDostawca(SDostawca val) {
        mdostawca = val;

    }

    @OneToMany(mappedBy = "mZamowienie")
    private List<SPozycja_zamowienia> pozycje;

    public List<SPozycja_zamowienia> getPozycje() {
        return pozycje;
    }

    public void setPozycje(List<SPozycja_zamowienia> val) {
        pozycje = val;
    }

    public SZamowienie() {
    

    }

    public SZamowienie(SDostawca dostawca) {
        //mprodukt = prod;
        mdostawca = dostawca;
    
        pozycje = new ArrayList<>();

    }

    public SPozycja_zamowienia dodaj_pozycje(SProdukt_Dostawca produktdostawca, int ilosc) {
        SPozycja_zamowienia nowa = new SPozycja_zamowienia();
        nowa.setMZamowienie(this);
        nowa.setIlosc(ilosc);
        nowa.setMProduktDostawcy(produktdostawca);
        return (SPozycja_zamowienia)addPozycja(nowa);
    }

    SPozycja_zamowienia Szukaj_pozycje(SPozycja_zamowienia pozycja)
    {
        int idx=pozycje.indexOf(pozycja);
        if(idx!=-1)
            return pozycje.get(idx);
        return null;
    }
    
    public SPozycja_zamowienia addPozycja(SPozycja_zamowienia nowa) {
       SPozycja_zamowienia pozycja=Szukaj_pozycje(nowa);
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