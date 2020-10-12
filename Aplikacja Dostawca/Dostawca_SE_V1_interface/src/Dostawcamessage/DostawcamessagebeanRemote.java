/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dostawcamessage;


import javax.ejb.Remote;
import javax.jms.ObjectMessage;

/**
 *
 * @author Grzesiek
 */
@Remote
public interface DostawcamessagebeanRemote  {
     public void Zapytanieoprodukt(int wybrany, String dane_dostawcy, String zapytanie);
     public String Wyslijzamowienie(String nrzamowienia);
   public String test();
     public void update_data();
    
}
