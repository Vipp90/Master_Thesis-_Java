/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dostawcabusiness_tier;

import Warstwa_biznesowa.Fasada;
import Warstwa_biznesowa.Zamowienie;
import DostawcaWarstwa_integracji.TBase;

import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 *
 * @author Grzesiek
 */
@Stateless
public class DostawcaFacade implements DostawcaFacadeRemote {

    Fasada fasada = new Fasada();
    TBase base = new TBase(fasada);

    public Zamowienie Szukajzamowienie(Zamowienie zam) {
        return fasada.Szukajzamowienie(zam);
    }

    public Object[][] getpozycje(String numer) {
        return fasada.getpozycje(numer);
    }

    public ArrayList<String> getzapytaniaklienta(String dane) {
        return fasada.getzapytaniaklienta(dane);
    }

    public String getzapytaniaklienta1(String dane, int i) {
        return fasada.getzapytaniaklienta1(dane, i);
    }

    public String dodaj_pozycje_zamowienia(String produkt[], String kli, String nr, int ilość) {
        return fasada.dodaj_pozycje_zamowienia(produkt, kli, nr, ilość);
    }
//public ArrayList<String> getprodukty(String dane) {
//return fasada.getprodukty(dane);
//}

//public Dostawca Szukajdostawcy (String dane_dostawcy){
//return fasada.Szukajdostawcy(dane_dostawcy);
//}
    public String dodaj_zamowienie1(String danedostawcy, String nr_zam) {
        return fasada.dodaj_zamowienie1(danedostawcy, nr_zam);
    }

//public ArrayList<Zamowienie> getZamowienia() {
    //  return fasada.getZamowienia();
//}
    @Override
    public void update_data() {
        base.update_data();
    }

    public void update_produkty() {
        base.update_produkty();
    }

    public void updateklienci() {
        base.updateklienci();
    }

    public void updatepozycje() {
        base.updatepozycje();
    }

    public void updatezamowienia() {
        base.updatezamowienia();
    }

    public void dodaj_pozycje_zamowien() {
        base.dodaj_pozycje_zamowien();
    }

    public void dodaj_zamowienia() {
        base.dodaj_zamowienia();
    }
     public void dodaj_produkty() {
        base.dodaj_produkty();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
public String dodaj_produkt(String dane_produkt[], String dost, int ilosc){
    return fasada.dodaj_produkt(dane_produkt, dost, ilosc);
    
}}
