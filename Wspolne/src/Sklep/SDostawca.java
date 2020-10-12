/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sklep;

import Warstwa_Biznesowa1.Klient1;
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
public class SDostawca extends Klient1 {

    private String Nazwa;
    private String NIP;
  /* private String Adres_1;
    private String Adres_2;
    private String NrTelefon;*/
    private String Nrkonta;

  /*  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long val) {
        this.id = val;
    }*/

    @OneToMany(mappedBy = "mdostawca", cascade = CascadeType.PERSIST)
    private List<SProdukt_Dostawca> produktydostawcy;

    public List<SProdukt_Dostawca> getProduktydostawcy() {
        return produktydostawcy;
    }

    public void setProduktydostawcy(List<SProdukt_Dostawca> val) {
        produktydostawcy = val;
    }

    @OneToMany(mappedBy = "mdostawca", cascade = CascadeType.PERSIST)
    private List<SZamowienie> zamowieniadostawcy;

    public List<SZamowienie> getZamowieniadostawcy() {
        return zamowieniadostawcy;
    }

    public void setZamowieniadostawcy(List<SZamowienie> val) {
        zamowieniadostawcy = val;
    }

    @OneToMany(mappedBy = "mdostawca", cascade = CascadeType.PERSIST)
    private List<SZapytania> zapytaniadodostawcy;

    public List<SZapytania> getZapytaniadodostawcy() {
        return zapytaniadodostawcy;
    }

    public void setZapytaniadodostawcy(List<SZapytania> val) {
        zapytaniadodostawcy = val;
    }

    public SDostawca() {
      
    }

    public SDostawca(String[] dane) {
        Nazwa = dane[0];
        NIP = dane[1];
        Adres_1 = dane[2];
        Adres_2 = dane[3];
        NrTelefon = dane[4];
        Nrkonta = dane[5];
        zamowieniadostawcy = new ArrayList();
        zapytaniadodostawcy = new ArrayList();
        produktydostawcy = new ArrayList();
    }

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

    @Override
    public String toString() {
        String pom = "\nNazwa: " + this.Nazwa + ", Adres: " + this.Adres_1 + "\n" + this.Adres_2 + ", NIP: " + this.NIP;
        return pom;
    }

    @Override
    public boolean equals(Object ob) {

        String NIP1 = getNIP();
        String NIP2 = ((SDostawca) ob).getNIP();
        boolean a = false;
        a = NIP1.equals(NIP2);

        return a;

    }

    public boolean IsProdukt(SProdukt_Dostawca nowy) {
        SProdukt pom = nowy.getMprodukt();
        for (SProdukt_Dostawca p : produktydostawcy) {
            if (pom.equals(p.getMprodukt())) {
                return false;
            }
        }
        return true;

    }

    public boolean addProdukt(SProdukt_Dostawca nowy) {
        if (IsProdukt(nowy)) {
            this.produktydostawcy.add(nowy);
            return true;
        }
        return false;
    }

    public SZamowienie IsZamowienie(SZamowienie nowy) {

        for (SZamowienie p : zamowieniadostawcy) {
            if (nowy.equals(p)) {
                return p;
            }
        }
        return null;

    }
    /*
     public Zamowienie addZamowienie(Zamowienie nowy) {
     if (IsZamowienie(nowy)==null) {
     this.zamowieniadostawcy.add(nowy);
     return nowy;
     }
     return null;
     }
     */

    public void addZamowienie1(SZamowienie nowy) {
        this.zamowieniadostawcy.add(nowy);
    }

    public ArrayList<String> getprodukty() {
        ArrayList<String> produkty = new ArrayList<String>();
        Iterator<SProdukt_Dostawca> help = this.produktydostawcy.iterator();
        while (help.hasNext()) {
            SProdukt_Dostawca next = help.next();
            produkty.add(next.toString2());
        }
        return produkty;
    }

}
