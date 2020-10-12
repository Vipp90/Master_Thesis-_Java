/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sklep;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Grzesiek
 */
@Entity

@Table
public class SZapytania extends Warstwa_Biznesowa1.Zapytania1 {

    @ManyToOne
    private SDostawca mdostawca;

    public SDostawca getDostawca() {
        return mdostawca;
    }

    public void setDostawca(SDostawca val) {
        mdostawca = val;

    }

    @ManyToOne
    private SProdukt_Dostawca produktdostawca;

    public SProdukt_Dostawca getProdukt_Dostawca() {
        return produktdostawca;
    }

    public void setProdukt_Dostawca(SProdukt_Dostawca val) {
        produktdostawca = val;

    }

    public SZapytania(String nr,SDostawca dostawca, SProdukt_Dostawca produkt) {
        //mprodukt = prod;
        super(nr);
        mdostawca = dostawca;

        produktdostawca = produkt;

    }

    public SZapytania() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SZapytania other = (SZapytania) obj;

        if (getTresc_zapytania().equals(other.getTresc_zapytania())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Zapytanie do dostawcy  " + mdostawca.getNazwa();
    }

}
