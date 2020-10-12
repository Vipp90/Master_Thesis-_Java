/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Grzesiek
 */
@Entity
@Table
public class Klient extends Warstwa_Biznesowa1.Klient1 {

 
    private String Nazwa;
    private String NIP;

    private String Nrkonta;

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String Nazwa) {
        this.Nazwa = Nazwa;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getNrkonta() {
        return Nrkonta;
    }

    public void setNrkonta(String Nrkonta) {
        this.Nrkonta = Nrkonta;
    }

    @OneToMany(mappedBy = "mklient", cascade = CascadeType.PERSIST)
    private List<Zamowienie> zamowieniaklienta;

    public List<Zamowienie> getZamowieniaklienta() {
        return zamowieniaklienta;
    }

    public void setZamowieniaklienta(List<Zamowienie> val) {
        zamowieniaklienta = val;
    }

    @OneToMany(mappedBy = "mklient", cascade = CascadeType.PERSIST)
    private List<Zapytania> zapytaniaodklienta;

    public List<Zapytania> getZapytaniaodklienta() {
        return zapytaniaodklienta;
    }

    public void setZapytaniaodklienta(List<Zapytania> val) {
        zapytaniaodklienta = val;
    }

    public Klient() {
      
    }

    public Klient(String[] dane) {
        Nazwa = dane[0];
        NIP = dane[1];
       
        Nrkonta = dane[5];
        zamowieniaklienta = new ArrayList();
        zapytaniaodklienta = new ArrayList();
    }

    public Zamowienie IsZamowienie(Zamowienie nowy) {

        for (Zamowienie p : zamowieniaklienta) {
            if (nowy.equals(p)) {
                return p;
            }
        }
        return null;

    }

    public Zamowienie addZamowienie(Zamowienie nowy) {
        if (IsZamowienie(nowy) == null) {
            this.zamowieniaklienta.add(nowy);
            return nowy;
        }
        return null;
    }

    public void addZamowienie1(Zamowienie nowy) {
        this.zamowieniaklienta.add(nowy);
    }

    public void addZapytanie1(Zapytania nowy) {
        this.zapytaniaodklienta.add(nowy);
    }

    public ArrayList<String> getzapytania() {
        ArrayList<String> zapytania = new ArrayList<String>();
        Iterator<Zapytania> help = this.zapytaniaodklienta.iterator();
        while (help.hasNext()) {
            Zapytania next = help.next();
            zapytania.add(next.toString());
        }
        return zapytania;
    }

    public String getzapytania1(int i) {
        Zapytania zapytanie = this.zapytaniaodklienta.get(i);
        String wynik = zapytanie.getTresc_zapytania();
        return wynik;
    }

    @Override
    public String toString() {
        String pom = "\nNazwa: " + this.Nazwa + ", NIP: " + this.NIP;
        return pom;
    }

    @Override
    public boolean equals(Object ob) {

        String NIP1 = getNIP();
        String NIP2 = ((Klient) ob).getNIP();
        boolean a = false;
        a = NIP1.equals(NIP2);

        return a;

    }

}
