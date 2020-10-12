/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Sklep.SDostawca;
import Sklep.SKlient;
import Sklep.SPozycja_zamowienia;
import Sklep.SProdukt;
import Sklep.SProdukt_Dostawca;
import Sklep.SZamowienie;


import Warstwa_biznesowa.Fasada;

/**
 *
 * @author Grzesiek
 */
public class TBase {

    private ProduktController produktJpaController;
    private KlientController klientJpaController;
    private DostawcaController dostawcacontroller;
    private Produkt_DostawcaController produktdostawcaJpaController;
    private ZamowienieController zamowieniecontroller;
    private Pozycja_zamowieniaController pozycjazamowieniaJpaController;
    private Fasada fasada;
    private SProdukt produkty[];
    private SProdukt_Dostawca produkty_dostawcy[];
    private SDostawca dostawcy[];
    private SKlient klienci[];
    private SPozycja_zamowienia pozycjezamowienia[];
    private SZamowienie zamowienia[];

    public ZamowienieController getZamowieniecontroller() {
        return zamowieniecontroller;
    }

    public void setZamowieniecontroller(ZamowienieController zamowieniecontroller) {
        this.zamowieniecontroller = zamowieniecontroller;
    }

    public Pozycja_zamowieniaController getPozycjazamowieniaJpaController() {
        return pozycjazamowieniaJpaController;
    }

    public void setPozycjazamowieniaJpaController(Pozycja_zamowieniaController pozycjazamowieniaJpaController) {
        this.pozycjazamowieniaJpaController = pozycjazamowieniaJpaController;
    }

    public ProduktController getProduktJpaController() {
        return produktJpaController;
    }

    public void setProduktJpaController(ProduktController produktJpaController) {
        this.produktJpaController = produktJpaController;
    }

    public KlientController getKlientJpaController() {
        return klientJpaController;
    }

    public void setKlientJpaController(KlientController klientJpaController) {
        this.klientJpaController = klientJpaController;
    }

    public DostawcaController getDostawcacontroller() {
        return dostawcacontroller;
    }

    public void setDostawcacontroller(DostawcaController dostawcacontroller) {
        this.dostawcacontroller = dostawcacontroller;
    }

    public Produkt_DostawcaController getProduktdostawcaJpaController() {
        return produktdostawcaJpaController;
    }

    public void setProduktdostawcaJpaController(Produkt_DostawcaController produktdostawcaJpaController) {
        this.produktdostawcaJpaController = produktdostawcaJpaController;
    }

    public TBase(Fasada fasada_) {
        fasada = fasada_;
        produktJpaController = new ProduktController("Sklep_SEPU");
        klientJpaController = new KlientController("Sklep_SEPU");
        dostawcacontroller = new DostawcaController("Sklep_SEPU");
        zamowieniecontroller = new ZamowienieController("Sklep_SEPU");
        produktdostawcaJpaController = new Produkt_DostawcaController("Sklep_SEPU");
        pozycjazamowieniaJpaController = new Pozycja_zamowieniaController("Sklep_SEPU");
          try {
            update_data();
         } catch (Exception e) {
         }
    }

    public void update_data() {
        update_produkty();
        updatedostawcy();
        updatezamowienia();
        update_produktydostawcy();
        updateklienci();
        updatepozycje();
        
        fasada.update_data(produkty, dostawcy, zamowienia, produkty_dostawcy, klienci, pozycjezamowienia);
        
    }

    public void updatezamowienia() {
        zamowienia = (SZamowienie[]) zamowieniecontroller.getZamówienia();
    }

    public void updatepozycje() {
        pozycjezamowienia = (SPozycja_zamowienia[]) pozycjazamowieniaJpaController.getPozycje_zamowienia();
    }

    public void updatedostawcy() {
        dostawcy = (SDostawca[]) dostawcacontroller.getProviders_();
    }

    public void updateklienci() {
        klienci = (SKlient[]) klientJpaController.getKlients_();
    }

    public void update_produkty() {
        produkty = (SProdukt[]) produktJpaController.getTProducts_();
    }

    public void update_produktydostawcy() {
        produkty_dostawcy = (SProdukt_Dostawca[]) produktdostawcaJpaController.getTProducts_();
    }

    public void dodaj_klientow() {
        klientJpaController.addList(fasada.getKlienci());
    }

    public void dodaj_dostawcow() {
        dostawcacontroller.addList(fasada.getDostawcy());
    }

    public void dodaj_produkty() {
        produktJpaController.addList(fasada.getProdukty());
        produktdostawcaJpaController.addList(fasada.getProduktydostawcy());
 
   }
    public void dodaj_zamowienia() {
        zamowieniecontroller.addList(fasada.getZamowienia());
    }
    public void dodaj_pozycje_zamowien() {
        pozycjazamowieniaJpaController.addPozycje_(fasada.getZamowienia());
    
    }
}
