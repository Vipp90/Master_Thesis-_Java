/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sklep;


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
public class SProdukt extends Warstwa_Biznesowa1.Produkt1 {



    @OneToMany(mappedBy = "mprodukt", cascade=CascadeType.PERSIST )
    private List<SProdukt_Dostawca> dostawcyproduktu;

    public List<SProdukt_Dostawca> getDostawcyproduktu() {
        return dostawcyproduktu;
    }

    public void setDostawcyproduktu(List<SProdukt_Dostawca> val) {
        dostawcyproduktu = val;
    }
   
   
    public SProdukt(String[] dane) {
     super (dane);
     dostawcyproduktu=new ArrayList();
    }

    public SProdukt() {
    }

   
     public boolean IsDostawca(SProdukt_Dostawca nowy) {
        SDostawca pom = nowy.getMdostawca();
        for (SProdukt_Dostawca p : dostawcyproduktu) {
            if (pom.equals(p.getMdostawca())) {
                return false;
            }
        }
        return true;

    }

    public boolean addProdukt(SProdukt_Dostawca nowy) {
        if (IsDostawca(nowy)) {
            this.dostawcyproduktu.add(nowy);
            return true;
        }
        return false;
    }
 
   

   

    public String toString2() {
        String pom = getNazwa() + ", Producent:  " + getProducent();
        return pom;
    }
   


}
