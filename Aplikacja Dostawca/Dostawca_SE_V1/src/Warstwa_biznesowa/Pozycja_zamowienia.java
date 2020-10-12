/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

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
public class Pozycja_zamowienia extends Encja {

 private int Ilosc;

 
    public int getIlosc() {
        return Ilosc;
    }

    public void setIlosc(int Ilosc) {
        this.Ilosc = Ilosc;
    }

    @ManyToOne
    private Produkt Produkt;

    public Produkt getMProdukt() {
        return Produkt;
    }

    public void setMProdukt(Produkt produkt) {
        Produkt = produkt;
    }

    @ManyToOne
    private Zamowienie mZamowienie;

    public Zamowienie getMZamowienie() {
        return mZamowienie;
    }

    public void setMZamowienie(Zamowienie zamowienie) {
        mZamowienie = zamowienie;
    }

    public Pozycja_zamowienia() {
    
    }

    @Override
    public boolean equals(Object ob) {
      
      //  Zamowienie zamówienie1 = getMZamowienie();
    //    Zamowienie zamówienie2 = ((Pozycja_zamowienia) ob).getMZamowienie();
        Produkt produkt1 = getMProdukt();
        Produkt produkt2 = ((Pozycja_zamowienia) ob).getMProdukt();
      
       // boolean b = zamówienie1.equals(zamówienie2);
        return produkt1.equals(produkt2);
       
    }
    @Override
    public String toString() {
        return "Pozycja{" + "Produkt=" + Produkt.toString()  + ", Ilosc = " +Ilosc; };

    }
      



