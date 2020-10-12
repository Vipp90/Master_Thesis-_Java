/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sklep;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Grzesiek
 */
@Entity
@Table
public class SKlient extends Warstwa_Biznesowa1.Klient1 {

    private String Imie;
    private String Nazwisko;
    private String Pesel;

    public String getPesel() {
        return Pesel;
    }

    public void setPesel(String Pesel) {
        this.Pesel = Pesel;
    }

    public SKlient(String dane[]) {
        super(dane);
        Imie = dane[0];
        Nazwisko = dane[1];
        Pesel = dane[5];

    }

    public SKlient() {
    }
    

    public String getImie() {
        return Imie;
    }

    public void setImie(String Imie) {
        this.Imie = Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public void setNazwisko(String Nazwisko) {
        this.Nazwisko = Nazwisko;
    }

    @Override
    public String toString() {
        String pom = "\nImie: " + this.Imie + "Nazwisko: " + this.Nazwisko + super.toString();
        return pom;
    }

    @Override
    public boolean equals(Object ob) {

        String Pesel1 = getPesel();
        String Pesel2 = ((Sklep.SKlient) ob).getPesel();
        boolean a = false;
        a = Pesel1.equals(Pesel2);

        return a;

    }

}
