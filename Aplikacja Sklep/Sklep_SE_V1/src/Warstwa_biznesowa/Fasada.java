/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import Sklep.SDostawca;
import Sklep.SKlient;
import Sklep.SPozycja_zamowienia;
import Sklep.SProdukt;
import Sklep.SProdukt_Dostawca;
import Sklep.SZamowienie;
import Sklep.SZapytania;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Grzesiek
 */
public class Fasada {

    private ArrayList<SDostawca> dostawcy = new ArrayList<>();
    private ArrayList<SKlient> klienci = new ArrayList<>();
    private ArrayList<SProdukt> produkty = new ArrayList<>();
    private ArrayList<SProdukt_Dostawca> produktydostawcy = new ArrayList<>();
    private ArrayList<SZamowienie> zamowienia = new ArrayList<>();
    private ArrayList<SZapytania> zapytania = new ArrayList<>();

    public ArrayList<SDostawca> getDostawcy() {
        return dostawcy;
    }

    public ArrayList<SZamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(ArrayList<SZamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public void setDostawcy(ArrayList<SDostawca> dostawcy) {
        this.dostawcy = dostawcy;
    }

    public ArrayList<SProdukt> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<SProdukt> produkty) {
        this.produkty = produkty;
    }

    public ArrayList<SKlient> getKlienci() {
        return klienci;
    }

    public void setKlienci(ArrayList<SKlient> klienci) {
        this.klienci = klienci;
    }

    public ArrayList<SProdukt_Dostawca> getProduktydostawcy() {
        return produktydostawcy;
    }

    public void setProduktydostawcy(ArrayList<SProdukt_Dostawca> produktydostawcy) {
        this.produktydostawcy = produktydostawcy;
    }

    public String dodaj_klienta(String[] dane) {
        SKlient klient = new SKlient(dane);
        if (Szukajklienta(klient) == null) {
            klienci.add(klient);
            System.out.print(klient.toString());
            return klient.toString();
        }
        return "Nie dodano";
    }

    public String dodaj_dostawce(String dane_dostawca[]) {
        SDostawca dostawca = new SDostawca(dane_dostawca);
        String jest = adddostawca(dostawca);
        if (!"Już jest".equals(jest)) {
            return "OK";
        } else {
            return null;
        }
    }

    public String adddostawca(SDostawca val) {
        boolean czy_jest = dostawcy.contains(val);
        if (czy_jest) {
            return ("Już jest");
        } else {
            dostawcy.add(val);
            System.out.println("Dostawcy w fasadzie po próbie wstawienia 2 dostawcow");
            System.out.println(getDostawcy().toString());
            return null;
        }
    }

    public String dodaj_produkt(String dane_produkt[], String dost, int ilosc) {
        SProdukt produkt = new SProdukt(dane_produkt);
        SProdukt produkt_;
        produkt.setNazwa(dane_produkt[0]);
        produkt.setProducent(dane_produkt[1]);
        produkt.setKategoria(dane_produkt[2]);
        produkt.setCena(Float.parseFloat(dane_produkt[3]));
        produkt_ = Szukajproduktu(produkt);
        SDostawca dostawca = Szukajdostawcy(dost);
        if (dostawca == null) {
            return "Brak dostawcy";
        }
        if (produkt_ == null) {
            produkty.add(produkt);
            produkt_ = produkt;
        }
        SProdukt_Dostawca produktdostawcy = new SProdukt_Dostawca(produkt_, dostawca, ilosc);
        if (!dostawca.addProdukt(produktdostawcy)) {
            return "Dostawca posiada już taki produkt";
        }
        if (!produkt_.addProdukt(produktdostawcy)) {
            return "Produkt już posiada takiego dostawce";
        }
        produktydostawcy.add(produktdostawcy);
        return "OK";
    }

    public String dodaj_zamowienie1(String danedostawcy, String nr_zam) {
        String wynik = "";
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        GregorianCalendar data1 = new GregorianCalendar();
        data1.setTime(data);
        SDostawca dostawca = Szukajdostawcy(danedostawcy);
        if (dostawca == null) {
            wynik = "Brak dostawcy";

        }
        SZamowienie zamowienie = new SZamowienie(dostawca);
        zamowienie.setData1(data1);
        zamowienie.setNr_zamówienia(nr_zam);
        if (Szukajzamowienie(nr_zam) == null) {
            zamowienia.add(zamowienie);
            dostawca.addZamowienie1(zamowienie);
        } else {
            wynik = "Juz jest";
        }
        return wynik;
    }

    public String dodaj_pozycje_zamowienia(int dane_produkt, String dost, String nr, String ilość) {
        int ilosc = Integer.parseInt(ilość);

        SDostawca dostawca = Szukajdostawcy(dost);
        if (dostawca == null) {
            return "Brak dostawcy";
        }

        SZamowienie zamowienie = new SZamowienie();
        SZamowienie zamowienie_;
        zamowienie.setNr_zamówienia(nr);
        if ((zamowienie_ = dostawca.IsZamowienie(zamowienie)) == null) {
            return "Brak zamowienia";
        }
        SProdukt_Dostawca produkt = new SProdukt_Dostawca();
        produkt = dostawca.getProduktydostawcy().get(dane_produkt);
        // Produkt produkt = new Produkt(dane_produkt);

        SProdukt_Dostawca produktdostawcy = new SProdukt_Dostawca(produkt.getMprodukt(), dostawca, 0);
        SProdukt_Dostawca prod_dost = Szukajdostawcyproduktu(produktdostawcy);
        if (prod_dost == null) {
            return "Brak produktu dostawcy";
        }
        zamowienie_.dodaj_pozycje(prod_dost, ilosc);
        return "Dodano pozycje w zamowieniu";
    }

    public SProdukt Szukajproduktu(SProdukt produkt) {
        int i = produkty.indexOf(produkt);
        if (i != -1) {
            return produkty.get(i);
        } else {
            return null;
        }
    }

    public SProdukt Szukajproduktu(String dane_produkt[]) {
        SProdukt produkt = new SProdukt();
        produkt.setNazwa(dane_produkt[0]);
        produkt.setProducent(dane_produkt[1]);
        return Szukajproduktu(produkt);
    }

    public SDostawca Szukajdostawcy(SDostawca dostawca) {
        int i = dostawcy.indexOf(dostawca);
        if (i != -1) {
            return dostawcy.get(i);
        } else {
            return null;
        }

    }

    public SProdukt_Dostawca Szukajdostawcyproduktu(SProdukt_Dostawca produktdostawca) {
        int i = produktydostawcy.indexOf(produktdostawca);
        if (i != -1) {
            return produktydostawcy.get(i);
        } else {
            return null;
        }

    }
    /*
     public Produkt_Dostawca Szukajdostawcyproduktu(Produkt_Dostawca produktdostawca, int ilosc) {
     Produkt_Dostawca produkt_dostawa = Szukajdostawcyproduktu(produktdostawca);
     if (produkt_dostawa != null) {
     // System.out.println("Znaleziono1");
     if (produkt_dostawa.spr_ilosc(ilosc) == 1) {
     //  System.out.println("Znaleziono2");
     return produkt_dostawa;
     }
     }
     //     System.out.println("Nie znaleziono");
     return null;
     }
     */

    public SDostawca Szukajdostawcy(String dane_dostawcy) {
        SDostawca dostawca = new SDostawca();
        dostawca.setNIP(dane_dostawcy);
        return Szukajdostawcy(dostawca);

    }

    public SKlient Szukajklienta(SKlient klient) {
        int i = klienci.indexOf(klient);
        if (i != -1) {
            return klienci.get(i);
        } else {
            return null;
        }
    }

    public SKlient Szukajklienta(String[] dane) {
        SKlient klient = new SKlient(dane);
        return Szukajklienta(klient);
    }

    public SZamowienie Szukajzamowienie(String numer_zam) {
        SZamowienie zam = new SZamowienie();
        zam.setNr_zamówienia(numer_zam);
        int i = zamowienia.indexOf(zam);
        if (i != -1) {
            return zamowienia.get(i);
        } else {
            return null;
        }
    }

    public synchronized Object[][] getproducts() {
        Object[][] products = new Object[produkty.size()][];
        int i = 0;
        for (SProdukt next : produkty) {
            String[] produkt = new String[4];
            produkt[0] = next.getNazwa();
            produkt[1] = next.getProducent();
            produkt[2] = next.getKategoria();
            products[i++] = produkt;

        }
        return products;
    }

    public synchronized Object[][] getproviders() {
        Object[][] providers = new Object[dostawcy.size()][];
        int i = 0;
        for (SDostawca next : dostawcy) {
            String[] dostawca = new String[4];
            dostawca[0] = next.getNazwa();
            dostawca[1] = next.getAdres_1() + ", " + next.getAdres_2();
            dostawca[2] = next.getNIP();
            providers[i++] = dostawca;

        }
        return providers;
    }

    public synchronized Object[][] getklients() {
        Object[][] klients = new Object[klienci.size()][];

        int i = 0;
        for (SKlient next : klienci) {
            String[] klient = new String[4];
            klient[0] = next.getImie();
            klient[1] = next.getNazwisko();
            klient[2] = next.getPesel();
            klients[i++] = klient;

        }
        return klients;
    }

    public synchronized Object[][] getpozycje(String numer) {
        SZamowienie zamowienie = new SZamowienie();

        int j = zamowienia.indexOf(Szukajzamowienie(numer));
        zamowienie = zamowienia.get(j);

        Object[][] pozycje = new Object[zamowienie.getPozycje().size()][];
        int i = 0;
        for (Object next : zamowienie.getPozycje()) {
            String[] pozycja = new String[4];
            pozycja[0] = ((SPozycja_zamowienia) next).getMProduktDostawcy().getMprodukt().getNazwa();
            pozycja[1] = ((SPozycja_zamowienia) next).getMProduktDostawcy().getMprodukt().getProducent();
            pozycja[2] = Integer.toString(((SPozycja_zamowienia) next).getIlosc());
            pozycje[i++] = pozycja;

        }
        return pozycje;
    }

    public ArrayList<String> getproduktydostawcy(String dane) {

        SDostawca pom = new SDostawca();
        pom = Szukajdostawcy(dane);

        return pom.getprodukty();

    }

    public synchronized void update_data(SProdukt products[], SDostawca providers[], SZamowienie zamówienia[], SProdukt_Dostawca produktydostawcy[], SKlient klients[], SPozycja_zamowienia pozycje[]) {

        boolean jest = false, jest1 = false, jest2 = false, jest3 = false;

        dostawcy.clear();
        for (SDostawca d : providers) {
            d.getZamowieniadostawcy().clear();
            dostawcy.add(d);
        }

        this.produktydostawcy.clear();
        for (SProdukt_Dostawca t : produktydostawcy) {
            this.produktydostawcy.add(t);
        }
        dostawcy.clear();
        for (SDostawca t : providers) {
            t.getProduktydostawcy().clear();
            dostawcy.add(t);
        }

        zamowienia.clear();
        for (SZamowienie k : zamówienia) {

            zamowienia.add(k);
        }

        for (SZamowienie p1 : this.zamowienia) {
            for (SDostawca p2 : dostawcy) {
                if (p1.getDostawca().equals(p2)) {
                    p2.getZamowieniadostawcy().add(p1);
                }
            }
        }

        for (SProdukt_Dostawca p1 : this.produktydostawcy) {
            for (SDostawca p2 : dostawcy) {
                if (p1.getMdostawca().equals(p2)) {
                    p2.getProduktydostawcy().add(p1);
                }
            }
        }

        produkty.clear();
        for (SProdukt t : products) {
            produkty.add(t);
            t.getDostawcyproduktu().clear();
        }

        for (SProdukt_Dostawca p1 : this.produktydostawcy) {
            for (SProdukt p2 : produkty) {
                if (p1.getMprodukt().equals(p2)) {
                    p2.getDostawcyproduktu().add(p1);
                }
            }
        }

        for (SPozycja_zamowienia p1 : pozycje) {
            for (SZamowienie p2 : zamowienia) {
                if (p1.getMZamowienie().equals(p2)) {
                    p2.getPozycje().add(p1);
                }
            }
        }

    }
}
