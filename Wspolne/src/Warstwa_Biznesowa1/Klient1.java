/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_Biznesowa1;

import javax.persistence.Entity;

/**
 *
 * @author Grzesiek
 */
@Entity


public class Klient1 extends Encja {

    protected String Adres_1;
    protected String Adres_2;
    protected String NrTelefon;

    /*@Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     public Long getId() {
     return id;
     }

     public void setId(Long id) {
     this.id = id;
     }
     */
    public Klient1(String[] dane) {

        Adres_1 = dane[2];
        Adres_2 = dane[3];
        NrTelefon = dane[4];

    }

    public Klient1() {
    }
    
   
    public String getAdres_1() {
        return Adres_1;
    }

    public void setAdres_1(String Adres_1) {
        this.Adres_1 = Adres_1;
    }

    public String getAdres_2() {
        return Adres_2;
    }

    public void setAdres_2(String Adres_2) {
        this.Adres_2 = Adres_2;
    }

    public String getNrTelefon() {
        return NrTelefon;
    }

    public void setNrTelefon(String NrTelefon) {
        this.NrTelefon = NrTelefon;
    }

    @Override
    public String toString() {
        String pom = ", Adres: " + this.Adres_1 + "\n" + this.Adres_2;
        return pom;
    }

}
