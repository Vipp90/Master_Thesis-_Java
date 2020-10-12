/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_Biznesowa1;

import java.beans.Transient;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author Grzesiek
 */
@Entity

public class Zapytania1 extends Encja {

    private String tresc_zapytania;
    private String Odpowiedz;

    public String getOdpowiedz() {
        return Odpowiedz;
    }

    public void setOdpowiedz(String Odpowiedz) {
        this.Odpowiedz = Odpowiedz;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private GregorianCalendar data;

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
    public Zapytania1(String nr_zam) {
        //mprodukt = prod;

        tresc_zapytania = nr_zam;

    }

    public Zapytania1() {
    }

    @Transient

    public GregorianCalendar getData() {
        return data;
    }

    public void setData1(GregorianCalendar data1) {
        this.data = data1;
    }

    public String getTresc_zapytania() {
        return tresc_zapytania;
    }

    public void setTresc_zapytania(String tresc_zapytania) {
        this.tresc_zapytania = tresc_zapytania;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zapytania1 other = (Zapytania1) obj;

        if (getTresc_zapytania().equals(other.getTresc_zapytania())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "o treści" + tresc_zapytania;
    }

}
