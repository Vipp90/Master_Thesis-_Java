/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dostawcabusiness_tier;

import DostawcaWarstwa_integracji.TBase;
import Sklep.SPozycja_zamowienia;
import Sklep.SZamowienie;
import Warstwa_biznesowa.Fasada;
import java.io.Serializable;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author Zesio
 */
public class Odbior {

long koniect2 = 0;
String numer ="";

 
     public long getkoniect2() {
        return koniect2;
    }
        public String getnumer() {
        return numer;
    }
    public String OdbiorZamowienia(Message inMessage) throws JMSException {
        Fasada fasada = new Fasada();
        TBase base = new TBase(fasada);
        TextMessage textmessage = null;
        ObjectMessage objectmessage = null;
        
        String wynik = "";
        String wynik_dodawania_pozycji = "";
        if (inMessage instanceof TextMessage) {
            textmessage = (TextMessage) inMessage;
            base.update_data();

            return "Otrzymano test";

        } else if (inMessage instanceof ObjectMessage) {
            objectmessage = (ObjectMessage) inMessage;
            Serializable objectData = objectmessage.getObject();
            if (objectData instanceof SZamowienie) {
                
            
                base.update_data();

                SZamowienie zamowienie_klienta = new SZamowienie();
                zamowienie_klienta = (SZamowienie) objectData;

                wynik = fasada.dodaj_zamowienie1("1", zamowienie_klienta.getNr_zamówienia());
                //  System.out.println(wynik);
                base.dodaj_zamowienia();
numer =zamowienie_klienta.getNr_zamówienia();
                List<SPozycja_zamowienia> pozycje;
                pozycje = zamowienie_klienta.getPozycje();

                for (SPozycja_zamowienia pozycja : pozycje) {

                    String klient = "1";
                    String data[] = {pozycja.getMProduktDostawcy().getMprodukt().getNazwa(), pozycja.getMProduktDostawcy().getMprodukt().getProducent(), pozycja.getMProduktDostawcy().getMprodukt().getKategoria()};
                    wynik_dodawania_pozycji = fasada.dodaj_pozycje_zamowienia(data, klient, zamowienie_klienta.getNr_zamówienia(), pozycja.getIlosc());
                    //System.out.println(wynik_dodawania_pozycji);
                }
                base.dodaj_pozycje_zamowien();
                base.update_data();
               koniect2 = System.currentTimeMillis();
                return wynik;

            }
        }
        return wynik;
    }

}
