/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;


import java.util.ArrayList;
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
public class Produkt extends Warstwa_Biznesowa1.Produkt1 {

    public Produkt(String[] dane) {
     super (dane);
     zapytaniaoprodukt=new ArrayList();
     pozycje = new ArrayList();
    }
    public Produkt() {
        
    }

  

   
 @OneToMany(mappedBy = "produkt", cascade = CascadeType.PERSIST)
    private List<Zapytania> zapytaniaoprodukt;

    public List<Zapytania> getZapytaniaoprodukt() {
        return zapytaniaoprodukt;
    }

    public void setZapytaniaoprodukt(List<Zapytania> val) {
        zapytaniaoprodukt = val;
    }
    @OneToMany(mappedBy = "Produkt")
    private List<Pozycja_zamowienia> pozycje;

    public void setPozycje(List<Pozycja_zamowienia> pozycje) {
        this.pozycje = pozycje;
    }

    public List<Pozycja_zamowienia> getPozycje() {
        return pozycje;
    }
   
 
   

    

}
