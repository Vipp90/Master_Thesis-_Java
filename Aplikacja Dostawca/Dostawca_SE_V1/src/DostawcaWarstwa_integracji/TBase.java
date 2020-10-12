/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DostawcaWarstwa_integracji;


import Warstwa_biznesowa.Fasada;
import Warstwa_biznesowa.Klient;
import Warstwa_biznesowa.Pozycja_zamowienia;
import Warstwa_biznesowa.Produkt;

import Warstwa_biznesowa.Zamowienie;
import Warstwa_biznesowa.Zapytania;


/**
 *
 * @author Grzesiek
 */
public class TBase {

    private DProduktController produktJpaController;
    private DKlientController klientJpaController;

    private DZapytaniaController zapytaniacontroller;

   
 
    private DZamowienieController zamowieniecontroller;
    private DPozycja_zamowieniaController pozycjazamowieniaJpaController;
    private Fasada fasada;
    private Produkt produkty[];


    private Klient klienci[];
    private Pozycja_zamowienia pozycjezamowienia[];
    private Zamowienie zamowienia[];
    private Zapytania zapytania[];

    public DZamowienieController getZamowieniecontroller() {
        return zamowieniecontroller;
    }

    public void setZamowieniecontroller(DZamowienieController zamowieniecontroller) {
        this.zamowieniecontroller = zamowieniecontroller;
    }

    public DPozycja_zamowieniaController getPozycjazamowieniaJpaController() {
        return pozycjazamowieniaJpaController;
    }

    public void setPozycjazamowieniaJpaController(DPozycja_zamowieniaController pozycjazamowieniaJpaController) {
        this.pozycjazamowieniaJpaController = pozycjazamowieniaJpaController;
    }

    public DProduktController getProduktJpaController() {
        return produktJpaController;
    }

    public void setProduktJpaController(DProduktController produktJpaController) {
        this.produktJpaController = produktJpaController;
    }

    public DKlientController getKlientJpaController() {
        return klientJpaController;
    }

    public void setKlientJpaController(DKlientController klientJpaController) {
        this.klientJpaController = klientJpaController;
    }



     public DZapytaniaController getZapytaniacontroller() {
        return zapytaniacontroller;
    }

    public void setZapytaniacontroller(DZapytaniaController zapytaniacontroller) {
        this.zapytaniacontroller = zapytaniacontroller;
    }

    public TBase(Fasada fasada_) {
        fasada = fasada_;
        produktJpaController = new DProduktController("Dostawca_SEPU");
        klientJpaController = new DKlientController("Dostawca_SEPU");

        zamowieniecontroller = new DZamowienieController("Dostawca_SEPU");
   
        pozycjazamowieniaJpaController = new DPozycja_zamowieniaController("Dostawca_SEPU");
        zapytaniacontroller = new DZapytaniaController("Dostawca_SEPU");
        /*  try {
         update_data();
         } catch (Exception e) {
         }*/
    }

    public void update_data() {
      update_produkty();
     
        updatezamowienia();
   
        updateklienci();
        updatepozycje();
        updatezapytania();
        
        fasada.update_data(produkty,zamowienia, klienci, pozycjezamowienia,zapytania);
        
    }

    public void updatezamowienia() {
        zamowienia = (Zamowienie[]) zamowieniecontroller.getZamówienia();
    }
    public void updatezapytania() {
        zapytania = (Zapytania[]) zapytaniacontroller.getZapytania();
    }
    public void updatepozycje() {
        pozycjezamowienia = (Pozycja_zamowienia[]) pozycjazamowieniaJpaController.getPozycje_zamowienia();
    }

 

    public void updateklienci() {
        klienci = (Klient[]) klientJpaController.getKlients_();
    }

    public void update_produkty() {
        produkty = (Produkt[]) produktJpaController.getTProducts_();
    }

 
 public void dodaj_produkty() {
        produktJpaController.addList(fasada.getProdukty());
    }

    public void dodaj_klientow() {
        klientJpaController.addList(fasada.getKlienci());
    }


    public void dodaj_zamowienia() {
        zamowieniecontroller.addList(fasada.getZamowienia());
    }
    public void dodaj_pozycje_zamowien() {
        pozycjazamowieniaJpaController.addPozycje_(fasada.getZamowienia());
    
    }
    public void dodaj_zapytania() {
        zapytaniacontroller.addList(fasada.getZapytania());
    }
}
