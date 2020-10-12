/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;





import java.util.ArrayList;
import javax.ejb.Remote;


/**
 *
 * @author Grzesiek
 */
@Remote
public interface FacadeRemote {
    public Object [][] getproviders();
     public  Object [][] getpozycje(String numer);
 



 ///  public void Zapytanieoprodukt(int wybrany, String dane_dostawcy, String zapytanie);
// public Dostawca Szukajdostawcy (String dane_dostawcy);
 public String dodaj_produkt (String dane_produkt[], String dost, int ilosc);
 public String dodaj_dostawce (String dane_dostawca[]);
 public String dodaj_klienta (String dane_klient[]);
// public int dodaj_zamowienie (int wybrany, String danedostawcy, String daneklienta, String ilosc, int licznik, String numerzamowienia,int i);
 public  Object [][] getklients(); 
public ArrayList<String> getproduktydostawcy(String dane);
public String dodaj_pozycje_zamowienia(int dane_produkt, String dost, String nr, String ilość);

 public void dodaj_dostawcow();
  public void dodaj_klientow();
   public void dodaj_produkty();
public String dodaj_zamowienie1(String danedostawcy, String nr_zam);   

    /**
     *
     
     * @return
     */

 
  //  public ArrayList<Zamowienie> getZamowienia();
 
 public void update_data();
   // public void update_egzemplarze();
    public void update_produkty();
    public void update_produktydostawcy();
    public void updatedostawcy();
    public void updateklienci();
    public void updatepozycje();
    public void updatezamowienia();
    public void dodaj_pozycje_zamowien();
     public void dodaj_zamowienia();


}
