/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Grzesiek
 */
public class Fasada {

    private ArrayList<Klient> klienci = new ArrayList<>();
    private ArrayList<Zapytania> zapytania = new ArrayList<>();

    private ArrayList<Produkt> produkty = new ArrayList<>();

    private ArrayList<Zamowienie> zamowienia = new ArrayList<>();

    public ArrayList<Zapytania> getZapytania() {
        return zapytania;
    }

    public void setZapytania(ArrayList<Zapytania> zapytania) {
        this.zapytania = zapytania;
    }

    public ArrayList<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(ArrayList<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }

    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt> produkty) {
        this.produkty = produkty;
    }

    public ArrayList<Klient> getKlienci() {
        return klienci;
    }

    public void setKlienci(ArrayList<Klient> klienci) {
        this.klienci = klienci;
    }

    public String dodaj_klienta(String[] dane) {
        Klient klient = new Klient(dane);
        if (Szukajklienta(klient) == null) {
            klienci.add(klient);
            return klient.toString();
        }
        return "Nie dodano";
    }

    public String Dodajzapytanie(Zapytania val) {

        Produkt produkt = Szukajproduktu(val.getProdukt());
        if (produkt == null) {
            System.out.println("Brak produktu");
            return "Brak produktu";
        }

        if (Szukajzapytanie(val) == null) {
            zapytania.add(val);
            val.getKlient().addZapytanie1(val);
            System.out.println("Dodano zapytanie");
        }
        return "Dodano zapytanie";
    }

    public String dodaj_zamowienie1(String daneklienta, String nr_zam) {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        GregorianCalendar data1 = new GregorianCalendar();
        data1.setTime(data);
        Klient klient = Szukajklienta(daneklienta);
        if (klient == null) {
            System.out.println("Brak klienta");
            return "Brak klienta";
        }
        Zamowienie zamowienie = new Zamowienie(klient);
        zamowienie.setData1(data1);
        zamowienie.setNr_zamówienia(nr_zam);
        if (Szukajzamowienie(zamowienie) == null) {
            zamowienia.add(zamowienie);
            klient.addZamowienie1(zamowienie);
            //System.out.println("Dodano zamówienie");

            return "Dodano zamowienie";
        } else {
            return "Istnieje";
        }
    }

    public String dodaj_pozycje_zamowienia(String produkt[], String kli, String nr, int ilosc) {
      // int ilosc = Integer.parseInt(ilość);

        Klient klient = Szukajklienta("1");

        if (klient == null) {
            return "Brak klienta";
        }

        Zamowienie zamowienie = new Zamowienie(), zamowienie_;
        zamowienie.setNr_zamówienia(nr);
        if ((zamowienie_ = klient.IsZamowienie(zamowienie)) == null) {
            return "Brak zamowienia";
        }
        Produkt produkt1 = new Produkt();

        produkt1 = this.Szukajproduktu(produkt);
        if (produkt1 == null) {
            return "Brak Produktu";
        }
       // produkt = dostawca.getProduktydostawcy().get(dane_produkt);
        // Produkt produkt = new Produkt(dane_produkt);

        zamowienie_.dodaj_pozycje(produkt1, ilosc);
        return "Dodano pozycje w zamowieniu";
    }

    public Produkt Szukajproduktu(Produkt produkt) {
        int i = produkty.indexOf(produkt);
        if (i != -1) {
            return produkty.get(i);
        } else {
            return null;
        }
    }

    public Produkt Szukajproduktu(String dane_produkt[]) {
        Produkt produkt = new Produkt();
        produkt.setNazwa(dane_produkt[0]);
        produkt.setProducent(dane_produkt[1]);
        produkt.setKategoria(dane_produkt[2]);
        return Szukajproduktu(produkt);
    }

    public Klient Szukajklienta(String dane_klienta) {
        Klient klient = new Klient();
        klient.setNIP(dane_klienta);
        return Szukajklienta(klient);

    }
 public String dodaj_produkt(String dane_produkt[], String dost, int ilosc) {
        Produkt produkt = new Produkt(dane_produkt);
        Produkt produkt_;
        produkt.setNazwa(dane_produkt[0]);
        produkt.setProducent(dane_produkt[1]);
        produkt.setKategoria(dane_produkt[2]);
        produkt.setCena(Float.parseFloat(dane_produkt[3]));
        produkt_ = Szukajproduktu(produkt);
        
        if (produkt_ == null) {
            produkty.add(produkt);
            produkt_ = produkt;
        }
        
        
       
        return "OK";
    }
    public Klient Szukajklienta(Klient klient) {
        int i = klienci.indexOf(klient);
        if (i != -1) {
            return klienci.get(i);
        } else {
            return null;
        }
    }

    public Zamowienie Szukajzamowienie(Zamowienie zam) {
        int i = zamowienia.indexOf(zam);
        if (i != -1) {
            return zamowienia.get(i);
        } else {
            return null;
        }
    }

    public Zapytania Szukajzapytanie(Zapytania zap) {
        int i = zapytania.indexOf(zap);
        if (i != -1) {
            return zapytania.get(i);
        } else {
            return null;
        }
    }

    public synchronized Object[][] getproducts() {
        Object[][] products = new Object[produkty.size()][];
        int i = 0;
        for (Produkt next : produkty) {
            String[] produkt = new String[4];
            produkt[0] = next.getNazwa();
            produkt[1] = next.getProducent();
            produkt[2] = next.getKategoria();
            products[i++] = produkt;

        }
        return products;
    }

    public synchronized Object[][] getpozycje(String numer) {
        Zamowienie zamowienie = new Zamowienie();
        zamowienie.setNr_zamówienia(numer);
        int j = zamowienia.indexOf(Szukajzamowienie(zamowienie));
        zamowienie = zamowienia.get(j);

        Object[][] pozycje = new Object[zamowienie.getPozycje().size()][];
        int i = 0;
        for (Pozycja_zamowienia next : zamowienie.getPozycje()) {
            String[] pozycja = new String[4];
            pozycja[0] = ((Pozycja_zamowienia)next).getMProdukt().getNazwa();
            pozycja[1] = ((Pozycja_zamowienia)next).getMProdukt().getProducent();
            pozycja[2] = Integer.toString(next.getIlosc());
            pozycje[i++] = pozycja;

        }
        return pozycje;
    }

    public ArrayList<String> getzapytaniaklienta(String dane) {

        Klient pom = new Klient();
        pom = Szukajklienta(dane);

        return pom.getzapytania();

    }

    public String getzapytaniaklienta1(String dane, int i) {

        Klient pom = new Klient();
        pom = Szukajklienta(dane);

        return pom.getzapytania1(i);

    }

    public synchronized void update_data(Produkt products[], Zamowienie zamówienia[], Klient klients[], Pozycja_zamowienia pozycje[], Zapytania request[]) {

        boolean jest = false, jest1 = false, jest2 = false, jest3 = false;

        klienci.clear();
        for (Klient d : klients) {
            d.getZamowieniaklienta().clear();
            klienci.add(d);
        }

        klienci.clear();
        for (Klient d : klients) {
            d.getZapytaniaodklienta().clear();
            klienci.add(d);
        }

        zamowienia.clear();
        for (Zamowienie k : zamówienia) {

            zamowienia.add(k);
        }
        zapytania.clear();
        for (Zapytania k : request) {

            zapytania.add(k);
        }

        for (Zamowienie p1 : this.zamowienia) {
            for (Klient p2 : klienci) {
                if (p1.getKlient().equals(p2)) {
                    p2.getZamowieniaklienta().add(p1);
                }
            }
        }
        for (Zapytania p1 : this.zapytania) {
            for (Klient p2 : klienci) {
                if (p1.getKlient().equals(p2)) {
                    p2.getZapytaniaodklienta().add(p1);
                }
            }
        }

        for (Zapytania p1 : this.zapytania) {
            for (Produkt p2 : produkty) {
                if (p1.getProdukt().equals(p2)) {
                    p2.getZapytaniaoprodukt().add(p1);
                }
            }
        }

        produkty.clear();
        for (Produkt t : products) {
            produkty.add(t);
            t.getZapytaniaoprodukt().clear();
        }

        for (Pozycja_zamowienia p1 : pozycje) {
            for (Zamowienie p2 : zamowienia) {
                if (p1.getMZamowienie().equals(p2)) {
                    p2.getPozycje().add(p1);
                }
            }
        }

    }
}
