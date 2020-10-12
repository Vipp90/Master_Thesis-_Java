/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import javax.ejb.Remote;

/**
 *
 * @author Grzesiek
 */
@Remote
public interface messagebeanRemote {

    public void Zapytanieoprodukt(int wybrany, String dane_dostawcy, String zapytanie);
    public String Wyslijzamowienie(String nrzamowienia);
    public void update_data();
    public void test();
}
