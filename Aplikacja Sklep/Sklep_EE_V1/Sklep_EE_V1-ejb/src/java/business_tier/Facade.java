/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import Sklep.SDostawca;
import Sklep.SZamowienie;

import Warstwa_biznesowa.Fasada;

import Warstwa_integracji.TBase;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author Grzesiek
 */
@Stateless
public class Facade implements FacadeRemote {

    Fasada fasada = new Fasada();
    TBase base = new TBase(fasada);

    public SZamowienie Szukajzamowienie(String numer_zam) {
        return fasada.Szukajzamowienie(numer_zam);
    }

    public SDostawca Szukajdostawcy(String dane_dostawcy) {
        return fasada.Szukajdostawcy(dane_dostawcy);
    }

    public SDostawca Szukajdostawcy(SDostawca dostawca) {
        return fasada.Szukajdostawcy(dostawca);
    }

    public Object[][] getproviders() {
        return fasada.getproviders();

    }

    public Object[][] getpozycje(String numer) {
        return fasada.getpozycje(numer);
    }

    public ArrayList<String> getproduktydostawcy(String dane) {
        return fasada.getproduktydostawcy(dane);
    }

    public String dodaj_pozycje_zamowienia(int dane_produkt, String dost, String nr, String ilość) {
        return fasada.dodaj_pozycje_zamowienia(dane_produkt, dost, nr, ilość);
    }

//public ArrayList<String> getprodukty(String dane) {
//return fasada.getprodukty(dane);
//}

//public Dostawca Szukajdostawcy (String dane_dostawcy){
//return fasada.Szukajdostawcy(dane_dostawcy);
//}
    public String dodaj_produkt(String dane_produkt[], String dost, int ilosc) {
        return fasada.dodaj_produkt(dane_produkt, dost, ilosc);
    }

    public String dodaj_dostawce(String dane_dostawca[]) {
        return fasada.dodaj_dostawce(dane_dostawca);
    }

    public String dodaj_klienta(String dane_klient[]) {
        return fasada.dodaj_klienta(dane_klient);
    }

    public void dodaj_dostawcow() {
        base.dodaj_dostawcow();
    }

    public void dodaj_produkty() {
        base.dodaj_produkty();
    }

    public void dodaj_klientow() {
        base.dodaj_klientow();
    }

    public String dodaj_zamowienie1(String danedostawcy, String nr_zam) {
        return fasada.dodaj_zamowienie1(danedostawcy, nr_zam);
    }

    public Object[][] getklients() {
        return fasada.getklients();
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

    public void update_produktydostawcy() {
        base.update_produktydostawcy();
    }

    public void updatedostawcy() {
        base.updatedostawcy();
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
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
