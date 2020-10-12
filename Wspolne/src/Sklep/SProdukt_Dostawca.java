/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sklep;

import Warstwa_Biznesowa1.Encja;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Grzesiek
 */
@Entity

@Table
public class SProdukt_Dostawca extends Encja  {
    
/*
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
*/
    private int ilosc; //liczba wszystkich produktów dostawcy

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @ManyToOne
    private SDostawca mdostawca;

    public SDostawca getMdostawca() {
        return mdostawca;
    }

    public void setMdostawca(SDostawca mdostawca) {
        this.mdostawca = mdostawca;
    }

    @ManyToOne
    private SProdukt mprodukt;

    public SProdukt getMprodukt() {
        return mprodukt;
    }

    public void setMprodukt(SProdukt mprodukt) {
        this.mprodukt = mprodukt;
    }

    @OneToMany(mappedBy = "mProduktDostawcy")
    private List<SPozycja_zamowienia> pozycje;

    public List<SPozycja_zamowienia> getPozycje() {
        return pozycje;
    }

    public void setPozycje(List<SPozycja_zamowienia> val) {
        pozycje = val;
    }

    @OneToMany(mappedBy = "produktdostawca", cascade = CascadeType.PERSIST)
    private List<SZapytania> zapytaniaoprodukt;

    public List<SZapytania> getZapytaniaoprodukt() {
        return zapytaniaoprodukt;
    }

    public void setZapytaniaoprodukt(List<SZapytania> val) {
        zapytaniaoprodukt = val;
    }
    
    public SProdukt_Dostawca() {
        setId(null); //prypisanie, które podkreśla to, co jest domyślne
    }

    public SProdukt_Dostawca(SProdukt prod, SDostawca dost, int ilosc) {
        mprodukt = prod;
        mdostawca = dost;
        this.ilosc=ilosc;
      }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SProdukt_Dostawca other = (SProdukt_Dostawca) obj;

        if (mdostawca.equals(other.mdostawca) && mprodukt.equals(other.mprodukt)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Produkt_Dostawca{" + "id=" + getId() + ", ilosc=" + ilosc + '}';
    }
    
    
  
    public String toString2() {
        return mprodukt.getNazwa().toString() +"," + mprodukt.getProducent().toString();
    }


   
   
}
