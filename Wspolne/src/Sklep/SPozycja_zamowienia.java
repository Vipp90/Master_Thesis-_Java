/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sklep;

import Warstwa_Biznesowa1.Encja;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Grzesiek
 */
@Entity

@Table
public class SPozycja_zamowienia extends Encja {

private int Ilosc;

  

    public SPozycja_zamowienia() {
    }

    public int getIlosc() {
        return Ilosc;
    }

    public void setIlosc(int Ilosc) {
        this.Ilosc = Ilosc;
    }

    @ManyToOne
    private SProdukt_Dostawca mProduktDostawcy;

    public SProdukt_Dostawca getMProduktDostawcy() {
        return mProduktDostawcy;
    }

    public void setMProduktDostawcy(SProdukt_Dostawca produkt) {
        mProduktDostawcy = produkt;
    }

    @ManyToOne
    private SZamowienie mZamowienie;

    public SZamowienie getMZamowienie() {
        return mZamowienie;
    }

    public void setMZamowienie(SZamowienie zamowienie) {
        mZamowienie = zamowienie;
    }

  
    @Override
    public boolean equals(Object ob) {
      
      //  Zamowienie zamówienie1 = getMZamowienie();
    //    Zamowienie zamówienie2 = ((Pozycja_zamowienia) ob).getMZamowienie();
        SProdukt_Dostawca produkt1 = getMProduktDostawcy();
        SProdukt_Dostawca produkt2 = ((SPozycja_zamowienia) ob).getMProduktDostawcy();
      
       // boolean b = zamówienie1.equals(zamówienie2);
        return produkt1.equals(produkt2);
       
    }
    @Override
    public String toString() {
        return "Pozycja{" + "Produkt=" + mProduktDostawcy.toString() + ", Ilosc = " +Ilosc; };

    }
      



