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
public class Produkt1 extends Encja {

    private String Nazwa;
    private String Kategoria;
    private String Producent;
    private float Cena;

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

    public Produkt1() {
    }
    
    
    public Produkt1(String[] dane) {
        Nazwa = dane[0];
        Kategoria = dane[1];
        Producent = dane[2];
        Cena = Float.parseFloat(dane[3]);

    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String Nazwa) {
        this.Nazwa = Nazwa;
    }

    public String getKategoria() {
        return Kategoria;
    }

    public void setKategoria(String Kategoria) {
        this.Kategoria = Kategoria;
    }

    public float getCena() {
        return Cena;
    }

    public void setCena(float Cena) {
        this.Cena = Cena;
    }

    public String getProducent() {
        return Producent;
    }

    public void setProducent(String Producent) {
        this.Producent = Producent;
    }

    @Override
    public boolean equals(Object ob) {
        String nazwa1 = getNazwa();
        String nazwa2 = ((Produkt1) ob).getNazwa();
        String producent1 = getProducent();
        String producent2 = ((Produkt1) ob).getProducent();
        boolean a = nazwa1.equals(nazwa2);
        boolean b = producent1.equals(producent2);
        String kategoria1 = getKategoria();
        String kategoria2 = ((Produkt1) ob).getKategoria();
        boolean c = kategoria1.equals(kategoria2);
        if (a && b && c) {
            return true;
        }
        return false;
    }

    public String toString2() {
        String pom = getNazwa() + ", Producent:  " + getProducent();
        return pom;
    }

    @Override
    public String toString() {
        return "Produkt{" + "Nazwa=" + Nazwa + ", Kategoria=" + Kategoria + ", Producent=" + Producent + ", Cena=" + Cena + ", id=" + getId() + '}';
    }
}
