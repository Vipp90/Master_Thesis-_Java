/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import java.beans.Transient;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Grzesiek
 */
@Entity
@Table
public class Zapytania extends Warstwa_Biznesowa1.Zapytania1 {

    @ManyToOne
    private Klient mklient;

    public Klient getKlient() {
        return mklient;
    }

    public void setKlient(Klient val) {
        mklient = val;

    }

    @ManyToOne
    private Produkt produkt;

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt val) {
        produkt = val;

    }

    public Zapytania() {

    }

    public Zapytania(Klient klient, Produkt produkt, String nr_zam) {
        super(nr_zam);
        //mprodukt = prod;
        mklient = klient;

        produkt = produkt;

    }

    @Transient

    @Override
    public String toString() {
        return "Zapytanie o produkt:  " + produkt.getNazwa();
    }

}
